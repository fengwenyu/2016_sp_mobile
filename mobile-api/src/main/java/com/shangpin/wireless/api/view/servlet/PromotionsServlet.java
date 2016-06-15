package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取促销广告接口
 * 
 */
public class PromotionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(PromotionsServlet.class);

	private static final String KEY_SHANGPIN_ADLIST	= "spadlist";
	private static final String KEY_AOLAI_ADLIST	= "aladlist";
	private static final int PROMOTION_KEEP_TIME	= 30 * 60 * 1000;	//	30分钟
	private static HashMap<String, JSONObject> PromotionCache = new HashMap<String, JSONObject>();
	private static long PromotionTime;


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String product = request.getHeader("p");

			JSONObject promotion = null;

			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long now = System.currentTimeMillis();
//			促销信息
			if (now > PromotionTime + PROMOTION_KEEP_TIME) {
				Properties props = PropertiesUtil.getInstance("/promotion/config.properties");
				PromotionCache.clear();
				for (Enumeration e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
					if (KEY_SHANGPIN_ADLIST.equals(propkey) || KEY_AOLAI_ADLIST.equals(propkey)) {
						JSONObject json = JSONObject.fromObject(propvalue);
						PromotionCache.put(propkey, json);
					}
				}
				PromotionTime = now;
			}
			JSONObject promotioncache = null;
			if ("1".equals(product) || "101".equals(product)) {
				promotioncache = PromotionCache.get(KEY_AOLAI_ADLIST);
			} else {
				promotioncache = PromotionCache.get(KEY_SHANGPIN_ADLIST);
			}
			if (promotioncache != null) {
				try {
					Date starttime = sdf.parse(promotioncache.getString("starttime"));
					Date endtime = sdf.parse(promotioncache.getString("endtime"));
					Date date = new Date(now);
					if (date.after(starttime) && date.before(endtime)) {
						promotion = JSONObject.fromObject(promotioncache);
						promotion.put("starttime", starttime.getTime());
						promotion.put("endtime", endtime.getTime());
						promotion.put("systime", now);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}

			try {
				JSONObject json = new JSONObject();
				json.put("code", "0");
				json.put("msg", "");
				json.put("content", promotion==null?"{}":promotion);
				response.getWriter().print(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("PromotionsServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
