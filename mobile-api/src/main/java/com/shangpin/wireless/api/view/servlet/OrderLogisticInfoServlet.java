package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 订单物流跟踪
 * @author xupengcheng
 *
 */
public class OrderLogisticInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(this.getClass());

	private ShangPinService shangPinService;
	@Override
	public void init() throws ServletException{
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String orderId = req.getParameter("orderId");
		if(StringUtil.isNotEmpty(userId, orderId)){
			try{
				String data=shangPinService.findOrderMoreLogistic(userId, orderId,"1","0");
				resp.getWriter().print(data);
			}catch(Exception e){
				log.error(this.getClass() + ":" + e);
				try {
					WebUtil.sendApiException(resp);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}else{
			try {
				WebUtil.sendErrorInvalidParams(resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
