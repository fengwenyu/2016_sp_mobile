package com.shangpin.api.utils;

import java.util.regex.Pattern;

/**
 * 验证工具类
 * @author zghw
 *
 */
public class ValidateUtil {
	/**
	 * 手机号码验证
	 * @param mobiles
	 * @return
	 * @author zghw
	 */
	public static boolean isMobileNumber(String mobiles) {
		return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}").matcher(mobiles).matches();
	}
}
