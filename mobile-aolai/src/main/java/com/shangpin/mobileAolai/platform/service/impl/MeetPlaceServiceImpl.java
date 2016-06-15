package com.shangpin.mobileAolai.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.CommonService;
import com.shangpin.mobileAolai.platform.service.MeetPlaceService;



/**
 * ifc会场接口实现类，用于获取ifc会场信息相关操作
 * 
 * @author wangfeng
 * @CreatDate 2013-10-07
 */
@Service("meetPlaceService")
public class MeetPlaceServiceImpl implements MeetPlaceService {
	
    @Autowired
    private CommonService commonService; 
	@Override
	public String getMeetPlaceList(String id, String isChange,String type) throws Exception {
		String json = null;
		try {
			// 获取会场json格式字符串
			json = commonService.findHallContent(id,isChange,type);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		return json;
	}

}