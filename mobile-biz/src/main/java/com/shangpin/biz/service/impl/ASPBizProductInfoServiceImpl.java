package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.Brand;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.GiftCardProductListItem;
import com.shangpin.biz.bo.Notice;
import com.shangpin.biz.bo.Pay;
import com.shangpin.biz.bo.ProductBasic;
import com.shangpin.biz.bo.ProductBiz;
import com.shangpin.biz.bo.ProductComment;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.ProductDetailByApp;
import com.shangpin.biz.bo.ProductFirstProps;
import com.shangpin.biz.bo.ProductSecondProps;
import com.shangpin.biz.bo.Tag;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.ASPBizProductDetailService;
import com.shangpin.biz.service.ASPBizProductInfoService;
import com.shangpin.biz.service.ASPBizRecommendService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class ASPBizProductInfoServiceImpl implements ASPBizProductInfoService {
	private static final Logger logger = LoggerFactory.getLogger(ASPBizProductInfoServiceImpl.class);
	@Autowired
	ASPBizProductDetailService aspBizProductDetailService;
	@Autowired
	ASPBizGiftCardService aspBizGiftCardService;
	@Autowired
	ASPBizRecommendService aspBizRecommendService;
	@Autowired
	ShangPinService shangPinService;

    @Override
	public String queryProductInfo(String activityId, String productId, String userId, String picNo,String type,String version) throws Exception {
		ProductDetailByApp productDetailByApp = new ProductDetailByApp();
		boolean isNew=false;//兼容老版本调用不同接口
		if(com.shangpin.utils.StringUtil.compareVersion("2.8.5", "2.9.3", version) == 0){
		    isNew=true;
		}
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.3", version) == 1) {
			productDetailByApp.setProduct(aspBizProductDetailService.queryProductDetail(activityId, productId, userId, picNo, "1"));
		} else {
			productDetailByApp.setProduct(aspBizProductDetailService.queryProductDetail(activityId, productId, userId, picNo,isNew));
		}
		ProductDetail productDetail = productDetailByApp.getProduct();
		if(productDetail != null){
		    productDetailByApp.setRecommend(aspBizRecommendService.doRecProduct("2", userId, "1", productId,"1","20"));
		
		    productDetail.setType(type);
	        ProductBasic productBasic = null;
	        String productNo = null;
	        if (productDetail != null) {
	            productBasic = productDetail.getBasic();
	            if(productBasic!=null){
	                productNo = productBasic.getProductId();
	                //如果是礼品卡商品  不显示颜色和尺码
	                if("1".equals(type) || "2".equals(type)){
	                    productDetail.getBasic().setFirstPropName("");
	                    productDetail.getBasic().setSecondPropName("");
	                    productDetail.setIsShowCart("0");
	                    //礼品卡不显示标签
	                    productDetail.getNotice().setTag(new ArrayList<Tag>());
	                    List<ProductFirstProps> firstPropsListNew=new ArrayList<ProductFirstProps>();
	                    List<ProductFirstProps> firstPropsList=productDetail.getBasic().getFirstProps();
	                    if(firstPropsList!=null){
	                        for(ProductFirstProps productFirstProps:firstPropsList){
	                            productFirstProps.setFirstProp("");
	                            productFirstProps.setIsSecondProp("0");
	                            List<ProductSecondProps> secondPropsListNew=new ArrayList<ProductSecondProps>();
	                            List<ProductSecondProps> secondPropsList=productFirstProps.getSecondProps();
	                            if(secondPropsList!=null){
	                                for(ProductSecondProps productSecondProps :secondPropsList){
	                                    productSecondProps.setSecondProp("");
	                                    secondPropsListNew.add(productSecondProps);
	                                }
	                                productFirstProps.setSecondProps(secondPropsListNew);
	                            }
	                            firstPropsListNew.add(productFirstProps);
	                        }
	                        productDetail.getBasic().setFirstProps(firstPropsListNew);
	                    }
	                    //如果是礼品卡商品不显示品牌
	                    Brand brand=productDetail.getBasic().getBrand();
	                    if(brand!=null){
	                        productDetail.getBasic().getBrand().setNameCN("");
	                        productDetail.getBasic().getBrand().setNameEN("");
	                    }
	                }
	            }
	            Notice notice = productDetail.getNotice();
	            if(notice!=null){
	                notice.setSalesPromotion("");
	                String codFlag = notice.getCodFlag()==null?"":notice.getCodFlag();
	                List<Pay> payList = payWay(productDetail.getPostArea(), codFlag);
	                notice.setPayment(payList);
	            }
	            //在详情页中增加评论的返回信息，只限前两条
	             String json = shangPinService.findCommentList(productId, "1", "2","0");
	             if (!StringUtils.isEmpty(json)) {
	                 ResultObjOne<ProductComment> productCommentObj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<ProductComment>>(){});
	                 productDetail.setProductComment(productCommentObj.getObj());
	             }
	        }
	        List<ProductBiz> bizList = new ArrayList<ProductBiz>();
	        ProductBiz biz=null;
	        if("1".equals(type)){
	            biz=new ProductBiz();
	            biz.setName("图片详情");
	            biz.setType("5");
	           //电子卡
	            biz.setRefContent(Constants.BASIC_INFO_GIFTCARD_ELECTRON_URL + "&productNo=" + productNo);
	            bizList.add(biz);
	        }else if("2".equals(type)){
	            biz=new ProductBiz();
	            biz.setName("图片详情");
	            biz.setType("5");
	            //实物卡
	            biz.setRefContent(Constants.BASIC_INFO_GIFTCARD_ENTITY_URL + "&productNo=" + productNo);
	            bizList.add(biz);
	        }else{
	            if(com.shangpin.utils.StringUtil.compareVersion("", "2.8.5", version) >0){
	                biz=new ProductBiz();
	                biz.setName("商品详情");
	                biz.setType("6");
	                biz.setRefContent("8");
	                bizList.add(biz);
	                biz=new ProductBiz();
	                biz.setName("图片详情");
	                biz.setType("5");    
	                //普通
	                biz.setRefContent(Constants.DETAIL_TEMPLATE_URL + "&productNo=" + productNo);      
	                bizList.add(biz);
	                if ("1".equals(productBasic.getIsSize())) {
	                    biz = new ProductBiz();
	                    biz.setName("尺码及试穿");
	                    biz.setType("5");
	                    biz.setRefContent(Constants.SIZE_TRY_URL + "&productNo=" + productNo);
	                    bizList.add(biz);
	                }
	                if ("1".equals(productBasic.getIsAfterSale())) {
	                    biz = new ProductBiz();
	                    biz.setName("保养售后");
	                    biz.setType("5");
	                    biz.setRefContent(Constants.DETAIL_AFTERSALE_URL + "&productNo=" + productNo);
	                    bizList.add(biz);
	                } 
	            }else if(com.shangpin.utils.StringUtil.compareVersion("2.8.2", "2.8.5", version) == 0){
	                biz=new ProductBiz();
	                biz.setName("商品详情");
	                biz.setType("5");
	                //普通
	                biz.setRefContent(Constants.BASIC_INFO_URL + "&productNo=" + productNo);             
	                bizList.add(biz);
	                if ("1".equals(productBasic.getIsSize())) {
	                    biz = new ProductBiz();
	                    biz.setName("尺码及试穿");
	                    biz.setType("5");
	                    biz.setRefContent(Constants.SIZE_TRY_URL + "&productNo=" + productNo);
	                    bizList.add(biz);
	                }
	                if ("1".equals(productBasic.getIsBrandStyle())) {
	                    biz = new ProductBiz();
	                    biz.setName("品牌风尚");
	                    biz.setType("5");
	                    biz.setRefContent(Constants.BRAND_STYLE_URL + "&productNo=" + productNo);
	                    bizList.add(biz);
	                }
	            }else{
	                biz=new ProductBiz();
	                biz.setName("图文详情");
	                biz.setType("5");   
	                //普通
	                biz.setRefContent(Constants.BASIC_INFO_URL + "&productNo=" + productNo);        
	                bizList.add(biz);           
	                if ("1".equals(productBasic.getIsSize())) {
	                    biz = new ProductBiz();
	                    biz.setName("尺码及试穿");
	                    biz.setType("5");
	                    biz.setRefContent(Constants.SIZE_TRY_URL + "&productNo=" + productNo);
	                    bizList.add(biz);
	                }
	                if ("1".equals(productBasic.getIsBrandStyle())) {
	                    biz = new ProductBiz();
	                    biz.setName("品牌风尚");
	                    biz.setType("5");
	                    biz.setRefContent(Constants.BRAND_STYLE_URL + "&productNo=" + productNo);
	                    bizList.add(biz);
	                }
	                if ("1".equals(productBasic.getIsAfterSale())) {
	                    biz = new ProductBiz();
	                    biz.setName("评论(" + productBasic.getCommentCount() + ")");
	                    biz.setType("6");
	                    biz.setRefContent("5");
	                    bizList.add(biz);
	                } 
	            }           
	        }
	        productDetailByApp.setBiz(bizList);
		}		
		if (productDetailByApp != null) {
			String json;
			try {
				json = ApiBizData.spliceData(productDetailByApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				logger.debug("调用api接口返回数据:" + json);
				return json;
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}

		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public String queryProductInfoGiftCard(String userId, String productId, String type) throws Exception {
		String categoryNO = "";
		if ("1".equals(type)) {
			//通过分类区分电子卡和实物卡
			categoryNO = "A03B01C03";
		} else if ("2".equals(type)) {
			categoryNO = "A03B01C02";
		}
		ResultObjOne<GiftCardProductList> list = aspBizGiftCardService.beList(categoryNO, productId, "", "", userId, "", "");

		GiftCardProductListItem item = list.getContent().getList().get(0);
		ProductDetail product = new ProductDetail();
		product.setType(type);
		ProductBasic basic = new ProductBasic();
		// 是否售罄
		basic.setIsSoldOut(item.getCount());
		basic.setProductId(item.getProductId());
		basic.setProductName(item.getProductName());
		// 收藏
		/*Collect collect = new Collect();
		collect.setIsCollected("");
		collect.setId("");
		basic.setCollect(collect);*/

		// basic.setIsAfterSale("0");
		// 是否包邮
		/*basic.setIsPackage("");*/
		// 图片列表
		String[] allPics = new String[] { item.getPic() };
		/*
		 * String firstPropName=""; String secondPropName="";
		 */
		basic.setAllPics(allPics);
		/*
		 * basic.setFirstPropName(firstPropName);
		 * basic.setSecondPropName(secondPropName);
		 */
		product.setBasic(basic);
		// 分享
		/*Share share = new Share();
		share.setTitle("");
		share.setDesc("");
		share.setPic("");
		share.setUrl("");
		product.setShare(share);*/
		ProductDetailByApp productApp = new ProductDetailByApp();
		productApp.setProduct(product);
		if (productApp != null) {
			String json;
			try {
				json = ApiBizData.spliceData(productApp, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
				logger.debug("调用api接口返回数据:" + json);
				return json;
			} catch (Exception e) {
				logger.error("调用api接口返回数据发生错误！");
				e.printStackTrace();
			}

		}
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}
	
	public static List<Pay> payWay(String region, String codFlag) {
		List<Pay> payList = new ArrayList<Pay>();
		if ("2".equals(region)) {
			Pay pay0 = new Pay();
			pay0.setId("30");
			pay0.setName("支付宝钱包支付");
			pay0.setEnable("1");
			payList.add(pay0);

				Pay pay4 = new Pay();
				pay4.setId("32");
				pay4.setName("微信支付");
				pay4.setEnable("1");
				payList.add(pay4);
			
			
		} else if ("1".equals(region)) {
			Pay pay1 = new Pay();
			pay1.setId("20");
			pay1.setName("支付宝");
			pay1.setEnable("1");
			payList.add(pay1);

			Pay pay2 = new Pay();
			pay2.setId("19");
			pay2.setName("银联支付");
			pay2.setEnable("1");
			payList.add(pay2);

			Pay pay3 = new Pay();
			pay3.setId("27");
			pay3.setName("微信支付");
			pay3.setEnable("1");
			payList.add(pay3);

			Pay pay4 = new Pay();
			pay4.setId("2");
			pay4.setName("货到付款");
			if ("1".equals(codFlag)) {
				pay4.setEnable("1");
			} else {
				pay4.setEnable("0");
			}
			payList.add(pay4);
		}

		return payList;
	}

}

