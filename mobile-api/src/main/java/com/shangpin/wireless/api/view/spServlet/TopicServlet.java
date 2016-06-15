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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取尚品首页专题
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(TopicServlet.class);

	private static final int MAX_TOPIC_NUM	= 8;
	private static final String KEY_TOPIC	= "sptopic";
	private static final int TOPIC_KEEP_TIME	= 30 * 60 * 1000;	// 30分钟
	private static HashMap<String, JSONArray> SpecialTopicCache = new HashMap<String, JSONArray>();
	private static long SpecialTopicTime;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pich = request.getParameter("pich");
		String picw = request.getParameter("picw");
		String gender = request.getParameter("gender");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		// header里获取wh、产品号,如何是iPhone，则赋值图片高宽
		String productNo = request.getHeader("p");
		String wh = request.getHeader("wh");
		if (StringUtils.isNotEmpty(productNo) && Constants.SP_IPHONE_PRODUCTNO.equals(productNo)) {
			if ("320x480".equals(wh)) {
				pich = Constants.SP_TOPIC_PICH_LOW;
				picw = Constants.SP_TOPIC_PICW_LOW;
			} else {
				pich = Constants.SP_TOPIC_PICH_HIGH;
				picw = Constants.SP_TOPIC_PICW_HIGH;
			}
		}
		JSONObject obj = null;
		JSONArray topiclist = null;
		if (StringUtil.isNotEmpty(pich, picw)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("gender", (null == gender || !"1".equals(gender) ? "0" : gender));
			map.put("pich", pich);
			map.put("picw", picw);
			String url = Constants.SP_BASE_URL + "SPTopicList/";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				obj = JSONObject.fromObject(data);
				JSONObject content = obj.getJSONObject("content");
				topiclist = content.getJSONArray("list");
//				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("TopicServlet：" + e);
			}

			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long now = System.currentTimeMillis();
			if (now > SpecialTopicTime + TOPIC_KEEP_TIME) {
				Properties props = PropertiesUtil.getInstance("/promotion/config.properties");
				SpecialTopicCache.clear();
				for (Enumeration e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
					if (KEY_TOPIC.equals(propkey)) {	//	特供专题
						JSONArray json = JSONArray.fromObject(propvalue);
						SpecialTopicCache.put(KEY_TOPIC, json);
						break;
					}
				}
				SpecialTopicTime = now;
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
							picurl = picurl.replace("{w}", picw);
							picurl = picurl.replace("{h}", pich);
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
				response.getWriter().print(obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("TopicServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpintopic", channelNo,
					"gender", gender);
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
