package com.shangpin.wireless.api.service;

import java.util.HashMap;
import java.util.List;

import com.shangpin.wireless.domain.AppPictures;

public interface AppPicturesService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.AppPicturesServiceImpl";

	void update(AppPictures appPictures) throws Exception;

	void save(AppPictures appPictures) throws Exception;

	List<HashMap> executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	Integer executeSqlCount(String queryListSql) throws Exception;

	void upadte(AppPictures model) throws Exception;

	void delete(Long id) throws Exception;

	AppPictures findById(Long id);

	void add(AppPictures model) throws Exception;

}
