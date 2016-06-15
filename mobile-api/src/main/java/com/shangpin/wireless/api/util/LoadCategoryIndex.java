package com.shangpin.wireless.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.shangpin.wireless.api.api2server.domain.SPGoodsDetailServerResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.NewProductBrand;

import com.shangpin.wireless.api.vo.BrandRecommendVO;
import com.shangpin.wireless.api.vo.NewProductsVO;

public class LoadCategoryIndex {
	

	/**
	 * 读取品类首页中的配置文件并写入缓存
	 * 
	 * @param categoryID
	 *            品类ID
	 * @throws Exception
	 */
	
//	public static void load(String categoryID) throws Exception {
//
//		//从数据库中读取数据
//		ApplicationContext ac = ApplicationContextUtil.get("ac");
//		NewProductBrandReService newProductBrandReService = (NewProductBrandReService) ac.getBean(NewProductBrandReService.SERVICE_NAME);
//		NewProductBrand newProductBrand=newProductBrandReService.findByCategoryID(categoryID,DBType.dataSourceManage.toString());
//		if(newProductBrand!=null){
//			Map<String, String> newProductBrandJsonStr = new HashMap<String, String>();
//			newProductBrandJsonStr=	getNewProductBrandJsonStr(newProductBrand);
//			getNewProducts(newProductBrandJsonStr.get("newProducts"), categoryID);
//			getRecommendBrands(newProductBrandJsonStr.get("recommendBrands"), categoryID);
//		}
//
//	}
	
	@SuppressWarnings("unused")
    private static Map<String,String> getNewProductBrandJsonStr(NewProductBrand newProductBrand){
		
		
		Map<String, String> newProductBrandJsonStr = new HashMap<String, String>();
		
		List<NewProductsVO> newProductList = new ArrayList<NewProductsVO>();

	
		NewProductsVO newProductsFirst = new NewProductsVO();
		newProductsFirst.setProductid(newProductBrand.getFirstNewProductId());
		newProductList.add(newProductsFirst);
		NewProductsVO newProductsSecond = new NewProductsVO();
		newProductsSecond.setProductid(newProductBrand.getSecondNewProductId());
		newProductList.add(newProductsSecond);
		JSONArray jsonArray = JSONArray.fromObject(newProductList); 
		
		
		List<BrandRecommendVO> brandlist = new ArrayList<BrandRecommendVO>();
		BrandRecommendVO brandRecommendVOFirst = new BrandRecommendVO();
		Map<String, String> mapfirst = new HashMap<String, String>();
		mapfirst.put("brandid", newProductBrand.getFirstBrandId());
		mapfirst.put("brandname", newProductBrand.getFirstBrandName());
		mapfirst.put("brandpic", "");
		brandRecommendVOFirst.setBrand(mapfirst);
		String[] brandProductstr = new String[] { newProductBrand.getFirstBrandProductId() };
		brandRecommendVOFirst.setProducts(brandProductstr);
		brandlist.add(brandRecommendVOFirst);
		BrandRecommendVO brandRecommendVOSecond = new BrandRecommendVO();
		Map<String, String> mapsecond = new HashMap<String, String>();
		mapsecond.put("brandid", newProductBrand.getSecondBrandId());
		mapsecond.put("brandname", newProductBrand.getSecondBrandName());
		mapsecond.put("brandpic", "");
		String[] str = new String[] { };
		brandRecommendVOSecond.setBrand(mapsecond);
		brandRecommendVOSecond.setProducts(str);
		brandlist.add(brandRecommendVOSecond);
		BrandRecommendVO brandRecommendVOthree = new BrandRecommendVO();
		Map<String, String> mapthree = new HashMap<String, String>();
		mapthree.put("brandid", newProductBrand.getThreeBrandId());
		mapthree.put("brandname", newProductBrand.getThreeBrandName());
		mapthree.put("brandpic", "");
		brandRecommendVOthree.setBrand(mapthree);
		brandRecommendVOthree.setProducts(str);
		brandlist.add(brandRecommendVOthree);
		BrandRecommendVO brandRecommendVOFourth = new BrandRecommendVO();
		Map<String, String> mapfourth = new HashMap<String, String>();
		mapfourth.put("brandid", newProductBrand.getFourthBrandId());
		mapfourth.put("brandname", newProductBrand.getFourthBrandName());
		mapfourth.put("brandpic", "");
		brandRecommendVOFourth.setBrand(mapfourth);
		brandRecommendVOFourth.setProducts(str);
		brandlist.add(brandRecommendVOFourth);
		BrandRecommendVO brandRecommendVOFifth = new BrandRecommendVO();
		Map<String, String> mapfifth = new HashMap<String, String>();
		mapfifth.put("brandid", newProductBrand.getFifthBrandId());
		mapfifth.put("brandname", newProductBrand.getFifthBrandName());
		mapfifth.put("brandpic", "");
		brandRecommendVOFifth.setBrand(mapfifth);
		brandRecommendVOFifth.setProducts(str);
		brandlist.add(brandRecommendVOFifth);
		BrandRecommendVO brandRecommendVOSixth = new BrandRecommendVO();
		Map<String, String> mapsixth = new HashMap<String, String>();
		mapsixth.put("brandid", newProductBrand.getSixthBrandId());
		mapsixth.put("brandname", newProductBrand.getSixthBrandName());
		mapsixth.put("brandpic", "");
		brandRecommendVOSixth.setBrand(mapsixth);
		brandRecommendVOSixth.setProducts(str);
		brandlist.add(brandRecommendVOSixth);
		BrandRecommendVO brandRecommendVOSeventh = new BrandRecommendVO();
		Map<String, String> mapseventh = new HashMap<String, String>();
		mapseventh.put("brandid", newProductBrand.getSeventhBrandId());
		mapseventh.put("brandname", newProductBrand.getSeventhBrandName());
		mapseventh.put("brandpic", "");
		brandRecommendVOSeventh.setBrand(mapseventh);
		brandRecommendVOSeventh.setProducts(str);
		brandlist.add(brandRecommendVOSeventh);
		
		JSONArray jsonArrayBrand = JSONArray.fromObject(brandlist); 
		
		newProductBrandJsonStr.put("newProducts", jsonArray.toString());
		newProductBrandJsonStr.put("recommendBrands",jsonArrayBrand.toString());
		return newProductBrandJsonStr;
	}
	/**
	 * 获取2个新品商品，并将商品详情数据转换为json对象写入缓存
	 * 
	 * @param jsonStr
	 *            新品商品json字符串
	 * @param categoryid
	 *            品类ID
	 * @return
	 */
//	private static void getNewProducts(String jsonStr, String categoryID) {
//		Map<String, String> map = new HashMap<String, String>();
//		JSONArray jarray = new JSONArray();
//		try {
//			if (StringUtils.isNotEmpty(jsonStr)) {
//				JSONArray array = JSONArray.fromObject(jsonStr);
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject jobj = (JSONObject) array.get(i);
//					// 配置文件中商品id
//					String productid = jobj.getString("productid");
//					map.put("productid", productid);
//					map.put("picw", "{w}");
//					map.put("pich", "{h}");
//					// 获取商品信息，并加入数组中
//					jarray.add(getproduct(map));
//				}
//			}
//		} catch (Exception e) {
//			jarray = new JSONArray();
//			e.printStackTrace();
//		}
//		DataContainerPool.categoryIndexContainer.putOrReplace(categoryID + "_NEW", jarray);
//	}

	/**
	 * 获取推荐品牌列表
	 * 
	 * @param jsonStr
	 * @param categoryid
	 * @return
	 */
//	private static void getRecommendBrands(String jsonStr, String categoryID) {
//		JSONArray array = new JSONArray();
//		try {
//			if (StringUtils.isNotEmpty(jsonStr)) {
//				JSONArray jarray = new JSONArray();
//				Map<String, String> map = new HashMap<String, String>();
//				array = JSONArray.fromObject(jsonStr);
//				if (array.size() > 0) {
//					for (int i = 0; i < array.size(); i++) {
//						JSONObject jobj = (JSONObject) array.get(i);
//						JSONArray prod = (JSONArray) jobj.get("products");
//						jarray.clear();
//						if (prod.size() > 0) {
//							for (int j = 0; j < prod.size(); j++) {
//								String productid = prod.getString(j);
//								map.clear();
//								// 获取商品信息
//								map.put("productid", productid);
//								map.put("picw", "{w}");
//								map.put("pich", "{h}");
//								jarray.add(getproduct(map));
//							}
//							prod.clear();
//							// 重新组装商品信息
//							prod.addAll(jarray);
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			array = new JSONArray();
//			e.printStackTrace();
//		}
//		DataContainerPool.categoryIndexContainer.putOrReplace(categoryID + "_RECOMMEND", array);
//	}
		/**
		 * 通过参数，获取商品简要信息
		 * 
		 * @param map
		 *            productid、userid、picw、pich
		 * @return 商品简要信息（json类型）
		 */
		@SuppressWarnings("unused")
        private static JSONObject getproduct(Map<String, String> map) throws Exception {
			String url = Constants.SP_BASE_URL + "SPProductDetail/";
			SPGoodsDetailServerResponse serverResponse = new SPGoodsDetailServerResponse();
			String data = WebUtil.readContentFromGet(url, map);
			return serverResponse.json2SummaryObj(data);
		}

}
