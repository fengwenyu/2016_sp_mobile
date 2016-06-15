package com.shangpin.biz.bo.base;

/**
 * 结果vo content是一个实体时 例如:T实体代表User对象 { code:0, message:xx, result: {
 * userid:"xx",gender:"xx","priceindex":"xx","name":"xx" } }
 * 
 * @author zhanghongwei
 *
 * @param <T>
 */

public class ResultObjOneResult<T> extends ResultBase {
    /**
     * 
     */
    private static final long serialVersionUID = -5000497773316941961L;
    // 内容数据
    private T result;

    public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	/**
     * 取得result下对应实体
     * 
     * @return
     * @author zhanghongwei
     */
    public T getObj() {
        if (!this.isSuccess()) {
            return null;
        }
        T content = this.getResult();
        if (content == null) {
            return null;
        }
        return content;
    }
}
