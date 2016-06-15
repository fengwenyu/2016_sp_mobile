package com.shangpin.mobileShangpin.platform.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.mobileShangpin.base.model.Account;
import com.shangpin.mobileShangpin.common.persistence.GenericDAOHibernateImpl;
import com.shangpin.mobileShangpin.platform.dao.AccountDAO;

@Repository("accountDAO")
@SuppressWarnings("unchecked")
public class AccountDAOImpl extends GenericDAOHibernateImpl<Account, Long>
		implements AccountDAO {

	private static final long serialVersionUID = -2477652355034306558L;

	public List<Account> getAccountByLoginName(String loginName)
			throws Exception {
		String hql = "from Account acc where acc.loginName=?";
		List<Account> list = this.getSession().createQuery(hql)
				.setString(0, loginName).list();
		return list;
	}

}
