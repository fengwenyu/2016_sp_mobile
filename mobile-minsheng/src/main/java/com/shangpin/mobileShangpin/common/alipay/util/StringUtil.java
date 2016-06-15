/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2006 All Rights Reserved.
 */
package com.shangpin.mobileShangpin.common.alipay.util;

/**
 * �ַ���Ĺ�����
 * 
 * @author stone.zhangjl
 * @version $Id: StringUtil.java, v 0.1 2008-8-21 ����10:47:41 stone.zhangjl Exp $
 */
public class StringUtil {
	/** ���ַ� */
	public static final String EMPTY_STRING = "";

	/**
	 * �Ƚ������ַ���Сд���У���
	 * 
	 * <pre>
	 * StringUtil.equals(null, null)   = true
	 * StringUtil.equals(null, "abc")  = false
	 * StringUtil.equals("abc", null)  = false
	 * StringUtil.equals("abc", "abc") = true
	 * StringUtil.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @param str1
	 *            Ҫ�Ƚϵ��ַ�1
	 * @param str2
	 *            Ҫ�Ƚϵ��ַ�2
	 * 
	 * @return ��������ַ���ͬ�����߶���<code>null</code>���򷵻�<code>true</code>
	 */
	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	/**
	 * �Ƚ������ַ���Сд�����У���
	 * 
	 * <pre>
	 * StringUtil.equalsIgnoreCase(null, null)   = true
	 * StringUtil.equalsIgnoreCase(null, "abc")  = false
	 * StringUtil.equalsIgnoreCase("abc", null)  = false
	 * StringUtil.equalsIgnoreCase("abc", "abc") = true
	 * StringUtil.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @param str1
	 *            Ҫ�Ƚϵ��ַ�1
	 * @param str2
	 *            Ҫ�Ƚϵ��ַ�2
	 * 
	 * @return ��������ַ���ͬ�����߶���<code>null</code>���򷵻�<code>true</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equalsIgnoreCase(str2);
	}

	/**
	 * ����ַ��Ƿ��ǿհף�<code>null</code>�����ַ�<code>""</code>��ֻ�пհ��ַ�
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            Ҫ�����ַ�
	 * 
	 * @return ���Ϊ�հ�, �򷵻�<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����ַ��Ƿ��ǿհף�<code>null</code>�����ַ�<code>""</code>��ֻ�пհ��ַ�
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = false
	 * StringUtil.isBlank("")        = false
	 * StringUtil.isBlank(" ")       = false
	 * StringUtil.isBlank("bob")     = true
	 * StringUtil.isBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            Ҫ�����ַ�
	 * 
	 * @return ���Ϊ�հ�, �򷵻�<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ����ַ��Ƿ�Ϊ<code>null</code>����ַ�<code>""</code>��
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 *            Ҫ�����ַ�
	 * 
	 * @return ���Ϊ��, �򷵻�<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	/**
	 * ����ַ��Ƿ���<code>null</code>�Ϳ��ַ�<code>""</code>��
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = false
	 * StringUtil.isEmpty("")        = false
	 * StringUtil.isEmpty(" ")       = true
	 * StringUtil.isEmpty("bob")     = true
	 * StringUtil.isEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            Ҫ�����ַ�
	 * 
	 * @return ���Ϊ��, �򷵻�<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	/**
	 * ���ַ��в���ָ���ַ������ص�һ��ƥ�������ֵ������ַ�Ϊ<code>null</code>��δ�ҵ����򷵻�<code>-1</code>��
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf("", "")           = 0
	 * StringUtil.indexOf("aabaabaa", "a")  = 0
	 * StringUtil.indexOf("aabaabaa", "b")  = 2
	 * StringUtil.indexOf("aabaabaa", "ab") = 1
	 * StringUtil.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 * 
	 * @param str
	 *            Ҫɨ����ַ�
	 * @param searchStr
	 *            Ҫ���ҵ��ַ�
	 * 
	 * @return ��һ��ƥ�������ֵ������ַ�Ϊ<code>null</code>��δ�ҵ����򷵻�<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		return str.indexOf(searchStr);
	}

	/**
	 * ���ַ��в���ָ���ַ������ص�һ��ƥ�������ֵ������ַ�Ϊ<code>null</code>��δ�ҵ����򷵻�<code>-1</code>��
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf(*, null, *)          = -1
	 * StringUtil.indexOf("", "", 0)           = 0
	 * StringUtil.indexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.indexOf("aabaabaa", "b", 0)  = 2
	 * StringUtil.indexOf("aabaabaa", "ab", 0) = 1
	 * StringUtil.indexOf("aabaabaa", "b", 3)  = 5
	 * StringUtil.indexOf("aabaabaa", "b", 9)  = -1
	 * StringUtil.indexOf("aabaabaa", "b", -1) = 2
	 * StringUtil.indexOf("aabaabaa", "", 2)   = 2
	 * StringUtil.indexOf("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 *            Ҫɨ����ַ�
	 * @param searchStr
	 *            Ҫ���ҵ��ַ�
	 * @param startPos
	 *            ��ʼ����������ֵ�����С��0������0
	 * 
	 * @return ��һ��ƥ�������ֵ������ַ�Ϊ<code>null</code>��δ�ҵ����򷵻�<code>-1</code>
	 */
	public static int indexOf(String str, String searchStr, int startPos) {
		if ((str == null) || (searchStr == null)) {
			return -1;
		}
		// JDK1.3�����°汾��bug��������ȷ������������
		if ((searchStr.length() == 0) && (startPos >= str.length())) {
			return str.length();
		}
		return str.indexOf(searchStr, startPos);
	}

	/**
	 * ȡָ���ַ���Ӵ���
	 * 
	 * <p>
	 * �����������β����ʼ���㡣����ַ�Ϊ<code>null</code>���򷵻�<code>null</code>��
	 * 
	 * <pre>
	 * StringUtil.substring(null, *, *)    = null
	 * StringUtil.substring("", * ,  *)    = "";
	 * StringUtil.substring("abc", 0, 2)   = "ab"
	 * StringUtil.substring("abc", 2, 0)   = ""
	 * StringUtil.substring("abc", 2, 4)   = "c"
	 * StringUtil.substring("abc", 4, 6)   = ""
	 * StringUtil.substring("abc", 2, 2)   = ""
	 * StringUtil.substring("abc", -2, -1) = "b"
	 * StringUtil.substring("abc", -4, 2)  = "ab"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            �ַ�
	 * @param start
	 *            ��ʼ�������Ϊ�����ʾ��β������
	 * @param end
	 *            ����������������Ϊ�����ʾ��β������
	 * 
	 * @return �Ӵ������ԭʼ��Ϊ<code>null</code>���򷵻�<code>null</code>
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}
		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}
		if (end > str.length()) {
			end = str.length();
		}
		if (start > end) {
			return EMPTY_STRING;
		}
		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}
		return str.substring(start, end);
	}

	/**
	 * ����ַ����Ƿ��ָ�����ַ�����ַ�Ϊ<code>null</code>��������<code>false</code>��
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)     = false
	 * StringUtil.contains(*, null)     = false
	 * StringUtil.contains("", "")      = true
	 * StringUtil.contains("abc", "")   = true
	 * StringUtil.contains("abc", "a")  = true
	 * StringUtil.contains("abc", "z")  = false
	 * </pre>
	 * 
	 * @param str
	 *            Ҫɨ����ַ�
	 * @param searchStr
	 *            Ҫ���ҵ��ַ�
	 * 
	 * @return ����ҵ����򷵻�<code>true</code>
	 */
	public static boolean contains(String str, String searchStr) {
		if ((str == null) || (searchStr == null)) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode digits. A decimal point is not a unicode digit and returns false.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNumeric(null)   = false
	 * StringUtils.isNumeric("")     = true
	 * StringUtils.isNumeric("  ")   = false
	 * StringUtils.isNumeric("123")  = true
	 * StringUtils.isNumeric("12 3") = false
	 * StringUtils.isNumeric("ab2c") = false
	 * StringUtils.isNumeric("12-3") = false
	 * StringUtils.isNumeric("12.3") = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains digits, and is non-null
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
	
	public static final String getTagContent(String wholeContent, String startTag, String endTag) {
		final int startIndex = wholeContent.indexOf(startTag);
		if (startIndex < 0)
			return null;
		final int endIndex = wholeContent.indexOf(endTag);
		if (endIndex <= 0 || endIndex < startIndex + startTag.length())
			return null;
		return wholeContent.substring(startIndex + startTag.length(), endIndex);
	}
}
