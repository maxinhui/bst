package top.builbu.core.wechat.entity;
/**
 * 微信企業號用戶返回
 * @author Administrator
 *
 */
public class WXUserResult {

	
	private String UserId;//成员UserID 
	
	private String OpenId;//非企业成员的标识，对当前企业号唯一 
	
	private String DeviceId;
	
	private String errcode;
	
	private String errmsg;

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getOpenId() {
		return OpenId;
	}

	public void setOpenId(String openId) {
		OpenId = openId;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
