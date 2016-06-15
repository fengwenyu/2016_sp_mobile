package com.shangpin.wireless.api.api2client.domain;


import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.domain.AlPromotionVO;
import com.shangpin.wireless.api.domain.ProductAllCartVO;
import com.shangpin.wireless.api.domain.ProductCartVO;
import com.shangpin.wireless.api.domain.ProductPropVO;
import com.shangpin.wireless.api.domain.PromotionVO;
import com.shangpin.wireless.api.domain.SpPromotionVO;
import com.shangpin.wireless.api.util.StringUtil;

/**
 * 返给客户端的json数据
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */

public class ConfirmProductAPIResponse {

	/**
	 *订单确定页返给客户端的json数据新版本
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-04-24
	 * @Return
	 */
	public String objListJson(ProductServerAllCartVO productServerAllCartVO) {
		JSONObject obj = null;
		JSONArray listArray=new JSONArray();
		if ("0".equals(productServerAllCartVO.getCode())){
			ProductAllCartVO all=productServerAllCartVO.getProductAllCartVO();
			List<SpPromotionVO> spProList=all.getSpPromotionVO();
			if(spProList!=null&&spProList.size()>0){
				List<ProductCartVO> spCart;
				List<ProductPropVO> propList;
				for(int i=0;i<spProList.size();i++){
					spCart=spProList.get(i).getProductCartVO();
					for(int j=0;j<spCart.size();j++){
						obj=new JSONObject();
						obj.put("sku", StringUtil.isNotEmptybyStr(spCart.get(j).getSkuNo()));
						obj.put("brand", StringUtil.isNotEmptybyStr(spCart.get(j).getBrandName()));
						propList=spCart.get(j).getSkuAttrText();
						JSONArray prop = new JSONArray();
						if(propList!=null&&propList.size()>0){
							for(int z=0;z<propList.size();z++){
								JSONObject propObj=new JSONObject();
								propObj.put("name", propList.get(z).getName());
								propObj.put("value", propList.get(z).getValue());
								prop.add(propObj);
							}
						}
						obj.put("prop", prop);
						obj.put("count", StringUtil.isNotEmptybyStr(spCart.get(j).getQuantity()));
						obj.put("price", StringUtil.isNotEmptybyStr(spCart.get(j).getPrice()));
						obj.put("pic", StringUtil.isNotEmptybyStr(spCart.get(j).getImg()));
						obj.put("errorcode", StringUtil.isNotEmptybyStr(spCart.get(j).getMsgType()));
						obj.put("errormsg", StringUtil.isNotEmptybyStr(spCart.get(j).getMsg()));
						obj.put("goodsid", StringUtil.isNotEmptybyStr(spCart.get(j).getProductNo()));
						obj.put("goodsname", StringUtil.isNotEmptybyStr(spCart.get(j).getProductName()));
						obj.put("topicid", StringUtil.isNotEmptybyStr(spCart.get(j).getCategoryNo()));
						obj.put("alcategoryno", StringUtil.isNotEmptybyStr(spCart.get(j).getAlCategoryNo()));						
						obj.put("shoppingcartdetailid", StringUtil.isNotEmptybyStr(spCart.get(j).getShopDetailId()));						
						listArray.add(obj);						
					}
				}				
			}			
			List<AlPromotionVO> alProList=all.getAlPromotionVO();
			if(alProList!=null&&alProList.size()>0){
				List<ProductCartVO> alCart;
				List<ProductPropVO> propList;
				for(int i=0;i<alProList.size();i++){
					alCart=alProList.get(i).getProductCartVO();
					for(int j=0;j<alCart.size();j++){
						obj=new JSONObject();
						obj.put("sku", StringUtil.isNotEmptybyStr(alCart.get(j).getSkuNo()));
						obj.put("brand", StringUtil.isNotEmptybyStr(alCart.get(j).getBrandName()));
						propList=alCart.get(j).getSkuAttrText();
						JSONArray prop = new JSONArray();
						if(propList!=null&&propList.size()>0){
							for(int z=0;z<propList.size();z++){
								JSONObject propObj=new JSONObject();
								propObj.put("name", propList.get(z).getName());
								propObj.put("value", propList.get(z).getValue());
								prop.add(propObj);
							}
						}
						obj.put("prop", prop);
						obj.put("count", StringUtil.isNotEmptybyStr(alCart.get(j).getQuantity()));
						obj.put("price", StringUtil.isNotEmptybyStr(alCart.get(j).getPrice()));
						obj.put("pic", StringUtil.isNotEmptybyStr(alCart.get(j).getImg()));
						obj.put("errorcode", StringUtil.isNotEmptybyStr(alCart.get(j).getMsgType()));
						obj.put("errormsg", StringUtil.isNotEmptybyStr(alCart.get(j).getMsg()));
						obj.put("goodsid", StringUtil.isNotEmptybyStr(alCart.get(j).getProductNo()));
						obj.put("goodsname", StringUtil.isNotEmptybyStr(alCart.get(j).getProductName()));
						obj.put("topicid", StringUtil.isNotEmptybyStr(alCart.get(j).getCategoryNo()));
						obj.put("alcategoryno", StringUtil.isNotEmptybyStr(alCart.get(j).getAlCategoryNo()));						
						obj.put("shoppingcartdetailid", StringUtil.isNotEmptybyStr(alCart.get(j).getShopDetailId()));						
						listArray.add(obj);						
					}
				}
				
			}					
		}
		return listArray.toString();
	}
	
	
	/**
	 * 返给客户端的json数据新版本兼容版本
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-04-24
	 * @Return
	 */
	public String objJson2(ProductServerAllCartVO productServerAllCartVO) {
		JSONObject obj = new JSONObject();
		obj.put("code", productServerAllCartVO.getCode());
		obj.put("msg", productServerAllCartVO.getMsg());
		JSONObject content = new JSONObject();
		if ("0".equals(productServerAllCartVO.getCode())){
			ProductAllCartVO all=productServerAllCartVO.getProductAllCartVO();
			content.put("totalamount",StringUtil.isNotEmptybyStr(all.getTotalPrice()));
			content.put("totalpayamount",StringUtil.isNotEmptybyStr(all.getPracticalPrice()));
			content.put("totalcount", StringUtil.isNotEmptybyStr(all.getTotalQuantity()));
			content.put("totalpromotionamount", StringUtil.isNotEmptybyStr(all.getTotalPromotionPrice()));
			List<SpPromotionVO> spProList=all.getSpPromotionVO();
			JSONArray spcart = new JSONArray();
			if(spProList!=null&&spProList.size()>0){
				PromotionVO spPromotion;
				List<ProductCartVO> spCart;
				List<ProductPropVO> propList;
				JSONObject spProJson;
				JSONObject spproduct;
				for(int i=0;i<=spProList.size();i++){
					spProJson=new JSONObject();
					spPromotion=spProList.get(i).getPromotion();
					spProJson.put("ispromotion",StringUtil.isNotEmptybyStr((spPromotion.getIspromotion())));
					spProJson.put("promotionno", StringUtil.isNotEmptybyStr(spPromotion.getPromotionno()));
					spProJson.put("promotiontype", StringUtil.isNotEmptybyStr(spPromotion.getPromotiontype()));
					spProJson.put("promotioncontent", StringUtil.isNotEmptybyStr(spPromotion.getPromotioncontent()));
					spProJson.put("promotiondesc", StringUtil.isNotEmptybyStr(spPromotion.getPromotiondesc()));
					spProJson.put("promotionurl", StringUtil.isNotEmptybyStr(spPromotion.getPromotionurl()));
					spProJson.put("couponprice", StringUtil.isNotEmptybyStr(spPromotion.getCouponprice()));
					spProJson.put("promotionsign", "0".equals(spPromotion.getCouponprice())?"":"优惠 ￥"+spPromotion.getCouponprice());
					spCart=spProList.get(i).getProductCartVO();
					JSONArray list = new JSONArray();
					for(int j=0;j<spCart.size();j++){
						spproduct=new JSONObject();
						spproduct.put("shortcutid", StringUtil.isNotEmptybyStr(spCart.get(j).getShopDetailId()));
						spproduct.put("productname", StringUtil.isNotEmptybyStr(spCart.get(j).getProductName()));
						spproduct.put("price", StringUtil.isNotEmptybyStr(spCart.get(j).getPromotionPrice()));
						spproduct.put("promotionprice", StringUtil.isNotEmptybyStr(spCart.get(j).getPromotionPrice()));
						spproduct.put("favoriteprice", StringUtil.isNotEmptybyStr(spCart.get(j).getFavoritePrice()));
						spproduct.put("count", StringUtil.isNotEmptybyStr(spCart.get(j).getQuantity()));
						spproduct.put("sku", StringUtil.isNotEmptybyStr(spCart.get(j).getSkuNo()));
						spproduct.put("brandname", StringUtil.isNotEmptybyStr(spCart.get(j).getBrandName()));
						spproduct.put("topicid", StringUtil.isNotEmptybyStr(spCart.get(j).getCategoryNo()));
						spproduct.put("pic", StringUtil.isNotEmptybyStr(spCart.get(j).getImg()));
						spproduct.put("url", StringUtil.isNotEmptybyStr(spCart.get(j).getProductUrl()));
						spproduct.put("productno", StringUtil.isNotEmptybyStr(spCart.get(j).getProductNo()));
						spproduct.put("datetime", StringUtil.isNotEmptybyStr(spCart.get(j).getDateTime()));
						propList=spCart.get(j).getSkuAttrText();
						JSONArray prop = new JSONArray();
						if(propList!=null&&propList.size()>0){
							for(int z=0;z<propList.size();z++){
								JSONObject propObj=new JSONObject();
								propObj.put("name", propList.get(z).getName());
								propObj.put("value", propList.get(z).getValue());
								prop.add(propObj);
							}
						}
						spproduct.put("prop", prop);
						spproduct.put("propstr", StringUtil.isNotEmptybyStr(spCart.get(j).getSkuAttrTextStr()));
						spproduct.put("fromtype", StringUtil.isNotEmptybyStr(spCart.get(j).getIslimitedOutlet()));
						spproduct.put("errorcode", StringUtil.isNotEmptybyStr(spCart.get(j).getMsgType()));
						spproduct.put("errormsg", StringUtil.isNotEmptybyStr(spCart.get(j).getMsg()));
						list.add(spproduct);
						
					}
					spProJson.put("list", list);										
					spcart.add(spProJson);
				}
				
			}
			content.put("spcart", spcart);
			
			
			List<AlPromotionVO> alProList=all.getAlPromotionVO();
			JSONArray alcart = new JSONArray();
			if(alProList!=null&&alProList.size()>0){
				PromotionVO alPromotion;
				List<ProductCartVO> alCart;
				List<ProductPropVO> propList;
				JSONObject alProJson;
				JSONObject alproduct;
				for(int i=0;i<=alProList.size();i++){
					alProJson=new JSONObject();
					alPromotion=alProList.get(i).getPromotion();
					alProJson.put("ispromotion",StringUtil.isNotEmptybyStr(alPromotion.getIspromotion()));
					alProJson.put("promotionno",StringUtil.isNotEmptybyStr( alPromotion.getPromotionno()));
					alProJson.put("promotiontype",StringUtil.isNotEmptybyStr( alPromotion.getPromotiontype()));
					alProJson.put("promotioncontent", StringUtil.isNotEmptybyStr(alPromotion.getPromotioncontent()));
					alProJson.put("promotiondesc",StringUtil.isNotEmptybyStr( alPromotion.getPromotiondesc()));
					alProJson.put("promotionurl",StringUtil.isNotEmptybyStr( alPromotion.getPromotionurl()));
					alProJson.put("couponprice", StringUtil.isNotEmptybyStr(alPromotion.getCouponprice()));
					alProJson.put("promotionsign", "0".equals(alPromotion.getCouponprice())?"":"优惠 ￥"+alPromotion.getCouponprice());
					alCart=alProList.get(i).getProductCartVO();
					JSONArray list = new JSONArray();
					for(int j=0;j<alCart.size();j++){
						alproduct=new JSONObject();
						alproduct.put("shortcutid", StringUtil.isNotEmptybyStr(alCart.get(j).getShopDetailId()));
						alproduct.put("productname", StringUtil.isNotEmptybyStr(alCart.get(j).getProductName()));
						alproduct.put("price", StringUtil.isNotEmptybyStr(alCart.get(j).getPromotionPrice()));
						alproduct.put("promotionprice", StringUtil.isNotEmptybyStr(alCart.get(j).getPromotionPrice()));
						alproduct.put("favoriteprice", StringUtil.isNotEmptybyStr(alCart.get(j).getFavoritePrice()));
						alproduct.put("count", StringUtil.isNotEmptybyStr(alCart.get(j).getQuantity()));
						alproduct.put("sku",StringUtil.isNotEmptybyStr( alCart.get(j).getSkuNo()));
						alproduct.put("brandname", StringUtil.isNotEmptybyStr(alCart.get(j).getBrandName()));
						alproduct.put("topicid",StringUtil.isNotEmptybyStr( alCart.get(j).getCategoryNo()));
						alproduct.put("pic", StringUtil.isNotEmptybyStr(alCart.get(j).getImg()));
						alproduct.put("url", StringUtil.isNotEmptybyStr(alCart.get(j).getProductUrl()));
						alproduct.put("productno",StringUtil.isNotEmptybyStr( alCart.get(j).getProductNo()));
						alproduct.put("datetime", StringUtil.isNotEmptybyStr(alCart.get(j).getDateTime()));
						propList=alCart.get(j).getSkuAttrText();
						JSONArray prop = new JSONArray();
						if(propList!=null&&propList.size()>0){
							for(int z=0;z<propList.size();z++){
								JSONObject propObj=new JSONObject();
								propObj.put("name", propList.get(z).getName());
								propObj.put("value", propList.get(z).getValue());
								prop.add(propObj);
							}
						}
						alproduct.put("prop", prop);
						alproduct.put("propstr", StringUtil.isNotEmptybyStr(alCart.get(j).getSkuAttrTextStr()));
						alproduct.put("fromtype", StringUtil.isNotEmptybyStr(alCart.get(j).getIslimitedOutlet()));
						alproduct.put("errorcode", StringUtil.isNotEmptybyStr(alCart.get(j).getMsgType()));
						alproduct.put("errormsg", StringUtil.isNotEmptybyStr(alCart.get(j).getMsg()));
						list.add(alproduct);
						
					}
					alProJson.put("list", list);										
					alcart.add(alProJson);
				}
				
			}
			content.put("alcart", spcart);
					
		}
		return obj.toString();
	}
	
	
}
