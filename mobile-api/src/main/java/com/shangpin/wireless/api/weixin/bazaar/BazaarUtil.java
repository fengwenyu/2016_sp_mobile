package com.shangpin.wireless.api.weixin.bazaar;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.weixinbean.ConfigEntity;

public class BazaarUtil {
	private static final String REPLY_TYPE_MENU_ADD = "menuadd";
	private static final String REPLY_TYPE_MENU_DEL = "menudel";
	private static final String ACCESS_PRIVATE = "private";
	private static final Hashtable<String, ConfigEntity> configs = new Hashtable<String, ConfigEntity>();

	public static void updateConfig() {
		try {
			Properties props = PropertiesUtil.getInstance("/weixin/bazaar.properties");
			configs.clear();
			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
				ConfigEntity entity = ConfigEntity.parse(propvalue);
				final String type = entity.getType();
				if (REPLY_TYPE_MENU_ADD.equals(type) || REPLY_TYPE_MENU_DEL.equals(type)) {
					entity.setAccess(ACCESS_PRIVATE);
				}
				final String[] keywords = entity.getKeywords();
				if (keywords != null) {
					for (int i = 0; i < keywords.length; i++) {
						configs.put(keywords[i], entity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		updateConfig();
	}

	public static boolean isEmpty(){
		return configs.isEmpty();
	}
	
	public static ConfigEntity get(String key) {
		ConfigEntity entity = configs.get(key);
		if (entity == null) {
			for (Iterator iter = configs.keySet().iterator(); iter.hasNext();) {
				String key1 = (String) iter.next();
				ConfigEntity e = configs.get(key1);
				String regular = e.getRegular();
				try {
					if (regular != null && regular.length() > 0) {
						Pattern pattern = Pattern.compile(regular);
						Matcher m = pattern.matcher(key);
						boolean matched = m.matches();
						if (matched) {
							entity = e;
							if (m.groupCount() > 0) {
								e.setRegularGroup(m.group(1));
							}
							break;
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return entity;
	}
}
