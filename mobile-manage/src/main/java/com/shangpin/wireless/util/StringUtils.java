package com.shangpin.wireless.util;

public class StringUtils {

	public static final String getTagContent(String wholeContent, String startTag, String endTag) {
		final int startIndex = wholeContent.indexOf(startTag);
		if (startIndex < 0)
			return null;
		final int endIndex = wholeContent.indexOf(endTag);
		if (endIndex <= 0 || endIndex < startIndex + startTag.length())
			return null;

		return wholeContent.substring(startIndex + startTag.length(), endIndex);
	}

	/**
	 *判断字符串是否为空
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param str
	 *            被判断对象
	 * @Return:
	 */
	public static boolean isNotEmpty(String... str) {
		int length = str.length;
		for (int i = 0; i < length; i++) {
			if (str[i] != null && !"".equals(str[i].trim())) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 *判断字符串为空
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2012-09-16
	 * @param str
	 *            被判断对象
	 * @Return:
	 */
	public static boolean isEmpty(String... str) {
		int length = str.length;
		for (int i = 0; i < length; i++) {
			if (str[i] == null || "".equals(str[i].trim())) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param sound
	 * @param defaultStr
	 * @return
	 */
    public static String defaultIfEmpty(String sound, String defaultStr) {
        if(isEmpty(sound)){
            return defaultStr;
        }
        return sound;
    }
}
