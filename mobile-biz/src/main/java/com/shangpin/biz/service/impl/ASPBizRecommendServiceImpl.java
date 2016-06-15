package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.RecRuleService;
import com.shangpin.biz.bo.ActivityHead;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.ProductSreach;
import com.shangpin.biz.bo.RecProduct;
import com.shangpin.biz.bo.RecProductFor;
import com.shangpin.biz.bo.Recommend;
import com.shangpin.biz.bo.RecommendProduct;
import com.shangpin.biz.bo.SearchProductJson;
import com.shangpin.biz.bo.Tag;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.base.SearchContentList;
import com.shangpin.biz.service.ASPBizRecommendService;
import com.shangpin.biz.service.abstraction.AbstractBizRecommendService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.biz.utils.SearchUtil;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.JsonUtil;

@Service
public class ASPBizRecommendServiceImpl extends AbstractBizRecommendService implements ASPBizRecommendService {
	protected final Log log = LogFactory.getLog(ASPBizRecommendServiceImpl.class);
	@Override
	public List<Recommend> doRecommendList(String userId) throws Exception {
		return doRecommends(userId);
	}
    @Autowired 
    private RecRuleService recRuleService;
    @Autowired
    private CommonService commonService;
	@Override
	public String doRecProduct(String type, String userId, String imei, String coord, String ip, String pageIndex, String pageSize) throws Exception {
		String data = null;
		RecProductFor rec = doBaseRecProduct(type, userId, imei, coord, ip, pageIndex, pageSize);
		if (rec != null) {
			rec.setSystime(String.valueOf(System.currentTimeMillis()));
		}
		if (null == rec) {
			data = ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		} else {
			data = ApiBizData.spliceData(rec, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
		return data;
	}

	@Override
	public List<Product> doRecProduct(String type, String userId, String pageIndex, String pageSize) throws Exception {
		List<Product> products = doBase(type, userId, pageIndex, pageSize);
		if (null == products) {
			products = new ArrayList<Product>();
		}
		return products;
	}
	@Override
    public List<Product> doRecProduct(String type, String userId, String shopType,String productId,String pageIndex, String pageSize) throws Exception {
        List<Product> products = new ArrayList<Product>();
        products=doBase(type, userId, shopType,productId,pageIndex, pageSize);
        return products;
    }

	@Override
	public String doRecRuleProduct(String userId, String imei,String offset,String num)
			throws Exception {
		String data=recRuleService.doRecRuleProduct(userId, imei,offset,num);
		return data;
	}

	@Override
	public String doSearchProduct(List product) throws Exception {
	/*	Set productNos =new HashSet();
		for (int i = 0; i < product.size(); i++) {
			productNos.add(product.get(i));
		}*/
		String data=recRuleService.findByProductNos(product);
		
		return data;
	}

	@Override
	public List<Product> products(List<String> product,String userId) {
		String json=recRuleService.findByProductNos(product);
		
		/*SearchContentList<RecommendProduct> recp=JsonUtil.fromJson(json, new TypeToken<SearchContentList<RecommendProduct>>(){});
		List<RecommendProduct> list=recp.getDocs();
		List<RecProduct>  pros =new ArrayList<RecProduct>();
	    if(null!=list&&list.size()>0){
	    	for(RecommendProduct recommoned:list){
	    		RecProduct recproduct =new RecProduct();
				recproduct.setPrefix(recommoned.getPrefix()==null?"":recommoned.getPrefix());
				recproduct.setSuffix(recommoned.getSuffix()==null?"":recommoned.getSuffix());
				recproduct.setProductId(recommoned.getproductNo()==null?"":recommoned.getproductNo());
				recproduct.setProductName(recommoned.getProductName()==null?"":recommoned.getProductName());
				recproduct.setBrandNameCN(recommoned.getBrandCnName()==null?"":recommoned.getBrandCnName());
				recproduct.setBrandNameEN(recommoned.getBrandEnName()==null?"":recommoned.getBrandEnName());
				recproduct.setBrandNo(recommoned.getBrandNo()==null?"":recommoned.getBrandNo());
				recproduct.setAdvertWord("");
				recproduct.setName(recommoned.getProductName()==null?"":recommoned.getProductName());
				
			    String marketprice=recommoned.getMarketPrice()==null?"":recommoned.getMarketPrice();	
				recproduct.setMarketPrice(marketprice.substring(0,marketprice.indexOf(".")));
				
				String limitprice=recommoned.getLimitedPrice()==null?"":recommoned.getLimitedPrice();
				recproduct.setLimitedPrice(limitprice.substring(0,limitprice.indexOf(".")));
				
				String sellprice=recommoned.getSellPrice()==null?"":recommoned.getSellPrice();
				
				recproduct.setGoldPrice(sellprice.substring(0,sellprice.indexOf(".")));
				
				String platinumprice=recommoned.getPlatinumPrice()==null?"":recommoned.getPlatinumPrice();
				
				recproduct.setPlatinumPrice(platinumprice.substring(0,platinumprice.indexOf(".")));
				
				String diamondprice=recommoned.getDiamondPrice()==null?"":recommoned.getDiamondPrice();
				
				recproduct.setDiamondPrice(diamondprice.substring(0,diamondprice.indexOf(".")));
				recproduct.setLimitedVipPrice("");
				
				String promotionprice=recommoned.getPromotionPrice()==null?"":recommoned.getPromotionPrice();
				
				recproduct.setPromotionPrice(promotionprice.substring(0,promotionprice.indexOf(".")));
				
				
				
				recproduct.setIsSupportDiscount(recommoned.getIsSupportDiscount()==null?"":recommoned.getIsSupportDiscount());
			  //  recproduct.setPromotionDesc("可优惠"+titles+"元");
				recproduct.setCount(recommoned.getAvailableStock()==null?"":recommoned.getAvailableStock());
				recproduct.setCollections("");
				recproduct.setComments("");
				String picFile=recommoned.getProductPicFile();
				
				if(!StringUtils.isEmpty(picFile)){
					recproduct.setPic(PicCdnHash.getPicUrl(picFile, "1"));
				}
				recproduct.setScenePic("");
				recproduct.setPostArea(recommoned.getMerchantType()==null?"":recommoned.getMerchantType());
				recproduct.setCountryPic("");
				recproduct.setStatus("10000");
				recproduct.setHeight("426");
				recproduct.setWidth("320");
				List<Tag> tag=new ArrayList<Tag>();
				recproduct.setTag(tag);
				recproduct.setSalePrice(recommoned.getSalePrice()==null?"":recommoned.getSalePrice());
				recproduct.setMarketPriceNew(recommoned.getMarketPriceNew()==null?"":recommoned.getMarketPriceNew());
				//5.24 是否促销
				recproduct.setIsPromotion(recommoned.getIsPromotion()==null?"":recommoned.getIsPromotion());
				
				String newLimitedPrice=recommoned.getNewLimitedPrice()==null?"":recommoned.getNewLimitedPrice();
				
				recproduct.setNewLimitedPrice(newLimitedPrice.substring(0,newLimitedPrice.indexOf(".")));
				
				//推荐根据预热/520活动期间变价
				recproduct = getPromionProducts(recproduct);
				pros.add(recproduct);
			}
	    }*/
		List<Product> list = null;
		List<Product> resultlist = new ArrayList<Product>();
		SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(json, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
		if(productJson!=null){
			logger.debug("searchProductListFloor   result:{description:"+productJson.getDiscription()+", qtime:"+productJson.getQTime()+", status:"+productJson.getStatus()+" ,total:"+productJson.getTotal()+" ,sid:"+productJson.getSid()+" ,dosCount:"+productJson.getDocs().size());
			//查询结果大于0
			List<ProductSreach> searchListDoc = productJson.getDocs();
			if(productJson.getTotal()>0 && searchListDoc!=null && searchListDoc.size()>0){
				list = SearchUtil.initJsonProductList(searchListDoc, "1", null);
				//设置下图片宽和高
				for (Product pro:list) {
					pro.setWidth("320");
					pro.setHeight("426");
					resultlist.add(pro);
				}
				
			}
		}
		return resultlist;
	}
	
	/**活动期间显示 520价格 尚品价等等  String fromType,ActivityHead activityHead
	 * @param product
	 * @return
	 */
	public RecProduct getPromionProducts(RecProduct product){
		String priceTitle = "";// 价格title
		String priceColor = "#2d2d2d";// 价格颜色
		String descColor = "";// 描述颜色
		String priceDesc = ""; // 价格描述
		String expressLogo = "";// 直发图片
		if (product != null) {
			StringBuffer descBuffer = null;
			String promotionPrice = product.getPromotionPrice();
			String diamondPrice = product.getDiamondPrice();
			String newLimitedPrice = product.getNewLimitedPrice();
			// 2015.11.24-实时变价，预热阶段
			if (StringUtil.compareDate(Constants.ACTIVITY_READY_START, Constants.ACTIVITY_READY_END, DateUtils.dateToStr(new Date())) == 0) {
				if ("1".equals(product.getIsPromotion())) {
					priceColor = "#c62026";
				} else {
					if (StringUtil.isNotEmpty(promotionPrice) && (Integer.parseInt(promotionPrice) < Integer.parseInt(diamondPrice))) {
						descColor = "#c62026";
						descBuffer = new StringBuffer();
						descBuffer.append(Constants.PRICE_ACTIVITY);
						descBuffer.append("¥" + promotionPrice);
						priceDesc = descBuffer.toString();
					}
				}
			}
			// 2015.11.24-实时变价，活动阶段
			else if (StringUtil.compareDate(Constants.ACTIVITY_OPEN_START, Constants.ACTIVITY_OPEN_END, DateUtils.dateToStr(new Date())) == 0) {
				logger.info("实时变价");
				if ("1".equals(product.getIsPromotion())) {
					priceTitle = Constants.PRICE_ACTIVITY;
					priceColor = "#c62026";
					//if (StringUtil.isNotEmpty(promotionPrice) && (Integer.parseInt(promotionPrice) < Integer.parseInt(newLimitedPrice))) {
					if (StringUtil.isNotEmpty(promotionPrice) && (Integer.parseInt(promotionPrice) <= Integer.parseInt(newLimitedPrice))) {
					
					    descColor = "#888888";
						descBuffer = new StringBuffer();
						descBuffer.append(Constants.PRICE_COMPARE);
						descBuffer.append("¥" + newLimitedPrice);
						priceDesc = descBuffer.toString();
					}
					product.setPromoLogo(Constants.IFC_KHJ_URL);
				}
			}
			// 原逻辑
			else {
				if ("2".equals(product.getPostArea())) {
					// #2d2d2d 黑色
					// #888888 灰色
					// #c62026 红色
					descColor = "#888888";
					int cheapPrice = 0;
					cheapPrice = Integer.parseInt(product.getMarketPrice()) - Integer.parseInt(product.getGoldPrice());
					String countryPic = product.getCountryPic();
					StringBuffer stringBuffer = new StringBuffer();
					if (countryPic != null) {
						if (cheapPrice > 0) {
							stringBuffer.append("省¥" + cheapPrice);
						}							
					}
					if ("1".equals(product.getIsPromotion())) {
                        priceColor = "#c62026";
                    }
				} else {
					if ("1".equals(product.getIsPromotion())) {
						priceColor = "#c62026";
						descColor = "#888888";
						// priceTitle="促销价:";//上线不显示
						descBuffer = new StringBuffer();
						if (Integer.parseInt(product.getLimitedPrice()) < Integer.parseInt(product.getMarketPrice())) {
							descBuffer.append("市场价:");
							descBuffer.append("¥" + product.getMarketPrice());
							// priceDesc=descBuffer.toString();
						}
					}
				}
				// 活动有预热单独处理
			/*	if ("1".equals(fromType)) {
					if (activityHead != null) {
						if ("1".equals(activityHead.getIsPre())) {
							if (!"1".equals(product.getIsPromotion())) {
								priceColor = "#888888";
							}
							descColor = "#c62026";
							descBuffer = new StringBuffer();
							if ("1".equals(activityHead.getPriceTag())) {
								descBuffer.append(activityHead.getPriceDesc());
								descBuffer.append(":¥" + promotionPrice);
								priceDesc = descBuffer.toString();
							} else if ("2".equals(activityHead.getPriceTag())) {
								descBuffer.append(activityHead.getPriceDesc());
								priceDesc = descBuffer.toString();
							}
						}
					}
				}*/
			}
			if ("2".equals(product.getPostArea())) {
				String countryPic = product.getCountryPic();
				if (countryPic != null) {
					if (countryPic.indexOf("yidali") > -1 || countryPic.indexOf("yingguo") > -1) {
						expressLogo = Constants.OZ_URL;
					} else if (countryPic.indexOf("xinjiapo") > -1) {
						expressLogo = Constants.IFC_XJP_URL;
					} else if (countryPic.indexOf("meiguo") > -1) {
						expressLogo = Constants.MG_URL;
					} else if (countryPic.indexOf("hanguo") > -1) {
						expressLogo = Constants.HG_URL;
					} else if (countryPic.indexOf("xianggang") > -1) {
						expressLogo = Constants.XG_URL;
					}
				}
			}
		}
		product.setCountryPic("");// 占时去掉国旗标
		product.setPriceColor(priceColor);
		product.setDescColor(descColor);
		product.setExpressLogo(expressLogo);
		product.setPriceTitle(priceTitle);
		product.setPriceDesc(priceDesc);
		return product;
	}
	
	

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public User findByUserId(String userId){
		String json = commonService.findUserInfo(userId);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjOne<User> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<User>>(){});
		if(Constants.SUCCESS.equals(result.getCode()) && null != result.getContent()){
			return result.getContent();
		}
		return null;
	}
	
	/**
	 * 获取用户级别
	 * @param userId
	 * @return
	 */
	public String getUserLv(String userId){
		if(StringUtils.isEmpty(userId)){
			return "";
		}
		User user = findByUserId(userId);
		if(null == user){
			return "";
		}
		return user.getLv();
	}

	@Override
	public RecProductFor fromFindPopularityAndWorthObj(String type,
			String userId, String pageIndex, String pageSize) {
		return null;
	}
}

