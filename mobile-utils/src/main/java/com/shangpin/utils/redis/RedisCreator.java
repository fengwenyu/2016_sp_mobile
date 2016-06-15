package com.shangpin.utils.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
 * redis 集群、连接池创建类
 * @date    2016年5月4日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public class RedisCreator {
	/**
	 * redis3.0以上的集群模式适用，创建一个redis集群对象<br/>
	 * @param redisCfg redis配置
	 * @return redis集群
	 */
	public static JedisCluster jedisCluster(RedisConfig redisCfg) {
		Set<HostAndPort> jedisClusterNodes = clusterNodes(redisCfg.getHost(),redisCfg.getPassword());
		GenericObjectPoolConfig cfg = poolCfg(redisCfg);
		JedisCluster jc = new JedisCluster(jedisClusterNodes,cfg);
		return jc;
	}
	/**
	 * redis3.0 以下的无集群模式情况下，jedis连接池 
	 * @param redisCfg redis配置
	 * @return
	 */
	public static JedisPool jedisPool(RedisConfig redisCfg){
		GenericObjectPoolConfig cfg = poolCfg(redisCfg);
		String host=redisCfg.getHost();
		int port=redisCfg.getPort();
		if(redisCfg.getHost().charAt(':')!=-1){
			String[] hostports=redisCfg.getHost().split(",");
			hostports=hostports[0].split(":");
			if(hostports.length>0){
				host=hostports[0];
				port=Integer.parseInt(hostports[1]);
			}
		}
		JedisPool jedisPool = new JedisPool(cfg, host, port,redisCfg.getDefaultTimeout(),redisCfg.getPassword());
		return jedisPool;
	}
	
	private static Set<HostAndPort> clusterNodes(String host,String passwd) {
		String[] hostport=host.split(",");
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>(hostport.length);
		for (String hp : hostport) {
			String[] h_p=hp.split(":");
			jedisClusterNodes.add(new HostAndPort(h_p[0], Integer.parseInt(h_p[1])));			
		}
		return jedisClusterNodes;
	}

	private static GenericObjectPoolConfig poolCfg(RedisConfig rcfg) {
		GenericObjectPoolConfig cfg= new JedisPoolConfig();
		cfg.setMaxTotal(rcfg.getMaxTotal());
		cfg.setTestOnBorrow(rcfg.isTestOnBorrow());
		cfg.setMaxIdle(rcfg.getMaxIdle());
		cfg.setMaxWaitMillis(rcfg.getMaxWaitMillis());
		cfg.setTestOnReturn(rcfg.isTestOnReturn());
		return cfg;
	}
}
