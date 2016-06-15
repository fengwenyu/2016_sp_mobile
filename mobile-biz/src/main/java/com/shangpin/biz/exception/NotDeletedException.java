package com.shangpin.biz.exception;

/** 
 * 不允许删除的异常
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * @since   2013年12月20日 下午4:12:32 
 */
public class NotDeletedException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7419811878588715532L;

	public NotDeletedException() {
		super();
	}

	public NotDeletedException(String message) {
		super(message);
	}

	public NotDeletedException(Throwable cause) {
		super(cause);
	}

	public NotDeletedException(String message, Throwable cause) {
		super(message, cause);
	}
}
