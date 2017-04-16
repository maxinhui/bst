package top.builbu.common.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class AntiRefresh implements Serializable {
	
	private static final long serialVersionUID = -8318096823300413363L;

	//服务器端随机代码
	private String code;
	
	//form上hidden input的name 和 存验证码session 的name
	public static final String name = "REFRESH_CODE";
	
	public String getName() {
		return name;
	}
	
	public String getCode(){
		return this.code;
	}
	
	public AntiRefresh(final HttpServletRequest request){
		create(request);
	}
	
	/**
	 * 生成一个防刷新验证码，放到form上
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public void create(final HttpServletRequest request){
		//生成随机4位编码存入session
		code = RandomUtil.random(4);

		List<String> pageCodes = (LinkedList<String>)request.getSession().getAttribute(name);
		
		if(pageCodes == null){
			pageCodes = new LinkedList<String>();
			request.getSession().setAttribute(name, pageCodes);
		}
		
		pageCodes.add(code);

	}
	
	/**
	 * 是否是刷新提交form.
	 * @param request
	 * @return true:刷新操作  false:正常操作
	 */
	@SuppressWarnings("unchecked")
	public static Boolean isRefresh(final HttpServletRequest request){

		//从request中取刷新码
		String codeRequest = StringUtil.null2String((String)request.getParameter(name));
		
		//从session中取上下文对应的刷新码
		List<String> pageCodes = (LinkedList<String>)request.getSession().getAttribute(name);
		if(null == pageCodes || !pageCodes.contains(codeRequest)){
			return true;
		}
		
		//移除匹配过的防刷码
		pageCodes.remove(codeRequest);
		codeRequest = null;
		
		//匹配找到seesion中对应的防刷码，此次操作为正常提交
		return false;
		
	}
	
}
