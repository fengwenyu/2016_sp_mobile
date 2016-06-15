package com.shangpin.mobileShangpin.common.util;



import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlUtil {

	/**
	 * 该方法是为了删除指定的html片段的元素对应的属性，如果属性值为null或者空，则删除整个html中所有的属性。
	 * 
	 * @param htmlStr
	 *            html片段的字符串
	 * @param element
	 *            元素，如div,td，tr等等
	 * @param attribute
	 *            元素中的属性，如class,style
	 * @return
	 */
	public static String deleteAttribute(String htmlStr, String element,
			String attribute) {
		if (!StringUtil.isNotEmpty(htmlStr))
			return "";
		try {
			Document doc = Jsoup.parseBodyFragment(htmlStr);
			if (StringUtil.isNotEmpty(element)) {
				doc.select(element).removeAttr(attribute);
			} else {
				doc.getAllElements().removeAttr(attribute);
			}
			Element body = doc.body();
			return body.html();
		} catch (Exception e) {
			return "";
		}
	}
	

	public static String changehref(String html) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("a");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (StringUtils.isNotEmpty(linkHref)) {
					String[] queryArr = linkHref.split("[?]");
					if (queryArr.length >1) {
						String queryStr = queryArr[1];
						if (queryStr.indexOf("topicid=") > -1) {
							String topicid = splitQuery(queryStr).get("topicid").toString();
							String MeetingNO = splitQuery(queryStr).get("MeetingNO")+"".toString();
							String ClickRegionID = splitQuery(queryStr).get("ClickRegionID")+"".toString();
							link.attr("href", "http://cmbc.m.shangpin.com/merchandiseaction!splist?ch=38&topicid="+topicid+"&MeetingNO="+MeetingNO+"&ClickRegionID="+ClickRegionID);
						}
					}
				}
			}
		}
		String dochtml = doc.html();
		return dochtml;
	}

	public static Map<String,String> splitQuery(String query) {
		Map<String,String> query_pairs = new LinkedHashMap<String,String>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
		}
		return query_pairs;
	}

	public static void main(String[] args) {
		String str = "<area shape='poly' coords='81,151,69,184,77,267,147,275,136,199,159,179,131,150' href='http://m.shangpin.com/merchandiseaction!spdetail?categoryid=A01B02&categoryname=%E6%9C%8D%E9%A5%B0&productid=01385362' target='_blank'>";
////		Document doc = Jsoup.parseBodyFragment(str);
////		if (StringUtil.isNotEmpty("div")) {
////			doc.select("div").attr("pageH");
////			System.out.println(doc.select("div").attr("pageH"));
////		} 
//		
//		
//		
//		String ua ="mozilla/5.0 (linux; u; android 2.3.5; zh-cn; gt-i9100g build/gingerbread) applewebkit/533.1 (khtml, like gecko) version/4.0 mobile safari/533.1; shangpinandroidapp 2.0.5;";
//		String[] appArray = {"ShangpinIOSApp","AolaiIOSApp","ShangpinAndroidApp","AolaiAndroidApp"};
//		boolean flag =false;
//		 for(int i=0;i<appArray.length;i++){
//			 if(ua.indexOf(appArray[i].toLowerCase())>-1){
//						flag = true;
//				}
//		 }
//		Str="";
		
//		System.out.println(deleteAttribute(str, null, "style"));
	}

	
}
