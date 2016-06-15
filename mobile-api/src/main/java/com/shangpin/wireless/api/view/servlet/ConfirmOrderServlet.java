package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.ConfirmProductAPIResponse;
import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.ProductCouponUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 确认订单信息
 * 
 * @Author:wangwenguan
 * @CreatDate: 2012-08-29
 */
public class ConfirmOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(EditAddressServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String version = request.getHeader("ver");
		String userid = request.getParameter("userid");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String picquality = request.getParameter("picquality");
		String shoptype = request.getParameter("shoptype");
		String picw = request.getParameter("picw");
		String pich = request.getParameter("pich");
		String shopdetailids=request.getParameter("shopdetailids");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		
		if (StringUtil.isNotEmpty(userid, imei, sessionid)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("shoptype", "");
				if(picw==null||pich==null){
					if ("high".equals(picquality)) {
						map.put("picw", "132");
						map.put("pich", "176");
						map.put("detailpicw", "132");
						map.put("detailpich", "176");
					} else if ("low".equals(picquality)) {
						map.put("picw", "132");
						map.put("pich", "176");
						map.put("detailpicw", "132");
						map.put("detailpich", "176");
					} else {
						map.put("picw", "132");
						map.put("pich", "176");
						map.put("detailpicw", "132");
						map.put("detailpich", "176");
					}
				}else{
					map.put("picw", picw);
					map.put("pich", pich);
					map.put("detailpicw", picw);
					map.put("detailpich", pich);
				}
					String url = Constants.BASE_URL + "ConfirmOrderInfo/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					Map<String, String> cartmap = new HashMap<String, String>();
					cartmap.put("userId", userid);
					cartmap.put("shopCartDetailIds", shopdetailids);
					if(picw==null||pich==null){
						if ("high".equals(picquality)) {
							cartmap.put("width", "132");
							cartmap.put("height", "176");
						} else if ("low".equals(picquality)) {
							cartmap.put("width", "132");
							cartmap.put("height", "176");
						} else {
							cartmap.put("width", "132");
							cartmap.put("height", "176");
						}
					}else{
						cartmap.put("width", picw);
						cartmap.put("height", pich);
					}
					String carturl = Constants.BASE_TRADE_URL + "getordershoppingcartdetail/";
					String cartdata=WebUtil.readContentFromGet(carturl, cartmap);
					//如果订单中有配置中的商品 则不可以使用 优惠劵 将返给客户端的优惠劵置为空
					JSONObject obj = JSONObject.fromObject(data);					
					ProductServerAllCartVO productServerAllCartVO=new ProductServerAllCartVO().jsonObj(cartdata);
					ConfirmProductAPIResponse apiResponse=new ConfirmProductAPIResponse();
					String list=apiResponse.objListJson(productServerAllCartVO);			
					JSONObject content = obj.getJSONObject("content");	
					content.put("list", list);
					/*JSONArray array = content.getJSONArray("list");					
					for(int i =0 ; i < array.size() ; i++){
						JSONObject temp = array.getJSONObject(i);
						String goodId = temp.getString("goodsid");
						String brandName=temp.getString("brand");
                        if(ProductCouponUtil.checkIsExist(goodId)){
                            content.put("coupon", new JSONArray());
                            break;
                        }else if(ProductCouponUtil.checkBrandIsExist(brandName)){
                            content.put("coupon", new JSONArray());
                            break;
                        }
					}*/
					Double payAmount=Double.parseDouble(productServerAllCartVO.getProductAllCartVO().getPracticalPrice());
					if(!"0".equals(content.getString("carriage"))||!"".equals(content.getString("carriage"))){
						payAmount+=Double.parseDouble(content.getString("carriage"));
					}
					content.put("amount", productServerAllCartVO.getProductAllCartVO().getTotalPrice());
					content.put("payamount", String.valueOf(payAmount));
					content.put("discountamount", productServerAllCartVO.getProductAllCartVO().getTotalPromotionPrice());					
					response.getWriter().print(obj.toString());
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
			FileUtil.addLog(request, "confirmorder", channelNo,
					"userid", userid,
					"shopdetailids",shopdetailids,
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
