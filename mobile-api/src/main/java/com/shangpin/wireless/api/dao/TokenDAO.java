package com.shangpin.wireless.api.dao;

import java.util.List;

import com.shangpin.wireless.api.base.dao.BaseDao;
import com.shangpin.wireless.api.domain.Token;

public interface TokenDAO extends BaseDao<Token> {
	public static final String SERVICE_NAME = "com.shangpin.wireless.api.dao.TokenDAO";
	public List<Token> findByCondition(String contidion) throws Exception;
}
