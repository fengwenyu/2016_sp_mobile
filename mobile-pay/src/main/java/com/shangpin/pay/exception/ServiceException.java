package com.shangpin.pay.exception;

import com.shangpin.pay.base.ErrType;


/**
 * 业务异常
 * User: liling
 * Date: 14-11-10
 * Time: 下午3:23
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -4465404647881061796L;
	private ErrType errType;
	public ServiceException() {
        super();
    }
	public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(ErrType errType,String msg) {
        super(msg);
        this.errType=errType;
    }
    public ServiceException(String msg, Throwable cause,ErrType errType) {
        super(msg,cause);
        this.errType=errType;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

	public ErrType getErrType() {
		return errType;
	}

    
}
