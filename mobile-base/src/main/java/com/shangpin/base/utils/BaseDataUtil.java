package com.shangpin.base.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.vo.ResultBase;
import com.shangpin.utils.CacheOption;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JedisUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.ThreadVariableGlobal;

/**
 * 主站的结果处理
 * 
 * @author xupengcheng
 * 
 */
public class BaseDataUtil {

    private static JedisUtil jedisUtil = JedisUtil.getInstance();
    private final static Log log = LogFactory.getLog(BaseDataUtil.class);
    private static String BLACK_IP = "black";
    private static String WHITE_IP = "white";
    private static String IP_COUNT = "count";

    /**
     * 给定的json串中的code存在并且等于0，则表示true，其他都是false
     * 
     * @param json
     * @return
     */
    public static boolean isSuccess(String json) {
        ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
        return resultBase.isSuccess();
    }

    /**
     * 缓存主站接口数据
     * 
     * @param data
     *            主站接口返回的数据
     * @param key
     *            缓存key
     * @param method
     *            主站接口名
     */
    public static String cacheResult(String data, String key, String method) {
        try {
            int expire = PropertyUtil.getInt(method,0);
            if (expire != 0 && isSuccess(data)) {
                jedisUtil.set(key, data);
                jedisUtil.expire(key, expire);
            }
        } catch (Exception e) {
        }
        return data;
    }

    /**
     * 返回需要的接口数据 (主要用缓存来管理数据)
     * 
     * @param methodType
     *            使用的标识
     * @param method
     *            使用的方法名
     * @param params
     *            主站接口对应的url参数map
     * @param url
     *            主站接口对应的url
     * @return
     */
    public static String findData(String methodType, String method, Map<String, String> params, String url) {
        long start = System.currentTimeMillis();
        String key = calculateKey(methodType, method, params);
        String data = null;
        try {
            CacheOption cp = ThreadVariableGlobal.getCacheOption();
            if (cp != null) {
                // 从线程中取出缓存参数对应的策略来进行取数据
                switch (cp) {
                case MISS:
                    // 跳过不缓存
                    data = httpDoGet(url, params);
                    log.debug(methodType + ":" + method + " cached miss");
                    return data;
                case REFRESH:
                    // 刷新缓存
                    data = refreshData(key, method, params, url);
                    log.debug(methodType + ":" + method + " cached refresh");
                    return data;
                case HIT:
                    // 跳过去默认缓存设置
                    break;
                default:
                    break;
                }
            }
            // 以下为默认缓存设置取数据
            if (invalidKey(key)) {
                data = refreshData(key, method, params, url);
                return data;
            }
            data = getCache(key);
            long end = System.currentTimeMillis();
            log.debug(methodType + ":" + method + " get data from redis, cost time: " + (end - start) + "ms");
        } catch (Exception e) {
            data = httpDoGet(url, params);
            return data;
        }
        return data;
    }
    
    /**
     * 返回接口所需要的数据（主要用缓存来管理数据）主要是针对dubbox框架来缓存数据
     * @param methodType
     *              使用的标识
     * @param method
     *              使用的方法名称
     * @param params
     *              参数map
     * @param obj
     *              获得的反射方法所在的类
     * @param methodName
     *              所需要得到的方法的名称
     * @param args
     *              方法所需要的参数
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static String findData(String methodType, String method, Map<String, String> params, Object obj, String methodName, Object[] args) {
        long start = System.currentTimeMillis();
        String key = calculateKey(methodType, method, params);
        String data = null;
        try {
            
            Class[] argsClass = new Class[args.length];
            
            for(int i=0; i<args.length; i++) {
                argsClass[i] = args[i].getClass();
            }
            //根据方法的名称及参数反射获取相应的方法
            Method methodReflect = obj.getClass().getMethod(methodName, argsClass);
            
            CacheOption cp = ThreadVariableGlobal.getCacheOption();
            if (cp != null) {
                // 从线程中取出缓存参数对应的策略来进行取数据
                switch (cp) {
                case MISS:
                    // 跳过不缓存
                    data = JsonUtil.toJson(methodReflect.invoke(obj, args));
                    log.debug(methodType + ":" + method + " cached miss");
                    return data;
                case REFRESH:
                    // 刷新缓存
                    data = refreshData(key, method, params, obj, methodReflect, args);
                    log.debug(methodType + ":" + method + " cached refresh");
                    return data;
                case HIT:
                    // 跳过去默认缓存设置
                    break;
                default:
                    break;
                }
            }
            
            // 以下为默认缓存设置取数据
            if (invalidKey(key)) {
                data = refreshData(key, method, params, obj, methodReflect, args);
                return data;
            }
            
            data = getCache(key);
            long end = System.currentTimeMillis();
            log.debug(methodType + ":" + method + " get data from redis, cost time: " + (end - start) + "ms");
        
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return data;
    }
  
    /**
     * 计算出缓存key
     * 
     * @param methodType
     *            方法的类型
     * @param method
     *            方法名称
     * @param params
     *            url对应的参数Map
     * @return
     */
    public static String calculateKey(String methodType, String method, Map<String, String> params) {
        String key = JedisUtil.piecedKey(methodType, method, params);
        return key;
    }

    /**
     * 通过HTTP的get方法取得请求结果
     * 
     * @param url
     *            url地址
     * @param params
     *            url对应的参数Map
     * @return
     */
    public static String httpDoGet(String url, Map<String, String> params) {
        String result = HttpClientUtil.doGet(url, params);
        return result;
    }

    /**
     * 刷新缓存中的数据
     * 
     * @param key
     *            缓存key
     * @param method
     *            方法名
     * @param params
     *            url对应的参数Map
     * @param url
     *            url地址
     * @return
     */
    public static String refreshData(String key, String method, Map<String, String> params, String url) {
        deleteKey(key);
        String data = httpDoGet(url, params);
        BaseDataUtil.cacheResult(data, key, method);
        return data;
    }
    
    /**
     * 通过反射调用方法然后刷新缓存中的数据
     * @param key
     *          缓存key
     * @param method
     *          方法名
     * @param params
     *          url对应的参数Map
     * @param m
     *          需要反射调用的方法
     * @param obj
     *          反射调用的对象
     * @param args
     *          反射调用方法所需要的参数
     * @return
     */
    public static String refreshData(String key, String method, Map<String, String> params, Object obj, Method m, Object[] args) {
        deleteKey(key);
        String data = null;
        System.out.println(obj);
        try {
            data = JsonUtil.toJson(m.invoke(obj, args));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        BaseDataUtil.cacheResult(data, key, method);
        return data;
    }

    /**
     * 删除缓存中key对应的数据
     * 
     * @param key
     */
    public static void deleteKey(String key) {
        jedisUtil.new Keys().del(key);
    }

    /**
     * 验证key是否是无效的缓存key
     * 
     * @param key
     * @return
     */
    public static boolean invalidKey(String key) {
        return (!jedisUtil.exists(key) || jedisUtil.new Keys().ttl(key) == -1);
    }

    /**
     * 通过Key从缓存中取出数据
     * 
     * @param key
     * @return
     */
    public static String getCache(String key) {
        return jedisUtil.get(key);
    }

    /**
     * 是否黑名单
     * 
     * @param ip
     * @return
     */
    public static boolean isBlackIp(String ip) {
        return checkIpOfType(ip, BLACK_IP);
    }

    /**
     * 是否白名单
     * 
     * @param ip
     * @return
     */
    public static boolean isWhiteIp(String ip) {
        return checkIpOfType(ip, WHITE_IP);
    }

    /**
     * ip类型
     * 
     * @param ip
     * @param ipType
     * @return
     */
    private static boolean checkIpOfType(String ip, String ipType) {
        boolean flag = false;
        try {
            String type = jedisUtil.get(ip);
            if (type != null && type.equals(ipType)) {
                flag = true;
            }
        } catch (Exception e) {

        }
        return flag;
    }

    /**
     * ip访问次数
     * 
     * @param ip
     * @return
     */
    public static long findIpOfCount(String ip) {
        long count = 0;
        try {
            count = Long.parseLong(jedisUtil.get(ip + IP_COUNT));
        } catch (Exception e) {
        }
        return count;
    }

    /**
     * 增加黑名单
     * 
     * @param ip
     */
    public static void addIpToBlack(String ip) {
        try {
            jedisUtil.set(ip, "black");
        } catch (Exception e) {

        }
    }

    /**
     * 增加ip访问次数
     * 
     * @param ip
     */
    public static void incrIp(String ip) {
        try {
            String key = ip + IP_COUNT;
            if (!jedisUtil.exists(ip) || jedisUtil.new Keys().ttl(key) == -1) {// 不存在的话
                                                                               // 或者过期
                jedisUtil.new Keys().del(key);
                jedisUtil.incr(ip);
                jedisUtil.expire(key, PropertyUtil.getInt("time_interval",0));
            } else {
                jedisUtil.incr(ip);
            }
        } catch (Exception e) {
        }
    }
}