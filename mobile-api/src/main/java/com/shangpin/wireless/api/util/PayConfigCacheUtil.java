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

/**
 * 支付方式数据缓存
 * 
 * @Author: wangwenguan
 * @CreatDate: 2012-09-17
 */
public class PayConfigCacheUtil {
	protected final static Log log = LogFactory.getLog(PayConfigCacheUtil.class);
	public final static String PAY_CONFIG_PREFIX = "onlinePayment";
	// 将首页数据放入内存中
	private static Map<String, Entity> onlinePayments = new HashMap<String, Entity>();
	private static Map<String, Entity> onlinePayments_nocod = new HashMap<String, Entity>();

	// 更新内存中的数据
	@SuppressWarnings("rawtypes")
    public static void update() {
		try {
//			ProReader.loadCategoryIndex();
//			ProReader.loadAppAddress();
			onlinePayments.clear();
			onlinePayments_nocod.clear();
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
					onlinePayments.put(propkey, entity);
					entity = parse(propkey, propvalue);
					for (int i = entity.paymentData.size() - 1; i >= 0; i--) {
						JSONObject obj = entity.paymentData.getJSONObject(i);
						if ("2".equals(obj.getString("id"))) { // 货到付款
							obj.put("enable", "0");
							break;
						}
					}
					onlinePayments_nocod.put(propkey, entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("PayConfigCacheUtil-update:" + e);
		}
	}

	@SuppressWarnings("rawtypes")
    public static JSONArray getPayConfig(String product, String channel, String version, boolean supportCod) {
		if (onlinePayments.isEmpty() || onlinePayments_nocod.isEmpty()) {
			update();
		}
		final Map<String, Entity> useOnlinePayments = supportCod ? onlinePayments : onlinePayments_nocod;

		for (Iterator e = useOnlinePayments.keySet().iterator(); e.hasNext();) {
			Entity entity = (Entity) useOnlinePayments.get(e.next());
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
