package top.builbu.website.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.builbu.business.wechat.dto.MemberDTO;
import top.builbu.business.wechat.entity.Member;
import top.builbu.business.wechat.service.MemberService;
import top.builbu.common.dto.PageDTO;
import top.builbu.common.dto.ResultDO;
import top.builbu.common.dto.ResultCode;
import top.builbu.common.dto.BaseResultCode;
import top.builbu.common.dto.UserCode;
import top.builbu.common.util.page.Pagination;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("/add")
	public String add(){
		log.info("for ：memberAdd");
		return "/wechat/memberAdd";
	}
	
	
	@RequestMapping("/selectByList")
	public String selectByList(HttpServletRequest request,MemberDTO dto,Pagination page){
		log.info(JSONObject.toJSONString(page));
		PageDTO<MemberDTO> result = null;
		try{
		    result = memberService.selectByList(dto,page);
		    request.setAttribute("pageDTO", result);
		    request.setAttribute("searchDTO", dto);
		    return "/wechat/memberList";
	    } catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			return ResultCode.ERROR;
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping("/selectById")
	public String selectById(HttpServletRequest request,HttpSession session){
	  ResultDO<?> result = null;
	    try{
	    	String openId = (String) session.getAttribute(UserCode.LOGINUSER);
	    	if(null != openId && !"".equals(openId)){
	    		result = memberService.selectById(openId);
	    	}
		    
		} catch (Exception e) {
			log.info(ExceptionUtils.getStackTrace(e));
			result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		}
	    
		return JSONObject.toJSONString(result);
	}
	
	
    @ResponseBody
	@RequestMapping("/save")
	public ResultDO<?> save(MemberDTO dto){
		ResultDO<?> result = null;
		 try{
			 result = memberService.save(dto);
			} catch (Exception e) {
			 log.info(ExceptionUtils.getStackTrace(e));
			 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
			 result.setCloseCurrent(Boolean.FALSE);
			}
		 return result;
	}  
	
	
	@ResponseBody
	@RequestMapping("/create")
    public ResultDO<?> update(HttpSession session,MemberDTO dto){
    	ResultDO<?> result = null;
    	 try{
    		 String openId = (String) session.getAttribute(UserCode.LOGINUSER);
    		 if(null != openId && !"".equals(openId)){
    			 dto.setOpenId(openId);
    			 result = memberService.update(dto);
    		 }else{
    			 result = new ResultDO<>(BaseResultCode.COMMON_NO_CHECK,Boolean.FALSE);
    		 }
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
			 result = memberService.deleteById(id);
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
   		 result  =  memberService.deleteByCheck(delids);
		} catch (Exception e) {
		 log.info(ExceptionUtils.getStackTrace(e));
		 result = new ResultDO<>(BaseResultCode.COMMON_FAIL,Boolean.FALSE);
		 result.setCloseCurrent(Boolean.FALSE);
		}
		 return result;
    }
    
}