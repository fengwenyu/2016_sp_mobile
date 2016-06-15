package com.shangpin.wireless.api.api2server.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.AlPromotionVO;
import com.shangpin.wireless.api.domain.ProductAllCartVO;
import com.shangpin.wireless.api.domain.ProductCartVO;
import com.shangpin.wireless.api.domain.ProductPropVO;
import com.shangpin.wireless.api.domain.PromotionVO;
import com.shangpin.wireless.api.domain.SpPromotionVO;
import com.shangpin.wireless.api.util.StringUtil;

public class ProductServerAllCartVO {

	private String code;
	private String msg;
	private ProductAllCartVO productAllCartVO;

	/**
	 * 返给客户端的json数据
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-04-24
	 * @Return
	 */
	public ProductServerAllCartVO jsonObj(String json) {
		JSONObject obj = JSONObject.fromObject(json);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if ("0".equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			productAllCartVO = new ProductAllCartVO();
			if (obj.isEmpty()) {
				return this;
			}
			productAllCartVO.setTotalPrice(obj.getString("TotalPrice"));
			productAllCartVO.setPracticalPrice(String.valueOf(Double.parseDouble(obj.getString("TotalPrice")) - Double.parseDouble(obj.getString("TotalPromotionPrice"))));
			productAllCartVO.setTotalQuantity(obj.getString("TotalQuantity"));
			productAllCartVO.setTotalPromotionPrice(obj.getString("TotalPromotionPrice"));
			if (!"null".equals(obj.getString("SpList"))) {
				JSONArray spArray = obj.getJSONArray("SpList");
				List<SpPromotionVO> spPromotionVOList = new ArrayList<SpPromotionVO>();
				if (spArray != null && spArray.size() > 0) {
					SpPromotionVO spPromotionVO;
					for (int i = 0; i < spArray.size(); i++) {
						spPromotionVO = new SpPromotionVO();
						PromotionVO promotionVO = new PromotionVO();
						List<ProductCartVO> spProuctCartVOList = new ArrayList<ProductCartVO>();
						JSONObject promotionObj = spArray.getJSONObject(i);
						promotionVO.setIspromotion(promotionObj.getString("IsPromotion"));
						promotionVO.setPromotionno(promotionObj.getString("PromotionNo"));
						promotionVO.setPromotiontype(promotionObj.getString("PromotionType"));
						promotionVO.setPromotioncontent(promotionObj.getString("PromotionContent"));
						promotionVO.setPromotiondesc(promotionObj.getString("PromotionDesc"));
						promotionVO.setPromotionurl(promotionObj.getString("PromotionUrl"));
						promotionVO.setCouponprice(promotionObj.getString("CouponPrice"));
						spPromotionVO.setPromotion(promotionVO);
						JSONArray spProductsArray = promotionObj.getJSONArray("CartItemList");
						if (spProductsArray != null && spProductsArray.size() > 0) {
							ProductCartVO productCartVO;
							List<ProductPropVO> productPropVOList;
							ProductPropVO productPropVO;
							for (int j = 0; j < spProductsArray.size(); j++) {
								productCartVO = new ProductCartVO();
								JSONObject productObj = spProductsArray.getJSONObject(j);
								productCartVO.setShopDetailId(productObj.getString("ShoppingCartDetailId"));
								productCartVO.setProductName(productObj.getString("ProductName"));
								productCartVO.setPrice(StringUtil.cutOffDecimal(productObj.getString("Price")));
								productCartVO.setPriceTitle(productObj.getString("priceTitle"));
	                            productCartVO.setQuantityTitle("数量");
								productCartVO.setPromotionPrice(productObj.getString("PromotionPrice"));
								productCartVO.setTotalQuantityAmount(productObj.getString("TotalAmount"));
								productCartVO.setFavoritePrice(productObj.getString("FavoritePrice"));
								productCartVO.setQuantity(productObj.getString("Quantity"));
								productCartVO.setBrandName(productObj.getString("BrandEnName"));
								productCartVO.setProductUrl(productObj.getString("ProductUrl"));
								productCartVO.setImg(productObj.getString("MobileImg"));
								productCartVO.setProductNo(productObj.getString("ProductNo"));
								productCartVO.setSkuNo(productObj.getString("SkuNo"));
								productCartVO.setCategoryNo(productObj.getString("CategoryNo"));
								productCartVO.setAlCategoryNo(productObj.getString("AolaiSubjectNo"));
								productCartVO.setDateTime(productObj.getString("DateAdd"));
								productCartVO.setIslimitedOutlet(productObj.getString("IsLimitedOutlet"));
								productCartVO.setMsgType(productObj.getString("MsgType"));
								productCartVO.setMsg(productObj.getString("Msg"));
								String propStr = productObj.getString("MobileSkuAttrText");
								productCartVO.setSkuAttrTextStr(propStr);
								if (propStr != null && !"".equals(propStr)) {
									productPropVOList = new ArrayList<ProductPropVO>();
									String[] propArray = propStr.split("\\|");
									if (propArray != null && propArray.length > 0) {
										productPropVOList = new ArrayList<ProductPropVO>();
										for (int z = 0; z < propArray.length; z++) {
											productPropVO = new ProductPropVO();
											if(propArray[z].indexOf(":")>-1){
											    productPropVO.setName(propArray[z].substring(0, propArray[z].indexOf(":")));
	                                            productPropVO.setValue(propArray[z].substring(propArray[z].indexOf(":") + 1));
	                                        }else{
	                                            productPropVO.setName(propArray[z]);
	                                            productPropVO.setValue("");
	                                        }
											
											productPropVOList.add(productPropVO);
										}
										productCartVO.setSkuAttrText(productPropVOList);
									}
								}
								spProuctCartVOList.add(productCartVO);
							}
						}
						spPromotionVO.setProductCartVO(spProuctCartVOList);
						spPromotionVOList.add(spPromotionVO);
					}
					productAllCartVO.setSpPromotionVO(spPromotionVOList);
				}
			}
			if (!"null".equals(obj.getString("AlList"))) {
				JSONArray alArray = obj.getJSONArray("AlList");
				List<AlPromotionVO> alPromotionVOList = new ArrayList<AlPromotionVO>();
				if (alArray != null && alArray.size() > 0) {
					AlPromotionVO alPromotionVO;
					for (int i = 0; i < alArray.size(); i++) {
						alPromotionVO = new AlPromotionVO();
						PromotionVO promotionVO = new PromotionVO();
						List<ProductCartVO> alProuctCartVOList = new ArrayList<ProductCartVO>();
						JSONObject promotionObj = alArray.getJSONObject(i);
						promotionVO.setIspromotion(promotionObj.getString("IsPromotion"));
						promotionVO.setPromotionno(promotionObj.getString("PromotionNo"));
						promotionVO.setPromotiontype(promotionObj.getString("PromotionType"));
						promotionVO.setPromotioncontent(promotionObj.getString("PromotionContent"));
						promotionVO.setPromotiondesc(promotionObj.getString("PromotionDesc"));
						promotionVO.setPromotionurl(promotionObj.getString("PromotionUrl"));
						promotionVO.setCouponprice(promotionObj.getString("CouponPrice"));
						alPromotionVO.setPromotion(promotionVO);
						JSONArray alProductsArray = promotionObj.getJSONArray("CartItemList");
						if (alProductsArray != null && alProductsArray.size() > 0) {
							ProductCartVO productCartVO;
							List<ProductPropVO> productPropVOList;
							ProductPropVO productPropVO;
							for (int j = 0; j < alProductsArray.size(); j++) {
								productCartVO = new ProductCartVO();
								JSONObject productObj = alProductsArray.getJSONObject(j);
								productCartVO.setShopDetailId(productObj.getString("ShoppingCartDetailId"));
								productCartVO.setProductName(productObj.getString("ProductName"));
								productCartVO.setPrice(StringUtil.cutOffDecimal(productObj.getString("Price")));
								productCartVO.setPriceTitle(productObj.getString("priceTitle"));
	                            productCartVO.setQuantityTitle("数量");
								productCartVO.setPromotionPrice(productObj.getString("PromotionPrice"));
								productCartVO.setTotalQuantityAmount(productObj.getString("TotalAmount"));
								productCartVO.setFavoritePrice(productObj.getString("FavoritePrice"));
								productCartVO.setQuantity(productObj.getString("Quantity"));
								productCartVO.setBrandName(productObj.getString("BrandEnName"));
								productCartVO.setProductUrl(productObj.getString("ProductUrl"));
								productCartVO.setImg(productObj.getString("MobileImg"));
								productCartVO.setProductNo(productObj.getString("ProductNo"));
								productCartVO.setSkuNo(productObj.getString("SkuNo"));
								productCartVO.setCategoryNo(productObj.getString("CategoryNo"));
								productCartVO.setAlCategoryNo(productObj.getString("AolaiSubjectNo"));
								productCartVO.setDateTime(productObj.getString("DateAdd"));
								productCartVO.setIslimitedOutlet(productObj.getString("IsLimitedOutlet"));
								productCartVO.setMsgType(productObj.getString("MsgType"));
								productCartVO.setMsg(productObj.getString("Msg"));
								String propStr = productObj.getString("MobileSkuAttrText");
								productCartVO.setSkuAttrTextStr(propStr);
								if (propStr != null && !"".equals(propStr)) {
									productPropVOList = new ArrayList<ProductPropVO>();
									String[] propArray = propStr.split("\\|");
									if (propArray != null && propArray.length > 0) {
										productPropVOList = new ArrayList<ProductPropVO>();
										for (int z = 0; z < propArray.length; z++) {
											productPropVO = new ProductPropVO();
											if(propArray[z].indexOf(":")>-1){
											    productPropVO.setName(propArray[z].substring(0, propArray[z].indexOf(":")));
	                                            productPropVO.setValue(propArray[z].substring(propArray[z].indexOf(":") + 1));
                                            }else{
                                                productPropVO.setName(propArray[z]);
                                                productPropVO.setValue("");
                                            }
											
											productPropVOList.add(productPropVO);
										}
										productCartVO.setSkuAttrText(productPropVOList);
									}
								}
								alProuctCartVOList.add(productCartVO);
							}
						}
						alPromotionVO.setProductCartVO(alProuctCartVOList);
						alPromotionVOList.add(alPromotionVO);
	
					}
					productAllCartVO.setAlPromotionVO(alPromotionVOList);
				}
			}
		}
		return this;
	}

	public ProductAllCartVO getProductAllCartVO() {
		return productAllCartVO;
	}

	public void setProductAllCartVO(ProductAllCartVO productAllCartVO) {
		this.productAllCartVO = productAllCartVO;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
