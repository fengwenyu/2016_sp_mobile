package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.AolaiIndexCacheUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.DataContainerPool;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 主页接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-27
 */
public class AolaiIndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AolaiIndexServlet.class);

	private static final String KEY_BILLBOARD	= "albillboard";
	private static final int PROMOTION_KEEP_TIME	= 30 * 60 * 1000;	//	30分钟
	private static HashMap<String, JSONObject> PromotionCache = new HashMap<String, JSONObject>();
	private static long PromotionTime;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");// 0:man 1:woman
		String picquality = request.getParameter("picquality");
		String actpicw = request.getParameter("actpicw");
		String actpich = request.getParameter("actpich");
		String toppicw = request.getParameter("toppicw");
		String toppich = request.getParameter("toppich");
		String promotionpicw = request.getParameter("promotionpicw");
		String promotionpich = request.getParameter("promotionpich");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		final String ver = request.getHeader("ver");
		final String productNo = request.getHeader("p");
		if (StringUtil.isNotEmpty(gender)) {
			if (!"1".equals(gender))
				gender = "0";

			JSONObject promotion = null;
			final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			long now = System.currentTimeMillis();
//			促销信息
			if (now > PromotionTime + PROMOTION_KEEP_TIME) {
				Properties props = PropertiesUtil.getInstance("/promotion/config.properties");
				PromotionCache.clear();
				for (Enumeration e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
					if (propkey.startsWith(KEY_BILLBOARD)) {	//	公告
						JSONObject json = JSONObject.fromObject(propvalue);
						PromotionCache.put(propkey, json);
					}
				}
				PromotionTime = now;
			}
			for (int i=0;i<2;i++) {
			JSONObject promotioncache = PromotionCache.get(KEY_BILLBOARD + (i==0?"":i));
			if (promotioncache != null) {
				try {
					Date starttime = sdfconfig.parse(promotioncache.getString("starttime"));
					Date endtime = sdfconfig.parse(promotioncache.getString("endtime"));
					Date date = new Date(now);
					if (date.after(starttime) && date.before(endtime)) {
						promotion = JSONObject.fromObject(promotioncache);
						promotion.put("starttime", starttime.getTime());
						promotion.put("endtime", endtime.getTime());
						promotion.put("systime", now);
						String picurl = promotioncache.getString("pic");
						picurl = picurl.replace("{w}", promotionpicw==null?"0":promotionpicw);
						picurl = picurl.replace("{h}", promotionpich==null?"0":promotionpich);
						promotion.put("pic", picurl);
						break;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			}
			if ("high".equals(picquality)) {
                actpicw = "620";
                actpich = "460";
                toppicw = "640";
                toppich = "640";
            } else if ("low".equals(picquality)) {
                actpicw = "310";
                actpich = "230";
                toppicw = "320";
                toppich = "320";
            }
			
//			//  调整获取活动时间段
//			String starttime = sdf.format(new Date(d.getTime() - 4 * 24 * 60 * 60 * 1000));
//			String endtime = sdf.format(new Date(d.getTime() + 6 * 24 * 60 * 60 * 1000));
//			String topicUrl = Constants.BASE_URL + "TopicList/";
//			String activityUrl = Constants.BASE_URL + "SubjectList/";
//			Map<String, String> topicMap = new HashMap<String, String>();
//			topicMap.put("gender", gender);
//			Map<String, String> activityMap = new HashMap<String, String>();
//			activityMap.put("gender", gender);
//			activityMap.put("starttime", starttime);
//			activityMap.put("endtime", endtime);
//			if ("high".equals(picquality)) {
//				topicMap.put("picw", "640");
//				topicMap.put("pich", "640");
//				activityMap.put("picw", "620");
//				activityMap.put("pich", "460");
//			} else if ("low".equals(picquality)) {
//				topicMap.put("picw", "320");
//				topicMap.put("pich", "320");
//				activityMap.put("picw", "310");
//				activityMap.put("pich", "230");
//			} else {
//				topicMap.put("picw", "640");
//				topicMap.put("pich", "640");
//				activityMap.put("picw", "620");
//				activityMap.put("pich", "460");
//			}
//			if (StringUtils.isNotEmpty(toppich) && StringUtils.isNotEmpty(toppicw)) {
//				topicMap.put("picw", toppicw);
//				topicMap.put("pich", toppich);
//			}
//			if (StringUtils.isNotEmpty(actpich) && StringUtils.isNotEmpty(actpicw)) {
//				activityMap.put("picw", actpicw);
//				activityMap.put("pich", actpich);
//			}
			try {
//				String indexTopic = "";
//				if ("high".equals(picquality)) {
//					if ("0".equals(gender)) {
//						indexTopic = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_WOMAN_TOPIC_HIGH);
//					} else {
//						indexTopic = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_MAN_TOPIC_HIGH);
//					}
//				} else if ("low".equals(picquality)) {
//					if ("0".equals(gender)) {
//						indexTopic = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_WOMAN_TOPIC_LOW);
//					} else {
//						indexTopic = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_MAN_TOPIC_LOW);
//					}
//				} else {
//					if ("0".equals(gender)) {
//						indexTopic = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_WOMAN_TOPIC_HIGH);
//					} else {
//						indexTopic = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_MAN_TOPIC_HIGH);
//					}
//				}
//				String topicReturn = "";
//				if (indexTopic == null || "".equals(indexTopic)) {
//					topicReturn = WebUtil.readContentFromGet(topicUrl, topicMap);
//					AolaiIndexCacheUtil.add("indexTopic", topicReturn);
//				} else {
//					topicReturn = indexTopic;
//				}
//				TopicAndActivityServerResponse topicAndActivityServerResponse = new TopicAndActivityServerResponse();
//				topicAndActivityServerResponse.json2Obj(topicReturn);
//				JSONArray topics = topicAndActivityServerResponse.getContent();
				JSONArray topics = AolaiIndexCacheUtil.getTopic(gender + "_" + toppicw + "_" + toppich);
				if(topics!=null&&topics.size()>0){
					List<String> topicNoList=new ArrayList<String>();
					for(int i=0;i<topics.size();i++){
						JSONObject topicsObj=(JSONObject)topics.get(i);
						topicNoList.add(topicsObj.getString("topicid"));
					}
					DataContainerPool.topicNosContainer.putOrReplace("toplist", topicNoList);
				}
				
//				String indexActivity = "";
//				if ("high".equals(picquality)) {
//					if ("0".equals(gender)) {
//						indexActivity = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_WOMAN_ACTIVITY_HIGH);
//					} else {
//						indexActivity = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_MAN_ACTIVITY_HIGH);
//					}
//				} else if ("low".equals(picquality)) {
//					if ("0".equals(gender)) {
//						indexActivity = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_WOMAN_ACTIVITY_LOW);
//					} else {
//						indexActivity = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_MAN_ACTIVITY_LOW);
//					}
//				} else {
//					if ("0".equals(gender)) {
//						indexActivity = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_WOMAN_ACTIVITY_HIGH);
//					} else {
//						indexActivity = AolaiIndexCacheUtil.get(AolaiIndexCacheUtil.INDEX_MAN_ACTIVITY_HIGH);
//					}
//				}
//				String activityReturn = "";
//				if (indexActivity == null || "".equals(indexActivity)) {
//					activityReturn = WebUtil.readContentFromGet(activityUrl, activityMap);
//					AolaiIndexCacheUtil.add("indexActivity", activityReturn);
//				} else {
//					activityReturn = indexActivity;
//				}
//				topicAndActivityServerResponse.json2Obj(activityReturn);
//				JSONArray activities = topicAndActivityServerResponse.getContent();
				JSONArray activities = AolaiIndexCacheUtil.getActivity(gender + "_" + actpicw + "_" + actpich);
				CommonAPIResponse apiResponse = new CommonAPIResponse();
				apiResponse.setCode(Constants.SUCCESS);
				apiResponse.setMsg("");
				JSONObject content = new JSONObject();
				content.put("topic", topics==null?"[]":topics);
//				// 为活动列表添加“售”字
//				for (int i = 0; i < activities.size(); i++) {
//					JSONArray array = activities.getJSONArray(i);
//					for (int j = 0; j < array.size(); j++) {
//						JSONObject obj = array.getJSONObject(j);
//						String str = obj.getString("t2");
//						str = str + "售";
//						obj.put("t2", str);
//					}
//				}
				content.put("activity", activities==null?"[]":activities);
				content.put("promotion", promotion==null?"{}":promotion);
				content.put("systime", String.valueOf(System.currentTimeMillis()));
				apiResponse.setContent(content);
				response.getWriter().print(apiResponse.obj2Json());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("AolaiIndexServlet：" + e);
//				AolaiIndexCacheUtil.add("indexActivity", null);
//				AolaiIndexCacheUtil.add("indexTopic", null);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "aolaiindex", channelNo,
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
