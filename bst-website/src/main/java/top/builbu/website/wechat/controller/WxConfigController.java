package top.builbu.website.wechat.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.business.wechat.dto.WxConfigDTO;
import top.builbu.business.wechat.entity.WxConfig;
import top.builbu.business.wechat.service.MemberService;
import top.builbu.business.wechat.service.WxConfigService;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.dto.ResultCode;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.UserCode;
import top.builbu.common.util.page.Pagination;
import top.builbu.core.wechat.entity.WXOpenIdResult;
import top.builbu.core.wechat.utils.Aouth2Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/wxConfig")
public class WxConfigController {

	@Autowired
	private WxConfigService wxConfigService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/add")
	public String add(){
		log.info("for ：wxConfigAdd");
		return "/wechat/wxConfigAdd";
	}
	
	@RequestMapping("/forload")
	public String forload(HttpSession session,String forH){
		log.info("for ："+forH);
		return "/wechat/"+forH;
	}
	
	@RequestMapping("/selectByList")
	public String selectByList(HttpServletRequest request,WxConfigDTO dto,Pagination page){
		log.info(JSONObject.toJSONString(page));
		PageDTO<WxConfigDTO> result = null;
		try{
		    result = wxConfigService.selectByList(dto,page);
		    request.setAttribute("pageDTO", result);
		    request.setAttribute("searchDTO", dto);
		    return "/wechat/wxConfigList";
	    } catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			return ResultCode.ERROR;
		}
		
	}
	
	
	
	@RequestMapping("/selectById")
	public String selectById(HttpServletRequest request,Long id){
	  ResultDO<WxConfig> result = null;
	    try{
		    result = wxConfigService.selectById(id);
		    if(result.isSuccess()){
		       request.setAttribute("module",result.getModule());
		       return "/wechat/wxConfigEdit";
		    }else{
		       return ResultCode.ERROR;
		    }
		} catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			return ResultCode.ERROR;
		}
		
	}
	
	
    @ResponseBody
	@RequestMapping("/save")
	public ResultDO<?> save(WxConfigDTO dto){
		ResultDO<?> result = null;
		 try{
			 result = wxConfigService.save(dto);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/update")
    public ResultDO<?> update(WxConfigDTO dto){
    	ResultDO<?> result = null;
    	 try{
			 result = wxConfigService.update(dto);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
    }
    
    @ResponseBody
	@RequestMapping("/deleteById")
    public ResultDO<?> deleteById(Long id){
    	ResultDO<?> result = null;
    	 try{
			 result = wxConfigService.deleteById(id);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
    }
    
    @ResponseBody
	@RequestMapping("/deleteByCheck")
    public ResultDO<?> deleteByCheck(Long[] delids){
    	ResultDO<?> result = null;
   	 try{
   		 result  =  wxConfigService.deleteByCheck(delids);
		} catch (Exception e) {
		 log.info(ExceptionUtils.getStackTrace(e));
		 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		 result.setCloseCurrent(Boolean.FALSE);
		}
		 return result;
    }
    
    //入口 https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4da938af1b995dcf&redirect_uri=http://prestone90th.vbooline.net/bst-website/wxConfig/auth&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
    @RequestMapping("/auth")
    public void auth(HttpServletResponse res,HttpSession session,String code){
    	String url = "http://prestone90th.vbooline.net/bst/h5/ReadPage.html";
    	ResultDO<?> result = null;
    	WXOpenIdResult openId = null;
    	ResultDO<WxConfig> config = null;
    	try {
    		if(null != code && !"".equals(code)){
        		config = wxConfigService.getToken("bst");
        		if(config.isSuccess()){
        			WxConfig wxConfig = config.getModule();
        			openId = Aouth2Utils.getOpenId(wxConfig.getCorpId(), wxConfig.getCorpSecret(), code);
        			if(null != openId.getOpenid() && !"".equals(openId.getOpenid())){
        				result = memberService.selectByOpenId(openId.getOpenid());
        				if(!result.isSuccess()){
        					MemberDTO memberDTO = new MemberDTO();
            				memberDTO.setOpenId(openId.getOpenid());
            				memberDTO.setCreateTime(new Date());
            				memberDTO.setIsLink("N");
            				result = memberService.save(memberDTO);	
        				}        				
        			}else{
        				result = new ResultDO<>(BaseResultCode.COMMON_NO_USER,Boolean.FALSE);
        			}
        		}else{
        			result = new ResultDO<>(BaseResultCode.COMMON_WRONG_PARAMS,Boolean.FALSE);
        		}
        	}else{
        		result = new ResultDO<>(BaseResultCode.COMMON_NO_DATA,Boolean.FALSE);
        	}        	
		} catch (Exception e) {
			log.error(e.getStackTrace().toString());
			result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		}
    	
    	//判断跳转
    	if(result.isSuccess()){
    		session.setAttribute(UserCode.LOGINUSER, openId.getOpenid());
    		try {
				res.sendRedirect(url);
			} catch (IOException e) {
				
				log.error(e.getMessage());
			}
    	}else{
    		log.debug("错误");
    	}
    }
    
    
	@ResponseBody
	@RequestMapping(value="/token")
	public ResultDO<WxConfig> selectAccessToken(){
	    ResultDO<WxConfig> result = null;	
		try {
			result = wxConfigService.getToken("bst");
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));															
			result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		}
		return result;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/getTicket")
	public JSONObject getTicket(String url,HttpSession session){
		JSONObject json=new JSONObject();
		ResultDO<Map<String, Object>> result=new ResultDO<>(BaseResultCode.COMMON_DB_ERRORS,Boolean.FALSE);
		try {
			result=wxConfigService.getTiekct(url,"bst");
			json.put("data", result);
			//MemberDTO memberDTO =  (MemberDTO) session.getAttribute(Lile.USERKEY);
			
		} catch (Exception e) {
			log.error(e.getStackTrace().toString());
			return json;
		}
		return json;
	}
	
	//http://prestone90th.vbooline.net/bst-website/wxConfig/rs
	@RequestMapping("/rs")
	public void rs(HttpServletResponse res){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4da938af1b995dcf&redirect_uri=http://prestone90th.vbooline.net/bst-website/wxConfig/auth&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		try {
			res.sendRedirect(url);
		} catch (IOException e) {
			log.error("入口错误");
			e.printStackTrace();
		}
		
	}
}