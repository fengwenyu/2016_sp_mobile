package com.shangpin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shangpin.biz.bo.Logistics;
import com.shangpin.biz.bo.OrderLogistics;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizLogisticeService;
import com.shangpin.biz.service.SPBizOrderService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.web.utils.Constants;

/**
 * @ClassName: LogisticeController
 * @Description: 物流跟踪
 * @author liling
 * @date 2014年11月29日
 * @version 1.0
 */
@Controller
@RequestMapping("/logistice")
public class LogisticeController extends BaseController {
	public static Logger logger = LoggerFactory.getLogger(LogisticeController.class);
	private static final String LIST = "logistice/list";
	@Autowired
	private SPBizLogisticeService logisticeService;
	@Autowired
	private SPBizOrderService orderBizService;

	/**
	 * 
	 * @Title: list
	 * @Description:物流跟踪列表页
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月29日
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String orderLogisticeInfo(@RequestParam("orderId") String orderId,
			@RequestParam(value = "postArea", defaultValue = "1", required = false) String postArea,
			HttpServletRequest request, Model model) {
		// 兼容客户端请求
		String userId = request.getParameter("userId");
		User user = getSessionUser(request);
		if (StringUtils.isEmpty(userId)||userId.equals("null")) {
			userId = user.userid;
		}
		model.addAttribute("orderId", orderId);
		try {
			String ua = request.getHeader("User-Agent").toLowerCase();
			String isNew="1";
			logger.debug("ua={}",ua);
			if (ClientUtil.CheckApp(ua)){
				if (!ClientUtil.isNotlessThanVer(ua.toLowerCase(),Constants.LOGISTIC_APP_VER)) {
					isNew="0";
				}
			}
			OrderLogistics orderLogistics = logisticeService.getOrderLogisticsInfo(orderId, userId, postArea,isNew);

			String ticketNo = request.getParameter("ticketNo"); // 页面显示的物流信息
			String index = request.getParameter("index"); // 页面显示的物流信息
			List<Logistics> list = orderLogistics.getList();
			if (list != null && list.size() > 0) {
				if (StringUtil.isNotEmpty(ticketNo)) {
					for (int i = 0; i < list.size(); i++) {
						Logistics obj = list.get(i);
						String tickNoTemp = obj.getTicketno();
						if (tickNoTemp.equals(ticketNo)) {
							orderLogistics.setLogistics(obj);
							orderLogistics.setIndex(i);
							break;
						}
					}
				} else {
					if(StringUtil.isNotEmpty(index)){
						for (int i = 0; i < list.size(); i++) {
							Logistics obj = list.get(i);
							if (Integer.valueOf(index)==i) {
								orderLogistics.setLogistics(obj);
								orderLogistics.setIndex(i);
								break;
							}
						}
					}else{
						orderLogistics.setLogistics(list.get(0));
						orderLogistics.setIndex(0);
					}
				
				}
			} 
			model.addAttribute("orderLogistics", orderLogistics);
			model.addAttribute("postArea", postArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}

}
