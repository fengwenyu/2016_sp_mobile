package com.shangpin.mobileAolai.platform.service;

/**
 * ifc会场逻辑接口，用于获取ifc会场信息相关操作
 * 
 * @author wangfeng
 * @date:2013-10-06
 */
public interface MeetPlaceService {

	public String getMeetPlaceList(String id, String isChange,String typeString) throws Exception;
	
}
