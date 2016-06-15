package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Sale;
import com.shangpin.biz.service.ASPBizSaleService;
import com.shangpin.web.utils.Constants;

@Controller
@RequestMapping("/sale")
public class SaleController extends BaseController {
	public static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	private static final String INDEX = "sale/index";
	private static final String TODAY = "sale/today";
	private static final String YESTERDAY = "sale/yesterday";
	private static final String LAST = "sale/last";
	@Autowired
	private ASPBizSaleService aspBizSaleService;

	/**
	 * 
	 * @Title: index
	 * @Description:加载首页时执行,直接跳转到首页面
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		return INDEX;
	}

	@RequestMapping(value = "/today", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> today(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<Sale> hotSales = aspBizSaleService.getHotSale(Constants.SLAE_TODAY_TYPE, Constants.SLAE_PAGEINDEX, Constants.SLAE_PAGESIZE);
			map.put("hotSales", hotSales);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/yesterday", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> yesterday(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Sale> hotSales = aspBizSaleService.getHotSale(Constants.SLAE_YESTODAY_TYPE, Constants.SLAE_PAGEINDEX, Constants.SLAE_PAGESIZE);
			map.put("hotSales", hotSales);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/last", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> last(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Sale> hotSales = aspBizSaleService.getHotSale(Constants.SLAE_LAST_TYPE, Constants.SLAE_PAGEINDEX, Constants.SLAE_PAGESIZE);
			map.put("hotSales", hotSales);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * @Title: hotToday
	 * @Description:今日新开
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/hot/today", method = { RequestMethod.GET, RequestMethod.POST })
	public String hotToday(Model model) {
		try {

			List<Sale> hotSales = aspBizSaleService.getHotSale(Constants.SLAE_TODAY_TYPE, Constants.SLAE_PAGEINDEX, Constants.SLAE_PAGESIZE);
			model.addAttribute("hotSales", hotSales);
			long now = System.currentTimeMillis();
			model.addAttribute("nowTime", String.valueOf(now));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TODAY;
	}

	/**
	 * 
	 * @Title: hotYesterday
	 * @Description:昨日上新
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/hot/yesterday", method = { RequestMethod.GET, RequestMethod.POST })
	public String hotYesterday(Model model) {
		try {
			List<Sale> hotSales = aspBizSaleService.getHotSale(Constants.SLAE_YESTODAY_TYPE, Constants.SLAE_PAGEINDEX, Constants.SLAE_PAGESIZE);
			model.addAttribute("hotSales", hotSales);
			long now = System.currentTimeMillis();
			model.addAttribute("nowTime", String.valueOf(now));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return YESTERDAY;
	}

	/**
	 * 
	 * @Title: hotLast
	 * @Description:最后疯抢
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/hot/last", method = { RequestMethod.GET, RequestMethod.POST })
	public String hotLast(Model model) {
		try {
			List<Sale> hotSales = aspBizSaleService.getHotSale(Constants.SLAE_LAST_TYPE, Constants.SLAE_PAGEINDEX, Constants.SLAE_PAGESIZE);
			model.addAttribute("hotSales", hotSales);
			long now = System.currentTimeMillis();
			model.addAttribute("nowTime", String.valueOf(now));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LAST;
	}
}
