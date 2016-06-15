package com.shangpin.wireless.api.exception;
/**
 * 为找到Token异常，一般情况为Token正在被更新
 */
public class NotFoundTokenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundTokenException() {
		super();
	}

	public NotFoundTokenException(String msg) {
		super(msg);
	}
}
