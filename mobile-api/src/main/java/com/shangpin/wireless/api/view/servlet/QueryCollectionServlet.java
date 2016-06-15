package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.api2client.domain.CollectionResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 查询收藏活动
 * 
 * @author lenovo
 * 
 */
public class QueryCollectionServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(QueryCollectionServlet.class);
	String url = Constants.BASE_URL + "queryCollection";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		String type = req.getParameter("type");
		String pageIndex = req.getParameter("pageIndex");
		String pageSize = req.getParameter("pageSize");
		PrintWriter writer = resp.getWriter();
		if (!StringUtil.isNotEmpty(userId, type)) {
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			if (type.equals("2")) {// 客户端调用参数错了
				type = "0";
			}
			map.put("type", type);
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			map.put("pich", "111");
			map.put("picw", "222");
			String data = "";
			try {
				data = WebUtil.readContentFromGet(url, map);
				CollectionResponse collectionResponse = new CollectionResponse();
				writer.print(collectionResponse.obj2Json(data).replace("-111", "-{h}").replace("-222-", "-{w}-"));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("QueryCollectionServlet：" + e);
				try {
					WebUtil.sendApiException(resp);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
