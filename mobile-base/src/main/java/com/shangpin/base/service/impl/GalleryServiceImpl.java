package com.shangpin.base.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.GalleryService;
import com.shangpin.product.model.cbwfs.MobileAlterPic;
import com.shangpin.product.model.common.ContentBuilder;
import com.shangpin.product.service.intf.cbwfs.IspGalleryService;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private IspGalleryService galleryService;
	
	@Override
	public ContentBuilder<Map<String, List<MobileAlterPic>>> gallery(String type) {
		try {
			ContentBuilder<Map<String, List<MobileAlterPic>>> contentBuilder = galleryService.gallery(type, "");
			return contentBuilder;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
