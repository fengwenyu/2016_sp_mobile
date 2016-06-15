package com.shangpin.utils;

/**
 * 缓存设置对象参数  
 * HIT 需要进行缓存 
 * MISS 不进行缓存  
 * REFRESH 刷新数据
 * @author zghw
 *
 */
public enum CacheOption {
    HIT, MISS, REFRESH;
}
