package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.shangpin.biz.bo.ActivityHead;
import com.shangpin.biz.bo.ActivityIndex;
import com.shangpin.biz.bo.BrandActivityHead;
import com.shangpin.biz.bo.SearchResult;
import com.shangpin.biz.bo.SearchSubjectFloor;
import com.shangpin.biz.bo.base.ResultBase;

/**
 * 活动首页
 * 
 * @author wangfeng
 *
 */
public interface ASPBizActivityService {
    
    String queryActivityIndex(String userid, String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId,
            String order, String gender, String userLv, String postArea, String imei) throws Exception;

	ActivityIndex searchActivityIndex(String userId, String subjectNo, String start, String end, String tagId, String brandId, String price, String colorId, String size, String categoyId, String order, String gender, String userLv, String postArea, String imei) throws Exception;

	SearchResult searchActivityProduct(String subjectNO, String start, String productListEnd, String tagId, String brandNo, String price, String color, String size, String categoryNo, String order, String gender, String userLv, String postArea, String imei,ActivityHead activity);
	
	String queryActivityIndexNew(String userid, String subjectNo, String start, String end, String tagId, String order,String userLv, String filters, String imei) throws Exception;

	String queryActivityIndexNew(String userid, String subjectNo, String start, String end, String tagId, String order,String userLv, String filters, String originalFilters, String dynamicFilters, String imei, String version) throws Exception;
	BrandActivityHead getBrandActivityHead(String userId,String subjectNo);
	
	Map<String, Object> getSubjectFloorInfo(String topicId,ActivityHead activityHead);
	
	ActivityIndex getSearchActivityIndexWithoutSearch(String userId, String topicId);
	/**
	 * 关注活动提醒时间
	 * @param phoneNum
	 * @param verifyCode
	 * @param time
	 * @return
	 */
	 Map<String, Object> activityStartRemind(String phoneNum,String actityId,String time);
}
