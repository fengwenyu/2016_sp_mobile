package com.shangpin.mobileAolai.common.util;


import java.io.File;
import java.io.IOException;
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
	public static String deleteAttribute(String htmlStr, String element, String attribute) {
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

	public static String addprefixhref(String html) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("a");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				
				String linkText = link.text();
				if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {
//					if(linkHref.indexOf("m.aolai.com") > -1){
						linkHref=linkHref.replace("amp;", "").trim();
						String[] queryArr = linkHref.split("[?]");
						if (queryArr.length >= 2) {
							String queryStr = queryArr[1];
							if (queryStr.indexOf("activityId=") > -1&&linkHref.indexOf("merchandiseaction!list") > -1) {
								String activityId = splitQuery(queryStr).get("activityId").toString();
								link.attr("href", "shangpinapp://phone.aolai/actiongoactivitylist?title=尚品奥莱&activityid=" + activityId);
							}else if (queryStr.indexOf("brandid=") > -1&&linkHref.indexOf("merchandiseaction!list") > -1) {
								String brandid = splitQuery(queryStr).get("brandid").toString();
								link.attr("href", "shangpinapp://phone.aolai/actiongobrandlist?title=尚品奥莱&brandid=" + brandid);
							}else if (queryStr.indexOf("cateid=") > -1&&linkHref.indexOf("merchandiseaction!list") > -1) {
								String cateid = splitQuery(queryStr).get("cateid").toString();
								link.attr("href", "shangpinapp://phone.aolai/actiongobrandlist?title=尚品奥莱&cateid=" + cateid);
							}else if (linkHref.indexOf("merchandiseaction!detail") > -1 && queryStr.indexOf("goodsid") > -1) {
								String productid = splitQuery(queryStr).get("goodsid").toString();
								if(queryStr.indexOf("categoryno") > -1){
									String categoryno= splitQuery(queryStr).get("categoryno").toString();
									if(StringUtil.isNotEmpty(categoryno)){
										link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid+"&categoryno="+categoryno);
									}else{
										link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid);
									}
								}else{
									link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid);
								}
								
							
							}
						}
					}
				
//				}
			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "");
		}
		return dochtml;
	}

	public static String addprefixhref(File html) {
		Document doc;
		try {
			doc = Jsoup.parse(html, "UTF-8");
			Elements links = doc.getElementsByTag("a");
			if (links != null) {
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text();
					if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {
//						if(linkHref.indexOf("m.aolai.com") > -1){
							linkHref=linkHref.replace("amp;", "").trim();
							String[] queryArr = linkHref.split("[?]");
							
							if (queryArr.length >= 2) {
								String queryStr = queryArr[1];
								if (queryStr.indexOf("activityId=") > -1&&linkHref.indexOf("merchandiseaction!list") > -1) {
									String activityId = splitQuery(queryStr).get("activityId").toString();
									link.attr("href", "shangpinapp://phone.aolai/actiongoactivitylist?title=尚品奥莱&activityid=" + activityId);
								}else if (queryStr.indexOf("brandid=") > -1&&linkHref.indexOf("merchandiseaction!list") > -1) {
									String brandid = splitQuery(queryStr).get("brandid").toString();
									link.attr("href", "shangpinapp://phone.aolai/actiongobrandlist?title=尚品奥莱&brandid=" + brandid);
								}else if (queryStr.indexOf("cateid=") > -1&&linkHref.indexOf("merchandiseaction!list") > -1) {
									String cateid = splitQuery(queryStr).get("cateid").toString();
									link.attr("href", "shangpinapp://phone.aolai/actiongobrandlist?title=尚品奥莱&cateid=" + cateid);
								}else if (linkHref.indexOf("merchandiseaction!detail") > -1 && queryStr.indexOf("goodsid") > -1) {
									String productid = splitQuery(queryStr).get("goodsid").toString();
									if(queryStr.indexOf("categoryno") > -1){
										String categoryno= splitQuery(queryStr).get("categoryno").toString();
										if(StringUtil.isNotEmpty(categoryno)){
											link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid+"&categoryno="+categoryno);
										}else{
											link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid);
										}
									}else{
										link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid);
									}
									
								
								}else{
									link.attr("href", "ShangPinApp://phone.aolai/actiongowebview?title=尚品奥莱&url=" + linkHref.replace("&", "8uuuuu8"));
									
								}
							}
						}
					
//					}
				}
			}
			String dochtml = doc.html();
			if (StringUtils.isNotEmpty(dochtml)) {
				dochtml = doc.html().replace("amp;", "");
			}
			return dochtml;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	public static String appDetailhref(String html) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("area");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (StringUtils.isNotEmpty(linkHref)) {
					String[] queryArr = linkHref.split("[?]");
					if (queryArr.length >= 1) {
						String queryStr = queryArr[1];
						if (queryStr.indexOf("productid=") > -1) {
							String productid = splitQuery(queryStr).get("productid").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
						}
					}
				}
			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "");
		}
		return dochtml;
	}

	public static Map<String, String> splitQuery(String query) {
		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
		}
		return query_pairs;
	}
	
	public static String addprefixcollecthrefs(String html) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("li");
		if (links != null) {
			for (Element link : links) {
				Elements linkAtags = link.getElementsByTag("a");
				for (Element linkAtag : linkAtags) {
					String linkHref = linkAtag.attr("href");
					String linkText = linkAtag.text();
					if (StringUtils.isNotEmpty(linkHref)&& !linkText.equals("领取优惠券")&&!linkHref.equals("#")) {
						linkHref = linkHref.replace("amp;", "").trim();
						String[] queryArr = linkHref.split("[?]");
						if (queryArr.length >= 2) {
							String queryStr = queryArr[1];
							if (queryStr.indexOf("topicid=") > -1&& linkHref.indexOf("merchandiseaction!splist") > -1) {
								String topicid = splitQuery(queryStr).get(
										"topicid").toString();
								Elements linkSans = link.getElementsByTag("span");
								for (Element linkSan : linkSans) {
									String linkSanCon = linkSan.attr("class");
									String linkSanText = linkSan.text();
									if (StringUtils.isNotEmpty(linkSanText)
											&& linkSanText.equals("收藏")) {
										linkSanText = linkSanText.replace(
												"amp;", "").trim();
										if (linkSanCon.equals("collection")) {
											// linkText.replace("收藏", "");
											linkSan.empty();
											linkSan.attr("class", "collection"+topicid);
											String colink = "javascript:collection("
													+ topicid+")";
											String cohtml = "<a href='"
													+ colink + "' ><i>收藏</i></a>";
											linkSan.append(cohtml);
										}
									}
								}
							}

						}

					}else{
						Elements linkSans = link.getElementsByTag("span");
						for (Element linkSan : linkSans) {
							String linkSanCon = linkSan.attr("class");
							String linkSanText = linkSan.text();
							if (StringUtils.isNotEmpty(linkSanText)
									&& linkSanText.equals("收藏")) {
								linkSanText = linkSanText.replace(
										"amp;", "").trim();
								if (linkSanCon.equals("collection")) {
									// linkText.replace("收藏", "");
									linkSan.empty();
									linkSan.attr("class", "collection");
								
									String cohtml = "<a href='#' ><i>收藏</i></a>";
									linkSan.append(cohtml);
								}
							}
						}
					}
				}
				

			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "");
		}
		return dochtml;
		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	File f=new File("c://1.html");
	}

	public static String addprefixShangPinhref(String html) {
		Document doc;

		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("a");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				String linkText = link.text();
				if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {
					linkHref=linkHref.replace("amp;", "").trim();
					if(linkHref.indexOf("www.shangpin.com/women/subject/")>-1){
						String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
						link.attr("href", "http://m.shangpin.com/merchandiseaction!splist?topicid=" + topicid);
					}
					if(linkHref.indexOf("http://www.aolai.com/Subject/Index/")>-1){
						String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
						link.attr("href", "http://m.aolai.com/merchandiseaction!list?activityId==" + topicid);
					}
				}
			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "");
		}
		return dochtml;
	}
}
