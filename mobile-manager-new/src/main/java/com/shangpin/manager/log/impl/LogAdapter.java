/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.log.LogAdapter.java
 * Class:			LogTemplateAdapter
 * Date:			2013-5-3
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.manager.log.impl;

import java.util.Map;

import com.shangpin.core.log.LogAPI;
import com.shangpin.core.log.LogLevel;
import com.google.common.collect.Maps;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * @version  2.1.0
 * @since   2013-5-3 下午5:21:07 
 */

public class LogAdapter implements LogAPI {

	/**   
	 * @param message
	 * @param logLevel  
	 * @see com.shangpin.core.log.LogAPI#log(java.lang.String, com.shangpin.core.log.LogLevel)  
	 */
	@Override
	public void log(String message, LogLevel logLevel) {
		log(message, null, logLevel);
	}

	/**   
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.shangpin.core.log.LogAPI#log(java.lang.String, java.lang.Object[], com.shangpin.core.log.LogLevel)  
	 */
	@Override
	public void log(String message, Object[] objects, LogLevel logLevel) {
		
	}
	
	/**   
	 * @return  
	 * @see com.shangpin.core.log.LogAPI#getRootLogLevel()  
	 */
	@Override
	public LogLevel getRootLogLevel() {
		return LogLevel.ERROR;
	}

	/**   
	 * @return  
	 * @see com.shangpin.core.log.LogAPI#getCustomLogLevel()  
	 */
	@Override
	public Map<String, LogLevel> getCustomLogLevel() {
		return Maps.newHashMap();
	}
}
