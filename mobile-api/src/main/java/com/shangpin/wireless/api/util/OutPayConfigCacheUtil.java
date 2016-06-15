package com.shangpin.wireless.api.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OutPayConfigCacheUtil {
	protected final static Log log = LogFactory.getLog(PayConfigCacheUtil.class);
	public final static String PAY_CONFIG_PREFIX = "outPay";
	
	private static Map<String, Entity> outPays = new HashMap<String, Entity>();
	private static Map<String, Entity> outPays_nocod = new HashMap<String, Entity>();

	// 更新内存中的数据
	@SuppressWarnings("rawtypes")
    public static void update() {
		try {
//			ProReader.loadCategoryIndex();
//			ProReader.loadAppAddress();
			outPays.clear();
			outPays_nocod.clear();
			Properties props = ProReader.getInstance();
			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				if (propkey.startsWith(PAY_CONFIG_PREFIX)) {
					String propvalue = props.getProperty(propkey);
//					final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
					Entity entity = parse(propkey, propvalue);
					if (entity == null)
						continue;
					for (int i = entity.paymentData.size() - 1; i >= 0; i--) {
						JSONObject obj = entity.paymentData.getJSONObject(i);
						if ("2".equals(obj.getString("id"))) { // 货到付款
							obj.put("enable", "1");
							break;
						}
					}
					outPays.put(propkey, entity);
					entity = parse(propkey, propvalue);
					for (int i = entity.paymentData.size() - 1; i >= 0; i--) {
						JSONObject obj = entity.paymentData.getJSONObject(i);
						if ("2".equals(obj.getString("id"))) { // 货到付款
							obj.put("enable", "0");
							break;
						}
					}
					outPays_nocod.put(propkey, entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("PayConfigCacheUtil-update:" + e);
		}
	}

	@SuppressWarnings("rawtypes")
    public static JSONArray getPayConfig(String product, String channel, String version, boolean supportCod) {
		if (outPays.isEmpty() || outPays_nocod.isEmpty()) {
			update();
		}
		final Map<String, Entity> useoutPays = supportCod ? outPays : outPays_nocod;

		for (Iterator e = useoutPays.keySet().iterator(); e.hasNext();) {
			Entity entity = (Entity) useoutPays.get(e.next());
			boolean findProduct = false;
			for (int i = entity.products.length - 1; i >= 0; i--) {
				if (product != null && product.equals(entity.products[i])) {
					findProduct = true;
					break;
				}
			}
			if (!findProduct)
				continue;
			boolean findChannel = false;
			for (int i = entity.channels.length - 1; i >= 0; i--) {
				if (channel != null && channel.equals(entity.channels[i])) {
					findChannel = true;
					break;
				}
			}
			if (!findChannel)
				continue;
			
			if (StringUtil.inVersionRange(version, entity.verConfig)) {
				return entity.paymentData;
			}
		}
		return getPayConfig(product, "0", version, supportCod);
	}

	private static final Entity parse(String propkey, String propvalue) {
		String[] infos = propkey.split("_");
		if (infos.length != 4)
			return null;
		Entity entity = new Entity();
		entity.products = infos[1].split(",");
		entity.channels = infos[2].split(",");
		entity.verConfig = infos[3];
		entity.paymentData = JSONArray.fromObject(propvalue);
		return entity;
	}

	static class Entity {
		String[] products;
		String[] channels;
		String verConfig;
		JSONArray paymentData;
	}
}
