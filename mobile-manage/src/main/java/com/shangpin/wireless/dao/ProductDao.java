package com.shangpin.wireless.dao;

import com.shangpin.wireless.base.dao.ManageBaseDao;
import com.shangpin.wireless.domain.Product;

/**
 * @Author zhouyu
 * @CreatDate  2012-07-11
 */
public interface ProductDao extends ManageBaseDao<Product> {
	public final static String DAO_NAME = "com.shangpin.wireless.dao.impl.ProductDaoImpl";
}
