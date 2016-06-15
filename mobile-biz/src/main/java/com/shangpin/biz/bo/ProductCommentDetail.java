package com.shangpin.biz.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ProductCommentDetail
 * @Description:评论详情实体类
 * @author wangfeng
 * @date 2015年3月12日
 * @version 1.0
 */
public class ProductCommentDetail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1677826515112385697L;
    
    private String userIcon;//用户头像
    private String userId;//用户id
    private String userName;//用户昵称
    private String date;//评论时间
    private String desc;//评论内容
    private String stars;//星级
    private String productInfo;//商品尺码
    private String userInfo;//客户商品信息
    private List<String> tags;//客户评价的标签
    private List<String> pics;//图片
    private List<ProductCommentReply> reply;//回复内容
    public String getUserIcon() {
        return userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getStrDate() {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       return sdf.format(new Date(Long.valueOf(date)));
    }
    
    public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getProductInfo() {
        return productInfo;
    }
    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
    public String getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    
    public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
    public List<ProductCommentReply> getReply() {
        return reply;
    }
    public void setReply(List<ProductCommentReply> reply) {
        this.reply = reply;
    }


}
