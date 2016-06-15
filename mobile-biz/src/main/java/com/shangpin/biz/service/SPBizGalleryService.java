package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Gallery;

public interface SPBizGalleryService {
	
	/**
	 * 轮播图，移动端可以共用
	 * @param type 轮播图的位置
	 * @return
	 */
	public List<Gallery> galleries(String type);

}
