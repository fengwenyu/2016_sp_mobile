package com.shangpin.wireless.dao.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.AppPicturesDao;
import com.shangpin.wireless.domain.AppPictures;



@Repository(AppPicturesDao.DAO_NAME)
public class AppPicturesDaoImpl  extends ApiBaseDaoImpl<AppPictures> implements  AppPicturesDao{



	@Override
	public AppPictures findById(Long id) {
		 
			Query query=getSession().createQuery("select t from AppPictures t where t.id=?");
			query.setLong(0,id);
			 return (AppPictures) query.uniqueResult();
		}
	

}
