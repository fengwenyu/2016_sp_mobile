package com.shangpin.wireless.api.service;

import java.util.List;

import com.shangpin.wireless.api.domain.AppPictures;

public interface AppPicturesService {

	public List<AppPictures> findAll() throws Exception;
	
	public List<AppPictures> findOne(String os) throws Exception;

	public List<AppPictures> findAolaiOne(String string) throws Exception;
}
