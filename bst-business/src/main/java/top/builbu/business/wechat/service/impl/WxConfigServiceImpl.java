package top.builbu.business.wechat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import lombok.extern.slf4j.Slf4j;
import top.builbu.common.util.page.Pagination;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.ResultDO;
import top.builbu.core.wechat.entity.WXAccessTokenResult;
import top.builbu.core.wechat.entity.WXJsapiTicketResult;
import top.builbu.core.wechat.utils.Aouth2Utils;
import top.builbu.core.wechat.utils.AouthUtils;
import top.builbu.core.wechat.utils.WXUtil;
import top.builbu.business.wechat.dto.WxConfigDTO;
import top.builbu.business.wechat.entity.WxConfig;
import top.builbu.business.wechat.service.WxConfigService;
import top.builbu.business.wechat.repository.WxConfigMapper;

@Slf4j
@Service
public class WxConfigServiceImpl implements WxConfigService{
 
    @Autowired
	private WxConfigMapper wxConfigMapper;
	
	
	@Override
	public PageDTO<WxConfigDTO> selectByList(WxConfigDTO dto,Pagination page ){
	    PageDTO<WxConfigDTO> pageDo = new PageDTO<WxConfigDTO>();
	    List<WxConfigDTO> result = wxConfigMapper.selectByList(dto,page.getCurrentResult(),page.getPageSize());
		if(null!=result&&result.size()>0){
		    pageDo.setList(result);
		   
		}
		 pageDo.setPageCurrent(page.getPageCurrent());
		 pageDo.setPageSize(page.getPageSize());
		 pageDo.setTotal(wxConfigMapper.selectByCount(dto));
		return pageDo;
	}
	
    @Override
	public ResultDO<WxConfig> selectById(Long id){
	    ResultDO<WxConfig> resultDo = null;
	    if(null!=id&&!"".equals(id)){
	        WxConfig result = wxConfigMapper.selectByPrimaryKey(id);
	        if(null!=result){
	            resultDo = new ResultDO<>(result);
	        }else{
	            resultDo = new ResultDO<>(BaseResultCode.COMMON_NO_DATA,Boolean.FALSE);
	        }
	    }else{
	        resultDo = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
	    }
	    return resultDo;
	}
	
    @Override
    public ResultDO<?> save(WxConfigDTO dto){
    	ResultDO<?> result = null;
    	WxConfig record = new WxConfig(); 
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = wxConfigMapper.insert(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("wxConfigList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> update(WxConfigDTO dto){
    	ResultDO<?> result = null;
    	WxConfig record = new WxConfig();
    	BeanUtils.copyProperties(dto, record);
    	Integer rowId = wxConfigMapper.updateByPrimaryKey(record);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    		result.setTabid("wxConfigList");
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteById(Long id){
    	ResultDO<?> result = null;
    	Integer rowId = wxConfigMapper.deleteByPrimaryKey(id);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    	}
    	return result;
    }
    
    @Override
    public ResultDO<?> deleteByCheck(Long[] delids){
    	ResultDO<?> result = null;
    	Integer rowId = wxConfigMapper.deleteByAll(delids);
    	if(null != rowId && rowId > 0){
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_CHENGGONG,Boolean.TRUE);
    	}else{
    		result = new ResultDO<>(BaseResultCode.COMMON_MESSAGE_LOSE,Boolean.FALSE);
    		result.setCloseCurrent(Boolean.FALSE);
    	}
    	return result;
    }
    
    
    /**
     * 刷新accessToken
     */
	@Override
	public ResultDO<WxConfig> getToken(String pramToken){
		log.info("service getToken");
		ResultDO<WxConfig> result = new ResultDO<>(BaseResultCode.COMMON_SALE_RETURN,Boolean.FALSE);
		Long thisTime = System.currentTimeMillis()/1000;//当前时间
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryToken(pramToken);
		
			if(null!=wxConfig.getRefreshTime() && wxConfig.getRefreshTime()+wxConfig.getExpiresIn()>thisTime){//未超时
				result = new ResultDO<>(wxConfig);
			}else{			
				wxConfig.setRefreshTime(thisTime);
				WXAccessTokenResult accessToken = Aouth2Utils.getAccessToken(wxConfig.getCorpId(), wxConfig.getCorpSecret());
				if(null != accessToken.getAccess_token()  && !"".equals(accessToken.getAccess_token())){
					WXJsapiTicketResult ticket= Aouth2Utils.jsapiTicket(accessToken.getAccess_token());
					if(null != ticket.getTicket() && !"".equals(ticket.getTicket())){
						wxConfig.setAccessToken(accessToken.getAccess_token());
						wxConfig.setJsApiTicket(ticket.getTicket());
						Integer rowId = wxConfigMapper.updateByPrimaryKey(wxConfig);
						if(null!=rowId&&rowId>0){
							result = new ResultDO<>(wxConfig);
							log.info("access_token two:"+result);
						}
					}
				}
			}
		
		return result;
	}
	
	
	
	@Override
	public ResultDO<Map<String,Object>> getTiekct(String url,String pramToken) throws Exception{
		
		Long timestamp=System.currentTimeMillis() / 1000;//获取当前时间戳
		ResultDO<Map<String, Object>> result=null;
		WxConfig wxConfig = wxConfigMapper.selectByPrimaryToken(pramToken);
		if(null!=wxConfig){
			if(null!=wxConfig.getRefreshTime() && wxConfig.getRefreshTime()+wxConfig.getExpiresIn()>timestamp){//未超时
				log.info("未超时");
			}else{
				wxConfig.setRefreshTime(timestamp);
				WXAccessTokenResult accessToken = Aouth2Utils.getAccessToken(wxConfig.getCorpId(), wxConfig.getCorpSecret());
				if(null != accessToken.getAccess_token()  && !"".equals(accessToken.getAccess_token())){
					WXJsapiTicketResult ticket= Aouth2Utils.jsapiTicket(accessToken.getAccess_token());
					if(null != ticket.getTicket() && !"".equals(ticket.getTicket())){
						wxConfig.setAccessToken(accessToken.getAccess_token());
						wxConfig.setJsApiTicket(ticket.getTicket());
						Integer rowId = wxConfigMapper.updateByPrimaryKey(wxConfig);
						if(null==rowId||0==rowId){//修改失败
							result=new ResultDO<>(BaseResultCode.COMMON_DB_ERRORS,Boolean.FALSE);
							return result;
						}
					}
				}
				
			}
			
		}
		Map<String,Object> resultData=WXUtil.getResultData(wxConfig.getJsApiTicket(), url, wxConfig.getCorpId());
		result=new ResultDO<Map<String,Object>>(resultData);
		return result;
	}
}
