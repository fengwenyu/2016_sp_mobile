package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.CommonService;
import com.shangpin.wireless.api.api2client.domain.CollectedBrandAPIResponse;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
/**
 * 收藏品牌列表
 * 
 * @author liling
 * @date 2014-8-27
 */
public class CollectedBrandlistServlet extends BaseServlet {

	
	private static final long serialVersionUID = -3152779985296447826L;
	protected final Log log = LogFactory.getLog(CollectedBrandlistServlet.class);
 
	CommonService commonService;
	
	@Override
	public void init() throws ServletException {
		commonService = (CommonService) getBean(CommonService.class);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			final String productNo = request.getHeader("p");
			final String imei = request.getHeader("imei");
			final String userId = request.getParameter("userId");
			final String pageIndex = request.getParameter("pageIndex");
			final String pageSize = request.getParameter("pageSize");
			final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
			if (StringUtil.isNotEmpty(imei, productNo, userId, pageIndex,pageSize)) {
				String data=commonService.collectedBrandlistObj(userId, pageIndex, pageSize);
				CollectedBrandAPIResponse collectedBrandAPIResponse = new CollectedBrandAPIResponse();
				collectedBrandAPIResponse.setCode("0");
				JSONObject collectedBrandJson = collectedBrandAPIResponse.obj2Json(data);
				response.getWriter().print(collectedBrandJson.toString());
			} else {
	            try {
	                WebUtil.sendErrorInvalidParams(response);
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }
	        }
			FileUtil.addLog(request, "collectedBrand", channelNo,
					"userId", userId,
					"imei", imei,
					"productNo", productNo,
					"pageInde", pageIndex,
					"pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("CollectedBrandServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
