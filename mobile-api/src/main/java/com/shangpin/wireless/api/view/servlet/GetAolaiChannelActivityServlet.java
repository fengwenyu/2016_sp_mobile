package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.wireless.api.api2client.domain.AolaiChannelActivityAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取频道下已开启的活动接口
 * 
 * @CreatDate: 2013-06-24
 */
public class GetAolaiChannelActivityServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GetAolaiChannelActivityServlet.class);

	private AoLaiService aoLaiService;
	@Override
	public void init() throws ServletException {
		aoLaiService = (AoLaiService) getBean(AoLaiService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo =  request.getHeader("p");
		String pageindex = request.getParameter("pageindex");
		String pagesize = request.getParameter("pagesize");
		String pich = request.getParameter("pich");
		String picw = request.getParameter("picw");
		final String chid = request.getParameter("cateid");
		final String imei = request.getHeader("imei");
		String picquality = request.getParameter("picquality");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtils.isNotEmpty(productNo) && Constants.AOLAI_IPHONE_PRODUCTNO.equals(productNo)) {
			if ("high".equals(picquality)) {
				picw = "620";
				pich = "460";
			} else if ("low".equals(picquality)) {
				picw = "310";
				pich = "230";
			} else {
				picw = "620";
				pich = "460";
			}
		}
		if (StringUtils.isEmpty(pageindex) || "0".equals(pageindex)) {
			pageindex = "1";
			pagesize = String.valueOf(Integer.MAX_VALUE);
		}
		if (StringUtil.isNotEmpty(chid, pich, picw, imei, productNo)) {
			try {
				ListOfGoods goods=new ListOfGoods();
				goods.setChId(chid);
				goods.setPicw(picw);
				goods.setPich(pich);
				goods.setPageIndex(pageindex);
				goods.setPageSize(pagesize);
				String data = aoLaiService.findChannelActivity(goods);
				JSONObject obj = new JSONObject();
				AolaiChannelActivityAPIResponse aolaiChannelActivityAPIResponse = new AolaiChannelActivityAPIResponse();
				aolaiChannelActivityAPIResponse.setCode("0");
				obj=aolaiChannelActivityAPIResponse.obj2Json(data);
				response.getWriter().print(obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
				log.error("GetAolaiChannelActivityServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "aolaiChannelActivity", channelNo,
					"chid", chid, 
					"imei", imei);
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
