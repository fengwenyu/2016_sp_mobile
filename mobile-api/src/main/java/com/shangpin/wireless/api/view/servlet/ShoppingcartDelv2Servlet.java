package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.wireless.api.api2client.domain.ProductListAPIResponse;
import com.shangpin.wireless.api.api2client.domain.ProductSnapAPIResponse;
import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 删除购物车中的商品(新架构)
 * 
 * @CreatDate: 2013-08-01
 */
public class ShoppingcartDelv2Servlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ShoppingcartDelv2Servlet.class);

	ShangPinService shangPinService = null;
    AoLaiService  aoLaiService=null;
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
        aoLaiService = (AoLaiService) getBean(AoLaiService.class);
    }



	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userid = request.getParameter("userid");
		final String skuid = request.getParameter("skuid");
		final String shoptype = request.getParameter("shoptype");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String picquality = request.getParameter("picquality");
		final String picw = request.getParameter("picw");
		final String pich = request.getParameter("pich");
		final String count = request.getParameter("count");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		final String shoppingCartDetailId = request.getParameter("shoppingcartdetailid");
		if (StringUtil.isNotEmpty(userid, imei, sessionid,count, shoppingCartDetailId)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userid);
				map.put("shopCartDetailIds", shoppingCartDetailId);
				String url = Constants.BASE_TRADE_URL + "trade/deletecartbydetailId/";										
				try {					
					String data = WebUtil.readContentFromPost(url, map);
					if(data!=null&&!"".equals(data)){
						String apiResult="";
						JSONObject cartResult;
						JSONObject centent;
						JSONObject obj = JSONObject.fromObject(data);
						String addCode=obj.getString("code");
						if("0".equals(addCode)){
								Map<String, String> listmap = new HashMap<String, String>();							
								listmap.put("userId", userid);
								listmap.put("isPromotion", "0");
								if(picw==null||pich==null){
									if ("high".equals(picquality)) {
										listmap.put("width", "132");
										listmap.put("height", "176");
									} else if ("low".equals(picquality)) {
										listmap.put("width", "132");
										listmap.put("height", "176");
									} else {
										listmap.put("width", "132");
										listmap.put("height", "176");
									}
								}else{
									listmap.put("width", picw);
									listmap.put("height", pich);
								}																
								String listurl = Constants.BASE_TRADE_URL + "trade/cartlist/";
									String data2 = WebUtil.readContentFromGet(listurl, listmap);
									ProductServerAllCartVO productServerAllCartVO=new ProductServerAllCartVO().jsonObj(data2);
									ProductListAPIResponse apiResponse=new ProductListAPIResponse();
									apiResult=apiResponse.objJson2(shoptype,productServerAllCartVO);	
									cartResult=JSONObject.fromObject(apiResult);
									centent=JSONObject.fromObject(cartResult.getString("content"));
									JSONArray productsArray=centent.getJSONArray("list");
									String data3="";
									String detail="";
									JSONArray newListArray=new JSONArray();
									JSONObject productObj;
									ListOfGoods listOfGoodsVO=new ListOfGoods();
                                    if ("high".equals(picquality)) {
                                        listOfGoodsVO.setPicw("420");
                                        listOfGoodsVO.setPich("560");
                                    } else if ("low".equals(picquality)) {
                                        listOfGoodsVO.setPicw("210");
                                        listOfGoodsVO.setPich("280");
                                    } else {
                                        listOfGoodsVO.setPicw("420");
                                        listOfGoodsVO.setPich("560");
                                    }   
									for(int i=0;i<productsArray.size();i++){
										productObj=(JSONObject)productsArray.get(i);
										if("1".equals(shoptype)){											
										    listOfGoodsVO.setProductId( productObj.getString("goodsid"));
                                            listOfGoodsVO.setUserId(userid == null ? "" : userid);
                                            listOfGoodsVO.setTopicId("");
                                            listOfGoodsVO.setUserId(userid);
                                            data3=shangPinService.findSPProductDetail(listOfGoodsVO);
											detail = new ProductSnapAPIResponse().objSpJson2(data3,productObj.getString("count"));										
										}else{
											data3=aoLaiService.findProductDetail(productObj.getString("goodsid"),"1",productObj.getString("categoryno"),listOfGoodsVO.getPicw(),listOfGoodsVO.getPich());                                            
											detail = new ProductSnapAPIResponse().objAlJson2(data3,productObj.getString("count"));									
										}
										productObj.put("detail", detail);
										newListArray.add(productObj);
									}
									centent.put("list", newListArray);
									cartResult.put("content", centent);
									apiResult=cartResult.toString();																															
						}else{
						    response.getWriter().print(data);
						}
						response.getWriter().print(apiResult);
					}													
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ShoppingcartDelServlet：" + e);
					try {
						WebUtil.sendErrorNoSessionid(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shoppingcartdel", channelNo,
					"userid", userid, 
					"skuid", skuid);
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
