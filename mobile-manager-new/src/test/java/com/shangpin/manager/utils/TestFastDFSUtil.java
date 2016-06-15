/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		TestFastDFSUtil.java 
 * Date:			2013-12-26 
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
package com.shangpin.manager.utils;


import org.csource.common.NameValuePair;
import org.junit.Assert;
import org.junit.Test;

import com.shangpin.utils.FastDFSUtil;

/**
 * 
 * @author <a href="mailto:sundful@gmail.com">sundful</a>
 * @version 2.0.0
 * @since 2013-12-26 下午6:21:19
 */
public class TestFastDFSUtil {

	@Test
	public void test() {
		FastDFSUtil util = FastDFSUtil.getInstance();
		String fileName = "Oliver Twist";
		String fileExtName = "ext";
		String fileLength = "102499";
		NameValuePair[] mataList = util.genMetaList(fileName, fileExtName,
				fileLength);
		for (NameValuePair pair : mataList) {
			Assert.assertNotNull(pair.getName());
			Assert.assertNotNull(pair.getValue());
		}
	}
}
