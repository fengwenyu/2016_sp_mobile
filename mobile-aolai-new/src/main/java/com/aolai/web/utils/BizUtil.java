package com.aolai.web.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.bo.Delivery;
import com.shangpin.biz.bo.Pay;

/**
 * 业务相关的处理方法类
 * 
 * @author cuibinqiang
 *
 */
public class BizUtil {
	public static Logger logger = LoggerFactory.getLogger(BizUtil.class);

	/**
	 * 从App中取出UserId
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	public static String getUserAppId(HttpServletRequest request) {
		String userId = request.getHeader(Constants.APP_COOKIE_NAME_UID);
		userId = (userId == null || "".equals(userId)) ? request.getHeader("userId") : userId;
		userId = (userId == null || "".equals(userId)) ? request.getParameter("userId") : userId;
		return userId;
	}

	/**
	 * 日期格式化：将字符串日期转换成银联需要的格式返回
	 * 
	 * @param date
	 *            字符串日期
	 * @param pattern
	 *            指定转化后日期格式
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public static String formatDate(String date, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 实际格式
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);// 需要的格式
			Date dateNew = format.parse(date);
			String worked = sdf.format(new Date(dateNew.getTime()));
			return worked;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 银联订单超时时间：订单交易时间加一个小时
	 * 
	 * @param date
	 *            订单交易时间
	 * @param pattern
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public static String expireTime(String date, String pattern) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 实际格式
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);// 需要的格式
			Date dateNew = format.parse(date);
			long endtime = dateNew.getTime() + 60 * 1000 * 60;
			String worked = sdf.format(new Date(endtime));
			return worked;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 银联金额格式化：去掉小数点，将"元"转为"分"
	 * 
	 * @param money
	 *            需要转化的金额
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public static String formatMoney(String money) {
		BigDecimal money1 = new BigDecimal(money);
		BigDecimal money2 = new BigDecimal(100);
		String money3 = money1.multiply(money2).toString().split("[.]")[0];

		return money3;
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public static boolean isNotEmpty(String... str) {
		int length = str.length;
		for (int i = 0; i < length; i++) {
			if (str[i] != null && !"".equals(str[i].trim())) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取活动主题名称
	 * 
	 * @param pageType
	 * @return
	 */
	public static String getActivityName(String pageType) {
		String activityName = "";
		if ("0".equals(pageType)) {
			activityName = "预售商品";
		} else if ("1".equals(pageType)) {
			activityName = "最新特卖";
		} else if ("2".equals(pageType)) {
			activityName = "限时特卖";
		} else if ("3".equals(pageType)) {
			activityName = "活动专区";
		} else {
			activityName = "预售商品";
		}

		return activityName;
	}

	/**
	 * 获取配送方式
	 * 
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public static List<Delivery> getDelivery() {
		List<Delivery> list = new ArrayList<Delivery>();
		Delivery d1 = new Delivery();
		d1.setId("1");
		d1.setName("工作日收货");
		d1.setEnable("1");

		Delivery d2 = new Delivery();
		d2.setId("2");
		d2.setName("工作日、节假日均可");
		d2.setEnable("1");

		Delivery d3 = new Delivery();
		d3.setId("3");
		d3.setName("双休日、节假日收货");
		d3.setEnable("1");

		list.add(d1);
		list.add(d2);
		list.add(d3);

		return list;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return
	 *
	 * @author cuibinqiang
	 */
	public static List<Pay> getPay() {
		List<Pay> list = new ArrayList<Pay>();
		Pay p1 = new Pay();
		p1.setId("20");
		p1.setName("支付宝");
		p1.setEnable("1");// 可用

		Pay p2 = new Pay();
		p2.setId("19");
		p2.setName("银联支付");
		p2.setEnable("1");// 可用

		Pay p3 = new Pay();
		p3.setId("2");
		p3.setName("货到付款");
		p3.setEnable("1");// 可用

		list.add(p1);
		list.add(p2);
		list.add(p3);
		return list;
	}
}
