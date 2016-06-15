package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.DynamicObj;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.Recommend;
import com.shangpin.biz.bo.WorthTitle;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizRecommendService extends AbstractBizCommonService {

	@Autowired
	ShangPinService shangPinService;

	@Autowired
	CommonService commonService;

	public String fromFindPopularityAndWorth(String type, String userId, String pageIndex, String pageSize) {
		String json = shangPinService.findPopularityAndWorth(type, userId, pageIndex, pageSize);
		return json;
	}
	
	protected List<Product> doBase(String type, String userId, String pageIndex, String pageSize) throws Exception {
		String json = shangPinService.findPopularityAndWorth(type, userId, pageIndex, pageSize);
		List<Product> products = null;
		if (StringUtils.isNotEmpty(json)) {
		    ResultObjOne<DynamicObj<Product>> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<DynamicObj<Product>>>(){});
			if (resultObjMapList != null) {
				products = resultObjMapList.getContent().getList();
			}
		}
		return products;
	}
	protected WorthTitle doBaseRecWorth(String type, String userId, String pageIndex, String pageSize) throws Exception {
		WorthTitle worthTitle =  doBaseWorthTitle(type, userId, pageIndex, pageSize);
	
		return worthTitle;
	}
	protected Recommend doRecommend(String type, String userId, String pageIndex, String pageSize) throws Exception {
		Recommend rec = new Recommend();
		List<Product> products = doBase(type, userId, pageIndex, pageSize);
		if (null != products && !products.isEmpty()) {
			if ("1".equals(type)) {
				rec.setType("1");
				rec.setTitle("24小时人气排行榜");
				rec.setList(products);
			} else if ("2".equals(type)) {
				rec.setType("2");
				rec.setTitle("今日值得买");
				rec.setList(products);
			}
		}
		return rec;
	}

	public List<Recommend> doRecommends(String userId) throws Exception {
		List<Recommend> recommends = new ArrayList<Recommend>();
		Recommend rec1 = doRecommend("1", userId, "1", "20");
		Recommend rec2 = doRecommend("2", "", "1", "20");
		if (null != rec1) {
			recommends.add(rec1);
		}
		if (null != rec2) {
			recommends.add(rec2);
		}
		return recommends;
	}

	protected RecProductFor doBaseRecProduct(String type, String userId, String imei, String coord, String ip, String pageIndex, String pageSize) throws Exception {
		String baseData = commonService.recProduct(type, userId, imei, coord, ip, pageIndex, pageSize);
		RecProductFor recProduct = null;
		if (StringUtils.isNotEmpty(baseData)) {
			ResultObjOne<RecProductFor> obj = JSONUtils.toGenericsCollection(baseData, ResultObjOne.class, RecProductFor.class);
			if (obj != null) {
				recProduct = obj.getObj();
				
			}
		}
		return recProduct;
	}
	protected List<Product> doBase(String type, String userId, String shopType,String productId, String pageIndex,String pageSize) throws Exception {
	    String baseData = commonService.recProduct(userId, type, shopType, productId, pageIndex, pageSize);
        List<Product> products = new ArrayList<Product>();
        if (StringUtils.isNotEmpty(baseData)) {
            ResultObjMapList<Product> obj = JSONUtils.toGenericsCollection(baseData, ResultObjMapList.class, Product.class);
            if (obj != null) {
                List<Product> productList = obj.getList("productList");
                if(productList!=null){                    
                    products = obj.getList("productList");    
                }
            }
        }
        return products;
    }

}

