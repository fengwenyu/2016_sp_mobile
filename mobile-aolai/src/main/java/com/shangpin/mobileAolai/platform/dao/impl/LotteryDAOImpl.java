package com.shangpin.mobileAolai.platform.dao.impl;

import org.springframework.stereotype.Repository;

import com.shangpin.mobileAolai.base.model.Lottery;
import com.shangpin.mobileAolai.common.persistence.GenericDAOHibernateImpl;
import com.shangpin.mobileAolai.platform.dao.LotteryDAO;

@Repository("lotteryDAO")
public class LotteryDAOImpl extends GenericDAOHibernateImpl<Lottery, Long>
		implements LotteryDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8706772920292517438L;

	
}
