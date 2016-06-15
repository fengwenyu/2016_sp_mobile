package com.shangpin.wechat.utils.common;

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
public class WeChatPropertyUtil {
    public static Logger logger = LoggerFactory.getLogger(WeChatPropertyUtil.class);
    
    private static final String CHARSET = "UTF-8";
    //private static final int DEFAULT_REFRESH_DELAY = 600000; //默认刷新时间10分钟

    private static final String CONFIG_WECHAT_OPEN_NAME = "wechat_open.properties";
    private static final String CONFIG_WECHAT_PUBLIC_NAME = "wechat_public.properties";
    private static final String CONFIG_WECHAT_CACHE_TIME_NAME = "cache_time.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(WeChatPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_WECHAT_OPEN_NAME));
            config.load(WeChatPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_WECHAT_PUBLIC_NAME));
            config.load(WeChatPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_WECHAT_CACHE_TIME_NAME));
            //FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
            //strategy.setRefreshDelay(DEFAULT_REFRESH_DELAY);
            //config.setReloadingStrategy(strategy);
            
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
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

    /**
     * 得到properties文件的绝对路径
     * 
     * @return
     */
    public static String getFilePath() {
        return config.getFile().getAbsolutePath();
    }

    /**
     * get string value from config file
     * 
     * @param name
     *            item name in config file
     * @return string value
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
    
    @SuppressWarnings("rawtypes")
	public static String getStrValue(String name, String defaultValue) {
        Object obj;
        obj = get(name);
        if (obj == null) {
            return defaultValue;
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        return (String) ((ArrayList) obj).get(0);
    }

    /**
     * get int value from config file
     * 
     * @param name
     *            item name in config file
     * @param default_value
     *            the default value
     * @return int value
     */
    public static int getIntValue(String name, int default_value) {
        String szValue = getStrValue(name);
        if (szValue == null) {
            return default_value;
        }

        return Integer.parseInt(szValue);
    }

    /**
     * get boolean value from config file
     * 
     * @param name
     *            item name in config file
     * @param default_value
     *            the default value
     * @return boolean value
     */
    public static boolean getBoolValue(String name, boolean default_value) {
        String szValue = getStrValue(name);
        if (szValue == null) {
            return default_value;
        }

        return szValue.equalsIgnoreCase("yes") || szValue.equalsIgnoreCase("on") || szValue.equalsIgnoreCase("true") || szValue.equals("1");
    }

    /**
     * get all values from config file
     * 
     * @param name
     *            item name in config file
     * @return string values (array)
     */
    @SuppressWarnings("rawtypes")
    public static String[] getValues(String name) {
        Object obj;
        String[] values;

        obj = get(name);
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            values = new String[1];
            values[0] = (String) obj;
            return values;
        }

        Object[] objs = ((ArrayList) obj).toArray();
        values = new String[objs.length];
        System.arraycopy(objs, 0, values, 0, objs.length);
        return values;
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
