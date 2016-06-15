package com.shangpin.utils.redis;


/** 
 * redis公共接口
 * @date    2016年5月4日 <br/> 
 * @author   陈小峰
 * @since    JDK 7
 */
public interface  SPRedis {
	public static final String CHARSET = "UTF-8";
	public static final String LOCK="lk";
	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param seconds
	 */
	public  void expire(String key, int seconds) ;
	/**
	 * 设置默认过期时间
	 * 
	 * @param key
	 */
	public void expire(String key) ;
	
	/**
	 * 判断key是否存在
	 * 
	 * @param String
	 *            key
	 * @return boolean
	 * */
	public boolean exists(String key);
	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 状态码
	 * */
	public String set(String key, String value);
	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, byte[] value);
	
	/**
	 * 根据key获取记录
	 * 
	 * @param String
	 *            key
	 * @return 值
	 * */
	public String get(String key);
	
	/**
	 * 根据key获取记录
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key);
	
	/**
	 * key自增
	 * @param key
	 * @return
	 */
	public Long incr(String key);
	
	/**
	 * key递减
	 * @param key
	 * @return
	 */
	public long decr(String key);
	
	/**
	 * 用于分布式锁 ，锁住；
	 * <br/>过了超时时间，自动释放锁
	 * @param key 锁的key
	 * @param timeout 锁的超时时间,秒为单位;
	 * @see #unlock(String, int) 解锁
	 * @return 锁住成功与否
	 */
	public boolean lock(String key,int timeout);
	/**
	 * 判断是否加锁了 
	 * <br/>
	 * @param key 锁的key
	 * @see #lock(String, int) 锁
	 * @see #unlock(String, int) 解锁
	 * @return
	 */
	public boolean isLock(String key);
	/**
	 * 分布式锁，开锁 
	 * <br/>
	 * @param key 锁住时的key
	 * @param timeout 解锁超时时间（并不一定有作用），秒为单位
	 * @see #lock(String, int) 锁住
	 * @return 解锁成功与否
	 */
	public boolean unlock(String key,int timeout);
}
