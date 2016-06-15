package com.shangpin.biz.bo.base;

import java.io.Serializable;

public class ResponseResult  implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = -8644962095954952046L;

	public String ResposeStatus;

       /// <summary>
       /// 由英文缩写描述组成 如 ForOrderSum-RowsCount-Error
       /// </summary>
       public String ErrorCode;

       /// <summary>
       /// 错误信息描述
       /// </summary>
       public String ErrorMessage;

	public String getResposeStatus() {
		return ResposeStatus;
	}

	public void setResposeStatus(String resposeStatus) {
		ResposeStatus = resposeStatus;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
       

}
