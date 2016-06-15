package com.shangpin.utils;

/**
 * 线程变量
 * 
 * @author zghw
 *
 */
public class ThreadVariableGlobal {
    /**
     * 缓存设置线程变量
     */
    private static ThreadLocal<CacheOption> cacheOptionVar = new ThreadLocal<CacheOption>();

    /**
     * 取得缓存设置
     * 
     * @return
     */
    public static CacheOption getCacheOption() {
        return cacheOptionVar.get();
    }

    /**
     * 设置缓存设置
     * 
     * @param cacheOption
     */
    public static void setCacheOption(CacheOption cacheOption) {
        cacheOptionVar.set(cacheOption);
    }

    /**
     * 移除缓存设置
     */
    public static void removeCacheOption() {
        cacheOptionVar.remove();
    }
}
