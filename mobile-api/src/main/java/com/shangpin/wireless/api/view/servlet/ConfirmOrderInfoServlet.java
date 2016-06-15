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
import com.shangpin.wireless.api.api2client.domain.ProductSnapAPIResponse;
import com.shangpin.wireless.api.api2client.domain.SettlementOrderByOldAPIResponse;
import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.api2server.domain.SettlementServerOrderVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 确认订单信息
 * 
 * @Author:wangwenguan
 * @CreatDate: 2012-08-29
 */
public class ConfirmOrderInfoServlet extends BaseServlet {

    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(EditAddressServlet.class);


    ShangPinService shangPinService = null;
    AoLaiService  aoLaiService=null;
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
        aoLaiService = (AoLaiService) getBean(AoLaiService.class);
    }


    /*
     * public void doGet(HttpServletRequest request, HttpServletResponse
     * response) throws ServletException, IOException { final String product =
     * request.getHeader("p"); final String version = request.getHeader("ver");
     * String userid = request.getParameter("userid"); String sessionid =
     * request.getParameter("sessionid"); String imei =
     * request.getHeader("imei"); String picquality =
     * request.getParameter("picquality"); String shoptype =
     * request.getParameter("shoptype"); if (StringUtil.isNotEmpty(userid, imei,
     * sessionid)) { if (SessionUtil.validate(userid, imei, sessionid)) {
     * Map<String, String> map = new HashMap<String, String>();
     * map.put("userid", userid); if(!StringUtil.isNotEmpty(shoptype)) {
     * shoptype = "2"; } map.put("shoptype", shoptype); if
     * ("high".equals(picquality)) { map.put("picw", "132"); map.put("pich",
     * "176"); map.put("detailpicw", "420"); map.put("detailpich", "560"); }
     * else if ("low".equals(picquality)) { map.put("picw", "132");
     * map.put("pich", "176"); map.put("detailpicw", "210");
     * map.put("detailpich", "280"); } else { map.put("picw", "132");
     * map.put("pich", "176"); map.put("detailpicw", "420");
     * map.put("detailpich", "560"); } String url = Constants.BASE_URL_OLD +
     * "ConfirmOrderInfo/"; // 奥莱Android版本大于1.0.0使用新架构；奥莱iPhone版本大于3.3.0使用新架构；
     * if (("101".equals(product) && StringUtil.compareVer(version, "1.0.0") ==
     * 1) || ("1".equals(product) && StringUtil.compareVer(version, "3.3.1") >
     * 0)) { url = Constants.BASE_URL + "ConfirmOrderInfo/"; } //
     * 尚品Android版本大于1.0.3使用新架构；尚品iPhone版本大于1.0.2使用新架构； if
     * (("102".equals(product) && StringUtil.compareVer(version, "1.0.3") == 1)
     * || ("2".equals(product) && StringUtil.compareVer(version, "1.0.2") == 1))
     * { url = Constants.BASE_URL_SP + "ConfirmOrderInfo/"; } try { String data
     * = WebUtil.readContentFromGet(url, map); //如果订单中有配置中的商品 则不可以使用 优惠劵
     * 将返给客户端的优惠劵置为空 JSONObject obj = JSONObject.fromObject(data); JSONObject
     * content = obj.getJSONObject("content"); JSONArray array =
     * content.getJSONArray("list"); for(int i =0 ; i < array.size() ; i++){
     * JSONObject temp = array.getJSONObject(i); String goodId =
     * temp.getString("goodsid"); String brandName=temp.getString("brand");
     * if(ProductCouponUtil.checkIsExist(goodId)){ content.put("coupon", new
     * JSONArray()); break; }else
     * if(ProductCouponUtil.checkBrandIsExist(brandName)){ content.put("coupon",
     * new JSONArray()); break; } } response.getWriter().print(obj.toString());
     * //System.out.println(data); } catch (Exception e) { e.printStackTrace();
     * log.error("ConfirmOrderInfoServlet：" + e); try {
     * WebUtil.sendApiException(response); } catch (Exception e1) {
     * e1.printStackTrace(); } } } else { try {
     * WebUtil.sendErrorNoSessionid(response); } catch (Exception e) {
     * "confirmorderinfo", "userid", userid); } else { try {
     * WebUtil.sendErrorInvalidParams(response); } catch (Exception e1) {
     * e1.printStackTrace(); } } }
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String sessionid = request.getParameter("sessionid");
        String imei = request.getHeader("imei");
        String shoptype = request.getParameter("shoptype");
        String picquality = request.getParameter("picquality");
        String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
        if (StringUtil.isNotEmpty(userid, imei, sessionid)) {
            if (SessionUtil.validate(userid, imei, sessionid)) {

                if (!StringUtil.isNotEmpty(shoptype)) {
                    shoptype = "2";
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId", userid);
                map.put("width", "132");
                map.put("height", "176");
                map.put("shoptype", shoptype);
                String url = Constants.BASE_TRADE_URL + "trade/getordershoppingcartdetail2/";
                try {
                    String settData = WebUtil.readContentFromGet(url, map);
                    ProductServerAllCartVO productServerAllCartVO = new ProductServerAllCartVO().jsonObj(settData);
                    if (Constants.SUCCESS.equals(productServerAllCartVO.getCode())) {
                        Map<String, String> settMap = new HashMap<String, String>();
                        settMap.put("userid", userid);
                        settMap.put("shoptype", shoptype);
                        settMap.put("width", "132");
                        settMap.put("height", "176");
                        String settUrl = Constants.BASE_TRADE_URL + "order/SettlementOrder/";
                        String data = WebUtil.readContentFromGet(settUrl, settMap);
                        SettlementServerOrderVO settlementServerOrderVO = new SettlementServerOrderVO().jsonObj(data);
                        if (Constants.SUCCESS.equals(settlementServerOrderVO.getCode())) {
                            SettlementOrderByOldAPIResponse apiResponse = new SettlementOrderByOldAPIResponse();
                            String apiResult = apiResponse.objJson(settlementServerOrderVO);
                            JSONObject apiResultObj = JSONObject.fromObject(apiResult);
                            JSONObject contentObj = JSONObject.fromObject(apiResultObj.getString("content"));
                            JSONArray productsArray = contentObj.getJSONArray("list");
                            String data3 = "";
                            String detail = "";
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
                            JSONArray newListArray = new JSONArray();
                            for (int i = 0; i < productsArray.size(); i++) {
                                productObj = (JSONObject) productsArray.get(i);
                                if ("1".equals(shoptype)) {
                                    listOfGoodsVO.setProductId( productObj.getString("goodsid"));
                                    listOfGoodsVO.setUserId(userid == null ? "" : userid);
                                    listOfGoodsVO.setTopicId("");
                                    listOfGoodsVO.setUserId(userid);
                                    data3=shangPinService.findSPProductDetail(listOfGoodsVO);
                                    detail = new ProductSnapAPIResponse().objSpJson2(data3,productObj.getString("count"));
                                } else {
                                    data3=aoLaiService.findProductDetail(productObj.getString("goodsid"),"1",productObj.getString("categoryno"),listOfGoodsVO.getPicw(),listOfGoodsVO.getPich());                                            
                                    detail = new ProductSnapAPIResponse().objAlJson2(data3,productObj.getString("count"));
                                }
                                productObj.put("detail", detail);
                                newListArray.add(productObj);
                            }

                            // 如果订单中有配置中的商品 则不可以使用 优惠劵 将返给客户端的优惠劵置为空
                            contentObj.put("list", newListArray);
                            /*for (int i = 0; i < newListArray.size(); i++) {
                                JSONObject temp = newListArray.getJSONObject(i);
                                String goodId = temp.getString("goodsid");
                                String brand = temp.getString("brand");
                                if (ProductCouponUtil.checkIsExist(goodId)) {
                                    contentObj.put("coupon", new JSONArray());
                                    break;
                                } else if (ProductCouponUtil.checkIsExist(brand)) {
                                	contentObj.put("coupon", new JSONArray());
                                    break;
                                }
                                
                            }*/
                            apiResultObj.put("content", contentObj);
                            response.getWriter().print(apiResultObj.toString());
                        } else {
                            response.getWriter().print(data);
                        }
                    }else{
                        response.getWriter().print(settData);
                    }
                    // System.out.println(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("ConfirmOrderInfoServlet：" + e);
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
            FileUtil.addLog(request, "confirmorderinfo", channelNo,
            		"userid", userid,
            		"shoptype",shoptype);
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
