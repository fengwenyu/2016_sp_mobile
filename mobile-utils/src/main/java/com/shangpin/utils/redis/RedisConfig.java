package com.shangpin.utils.redis;

import com.shangpin.utils.GlobalConstants;

/** 
 * redis连接池配置类<br/>
 * 如果是集群配置,那么host务必是ip:port,ip:port形式,如：<br/>
 * host=192.168.20.77:6379,192.168.20.100:6379<br/>
 * 如果是简单的jedispool，那么host可以是ip:port也可只有host，port可无,如：<br/>
 * host=192.168.20.77:6379或者host=192.168.20.77(port=6379)<br/>
 * <br/>默认设置：port： 6379,最大连接数：100，最大空闲数100，等待时间3s，过期时间无，默认超时时间3s
 * 
 * @date    2016年5月4日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public class RedisConfig {
	
	private int maxTotal=100;
	private int maxIdle=100;
	private int maxWaitMillis=3000;
	private int expireTime=-1;
	private int defaultTimeout=3000;
	private boolean testOnBorrow=false;
	private boolean testOnReturn=false;
	private String host;
	private int port=6379;
	private String password;
	
	public RedisConfig(int maxTotal, int maxIdle, int maxWait_millis,
			int expireTime, int defaultTimeout, boolean testOnBorrow,
			boolean testOnReturn, String host, int port, String password) {
		super();
		this.maxTotal = maxTotal;
		this.maxIdle = maxIdle;
		this.maxWaitMillis = maxWait_millis;
		this.expireTime = expireTime;
		this.defaultTimeout = defaultTimeout;
		this.testOnBorrow = testOnBorrow;
		this.testOnReturn = testOnReturn;
		this.host = host;
		this.port = port;
		this.password = password;
	}
	public RedisConfig() {
		super();
	}
	/**
	 * 获取配置文件中配置好的默认 redis的配置
	 * <br/>
	 * @return
	 */
	public static RedisConfig getConfigedRedisConfig(){
		RedisConfig redisCfg=new RedisConfig();
		redisCfg.setMaxTotal(GlobalConstants.REDIS_MAX_TOTAL);
		redisCfg.setMaxIdle(GlobalConstants.REDIS_MAX_IDLE);
		redisCfg.setMaxWaitMillis(GlobalConstants.REDIS_MAX_WAIT_MILLIS);
		redisCfg.setTestOnBorrow(GlobalConstants.REDIS_TEST_ON_BORROW);
		redisCfg.setTestOnReturn(GlobalConstants.REDIS_TEST_ON_RETURN);
		redisCfg.setHost(GlobalConstants.REDIS_HOST);redisCfg.setPort(GlobalConstants.REDIS_PORT);
		return redisCfg;
	}
	
	public int getMaxTotal() {
		return maxTotal;
	}
	/**
	 * 最大连接数 
	 * <br/>
	 * @param maxTotal
	 */
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	/**
	 * 最大空闲连接数 
	 * <br/>
	 * @param maxIdle
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public int getMaxWaitMillis() {
		return maxWaitMillis;
	}
	/**
	 * 最大等等时间 
	 * <br/>
	 * @param maxWait_millis
	 */
	public void setMaxWaitMillis(int maxWait_millis) {
		this.maxWaitMillis = maxWait_millis;
	}
	public int getExpireTime() {
		return expireTime;
	}
	/**
	 * 过期时间 
	 * <br/>
	 * @param expireTime
	 */
	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}
	public int getDefaultTimeout() {
		return defaultTimeout;
	}
	/**
	 * 默认超时时间 
	 * <br/>
	 * @param defaultTimeout
	 */
	public void setDefaultTimeout(int defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public boolean isTestOnReturn() {
		return testOnReturn;
	}
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
