package com.shangpin.api.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.shangpin.api.utils.Constants;
import com.shangpin.utils.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BaseController {

	/** APP登录APP登录uid信息的key名称 */
	public static final String APP_NAME_UID = "userid";
	/**
	 * 把结果写回
	 * 
	 * @param response
	 * @param result
	 */
	protected void responseResult(HttpServletResponse response, String result) {
		response.setCharacterEncoding(Constants.DEFAULT_CHARACTER);
		response.setHeader("content-type", "application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 请求参数不完整
	 * 
	 * @return
	 */
	protected String returnParamError() {
		return "{\"code\":\"" + Constants.ERROR_PARAM_CODE + "\",\"msg\":\"" + Constants.ERROR_PARAM_MSG + "\",\"content\":{}}";
	}
	/**
	 * 请求参数不合法
	 * 
	 * @return
	 */
	protected String returnParamVerifyError() {
		return "{\"code\":\"" + Constants.ERROR_VERIFY_CODE + "\",\"msg\":\"" + Constants.ERROR_VERIFY_MSG + "\",\"content\":{}}";
	}
	/**
	 * 返回错误
	 * 
	 * @return
	 */
	protected String returnError(String code,String msg) {
		return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\",\"content\":{}}";
	}
	/***
	 * 对外提供数据有误的json
	 * @return json String
	 */
	protected String returnExternalError(){
	    return "{\"result\":\"" + Constants.ERROR_DATA + "\",\"data\":[]}";
	}

	/**
	 * 程序异常
	 * 
	 * @return
	 */
	protected String returnSystemError() {
		return "{\"code\":\"" + Constants.ERROR_SYSTEM_CODE + "\",\"msg\":\"" + Constants.ERROR_SYSTEM_MSG + "\",\"content\":{}}";
	}
	/**
	 * 从客户端内请求中取出userId
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	protected String getAppUId(HttpServletRequest request) {
		String userId = request.getHeader(APP_NAME_UID);
		if (StringUtils.isNotBlank(userId)) {
			return userId;
		} else {
			userId = request.getParameter("userId");
			if (StringUtils.isNotBlank(userId)) {
				return userId;
			}
		}
		return null;
	}
	
	/**
	 * 处理返回结果
	 * @param result
	 * @return
	 * @author zghw
	 */
	protected String toResult(String result){
		if(StringUtil.isEmpty(result)){
			return returnSystemError();
		}
		return result;
	}
	
	/**
	 * 获取spring web容器
	 * @param request
	 * @return
	 * @author zghw
	 */
	protected WebApplicationContext getCtx(HttpServletRequest request){
		ServletContext sc = request.getSession().getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		return ctx;
	}
}
