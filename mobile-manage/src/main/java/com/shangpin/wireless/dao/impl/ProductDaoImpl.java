package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.ProductDao;
import com.shangpin.wireless.domain.Product;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(ProductDao.DAO_NAME)
public class ProductDaoImpl extends ManageBaseDaoImpl<Product> implements ProductDao {

}
