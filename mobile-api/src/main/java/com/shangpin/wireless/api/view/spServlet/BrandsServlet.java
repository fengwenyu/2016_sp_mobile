package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.wireless.api.api2client.domain.BrandAPIResponse;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 获取所有品牌
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class BrandsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(BrandsServlet.class);
	private ShangPinService shangPinService;

	@Override
	public void init() throws ServletException {
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		try {
			String data = shangPinService.findCapitalBrandList(gender);
			BrandAPIResponse brandAPIResponse = new BrandAPIResponse();
			brandAPIResponse.setData(data);
			response.getWriter().print(brandAPIResponse.obj2Json());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BrandsServlet：" + e);
			try {
				WebUtil.sendApiException(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// 记录访问日志
		FileUtil.addLog(request, "shangpinbrands", channelNo,
				"gender", gender);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
}
