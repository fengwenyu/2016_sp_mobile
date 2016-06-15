package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * 购物车列表接口
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-23
 */
public class ProductcartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ProductcartServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String width = request.getParameter("picw");
		String height = request.getParameter("pich");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(userid, sessionid, imei)) {
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userid);
				map.put("width", width);
				map.put("height", height);
				String url = Constants.BASE_TRADE_URL + "cartlist/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					ProductServerAllCartVO productServerAllCartVO=new ProductServerAllCartVO().jsonObj(data);
					ProductListAPIResponse apiResponse=new ProductListAPIResponse();
					String rusult=apiResponse.objJson(productServerAllCartVO);					
					response.getWriter().print(rusult);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("ProductcartServlet：" + e);
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
					"userid", userid);
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
