package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.RunningService;
import com.shangpin.biz.bo.APPBrand;
import com.shangpin.biz.bo.APPProduct;
import com.shangpin.biz.bo.APPRunCategory;
import com.shangpin.biz.bo.CategoryProductList;
import com.shangpin.biz.bo.Color;
import com.shangpin.biz.bo.Fact;
import com.shangpin.biz.bo.Item;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RunVo;
import com.shangpin.biz.bo.Size;
import com.shangpin.biz.bo.base.ResultBaseWy;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.CDNHash;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizRunningService {
	
	@Autowired
	protected RunningService runningService;
	
	public String categoryProductList(String pageIndex, String pageSize, String channelCategoryNO, String categoryNO, String userType, String price, String productSize, String primaryColorId, String brandNO, String order, String postArea){
		String start = null;
		String end= null;
		if(!StringUtils.isEmpty(pageIndex)&&!StringUtils.isEmpty(pageSize)){
			start = String.valueOf((Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize) + 1);
			end = String.valueOf(Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
		}
		String json = runningService.categoryProductList(start, end, channelCategoryNO, categoryNO, userType, price, productSize, primaryColorId, brandNO, order, postArea);
		return json;
	}
	
	public CategoryProductList CategoryProductListObj(String start, String end, String channelCategoryNO, String categoryNO, String userType, String price, String productSize, String primaryColorId, String brandNO, String order, String postArea){
		String json = categoryProductList(start, end, channelCategoryNO, categoryNO, userType, price, productSize, primaryColorId, brandNO, order, postArea);
		//String json = "{\"result\": {\"description\": \"查询成功\",\"docs\": [{\"adverTitle\": \"\",\"availableStock\": 1,\"brandCnName\": \"化石\",\"brandEnName\": \"FOSSIL\",\"brandNo\": \"B2235\",\"diamondPrice\": \"1900.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 0,\"limitedPrice\": \"1900.00\",\"marketPrice\": \"1900.00\",\"platinumPrice\": \"1900.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141215165749008859\",\"productName\": \"FOSSIL化石 石英男表CH2948\",\"productNo\": \"30008385\",\"productPicFile\": \"20141215165747323921\",\"promotionNotice\": \"\",\"sellPrice\": \"1900.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 4,\"brandCnName\": \"诗格恩\",\"brandEnName\": \"SKAGEN\",\"brandNo\": \"B0105\",\"diamondPrice\": \"1798.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 1,\"limitedPrice\": \"1798.00\",\"marketPrice\": \"1798.00\",\"platinumPrice\": \"1798.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141210180007715020\",\"productName\": \"Skagen诗格恩石英男表SKW6001\",\"productNo\": \"30004952\",\"productPicFile\": \"20141210180007715020\",\"promotionNotice\": \"\",\"sellPrice\": \"1798.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 3,\"brandCnName\": \"化石\",\"brandEnName\": \"FOSSIL\",\"brandNo\": \"B2235\",\"diamondPrice\": \"2020.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 0,\"limitedPrice\": \"2020.00\",\"marketPrice\": \"2020.00\",\"platinumPrice\": \"2020.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141215165924279932\",\"productName\": \"FOSSIL化石 石英男表FS4926\",\"productNo\": \"30008400\",\"productPicFile\": \"20141215165922609517\",\"promotionNotice\": \"\",\"sellPrice\": \"2020.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 2,\"brandCnName\": \"诗格恩\",\"brandEnName\": \"SKAGEN\",\"brandNo\": \"B0105\",\"diamondPrice\": \"2698.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 1,\"limitedPrice\": \"2698.00\",\"marketPrice\": \"2698.00\",\"platinumPrice\": \"2698.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141210175927809915\",\"productName\": \"Skagen诗格恩石英男表585XLTMXM\",\"productNo\": \"30004947\",\"productPicFile\": \"20141210175927809915\",\"promotionNotice\": \"\",\"sellPrice\": \"2698.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 1,\"brandCnName\": \"诗格恩\",\"brandEnName\": \"SKAGEN\",\"brandNo\": \"B0105\",\"diamondPrice\": \"1798.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 1,\"limitedPrice\": \"1798.00\",\"marketPrice\": \"1798.00\",\"platinumPrice\": \"1798.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141210180133719958\",\"productName\": \"Skagen诗格恩石英男表SKW6036\",\"productNo\": \"30004964\",\"productPicFile\": \"20141210180133719958\",\"promotionNotice\": \"\",\"sellPrice\": \"1798.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 1,\"brandCnName\": \"化石\",\"brandEnName\": \"FOSSIL\",\"brandNo\": \"B2235\",\"diamondPrice\": \"1670.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 0,\"limitedPrice\": \"1670.00\",\"marketPrice\": \"1670.00\",\"platinumPrice\": \"1670.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141215170806277144\",\"productName\": \"FOSSIL化石 石英男表FS4973\",\"productNo\": \"30008414\",\"productPicFile\": \"20141215170803937100\",\"promotionNotice\": \"\",\"sellPrice\": \"1670.00\",\"suffix\": \"\"},{\"adverTitle\": \"Arbutus手表设计简洁、大方，深受广泛顾客的爱戴，每一只Arbu\",\"availableStock\": 5,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"3137.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"3137.00\",\"marketPrice\": \"4100.00\",\"platinumPrice\": \"3137.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20140915122451874045\",\"productName\": \"爱彼特男款自动机械手表 AR513SWS\",\"productNo\": \"04285784\",\"productPicFile\": \"20140915122351938795\",\"promotionNotice\": \"\",\"sellPrice\": \"3137.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 6,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"2599.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"2599.00\",\"marketPrice\": \"3980.00\",\"platinumPrice\": \"2599.00\",\"postArea\": 1,\"prefix\": \"【此商品只做直降，不送赠品】\",\"productModelPicFile\": \"20140915122041618397\",\"productName\": \"爱彼特多功能自动机械男士手表 AR509SBS\",\"productNo\": \"04285776\",\"productPicFile\": \"20140915122041509192\",\"promotionNotice\": \"&nbsp;\",\"sellPrice\": \"2599.00\",\"suffix\": \"\"},{\"adverTitle\": \"新品上市\",\"availableStock\": 2,\"brandCnName\": \"松拓\",\"brandEnName\": \"SUUNTO\",\"brandNo\": \"B1624\",\"diamondPrice\": \"5290.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 2,\"limitedPrice\": \"5290.00\",\"marketPrice\": \"5990.00\",\"platinumPrice\": \"5290.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20150617132359620914\",\"productName\": \"SUUNTO 颂拓 本源系列 军绿编织表带 男士 绿色 户外运动腕表\",\"productNo\": \"30071404\",\"productPicFile\": \"20150617132355736111\",\"promotionNotice\": \"\",\"sellPrice\": \"5290.00\",\"suffix\": \"\"},{\"adverTitle\": \"Arbutus手表设计简洁、大方，深受广泛顾客的爱戴，每一只Arbu\",\"availableStock\": 5,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"2754.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"2754.00\",\"marketPrice\": \"3600.00\",\"platinumPrice\": \"2754.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20140915121856396265\",\"productName\": \"Classic系列 爱彼特全自动机械男表 AR505SBS\",\"productNo\": \"04285770\",\"productPicFile\": \"20140915121851372978\",\"promotionNotice\": \"\",\"sellPrice\": \"2754.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 2,\"brandCnName\": \"化石\",\"brandEnName\": \"FOSSIL\",\"brandNo\": \"B2235\",\"diamondPrice\": \"1900.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 0,\"limitedPrice\": \"1900.00\",\"marketPrice\": \"1900.00\",\"platinumPrice\": \"1900.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141215171014885997\",\"productName\": \"FOSSIL化石 石英男表JR1437\",\"productNo\": \"30008432\",\"productPicFile\": \"20141215171013044881\",\"promotionNotice\": \"\",\"sellPrice\": \"1900.00\",\"suffix\": \"\"},{\"adverTitle\": \"Arbutus手表设计简洁、大方，深受广泛顾客的爱戴，每一只Arbu\",\"availableStock\": 3,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"2943.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"2943.00\",\"marketPrice\": \"3850.00\",\"platinumPrice\": \"2943.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20140915122922284938\",\"productName\": \"爱彼特男士自动机械手表AR603BBB\",\"productNo\": \"04303794\",\"productPicFile\": \"20140915122922175733\",\"promotionNotice\": \"\",\"sellPrice\": \"2943.00\",\"suffix\": \"\"},{\"adverTitle\": \"Arbutus手表设计简洁、大方，深受广泛顾客的爱戴，每一只Arbu\",\"availableStock\": 7,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"2599.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"2599.00\",\"marketPrice\": \"3980.00\",\"platinumPrice\": \"2599.00\",\"postArea\": 1,\"prefix\": \"【此商品只做直降，不送赠品】\",\"productModelPicFile\": \"20140915122051742848\",\"productName\": \"爱彼特多功能大表盘机械男表AR509SWS\",\"productNo\": \"04285777\",\"productPicFile\": \"20140915122051633643\",\"promotionNotice\": \"&nbsp;\",\"sellPrice\": \"2599.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 2,\"brandCnName\": \"诗格恩\",\"brandEnName\": \"SKAGEN\",\"brandNo\": \"B0105\",\"diamondPrice\": \"1259.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 1,\"limitedPrice\": \"1398.00\",\"marketPrice\": \"1098.00\",\"platinumPrice\": \"1287.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141212220227679311\",\"productName\": \"Skagen诗格恩石英男表233XXLSLB\",\"productNo\": \"0430336\",\"productPicFile\": \"20141212220430405095\",\"promotionNotice\": \"\",\"sellPrice\": \"1329.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 1,\"brandCnName\": \"化石\",\"brandEnName\": \"FOSSIL\",\"brandNo\": \"B2235\",\"diamondPrice\": \"1440.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 0,\"limitedPrice\": \"1440.00\",\"marketPrice\": \"1440.00\",\"platinumPrice\": \"1440.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141215170836463364\",\"productName\": \"FOSSIL化石 石英男表FS4986\",\"productNo\": \"30008418\",\"productPicFile\": \"20141215170834560555\",\"promotionNotice\": \"\",\"sellPrice\": \"1440.00\",\"suffix\": \"\"},{\"adverTitle\": \"崇尚户外运动/时尚都市人群\",\"availableStock\": 3,\"brandCnName\": \"松拓\",\"brandEnName\": \"SUUNTO\",\"brandNo\": \"B1624\",\"diamondPrice\": \"5950.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 2,\"limitedPrice\": \"5950.00\",\"marketPrice\": \"6990.00\",\"platinumPrice\": \"5950.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141118150859325753\",\"productName\": \"SUUNTO ELEMENTUM TERRA n/stealth rubber山雄登山 负性显示/灰色橡胶带腕表\",\"productNo\": \"04514313\",\"productPicFile\": \"20141118150859325753\",\"promotionNotice\": \"\",\"sellPrice\": \"5950.00\",\"suffix\": \"\"},{\"adverTitle\": \"Arbutus手表设计简洁、大方，深受广泛顾客的爱戴，每一只Arbu\",\"availableStock\": 3,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"2754.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"2754.00\",\"marketPrice\": \"3600.00\",\"platinumPrice\": \"2754.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20140915121706275665\",\"productName\": \"爱彼特Classic系列自动机械男表AR502SBS\",\"productNo\": \"04285760\",\"productPicFile\": \"20140915121701533253\",\"promotionNotice\": \"\",\"sellPrice\": \"2754.00\",\"suffix\": \"\"},{\"adverTitle\": \"Arbutus手表设计简洁、大方，深受广泛顾客的爱戴，每一只Arbu\",\"availableStock\": 3,\"brandCnName\": \"爱彼特\",\"brandEnName\": \"ARBUTUS\",\"brandNo\": \"B0337\",\"diamondPrice\": \"2754.00\",\"erpCategoryNo\": \"A04B03C01D02\",\"isPromotion\": 1,\"isSupportDiscount\": 1,\"limitedPrice\": \"2754.00\",\"marketPrice\": \"3600.00\",\"platinumPrice\": \"2754.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20140915123002220929\",\"productName\": \"时尚男士自动机械表\",\"productNo\": \"04326166\",\"productPicFile\": \"20140915123002111771\",\"promotionNotice\": \"\",\"sellPrice\": \"2754.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 1,\"brandCnName\": \"化石\",\"brandEnName\": \"FOSSIL\",\"brandNo\": \"B2235\",\"diamondPrice\": \"1330.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 0,\"limitedPrice\": \"1330.00\",\"marketPrice\": \"1330.00\",\"platinumPrice\": \"1330.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141215170800224676\",\"productName\": \"FOSSIL化石 石英男表FS4965\",\"productNo\": \"30008413\",\"productPicFile\": \"20141215170758399995\",\"promotionNotice\": \"\",\"sellPrice\": \"1330.00\",\"suffix\": \"\"},{\"adverTitle\": \"\",\"availableStock\": 3,\"brandCnName\": \"诗格恩\",\"brandEnName\": \"SKAGEN\",\"brandNo\": \"B0105\",\"diamondPrice\": \"1598.00\",\"erpCategoryNo\": \"A04B03C02D02\",\"isPromotion\": 0,\"isSupportDiscount\": 1,\"limitedPrice\": \"1598.00\",\"marketPrice\": \"1598.00\",\"platinumPrice\": \"1598.00\",\"postArea\": 1,\"prefix\": \"\",\"productModelPicFile\": \"20141210180249473089\",\"productName\": \"Skagen诗格恩石英男表SKW6082\",\"productNo\": \"30004975\",\"productPicFile\": \"20141210180249473089\",\"promotionNotice\": \"\",\"sellPrice\": \"1598.00\",\"suffix\": \"\"}],\"end\": 20,\"facets\": [{\"facetName\": \"Brand\",\"items\": [{\"itemName\": \"B0001|EMPORIO ARMANI|安普里奥·阿玛尼|20150624134930789999\",\"itemValue\": 1},{\"itemName\": \"B0105|SKAGEN|诗格恩|20150128182239294704\",\"itemValue\": 51},{\"itemName\": \"B0337|ARBUTUS|爱彼特|20140305100018779397\",\"itemValue\": 90},{\"itemName\": \"B1624|SUUNTO|松拓|20140831005005214884\",\"itemValue\": 21},{\"itemName\": \"B2235|FOSSIL|化石|20141224174429452889\",\"itemValue\": 78}]},{\"facetName\": \"CLv2\",\"items\": [{\"itemName\": \"A01B04|配饰|A01|1|-5\",\"itemValue\": 241}]},{\"facetName\": \"CLv3\",\"items\": [{\"itemName\": \"A01B04C54|腕表/钟|A01B04|1|3\",\"itemValue\": 241}]},{\"facetName\": \"CLv4\",\"items\": [{\"itemName\": \"A01B04C54D01|男表|A01B04C54|1|1\",\"itemValue\": 234},{\"itemName\": \"A01B04C54D02|女表|A01B04C54|1|2\",\"itemValue\": 7}]},{\"facetName\": \"ProductSize\"},{\"facetName\": \"ProductPrimaryColors\",\"items\": [{\"itemName\": \"19|黑色|#000000\",\"itemValue\": 112},{\"itemName\": \"20|白色|#ffffff\",\"itemValue\": 10},{\"itemName\": \"22|粉色|#f883c8\",\"itemValue\": 1},{\"itemName\": \"23|橙色|#ff7800\",\"itemValue\": 1},{\"itemName\": \"25|绿色|#00ad1c\",\"itemValue\": 1},{\"itemName\": \"27|蓝色|#385eff\",\"itemValue\": 9},{\"itemName\": \"29|灰色|#bcbcbc\",\"itemValue\": 13},{\"itemName\": \"30|棕色|#8b472c\",\"itemValue\": 14},{\"itemName\": \"31|米色|#f5dfb5\",\"itemValue\": 3},{\"itemName\": \"32|金色|#d6af51\",\"itemValue\": 14},{\"itemName\": \"33|银色|#e5ebec\",\"itemValue\": 16},{\"itemName\": \"35|其它|\",\"itemValue\": 1}]},{\"facetName\": \"PostArea\",\"items\": [{\"itemName\": \"1\",\"itemValue\": 241}]}],\"qtime\": 1,\"sid\": \"RCCategoryProductList-0ac9e099-30c7-42d1-a6da-ce66e02215bb\",\"start\": 1,\"status\": 0,\"total\": 241}}";
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultBaseWy<CategoryProductList> result = JsonUtil.fromJson(json, new TypeToken<ResultBaseWy<CategoryProductList>>(){});
		CategoryProductList obj = result.getResult();
		if(Constants.SUCCESS.equals(obj.getStatus())){
			return obj;
		}
		return null;
	}
	
	public RunVo categoryProductListVo(String pageIndex, String pageSize, String userLv, String price, String size, String color, String channelCategoryNo, String categoryId, String brandId,String order, String postArea) {
//		String start = null;
//		String end= null;
//		if(!StringUtils.isEmpty(pageIndex)&&!StringUtils.isEmpty(pageSize)){
//			start = String.valueOf((Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize) + 1);
//			end = String.valueOf(Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
//		}
		String userType = null;
		String orderParam = null;
		if(null != userLv){
			if("0002".equals(userLv)){
				userType ="gold";
			}else if("0003".equals(userLv)){
				userType ="platinum";
			}else if("0004".equals(userLv)){
				userType ="diamond";
			}else {
				userType = "";
			}
		}
		if(null != order){
			if (order.equals("3")) {
	            orderParam = "newest desc";
	        } else if (order.equals("1")) {
	        	orderParam = "price desc";
	        } else if (order.equals("2")) {
	        	orderParam = "price asc";
	        } else if (order.equals("4")) {
	        	orderParam = "Hot";
	        } else if (order.equals("5")) {
	        	orderParam = "sales desc";
	        } else if (order.equals("6")) {
	        	orderParam = "sales asc";
	        } else if (order.equals("7")) {
	        	orderParam = "discount desc";
	        } else if (order.equals("8")) {
	        	orderParam = "discount asc";
	        }else {
				orderParam = "";
			}
		}
		CategoryProductList categoryProductList = this.CategoryProductListObj(pageIndex, pageSize, channelCategoryNo, categoryId, userType, price, size, color, brandId, orderParam, postArea);
		if(null == categoryProductList){
			return null;
		}
		List<Product> products = categoryProductList.getDocs();
		List<APPProduct> appProducts = this.convertProduct(products);
		List<Fact> facts = categoryProductList.getFacets();
		RunVo runVo = this.convertRunVo(facts);
		runVo.setProductList(appProducts);
		return runVo;
	}
	
	private List<APPProduct> convertProduct(List<Product> products){
		List<APPProduct> appProducts = new ArrayList<APPProduct>();
		for(Product product : products){
			APPProduct appProduct = new APPProduct();
			appProduct.setProductId(product.getProductNo());
			appProduct.setProductName(product.getProductName());
			appProduct.setBrandNo(product.getBrandNo());
			appProduct.setBrandNameEN(product.getBrandEnName());
			appProduct.setBrandNameCN(product.getBrandCnName());
			appProduct.setMarketPrice(product.getMarketPrice());
			appProduct.setGoldPrice(product.getSellPrice());
			appProduct.setPlatinumPrice(product.getPlatinumPrice());
			appProduct.setDiamondPrice(product.getDiamondPrice());
			appProduct.setLimitedPrice(product.getLimitedPrice());
			appProduct.setIsSupportDiscount(product.getIsSupportDiscount());
			appProduct.setPromotionDesc(product.getPromotionNotice());
			appProduct.setPromotionPrice("");//促销价为空，如果有促销的商品价格已经算在各个级别价格上
			appProduct.setStatus("100000");
			appProduct.setName("");
			appProduct.setType("");
			appProduct.setRefContent("");
			appProduct.setPostArea(product.getPostArea());
			appProduct.setCount(product.getAvailableStock());
			appProduct.setPic(this.getPicUrl(product.getProductPicFile(), "1"));
			appProduct.setAdverTitle(product.getAdverTitle());
			appProduct.setPrefix(product.getPrefix());
			appProduct.setSuffix(product.getSuffix());
			appProducts.add(appProduct);
		}
		return appProducts;
	}
	
	private RunVo convertRunVo(List<Fact> facts){
		RunVo runVo = new RunVo();
		List<APPRunCategory> appRunCategories = new ArrayList<APPRunCategory>();
		for(Fact fact : facts){
			String name = fact.getFacetName();
			if("Brand".equals(name)){
				List<Item> items = fact.getItems();
				if(null != items && items.size() > 0){
					List<APPBrand> brands = this.getAppBrands(items);
					runVo.setBrandList(brands);
				}else {
					runVo.setBrandList(new ArrayList<APPBrand>());
				}
			}else if("ProductPrimaryColors".equals(name)){
				List<Item> items = fact.getItems();
				if(null != items && items.size() > 0){
					List<Color> colors = this.getColors(items);
					runVo.setColorList(colors);
				}else {
					runVo.setColorList(new ArrayList<Color>());
				}
			}else if("ProductSize".equals(name)){
				List<Item> items = fact.getItems();
				if(null != items && items.size() > 0){
					List<Size> sizes = this.getSizes(items);
					runVo.setSizeList(sizes);
				}else {
					runVo.setSizeList(new ArrayList<Size>());
				}
			}else if("PostArea".equals(name)){
				List<Item> items = fact.getItems();
				if(null != items && items.size() > 0){
					if(this.isAbroad(items)){
						runVo.setAbroad("1");
					}else {
						runVo.setAbroad("0");
					}
				}else {
					runVo.setAbroad("");
				}
			}else if(name.startsWith("CLv")){
				List<Item> items = fact.getItems();
				if(null != items && items.size() > 0){
					List<APPRunCategory> categories = this.getCategories(items);
					appRunCategories.addAll(categories);
				}
			}
			runVo.setCategoryList(appRunCategories);
		}
		return runVo;
	}
	
	private List<APPBrand> getAppBrands(List<Item> items){
		List<APPBrand> brands = new ArrayList<APPBrand>();
		for(Item item : items){
			String itemName = item.getItemName();
			APPBrand brand = this.getAppBrand(itemName);
			brands.add(brand);
		}
		return brands;
	}
	
	private APPBrand getAppBrand(String itemName){
		APPBrand brand = new APPBrand();
		String[] names = itemName.split("\\|");
		brand.setId(names[0]);
		brand.setNameEN(names[1]);
		brand.setName(names[2]);
		return brand;
	}
	
	private List<Color> getColors(List<Item> items){
		List<Color> colors = new ArrayList<Color>();
		for(Item item : items){
			String itemName = item.getItemName();
			Color color = this.getColor(itemName);
			colors.add(color);
		}
		return colors;
	}
	
	private Color getColor(String itemName){
		Color color = new Color();
		String[] names = itemName.split("\\|");
		color.setId(names[0]);
		color.setName(names[1]);
		if(!"其它".equals(names[1])){
			color.setRgb(names[2]);
		}
		return color;
	}
	
	private List<Size> getSizes(List<Item> items){
		List<Size> sizes = new ArrayList<Size>();
		for(Item item : items){
			String itemName = item.getItemName();
			Size size = new Size();
			size.setId(itemName);
			size.setName(itemName);
			sizes.add(size);
		}
		return sizes;
	}
	
	private boolean isAbroad(List<Item> items){
		for(Item item : items){
			String itemName = item.getItemName();
			if("2".equals(itemName)){
				return true;
			}
		}
		return false;
	}
	
	private List<APPRunCategory> getCategories(List<Item> items){
		List<APPRunCategory> categories = new ArrayList<APPRunCategory>();
		for(Item item : items){
			String itemName = item.getItemName();
			APPRunCategory category = this.getCategory(itemName);
			categories.add(category);
		}
		return categories;
	}
	
	private APPRunCategory getCategory(String itemName){
		APPRunCategory category = new APPRunCategory();
		String[] names = itemName.split("\\|");
		category.setId(names[0]);
		category.setName(names[1]);
		return category;
	}
	
	/**
     * 获取图片url
     * 
     * @author wangfeng
     * @createDate 2014-12-22
     * @param picNo
     *            ,type 1 商品，2 品牌
     */
	private String getPicUrl(String picNo, String type) {
        StringBuffer picBuffer = new StringBuffer("");
        picBuffer.append(CDNHash.getUrlHash(picNo));
        if ("1".equals(type)) {
            picBuffer.append("/f/p/");
        } else if ("2".equals(type)) {
            picBuffer.append("/e/s/");
        }
        picBuffer.append(picNo.substring(2, 4));
        picBuffer.append("/");
        picBuffer.append(picNo.substring(4, 6));
        picBuffer.append("/");
        picBuffer.append(picNo.substring(6, 8));
        picBuffer.append("/");
        picBuffer.append(picNo);
        picBuffer.append("-10-10.jpg");
        return picBuffer.toString();
    }
	
//	public static void main(String[] args) {
//		AbstractBizRunningService service = new AbstractBizRunningService();
//		RunVo runVo = service.categoryProductListVo(null, null, null, null, null, null, null, null, null, null, null);
//		System.out.println(runVo);
//	}
}
