package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 尚品首页接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-27
 */
public class ShangpinIndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ShangpinIndexServlet.class);

	private static final int MAX_TOPIC_NUM	= 8;
	private static final String KEY_TOPIC	= "sptopic";
	private static final String KEY_BILLBOARD	= "spbillboard";
	private static final int TOPIC_KEEP_TIME	= 5 * 60 * 1000;	// 5分钟
	private static final int PROMOTION_KEEP_TIME	= 30 * 60 * 1000;	//	30分钟
	private static HashMap<String, JSONArray> NormalTopicCache = new HashMap<String, JSONArray>();
	private static HashMap<String, JSONArray> SpecialTopicCache = new HashMap<String, JSONArray>();
	private static HashMap<String, JSONObject> PromotionCache = new HashMap<String, JSONObject>();
	private static long NormalTopicTime;
	private static long PromotionTime;

//	@Override
//	public void init() throws ServletException {
//		super.init();
//	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");// 0:man 1:woman
		String topicpicw = request.getParameter("topicpicw");
		String topicpich = request.getParameter("topicpich");
		String promotionpicw = request.getParameter("promotionpicw");
		String promotionpich = request.getParameter("promotionpich");
		String product = request.getHeader("p");
		String wh = request.getHeader("wh");
		
		if (StringUtil.isNotEmpty(gender, topicpicw, topicpich)) {
			if (!"1".equals(gender))
				gender = "0";
			
			JSONArray topiclist = null;
			JSONObject promotion = null;

			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long now = System.currentTimeMillis();
//			促销信息
			if (now > PromotionTime + PROMOTION_KEEP_TIME) {
				Properties props = PropertiesUtil.getInstance("/promotion/config.properties");
				PromotionCache.clear();
				SpecialTopicCache.clear();
				for (Enumeration e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
					if (propkey.startsWith(KEY_BILLBOARD)) {	//	公告
						JSONObject json = JSONObject.fromObject(propvalue);
						PromotionCache.put(propkey, json);
					} else if (KEY_TOPIC.equals(propkey)) {	//	特供专题
						JSONArray json = JSONArray.fromObject(propvalue);
						SpecialTopicCache.put(KEY_TOPIC, json);
					}
				}
				PromotionTime = now;
			}
			for (int i=0;i<2;i++) {
			JSONObject promotioncache = PromotionCache.get(KEY_BILLBOARD + (i==0?"":i));
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
						String picurl = promotioncache.getString("pic");
						picurl = picurl.replace("{w}", promotionpicw);
						picurl = picurl.replace("{h}", promotionpich);
						promotion.put("pic", picurl);
						break;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			}
//			专题信息
			if ("2".equals(product) && "320x480".equals(wh) && (now > NormalTopicTime + TOPIC_KEEP_TIME)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("gender", gender);
				map.put("picw", topicpicw);
				map.put("pich", topicpich);
				String url = Constants.SP_BASE_URL + "SPTopicList/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					JSONObject json = JSONObject.fromObject(data);
					JSONObject content = json.getJSONObject("content");
					NormalTopicCache.put(gender, content.getJSONArray("list"));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ShangpinIndexServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				String othergender = "0".equals(gender)?"1":"0";
				map.put("gender", othergender);
				url = Constants.SP_BASE_URL + "SPTopicList/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					JSONObject json = JSONObject.fromObject(data);
					JSONObject content = json.getJSONObject("content");
					NormalTopicCache.put(othergender, content.getJSONArray("list"));
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ShangpinIndexServlet：" + e);
				}
				NormalTopicTime = now;
			}
			if ("2".equals(product) && "320x480".equals(wh)) {
				JSONArray topiclistcache = NormalTopicCache.get(gender);
				topiclist = JSONArray.fromObject(topiclistcache);
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("gender", gender);
				map.put("picw", topicpicw);
				map.put("pich", topicpich);
				String url = Constants.SP_BASE_URL + "SPTopicList/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					JSONObject json = JSONObject.fromObject(data);
					JSONObject content = json.getJSONObject("content");
					topiclist = content.getJSONArray("list");
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ShangpinIndexServlet：" + e);
				}
			}
			//	特供专题
			JSONArray specialTopics = SpecialTopicCache.get(KEY_TOPIC);
			if (specialTopics != null) {
				try {
					Date date = new Date(now);
					for (int i=0;i<specialTopics.size();i++) {
						JSONObject specialTopic = specialTopics.getJSONObject(i);
						Date starttime = sdf.parse(specialTopic.getString("starttime"));
						Date endtime = sdf.parse(specialTopic.getString("endtime"));
						if (date.after(starttime) && date.before(endtime)) {
							String picurl = specialTopic.getString("pic");
							picurl = picurl.replace("{w}", topicpicw);
							picurl = picurl.replace("{h}", topicpich);
							specialTopic.put("pic", picurl);
							
							topiclist.add(0, specialTopic);
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			while (topiclist.size() > MAX_TOPIC_NUM) {
				topiclist.remove(topiclist.size() - 1);
			}
			try {
				JSONObject content = new JSONObject();
				content.put("list", topiclist==null?"[]":topiclist);
				content.put("promotion", promotion==null?"{}":promotion);
				
				JSONObject json = new JSONObject();
				json.put("code", "0");
				json.put("msg", "");
				json.put("content", content);
				response.getWriter().print(json.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ShangpinIndexServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
