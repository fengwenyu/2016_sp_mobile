package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.ProductListAPIResponse;
import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 删除购物车中的商品
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-23
 */
public class DelProductcartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(DelProductcartServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userId = request.getParameter("userid");
		String shopCartDetailIds = request.getParameter("shopcartdetailids");
		String sessionid = request.getParameter("sessionid");
		String flag=request.getParameter("flag");
		String width=request.getParameter("picw");
		String height=request.getParameter("pich");
		String imei = request.getHeader("imei");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userId, userId, imei, sessionid)) {
			if (SessionUtil.validate(userId, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userId);
				map.put("shopCartDetailIds", shopCartDetailIds);
				String url = Constants.BASE_TRADE_URL + "deletecartbydetailId/";
				try {
					String data = WebUtil.readContentFromPost(url, map);					
					if(data!=null&&!"".equals(data)){
						JSONObject result=new JSONObject();
						JSONObject content=new JSONObject();
						String apiResult="";
						JSONObject obj = JSONObject.fromObject(data);
						String delCode="true".equals(obj.getString("Success"))?"0":"1";
						if("0".equals(delCode)){
							if("0".equals(flag)){
								Map<String, String> listmap = new HashMap<String, String>();
								listmap.put("userId", userId);
								listmap.put("width", width);
								listmap.put("height", height);
								String listurl = Constants.BASE_TRADE_URL + "cartlist/";
									String data2 = WebUtil.readContentFromGet(listurl, listmap);
									ProductServerAllCartVO productServerAllCartVO=new ProductServerAllCartVO().jsonObj(data2);
									ProductListAPIResponse apiResponse=new ProductListAPIResponse();
									apiResult=apiResponse.objJson(productServerAllCartVO);		
							}else{
								result.put("code",delCode);
								result.put("msg", "");
								result.put("content", content);
								apiResult=result.toString();
							}
						}else{
							result.put("code",delCode);
							result.put("msg", obj.getString("ErrorMsg"));
							result.put("content", content);
							apiResult=result.toString();
						}
						response.getWriter().print(apiResult);
					}					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("DelProductcartServlet：" + e);
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
			FileUtil.addLog(request, "delcart", channelNo,
					"userid", userId, 
					"ids", shopCartDetailIds);
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
