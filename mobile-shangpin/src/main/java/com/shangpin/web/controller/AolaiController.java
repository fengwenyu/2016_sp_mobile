package com.shangpin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName: AolaiController
 * @Description: ao lai
 */
@Controller
public class AolaiController  {
    private static final Logger logger = LoggerFactory.getLogger(AolaiController.class);
	
    /**
	 * 奥莱结束服务通知
	 * @return
	 */
	@RequestMapping(value = "/notice")
    public String notice() {
	    logger.info("访问奥莱的通知");
        return "/aolaiNotice/index";
    }
}
