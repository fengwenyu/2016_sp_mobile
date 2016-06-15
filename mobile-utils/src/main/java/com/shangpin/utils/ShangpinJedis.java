package com.shangpin.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.shangpin.commons.redis.IShangpinRedis;
import com.shangpin.commons.redis.ShangpinRedis;
import com.shangpin.commons.redis.ShangpinRedisFactory;
import com.shangpin.commons.redis.cluster.jedis.ShangpinRedisClusterFactory;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;
/**
 * 底层支持集群的Jedis
 * <p>Title:ShangpinJedis </p>
 * <p>Description: </p>
 * <p>Company:www.shangpin.com </p> 
 * @author yanxiaobin
 * @date 2016年5月11日 上午11:48:30
 */
public class ShangpinJedis extends Jedis{

	/**
	 * Redis组件连接接口
	 */
	private IShangpinRedis shangpinRedis;
	/**
	 * 应用编号
	 */
	private String applicationId;
	/**
	 * 集群编号
	 */
	private String clusterId;
	/**
	 * 构造器
	 */
	public ShangpinJedis(String applicationId, String clusterId) {
		this.applicationId = applicationId;
		this.clusterId = clusterId;
		ShangpinRedisClusterFactory clusterFactory = ShangpinRedisFactory.getClusterFactory();
		ShangpinRedis shangpinRedis2 = clusterFactory.getShangpinRedis(applicationId,clusterId);
		shangpinRedis = shangpinRedis2.getShangpinRedis();
	}
	/************************************************************************************************************************/
	@Override
	public Long msetnx(String... keysvalues) {
		return shangpinRedis.msetnx(keysvalues);
	}
	@Override
	public Long move(String key, int dbIndex) {
		return shangpinRedis.move(key, dbIndex);
	}
	@Override
	public String rename(String oldkey, String newkey) {
		return shangpinRedis.rename(oldkey, newkey);
	}
	@Override
	public String set(String key, String value, String nxxx, String expx, long time) {
		return shangpinRedis.set(key, value, nxxx, expx, time);
	}
	@Override
	public String set(String key, String value) {
		return shangpinRedis.set(key, value);
	}
	@Override
	public Long decr(String key) {
		return shangpinRedis.decr(key);
	}
	@Override
	public Set<String> keys(String pattern) {
		return shangpinRedis.keys(pattern);
	}
	@Override
	public Long expire(String key, int seconds) {
		return shangpinRedis.expire(key, seconds);
	}
	@Override
	public Long renamenx(String oldkey, String newkey) {
		return shangpinRedis.renamenx(oldkey, newkey);
	}
	@Override
	public String rename(byte[] oldkey, byte[] newkey) {
		return shangpinRedis.rename(oldkey, newkey);
	}
	@Override
	public Long expireAt(String key, long unixTime) {
		return shangpinRedis.expireAt(key, unixTime);
	}
	@Override
	public Long ttl(String key) {
		return shangpinRedis.ttl(key);
	}
	@Override
	public Long persist(String key) {
		return shangpinRedis.persist(key);
	}
	@Override
	public Long del(String... keys) {
		return shangpinRedis.del(keys);
	}
	@Override
	public Long del(byte[]... keys) {
		return shangpinRedis.del(keys);
	}
	@Override
	public Boolean exists(String key) {
		return shangpinRedis.exists(key);
	}
	@Override
	public List<String> sort(String key) {
		return shangpinRedis.sort(key);
	}
	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		return shangpinRedis.sort(key, sortingParameters);
	}
	@Override
	public String type(String key) {
		return shangpinRedis.type(key);
	}
	@Override
	public Long sadd(String key, String... members) {
		return shangpinRedis.sadd(key, members);
	}
	@Override
	public Long sadd(byte[] key, byte[]... members) {
		return shangpinRedis.sadd(key, members);
	}
	@Override
	public Long scard(String key) {
		return shangpinRedis.scard(key);
	}
	@Override
	public Set<String> sdiff(String... keys) {
		return shangpinRedis.sdiff(keys);
	}
	@Override
	public Long sdiffstore(String dstkey, String... keys) {
		return shangpinRedis.sdiffstore(dstkey, keys);
	}
	@Override
	public Set<String> sinter(String... keys) {
		return shangpinRedis.sinter(keys);
	}
	@Override
	public Long sinterstore(String dstkey, String... keys) {
		return shangpinRedis.sinterstore(dstkey, keys);
	}
	@Override
	public Boolean sismember(String key, String member) {
		return shangpinRedis.sismember(key, member);
	}
	@Override
	public Set<String> smembers(String key) {
		return shangpinRedis.smembers(key);
	}
	@Override
	public Set<byte[]> smembers(byte[] key) {
		return shangpinRedis.smembers(key);
	}
	@Override
	public Long smove(String srckey, String dstkey, String member) {
		return shangpinRedis.smove(srckey, dstkey, member);
	}
	@Override
	public String spop(String key) {
		return shangpinRedis.spop(key);
	}
	@Override
	public Long srem(String key, String... members) {
		return shangpinRedis.srem(key, members);
	}
	@Override
	public Set<String> sunion(String... keys) {
		return shangpinRedis.sunion(keys);
	}
	@Override
	public Long sunionstore(String dstkey, String... keys) {
		return shangpinRedis.sunionstore(dstkey, keys);
	}
	@Override
	public String srandmember(String key) {
		return shangpinRedis.srandmember(key);
	}
	@Override
	public Long zadd(String key, double score, String member) {
		return shangpinRedis.zadd(key, score, member);
	}
	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		return shangpinRedis.zadd(key, scoreMembers);
	}
	@Override
	public Long zcard(String key) {
		return shangpinRedis.zcard(key);
	}
	@Override
	public Long zcount(String key, double min, double max) {
		return shangpinRedis.zcount(key, min, max);
	}
	@Override
	public Double zincrby(String key, double score, String member) {
		return shangpinRedis.zincrby(key, score, member);
	}
	@Override
	public Set<String> zrange(String key, long start, long end) {
		return shangpinRedis.zrange(key, start, end);
	}
	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		return shangpinRedis.zrangeByScore(key, min, max);
	}
	@Override
	public Long zrank(String key, String member) {
		return shangpinRedis.zrank(key, member);
	}
	@Override
	public Long zrevrank(String key, String member) {
		return shangpinRedis.zrevrank(key, member);
	}
	@Override
	public Long zrem(String key, String... members) {
		return shangpinRedis.zrem(key, members);
	}
	@Override
	public Long del(String key) {
		return shangpinRedis.del(key);
	}
	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		return shangpinRedis.zremrangeByRank(key, start, end);
	}
	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		return shangpinRedis.zremrangeByScore(key, start, end);
	}
	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		return shangpinRedis.zrevrange(key, start, end);
	}
	@Override
	public Double zscore(String key, String member) {
		return shangpinRedis.zscore(key, member);
	}
	@Override
	public Long hdel(String key, String... fields) {
		return shangpinRedis.hdel(key, fields);
	}
	@Override
	public Boolean hexists(String key, String field) {
		return shangpinRedis.hexists(key, field);
	}
	@Override
	public String hget(String key, String field) {
		return shangpinRedis.hget(key, field);
	}
	@Override
	public byte[] hget(byte[] key, byte[] field) {
		return shangpinRedis.hget(key, field);
	}
	@Override
	public Map<String, String> hgetAll(String key) {
		return shangpinRedis.hgetAll(key);
	}
	@Override
	public Long hset(String key, String field, String value) {
		return shangpinRedis.hset(key, field, value);
	}
	@Override
	public Long hset(byte[] key, byte[] field, byte[] value) {
		return shangpinRedis.hset(key, field, value);
	}
	@Override
	public Long hsetnx(String key, String field, String value) {
		return shangpinRedis.hsetnx(key, field, value);
	}
	@Override
	public List<String> hvals(String key) {
		return shangpinRedis.hvals(key);
	}
	@Override
	public Long hincrBy(String key, String field, long value) {
		return shangpinRedis.hincrBy(key, field, value);
	}
	@Override
	public Set<String> hkeys(String key) {
		return shangpinRedis.hkeys(key);
	}
	@Override
	public Long hlen(String key) {
		return shangpinRedis.hlen(key);
	}
	@Override
	public List<String> hmget(String key, String... fields) {
		return shangpinRedis.hmget(key, fields);
	}
	@Override
	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		return shangpinRedis.hmget(key, fields);
	}
	@Override
	public String hmset(String key, Map<String, String> hash) {
		return shangpinRedis.hmset(key, hash);
	}
	@Override
	public String hmset(byte[] key, Map<byte[], byte[]> hash) {
		return shangpinRedis.hmset(key, hash);
	}
	@Override
	public String get(String key) {
		return shangpinRedis.get(key);
	}
	@Override
	public byte[] get(byte[] key) {
		return shangpinRedis.get(key);
	}
	@Override
	public String setex(String key, int seconds, String value) {
		return shangpinRedis.setex(key, seconds, value);
	}
	@Override
	public String setex(byte[] key, int seconds, byte[] value) {
		return shangpinRedis.setex(key, seconds, value);
	}
	@Override
	public Long setnx(String key, String value) {
		return shangpinRedis.setnx(key, value);
	}
	@Override
	public String set(byte[] key, byte[] value) {
		return shangpinRedis.set(key, value);
	}
	@Override
	public Long setrange(String key, long offset, String value) {
		return shangpinRedis.setrange(key, offset, value);
	}
	@Override
	public Long append(String key, String value) {
		return shangpinRedis.append(key, value);
	}
	@Override
	public Long decrBy(String key, long integer) {
		return shangpinRedis.decrBy(key, integer);
	}
	@Override
	public Long incrBy(String key, long integer) {
		return shangpinRedis.incrBy(key, integer);
	}
	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		return shangpinRedis.getrange(key, startOffset, endOffset);
	}
	@Override
	public String getSet(String key, String value) {
		return shangpinRedis.getSet(key, value);
	}
	@Override
	public List<String> mget(String... keys) {
		return shangpinRedis.mget(keys);
	}
	@Override
	public String mset(String... keysvalues) {
		return shangpinRedis.mset(keysvalues);
	}
	@Override
	public Long strlen(String key) {
		return shangpinRedis.strlen(key);
	}
	@Override
	public Long llen(byte[] key) {
		return shangpinRedis.llen(key);
	}
	@Override
	public String lset(byte[] key, long index, byte[] value) {
		return shangpinRedis.lset(key, index, value);
	}
	@Override
	public Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
		return shangpinRedis.linsert(key, where, pivot, value);
	}
	@Override
	public byte[] lindex(byte[] key, long index) {
		return shangpinRedis.lindex(key, index);
	}
	@Override
	public byte[] lpop(byte[] key) {
		return shangpinRedis.lpop(key);
	}
	@Override
	public String rpop(String key) {
		return shangpinRedis.rpop(key);
	}
	@Override
	public Long rpush(String key, String... strings) {
		return shangpinRedis.rpush(key, strings);
	}
	@Override
	public Long rpush(byte[] key, byte[]... strings) {
		return shangpinRedis.rpush(key, strings);
	}
	@Override
	public Long lpush(byte[] key, byte[]... strings) {
		return shangpinRedis.lpush(key, strings);
	}
	@Override
	public List<String> lrange(String key, long start, long end) {
		return shangpinRedis.lrange(key, start, end);
	}
	@Override
	public List<byte[]> lrange(byte[] key, long start, long end) {
		return shangpinRedis.lrange(key, start, end);
	}
	@Override
	public Long lrem(byte[] key, long count, byte[] value) {
		return shangpinRedis.lrem(key, count, value);
	}
	@Override
	public String ltrim(byte[] key, long start, long end) {
		return shangpinRedis.ltrim(key, start, end);
	}
	@Override
	public Double incrByFloat(String key, double value) {
		return shangpinRedis.incrByFloat(key, value);
	}
	@Override
	public Long incr(String key) {
		return shangpinRedis.incr(key);
	}
	@Override
	public Double hincrByFloat(String key, String field, double value) {
		return shangpinRedis.hincrByFloat(key, field, value);
	}
	@Override
	public Long lpush(String key, String... strings) {
		return shangpinRedis.lpush(key, strings);
	}
	@Override
	public Long llen(String key) {
		return shangpinRedis.llen(key);
	}
	@Override
	public String ltrim(String key, long start, long end) {
		return shangpinRedis.ltrim(key, start, end);
	}
	@Override
	public String lindex(String key, long index) {
		return shangpinRedis.lindex(key, index);
	}
	@Override
	public String lset(String key, long index, String value) {
		return shangpinRedis.lset(key, index, value);
	}
	@Override
	public Long lrem(String key, long count, String value) {
		return shangpinRedis.lrem(key, count, value);
	}
	@Override
	public String lpop(String key) {
		return shangpinRedis.lpop(key);
	}
	@Override
	public String rpoplpush(String srckey, String dstkey) {
		return shangpinRedis.rpoplpush(srckey, dstkey);
	}
	@Override
	public Set<String> spop(String key, long count) {
		return shangpinRedis.spop(key, count);
	}
	@Override
	public List<String> srandmember(String key, int count) {
		return shangpinRedis.srandmember(key, count);
	}
	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		return shangpinRedis.zadd(key, score, member, params);
	}
	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
		return shangpinRedis.zadd(key, scoreMembers, params);
	}
	@Override
	public Double zincrby(String key, double score, String member, ZIncrByParams params) {
		return shangpinRedis.zincrby(key, score, member, params);
	}
	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		return shangpinRedis.zrangeWithScores(key, start, end);
	}
	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		return shangpinRedis.zrevrangeWithScores(key, start, end);
	}
	@Override
	public List<String> blpop(int timeout, String... keys) {
		return shangpinRedis.blpop(timeout, keys);
	}
	@Override
	public Long sort(String key, SortingParams sortingParameters, String dstkey) {
		return shangpinRedis.sort(key, sortingParameters, dstkey);
	}
	@Override
	public Long sort(String key, String dstkey) {
		return shangpinRedis.sort(key, dstkey);
	}
	@Override
	public List<String> brpop(int timeout, String... keys) {
		return shangpinRedis.brpop(timeout, keys);
	}
	@Override
	public Long zcount(String key, String min, String max) {
		return shangpinRedis.zcount(key, min, max);
	}
	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		return shangpinRedis.zrangeByScore(key, min, max);
	}
	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		return shangpinRedis.zrangeByScore(key, min, max);
	}
	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		return shangpinRedis.zrangeByScore(key, min, max);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max);
	}
	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		return shangpinRedis.zrevrangeByScore(key, max, min);
	}
	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		return shangpinRedis.zrevrangeByScore(key, max, min);
	}
	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		return shangpinRedis.zrevrangeByScore(key, max, min);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min);
	}
	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		return shangpinRedis.zrevrangeByScore(key, max, min);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min);
	}
	@Override
	public Long zunionstore(String dstkey, ZParams params, String... sets) {
		return shangpinRedis.zunionstore(dstkey, sets);
	}
	@Override
	public Long zunionstore(String dstkey, String... sets) {
		return shangpinRedis.zunionstore(dstkey, sets);
	}
	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		return shangpinRedis.zremrangeByScore(key, start, end);
	}
	@Override
	public Long zinterstore(String dstkey, String... sets) {
		return shangpinRedis.zinterstore(dstkey, sets);
	}
	@Override
	public Long zinterstore(String dstkey, ZParams params, String... sets) {
		return shangpinRedis.zinterstore(dstkey, sets);
	}
	@Override
	public Long zlexcount(String key, String min, String max) {
		return shangpinRedis.zlexcount(key, min, max);
	}
	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		return shangpinRedis.zrangeByLex(key, min, max);
	}
	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		return shangpinRedis.zrevrangeByLex(key, max, min);
	}
	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		return shangpinRedis.zrangeByLex(key, min, max);
	}
	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		return shangpinRedis.zrevrangeByLex(key, max, min);
	}
	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		return shangpinRedis.zremrangeByLex(key, min, max);
	}
	@Override
	public Long lpushx(String key, String... string) {
		return shangpinRedis.lpushx(key, string);
	}
	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		return shangpinRedis.pexpireAt(key, millisecondsTimestamp);
	}
	@Override
	public Long rpushx(String key, String... string) {
		return shangpinRedis.rpushx(key, string);
	}
	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		return shangpinRedis.linsert(key, where, pivot, value);
	}
	@Override
	public String brpoplpush(String source, String destination, int timeout) {
		return shangpinRedis.brpoplpush(source, destination, timeout);
	}
	@Override
	public Long bitpos(String key, boolean value) {
		return shangpinRedis.bitpos(key, value);
	}
	@Override
	public Long bitpos(String key, boolean value, BitPosParams params) {
		return shangpinRedis.bitpos(key, value, params);
	}
	@Override
	public Long bitcount(String key) {
		return shangpinRedis.bitcount(key);
	}
	@Override
	public Long bitcount(String key, long start, long end) {
		return shangpinRedis.bitcount(key, start, end);
	}
	@Override
	public Long bitop(BitOP op, String destKey, String... srcKeys) {
		return shangpinRedis.bitop(op, destKey, srcKeys);
	}
	@Override
	public Long pexpire(String key, long milliseconds) {
		return shangpinRedis.pexpire(key, milliseconds);
	}
	@Override
	public Long pttl(String key) {
		return shangpinRedis.pttl(key);
	}
	@Override
	public String psetex(String key, long milliseconds, String value) {
		return shangpinRedis.psetex(key, milliseconds, value);
	}
	@Override
	public String set(String key, String value, String nxxx) {
		return shangpinRedis.set(key, value, nxxx);
	}
	@Override
	public String set(String key, String value, String nxxx, String expx, int time) {
		return shangpinRedis.set(key, value, nxxx, expx, time);
	}
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		return shangpinRedis.hscan(key, cursor);
	}
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor, ScanParams params) {
		return shangpinRedis.hscan(key, cursor, params);
	}
	@Override
	public ScanResult<String> sscan(String key, int cursor) {
		return shangpinRedis.sscan(key, cursor);
	}
	@Override
	public ScanResult<String> sscan(String key, int cursor, ScanParams params) {
		return shangpinRedis.sscan(key, cursor, params);
	}
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		return shangpinRedis.hscan(key, cursor);
	}
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
		return shangpinRedis.hscan(key, cursor, params);
	}
	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		return shangpinRedis.sscan(key, cursor);
	}
	@Override
	public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
		return shangpinRedis.sscan(key, cursor, params);
	}
	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		return shangpinRedis.zscan(key, cursor);
	}
	@Override
	public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
		return shangpinRedis.zscan(key, cursor, params);
	}
	@Override
	public Long pfadd(String key, String... elements) {
		return shangpinRedis.pfadd(key, elements);
	}
	@Override
	public long pfcount(String key) {
		return 0;
	}
	@Override
	public long pfcount(String... keys) {
		return 0;
	}
	@Override
	public String pfmerge(String destkey, String... sourcekeys) {
		return shangpinRedis.pfmerge(destkey, sourcekeys);
	}
	@Override
	public List<String> blpop(int timeout, String key) {
		return shangpinRedis.blpop(timeout, key);
	}
	@Override
	public List<String> brpop(int timeout, String key) {
		return shangpinRedis.brpop(timeout, key);
	}
	@Override
	public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long time) {
		return shangpinRedis.set(key, value, nxxx, expx, time);
	}
	@Override
	public Long exists(byte[]... keys) {
		return shangpinRedis.exists(keys);
	}
	@Override
	public Boolean exists(byte[] key) {
		return shangpinRedis.exists(key);
	}
	@Override
	public Long del(byte[] key) {
		return shangpinRedis.del(key);
	}
	@Override
	public String type(byte[] key) {
		return shangpinRedis.type(key);
	}
	@Override
	public Long renamenx(byte[] oldkey, byte[] newkey) {
		return shangpinRedis.renamenx(oldkey, newkey);
	}
	@Override
	public Long expire(byte[] key, int seconds) {
		return shangpinRedis.expire(key, seconds);
	}
	@Override
	public Long expireAt(byte[] key, long unixTime) {
		return shangpinRedis.expireAt(key, unixTime);
	}
	@Override
	public Long ttl(byte[] key) {
		return shangpinRedis.ttl(key);
	}
	@Override
	public Long move(byte[] key, int dbIndex) {
		return shangpinRedis.move(key, dbIndex);
	}
	@Override
	public byte[] getSet(byte[] key, byte[] value) {
		return shangpinRedis.getSet(key, value);
	}
	@Override
	public List<byte[]> mget(byte[]... keys) {
		return shangpinRedis.mget(keys);
	}
	@Override
	public Long setnx(byte[] key, byte[] value) {
		return shangpinRedis.setnx(key, value);
	}
	@Override
	public String mset(byte[]... keysvalues) {
		return shangpinRedis.mset(keysvalues);
	}
	@Override
	public Long msetnx(byte[]... keysvalues) {
		return shangpinRedis.msetnx(keysvalues);
	}
	@Override
	public Long decrBy(byte[] key, long integer) {
		return shangpinRedis.decrBy(key, integer);
	}
	@Override
	public Long decr(byte[] key) {
		return shangpinRedis.decr(key);
	}
	@Override
	public Long incrBy(byte[] key, long integer) {
		return shangpinRedis.incrBy(key, integer);
	}
	@Override
	public Double incrByFloat(byte[] key, double integer) {
		return shangpinRedis.incrByFloat(key, integer);
	}
	@Override
	public Long incr(byte[] key) {
		return shangpinRedis.incr(key);
	}
	@Override
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		return shangpinRedis.hsetnx(key, field, value);
	}
	@Override
	public Long hincrBy(byte[] key, byte[] field, long value) {
		return shangpinRedis.hincrBy(key, field, value);
	}
	@Override
	public Double hincrByFloat(byte[] key, byte[] field, double value) {
		return shangpinRedis.hincrByFloat(key, field, value);
	}
	@Override
	public Boolean hexists(byte[] key, byte[] field) {
		return shangpinRedis.hexists(key, field);
	}
	@Override
	public Long hdel(byte[] key, byte[]... fields) {
		return shangpinRedis.hdel(key, fields);
	}
	@Override
	public Long hlen(byte[] key) {
		return shangpinRedis.hlen(key);
	}
	@Override
	public Set<byte[]> hkeys(byte[] key) {
		return shangpinRedis.hkeys(key);
	}
	@Override
	public List<byte[]> hvals(byte[] key) {
		return shangpinRedis.hvals(key);
	}
	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		return shangpinRedis.hgetAll(key);
	}
	@Override
	public byte[] rpop(byte[] key) {
		return shangpinRedis.rpop(key);
	}
	@Override
	public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
		return shangpinRedis.rpoplpush(srckey, dstkey);
	}
	@Override
	public Long srem(byte[] key, byte[]... member) {
		return shangpinRedis.srem(key, member);
	}
	@Override
	public byte[] spop(byte[] key) {
		return shangpinRedis.spop(key);
	}
	@Override
	public Set<byte[]> spop(byte[] key, long count) {
		return shangpinRedis.spop(key, count);
	}
	@Override
	public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
		return shangpinRedis.smove(srckey, dstkey, member);
	}
	@Override
	public Long scard(byte[] key) {
		return shangpinRedis.scard(key);
	}
	@Override
	public Boolean sismember(byte[] key, byte[] member) {
		return shangpinRedis.sismember(key, member);
	}
	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		return shangpinRedis.sinter(keys);
	}
	@Override
	public Long sinterstore(byte[] dstkey, byte[]... keys) {
		return shangpinRedis.sinterstore(dstkey, keys);
	}
	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		return shangpinRedis.sdiff(keys);
	}
	@Override
	public Long sdiffstore(byte[] dstkey, byte[]... keys) {
		return shangpinRedis.sdiffstore(dstkey, keys);
	}
	@Override
	public byte[] srandmember(byte[] key) {
		return shangpinRedis.srandmember(key);
	}
	@Override
	public List<byte[]> srandmember(byte[] key, int count) {
		return shangpinRedis.srandmember(key, count);
	}
	@Override
	public Long zadd(byte[] key, double score, byte[] member) {
		return shangpinRedis.zadd(key, score, member);
	}
	@Override
	public Long zadd(byte[] key, double score, byte[] member, ZAddParams params) {
		return shangpinRedis.zadd(key, score, member, params);
	}
	@Override
	public Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
		return shangpinRedis.zadd(key, scoreMembers);
	}
	@Override
	public Long zadd(byte[] key, Map<byte[], Double> scoreMembers, ZAddParams params) {
		return shangpinRedis.zadd(key, scoreMembers, params);
	}
	@Override
	public Set<byte[]> zrange(byte[] key, long start, long end) {
		return shangpinRedis.zrange(key, start, end);
	}
	@Override
	public Double zincrby(byte[] key, double score, byte[] member) {
		return shangpinRedis.zincrby(key, score, member);
	}
	@Override
	public Double zincrby(byte[] key, double score, byte[] member, ZIncrByParams params) {
		return shangpinRedis.zincrby(key, score, member, params);
	}
	@Override
	public Long zrank(byte[] key, byte[] member) {
		return shangpinRedis.zrank(key, member);
	}
	@Override
	public Long zrevrank(byte[] key, byte[] member) {
		return shangpinRedis.zrevrank(key, member);
	}
	@Override
	public Set<byte[]> zrevrange(byte[] key, long start, long end) {
		return shangpinRedis.zrevrange(key, start, end);
	}
	@Override
	public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
		return shangpinRedis.zrangeWithScores(key, start, end);
	}
	@Override
	public Set<Tuple> zrevrangeWithScores(byte[] key, long start, long end) {
		return shangpinRedis.zrevrangeWithScores(key, start, end);
	}
	@Override
	public Long zcard(byte[] key) {
		return shangpinRedis.zcard(key);
	}
	@Override
	public Double zscore(byte[] key, byte[] member) {
		return shangpinRedis.zscore(key, member);
	}
	@Override
	public List<byte[]> sort(byte[] key) {
		return shangpinRedis.sort(key);
	}
	@Override
	public Long sort(byte[] key, byte[] dstkey) {
		return shangpinRedis.sort(key, dstkey);
	}
	@Override
	public Long zcount(byte[] key, double min, double max) {
		return shangpinRedis.zcount(key, min, max);
	}
	@Override
	public Long zcount(byte[] key, byte[] min, byte[] max) {
		return shangpinRedis.zcount(key, min, max);
	}
	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		return shangpinRedis.zrangeByScore(key, min, max);
	}
	@Override
	public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
		return shangpinRedis.zrangeByScore(key, min, max);
	}
	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
		return shangpinRedis.zrangeByScore(key, min, max, offset, count);
	}
	@Override
	public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
		return shangpinRedis.zrangeByScore(key, min, max, offset, count);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max, offset, count);
	}
	@Override
	public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
		return shangpinRedis.zrangeByScoreWithScores(key, min, max, offset, count);
	}
	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
		return shangpinRedis.zrevrangeByScore(key, max, min);
	}
	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
		return shangpinRedis.zrevrangeByScore(key, max, min);
	}
	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
		return shangpinRedis.zrevrangeByScore(key, max, min, offset, count);
	}
	@Override
	public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
		return shangpinRedis.zrevrangeByScore(key, max, min, offset, count);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min);
	}
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
		return shangpinRedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}
	@Override
	public Long zremrangeByRank(byte[] key, long start, long end) {
		return shangpinRedis.zremrangeByRank(key, start, end);
	}
	@Override
	public Long zremrangeByScore(byte[] key, double start, double end) {
		return shangpinRedis.zremrangeByScore(key, start, end);
	}
	@Override
	public Long zremrangeByScore(byte[] key, byte[] start, byte[] end) {
		return shangpinRedis.zremrangeByScore(key, start, end);
	}
	@Override
	public Long zunionstore(byte[] dstkey, byte[]... sets) {
		return shangpinRedis.zunionstore(dstkey, sets);
	}
	@Override
	public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
		return shangpinRedis.zunionstore(dstkey, params, sets);
	}
	@Override
	public Long zinterstore(byte[] dstkey, byte[]... sets) {
		return shangpinRedis.zinterstore(dstkey, sets);
	}
	@Override
	public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
		return shangpinRedis.zinterstore(dstkey, params, sets);
	}
	@Override
	public Long zlexcount(byte[] key, byte[] min, byte[] max) {
		return shangpinRedis.zlexcount(key, min, max);
	}
	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
		return shangpinRedis.zrangeByLex(key, min, max);
	}
	@Override
	public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
		return shangpinRedis.zrangeByLex(key, min, max, offset, count);
	}
	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
		return shangpinRedis.zrevrangeByLex(key, max, min);
	}
	@Override
	public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
		return shangpinRedis.zrevrangeByLex(key, max, min, offset, count);
	}
	@Override
	public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
		return shangpinRedis.zremrangeByLex(key, min, max);
	}
	@Override
	public Long strlen(byte[] key) {
		return shangpinRedis.strlen(key);
	}
	@Override
	public Long persist(byte[] key) {
		return shangpinRedis.persist(key);
	}
	@Override
	public Long rpushx(byte[] key, byte[]... string) {
		return shangpinRedis.rpushx(key, string);
	}
	@Override
	public Boolean setbit(byte[] key, long offset, boolean value) {
		return shangpinRedis.setbit(key, offset, value);
	}
	@Override
	public Boolean setbit(byte[] key, long offset, byte[] value) {
		return shangpinRedis.setbit(key, offset, value);
	}
	@Override
	public Long setrange(byte[] key, long offset, byte[] value) {
		return shangpinRedis.setrange(key, offset, value);
	}
	@Override
	public byte[] getrange(byte[] key, long startOffset, long endOffset) {
		return shangpinRedis.getrange(key, startOffset, endOffset);
	}
	@Override
	public Long pexpire(byte[] key, long milliseconds) {
		return shangpinRedis.pexpire(key, milliseconds);
	}
	@Override
	public Long pexpireAt(byte[] key, long millisecondsTimestamp) {
		return shangpinRedis.pexpireAt(key, millisecondsTimestamp);
	}
	@Override
	public ScanResult<Tuple> zscan(byte[] key, byte[] cursor, ScanParams params) {
		return shangpinRedis.zscan(key, cursor, params);
	}
	@Override
	public ScanResult<Tuple> zscan(byte[] key, byte[] cursor) {
		return shangpinRedis.zscan(key, cursor);
	}
	@Override
	public ScanResult<byte[]> sscan(byte[] key, byte[] cursor, ScanParams params) {
		return shangpinRedis.sscan(key, cursor, params);
	}
	@Override
	public ScanResult<byte[]> sscan(byte[] key, byte[] cursor) {
		return shangpinRedis.sscan(key, cursor);
	}
	@Override
	public ScanResult<Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor, ScanParams params) {
		return shangpinRedis.hscan(key, cursor, params);
	}
	@Override
	public ScanResult<Entry<byte[], byte[]>> hscan(byte[] key, byte[] cursor) {
		return shangpinRedis.hscan(key, cursor);
	}
	@Override
	public Long pfcount(byte[]... keys) {
		return shangpinRedis.pfcount(keys);
	}
	@Override
	public String pfmerge(byte[] destkey, byte[]... sourcekeys) {
		return shangpinRedis.pfmerge(destkey, sourcekeys);
	}
	@Override
	public long pfcount(byte[] key) {
		return shangpinRedis.pfcount(key);
	}
	@Override
	public Long pfadd(byte[] key, byte[]... elements) {
		return shangpinRedis.pfadd(key, elements);
	}
	@Override
	public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, int time) {
		return shangpinRedis.set(key, value, nxxx, expx, time);
	}
	@Override
	public String set(byte[] key, byte[] value, byte[] nxxx) {
		return shangpinRedis.set(key, value, nxxx);
	}
	@Override
	public Long pttl(byte[] key) {
		return shangpinRedis.pttl(key);
	}
	@Override
	public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
		return shangpinRedis.sort(key, sortingParameters);
	}
	@Override
	public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
		return shangpinRedis.sort(key, sortingParameters, dstkey);
	}
	@Override
	public Long append(byte[] key, byte[] value) {
		return shangpinRedis.append(key, value);
	}
	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		return shangpinRedis.sunion(keys);
	}
	@Override
	public Long sunionstore(byte[] dstkey, byte[]... keys) {
		return shangpinRedis.sunionstore(dstkey, keys);
	}
	@Override
	public Long zrem(byte[] key, byte[]... members) {
		return shangpinRedis.zrem(key, members);
	}
	@Override
	public List<byte[]> blpop(int timeout, byte[]... keys) {
		return shangpinRedis.blpop(timeout, keys);
	}
	@Override
	public List<byte[]> brpop(int timeout, byte[]... keys) {
		return shangpinRedis.brpop(timeout, keys);
	}
	@Override
	public Long lpushx(byte[] key, byte[]... string) {
		return shangpinRedis.lpushx(key, string);
	}
	@Override
	public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
		return shangpinRedis.brpoplpush(source, destination, timeout);
	}
	@Override
	public Long bitcount(byte[] key) {
		return shangpinRedis.bitcount(key);
	}
	@Override
	public Long bitcount(byte[] key, long start, long end) {
		return shangpinRedis.bitcount(key, start, end);
	}
	@Override
	public Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
		return shangpinRedis.bitop(op, destKey, srcKeys);
	}
	
	 
}
