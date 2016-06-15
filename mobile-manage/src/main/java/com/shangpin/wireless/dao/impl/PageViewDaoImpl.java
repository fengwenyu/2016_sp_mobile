package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ManageBaseDaoImpl;
import com.shangpin.wireless.dao.PageViewDao;
import com.shangpin.wireless.domain.PageView;
/**
 * @Author zhouyu
 * @CreatDate  2012-07-12
 */
@Repository(PageViewDao.DAO_NAME)
public class PageViewDaoImpl extends ManageBaseDaoImpl<PageView> implements PageViewDao {

}
