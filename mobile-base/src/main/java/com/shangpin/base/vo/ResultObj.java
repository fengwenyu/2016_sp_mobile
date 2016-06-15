package com.shangpin.base.vo;

import java.util.List;

/**
 * 结果vo content是Content<T> 例如:Content<T>为 Content<User> { code:0, message:xx,
 * content:{ list: [
 * {userid:"xx1",gender:"xx1","priceindex":"xx1","name":"xx1"},
 * {userid:"xx2",gender:"xx2","priceindex":"xx2","name":"xx2"},
 * {userid:"xxn",gender:"xxn","priceindex":"xxn","name":"xxn"} ] } }
 * 
 * @author zhanghongwei
 *
 * @param <T>
 * 
 * 不建议使用的原因： 
 * getList() getGallery() getLatestProductList()... 代码复用率低，
 * 解析时候会把不需要的对象解析一遍,解析无用对象占用的时间多。
 * 扩展性不好，每次如果有这种格式的json串都要创建一个getXXX()方法， 并且还要在Content类中创建get set 字段.
 * by:zhanghognwei
 * 尽可能的使用@see com.shangpin.base.vo.ResultObjMapList 来解决问题
 */
@Deprecated  
public class ResultObj<T> extends ResultBase {
	private static final long serialVersionUID = 1L;
	// 内容数据
    private Content<T> content;

    public Content<T> getContent() {
        return content;
    }

    public void setContent(Content<T> content) {
        this.content = content;
    }

    /**
     * 取得content下list对应集合
     * 
     * @return
     * @author zhanghongwei
     */
    public List<T> getList() {
        if (!isSuccess()) {
            return null;
        }
        Content<T> content = this.getContent();
        if (content == null) {
            return null;
        }
        List<T> list = content.getList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list;
    }
    
    public List<T> getGallery() {
    	if (!isSuccess()) {
            return null;
        }
        Content<T> content = this.getContent();
        if (content == null) {
            return null;
        }
        List<T> list = content.getGallery();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list;
	}
    
    public List<T> getLatestProductList() {
    	if (!isSuccess()) {
            return null;
        }
        Content<T> content = this.getContent();
        if (content == null) {
            return null;
        }
        List<T> list = content.getLatestProductList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list;
	}

	public List<T> getPromotion() {
		//  Auto-generated method stub
		if (!isSuccess()) {
            return null;
        }
        Content<T> content = this.getContent();
        if (content == null) {
            return null;
        }
        List<T> list = content.getPromotion();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list;
	}

}
