package com.inkyi.ius.enums;

public enum IusUrlEnum {
	
	STUTAS_NO("启用","1"),STATUS_OFF("禁用","0");
	
	private IusUrlEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public Integer getValues(){
		return Integer.valueOf(value);
	}
	
	
	
	
	
}
