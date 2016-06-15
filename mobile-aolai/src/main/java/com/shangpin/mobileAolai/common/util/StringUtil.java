package com.shangpin.mobileAolai.common.util;

public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-29
	 * @param str
	 *            被判断对象
	 * @Return
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
     * 比较版本号
     * 
     * @param ver
     *            版本号
     * @param anotherVer
     *            比较的版本号
     */
    public static int compareVer(String ver, String anotherVer) {
        // 0：相等；1：大于；-1：小于
        String[] bigArr = ver.split("\\.");
        String[] smallArr = anotherVer.split("\\.");
        for (int i = 0; i < 3; i++) {
            if (Integer.parseInt(bigArr[i]) > Integer.parseInt(smallArr[i])) {
                return 1;
            } else if (Integer.parseInt(bigArr[i]) < Integer.parseInt(smallArr[i])) {
                return -1;
            }
        }
        return 0;
    }
    /**
     * 截掉小数
     * 
     * @Author: wangfeng
     * @CreateDate: 2014-07-18
     * @param str
     *            被判断对象
     * @Return:
     */
    public static String cutOffDecimal (String str) {
        if (str != null && !"".equals(str.trim()) && !"null".equals(str)
                && !"0".equals(str)) {
            if(str.indexOf(".") > 0){
                str = str.substring(0, str.indexOf("."));
            }
        } 
        return str;
    }
    
    /**
     * 检查对象是否为空， null 以 "" 返回
     * @param value
     * @return
     */
    public static String checkNull(String value) {
		if (value != null && !"null".equals(value)) {
			return value;
		} else {
			return "";
		}
	}
    /**
     * 取得字符串的boolean 值
     * @param value
     * @return
     */
    public static String checkTrueOrFalse(String value) {
		if (value != null && "true".equals(value)) {
			return "1";
		} else {
			return "0";
		}
	}
}
