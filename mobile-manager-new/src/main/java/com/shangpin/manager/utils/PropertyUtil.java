/**
 * create on 2011-12-12
 */
package com.shangpin.manager.utils;

import java.util.ArrayList;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 孙维维(sundful)
 * 
 */
public class PropertyUtil {
	public static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);

	private static final String CONFIG_FILE_NAME = "config.properties";
	private static final PropertiesConfiguration config = new PropertiesConfiguration();
	static {
		config.setEncoding("UTF-8");
		try {
			config.load(CONFIG_FILE_NAME);
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
	@SuppressWarnings("static-access")
	public boolean getBoolValue(String name, boolean default_value) {
		String szValue = this.getStrValue(name);
		if (szValue == null) {
			return default_value;
		}

		return szValue.equalsIgnoreCase("yes")
				|| szValue.equalsIgnoreCase("on")
				|| szValue.equalsIgnoreCase("true") || szValue.equals("1");
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

}
