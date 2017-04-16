package top.builbu.core.wechat.enums;

public enum EventEnum {

	SUBSCRIBE("subscribe","订阅"),
	UNSUBSCRIBE("unsubscribe","取消订阅"),
	SCAN("SCAN","扫码"),
	LOCATION("LOCATION","地理位置"),
	CLICK("CLICK","自定义菜单"),
	VIEW("VIEW","跳转链接");
	
	private String key;
	
	private String value;
	
	EventEnum(String key,String value){
		this.setKey(key);
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
