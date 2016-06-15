package com.shangpin.wireless.api.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CharacterFilter extends HttpFilter {
	private final Log log = LogFactory.getLog(CharacterFilter.class);
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");;
	    String uri = request.getRequestURI();
	    List<String> list = URIList.list;
	    for(String str : list){
	        if(uri.toLowerCase().endsWith(str)){
	            chain.doFilter(request, response);
	            return;
	        }
	    }
		Myrequest myrequest = new Myrequest(request);
		myrequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//保存IMEI
		final String imei = request.getHeader("imei");
		final String oldimei = request.getHeader("oldimei");
		if (StringUtils.isNotEmpty(imei) && StringUtils.isNotEmpty(oldimei)) {
			log.debug("imei :"+imei +" ,"+"oldimei :"+oldimei);
		}
		chain.doFilter(myrequest, response);
	}
}

class Myrequest extends HttpServletRequestWrapper {

	String encoding = null;
	public Myrequest(HttpServletRequest request) {
		super(request);
		encoding = request.getCharacterEncoding();
	}

	@Override
	public String getParameter(String name) {
		String string = super.getParameter(name);
		String method = super.getMethod();
		if (string != null && "get".equalsIgnoreCase((method)) && string.length() > 0 && !"".equals(string.trim())) {
			try {
				if(encoding != null){
					String a = new String(string.getBytes(encoding), "ISO-8859-1");
					string = new String(a.getBytes("ISO-8859-1"), encoding);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return string;
	}
}

class URIList {
    static List<String> list = null;
    static {
        list = new ArrayList<String>();
        list.add(".jsp");
        list.add(".html");
        list.add(".htm");
        list.add(".css");
        list.add(".js");
    }
}
