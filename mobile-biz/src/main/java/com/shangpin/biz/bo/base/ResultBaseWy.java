package com.shangpin.biz.bo.base;

import java.io.Serializable;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RunProduct;
import com.shangpin.utils.JsonUtil;

public class ResultBaseWy<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private T result;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}


	public static void main(String[] args) {
		String json = "{\"sid\":\"ListFilters-d9c53c86-2a61-41ff-9b37-437c60fde40a\",\"status\":\"0\",\"discription\":\"查询成功\",\"qtime\":\"8\",\"total\":\"144\",\"start\":\"0\",\"end\":\"10\",\"docs\":[{\"productNo\":\"30001157\",\"productName\":\"经销\",\"marketPrice\":\"6000.00\",\"sellPrice\":\"4500.00\",\"platinumPrice\":\"4000.00\",\"diamondPrice\":\"3500.00\",\"limitedPrice\":\"5000.00\",\"productPicFile\":\"20150121130038024531\",\"productModelPicFile\":\"20150121130038024531\",\"erpCategoryNo\":\"A01B01C01D02\",\"brandNo\":\"B0009\",\"brandCnName\":\"芬迪\",\"brandEnName\":\"FENDI\",\"isSupportDiscount\":\"1\",\"availableStock\":\"7\",\"isPromotion\":\"0\",\"postArea\":\"1\",\"prefix\":\"\",\"puffix\":\"\",\"promotionNotice\":\"\"},{\"productNo\":\"30001157\",\"productName\":\"经销\",\"marketPrice\":\"6000.00\",\"sellPrice\":\"4500.00\",\"platinumPrice\":\"4000.00\",\"diamondPrice\":\"3500.00\",\"limitedPrice\":\"5000.00\",\"productPicFile\":\"20150121130038024531\",\"productModelPicFile\":\"20150121130038024531\",\"erpCategoryNo\":\"A01B01C01D02\",\"brandNo\":\"B0009\",\"brandCnName\":\"芬迪\",\"brandEnName\":\"FENDI\",\"isSupportDiscount\":\"1\",\"availableStock\":\"7\",\"isPromotion\":\"0\",\"postArea\":\"1\",\"prefix\":\"\",\"puffix\":\"\",\"promotionNotice\":\"\"}]}";
		RunProduct product = JsonUtil.fromJson(json, RunProduct.class);
		List<Product> products = product.getDocs();
		System.out.println(products.size());
		
		String json1 = "{\"result\":{\"sid\":\"ListFilters-d9c53c86-2a61-41ff-9b37-437c60fde40a\",\"status\":\"0\",\"discription\":\"查询成功\",\"qtime\":\"8\",\"total\":\"144\",\"start\":\"0\",\"end\":\"10\",\"docs\":[{\"productNo\":\"30001157\",\"productName\":\"经销\",\"marketPrice\":\"6000.00\",\"sellPrice\":\"4500.00\",\"platinumPrice\":\"4000.00\",\"diamondPrice\":\"3500.00\",\"limitedPrice\":\"5000.00\",\"productPicFile\":\"20150121130038024531\",\"productModelPicFile\":\"20150121130038024531\",\"erpCategoryNo\":\"A01B01C01D02\",\"brandNo\":\"B0009\",\"brandCnName\":\"芬迪\",\"brandEnName\":\"FENDI\",\"isSupportDiscount\":\"1\",\"availableStock\":\"7\",\"isPromotion\":\"0\",\"postArea\":\"1\",\"prefix\":\"\",\"puffix\":\"\",\"promotionNotice\":\"\"},{\"productNo\":\"30001157\",\"productName\":\"经销\",\"marketPrice\":\"6000.00\",\"sellPrice\":\"4500.00\",\"platinumPrice\":\"4000.00\",\"diamondPrice\":\"3500.00\",\"limitedPrice\":\"5000.00\",\"productPicFile\":\"20150121130038024531\",\"productModelPicFile\":\"20150121130038024531\",\"erpCategoryNo\":\"A01B01C01D02\",\"brandNo\":\"B0009\",\"brandCnName\":\"芬迪\",\"brandEnName\":\"FENDI\",\"isSupportDiscount\":\"1\",\"availableStock\":\"7\",\"isPromotion\":\"0\",\"postArea\":\"1\",\"prefix\":\"\",\"puffix\":\"\",\"promotionNotice\":\"\"}]}}";
		ResultBaseWy<RunProduct> product2  = JsonUtil.fromJson(json1, new TypeToken<ResultBaseWy<RunProduct>>(){});
		System.out.println(product2.getResult().getDocs().size());
	}
}
