package com.shangpin.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

public class StringUtil {
    
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");
    
    public static boolean isBlank(String str)
    {
        if( str == null || str.length() == 0 )
            return true;
        return false;
    }

    /**
     * is empty string.
     * 
     * @param str source string.
     * @return is empty.
     */
    public static boolean isEmpty(String str)
    {
        if( str == null || str.length() == 0 )
            return true;
        return false;
    }

    /**
     * is not empty string.
     * 
     * @param str source string.
     * @return is not empty.
     */
    public static boolean isNotEmpty(String str)
    {
        return str != null && str.length() > 0;
    }
    
    /**
     * 
     * @param s1
     * @param s2
     * @return equals
     */
    public static boolean isEquals(String s1, String s2) {
        if (s1 == null && s2 == null)
            return true;
        if (s1 == null || s2 == null)
            return false;
        return s1.equals(s2);
    }
    
    /**
     * is integer string.
     * 
     * @param str
     * @return is integer
     */
    public static boolean isInteger(String str) {
        if (str == null || str.length() == 0)
            return false;
        return INT_PATTERN.matcher(str).matches();
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static int parseInteger(String str) {
        if (! isInteger(str))
            return 0;
        return Integer.parseInt(str);
    }

    /**
     * Returns true if s is a legal Java identifier.<p>
     * <a href="http://www.exampledepot.com/egs/java.lang/IsJavaId.html">more info.</a>
     */
    public static boolean isJavaIdentifier(String s) {
        if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i=1; i<s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @param values
     * @param value
     * @return contains
     */
    public static boolean isContains(String[] values, String value) {
        if (value != null && value.length() > 0 && values != null && values.length > 0) {
            for (String v : values) {
                if (value.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

  
    /**
     * translat.
     * 
     * @param src source string.
     * @param from src char table.
     * @param to target char table.
     * @return String.
     */
    public static String translat(String src, String from, String to)
    {
        if( isEmpty(src) ) return src;
        StringBuilder sb = null;
        int ix;
        char c;
        for(int i=0,len=src.length();i<len;i++)
        {
            c = src.charAt(i);
            ix = from.indexOf(c);
            if( ix == -1 )
            {
                if( sb != null )
                    sb.append(c);
            }
            else
            {
                if( sb == null )
                {
                    sb = new StringBuilder(len);
                    sb.append(src, 0, i);
                }
                if( ix < to.length() )
                    sb.append(to.charAt(ix));
            }
        }
        return sb == null ? src : sb.toString();
    }

    /**
     * split.
     * 
     * @param ch char.
     * @return string array.
     */
    public static String[] split(String str, char ch)
    {
        List<String> list = null;
        char c;
        int ix = 0,len=str.length();
        for(int i=0;i<len;i++)
        {
            c = str.charAt(i);
            if( c == ch )
            {
                if( list == null )
                    list = new ArrayList<String>();
                list.add(str.substring(ix, i));
                ix = i + 1;
            }
        }
        if( ix > 0 )
            list.add(str.substring(ix));
        return list == null ? EMPTY_STRING_ARRAY : (String[])list.toArray(EMPTY_STRING_ARRAY);
    }

    /**
     * join string.
     * 
     * @param array String array.
     * @return String.
     */
    public static String join(String[] array)
    {
        if( array.length == 0 ) return "";
        StringBuilder sb = new StringBuilder();
        for( String s : array )
            sb.append(s);
        return sb.toString();
    }

    /**
     * join string like javascript.
     * 
     * @param array String array.
     * @param split split
     * @return String.
     */
    public static String join(String[] array, char split)
    {
        if( array.length == 0 ) return "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<array.length;i++)
        {
            if( i > 0 )
                sb.append(split);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    /**
     * join string like javascript.
     * 
     * @param array String array.
     * @param split split
     * @return String.
     */
    public static String join(String[] array, String split)
    {
        if( array.length == 0 ) return "";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<array.length;i++)
        {
            if( i > 0 )
                sb.append(split);
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    /**
     * 
     * @param coll
     * @param split
     * @return
     */
    public static String join(Collection<String> coll, String split) {
        if(coll.isEmpty()) return "";
        
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(String s : coll) {
            if(isFirst) isFirst = false; else sb.append(split);
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 
     * @param ps
     * @return
     */
    public static String toQueryString(Map<String, String> ps) {
        StringBuilder buf = new StringBuilder();
        if (ps != null && ps.size() > 0) {
            for (Map.Entry<String, String> entry : new TreeMap<String, String>(ps).entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && key.length() > 0
                        && value != null && value.length() > 0) {
                    if (buf.length() > 0) {
                        buf.append("&");
                    }
                    buf.append(key);
                    buf.append("=");
                    buf.append(value);
                }
            }
        }
        return buf.toString();
    }
    
    /**
     * 
     * @param camelName
     * @param split
     * @return
     */
    public static String camelToSplitName(String camelName, String split) {
        if (camelName == null || camelName.length() == 0) {
            return camelName;
        }
        StringBuilder buf = null;
        for (int i = 0; i < camelName.length(); i ++) {
            char ch = camelName.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                if (buf == null) {
                    buf = new StringBuilder();
                    if (i > 0) {
                        buf.append(camelName.substring(0, i));
                    }
                }
                if (i > 0) {
                    buf.append(split);
                }
                buf.append(Character.toLowerCase(ch));
            } else if (buf != null) {
                buf.append(ch);
            }
        }
        return buf == null ? camelName : buf.toString();
    }
    
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
	 * 判断字符串是否为空
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-29
	 * @param str
	 *            被判断对象
	 * @Return
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
	 * 检查对象是否为空， null 以 "" 返回
	 * 
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
	 * 
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

	/**
	 * 截取带小数点的数字的字符串 例如：123.00 截取为 123
	 * 
	 * @param doubleStr
	 * @return
	 * @author zghw
	 */
	public static String removePoint(String doubleStr) {
		int pos = doubleStr.indexOf(".");
		if (pos != -1) {
			return doubleStr.substring(0, pos);
		}
		return doubleStr;
	}
	
	/**
	 * 判断字符串是否是由数字组成
	 * @param value
	 * @return
	 */
	public static boolean checkStringByNum(String value) {
        String capitalLetters = "^-?[0-9]+$";
        if (value == null || value.trim().length() < 1) {
        	return false;
        }
        if (value.matches(capitalLetters)) {
        	return true;
        } else {
        	return false;
		}
    }
	
	/**
	 * 生成随机字符串
	 * @param length 长度
	 * @return
	 */
	public static String getRandomNumber(int length) { //length表示生成字符串的长度
		String base = "0123456789";   
		return getRandomString(base, length);
	}   
	/**
	 * 生成随机字符串
	 * @param length 长度
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";   
		return getRandomString(base, length);
	}   
	/**
	 * 生成随机字符串
	 * @param length 长度
	 * @return
	 */
	public static String getRandomString(String base, int length) { //length表示生成字符串的长度   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
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
	public static int compareVersion(String startVersion, String endVersion, String theVersion) {
		int result = 0;
		if(StringUtils.isBlank(endVersion) && StringUtils.isBlank(startVersion)){
			return 1;
		}
		if(StringUtils.isBlank(startVersion)){
			int v2=compareVer(theVersion,endVersion);
			if(v2>=0){
				return 1;
			}else{
				return -1;
			}
		}
		if(StringUtils.isBlank(endVersion)){
			int v1=compareVer(theVersion,startVersion);
			if(v1>0){
				return 1;
			}else if(v1==0){
				return 0;
			}else{
				return -1;
			}
		}
		int v1=compareVer(startVersion, theVersion);
		int v2=compareVer(endVersion,theVersion);
		if(v1<0 && v2<=0){
			return 1;
		}
		if(v1>=0 && v2<0){
			return 0;
		} else if (v1 > 0) {//开始版本>当前版本，则当前版本是老版本
			return -1;//result = 1;
		} else if (v2 <= 0) {//结束版本<当前版本，则当前版本是新版本
			return 1;//result = -1;
		}
		
		return result;
	}
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

	/**
	 * 版本比较
	 * @param baseVersion 基于比较的版本
	 * @param currentVersion 客户端请求头部过来的版本号
	 * 如baseVersion = "2.9.6", currentVersion = "2.9.9",
	 * 需要返回currentVersion大于baseVersion
	 * 如果大于等于则返回1，反之则返回0
	 */
	public static int compareVersion(String baseVersion, String currentVersion){
		String[] base = baseVersion.split("\\.");
		String[] current = currentVersion.split("\\.");
		for(int i = 0; i < current.length; i++){
			int c = Integer.parseInt(current[i]);
			int b = Integer.parseInt(base[i]);
			if(c > b){
				return 1;
			}
		}
		//两个版本相同
		if(Integer.parseInt(baseVersion.replace(".", "").trim()) == Integer.parseInt(currentVersion.replace(".", "").trim())){
			return 1;
		}
		return 0;
	}


    /**
     * 版本校验
     *
     * 如果传空即为不限制 开始包含，结束不包含
     *      大于等于开始区间，并小于结束区间即为true,
     *      开始版本为空 只要小于结束区间即为true
     *      结束版本为空 只要大于等于开始区间即为true
     *  true (1, 9, 8)
     *  false (1, 9, 9)
     *  false (1, 9, 10)
     *  true (1, 9, 1)
     *  false (1, 9, 0)
     *  true (1, null, 8)
     *  true (1, null, 1)
     *  false (1, null, 0)
     *  true (null, 9, 1)
     *  false (null, 9, 9)
     *  false (null, 9, 10)
     *  treu (null, null, 10)
     *  false (*, *, null)
     *
     * @param startVersion 开始版本，空为不限制
     * @param endVersion 结束版本，空为不限制
     * @param theVersion 客户端请求头部过来的版本号
     * @return
     */
    public static boolean verifyVersion(String startVersion, String endVersion, String theVersion) {

        if(StringUtils.isBlank(theVersion)){
            return false;
        }

        if(StringUtils.isBlank(endVersion) && StringUtils.isBlank(startVersion)){
            return true;
        }
        if(StringUtils.isBlank(startVersion)){
            int v2=compareVer(theVersion,endVersion);
            if(v2>=0){
                return false;
            }else{
                return true;
            }
        }
        if(StringUtils.isBlank(endVersion)){
            int v1=compareVer(theVersion,startVersion);
            if(v1>=0){
                return true;
            }else{
                return false;
            }
        }
        int v1=compareVer(startVersion, theVersion);
        int v2=compareVer(endVersion,theVersion);
        if(v1<=0 && v2>0){
            return true;
        }

        return false;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
//		System.out.println(getRandomString(32));
//		System.out.println(compareVer("2.9.3","2.9.10"));
//		System.out.println(compareVersion("2.9.9", "2.9.9"));
		System.out.println(verifyVersion("2.9.0", "2.9.9", "2.9.8"));
		System.out.println(verifyVersion("2.9.0", "2.9.9", "2.9.0"));
		System.out.println(verifyVersion(null, "2.9.9", "2.9.0"));
		System.out.println(verifyVersion("2.9.0", null, "2.9.0"));
		System.out.println(verifyVersion("2.9.0", null, "2.9.100"));
        System.out.println("verify----------");
        System.out.println(verifyVersion("2.9.1", "2.9.9", "2.9.0"));
		System.out.println(verifyVersion("2.9.1", "2.9.9", "2.9.10"));
		System.out.println(verifyVersion("2.9.1", "2.9.9", "2.9.9"));
		System.out.println(verifyVersion("2.9.1", null, "2.9.0"));
		System.out.println(verifyVersion(null, "2.9.0", "2.9.0"));
		System.out.println(verifyVersion(null, "2.8.9", "2.9.0"));

	}

}
