package top.builbu.core.wechat.utils;


import top.builbu.common.dto.ResultWeChat;
import top.builbu.core.wechat.entity.WXAccessTokenResult;
import top.builbu.core.wechat.entity.WXUserResult;

import com.alibaba.fastjson.JSONObject;


/**
 * 企业号授权
 * @author Administrator
 *
 */
public class AouthUtils {

	
	
	/**
	 * 企业号获取access_token
	 * @param corpid
	 * @param secrect
	 * @return
	 */
	public static WXAccessTokenResult getToken(String corpid,String secrect){
		String url="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT";
	
		String s=HttpXmlUtils.httpRequest(url.replace("ID",corpid).replace("SECRECT",secrect), "GET",null);
		WXAccessTokenResult result=  JSONObject.parseObject(s, WXAccessTokenResult.class);	
		return result;
	}
	
	
	/**
	 * 企业号员工ID
	 * @param accessToken
	 * @param code
	 * @return
	 */
	public static WXUserResult getUserID(String accessToken,String code){
		String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
		String s=HttpXmlUtils.httpRequest(url.replace("ACCESS_TOKEN",accessToken).replace("CODE",code), "GET",null);
		System.out.print(s);
		WXUserResult result=(WXUserResult) JSONObject.parseObject(s, WXUserResult.class);
		return result;
	}
	
	
	/**
	 * 通知腾讯员工可以关注
	 * @param accessToken
	 * @param userId
	 * @return
	 */
	public static ResultWeChat authsucc(String accessToken,String userId){
		String url="https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=ACCESS_TOKEN&userid=USERID";
		String s=HttpXmlUtils.httpRequest(url.replace("ACCESS_TOKEN",accessToken).replace("USERID",userId), "GET",null);
		System.out.print(s);
		ResultWeChat result=(ResultWeChat) JSONObject.parseObject(s,ResultWeChat.class);
		return result;
	}
	
	
	public static void main(String[] args) {
		getToken("wxab3d9bfacdaa09df", "yz_Watq2DzmNWUDxyZVD81PbTmuqeWjTGuAGpO6WOqmDkXtmUivfIjaAAUCVPT6R");
		//getUserID("fj9hecgzZJ8ZnxjRP6v0bk1KvJWHJU6MH8LMdH1Ot3DrXBSLIK_dsLakGRvnR_1p","a2ee86a3859c667173b3a0767f541803");
		//authsucc("fj9hecgzZJ8ZnxjRP6v0bk1KvJWHJU6MH8LMdH1Ot3DrXBSLIK_dsLakGRvnR_1p","Y111");
	}
}
