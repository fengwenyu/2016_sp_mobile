package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.MeetPlaceService;




/**
 * ifc会场接口实现类，用于获取ifc会场信息相关操作
 * 
 * @author wangfeng
 * @CreatDate 2013-10-07
 */
@Service("meetPlaceService")
public class MeetPlaceServiceImpl implements MeetPlaceService {
	
	@Override
	public String getMeetPlaceList(String id) throws Exception {

		String url = Constants.BASE_SP_URL + "hall/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();		
			map.put("id", id);
		String json = null;
		try {
			// 获取会场json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		return json;
	}
}