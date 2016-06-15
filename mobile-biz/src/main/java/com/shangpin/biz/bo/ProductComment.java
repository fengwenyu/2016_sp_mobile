package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ProductComment
 * @Description:评论详情实体类
 * @author wangfeng
 * @date 2015年3月12日
 * @version 1.0
 */
public class ProductComment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2045285649346474083L;

    private List<ProductCommentDetail> list;

    private List<Tag> tag;
    private String count;//评论总数

    public List<ProductCommentDetail> getList() {
        return list;
    }

    public void setList(List<ProductCommentDetail> list) {
        this.list = list;
    }

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
