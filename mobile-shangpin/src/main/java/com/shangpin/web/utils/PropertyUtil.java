/**
 * create on 2014-10-08
 */
package com.shangpin.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String COUPON_FILE_NAME="config/coupon/coupon.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(PropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
            config.load(PropertyUtil.class.getClassLoader().getResourceAsStream(COUPON_FILE_NAME));
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
    /***
     * 获得活动是否结束
     * @return
     */
    public static Boolean isActEndTime() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date configEndTime = df.parse(PropertyUtil.getString("redActivityEndTime"));
            Date date = new Date();
            if (configEndTime.after(date)) {
                return true;
            }
        }catch (ParseException e) {
            logger.info("error 获取配置红包结束时间出错",e);
        }
        return false;
    }
}
