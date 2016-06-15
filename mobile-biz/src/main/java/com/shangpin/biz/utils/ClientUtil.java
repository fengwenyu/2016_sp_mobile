package com.shangpin.biz.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.shangpin.biz.bo.SiteType;

public class ClientUtil {

	// 微信用户ua
	public static final String MIRCRO_MSG = "micromessenger";
	// app用户ua
	public static final String[] APP_MSG = { "AolaiIOSApp", "ShangpinAndroidApp", "AolaiAndroidApp", "ShangpinIOSApp" };
	// app ios用户ua
	public static final String[] APP_IOS_MSG = { "AolaiIOSApp", "ShangpinIOSApp" };
	public static final String SHANGPIN_IOS_APP = "ShangpinIOSApp";
	public static final String SHANGPIN_ANDRIOD_APP = "ShangpinAndroidApp";
	// andriod 用户ua
	public static final String[] APP_ANDRIOD_MSG = { "ShangpinAndroidApp", "AolaiAndroidApp" };
	public static final String APP_ANDRIOD_ORIGIN = "app";

	/**
	 * 判断是否为微博客户端
	 * 
	 * @param ua
	 * @return
	 */
	public static boolean checkWeibo(String ua) {
		if (ua.toLowerCase().indexOf("weibo") > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断微博是否在浏览器中打开
	 * 
	 * @param ua
	 * @return
	 */
	public static boolean checkWeiboBrower(String referer) {
		if (!org.springframework.util.StringUtils.isEmpty(referer) && referer.toLowerCase().indexOf("weibo") > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断客户端是否为微信
	 * 
	 * @Author wanghaibo
	 * @CreateDate 2014-1-28
	 * @param user
	 *            -agent
	 * @Return
	 */
	public static boolean CheckMircro(String uaString) {
		if (StringUtils.isNotEmpty(uaString) && uaString.toLowerCase().indexOf(MIRCRO_MSG) > -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是ios手机访问
	 * 
	 * @param uaString
	 * @return
	 * @author zghw
	 */
	public static boolean isIOS(String uaString) {
		if(StringUtils.isEmpty(uaString)){
			return false;
		}
		boolean isIphone = uaString.toLowerCase().indexOf("iphone") > -1;
		boolean isIpod = uaString.toLowerCase().indexOf("ipod") > -1;
		boolean isIpad = uaString.toLowerCase().indexOf("ipad") > -1;
		if (isIphone || isIpod || isIpad) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否android手机访问
	 * 
	 * @param uaString
	 * @return
	 * @author zghw
	 */
	public static boolean isAndroid(String uaString) {
		boolean isAndroid = uaString.toLowerCase().indexOf("android") > -1;
		return isAndroid;
	}

	/**
	 * 判断客户端是否为APP
	 * 
	 * @Author wanghaibo
	 * @CreateDate 2014-1-28
	 * @param user
	 *            -agent
	 * @Return
	 */
	public static boolean CheckApp(String uaString) {
		boolean appFlag = false;
		if (!StringUtils.isEmpty(uaString)) {
			uaString = uaString.toLowerCase();
			for (int i = 0; i < APP_MSG.length; i++) {
                if (uaString.indexOf(APP_MSG[i].toLowerCase()) > -1) {
					appFlag = true;
					break;
				}
			}
		}
		return appFlag;

	}

	/**
	 * 
	 * @Description: 判断客户端的系统。true表示ios
	 * @param uaString
	 * @return
	 */
	public static boolean CheckIOS(String uaString) {
		boolean appFlag = false;
		if (!StringUtils.isEmpty(uaString)) {
			uaString = uaString.toLowerCase();
			for (int i = 0; i < APP_IOS_MSG.length; i++) {
				if (uaString.indexOf(APP_IOS_MSG[i].toLowerCase()) > -1) {
					appFlag = true;
					break;
				}
			}
		}
		return appFlag;
	}

	/**
	 * 
	 * @Description: 判断客户端的系统Agent。true表示ios客户端
	 * @param uaString
	 * @return
	 */
	public static boolean CheckIosAgent(String uaString) {
		boolean appFlag = false;
		if (!StringUtils.isEmpty(uaString)) {
			uaString = uaString.toLowerCase();
			for (int i = 0; i < APP_IOS_MSG.length; i++) {
				if (uaString.indexOf(APP_IOS_MSG[i].toLowerCase()) > -1) {
					appFlag = true;
					break;
				}
			}
		}
		return appFlag;
	}

	/**
	 * 
	 * @Description: 判断客户端的系统Agent。true表示安卓客户端
	 * @param uaString
	 * @return
	 */
	public static boolean CheckAndriodAgent(String uaString) {
		boolean appFlag = false;
		if (!StringUtils.isEmpty(uaString)) {
			uaString = uaString.toLowerCase();
			for (int i = 0; i < APP_ANDRIOD_MSG.length; i++) {
				if (uaString.indexOf(APP_ANDRIOD_MSG[i].toLowerCase()) > -1) {
					appFlag = true;
					break;
				}
			}
		}
		return appFlag;
	}

	/**
	 * 
	 * @Description: 判断客户端的系统。true表示ios
	 * @param uaString
	 * @return
	 */
	public static boolean CheckOrigin(String origin) {
		if (origin == null || "".equals(origin)) {
			return false;
		}
		return origin.equals(APP_ANDRIOD_ORIGIN);
	}

	/**
	 * 取得APP M站 来源
	 * 
	 * @param request
	 * @return
	 * @author zghw
	 */
	public static SiteType getSiteType(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent").toLowerCase();
		String origin = request.getHeader("origin");
		if (origin != null) {
			origin = origin.toLowerCase();
		}
		SiteType siteType = SiteType.M_SHANGPIN;
		if (ClientUtil.CheckIosAgent(ua)) {
			siteType = SiteType.IOS_SHANGPIN;
		} else if (ClientUtil.CheckOrigin(origin) || ClientUtil.CheckAndriodAgent(ua)) {
			siteType = SiteType.ANDRIOD_SHANGPIN;
		}
		return siteType;
	}

	/**
	 * request中的User-Agent包含客户端字符串
	 * 
	 * @param request
	 * @return ShangpinIOSApp 或者 ShangpinAndroidApp M站null
	 */
	public static String getUaClient(HttpServletRequest request) {
		String ua = request.getHeader("User-Agent");
		String origin = request.getHeader("origin");
		if (origin != null) {
			origin = origin.toLowerCase();
		}
		// key
		if (!StringUtils.isEmpty(ua)) {
			ua = ua.toLowerCase();
			if (ClientUtil.CheckIosAgent(ua)) {
				return SHANGPIN_IOS_APP;
			} else if (CheckOrigin(origin) || CheckAndriodAgent(ua)) {
				return SHANGPIN_ANDRIOD_APP;
			}
		}
		return null;
	}

	public static boolean isNotlessThanVer(String ua, String appVer) {
		boolean flag = false;
		for (int i = 0; i < APP_MSG.length; i++) {
			if (ua.indexOf(APP_MSG[i].toLowerCase()) > -1) {
				String str = ua.substring(ua.indexOf(APP_MSG[i].toLowerCase()), ua.length());
				if (str.length() >= APP_MSG[i].length() + 1 && str.indexOf(";") > -1) {
					String ver = str.substring(APP_MSG[i].length() + 1, str.indexOf(";"));
					if (StringUtil.compareVer(ver, appVer) == 1 || StringUtil.compareVer(ver, appVer) == 0) {
						flag = true;
					}

				}
				if (str.length() >= APP_MSG[i].length() + 1 && str.indexOf(";") == -1) {
					String ver = str.substring(APP_MSG[i].length() + 1);
					if (StringUtil.compareVer(ver, appVer) == 1 || StringUtil.compareVer(ver, appVer) == 0) {
						flag = true;
					}

				}
			}
		}
		return flag;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		String ua = "uamozilla/5.0 (linux; android 5.0; huawei gra-ul00 build/huaweigra-ul00) applewebkit/537.36 (khtml, like gecko) version/4.0 chrome/37.0.0.0 mobile safari/537.36; shangpinandroidapp 2.8.0";
		// String
		// ua="uamozilla/5.0 (iphone; cpu iphone os 8_3 like mac os x) applewebkit/600.1.4 (khtml, like gecko) mobile/12f69 ;  shangpiniosapp 2.8.0; ;  shangpiniosapp 2.8.0 ;  shangpiniosapp 2.8.0;";
		ClientUtil clientUtil = new ClientUtil();
		System.out.println(clientUtil.isNotlessThanVer(ua.toLowerCase(), "2.8.0"));

	}
}
