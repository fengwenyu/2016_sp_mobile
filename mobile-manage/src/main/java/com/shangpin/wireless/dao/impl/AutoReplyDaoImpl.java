package com.shangpin.wireless.dao.impl;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.AutoReplyDao;
import com.shangpin.wireless.domain.AutoReply;
import org.springframework.stereotype.Repository;

/**
 * Created by cuibinqiang on 2015/12/4.
 */

@Repository(AutoReplyDao.DAO_NAME)
public class AutoReplyDaoImpl  extends ApiBaseDaoImpl<AutoReply> implements AutoReplyDao {


}
