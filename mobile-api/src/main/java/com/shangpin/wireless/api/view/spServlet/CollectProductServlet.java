package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.CollectionProductAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 删除收藏商品
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-12-28
 */
public class CollectProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(CollectProductServlet.class);


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String userid = request.getParameter("userid");
		final String skuid = request.getParameter("sku");
		final String picw = request.getParameter("picw");
		final String pich = request.getParameter("pich");
		final String detailpicw = request.getParameter("detailpicw");
		final String detailpich = request.getParameter("detailpich");
		/*String productNo = request.getHeader("p");// 产品号
        final String ver = request.getHeader("ver");*/
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String url = Constants.SP_BASE_URL + "AddProductToCollect/";
		if (StringUtil.isNotEmpty(userid,skuid)) {
				try {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("userid", userid);
					map.put("sku", skuid);
					map.put("picw", picw);
					map.put("pich", pich);
					map.put("detailpicw", detailpicw);
					map.put("detailpich", detailpich);
					map.put("shoptyp", "1");
					String data = WebUtil.readContentFromGet(url, map);
					response.getWriter().print(data);
					/*if("2".equals(productNo)||"102".equals(productNo)){
					    if((StringUtil.compareVer(ver, "2.5.6")> 0)){
					        response.getWriter().print(data);
					    }
					}else{
					    CollectionProductAPIResponse apiResponse = new CollectionProductAPIResponse();
	                    apiResponse.setData(data);
	                    apiResponse.setSkuId(skuid);
	                    response.getWriter().print(apiResponse.obj2Json());
					}*/
					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("CollectProductServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			// 记录访问日志
			FileUtil.addLog(request, "addproducttocollect", channelNo,
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
