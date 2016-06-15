package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.utils.BrandUtil;
import com.shangpin.base.vo.LatestProductMore;
import com.shangpin.base.vo.ProductMore;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.DateUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 新品更多列表
 * 
 * @author huangxiaoliang
 * 
 */
public class LatestProductMoreList extends BaseServlet {
	private static final long serialVersionUID = 6345447459447582412L;

	protected final Log log = LogFactory.getLog(LatestProductMoreList.class);

	ShangPinService shangPinService;

	@Override
	public void init() throws ServletException {
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userId = request.getParameter("userId");
		final String brandId = request.getParameter("brandId");
		final String sortId = request.getParameter("sortId");
		final String pageIndex = request.getParameter("pageIndex");
		final String pageSize = request.getParameter("pageSize");
		final String returnTime = request.getParameter("returnTime");
		final String returnStatus = request.getParameter("returnStatus");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(brandId, sortId, pageIndex, pageSize, returnTime, returnStatus)) {
			try {
//				String productSort = shangPinService.findLatestProductSortList(brandId);
				StringBuffer strBuffer = new StringBuffer();
				LatestProductMore latestProductMore = shangPinService.findLatestProductMoreObj(userId, brandId, sortId, pageIndex, pageSize);
				if (!BrandUtil.isFlagship(latestProductMore.getBrandNameEN())) {
					latestProductMore.setName(latestProductMore.getBrandNameEN());
					latestProductMore.setType("3");
					latestProductMore.setRefContent(latestProductMore.getBrandId());
				} else {
					latestProductMore.setName(BrandUtil.getFlagShipName(latestProductMore.getBrandNameEN()));
					latestProductMore.setType("5");
					latestProductMore.setRefContent(BrandUtil.getFlagShipUrl(latestProductMore.getBrandNameEN()));
				}
				List<ProductMore> productMores = latestProductMore.getLatestProductMoreList();
				if (productMores != null && !productMores.isEmpty()) {
					SimpleDateFormat spformat = new SimpleDateFormat("yyyy-MM-dd");
					String time;
					ProductMore productMore1 = productMores.get(0);
					time = Long.toString(spformat.parse(productMore1.getTime()).getTime());
					if ("0".equals(returnTime) && "0".equals(returnStatus)) {
						productMore1.setTitle("今日上新  "+productMore1.getProductCount());
						productMore1.setStatus("today");
						productMore1.setTime(time);
						productMores.set(0,productMore1);
						if (1 < productMores.size()) {
							ProductMore productMore2 = productMores.get(1);
							productMore2.setTitle("昨日上新  "+productMore2.getProductCount());
							productMore2.setStatus("yesterday");
							time = Long.toString(spformat.parse(productMore2.getTime()).getTime());
							productMore2.setTime(time);
							productMores.set(1,productMore2);
							if (2 < productMores.size()) {
								productMores = generalGroup(2, productMores);
							}
						}
					} else if ("today".equals(returnStatus)) {
						if (returnTime.equals(time)) {
							productMore1.setTitle("今日上新  "+productMore1.getProductCount());
							productMore1.setStatus("today");
							productMore1.setTime(time);
							productMores.set(0,productMore1);
							if (1 < productMores.size()) {
								ProductMore productMore2 = productMores.get(1);
								productMore2.setTitle("昨日上新  "+productMore2.getProductCount());
								productMore2.setStatus("yesterday");
								time = Long.toString(spformat.parse(productMore2.getTime()).getTime());
								productMore2.setTime(time);
								productMores.set(1,productMore2);
								if (2 < productMores.size()) {
									productMores = generalGroup(2, productMores);
								}
							}
						} else if (Long.valueOf(returnTime) > Long.valueOf(time)) {
							productMore1.setTitle("昨日上新  "+productMore1.getProductCount());
							productMore1.setStatus("yesterday");
							productMore1.setTime(time);
							productMores.set(0,productMore1);
							if (1 < productMores.size()) {
								productMores = generalGroup(1, productMores);
							}
						}
					} else if ("yesterday".equals(returnStatus)) {
						if (returnTime.equals(time)) {
							productMore1.setTitle("昨日上新  "+productMore1.getProductCount());
							productMore1.setStatus("yesterday");
							productMore1.setTime(time);
							productMores.set(0,productMore1);
							if (1 < productMores.size()) {
								productMores = generalGroup(1, productMores);
							}
						} else if (Long.valueOf(returnTime) > Long.valueOf(time)) {
							productMores = generalGroup(0, productMores);
						}
					} else if ("no".equals(returnStatus)) {
						productMores = generalGroup(0, productMores);
					}
					latestProductMore.setLatestProductMoreList(productMores);
					
					String jsonStr1 = JsonUtil.toJson(latestProductMore, null, false, null, null, false, false);

					strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":" + jsonStr1);
//					strBuffer.append(",\"latestProductSortList\":[{" + productSort + "}}");
					strBuffer.append("}");
					
				} else {
					strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{}}");
				}
				response.getWriter().print(strBuffer);
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("LatestProductList：" + e1);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			FileUtil.addLog(request, "LatestProductList", channelNo,
					"userId", userId, 
					"pageIndex", pageIndex, 
					"pageSize", pageSize, 
					"returnTime", returnTime, 
					"returnStatus", returnStatus);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 返回通用的时间分组信息
	 * @param productMores
	 * @param params
	 * @throws ParseException 
	 */
	public List<ProductMore> generalGroup(int index, List<ProductMore> productMores) throws ParseException {
		ProductMore productMore;
		SimpleDateFormat spformat = new SimpleDateFormat("yyyy-MM-dd");
		String time;
		for (int i = index; i < productMores.size(); i++) {
			productMore = productMores.get(i);
			productMore.setTitle(DateUtil.getStringDateMonth(productMore.getTime(), "0", "1", "1", "1")+"上新  "+productMore.getProductCount());
			productMore.setStatus("no");
			time = Long.toString(spformat.parse(productMore.getTime()).getTime());
			productMore.setTime(time);
			productMores.set(i,productMore);
		}
		return productMores;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
