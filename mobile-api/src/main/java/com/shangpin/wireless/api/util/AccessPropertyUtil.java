/**
 * create on 2011-12-12
 */
package com.shangpin.wireless.api.util;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class AccessPropertyUtil {
    public static Logger logger = LoggerFactory.getLogger(AccessPropertyUtil.class);

    private static final String CHARSET = "UTF-8";
    private static final int DEFAULT_REFRESH_DELAY = 600000; // 默认刷新时间10分钟

    private static final String CONFIG_FILE_NAME = "access.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(AccessPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
            FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
            strategy.setRefreshDelay(DEFAULT_REFRESH_DELAY);
            config.setReloadingStrategy(strategy);
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static String getString(String key, String def) {
        return config.getString(key, def);
    }

    public static int getInt(String key) {
        return config.getInt(key);
    }

    public static int getInt(String key, int def) {
        return config.getInt(key, def);
    }

    public static long getLong(String key) {
        return config.getLong(key);
    }

    public static long getLong(String key, long def) {
        return config.getLong(key, def);
    }

    public static float getFloat(String key) {
        return config.getFloat(key);
    }

    public static float getFloat(String key, float def) {
        return config.getFloat(key, def);
    }

    public static double getDouble(String key) {
        return config.getDouble(key);
    }

    public static double getDouble(String key, double def) {
        return config.getDouble(key, def);
    }

    public static boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public static boolean getBoolean(String key, boolean def) {
        return config.getBoolean(key, def);
    }

    public static String[] getStringArray(String key) {
        return config.getStringArray(key);
    }

    @SuppressWarnings("rawtypes")
    public static List getList(String key) {
        return config.getList(key);
    }

    @SuppressWarnings("rawtypes")
    public static List getList(String key, List def) {
        return config.getList(key, def);
    }

    public static void setProperty(String key, Object value) {
        config.setProperty(key, value);
    }

    public static void main(String[] args) {
        System.out.println(AccessPropertyUtil.getString("ip_black"));
        config.addProperty("ip_black", "172.20.10.111");
        System.out.println(AccessPropertyUtil.getString("ip_black"));
    }
}
