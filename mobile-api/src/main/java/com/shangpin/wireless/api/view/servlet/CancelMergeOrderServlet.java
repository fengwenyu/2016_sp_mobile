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
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 取消合并的订单
 * @author xupengcheng
 *
 */
public class CancelMergeOrderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(DelCollectionServlet.class);
	String url = Constants.BASE_TRADE_URL + "order/CancelOrder";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userId");
		String orderId = req.getParameter("orderId");
		String postArea=req.getParameter("postArea");
		String channelNo = ChannelNoUtil.getChannelNo(req);//获取渠道号
		PrintWriter writer = resp.getWriter();
		if(!StringUtil.isNotEmpty(userid, orderId)){
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			String data = ""; 
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", userid);
			map.put("mainOrderNo", orderId);
			map.put("postArea", postArea);
			try{
				data = WebUtil.readContentFromGet(url, map );
				writer.print(data);
			    // 记录访问日志
				FileUtil.addLog(req, "cancelMergeOrder", channelNo,
						"userid", userid, 
						"orderid", orderId);
	        
			}catch(Exception e){
				e.printStackTrace();
				log.error("CancelMergeOrderServlet：" + e);
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
