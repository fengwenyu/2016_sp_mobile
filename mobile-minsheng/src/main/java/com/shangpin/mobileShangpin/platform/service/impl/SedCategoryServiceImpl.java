package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;


import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.SedCategoryService;
import com.shangpin.mobileShangpin.platform.vo.CategoryVo;

@Service("sedCategoryService")
@SuppressWarnings("unchecked")
// @Transactional
public class SedCategoryServiceImpl implements SedCategoryService {

	/**
	 * 二级分类业务实现类，用于首页二级分类相关操作
	 * 
	 * @author wangfeng
	 * @CreatDate 2013-7-29
	 */
	
	public List<CategoryVo> getCategory(String firstCatId,String gender) {
		List<CategoryVo> list=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("categoryid", firstCatId);
		String url = Constants.BASE_SP_URL + "SPSearchChildCategory/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					if (null != contentObj.get("secondcategorys") && !"[]".equals(contentObj.get("secondcategorys").toString())) {
						JSONArray array = (JSONArray) contentObj.get("secondcategorys");
						list = new ArrayList<CategoryVo>();
						for (int i = 0; i < array.size(); i++) {
							CategoryVo categoryVo = new CategoryVo();
							JSONObject categoryVoJsonObj = array.getJSONObject(i);
							categoryVo.setId(categoryVoJsonObj.getString("id"));
							categoryVo.setName(categoryVoJsonObj.getString("name"));													
							list.add(categoryVo);
						}
					}
				}
			}
		}
		return list;
	}

}
