package com.inkyi.iblog.exception;
/**
 * 用户运行异常类
 * @author InkYi
 *
 */
public class InkUserRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InkUserRuntimeException() {
		super();
	}

	public InkUserRuntimeException(String message) {
		super(message);
	}
	
	

}
