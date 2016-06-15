/**
 * Alipay.com Inc.
 * Copyright (c) 2005-2006 All Rights Reserved.
 */
package com.shangpin.mobileShangpin.common.alipay.util;

import java.io.InputStream;
import java.util.List;

import org.nuxeo.common.xmap.XMap;

/**
 * XMap�����࣬��������xml�ļ�
 * 
 * @author stone.zhangjl
 * @version $Id: XMapUtil.java, v 0.1 2008-8-21 ����10:11:28 stone.zhangjl Exp $
 */
public class XMapUtil {
	private static final XMap xmap;
	static {
		xmap = new XMap();
	}

	/**
	 * ע��Object��
	 * 
	 * @param clazz
	 */
	public static void register(Class<?> clazz) {
		if (clazz != null) {
			xmap.register(clazz);
		}
	}

	/**
	 * ����xml��Object
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static Object load(InputStream is) throws Exception {
		Object obj = null;
		try {
			obj = xmap.load(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return obj;
	}

	/**
	 * Object��XML��
	 * 
	 * @param obj
	 * @param encoding
	 * @param outputsFields
	 * @return
	 * @throws PaygwException
	 */
	public static String asXml(Object obj, String encoding, List<String> outputsFields) throws Exception {
		return xmap.asXmlString(obj, encoding, outputsFields);
	}
}
