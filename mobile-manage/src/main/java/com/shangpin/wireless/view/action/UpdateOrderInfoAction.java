package com.shangpin.wireless.view.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.wireless.api.api2server.domain.OrderInfoServerResponse;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.manage.service.OrderStatisticsService;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.WebUtil;

/**
 * 更新订单统计信息解析Action
 * 
 * @Author wangwenguan
 * @CreatDate 2013-11-12
 */
@Controller
@Scope("prototype")
public class UpdateOrderInfoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = OrderStatisticsService.SERVICE_NAME)
	protected OrderStatisticsService orderStatisticsService;

	public String update() throws Exception {
		String date = ServletActionContext.getRequest().getParameter("date");
		String count = ServletActionContext.getRequest().getParameter("count");
		HqlHelper hqlHelper = new HqlHelper(OrderStatistics.class, "a");
		hqlHelper.addWhereCondition("a.reserve5 IS NULL");
		if (date != null && date.length() > 0) {
//			hqlHelper.addWhereCondition("a.orderTime IS NULL OR a.orderTime BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59'");
			hqlHelper.addWhereCondition("a.orderTime BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59'");
		}
		if (count != null && count.length() > 0) {
			hqlHelper.addLimit(0, Integer.parseInt(count));
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		hqlHelper.addOrderByProperty("id", true);System.out.println(hqlHelper.getQueryListHql());
		List<OrderStatistics> orderStatisticsList = orderStatisticsService.getListByCondition(hqlHelper);
		for (OrderStatistics order : orderStatisticsList) {
			if (order.getOrderId() == null) {
				System.out.println("OrderId is NULL, Item id:" + order.getId());
				continue;
			}
			System.out.println("id:" + order.getId() + "---orderTime:" + order.getOrderTime());
//			if (!"1".equals(order.getIsok())
//					|| order.getOrderTime() == null
//					|| order.getUserId() == null
//					|| (!"1".equals(order.getOrderType()) && !"2".equals(order.getOrderType()))
//					|| order.getOrderOrigin() == null
//					|| order.getPayTime() == null
//					|| !"1".equals(order.getIspaid())) {
				final String url = Constants.BASE_URL_SP_SP + "getorderstatistics";
				final HashMap<String, String> map = new HashMap<String, String>();
				map.put("orderid", order.getOrderId());
				final String data = WebUtil.readContentFromGet(url, map);
				OrderInfoServerResponse resp = new OrderInfoServerResponse();
				resp.json2Obj(data);
				if (!Constants.CODE_SUCCESS.equals(resp.getCode())) {
					System.out.println(data);
					continue;
				}
				if (!"1".equals(order.getIsok()))
					order.setIsok("1");
				if (order.getOrderTime() == null)
					order.setOrderTime(resp.getOrderTime());
				if (order.getUserId() == null)
					order.setUserId(resp.getUserId());
				if (!"1".equals(order.getOrderType()) && !"2".equals(order.getOrderType()))
					order.setOrderType(resp.getOrderType());
				if (order.getOrderOrigin() == null)
					order.setOrderOrigin(resp.getOrderOrigin());
				order.setMainPayMode(resp.getPayTypeId());
				order.setSubPayMode(resp.getPayTypeChildId());
				if (order.getPayTime() == null) {
					order.setPayTime(resp.getPayTime());
				}
				order.setIspaid(resp.getPayTime()==null?"0":"1");
				order.setOnlineAmount(Float.parseFloat(resp.getOnlineAmount()));
				order.setCouponAmount(Float.parseFloat(resp.getCouponAmount()));
				order.setGiftcardAmount(Float.parseFloat(resp.getGiftcardAmount()));
				order.setReserve5("updated");
				orderStatisticsService.update(order);
//			} else {
//				order.setReserve5("not updated");
//				orderStatisticsService.update(order);
//			}
			System.out.println("UpdateOrderInfoAction::update-success: " + sdf2.format(new Date()));
		}
		System.out.println("UpdateOrderInfoAction::update finished!!" + sdf2.format(new Date()));
		ServletActionContext.getResponse().getWriter().print("UpdateOrderInfoAction::update-success");
		return null;
	}
}
