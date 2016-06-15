package com.shangpin.biz.utils;

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
	private static final String APP_LINK_ACTIVITY_SUFFIX="shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=";
	private static final String APP_LINK_BRAND_SUFFIX="shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&filters=";
	private static final String APP_LINK_CATEGORY_SUFFIX="shangpinapp://phone.shangpin/actiongocatelist?title=尚品&filters=";
	private static final String APP_LINK_TAG_SUFFIX="shangpinapp://phone.shangpin/actiongotaglist?title=尚品";
	private static final String APP_LINK_WEBVIEW_SUFFIX="ShangPinApp://phone.shangpin/actiongowebview?title=尚品";
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

	/**
	 * 
	 * @Description: 设置href的数据
	 * @param html
	 * @param osFlag
	 *            1:m站;2:android;3:ios
	 * @return
	 */
	public static String addprefixhref(String html, String osFlag, String id) {
		Document doc;
		final String htmlCache = SizeINFOCacheUtil.getHtmlValue("addprefixhref" + id);
		if (null != htmlCache) {
			return htmlCache;
		} else {
			doc = Jsoup.parse(html, "UTF-8");
			Elements links = doc.getElementsByTag("a");
			if (links != null) {
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text();
					if (StringUtils.isNotEmpty(linkHref) && "javascript:collectionList();".equals(linkHref)) {
						if ("2".equals(osFlag)) {
							link.attr("href", "javascript:collectionListForAndro();");
						}
					}

					if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {
						linkHref = linkHref.replace("amp;", "").trim();
						String[] queryArr = linkHref.split("[?]");
						if (queryArr.length >= 2) {
							String queryStr = queryArr[1];
							if (linkHref.indexOf("merchandiseaction!splist") > -1 && queryStr.indexOf("topicid=") > -1) {
								String topicid = splitQuery(queryStr).get("topicid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
							}
							if (linkHref.indexOf("brandaction!getBrandProductsList") > -1 && queryStr.indexOf("brandNO=") > -1) {
								String brandid = splitQuery(queryStr).get("brandNO").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
							}
							if (linkHref.indexOf("brandaction!getSPProductsBrand") > -1 && queryStr.indexOf("brandid=") > -1) {
								String brandid = splitQuery(queryStr).get("brandid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
							}
							if (linkHref.indexOf("merchandiseaction!spdetail") > -1 && queryStr.indexOf("productid=") > -1) {
								String productid = splitQuery(queryStr).get("productid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
							}
							if (linkHref.indexOf("categoryproductsaction!getCategoryProducts") > -1 && queryStr.indexOf("categoryid=") > -1) {
								String cateid = splitQuery(queryStr).get("categoryid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
							}
							if (linkHref.indexOf("categoryproductsaction!getProductList") > -1 && queryStr.indexOf("categoryNO=") > -1) {
								String cateid = splitQuery(queryStr).get("categoryNO").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
							}
						}
					}
				}
			}
			String dochtml = doc.html();
			if (StringUtils.isNotEmpty(dochtml)) {
				dochtml = doc.html().replace("amp;", "");
			}
			if (null != dochtml) {
				SizeINFOCacheUtil.put("addprefixhref" + id, dochtml);
			}
			return dochtml;
		}
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
						linkHref = linkHref.replace("amp;", "").trim();
						String[] queryArr = linkHref.split("[?]");
						if (queryArr.length >= 2) {
							String queryStr = queryArr[1];
							if (queryStr.indexOf("topicid=") > -1) {
								String topicid = splitQuery(queryStr).get("topicid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
							}
							if (linkHref.indexOf("brandaction!getSPProductsBrand") > -1 && queryStr.indexOf("brandid=") > -1) {
								String brandid = splitQuery(queryStr).get("brandid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
							}
							if (linkHref.indexOf("brandaction!getBrandProductsList") > -1 && queryStr.indexOf("brandNO=") > -1) {
								String brandid = splitQuery(queryStr).get("brandNO").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
							}
							if (linkHref.indexOf("merchandiseaction!spdetail") > -1 && queryStr.indexOf("productid=") > -1) {
								String productid = splitQuery(queryStr).get("productid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
							}
							if (linkHref.indexOf("categoryproductsaction!getCategoryProducts") > -1 && queryStr.indexOf("categoryid=") > -1) {
								String cateid = splitQuery(queryStr).get("categoryid").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
							}
							if (linkHref.indexOf("categoryproductsaction!getProductList") > -1 && queryStr.indexOf("categoryNO=") > -1) {
								String cateid = splitQuery(queryStr).get("categoryNO").toString();
								link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
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
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String addprefixShangPinhref(String html, String id) {
		Document doc;
		final String htmlCache = SizeINFOCacheUtil.getHtmlValue("addprefixShangPinhref" + id);
		if (null != htmlCache) {
			return htmlCache;
		} else {
			doc = Jsoup.parse(html, "UTF-8");
			Elements links = doc.getElementsByTag("a");
			if (links != null) {
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text();
					if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {
						linkHref = linkHref.replace("amp;", "").trim();
						if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", "http://m.shangpin.com/merchandiseaction!splist?topicid=" + topicid);
						}
						if (linkHref.indexOf("http://www.aolai.com/Subject/Index/") > -1) {
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
			if (null != dochtml) {
				SizeINFOCacheUtil.put("addprefixShangPinhref" + id, dochtml);
			}

			return dochtml;
		}

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
			if (pair.indexOf("=") > -1) {
				int idx = pair.indexOf("=");
				query_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
			}

		}
		return query_pairs;
	}

	public static String addprefixcollecthrefs(String html, String id) {
		Document doc;
		final String htmlCache = SizeINFOCacheUtil.getHtmlValue("addprefixcollecthrefs" + id);
		if (null != htmlCache) {
			return htmlCache;
		} else {
			doc = Jsoup.parse(html, "UTF-8");
			Elements links = doc.getElementsByTag("li");
			if (links != null) {
				for (Element link : links) {
					Elements linkAtags = link.getElementsByTag("a");
					for (Element linkAtag : linkAtags) {
						String linkHref = linkAtag.attr("href");
						String linkText = linkAtag.text();
						if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券") && !linkHref.equals("#")) {
							linkHref = linkHref.replace("amp;", "").trim();
							String[] queryArr = linkHref.split("[?]");
							if (queryArr.length >= 2) {
								String queryStr = queryArr[1];
								if (queryStr.indexOf("topicid=") > -1 && linkHref.indexOf("merchandiseaction!splist") > -1) {
									String topicid = splitQuery(queryStr).get("topicid").toString();
									Elements linkSans = link.getElementsByTag("span");
									for (Element linkSan : linkSans) {
										String linkSanCon = linkSan.attr("class");
										String linkSanText = linkSan.text();
										if (StringUtils.isNotEmpty(linkSanText) && linkSanText.equals("收藏")) {
											linkSanText = linkSanText.replace("amp;", "").trim();
											if (linkSanCon.equals("collection")) {
												// linkText.replace("收藏", "");
												linkSan.empty();
												linkSan.attr("class", "collection" + topicid);
												String colink = "javascript:collection(" + topicid + ")";
												String cohtml = "<a href='" + colink + "' ><i>收藏</i></a>";
												linkSan.append(cohtml);
											}
										}
									}
								}

							}

						} else {
							Elements linkSans = link.getElementsByTag("span");
							for (Element linkSan : linkSans) {
								String linkSanCon = linkSan.attr("class");
								String linkSanText = linkSan.text();
								if (StringUtils.isNotEmpty(linkSanText) && linkSanText.equals("收藏")) {
									linkSanText = linkSanText.replace("amp;", "").trim();
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
			if (null != dochtml) {
				SizeINFOCacheUtil.put("addprefixcollecthrefs" + id, dochtml);
			}
			return dochtml;
		}

	}

	public static String addprefixhrefs(File html) {
		Document doc;
		try {
			doc = Jsoup.parse(html, "UTF-8");
			Elements links = doc.getElementsByTag("li");
			if (links != null) {
				for (Element link : links) {

					String linkHref = link.attr("href");
					String linkText = link.text();
					if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {

						linkHref = linkHref.replace("amp;", "").trim();
						String[] queryArr = linkHref.split("[?]");
						if (queryArr.length >= 2) {
							String queryStr = queryArr[1];
							if (queryStr.indexOf("topicid=") > -1 && linkHref.indexOf("merchandiseaction!splist") > -1) {
								String topicid = splitQuery(queryStr).get("topicid").toString();
								Elements linkSans = link.getElementsByTag("span");
								for (Element linkSan : linkSans) {
									String linkSanCon = linkSan.attr("class");
									String linkSanText = linkSan.text();
									if (StringUtils.isNotEmpty(linkSanText) && linkSanText.equals("收藏")) {
										linkSanText = linkSanText.replace("amp;", "").trim();
										if (linkSanCon.equals("collection")) {
											// linkText.replace("收藏", "");
											linkSan.empty();
											String colink = "http://m.shangpin.com/+collected!activity?activityId=" + topicid;
											String cohtml = "<a href='" + colink + "' >收藏</a>";
											linkSan.append(cohtml);
										}
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
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String addprefixhrefaolai(String html, String osFlag, String id) {
		Document doc;
		final String htmlCache = SizeINFOCacheUtil.getHtmlValue("addprefixhrefaolai" + id);
		if (null != htmlCache) {
			return htmlCache;
		} else {
			doc = Jsoup.parse(html, "UTF-8");
			Elements links = doc.getElementsByTag("a");
			if (links != null) {
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text();
					if (StringUtils.isNotEmpty(linkHref) && "javascript:collectionList();".equals(linkHref)) {
						if ("2".equals(osFlag)) {
							link.attr("href", "javascript:collectionListForAndro();");
						}
					}

					if (StringUtils.isNotEmpty(linkHref) && !linkText.equals("领取优惠券")) {
						// if(linkHref.indexOf("m.aolai.com") > -1){
						linkHref = linkHref.replace("amp;", "").trim();
						String[] queryArr = linkHref.split("[?]");
						if (queryArr.length >= 2) {
							String queryStr = queryArr[1];
							if (queryStr.indexOf("activityId=") > -1 && linkHref.indexOf("merchandiseaction!list") > -1) {
								String activityId = splitQuery(queryStr).get("activityId").toString();
								link.attr("href", "shangpinapp://phone.aolai/actiongoactivitylist?title=尚品奥莱&activityid=" + activityId);
							} else if (queryStr.indexOf("brandid=") > -1 && linkHref.indexOf("merchandiseaction!list") > -1) {
								String brandid = splitQuery(queryStr).get("brandid").toString();
								link.attr("href", "shangpinapp://phone.aolai/actiongobrandlist?title=尚品奥莱&brandid=" + brandid);
							} else if (queryStr.indexOf("cateid=") > -1 && linkHref.indexOf("merchandiseaction!list") > -1) {
								String cateid = splitQuery(queryStr).get("cateid").toString();
								link.attr("href", "shangpinapp://phone.aolai/actiongobrandlist?title=尚品奥莱&cateid=" + cateid);
							} else if (linkHref.indexOf("merchandiseaction!detail") > -1 && queryStr.indexOf("goodsid") > -1) {
								String productid = splitQuery(queryStr).get("goodsid").toString();
								if (queryStr.indexOf("categoryno") > -1) {
									String categoryno = splitQuery(queryStr).get("categoryno").toString();
									if (StringUtil.isNotEmpty(categoryno)) {
										link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid + "&categoryno=" + categoryno);
									} else {
										link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid);
									}
								} else {
									link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=商品详情&productno=" + productid);
								}

							}

						}
					}

					// }
				}
			}
			String dochtml = doc.html();
			if (StringUtils.isNotEmpty(dochtml)) {
				dochtml = doc.html().replace("amp;", "");
			}
			if (null != dochtml) {
				SizeINFOCacheUtil.put("addprefixhrefaolai" + id, dochtml);
			}
			return dochtml;
		}
	}
	
	/**
     * 
     * @param html
     *            主站返回的html
     * @param type
     *            1:m站;2:android;3:ios
     * @return
     */
    public static String modifyOldHref(String html, String type) {
        Document doc;
        doc = Jsoup.parse(html, "UTF-8");
        //访问的js或者css 是否需要内部提供
        Element internalElement=doc.getElementById("_internal_");
        String internal="";
        if(internalElement!=null){
            internal=internalElement.attr("value");
        }
        
        Elements links = doc.getElementsByTag("a");
        if (links != null) {
            for (Element link : links) {
                String linkHref = link.attr("href");
                if (StringUtils.isNotEmpty(linkHref)) {
                    if (linkHref.indexOf("amp;") > -1) {
                        linkHref = linkHref.replace("amp;", "").trim();
                    }
                    if ("1".equals(type)) {
                        if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1) {
                            String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
                            link.attr("href", "http://m.shangpin.com/subject/product/list?topicId=" + topicid);
                        } else if (linkHref.indexOf("http://www.aolai.com/Subject/Index/") > -1) {
                            String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
                            link.attr("href", "http://m.aolai.com/merchandiseaction!list?activityId=" + topicid);
                        } else if (linkHref.indexOf("http://www.shangpin.com/product/") > -1) {
                            String productNo = linkHref.split("http://www.shangpin.com/product/")[1];
                            link.attr("href", "http://m.shangpin.com/product/detail?productNo=" + productNo);
                        }
                    } else {
                        String[] queryArr = linkHref.split("[?]");
                        // 好多，心好累
                        String queryStr = null;
                        if (queryArr.length >= 2) {
                            queryStr = queryArr[1];
                        }
                        if (linkHref.indexOf("merchandiseaction!splist") > -1 && queryStr.indexOf("topicid=") > -1) {
                            String topicid = splitQuery(queryStr).get("topicid").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                        } else if (linkHref.indexOf("brandaction!getBrandProductsList") > -1 && queryStr.indexOf("brandNO=") > -1) {
                            String brandid = splitQuery(queryStr).get("brandNO").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                        } else if (linkHref.indexOf("brandaction!getSPProductsBrand") > -1 && queryStr.indexOf("brandid=") > -1) {
                            String brandid = splitQuery(queryStr).get("brandid").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                        } else if (linkHref.indexOf("merchandiseaction!spdetail") > -1 && queryStr.indexOf("productid=") > -1) {
                            String productid = splitQuery(queryStr).get("productid").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
                        } else if (linkHref.indexOf("categoryproductsaction!getCategoryProducts") > -1 && queryStr.indexOf("categoryid=") > -1) {
                            String cateid = splitQuery(queryStr).get("categoryid").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                        } else if (linkHref.indexOf("categoryproductsaction!getProductList") > -1 && queryStr.indexOf("categoryNO=") > -1) {
                            String cateid = splitQuery(queryStr).get("categoryNO").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                        } else if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1) {
                            String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                        } else if (linkHref.indexOf("www.shangpin.com/product/") > -1) {
                            String productNo = linkHref.split("www.shangpin.com/product/")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
                        } else if (linkHref.indexOf("product/detail_") > -1) {
                            String productNo = linkHref.split("product/detail_")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
                        } else if (linkHref.indexOf("product/detail?") > -1) {
                            String productNo = splitQuery(queryStr).get("productNo").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
                        } else if (linkHref.indexOf("subject/product/list_") > -1) {
                            String topicid = linkHref.split("subject/product/list_")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                        } else if (linkHref.indexOf("subject/product/list?") > -1) {
                            String topicid = splitQuery(queryStr).get("topicId").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                        } else if (linkHref.indexOf("category/product/list?") > -1) {
                            String cateid = splitQuery(queryStr).get("categoryNo").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                        } else if (linkHref.indexOf("category/product/list_") > -1) {
                            String cateid = linkHref.split("category/product/list_")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                        } else if (linkHref.indexOf("brand/product/list_") > -1) {
                            String brandid = linkHref.split("brand/product/list_")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                        } else if (linkHref.indexOf("brand/product/list?") > -1) {
                            String brandid = splitQuery(queryStr).get("brandNo").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                        }

                    }
                }
            }
        }
        String dochtml = doc.html();
        if (StringUtils.isNotEmpty(dochtml)) {
            dochtml = doc.html().replace("amp;", "").replaceFirst("<html>", "").replaceFirst(" <head></head>", "").replaceFirst("<body>", "").replaceFirst("</body>", "")
                    .replaceFirst("</html>", "");
        }
        return internal+"|"+dochtml;
    }

	/**
	 * 
	 * @param html
	 *            主站返回的html
	 * @param type
	 *            1:m站;2:android;3:ios
	 * @return
	 */
	public static String modifyHref(String html, String type) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		//访问的js或者css 是否需要内部提供
		Element internalElement=doc.getElementById("_internal_");
		String internal="";
		if(internalElement!=null){
			internal=internalElement.attr("value");
		}
		
		Elements links = doc.getElementsByTag("a");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (StringUtils.isNotEmpty(linkHref)) {
					if (linkHref.indexOf("amp;") > -1) {
						linkHref = linkHref.replace("amp;", "").trim();
					}
					boolean isFloor = false;
					if(linkHref.indexOf("www.shangpin.com/women/subject/") > -1 && linkHref.indexOf("isFloor=1")>-1){
						isFloor = true;
						linkHref = linkHref.replace("?isFloor=1", "").trim();
					}else if(linkHref.indexOf("/subject/product/list") > -1 && linkHref.indexOf("isFloor=1")>-1){
						isFloor = true;
						linkHref = linkHref.replace("&isFloor=1", "").trim();
					}
					
					if ("1".equals(type)) {
						if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", "http://m.shangpin.com/subject/product/list?topicId=" + topicid);
						} else if (linkHref.indexOf("http://www.aolai.com/Subject/Index/") > -1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", "http://m.aolai.com/merchandiseaction!list?activityId=" + topicid);
						} else if (linkHref.indexOf("http://www.shangpin.com/product/") > -1) {
							String productNo = linkHref.split("http://www.shangpin.com/product/")[1];
							link.attr("href", "http://m.shangpin.com/product/detail?productNo=" + productNo);
						}
					} else {
						String[] queryArr = linkHref.split("[?]");
						// 好多，心好累
						String queryStr = null;
						if (queryArr.length >= 2) {
							queryStr = queryArr[1];
						}
						if (linkHref.indexOf("merchandiseaction!splist") > -1 && queryStr.indexOf("topicid=") > -1) {
							String topicid = splitQuery(queryStr).get("topicid").toString();
							link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
						} else if (linkHref.indexOf("brandaction!getBrandProductsList") > -1 && queryStr.indexOf("brandNO=") > -1) {
							String brandid = splitQuery(queryStr).get("brandNO").toString();
							//link.attr("href",APP_LINK_BRAND_SUFFIX+"brandid_" + brandid);
							link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
						} else if (linkHref.indexOf("brandaction!getSPProductsBrand") > -1 && queryStr.indexOf("brandid=") > -1) {
							String brandid = splitQuery(queryStr).get("brandid").toString();
							//link.attr("href",APP_LINK_BRAND_SUFFIX+"brandid_" + brandid);
							link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
						} else if (linkHref.indexOf("merchandiseaction!spdetail") > -1 && queryStr.indexOf("productid=") > -1) {
							String productid = splitQuery(queryStr).get("productid").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
						} else if (linkHref.indexOf("categoryproductsaction!getCategoryProducts") > -1 && queryStr.indexOf("categoryid=") > -1) {
							String cateid = splitQuery(queryStr).get("categoryid").toString();
							//link.attr("href",APP_LINK_CATEGORY_SUFFIX+"categoryid_" + cateid);
							link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
						} else if (linkHref.indexOf("categoryproductsaction!getProductList") > -1 && queryStr.indexOf("categoryNO=") > -1) {
							String cateid = splitQuery(queryStr).get("categoryNO").toString();
							link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid);
							//link.attr("href",APP_LINK_CATEGORY_SUFFIX+"categoryid_" + cateid+"&categoryid=" + cateid);
						} else if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							if(isFloor){//开启楼层，app需要配置成 m站地址
//	                            link.attr("href", "http://m.shangpin.com/subject/product/list?topicId=" + topicid);
	                            link.attr("href", "ShangPinApp://phone.shangpin/actiongowebview?title=尚品&url=http://m.shangpin.com/subject/product/list?topicId=" + topicid);
							}else{
								//link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
								link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
							}
						} else if (linkHref.indexOf("www.shangpin.com/product/") > -1) {
							String productNo = linkHref.split("www.shangpin.com/product/")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
						} else if (linkHref.indexOf("product/detail_") > -1) {
							String productNo = linkHref.split("product/detail_")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
						} else if (linkHref.indexOf("product/detail?") > -1) {
							String productNo = splitQuery(queryStr).get("productNo").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
						} else if (linkHref.indexOf("subject/product/list_") > -1) {
							String topicid = linkHref.split("subject/product/list_")[1];
							//link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
							link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
						} else if (linkHref.indexOf("subject/product/list?") > -1) {
							String topicid = splitQuery(queryStr).get("topicId").toString();
							if(isFloor){//开启楼层，app需要配置成 m站地址
//	                            link.attr("href", "http://m.shangpin.com/subject/product/list?topicId=" + topicid);
								link.attr("href", "ShangPinApp://phone.shangpin/actiongowebview?title=尚品&url=http://m.shangpin.com/subject/product/list?topicId=" + topicid);
							}else{
								//link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
								link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
							}
						} else if (linkHref.indexOf("category/product/list?") > -1) {
							String cateid = splitQuery(queryStr).get("categoryNo").toString();
							//link.attr("href",APP_LINK_CATEGORY_SUFFIX+"categoryid_" + cateid);
							link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
						} else if (linkHref.indexOf("category/product/list_") > -1) {
							String cateid = linkHref.split("category/product/list_")[1];
							//link.attr("href",APP_LINK_CATEGORY_SUFFIX+"categoryid_" + cateid);
							link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
						} else if (linkHref.indexOf("brand/product/list_") > -1) {
							String brandid = linkHref.split("brand/product/list_")[1];
							//link.attr("href",APP_LINK_BRAND_SUFFIX+"brandid_" + brandid);
							link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
						} else if (linkHref.indexOf("brand/product/list?") > -1) {
							String brandid = splitQuery(queryStr).get("brandNo").toString();
							//link.attr("href",APP_LINK_BRAND_SUFFIX+"brandid_" + brandid);
							link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
							
						} else if (linkHref.indexOf("lable/product/list?") > -1) {
							String tagId = splitQuery(queryStr).get("tagId").toString();
							link.attr("href",APP_LINK_TAG_SUFFIX+"&tagid="+ tagId);
						}else{
							if(linkHref.indexOf("javascript") == -1&&linkHref.length()>10){
								link.attr("href",APP_LINK_WEBVIEW_SUFFIX+"&url="+ linkHref.replace("&", "8uuuuu8"));
							}
						}

					}
				}
			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "").replaceFirst("<html>", "").replaceFirst(" <head></head>", "").replaceFirst("<body>", "").replaceFirst("</body>", "")
					.replaceFirst("</html>", "");
		}
		return internal+"|"+dochtml;
	}
	/**
	 * 
	 * @param html
	 *            主站返回的html
	 * @param type
	 *            1:m站;2:android;3:ios
	 * @return
	 */
	public static String modifyHrefAoLai(String html, String type) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("a");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (StringUtils.isNotEmpty(linkHref)) {
					if (linkHref.indexOf("amp;") > -1) {
						linkHref = linkHref.replace("amp;", "").trim();
					}
					if ("1".equals(type)) {
						if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", "http://m.aolai.com/merchandiseaction!list?activityId=" + topicid);
						}
					}else {
						String[] queryArr = linkHref.split("[?]");
						String queryStr = null;
						if (queryArr.length >= 2) {
							queryStr = queryArr[1];
						}
						if (linkHref.indexOf("activity/list/") > -1 && linkHref.split("activity/list/").length>1 && linkHref.split("activity/list/")[1].split("_").length>=1) {
							String activityid=linkHref.split("activity/list/")[1].split("_")[0];
							link.attr("href", "shangpinapp://phone.aolai/actiongoactivitylist?title=奥莱&activityid=" + activityid);
						}else if(linkHref.indexOf("activity/lv2") > -1 && queryStr.indexOf("activityId=") > -1){
							String activityid = splitQuery(queryStr).get("activityId").toString();
							link.attr("href", "shangpinapp://phone.aolai/actiongoactivitylist?title=奥莱&activityid=" + activityid);
						} else if (linkHref.indexOf("product/") > -1 && linkHref.split("product/").length>1 && linkHref.split("product/")[1].split("_").length>1) {
							String categoryno=linkHref.split("product/")[1].split("_")[0];
							String productno=linkHref.split("product/")[1].split("_")[1];
							link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=奥莱&categoryno=" + categoryno+"&productno="+productno);
						}else if(linkHref.indexOf("activity/detail") > -1 && queryStr.indexOf("categoryNo=") > -1 && queryStr.indexOf("goodsId=") > -1){
							String categoryno = splitQuery(queryStr).get("categoryNo").toString();
							String productno = splitQuery(queryStr).get("goodsId").toString();
							link.attr("href", "shangpinapp://phone.aolai/actiongodetail?title=奥莱&categoryno=" + categoryno+"&productno="+productno);
						}

					} 
				}
			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "").replaceFirst("<html>", "").replaceFirst(" <head></head>", "").replaceFirst("<body>", "").replaceFirst("</body>", "")
					.replaceFirst("</html>", "");
		}
		return dochtml;
	}

	/**
	 * 尚潮流兼容
	 * 
	 * @param html
	 *            主站返回的html
	 * @param type
	 *            1:m站;2:android;3:ios
	 * @return
	 * @throws IOException 
	 */
	public static String modifyOldFashionDetail(String html, String type) {
		Document doc;
		doc = Jsoup.parse(html, "UTF-8");
		Elements links = doc.getElementsByTag("a");
		if (links != null) {
			for (Element link : links) {
				String linkHref = link.attr("href");
				if (StringUtils.isNotEmpty(linkHref)) {
					if (linkHref.indexOf("amp;") > -1) {
						linkHref = linkHref.replace("amp;", "").trim();
					}
					if ("1".equals(type)) {
						if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1 && linkHref.split("www.shangpin.com/women/subject/").length>1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
						} else if (linkHref.indexOf("http://www.aolai.com/Subject/Index/") > -1 && linkHref.split("www.shangpin.com/women/subject/").length>1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", "http://m.aolai.com/merchandiseaction!list?activityId=" + topicid);
						} else if (linkHref.indexOf("http://www.shangpin.com/product/") > -1 && linkHref.split("http://www.shangpin.com/product/").length>1) {
							String productNo = linkHref.split("http://www.shangpin.com/product/")[1];
							link.attr("href", "http://m.shangpin.com/product/detail?productNo=" + productNo);
						}
					} else {
						String[] queryArr = linkHref.split("[?]");
						// 好多，心好累
						String queryStr = null;
						if (queryArr.length >= 2) {
							queryStr = queryArr[1];
						}
						if (linkHref.indexOf("merchandiseaction!splist") > -1 && queryStr.indexOf("topicid=") > -1) {
							String topicid = splitQuery(queryStr).get("topicid").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
						} else if (linkHref.indexOf("brandaction!getBrandProductsList") > -1 && queryStr.indexOf("brandNO=") > -1) {
							String brandid = splitQuery(queryStr).get("brandNO").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
						} else if (linkHref.indexOf("brandaction!getSPProductsBrand") > -1 && queryStr.indexOf("brandid=") > -1) {
							String brandid = splitQuery(queryStr).get("brandid").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
						} else if (linkHref.indexOf("merchandiseaction!spdetail") > -1 && queryStr.indexOf("productid=") > -1) {
							String productid = splitQuery(queryStr).get("productid").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
						} else if (linkHref.indexOf("categoryproductsaction!getCategoryProducts") > -1 && queryStr.indexOf("categoryid=") > -1) {
							String cateid = splitQuery(queryStr).get("categoryid").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
						} else if (linkHref.indexOf("categoryproductsaction!getProductList") > -1 && queryStr.indexOf("categoryNO=") > -1) {
							String cateid = splitQuery(queryStr).get("categoryNO").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
						} else if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1 && linkHref.split("www.shangpin.com/women/subject/").length>1) {
							String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
						} else if (linkHref.indexOf("www.shangpin.com/product/") > -1 && linkHref.split("www.shangpin.com/product/").length>1) {
							String productNo = linkHref.split("www.shangpin.com/product/")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
						} else if (linkHref.indexOf("product/detail_") > -1&&linkHref.split("product/detail_").length>1) {
							String productNo = linkHref.split("product/detail_")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
						} else if (linkHref.indexOf("product/detail?") > -1 && linkHref.split("product/detail?").length>1) {
							String productNo = splitQuery(queryStr).get("productNo").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
						} else if (linkHref.indexOf("subject/product/list_") > -1 && linkHref.split("subject/product/list_").length>1) {
							String topicid = linkHref.split("subject/product/list_")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
						} else if (linkHref.indexOf("subject/product/list?") > -1 && linkHref.split("subject/product/list?").length>1) {
							String topicid = splitQuery(queryStr).get("topicId").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
						} else if (linkHref.indexOf("category/product/list?") > -1 && linkHref.split("category/product/list?").length>1) {
							String cateid = splitQuery(queryStr).get("categoryNo").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
						} else if (linkHref.indexOf("category/product/list_") > -1 && linkHref.split("category/product/list_").length>1) {
							String cateid = linkHref.split("category/product/list_")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
						} else if (linkHref.indexOf("brand/product/list_") > -1 && linkHref.split("brand/product/list_").length>1) {
							String brandid = linkHref.split("brand/product/list_")[1];
							link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
						} else if (linkHref.indexOf("brand/product/list?") > -1) {
							String brandid = splitQuery(queryStr).get("brandNo").toString();
							link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
						}

					}
				}
			}
		}
		String dochtml = doc.html();
		if (StringUtils.isNotEmpty(dochtml)) {
			dochtml = doc.html().replace("amp;", "").replaceFirst("<body>", "").replaceFirst("</body>", "").replaceFirst("<html>", "").replaceFirst("</html>", "")
					.replaceFirst("<head>", "").replaceFirst("</head>", "");
		}
		return dochtml;
	}
	
	/**
     * 尚潮流兼容
     * 
     * @param html
     *            主站返回的html
     * @param type
     *            1:m站;2:android;3:ios
     * @return
     * @throws IOException 
     */
    public static String modifyFashionDetail(String html, String type) {
        Document doc;
        doc = Jsoup.parse(html, "UTF-8");
        Elements links = doc.getElementsByTag("a");
        if (links != null) {
            for (Element link : links) {
                String linkHref = link.attr("href");
                if (StringUtils.isNotEmpty(linkHref)) {
                    if (linkHref.indexOf("amp;") > -1) {
                        linkHref = linkHref.replace("amp;", "").trim();
                    }
                    if ("1".equals(type)) {
                        if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1 && linkHref.split("www.shangpin.com/women/subject/").length>1) {
                            String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
                            link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
                        } else if (linkHref.indexOf("http://www.aolai.com/Subject/Index/") > -1 && linkHref.split("www.shangpin.com/women/subject/").length>1) {
                            String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
                            link.attr("href", "http://m.aolai.com/merchandiseaction!list?activityId=" + topicid);
                        } else if (linkHref.indexOf("http://www.shangpin.com/product/") > -1 && linkHref.split("http://www.shangpin.com/product/").length>1) {
                            String productNo = linkHref.split("http://www.shangpin.com/product/")[1];
                            link.attr("href", "http://m.shangpin.com/product/detail?productNo=" + productNo);
                        }
                    } else {
                        String[] queryArr = linkHref.split("[?]");
                        // 好多，心好累
                        String queryStr = null;
                        if (queryArr.length >= 2) {
                            queryStr = queryArr[1];
                        }
                        if (linkHref.indexOf("merchandiseaction!splist") > -1 && queryStr.indexOf("topicid=") > -1) {
                            String topicid = splitQuery(queryStr).get("topicid").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                            link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
                        } else if (linkHref.indexOf("brandaction!getBrandProductsList") > -1 && queryStr.indexOf("brandNO=") > -1) {
                            String brandid = splitQuery(queryStr).get("brandNO").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                            link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
                        } else if (linkHref.indexOf("brandaction!getSPProductsBrand") > -1 && queryStr.indexOf("brandid=") > -1) {
                            String brandid = splitQuery(queryStr).get("brandid").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                            link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
                        } else if (linkHref.indexOf("merchandiseaction!spdetail") > -1 && queryStr.indexOf("productid=") > -1) {
                            String productid = splitQuery(queryStr).get("productid").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productid);
                        } else if (linkHref.indexOf("categoryproductsaction!getCategoryProducts") > -1 && queryStr.indexOf("categoryid=") > -1) {
                            String cateid = splitQuery(queryStr).get("categoryid").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                            link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
                        } else if (linkHref.indexOf("categoryproductsaction!getProductList") > -1 && queryStr.indexOf("categoryNO=") > -1) {
                            String cateid = splitQuery(queryStr).get("categoryNO").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                            link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
                        } else if (linkHref.indexOf("www.shangpin.com/women/subject/") > -1 && linkHref.split("www.shangpin.com/women/subject/").length>1) {
                            String topicid = linkHref.split("www.shangpin.com/women/subject/")[1];
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                            link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
                        } else if (linkHref.indexOf("www.shangpin.com/product/") > -1 && linkHref.split("www.shangpin.com/product/").length>1) {
                            String productNo = linkHref.split("www.shangpin.com/product/")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
                        } else if (linkHref.indexOf("product/detail_") > -1&&linkHref.split("product/detail_").length>1) {
                            String productNo = linkHref.split("product/detail_")[1];
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
                        } else if (linkHref.indexOf("product/detail?") > -1 && linkHref.split("product/detail?").length>1) {
                            String productNo = splitQuery(queryStr).get("productNo").toString();
                            link.attr("href", "shangpinapp://phone.shangpin/actiongodetail?title=商品详情&productid=" + productNo);
                        } else if (linkHref.indexOf("subject/product/list_") > -1 && linkHref.split("subject/product/list_").length>1) {
                            String topicid = linkHref.split("subject/product/list_")[1];
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                            link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
                        } else if (linkHref.indexOf("subject/product/list?") > -1 && linkHref.split("subject/product/list?").length>1) {
                            String topicid = splitQuery(queryStr).get("topicId").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongoactivitylist?title=尚品&activityid=" + topicid);
                            link.attr("href", APP_LINK_ACTIVITY_SUFFIX + topicid);
                        } else if (linkHref.indexOf("category/product/list?") > -1 && linkHref.split("category/product/list?").length>1) {
                            String cateid = splitQuery(queryStr).get("categoryNo").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                            link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
                        } else if (linkHref.indexOf("category/product/list_") > -1 && linkHref.split("category/product/list_").length>1) {
                            String cateid = linkHref.split("category/product/list_")[1];
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongocatelist?title=尚品&categoryid=" + cateid);
                            link.attr("href",APP_LINK_CATEGORY_SUFFIX+"category_" + cateid+"&categoryid=" + cateid);
                        } else if (linkHref.indexOf("brand/product/list_") > -1 && linkHref.split("brand/product/list_").length>1) {
                            String brandid = linkHref.split("brand/product/list_")[1];
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                            link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
                        } else if (linkHref.indexOf("brand/product/list?") > -1) {
                            String brandid = splitQuery(queryStr).get("brandNo").toString();
                            //link.attr("href", "shangpinapp://phone.shangpin/actiongobrandlist?title=尚品&brandid=" + brandid);
                            link.attr("href",APP_LINK_BRAND_SUFFIX+"brand_" + brandid+"&brandid=" + brandid);
                        }

                    }
                }
            }
        }
        String dochtml = doc.html();
        if (StringUtils.isNotEmpty(dochtml)) {
            dochtml = doc.html().replace("amp;", "").replaceFirst("<body>", "").replaceFirst("</body>", "").replaceFirst("<html>", "").replaceFirst("</html>", "")
                    .replaceFirst("<head>", "").replaceFirst("</head>", "");
        }
        return dochtml;
    }

	
}
