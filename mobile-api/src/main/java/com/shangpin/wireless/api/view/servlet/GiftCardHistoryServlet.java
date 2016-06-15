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

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
/**
 * 礼品卡历史记录
 * @author liling
 */
public class GiftCardHistoryServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = -1732243250380628293L;
	protected final Log log = LogFactory.getLog(GiftCardRechargeServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");;
		final String userId = request.getParameter("userId");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(imei, userId)) {
			String url = Constants.SP_BASE_URL + "giftHistory/";
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", userId);
			
				String data = WebUtil.readContentFromGet(url, map);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("GiftCardHistoryServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "giftHistory", channelNo,
					"imei", imei, 
					"productNo", productNo, 
					"userId", userId);
			
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
