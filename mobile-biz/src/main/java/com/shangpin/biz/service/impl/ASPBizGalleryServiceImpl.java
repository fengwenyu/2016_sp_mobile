package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Gallery;
import com.shangpin.biz.bo.GalleryList;
import com.shangpin.biz.service.ASPBizGalleryService;
import com.shangpin.biz.service.abstraction.AbstractBizGalleryService;

@Service
public class ASPBizGalleryServiceImpl extends AbstractBizGalleryService implements ASPBizGalleryService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ASPBizGalleryServiceImpl.class);

	@Override
	public List<Gallery> queryGalleryList(String type, String frames) {
		List<Gallery> gallery = new ArrayList<Gallery>();
		GalleryList galleryList = queryGallery(type, frames);
		if (gallery != null && !galleryList.getGallery().isEmpty()) {
			gallery = galleryList.getGallery();
		}
		return gallery;
	}

}

