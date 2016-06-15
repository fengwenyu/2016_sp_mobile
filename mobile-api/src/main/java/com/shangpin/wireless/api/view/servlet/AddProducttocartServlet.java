package com.shangpin.wireless.api.view.servlet;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 将商品加入购物车（尚品奥莱合并）
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-22
 */
public class AddProducttocartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AddProducttocartServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		final String product = request.getHeader("p");
		final String sessionid = request.getParameter("sessionid");
		final String ver = request.getHeader("ver");
		final String imei = request.getHeader("imei");
		final String productNo = request.getParameter("productno");
		final String userid = request.getParameter("userid");
		final String sku = request.getParameter("sku");
		final String quantity = request.getParameter("count");
		final String categoryNo = request.getParameter("categoryno");//
		final String shoptype = request.getParameter("shoptype");//
		if (StringUtil.isNotEmpty(product, userid, sku, quantity)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("productNo", productNo);
				map.put("quantity", quantity);
				map.put("skuNo", sku);
				map.put("dynamicattributetext", "");
				if(categoryNo==null||"".equals(categoryNo)){
					map.put("categoryNo", "0");
					map.put("topicSubjectFlag", "1");
				}else{	
					if("1".equals(shoptype)){
						map.put("categoryNo", categoryNo);
						map.put("topicSubjectFlag", "2");
					}else{
						map.put("categoryNo", categoryNo);
						map.put("topicSubjectFlag", "1");
					}
				}
				map.put("skuFrom", "3");
				map.put("vipNo", "0");
				map.put("userId", userid);
				map.put("siteNo", shoptype);

				//2.9.12之后放开海外购多件购买
				if (StringUtil.compareVer(ver, "2.9.12") >= 0) {
					map.put("limitOverseaProductQuantity","0");
				}

				String url = Constants.BASE_TRADE_URL + "trade/addproducttocart/";		
				try {
					String data = WebUtil.readContentFromPost(url, map);
					log.info("添加购物车:入参:"+map+",返回数据"+data);
					JSONObject result=new JSONObject();
					if(data!=null&&!"".equals(data)){
						JSONObject obj = JSONObject.fromObject(data);
						JSONObject content=new JSONObject();
						result.put("code", obj.getString("code"));
						result.put("msg", obj.getString("msg"));
						result.put("content", content);

					}else{
						result.put("code", "1");
						JSONObject content=new JSONObject();
						result.put("msg", "商品加入购物车失败了，再来一次吧");
						result.put("content", content);
					}
					response.getWriter().print(result.toString());
				} catch (Exception e) {
					e.printStackTrace();
					log.error("AddProducttocartcartServlet：" + e);
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
			FileUtil.addLog(request, "addnewcart", channelNo,
					"userid", userid, 
					"sku", sku, 
					"quantity", quantity, 
					"sessionid", sessionid, 
					"categoryno", categoryNo, 
					"shoptype", shoptype,
					"code", "success");
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
