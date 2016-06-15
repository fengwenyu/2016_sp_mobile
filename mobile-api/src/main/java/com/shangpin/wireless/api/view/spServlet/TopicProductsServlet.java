package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.DateUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.PropertiesUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;


/**
 * 获取尚品专题商品列表
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class TopicProductsServlet extends BaseServlet  {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(TopicProductsServlet.class);

	private static HashMap<String, String> ActbannerPricetagCache = new HashMap<String, String>();
	private static final int ACTBANNERPRICETAG_KEEP_TIME = 30 * 60 * 1000; // 30分钟
	private static long ActbannerPricetagTime;
	ShangPinService shangPinService = null;
    
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pagesize = request.getParameter("pagesize");
		String pageindex = request.getParameter("pageindex");
		String gender = request.getParameter("gender");
		String topicid = request.getParameter("topicid");
		String channelNo = request.getHeader("ch");;//获取渠道号
		String widthHeight = "10";
//		String picw = request.getParameter("picw");
//		String pich = request.getParameter("pich");
		if (StringUtil.isNotEmpty(topicid, pagesize)) {
			/*Map<String, String> map = new HashMap<String, String>();
			map.put("userid", null == userid ? "" : userid);
			map.put("pagesize", pagesize);
			map.put("pageindex", pageindex);
			map.put("gender", (null == gender || "".equals(gender.trim()) ? "0" : gender));
			map.put("topicid", null == topicid ? "" : topicid);
			map.put("picw", "{w}");
			map.put("pich", "{h}");*/
//			String url = Constants.SP_BASE_URL + "SPTopicProducts/";
//			try {
//				String data = WebUtil.readContentFromGet(url, map);
//				response.getWriter().print(data);
//			} catch (Exception e) {
//				e.printStackTrace();
//				log.error("TopicProductsServlet：" + e);
//				try {
//					WebUtil.sendApiException(response);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
//			}
			//String url = Constants.BASE_URL_SP_SP + "SPNewTopicProducts/";
		    ListOfGoods listOfGoodsVO=new ListOfGoods();
            listOfGoodsVO.setUserId(null == userid ? "" : userid);
            listOfGoodsVO.setPageSize(pagesize);
            listOfGoodsVO.setPageIndex(pageindex);
            listOfGoodsVO.setGender((null == gender || "".equals(gender.trim()) ? "0" : gender));
            listOfGoodsVO.setTopicId(null == topicid ? "" : topicid);
            listOfGoodsVO.setPicw(widthHeight);
            listOfGoodsVO.setPich(widthHeight);
			try {
			   
				String data = shangPinService.findTopicProducts(listOfGoodsVO);
				JSONObject obj = JSONObject.fromObject(data);
				final String code = obj.getString("code");
				if (Constants.SUCCESS.equals(code)) {
					JSONObject content = obj.getJSONObject("content");
					JSONObject respcontent = new JSONObject();
					respcontent.put("shareurl", content.getString("shareurl"));
					respcontent.put("topicid", content.getString("topicid"));
					respcontent.put("name", content.getString("name"));
					respcontent.put("topicdesc", content.getString("topicdesc"));
					String o1code = content.getString("o1code");
					//0无、1领取优惠券、2图片链接
					if (StringUtil.isNotEmpty(o1code)) {
						respcontent.put("o1code", o1code);
					}
					respcontent.put("o1logo",content.getString("o1logo"));
					respcontent.put("o1url",content.getString("o1url"));
					String pretime=content.getString("datepretime");
					String starttime=content.getString("starttime");
					String productNum = request.getHeader("p");
					//兼容iphone的bug
					if(productNum != null && "2".equals(productNum)){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(starttime != null){
							long startTime = sdf.parse(starttime).getTime();
							long now = new Date().getTime();
							if(now >= (startTime)){
								respcontent.put("starttime","");
							}else{
								respcontent.put("starttime",starttime);
							}
						}
					}else{
						respcontent.put("starttime",starttime);
					}
					String endtime=content.getString("endtime");
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					if(StringUtils.isNotEmpty(endtime)){
						if(DateUtil.marginDate(sdf.parse(starttime),sdf.parse(endtime))>=7){
							respcontent.put("endtime",DateUtil.getAfterAmountDay(sdf.parse(starttime), 7, "yyyy-MM-dd HH:mm:ss"));
						}else{
							respcontent.put("endtime",endtime);
						}
					}else{
						respcontent.put("endtime",DateUtil.getAfterAmountDay(sdf.parse(starttime), 7, "yyyy-MM-dd HH:mm:ss"));
					}
					
					
					respcontent.put("pretime", pretime);
					respcontent.put("topicdesc", content.getString("topicdesc"));
					
					long now = System.currentTimeMillis();
				

					if (now > ActbannerPricetagTime + ACTBANNERPRICETAG_KEEP_TIME) {
						Properties props = PropertiesUtil.getInstance("/config/topicproduct/actbanner.properties");
						ActbannerPricetagCache.clear();
						for (Enumeration e = props.keys(); e.hasMoreElements();) {
							String propkey = (String) e.nextElement();
							String propvalue = null;
							if(StringUtil.isNotEmpty(props.getProperty(propkey))){
								propvalue = new String(props.getProperty(propkey));
							}
							
							ActbannerPricetagCache.put(propkey, propvalue);
						}
						ActbannerPricetagTime= now;
					}
					
										
					String html = null;
					String adverdata = null;
					// 获取尚品专题json格式字符串
					adverdata = shangPinService.getTopAdver(topicid);//20120810340
					if (null != adverdata && !"".equals(adverdata)) {
						JSONObject jsonObj = JSONObject.fromObject(adverdata);
						if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
							JSONObject topAdverobj = (JSONObject) jsonObj.get("content");
							if (null != topAdverobj.get("template") && !"".equals(topAdverobj.get("template").toString())) {
								html = topAdverobj.getString("template");
							}
						}
					}
					
					String actbannerheight=null;
					if(StringUtils.isNotEmpty(html)){
						Document doc = Jsoup.parseBodyFragment(html);
						if (StringUtil.isNotEmpty("div")) {
							actbannerheight=doc.select("div").attr("pageH");
							
						} 
					}
				
					JSONObject actbanner = new JSONObject();
					String actbannerurl=null;
					String actbannerwidth=null;
					if(ActbannerPricetagCache!=null){
						actbannerurl =ActbannerPricetagCache.get("actbannerurl");
						actbannerwidth=ActbannerPricetagCache.get("actbannerwidth");
					}
					if(actbannerheight!=null){
						actbanner.put("height", actbannerheight);
						if(actbannerwidth!=null){
							actbanner.put("width", actbannerwidth);
						}else{
							actbanner.put("width", "");
						}
						if(actbannerurl!=null){
							actbanner.put("url", actbannerurl+topicid);
						}else{						    
							actbanner.put("url", Constants.BASE_M_SHANGPIN_URL+"subject/top/adver?toLink=1&topicId="+topicid);
						}
					}else{
						actbanner.put("height", "");
						actbanner.put("width", "");
						actbanner.put("url", "");
					}
					String pricename = null;
					respcontent.put("actbanner", actbanner);
					pricename=content.getString("pricename");
					boolean flag=false;
					if(pretime!=null&&!"".equals(pretime)){
						Date datepretime=sdf.parse(pretime);
						Date nowtime=new Date();
						flag=datepretime.before(nowtime);
						if(flag){
							Date startpretime=sdf.parse(starttime);
							flag=nowtime.before(startpretime);
						}
					}
					if(StringUtils.isNotEmpty(pricename)&&flag==true){
						respcontent.put("pricetag", "1");
					}else{ 
						respcontent.put("pricetag", "");
						
					}
					//	模板类型：1错落、2楼层、3标准
					String type = content.getString("type");//楼层模板支持分页，把楼层模板修改成普通模板
					if("2".equals(type)){
						type="3";
					}
					respcontent.put("type", type);
					JSONArray respProducts = new JSONArray();
					if (!"2".equals(type) || "1".equals(pageindex)) {
						JSONArray groups = content.getJSONArray("list");
						for (int i=0;i<groups.size();i++) {
							JSONObject group = groups.getJSONObject(i);
							JSONArray products = group.getJSONArray("products");
							
						
							for (int j=0;j<products.size();j++) {
								JSONArray specialprice =products.getJSONObject(j).getJSONArray("specialprice");
								JSONObject productObj = products.getJSONObject(j);
								if(specialprice!=null&&StringUtils.isNotEmpty(pricename)){
								    // TODO 这个太丑了！~
                                    if (!"更多惊喜".equalsIgnoreCase(pricename.trim())) {
                                    	productObj.put("pricename", pricename + " ￥" + specialprice.get(0));
                                    } else {
                                    	productObj.put("pricename", pricename);
                                    }
								}else{
									if(StringUtils.isEmpty(pricename)){
										productObj.put("pricename", "");
									}else{
										productObj.put("pricename", pricename);
									}
									
								}
								String pic = productObj.getString("pic");
								if(null != pic && !"".equals(pic)){
									productObj.put("pic", pic.replace("-"+widthHeight+"-"+widthHeight, "-{w}-{h}"));
								}
								JSONArray picsArray=productObj.getJSONArray("pics");
								JSONArray newPicsArray=new JSONArray();
								if (picsArray!=null&&picsArray.size()>0) {
								    String picObj=null;
								    for (int k = 0; k < picsArray.size(); k++) {
								        picObj=picsArray.get(k).toString();
								        newPicsArray.add(picObj.replace("-"+widthHeight+"-"+widthHeight, "-{w}-{h}"));
                                    }
                                    productObj.put("pics", newPicsArray);
                                }
								respProducts.add(productObj);
								
							}
						}
					}
					respcontent.put("list", respProducts);
					
					JSONObject respobj = new JSONObject();
					respobj.put("code", Constants.SUCCESS);
					respobj.put("msg", "");
					respobj.put("content", respcontent);
					response.getWriter().print(respobj.toString());
				} else {
					response.getWriter().print(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("TopicNewProductsServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpintopicproducts", channelNo,
					"userid", userid, 
					"pagesize", pagesize, 
					"pageindex", pageindex, 
					"gender", gender, 
					"topicid", topicid);
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
