package com.shangpin.wireless.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.TokenDAO;
import com.shangpin.wireless.api.domain.Token;

@Repository(TokenDAO.SERVICE_NAME)
public class TokenDAOImpl extends BaseDaoImpl<Token> implements TokenDAO {

	@Override
	public List<Token> findByCondition(String contidion) throws Exception {
		return getSession("").createQuery(contidion).list();
	}

}
