package com.shangpin.mobileAolai.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MyElFunction {
	public static String getBase64(String str) throws UnsupportedEncodingException {
		return new String(URLEncoder.encode(str,"UTF-8"));
	}
}
