package com.shangpin.utils.redis.cluster;

import redis.clients.jedis.JedisCluster;
import redis.clients.util.SafeEncoder;

import com.shangpin.utils.redis.RedisConfig;
import com.shangpin.utils.redis.RedisCreator;
import com.shangpin.utils.redis.SPRedisAdapter;

/** 
 * 使用Reids Cluster的方式实现;
 * @date    2016年5月4日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public class SPRedisCluster extends SPRedisAdapter{
	JedisCluster cluster=null;
	int expireTime=-1;
	/**
	 * 构造器，
	 * @see RedisCreator#jedisCluster(RedisConfig) redis集群
	 * @param cfg redis配置参数
	 */
	public SPRedisCluster(RedisConfig cfg){
		cluster=RedisCreator.jedisCluster(cfg);
		expireTime=cfg.getDefaultTimeout();
	}
	/**
	 * 无参构造器
	 */
	public SPRedisCluster() {
		super();
	}

	@Override
	public void expire(String key, int seconds) {
		cluster.expire(key, seconds);
	}

	@Override
	public void expire(String key) {
		cluster.expire(key, expireTime);
	}

	@Override
	public boolean exists(String key) {
		return cluster.exists(key);
	}

	@Override
	public String set(String key, String value) {
		return cluster.set(key, value);
	}

	@Override
	public String set(String key, byte[] value) {
		return cluster.set(SafeEncoder.encode(key), value);
	}

	@Override
	public String get(String key) {
		return cluster.get(key);
	}

	@Override
	public byte[] get(byte[] key) {
		return cluster.get(key);
	}

	@Override
	public Long incr(String key) {
		return cluster.incr(key);
	}

	@Override
	public long decr(String key) {
		return cluster.decr(key);
	}

	@Override
	public boolean lock(String key, int timeout) {
		//1设置成功，0没有
		long rs=cluster.setnx(key, LOCK);
		if(rs==1){
			cluster.expire(key, timeout);
			return true;
		}
		return false;
	}

	@Override
	public boolean isLock(String key) {
		String value=cluster.get(key);
		if(value!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean unlock(String key, int timeout) {
		String value=cluster.get(key);
		if(value==null){
			//超时自动解锁
			return true;
		}else{
			long rs=cluster.del(key);
			if(rs==1){
				return true;
			}
		}
		return false;
	}
	
}
