/**
 * create on 2014-10-08
 */
package com.aolai.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class PropertyUtil {
    public static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

    private static final String CHARSET = "UTF-8";

    private static final String CONFIG_AOLAI_NAME = "aolaiConfig.properties";
    private static final String COUPON_FILE_NAME="config/coupon/coupon.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(PropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_AOLAI_NAME));
            config.load(PropertyUtil.class.getClassLoader().getResourceAsStream(COUPON_FILE_NAME));
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }
    
    /**
     * 读取属性文件的值
     * 
     * @param name
     * @return
     *
     */
    @SuppressWarnings("rawtypes")
    public static String getStrValue(String name) {
        Object obj;
        obj = get(name);
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        return (String) ((ArrayList) obj).get(0);
    }
    
    /**
     * 通过key值得到对应的value
     * 
     * @param key
     * @return
     */
    public static Object get(String key) {
        return config.getString(key);
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

}
