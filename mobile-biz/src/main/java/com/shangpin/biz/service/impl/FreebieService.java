package com.shangpin.biz.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.FreebieBaseService;
import com.shangpin.base.service.SearchService;
import com.shangpin.biz.bo.Freebie;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.ProductDetail;
import com.shangpin.biz.bo.ProductSreach;
import com.shangpin.biz.bo.SearchProductJson;
import com.shangpin.biz.bo.freeBie.FreebiePro;
import com.shangpin.biz.service.ASPBizProductDetailService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.SearchUtil;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.JsonUtil;

/**
 * #2016-04-29买赠分享页面，
 * 1.获取订单的可赠送商品,返回赠品分享数据<br/>
 * 2.对赠送品进行领取,记录领取人、领取记录，发送领取短信<br/>
 * 3.验证商品是否已经领取，<br/>
 * 4.获取所有的订单中可赠送的商品<br/>
 * @date:     2016年4月29日 <br/> 
 * @author 陈小峰
 * @since JDK7
 */
@Service
public class FreebieService {
	private static final String SPLIT_CHAR = "`";
	private static final String freebieKey="key_shangpin_fbe";
	
	private static final Logger logger = LoggerFactory.getLogger(FreebieService.class);
	
	@Autowired
	private FreebieBaseService freebieBaseService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ASPBizProductDetailService aspBizProductDetailService;
	/**
	@Autowired
	private ASPBizProductDetailService aspBizProductDetailService;
	/**
	 * 根据订单id确定是否有赠送品
	 * @param orderId 订单id
	 * @param userId 用户id，<br/>用于加密参数串（应该可以不要的）
	 * @return 订单中可分享商品的集合
	 */
	public Freebie getOrderFreeBie(String userId,String orderId){
		Freebie fb = this.fromOrderFreeBie(userId,orderId);
		return fb;
	}
	
	/**
	 * 获取用户在所有的订单中是否有符合买赠的商品，并给出分享连接
	 * <br/>用于在app的订单列表中展示分享按钮
	 * @param userId 用户id
	 * @return 用户订单中有符合买赠的商品时，返回买赠分享连接
	 * {@link #getUserFreebieProducts(String, int, int)}
	 */
	public Freebie getUserAllFreebies(String userId){
		Freebie fb = null;
		String data = freebieBaseService.getUserAllFreebies(userId);
		if(data!=null){
			JSONObject jsonObj = JSONObject.fromObject(data);
			String code = jsonObj.getString("code");
			if(code!=null ||"0".equals(code)){
				JSONObject contentObj = jsonObj.getJSONObject("content");
				String isHaveShareSpu = contentObj.getString("isHaveShareSpu");
				if(isHaveShareSpu!=null && "1".equals(isHaveShareSpu)){
					fb = new Freebie();
					fb.setIsMorethan("1");
					StringBuffer url = new StringBuffer();
					url.append(commonService.getShangpinDomain());
					url.append(Constants.FREEBIE_520_SHARE_M_LIST_URL);
					fb.setWapurl(url.toString());//m站分享列表url
					fb.setWapTitle(Constants.FREEBIE_520_SHARE_M_TITLE);
					fb.setShareName(Constants.FREEBIE_520_SHARE_BUTTON_NAME);//app按钮的文字
				}
			}
		}
		return fb;
	}
	/**
	 * 领取赠品 
	 * <br/>
	 * @param userId 领取人用户id
	 * @param skuId 领取的skuId
	 * @param addressId 领取人用户地址Id
	 * @param encodedParam 领取赠品时原始的加密串，{@link #decodeFreebieParam(String) 解密串}
	 * @return
	 */
	public boolean pickFreebie (String userId,String skuId,String addressId,String encodedParam){
		return false;
	}
	/**
	 * 判断某订单的，某sku是否已经领取 
	 * <br/>如果商品没有可被领取的也是不能领取的
	 * @param orderId 订单id
	 * @param skuId skuId
	 * @return
	 */
	public boolean isPicked(String orderId,String skuId){
		return false;
	}
	
	
	/**
	 * 加密分享url上面的参数 
	 * <br/>
	 * @param userId 用户id
	 * @param orderId 订单id
	 * @param skuId skuid 
	 * @return
	 */
	public static String encodeFreebieUrlParam(String userId,String orderId,String skuId, String sortNo){
		StringBuffer orgin= new StringBuffer();
		orgin.append(SPLIT_CHAR+userId);
		if(skuId!=null && !"".equals(skuId)){
			orgin.append(SPLIT_CHAR+skuId);
		}
		if(sortNo!=null && !"".equals(sortNo)){
			orgin.append(SPLIT_CHAR+sortNo);
		}
		try {
			return AESUtil.encrypt(orgin.toString(), freebieKey);
		} catch (Exception e) {
			logger.error("买赠参数加密失误，原始串：{}",orgin);
		}
		return "";
	}
	/**
	 * 将分享的url上的加密参数解密<br/>
	 * @param freebieParam 加密的参数
	 * @return 解密后的参数键值对,键有userId,orderId,skuId
	 */
	public static Map<String,String> decodeFreebieParam(String freebieParam){
		try {
			String fb=AESUtil.decrypt(freebieParam, freebieKey);
			String[] params=fb.split(SPLIT_CHAR);
			Map<String,String> map = new HashMap<>(params.length);
			map.put("orderId",params[0]);
			map.put("userId",params[1]);
			if(params.length>2){
				map.put("spu", params[2]);
			}
			if(params.length>3){
				map.put("sortNo", params[3]);
			}
			return map;
		} catch (Exception e) {
		}
		return null;
	}
	/***
	 * 初始分享列表页数据
	 * @param userid
	 * @param p
	 */
    public List<Product> initFreebieList(String userid) {
    	List<Product> list = this.fromUserFreebieProducts(userid);
		return list;
    }
    /**
     * 解密后根据sharePlat=1,2,3 判断是到商品详情页还是到活动页面
     * @param p
     * @return
     */
    public String judgmentPar(String parameter) {
        return null;
    }
    
    public Boolean checkParameter(String p) {
       return true;
        
    }
    
    /***
     * 初始化分享详情页
     * @param p
     */
    public ProductDetail initFreebieDetail(String p) {
    	Map<String, String> param = this.decodeFreebieParam(p);
    	if(param ==null){
    		return null;
    	}
    	String spu = param.get("spu");
    	ProductDetail productDetail = null;
    	try {
    		productDetail = aspBizProductDetailService.queryProductDetail(null, spu, null, null, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return productDetail;
    }
    
    
    /**
     * 分享买赠详情页 根据当前spu获取可换购的商品
     * @param price
     * @return
     */
    public List<Product> recommendProduct(String spu) {
    	List<String> spus = new ArrayList<String>();
    	spus.add(spu);
    	String serachList = searchService.searchProductList(spus);
    	List<Product> oldProList = this.getSerachProList(serachList);
    	if(oldProList==null || oldProList.isEmpty()){
    		return null;
    	}
    	Product oldPro = oldProList.get(0);
    	Double limitedPrice = Double.parseDouble(oldPro.getLimitedPrice());
    	List<ProductSreach> searchList = null;
		int maxSize = Integer.parseInt(Constants.FREEBIE_520_SHARE_PRO_SIZE);
		String searchdataLeft = null;
		String searchdataRight = null;
		try {
			searchdataLeft = searchService.searchActivityProductList(Constants.FREEBIE_520_SHARE_TOPICID, "1",(maxSize+1)+"", null, null, "0-"+limitedPrice, null, null, null, "price+desc", null, null, "0", null);
			searchdataRight = searchService.searchActivityProductList(Constants.FREEBIE_520_SHARE_TOPICID, "1",maxSize+"", null, null, limitedPrice+"-", null, null, null, "price+asc", null, null, "0", null);
		} catch (UnsupportedEncodingException | DocumentException e) {
			e.printStackTrace();
		}
		List<Product> productList = null;
		if(searchdataLeft != null &&searchdataRight != null){
			SearchProductJson<ProductSreach> leftProducts = JsonUtil.fromJson(searchdataLeft, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
			SearchProductJson<ProductSreach> rightProducts = JsonUtil.fromJson(searchdataRight, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
			if(leftProducts!=null && rightProducts!=null){
				searchList = new ArrayList<ProductSreach>();
				int leftTotal = leftProducts.getTotal();
				int rigthTotal = rightProducts.getTotal();
				int rigth;
				if(leftTotal == 0){
					rigth = maxSize;
				}else if(rigthTotal <= maxSize/2){
					rigth = rigthTotal;
				}else if(leftTotal <maxSize){
					rigth = maxSize - leftTotal;
				}else {
					rigth = maxSize/2;
				}
				int left = maxSize-rigth+1;
				if(left > leftTotal){
					left = leftTotal;
				}
				for (int i = 0; i < rigth; i++) {
					searchList.add(rightProducts.getDocs().get(i));
				}
				while(left > 0 && searchList.size() < maxSize){
					for (ProductSreach productSreach : leftProducts.getDocs()) {
						if(!oldPro.getProductId().equals(productSreach.getProductNo())){
							searchList.add(productSreach);
						}
					}
					left --;
				}
			}
			Collections.sort(searchList, new Comparator<ProductSreach>() {
			@Override
			public int compare(ProductSreach ps1, ProductSreach ps2) {
				return ps1.getLimitedPrice().compareTo(ps2.getLimitedPrice());
			}
		});
			productList = SearchUtil.initJsonProductList(searchList, "1", null);
    	}
    	return productList;
    }
	
    
	/**
	 * 调用主站 拼装数据
	 * @param userId
	 * @param orderId
	 * @return
	 */
	private Freebie fromOrderFreeBie(String userId, String orderId) {
		Freebie fb = null;
		String data = freebieBaseService.getOrderDeatil(userId,orderId);
		List<FreebiePro> freebieProList = this.getFreebieProList(data);
		if(freebieProList!=null && !freebieProList.isEmpty()){
			int size = freebieProList.size();
			fb = new Freebie();
			fb.setShareName(Constants.FREEBIE_520_SHARE_BUTTON_NAME);//app按钮的文字
			String isMorethan=null;//是否多于1件，0否:本地分享,1是多件，跳wapurl"
			if(size==1){
				isMorethan = "0";
				FreebiePro freebiePro = freebieProList.get(0);
				fb.setUrl(this.getShareUrl(userId, orderId, freebiePro.getSpu(),"001"));//分享的url
				fb.setDesc(Constants.FREEBIE_520_SHARE_DESC);//分享描述
				fb.setPic(Constants.FREEBIE_520_SHARE_PIC);//分享消息的图片
			}else{
				isMorethan = "1";
				StringBuffer url = new StringBuffer();
				url.append(commonService.getShangpinDomain());
				url.append(Constants.FREEBIE_520_SHARE_M_LIST_URL);
				fb.setWapurl(url.toString());//m站分享列表url
				fb.setWapTitle(Constants.FREEBIE_520_SHARE_M_TITLE);
			}
			fb.setIsMorethan(isMorethan);
		}
		return fb;
	}
	
	/**
	 * 获取用户下所有可参与活动的商品
	 * @param userId
	 * @return
	 */
	private List<Product> fromUserFreebieProducts(String userId) {
		//调取主站接口，得到spus的列表
		String data = freebieBaseService.getUserFreebieProductList(userId);
		
		List<FreebiePro> freebieProList = getFreebieProList(data);
		if(freebieProList == null){
			return null;
		}
		
		List<List<String>> spuList = new ArrayList<>();
		List<String> spuSearch = new ArrayList<>();
		
		int count =1;
		for (int i = 0, n = freebieProList.size(); i < n; i++) {
			FreebiePro freebiePro = freebieProList.get(i);
			spuSearch.add(freebiePro.getSpu());
			if(count==20||i==n-1){
				spuList.add(spuSearch);
				count=1;
				spuSearch = new ArrayList<>();
			}
			count++;
		}
		List<String> results = new ArrayList<>();
		for (List<String> list : spuList) {
			String searchStr = searchService.searchProductList(list);
			results.add(searchStr);
		}
		
		Map<String, Product> proMap = new HashMap<String, Product>();
		for (String searchPro : results) {
			if (org.apache.commons.lang3.StringUtils.isNotBlank(searchPro)) {
				SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(searchPro, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
				if(productJson!=null){
					//查询结果大于0
					List<ProductSreach> searchListDoc = productJson.getDocs();
					if(productJson.getTotal()>0 && searchListDoc!=null && searchListDoc.size()>0){
						List<Product> productList = SearchUtil.initJsonProductList(searchListDoc, "1", null);
						for (Product product : productList) {
							proMap.put(product.getProductNo(), product);
						}
					}
				}
			}
		}
		List<Product> resultProList = new ArrayList<Product>();
		for (int i = 0, n = freebieProList.size(); i < n; i++) {
			FreebiePro freebiePro = freebieProList.get(i);
			int num = Integer.parseInt(freebiePro.getPayNum());
			for (int j = 0; j <num ; j++) {
				String payNum = null;
				Product pro = proMap.get(freebiePro.getSpu());
				if(j <10){
					payNum += "00";
				}else if(payNum.length()==2){
					payNum += "0";
				}
				pro.setType(payNum);//spu的顺序标识
				
				resultProList.add(proMap.get(freebiePro.getSpu()));
			}
		}
		
		return resultProList;
	}
	/**
	 * 获取加密后的url
	 * @param userId
	 * @param orderId
	 * @param spu
	 * @return
	 */
	public String getShareUrl(String userId,String orderId,String spu,String sortNo){
		StringBuffer url = new StringBuffer();
		url.append(commonService.getShangpinDomain());
		url.append("?p=").append(this.encodeFreebieUrlParam(userId, orderId, spu,sortNo));
		return url.toString();
	}
	
	/**
	 * 获取app的活动按钮 信息
	 * @param data
	 * @return
	 */
	private List<FreebiePro> getFreebieProList(String data){
		JSONObject jsonObj = JSONObject.fromObject(data);
		String code = jsonObj.getString("code");
		if(code!=null ||"0".equals(code)){
			JSONObject contentObj = jsonObj.getJSONObject("content");
			String productListStr = contentObj.getString("productList");
			List<FreebiePro> productList = JsonUtil.fromJson(productListStr, new TypeToken<List<FreebiePro>>(){}.getType());
			return productList;
		}
		return null;
	}
	
	
	/**
	 * 查询用户下订单下的 某个spu的购买数量
	 * @param userId 用户id
	 * @param OrderId 订单id
	 * @param spu spuid
	 * @return
	 */
	public String checkSpuNum(String userId,String OrderId,String spu){
		String data = freebieBaseService.getUserFreebieProductList(userId);
		List<FreebiePro> freebieProList = getFreebieProList(data);
		for (FreebiePro freebiePro : freebieProList) {
			if(OrderId.equals(freebiePro.getOrderId())&&spu.equals(freebiePro.getSpu())){
				return freebiePro.getPayNum();
			}
		}
		return null;
	}
	/**
	 * 判断某个spu是否是属于一个活动 
	 * @param topic
	 * @param spu
	 * @return
	 */
	public boolean checkSpuIsTopic(String topic,String spu){
		String data = searchService.searchSpuIsInTopic(topic,spu);
		SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(data, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
		if(productJson!=null &&productJson.getTotal()==1){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据搜索的json返回对应的product 
	 * @param data
	 * @return
	 */
	private List<Product> getSerachProList(String data){
		List<Product> list = null;
		SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(data, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
		if(productJson!=null){
			logger.debug("searchProductListFloor   result:{description:"+productJson.getDiscription()+", qtime:"+productJson.getQTime()+", status:"+productJson.getStatus()+" ,total:"+productJson.getTotal()+" ,sid:"+productJson.getSid()+" ,dosCount:"+productJson.getDocs().size());
			//查询结果大于0
			List<ProductSreach> searchListDoc = productJson.getDocs();
			if(productJson.getTotal()>0 && searchListDoc!=null && searchListDoc.size()>0){
				list = SearchUtil.initJsonProductList(searchListDoc, "1", null);
			}
		}
		return list;
	}
}
