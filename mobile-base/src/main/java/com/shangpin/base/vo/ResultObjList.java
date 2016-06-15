package com.shangpin.base.vo;

import java.util.List;

/**
 * 结果vo content是一个集合 例如：List<T>是List<User> { code:0, message:xx, content:[ {
 * userid:"xx",gender:"xx","priceindex":"xx","name":"xx"}, {
 * userid:"xx2",gender:"xx2","priceindex":"xx2","name":"xx2"}, {
 * userid:"xxn",gender:"xxn","priceindex":"xxn","name":"xxn"} ] }
 * 
 * @author zhanghongwei
 *
 * @param <T>
 */

public class ResultObjList<T> extends ResultBase {
	private static final long serialVersionUID = 1L;
	// 内容数据
    private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    /**
     * 取得content下对应集合
     * 
     * @return
     * @author zhanghongwei
     */
    public List<T> getList() {
        if (!this.isSuccess()) {
            return null;
        }
        List<T> content = this.getContent();
        if (content == null || content.size() < 1) {
            return null;
        }
        return content;
    }
}
