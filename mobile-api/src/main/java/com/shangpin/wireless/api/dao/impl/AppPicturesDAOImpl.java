package com.shangpin.wireless.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AppPicturesDAO;
import com.shangpin.wireless.api.domain.AppPictures;

@Repository("appPicturesDAO")
public class AppPicturesDAOImpl extends BaseDaoImpl<AppPictures> implements AppPicturesDAO {
}
