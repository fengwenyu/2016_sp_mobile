package com.aolai.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class DownLoadController extends BaseController {
	
	/**下载首页*/
	private static final String INDEX = "download/index";

	@RequestMapping(value = "/download")
	public String download() {
		return INDEX;
	}

	/**
	 * 下载手机客户端（Android/IOS版）
	 * 
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value = "/appDownload", method = RequestMethod.GET)
	public String downloadApp(Map<String, Object> map, String store, String p, HttpServletRequest request, HttpServletResponse response) {
		// 记录访问日志
		try {
			if (StringUtils.isNotEmpty(store)) {
				if ("iphone".equals(store.trim())) {
					response.sendRedirect("https://itunes.apple.com/cn/app/id432489082?mt=8");
				} else if ("android".equals(store.trim())) {
					response.sendRedirect("http://m.shangpin.com/download.action?p=101&ch=3&fileName=aolai_3.apk");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
