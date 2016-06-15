package com.shangpin.wireless.api.dao.impl;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.AutoReplyDao;
import com.shangpin.wireless.api.domain.AutoReply;
import org.springframework.stereotype.Repository;

/**
 * Created by cuibinqiang on 2015/12/9.
 */
@Repository(AutoReplyDao.DAO_NAME)
public class AutoReplyDaoImpl extends BaseDaoImpl<AutoReply> implements AutoReplyDao {
}
