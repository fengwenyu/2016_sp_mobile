package com.shangpin.wireless.api.service;


import java.util.List;

import com.shangpin.wireless.api.domain.Token;

public interface TokenService {
 
	public static final String SERVICE_NAME = "com.shangpin.wireless.api.service.TokenService";
	/**
	 * 更新或者增加
	 * @author xupengcheng
	 * @createDate 2014-1-10 上午10:48:46
	 * @param token
	 */
	public void update(Token token) throws Exception;
	
	/**
	 * 增加
	 * @author xupengcheng
	 * @createDate 2014-1-10 上午10:48:46
	 * @param token
	 */
	public Token save(Token token) throws Exception;
	
	/**
	 * 根据HSQL查询
	 * @author xupengcheng
	 * @createDate 2014-1-10 上午10:57:16
	 * @param querySql
	 * @return
	 */
	public List find(String querySql) throws Exception; 
	
	/**
	 * 用新获取的token更新数据库token
	 * @author xupengcheng
	 * @createDate 2014-1-13 上午11:43:26
	 * @return
	 */
	public Token updateToken() throws Exception;
	/**
	 * 重置数据库token状态
	 * @author xupengcheng
	 * @createDate 2014-1-13 上午11:43:50
	 */
	public void resetTokenStatus() throws Exception;
	/**
	 * 从数据库获取状态为normal 的Token
	 * @author xupengcheng
	 * @createDate 2014-1-13 上午11:44:07
	 * @return
	 */
	public Token findToken() throws Exception;
    
    /**
	 * 验证token是否失效
	 * @author xupengcheng
	 * @createDate 2014-1-11 下午06:33:18
	 * @param tokenCode
	 * @return
	 */
	public boolean findValidaeToken(String tokenCode) throws Exception;
}
