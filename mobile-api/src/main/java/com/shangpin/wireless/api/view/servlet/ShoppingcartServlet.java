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
 * 购物车列表接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-28
 */
public class ShoppingcartServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ShoppingcartServlet.class);

	ShangPinService shangPinService = null;
    AoLaiService  aoLaiService=null;
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
        aoLaiService = (AoLaiService) getBean(AoLaiService.class);
    }
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String sessionid = request.getParameter("sessionid");
		String shoptype = request.getParameter("shoptype");
		String imei = request.getHeader("imei");
		String picquality = request.getParameter("picquality");
		String productNum = request.getHeader("p");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, sessionid, imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("isPromotion", "0");
				if ("high".equals(picquality)) {
					map.put("picw", "132");
					map.put("pich", "176");
				} else if ("low".equals(picquality)) {
					map.put("picw", "132");
					map.put("pich", "176");
				} else {
					map.put("picw", "132");
					map.put("pich", "176");
				}		
				String apiResult="";
				JSONObject cartResult;
				JSONObject centent;
				String url = Constants.BASE_TRADE_URL + "trade/cartlist/";
				String data2 = WebUtil.readContentFromGet(url, map);
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
				try {
					response.getWriter().print(apiResult);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ShoppingcartServlet：" + e);
					try {
						WebUtil.sendApiException(response);
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
			FileUtil.addLog(request, "shoppingcart", channelNo,
					"userid", userid,
					"shoptype",shoptype,
					"productNum", productNum);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
	/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String sessionid = request.getParameter("sessionid");
		String shoptype = request.getParameter("shoptype");
		String imei = request.getHeader("imei");
		String picquality = request.getParameter("picquality");
		String productNum = request.getHeader("p");
		String ver = request.getHeader("ver");
		if (StringUtil.isNotEmpty(userid, sessionid, imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				if(!StringUtil.isNotEmpty(shoptype)) {
					shoptype = "2";
				}
				map.put("shoptype", shoptype);
				if ("high".equals(picquality)) {
					map.put("picw", "132");
					map.put("pich", "176");
					map.put("detailpicw", "420");
					map.put("detailpich", "560");
				} else if ("low".equals(picquality)) {
					map.put("picw", "132");
					map.put("pich", "176");
					map.put("detailpicw", "210");
					map.put("detailpich", "280");
				} else {
					map.put("picw", "132");
					map.put("pich", "176");
					map.put("detailpicw", "420");
					map.put("detailpich", "560");
				}
				String url = Constants.BASE_URL_OLD + "SelectShoppingCartList/";
				// 奥莱Android版本大于1.0.0使用新架构；奥莱iPhone版本大于3.3.0使用新架构；
				if (("101".equals(productNum) && StringUtil.compareVer(ver, "1.0.0") == 1) || ("1".equals(productNum) && StringUtil.compareVer(ver, "3.3.1") > 0)) {
					url = Constants.BASE_URL + "SelectShoppingCartList/";
				}
				// 尚品Android版本大于1.0.3使用新架构；尚品iPhone版本大于1.0.2使用新架构；
				if (("102".equals(productNum) && StringUtil.compareVer(ver, "1.0.3") == 1) || ("2".equals(productNum) && StringUtil.compareVer(ver, "1.0.2") == 1)) {
					url = Constants.BASE_URL_SP + "SelectShoppingCartList/";
				}
				try {
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ShoppingcartServlet：" + e);
					try {
						WebUtil.sendApiException(response);
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
			FileUtil.addLog(request, "shoppingcart", "userid", userid);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
