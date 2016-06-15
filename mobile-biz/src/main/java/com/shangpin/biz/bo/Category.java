package com.shangpin.biz.bo;

import java.io.Serializable;


/** 
* @ClassName: Category 
* @Description: 商品类别实体类
* @author qinyingchun
* @date 2014年10月22日
* @version 1.0 
*/
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String channelCategoryNo;
	private String channelCategoryName;
	private String channelCategoryPic;
	private String channelCategoryPNo;
	
	

	public String getChannelCategoryPNo() {
		return channelCategoryPNo;
	}

	public void setChannelCategoryPNo(String channelCategoryPNo) {
		this.channelCategoryPNo = channelCategoryPNo;
	}

	public String getChannelCategoryNo() {
		return channelCategoryNo;
	}

	public void setChannelCategoryNo(String channelCategoryNo) {
		this.channelCategoryNo = channelCategoryNo;
	}

	public String getChannelCategoryName() {
		return channelCategoryName;
	}

	public void setChannelCategoryName(String channelCategoryName) {
		this.channelCategoryName = channelCategoryName;
	}

	public String getChannelCategoryPic() {
		return channelCategoryPic;
	}

	public void setChannelCategoryPic(String channelCategoryPic) {
		this.channelCategoryPic = channelCategoryPic;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
