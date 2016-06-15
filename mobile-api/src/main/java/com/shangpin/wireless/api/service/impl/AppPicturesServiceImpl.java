package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.AppPicturesDAO;
import com.shangpin.wireless.api.domain.AppPictures;
import com.shangpin.wireless.api.service.AppPicturesService;

@Service("appPicturesService")
public class AppPicturesServiceImpl implements AppPicturesService {

	@Resource
	private AppPicturesDAO appPicturesDAO;

	@Override
	public List<AppPictures> findAll() throws Exception {
		return appPicturesDAO.findAll("");
	}

	@SuppressWarnings("unchecked")
	public List<AppPictures> findOne(String os) throws Exception {
		return appPicturesDAO.executeHql(" from AppPictures where productType!=1 and ostype='" + os + "' order by createDate desc", "");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppPictures> findAolaiOne(String os) throws Exception {
		return appPicturesDAO.executeHql(" from AppPictures where productType=1 and ostype='" + os + "' order by createDate desc", "");
	}

}
