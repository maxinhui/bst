package top.builbu.core.wechat.enums;

public enum TokenRefreshEnum {

	ID("刷新",1),
	TIME("刷新时间",7000);
	
	private String key;
	
	private Integer value;
	
	TokenRefreshEnum(String key,Integer value){
		this.key=key;
		this.value=value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
