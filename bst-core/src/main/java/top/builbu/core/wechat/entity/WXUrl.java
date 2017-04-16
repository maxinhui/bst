package top.builbu.core.wechat.entity;

import top.builbu.core.wechat.utils.WeixinConfigUtils;



public class WXUrl {

	/**
	 * 微信oauth2页面授权并重定向是所需的路径
	 */
	public final static String oauth2_authorize_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";	

	/**
	 * 微信统一支付接口
	 */
	public final static String wxUrl="https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	/**
	 * 重定向路径
	 */
	public static String sendRedirect_url=WeixinConfigUtils.bathUrl+"/WX-Controller/sys/rest";
	
	/**
	 * oauth2授权接口中所需的会员接口路径 
	 * 生成签名是所需的URL 
	 */
	public final static String Ruri_url=WeixinConfigUtils.bathUrl+"/WX-Controller/sys/ruri";
	
	/**
	 * 企业授权
	 */
	public final static String oauth_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	
	/**
	 * 微信access_token 路径
	 */
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 微信JSAPITicket 路径
	 */
	public final static String ticket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		
	/**
	 * 微信code获取openId 路径
	 */
	public final static String oauth2_url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		
	/**
	 * 微信openId获取信息 路径
	 */
	public final static String open_aouth_url= "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		
	/**
	 * 会员基本信息
	 */
	public final static String scribe_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
}
