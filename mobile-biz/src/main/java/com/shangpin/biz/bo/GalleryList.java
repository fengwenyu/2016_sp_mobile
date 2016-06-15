package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: GalleryList
 * @Description:轮播图主站返回的数据结构
 * @author 秦迎春
 * @date 2014年10月22日 下午3:30:37
 * @version 1.0
 */
public class GalleryList implements Serializable {

	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String title;
	private List<Gallery> gallery;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the gallery
	 */
	public List<Gallery> getGallery() {
		return gallery;
	}

	/**
	 * @param gallery
	 *            the gallery to set
	 */
	public void setGallery(List<Gallery> gallery) {
		this.gallery = gallery;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
