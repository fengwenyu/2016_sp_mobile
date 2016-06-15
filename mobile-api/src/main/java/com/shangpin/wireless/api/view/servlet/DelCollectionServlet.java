package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 删除收藏活动
 * @author lenovo
 *
 */
public class DelCollectionServlet extends HttpServlet {

	protected final Log log = LogFactory.getLog(DelCollectionServlet.class);
	String url = Constants.BASE_URL + "delCollection";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userId");
		String activityId = req.getParameter("activityId");
		PrintWriter writer = resp.getWriter();
		if(!StringUtil.isNotEmpty(userid, activityId)){
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			String data = ""; 
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("activityId", activityId);
			try{
				data = WebUtil.readContentFromGet(url, map );
				writer.print(data);
			}catch(Exception e){
				e.printStackTrace();
				log.error("DelCollectionServlet：" + e);
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
