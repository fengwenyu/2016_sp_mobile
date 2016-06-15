package com.shangpin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TOPSHOP的controller
 * 
 * @author liling
 */
@Controller
@RequestMapping("/topshop")
public class TopshopController extends BaseController {
	public static Logger logger = LoggerFactory.getLogger(StyleController.class);
	private static final String INDEX = "topshop/index";
	/**
     * 直接跳转到topshop首页面
     * 
     * @author liling
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index() {
        return INDEX;
    }
}
