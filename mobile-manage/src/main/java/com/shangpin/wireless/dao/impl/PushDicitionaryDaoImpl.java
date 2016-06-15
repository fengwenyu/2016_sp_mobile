package com.shangpin.wireless.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.PushDictionaryDao;
import com.shangpin.wireless.domain.PushDictionary;

/**
 * zghw
 * 
 * @author zghw
 *
 */
@Repository(PushDictionaryDao.DAO_NAME)
public class PushDicitionaryDaoImpl extends ApiBaseDaoImpl<PushDictionary> implements PushDictionaryDao {

}
