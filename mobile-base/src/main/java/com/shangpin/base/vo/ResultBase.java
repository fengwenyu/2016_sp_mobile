package com.shangpin.base.vo;

import java.io.Serializable;

/**
 * 解析json字符串格式对应的实体对象
 *
 * 
 * @author zhanghongwei
 *
 */

public class ResultBase implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 成功标准
    public static final String SUCCESS = "0";
    // 返回错误码，0为返回成功
    private String code;
    // 错误信息
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回结果是否成功
     * 
     * @return
     * @author zhanghongwei
     */
    public boolean isSuccess() {
        if (this.getCode() == null || !SUCCESS.equals(this.getCode())) {
            return false;
        }
        return true;
    }

}
