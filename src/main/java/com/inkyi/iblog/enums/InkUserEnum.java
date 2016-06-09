package com.inkyi.iblog.enums;

public enum InkUserEnum {
	/** 用户是否有效:0-无效，1-有效 */
	VALID_YES(1),VALID_NO(0), 
	/** 用户是否激活:0-未激活，1-激活 */
	ACTIVATION_YES(1),ACTIVATION_NO(0),
	;
	
	private final Integer value;

	InkUserEnum(Integer value) {
        this.value = value;
    }
    
    public Integer getValue() {
        return value;
    }
}
