package top.builbu.core.wechat.entity;

import top.builbu.common.dto.ResultWeChat;

/**
 * 微信公眾號jsTicket返回
 * @author Administrator
 *
 */
public class WXJsapiTicketResult extends ResultWeChat{


	
	private String ticket;
	
	private Integer expires_in;


	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	
	
}
