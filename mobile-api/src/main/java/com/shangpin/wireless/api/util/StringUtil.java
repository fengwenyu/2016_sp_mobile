package com.shangpin.wireless.api.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
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
     * 判断字符串是否为空
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
     * 字符串为null返回""
     * 
     * @Author: zhouyu
     * @CreateDate: 2012-09-16
     * @param str
     *            被判断对象
     * @Return:
     */
    public static String isNotEmptybyStr(String str) {
        if (str != null && !"".equals(str.trim()) && !"null".equals(str) && !"0".equals(str)) {
        } else {
            return "";
        }
        return str;
    }

    /**
     * 验证手机号码的合法性
     * 
     * @param mobilePhone
     * @return
     */
    public static boolean checkMobliePhone(String mobilePhone) {
        if (!isNotEmpty(mobilePhone)) {
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobilePhone);
        return m.matches();
    }

    /**
	 * 比较版本号
	 * 
	 * @param ver
	 *            版本号
	 * @param anotherVer
	 *            比较的版本号
	 * @return 0两者相等，1 ver>anotherVer, -1 ver<anotherVer
	 */
	public static int compareVer(String ver, String anotherVer) {
		if(StringUtils.isBlank(ver) && StringUtils.isNotBlank(anotherVer)){
			return -1;
		}
		if(StringUtils.isBlank(anotherVer) && StringUtils.isNotBlank(ver)){
			return 1;
		}
		int gtOrlt=0;//=是0，1是>，-1<
		String[] svs=ver.split("\\.");
		String[] ovs = anotherVer.split("\\.");
		int i=0;
		while(true){//先比较.位相同的
			if(i+1>ovs.length) break;
			Integer nextInt=getNextVer(ovs,i);
			gtOrlt=Integer.valueOf(getNextVer(svs,i)).compareTo(nextInt);
			if(gtOrlt!=0){
				break;
			}
			i++;
		}
		if(gtOrlt==0 ){
			if(ovs.length>svs.length){
				return -1;
			}else if(ovs.length<svs.length){
				return 1;
			}
		}
		return gtOrlt;
	}

    public static boolean inVersionRange(String ver, String verRangeConfig) {
        String[] config = verRangeConfig.split(",");
        if (config.length != 2)
            return false;

        boolean matchSmall = false, matchBig = false;
        String configVer = config[0].substring(1);
        if (config[0].startsWith("[") && compareVer(ver, configVer) >= 0) {
            matchSmall = true;
        } else if (config[0].startsWith("(") && compareVer(ver, configVer) > 0) {
            matchSmall = true;
        }
        configVer = config[1].substring(0, config[1].length() - 1);
        if ("oo".equals(configVer)) {
            matchBig = true;
        } else if (config[1].endsWith("]") && compareVer(ver, configVer) <= 0) {
            matchBig = true;
        } else if (config[1].endsWith(")") && compareVer(ver, configVer) < 0) {
            matchBig = true;
        }

        if (matchSmall && matchBig) {
            return true;
        }
        return false;
    }

    public static final String buildParams(Map<String, String> params, String start, String end, boolean sort, boolean encode) {

        ArrayList<String> keys = new ArrayList<String>(params.keySet());
        if (sort) {
            Collections.sort(keys); // 参数排序
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (value == null || value.length() == 0)
                continue;

            if (encode) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            sb.append(key).append("=");
            if (start != null && end != null) {
                sb.append(start).append(value).append(end);
            } else {
                sb.append(value);
            }
            if (i < keys.size() - 1) {// 拼接时，不包括最后一个&字符
                sb.append("&");
            }
        }

        return sb.toString();
    }

    public static final String buildParams(Map<String, String> params, boolean sort, boolean encode) {
        return buildParams(params, null, null, sort, encode);
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
    public static String cutOffDecimal(String str) {
        if (str != null && !"".equals(str.trim()) && !"null".equals(str) && !"0".equals(str)) {
            if (str.indexOf(".") > 0) {
                str = str.substring(0, str.indexOf("."));
            }
        }
        return str;
    }

    /**
     * 过滤特殊字符
     * 
     * @param str
     * @return
     */
    public static String StringFilter(String str) throws PatternSyntaxException {
        if(str!=null||"".equals(str)){
            String regEx = "[`~!@#$%^&*()+=|{}';',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
        }else{
            return "";
        }
        
    }
    /**
	 * 
	 * 比较版本大小；支持版本区间<br/>
	 * 比较某版本是否在版本区间内
	 * @param startVersion 版本开始区间
	 * @param endVersion 版本结束区间
	 * @param theVersion 需要比较的版本
	 * @return
	 * 		0：当前所在版本区间[xx,yy)，一般是【xx版本到yy版本，但不包含yy版本】需求未变动；<br/>
	 * 		1：最新版本[xx,~)；<br/>
	 * 		-1：老版本(~,xx)
	 */
	/*public static int compareVersion(String startVersion, String endVersion, String theVersion) {
		return com.shangpin.utils.StringUtil.compareVersion(startVersion, endVersion, theVersion);
	}*/
	
	/**
	 * 获取ovs下一个整型版本<br/>如果没有下一个则返回0
	 * @param ovs 2.3.23这种以.分隔的数组
	 * @param i 下标，从0开始
	 * @return
	 */
	private static Integer getNextVer(String[] ovs, int i) {
		if(ovs.length-1>=i)
			return Integer.valueOf(ovs[i].trim());
		return 0;
	}

	
	
}
