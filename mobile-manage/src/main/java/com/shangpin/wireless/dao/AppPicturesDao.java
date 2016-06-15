package com.shangpin.wireless.dao;


import com.shangpin.wireless.base.dao.ApiBaseDao;

import com.shangpin.wireless.domain.AppPictures;


public interface AppPicturesDao  extends ApiBaseDao<AppPictures> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.AppPicturesDaoImpl";
	

	AppPictures findById(Long id);
}
