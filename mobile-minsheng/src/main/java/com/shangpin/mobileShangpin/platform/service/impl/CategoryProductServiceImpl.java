package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.CategoryProductService;
import com.shangpin.mobileShangpin.platform.vo.SPCategoryVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;

/**
 * 尚品分类和分类下的商品业务逻辑接口，用于分类和分类下的商品相关操作
 * 
 * @author liling
 * @date:2013-07-29
 */
@Service("categoryProductService")
@SuppressWarnings({ "unchecked" })
public class CategoryProductServiceImpl implements CategoryProductService {
	
	/**
	 * 尚品分类商品列表
	 * 
	 * @param userid
	 *            用户id
	 * @param categoryid
	 *            分类id
	 * @param gender
	 *            性别 0女1男
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return List<SPMerchandiseVO> 尚品list传输对象
	 */
	@Override
	public List<SPMerchandiseVO> getNewProducts(String userid, String categoryid, String gender, Integer pageIndex, Integer pageSize) {
		List<SPMerchandiseVO> list = null;
		if (StringUtils.isEmpty(categoryid)) {
			// res.put("spCategoryList", "");
			return list;
		}
		// 组装访问参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pagesize", pageSize == null ? "" : pageSize + "");
		map.put("filtersold", "0");// 包含售罄的商品
		map.put("gender", StringUtils.isEmpty(gender) ? "0" : gender);
		map.put("userid", StringUtils.isEmpty(userid) ? "" : userid);
		map.put("categoryid", categoryid);// 20120810340
		String url = Constants.BASE_SP_URL + "SPProductsInCateAndBrand/";
		String data = null;
		try {
			// 获取尚品分类下的尚品json格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				JSONArray array = obj.getJSONArray("list");
				if (array.size() > 0) {
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = JSONArray.toList(array, new SPMerchandiseVO(), new JsonConfig());
				}
			}
		}
		return list;
	}

	/**
	 * 尚品分类列表
	 * 
	 * @param categoryid
	 *            分类id
	 * @param gender
	 *            性别 0女1男
	 * @Return  Map<String, Object> 
	 */
	@Override
	public Map<String, Object> getChildCategoryMap(String categoryid, String gender) {
		List<SPCategoryVO> list = new ArrayList<SPCategoryVO>();
		Map<String, Object> res = new HashMap<String, Object>();
		// 组装访问参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", StringUtils.isEmpty(gender) ? "0" : gender);//性别 0代表女，1代表男
		map.put("categoryid", categoryid);// 分类id
		String url = Constants.BASE_SP_URL + "SPSearchChildCategory/";
		String data = null;
		try {
			// 获取尚品分类json格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
/*				res.put("categoryname", obj.get("name"));
				res.put("categoryid", obj.get("cateid"));*/
					JSONArray array = obj.getJSONArray("secondcategorys");
					for (int i = 0; i < array.size(); i++) {
						JSONObject oj = (JSONObject) array.get(i);
						if (!oj.isEmpty()) {
							SPCategoryVO spCategory = new SPCategoryVO();
							spCategory.setId(oj.get("id").toString());
							spCategory.setName(oj.get("name").toString());
							/*if (oj.size() == 3) {
								JSONArray arr = oj.getJSONArray("thirdcategorys");
								if (arr != null) {
									for (int k = 0; k < arr.size(); k++) {
										SPCategoryVO spCategoryVO = new SPCategoryVO();
										JSONObject ogj = (JSONObject) arr.get(k);
										spCategoryVO.setId(ogj.get("id").toString());
										spCategoryVO.setName(ogj.get("name").toString());
										list.add(spCategoryVO);
									}
								}
							}*/
							list.add(spCategory);
						}
					}
					res.put("list", list);
			}
		}
		return res;
	}

	
}
