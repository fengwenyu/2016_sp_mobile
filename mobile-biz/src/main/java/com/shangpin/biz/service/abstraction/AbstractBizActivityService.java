package com.shangpin.biz.service.abstraction;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizActivityService {
	
	@Autowired 
	ShangPinService shangPinService;
	
	public Map<String, Object> aActivityStartRemind(String phoneNum, String actityId, String time) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    try {
	        String json=shangPinService.activityStartRemind(phoneNum,actityId,time);
            ResultBase resultBase = JsonUtil.fromJson(json, ResultBase.class);
            if (Constants.SUCCESS.equals(resultBase.getCode())) {
                map.put("code", Constants.SUCCESS);
            } else {
                map.put("code", Constants.FAILE);
                String msg = resultBase.getMsg();
                if (StringUtil.isNotEmpty(msg)) {
                    map.put("mag", resultBase.getMsg());
                } else {
                    map.put("mag", "关注失败");
                }

            }
          } catch (Exception e) {
              e.printStackTrace();
          }
	    return map;
	 }
	
}
