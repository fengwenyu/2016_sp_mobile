package com.shangpin.web.controller;


import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.web.utils.ActivifyUtil;
import com.shangpin.web.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 口令红包
 * @author ZRS
 * @date 2016年5月17日13:53:21
 */
@Controller
@RequestMapping("/command")
public class CommandController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CommandController.class);

	private static Map<Integer, String> COMMAND_MAP  = new HashMap<>();

	static{
//		1、99元女装现金券2433070747
//		2、66元男装现金券2443464002
//		3、66元鞋靴现金券2458135761
//		4、100元快时尚现金券2400710148
//		5、箱包8大品牌150现金券2415086676

		COMMAND_MAP.put(1, "2433070747");
		COMMAND_MAP.put(2, "2443464002");
		COMMAND_MAP.put(3, "2458135761");
		COMMAND_MAP.put(4, "2400710148");
		COMMAND_MAP.put(5, "2415086676");
		COMMAND_MAP.put(6, "2511855732");

	}

	@Autowired
	private SPBizCouponService bizCouponService;

	
	@RequestMapping(value = "/index/{flag:[6]}", method = RequestMethod.GET)
	public String index() {

		if(ActivifyUtil.isRunActivify()){
			return "/command/index";
		}

		return "/command/end";
		
	}

	/**
	 * 领取口令红包
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/get/{flag:[6]}", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> receiveCoupons( HttpServletRequest request, @PathVariable("flag") Integer flag) {

		if (!ActivifyUtil.isRunActivify()) {
			Map<String, Object> map = new HashMap<>();
			map.put("code", "1");
			map.put("msg", "活动已结束");//临近结束新加
		}


		String command = COMMAND_MAP.get(flag);

		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);

		// 4用户优惠劵接口(addPacket)
		Map<String, Object> map = bizCouponService.sendCoupon(user.getUserid(), "1", "coupon:" + command, "30");

		logger.info("520口令红包页面.用户:" + user.getUserid() + ",领取[" + command + "],结果:" + map);
		return map;

	}



}
