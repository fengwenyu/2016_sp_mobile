package com.shangpin.wireless.api.exception;

/**
 * Token过期异常，如果微信返回Token过期则抛出这个异常
 */
public class TokenExpiredException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenExpiredException() {
		super();
	}

	public TokenExpiredException(String msg) {
		super(msg);
	}
}
