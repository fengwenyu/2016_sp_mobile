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
import com.shangpin.mobileShangpin.platform.service.BrandService;
import com.shangpin.mobileShangpin.platform.vo.BrandVo;
import com.shangpin.mobileShangpin.platform.vo.SPBrandVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;

import com.shangpin.mobileShangpin.platform.vo.SimpleBrandsVO;

@Service("brandService")
public class BrandServiceImpl implements BrandService {
	@SuppressWarnings("unchecked")
	/**
	 * 尚品品牌列表
	 * 
	 * @param gender
	 *            性别 0女1男
	 * @Return List<SPBrandVO> 尚品品牌传输对象
	 */
	@Override
	public List<SPBrandVO> getSPBrands(String gender) {
		List<SPBrandVO> list = new ArrayList<SPBrandVO>();
		// 组装访问参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", StringUtils.isEmpty(gender) ? "0" : gender);
		String url = Constants.BASE_SP_URL + "SPBrands/";
		String data = null;
		try {
			// 获取尚品品牌json格式字符串
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
				if (array != null) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject brandsObj = (JSONObject) array.get(i);
						JSONArray brandsArray = brandsObj.getJSONArray("brands");
						SPBrandVO spBrandVO = new SPBrandVO();
						spBrandVO.setCapital(brandsObj.getString("capital"));
						spBrandVO.setBrands(JSONArray.toList(brandsArray, new SimpleBrandsVO(), new JsonConfig()));
						list.add(spBrandVO);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 尚品分类商品列表
	 * 
	 * @param userid
	 *            用户id
	 * @param brandid
	 *            品牌id
	 * @param gender
	 *            性别 0女1男
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return List<SPMerchandiseVO> 尚品list传输对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SPMerchandiseVO> SPProductsBrand(String userid,String categoryid, String brandid, String gender, Integer pageIndex, Integer pageSize) {
		List<SPMerchandiseVO> list = null;
		// 组装访问参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pagesize", pageSize == null ? "" : pageSize + "");
		map.put("filtersold", "0");// 包含售罄的商品
		map.put("gender", StringUtils.isEmpty(gender) ? "0" : gender);
		map.put("userid", StringUtils.isEmpty(userid) ? "" : userid);
		map.put("brandid", brandid);
		map.put("categoryid", categoryid);
		String url = Constants.BASE_SP_URL + "SPProductsInCateAndBrand/";
		String data = null;
		try {
			// 获取尚品品牌下的商品json格式字符串
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
	public List<BrandVo> getBrand(String categoryId,String gender) {
		List<BrandVo> list=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("categoryid", categoryId);
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		String url = Constants.BASE_SP_URL + "SPSearchMoreBrands/";
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
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray array = (JSONArray) contentObj.get("list");
							list = new ArrayList<BrandVo>();							
							for (int i = 0; i < array.size(); i++) {
								if(list.size()>=3) break;
								JSONObject brandVoJsonObj = array.getJSONObject(i);
								if (null != brandVoJsonObj.get("brands") && !"[]".equals(brandVoJsonObj.get("brands").toString())) {
									JSONArray arrayBrand=(JSONArray)brandVoJsonObj.get("brands");
									for (int j = 0; j < arrayBrand.size(); j++) {
										BrandVo brandVo=new BrandVo();
										JSONObject brandsJsonObj = arrayBrand.getJSONObject(j);
										brandVo.setId(brandsJsonObj.getString("id"));
										brandVo.setName(brandsJsonObj.getString("name"));													
										list.add(brandVo);
										break;
									}
									
								}						
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<BrandVo> getBrand(String gender) throws Exception {
		List<BrandVo> list=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		String url = Constants.BASE_SP_URL + "SPBrands/";
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
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray array = (JSONArray) contentObj.get("list");
							list = new ArrayList<BrandVo>();
							
							for (int i = 0; i < array.size(); i++) {
								JSONObject brandVoJsonObj = array.getJSONObject(i);
								if (null != brandVoJsonObj.get("brands") && !"[]".equals(brandVoJsonObj.get("brands").toString())) {
									JSONArray arrayBrand=(JSONArray)brandVoJsonObj.get("brands");
									for (int j = 0; j < arrayBrand.size(); j++) {
										BrandVo brandVo = new BrandVo();
										JSONObject brandsJsonObj = arrayBrand.getJSONObject(j);
										brandVo.setId(brandsJsonObj.getString("id"));
										brandVo.setName(brandsJsonObj.getString("name"));													
										list.add(brandVo);
									}
									
								}						
						}
					}
				}
			}
		}
		return list;
	}
}