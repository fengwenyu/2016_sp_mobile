package com.shangpin.utils;

public class JedisUtilFactory {
    
    /**
     * 获取默认的Jedis对象
     * @return
     */
    public static JedisUtil getDefaultJedisUtil(){
        return JedisUtil.getInstance();
    }

    /**
     * 获取优惠券对应的Jedis对象
     * @return
     */
    public static CouponJedisUtil getCouponJedisUtil(){
        return CouponJedisUtil.getInstance();
    }
    
    /**
     * 获取后台服务对应的Jedis对象
     * @return
     */
    public static ServiceJedisUtil getServiceJedisUtil(){
        return ServiceJedisUtil.getInstance();
    }
}
