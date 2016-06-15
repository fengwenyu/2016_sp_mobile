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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.base.vo.Look;
import com.shangpin.base.vo.LookForProduct;
import com.shangpin.biz.common.page.Page;
import com.shangpin.biz.service.SPBizStyleService;


/** 
* @ClassName: StyleController 
* @Description:style搭配
* @author liling
* @date 2014年12月18日
* @version 1.0 
*/
@Controller
@RequestMapping("/style")
public class StyleController extends BaseController {
	public static Logger logger = LoggerFactory.getLogger(StyleController.class);
	private static final String INDEX = "style/index";
	private static final String LIST = "style/list";
	@Autowired
	private SPBizStyleService styleBizService;

	/**
	 * 
	 * @Title: index
	 * @Description: 直接跳转到style搭配的首页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String pageIndex, Model model) {
		pageIndex = pageIndex != null ? pageIndex : "1";
		List<Look> look = styleBizService.getLooks(pageIndex);
		if (look.size() < Page.DEFAULT_PAGE_SIZE) {
			model.addAttribute("haveMore", "0");
		} else {
			model.addAttribute("haveMore", "1");
			pageIndex = String.valueOf(Integer.parseInt(pageIndex) + 1);
		}
		model.addAttribute("look", look);
		model.addAttribute("pageIndex", pageIndex);
		return INDEX;
	}

	/**
	 * 
	 * @Title: getMore
	 * @Description: style搭配列表获取更多
	 * @param
	 * @return Map<String, Object>
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@ResponseBody
	@RequestMapping(value = "/getMore", method = RequestMethod.POST)
	public Map<String, Object> getMore(@RequestParam String pageIndex) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Look> look = styleBizService.getLooks(pageIndex);
		if (look.size() < Page.DEFAULT_PAGE_SIZE) {
			map.put("haveMore", "0");
		} else {
			map.put("haveMore", "1");
		}
		pageIndex = String.valueOf(Integer.parseInt(pageIndex) + 1);
		map.put("look", look);
		map.put("pageIndex", pageIndex);
		return map;
	}

	/**
	 * 
	 * @Title: index
	 * @Description: style搭配商品列表
	 * @param id
	 *            look活动编号
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@RequestParam String id, Model model) {
		LookForProduct lookForProduct = styleBizService.getLookProducts(id);
		model.addAttribute("lookForProduct", lookForProduct);
		return LIST;
	}

}
