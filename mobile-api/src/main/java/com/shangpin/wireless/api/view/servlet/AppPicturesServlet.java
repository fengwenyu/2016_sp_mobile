package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.AppPicturesAPIResponse;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 启动图  导航图
 * @author Administrator
 *
 */
public class AppPicturesServlet extends HttpServlet{
	protected final Log log = LogFactory.getLog(AppPicturesServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		try{
			//2尚品iPhone客户端,1奥莱iPhone客户端,102尚品安卓客户端,101奥莱安卓客户端
			final String product = req.getHeader("p");
			String os = req.getHeader("os");//andriod & ios
			String wh = req.getHeader("wh") == null ? "" : req.getHeader("wh");
			AppPicturesAPIResponse appPicturesAPIResponse = new AppPicturesAPIResponse();
			appPicturesAPIResponse.setOs(os);
			appPicturesAPIResponse.setWh(wh);
			String producType = null;
			if(product.equals("2")||product.equals("102")){
				producType="shangpin";
			}
			if(product.equals("1")||product.equals("101")){
				producType="aolai";
			}
			String result = appPicturesAPIResponse.obj2Json(producType);
			resp.getWriter().print(result);
		}catch(Exception e){
			e.printStackTrace();
			log.error("AppPicturesServlet：" + e);
			try {
				WebUtil.sendApiException(resp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
