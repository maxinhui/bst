package top.builbu.common.dto;

import java.io.Serializable;


/**
 * @ClassName: BaseResultDO
 * @Description: TODO 返回基础对象
 * @date 2016年3月7日 上午9:48:56
 *  
 */
public class BaseResultDO implements Serializable {
	private static final long serialVersionUID = 4455702538105064491L;
	private boolean success = true;
	private String statusCode;
	protected String message;
	private String tabid;
	private boolean closeCurrent = true;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTabid() {
		return tabid;
	}
	public void setTabid(String tabid) {
		this.tabid = tabid;
	}
	public boolean getCloseCurrent() {
		return closeCurrent;
	}
	public void setCloseCurrent(boolean closeCurrent) {
		this.closeCurrent = closeCurrent;
	}



	
	
}
