package com.shangpin.wireless.api.util;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.service.FindManageService;

public class DiscoverActivtyProductUtil {

	private FindManageService findManageService;
	//活动和商品关系容器
	private DataContainer container = DataContainerPool.discoverContainer;
	public void refreshDiscoverCache(){
		initFindManageService();
		container.clear();
		List<FindManage> list = findManageService.findByActivityManage(null);
		if(list != null && list.size() > 0){
			for(FindManage findManage : list){
				String activityId = findManage.getActivityID();
				if(activityId != null && !"".equals(activityId)){
					saveProductsOfActivity(activityId);
				}
			}
		}
	}
	
	private void initFindManageService(){
		ApplicationContext applicationContext = ApplicationContextUtil.get("ac");
		findManageService = (FindManageService) applicationContext.getBean(FindManageService.SERVICE_NAME);
	}
	
	/**
	 * 保存商品和活动的关系   商品ID--> 活动ID,活动ID....
	 * @param activityId
	 */
	private void saveProductsOfActivity(String activityId){
		HashMap map = new HashMap();
		map.put("topicid", activityId);
		String url = Constants.SP_BASE_URL + "SPNewTopicProducts/";
		String data = WebUtil.readContentFromGet(url, map);
		if(data != null){
			JSONObject obj = JSONObject.fromObject(data);
			Object code = obj.getString("code");
			if(code != null && "0".equals(code)){
				JSONObject content = obj.getJSONObject("content");
				if(content != null && !"{}".equals(content.toString())){
					JSONArray array = content.getJSONArray("list");
					if(array != null && !"[]".equals(array.toString()) && array.size() > 0){
						for(int i =0 ; i< array.size(); i++){
							JSONObject activy = array.getJSONObject(i);
							JSONArray arrayProduct = activy.getJSONArray("products");
							if(arrayProduct != null && arrayProduct.size() > 0){
								for(int j =0 ; j < arrayProduct.size(); j++){
									JSONObject product = arrayProduct.getJSONObject(j);
									String productId = product.getString("productid");
									if(productId != null && !"".equals(productId)){
										if(container.containsKey(productId)){
											String topicIds = (String) container.get(productId);
											container.putOrReplace(productId, activityId + "," + topicIds);
										}else{
											container.putOrReplace(productId, activityId);
										}
									}
								}
							}
						}
					}else{
						
					}
				}
			}
		}
	}
}
