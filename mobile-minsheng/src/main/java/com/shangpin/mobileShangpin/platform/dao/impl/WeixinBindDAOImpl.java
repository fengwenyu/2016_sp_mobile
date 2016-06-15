package com.shangpin.mobileShangpin.platform.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.mobileShangpin.base.model.AccountWeixinBind;
import com.shangpin.mobileShangpin.common.persistence.GenericDAOHibernateImpl;
import com.shangpin.mobileShangpin.platform.dao.WeixinBindDAO;

@Repository("weixinBindDAO")
@SuppressWarnings("unchecked")
public class WeixinBindDAOImpl extends GenericDAOHibernateImpl<AccountWeixinBind, Long> implements WeixinBindDAO {
	private static final long serialVersionUID = 5262655422520580154L;

	@Override
	public long findWXBindBywxid(String weixinid) throws Exception {
		String sql = "select count(*) from AccountWeixinBind a where weixinid = ? and unbindTime is null";
		return (Long) getSession().createQuery(sql).setString(0, weixinid).uniqueResult();
	}

	public List<AccountWeixinBind> findWXBind(String weixinid) throws Exception {
		String sql = "from AccountWeixinBind a where weixinid = ? and unbindTime is null";
		return getSession().createQuery(sql).setString(0, weixinid).list();
	}
}
