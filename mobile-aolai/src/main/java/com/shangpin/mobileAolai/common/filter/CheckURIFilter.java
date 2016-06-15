package com.shangpin.mobileAolai.common.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import net.sf.json.JSONObject;

import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.PropertiesUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.WebUtil;

public class CheckURIFilter implements Filter {
	private FilterConfig filterConfig;
	
	private static HashMap<String, JSONObject> GetUrlCache = new HashMap<String, JSONObject>();
	private static final int GETURL_KEEP_TIME = 30 * 60 * 1000; // 30分钟
	private static long GetUrlTime;

	@SuppressWarnings("rawtypes")
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getRequestURI();
		//String localAddr = request.getLocalAddr();
		String url = request.getRequestURL().toString();
		String ch = request.getParameter(Constants.CHANNEL_PARAM_NAME);
		int maxAge = 30 * 24 * 60 * 60;
		if (StringUtils.isNotEmpty(ch)) {
			CookieUtil.addCookie(response, Constants.CHANNEL_PARAM_NAME, ch, maxAge);
		} else {
			if (CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(request, Constants.CHANNEL_PARAM_NAME).getValue();
			}
			if (!StringUtil.isNotEmpty(ch)) {
				if (Constants.WAL_URL.equals(request.getHeader("referer"))) {
					CookieUtil.addCookie(response, Constants.COOKIE_CHANNEL_PARAM_NAME, Constants.AOLAI_WEB_DEFAULT_CHANNELNO, maxAge);
				} else {
					CookieUtil.addCookie(response, Constants.COOKIE_CHANNEL_PARAM_NAME, Constants.AOLAI_WAP_DEFAULT_CHANNELNO, maxAge);
				}
			}
			
		}
		if (ch == null) {
			ch = "";
		}else{
			Pattern pattern = Pattern.compile("[0-9]*");
			boolean isNum = pattern.matcher(ch).matches();
			if(!isNum){
				response.sendRedirect(Constants.AOLAI_URL);
				return;
			}
		}
		if (uri != null && url != null) {
			String basePath = "";
//			if (Constants.SERVER_IP.equals(localAddr)) {
//				basePath = "http://m.aolai.com/";
//			} else {
//				basePath = request.getScheme() + "://" + localAddr + ":" + request.getServerPort() + request.getContextPath() + "/";
//			}
			basePath = Constants.AOLAI_URL;
			StringBuffer para = new StringBuffer();
			// 获取推广链接（3个级别），将参数保存到cookie中
			String contain = Constants.AOLAI_URL+"promotion";
//			String contain = "http://2008dreams.gicp.net/MobileAolai/promotion";
			if (url.indexOf(contain) >= 0) {
				para.append(url.substring(url.lastIndexOf("/") + 1));
				para.append(Constants.SP_COOKIE_SEPARATOR_CHAR);
				para.append(new Date().getTime());
				if (url.indexOf(Constants.SP_COOKIE_NAME_PROMOTION1ST) > 0) {
					CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_PROMOTION1ST, para.toString(), 30 * 24 * 60 * 60);
				}
				if (url.indexOf(Constants.SP_COOKIE_NAME_PROMOTION2ND) > 0) {
					CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_PROMOTION2ND, para.toString(), 7 * 24 * 60 * 60);
				}
				if (url.indexOf(Constants.SP_COOKIE_NAME_PROMOTION3TH) > 0) {
					CookieUtil.addCookie(response, Constants.SP_COOKIE_NAME_PROMOTION3TH, para.toString(), 1 * 24 * 60 * 60);
				}
				url = basePath + "aolaiindex!index";
				String jump = request.getParameter("url");
				if (jump != null && jump.length() > 0) {
					url = jump;	//	跳转到指定链接
				}
				response.sendRedirect(url);
				return;
			}
			
			long now = System.currentTimeMillis();
			if (now > GetUrlTime + GETURL_KEEP_TIME) {
				Properties props = PropertiesUtil.getInstance("/resources/config/redirect/urlfilter.properties");
				GetUrlCache.clear();
				for (Enumeration e = props.keys(); e.hasMoreElements();) {
					String propkey = (String) e.nextElement();
					String propvalue;
					try {
						propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
						JSONObject json = JSONObject.fromObject(propvalue);
						GetUrlCache.put(propkey, json);
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}
				GetUrlTime = now;
			}
			String redirectURL = null;
			Set<String> key = GetUrlCache.keySet();
			for (Iterator it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				JSONObject obj = GetUrlCache.get(s);
				final SimpleDateFormat sdfconfig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					if(obj.has("startDate")){
						Date starttime;
						starttime = sdfconfig.parse(obj.getString("startDate"));
						Date date = new Date(now);
						if(!date.after(starttime)){
							continue;
						}
					}
					if(obj.has("endDate")){
						Date endtime;
						endtime = sdfconfig.parse(obj.getString("endDate"));
						Date date = new Date(now);
						if(!date.before(endtime)){
							continue;
						}
					}

					redirectURL = obj.getString("geturl");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				Pattern pattern = Pattern.compile(obj.getString("regex"));
			
				Matcher m = pattern.matcher(uri);
				if (m.matches()) {
	//				redirectURL = PromotionCache.get(s).getString("geturl");
					final String query = request.getQueryString();
					if (StringUtil.isNotEmpty(query)) {
						if (query.length() > 0) {
							final int questionIndex = redirectURL.indexOf("?");
							if (questionIndex > 0) {
								redirectURL = redirectURL + "&" + query;
							} else {
								redirectURL = redirectURL + "?" + query;
							}
						}
					}
					if (m.groupCount() > 0) {
						String[] arr = null;
						arr = new String[m.groupCount()];
						for (int j = 0; j < m.groupCount(); j++) {
								arr[j] = m.group(j + 1);
						}
						
						redirectURL = MessageFormat.format(redirectURL, arr);
					}
					if(StringUtil.isNotEmpty(redirectURL)){
						response.sendRedirect(redirectURL);
						return;
					}
				}	
			}
			String[] split = uri.split("/");

			if (split.length > 1) {
				uri = split[split.length - 1];
				if (uri.startsWith("cartaction!deletecart") || uri.startsWith("cartaction!showcart")) {
					Object obj = request.getSession().getAttribute(WebUtil.SESSION_USER_PARAM);
					if (obj == null) {
						response.sendRedirect(basePath + "accountaction!loginui?loginFrom=4");
						return;
					}
				} else if (uri.startsWith("orderaction")) {
					Object obj = request.getSession().getAttribute(WebUtil.SESSION_USER_PARAM);
					if (obj == null) {
						response.sendRedirect(basePath + "accountaction!loginui?loginFrom=2");
						return;
					}
				} else if (uri.startsWith("accountaction!addresslist") || uri.startsWith("accountaction!deleteaddress") || uri.startsWith("accountaction!newaddress") || uri.startsWith("accountaction!modifyaddress") || uri.startsWith("accountaction!saveaddress") || uri.startsWith("accountaction!ajaxsaveaddress") || uri.startsWith("accountaction!ajaxsaveinvoiceaddress")) {
					Object obj = request.getSession().getAttribute(WebUtil.SESSION_USER_PARAM);
					if (obj == null) {
						response.sendRedirect(basePath + "accountaction!loginui?loginFrom=3");
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		init();
	}

	public void init() throws ServletException {
	}

	public void destroy() {
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public static void main(String[] args) {
		// http://m.aolai.com/merchandiseaction!list?ch=&activityId=20130308986&pageType=02&typeFlag=1
		// String url = "http://m.aolai.com/Subject/Index/20130308986";
		// http://m.aolai.com/merchandiseaction!list?ch=&activityId=20130305414&pageType=03&typeFlag=0
		// String url = "http://m.aolai.com/Topic/Index/20121129473";
		// http://m.aolai.com/merchandiseaction!detail?ch=&categoryno=20130308664&goodsid=04230566&pageType=02&typeFlag=1&activityId=20130308986
		 String url = Constants.AOLAI_URL+"women/product/detail/20121129473/0/01220070";
		 //String url = "http://m.aolai.com/men/product/detail/20121129473/0/01220070";
		if (url.indexOf("Subject") > 0) {
			String[] split = url.substring(url.indexOf("Subject"), url.length()).split("/");
			url = Constants.AOLAI_URL+"merchandiseaction!list?activityId=" + split[2] + "&pageType=03&typeFlag=1";
		} else if (url.indexOf("Topic") > 0) {
			String[] split = url.substring(url.indexOf("Topic"), url.length()).split("/");
			url = Constants.AOLAI_URL+"merchandiseaction!list?activityId=" + split[2] + "&pageType=03&typeFlag=0";
		} else if (url.indexOf("detail") > 0) {
			String[] split = url.substring(url.indexOf("detail"), url.length()).split("/");
			url = Constants.AOLAI_URL+"merchandiseaction!detail?categoryno=" + split[1] + "&goodsid=" + split[3] + "&pageType=02&typeFlag=" + split[2] + "&activityId=20130308986";
		}
	}
}
