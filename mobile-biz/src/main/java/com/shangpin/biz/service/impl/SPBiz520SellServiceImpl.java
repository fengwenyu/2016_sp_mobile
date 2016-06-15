package com.shangpin.biz.service.impl;

import com.google.common.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.FreebieBaseService;
import com.shangpin.base.service.SearchService;
import com.shangpin.biz.bo.*;
import com.shangpin.biz.bo.freeBie.FreebiePro;
import com.shangpin.biz.service.SPBiz520SellService;
import com.shangpin.biz.service.SPBizProductService;
import com.shangpin.biz.service.SPBizReceiveService;
import com.shangpin.biz.service.abstraction.AbstractBiz520SellService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.SearchUtil;
import com.shangpin.utils.JsonUtil;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 立即购买接口的实现类
 * 
 */

@Service
public class SPBiz520SellServiceImpl extends AbstractBiz520SellService implements SPBiz520SellService {
	private static final Logger logger = LoggerFactory.getLogger(SPBiz520SellServiceImpl.class);
	
	@Autowired
	private FreebieBaseService freebieBaseService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private SPBizProductService spBizProductService;
	
	@Autowired
	private SPBizReceiveService spBizReceiveService;

	
	@Override
	public List<Product> initFreebieList(String userid) {
    	List<Product> list = this.fromUserFreebieProducts(userid);
		return list;
    }

	@Override
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
    	List<Product> searchList = null;
		int maxSize = Integer.parseInt(Constants.FREEBIE_520_SHARE_PRO_SIZE);
		String searchdataLeft = null;
		String searchdataRight = null;
		try {
			String cateNo=null;
			List<String> categoryNos = oldPro.getCategoryNo();
			if(categoryNos!=null && categoryNos.size()>0){
				String categoryNo = categoryNos.get(0);
				if(categoryNo!= null && !"".equals(categoryNo) && categoryNo.length()>6){
					cateNo = categoryNo.substring(0,6);
				}
			}
			searchdataLeft = searchService.searchActivityProductListNosellOut(Constants.FREEBIE_520_SHARE_TOPICID, "1",(maxSize+1)+"", null, null, "0-"+limitedPrice, null, null, cateNo, "1", null, null, "0", null);
			searchdataRight = searchService.searchActivityProductListNosellOut(Constants.FREEBIE_520_SHARE_TOPICID, "1",maxSize+"", null, null, (limitedPrice+0.1)+"-", null, null, cateNo, "2", null, null, "0", null);
		} catch (UnsupportedEncodingException | DocumentException e) {
			e.printStackTrace();
		}
		if(searchdataLeft != null &&searchdataRight != null){
			SearchResult serachResultLest = getSerachResult(searchdataLeft);
			SearchResult serachResultRight = getSerachResult(searchdataRight);
			if(serachResultLest!=null && serachResultRight !=null){
				searchList = new ArrayList<Product>();
				int leftTotal = Integer.parseInt(serachResultLest.getCount());
				int rigthTotal = Integer.parseInt(serachResultRight.getCount());
				if(leftTotal>maxSize+1){
					leftTotal = maxSize+1;
				}
				if(rigthTotal>maxSize){
					rigthTotal = maxSize;
				}
				int rigth;
				if(leftTotal == 0){
					rigth = maxSize;
				}else if(rigthTotal <= maxSize/2){
					rigth = rigthTotal;
				}else if(leftTotal <maxSize){
					rigth = maxSize - leftTotal +1;
				}else {
					rigth = maxSize/2;
				}
				int left = maxSize-rigth+1;
				if(left > leftTotal){
					left = leftTotal;
				}
				if(rigth >rigthTotal){
					rigth = rigthTotal;
				}
				logger.debug("leftTotal"+ leftTotal+ " rigthTotal"+ rigthTotal +" left:"+left+" right:"+rigth);
				if(serachResultRight.getProductList()!=null){
					for (int i = 0; i < rigth; i++) {
						searchList.add(serachResultRight.getProductList().get(i));
					}
				}
				if(serachResultLest.getProductList()!=null){
					for (Product pro : serachResultLest.getProductList()) {
						if(left>0 && searchList.size() < maxSize){
							if(!oldPro.getProductId().equals(pro.getProductId())){
								searchList.add(pro);
							}
						}
					left --;
					}
				}
			}
			Collections.sort(searchList, new Comparator<Product>() {
				@Override
				public int compare(Product ps1, Product ps2) {
					return Integer.parseInt(ps1.getLimitedPrice())-Integer.parseInt(ps2.getLimitedPrice());
				}
			});

		}
		logger.debug("searchList:"+searchList.size());
    	return searchList;
    }


	@Override
	public List<Product> fromUserFreebieProducts(String userId) {
		//调取主站接口，得到spus的列表
		String data = freebieBaseService.getUserFreebieProductList(userId);
		List<FreebiePro> freebieProList = getFreebieProList(data);
		if (freebieProList == null) {
			return null;
		}

		List<List<String>> spuList = new ArrayList<>();
		List<String> spuSearch = new ArrayList<>();

		int count = 1;
		for (int i = 0, n = freebieProList.size(); i < n; i++) {
			FreebiePro freebiePro = freebieProList.get(i);
			spuSearch.add(freebiePro.getSpu());
			if (count == 20 || i == n - 1) {
				spuList.add(spuSearch);
				count = 1;
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
				SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(searchPro, new TypeToken<SearchProductJson<ProductSreach>>() {
				}.getType());
				if (productJson != null) {
					//查询结果大于0
					List<ProductSreach> searchListDoc = productJson.getDocs();
					if (productJson.getTotal() > 0 && searchListDoc != null && searchListDoc.size() > 0) {
						List<Product> productList = SearchUtil.initJsonProductList(searchListDoc, "1", null);
						for (Product product : productList) {
							proMap.put(product.getProductId(), product);
						}
					}
				}
			}
		}
		List<Product> resultProList = new ArrayList<Product>();
		for (int i = 0, n = freebieProList.size(); i < n; i++) {
			FreebiePro freebiePro = freebieProList.get(i);
			int num = Integer.parseInt(freebiePro.getPayNum());
			//查询是否可领
			List<String> can_ling_nums = spBizReceiveService.receivedSkuSeq(userId, freebiePro.getOrderId(), freebiePro.getSku());
			Product product = proMap.get(freebiePro.getSpu());
			if(product!=null) {
				List<Product> tempList = new ArrayList<>();
				if (can_ling_nums.size() > 0 && tempList.size() < num) {
					for (String canLingNum : can_ling_nums) {
						Product product2 = new Product();
						BeanUtils.copyProperties(product, product2);
						product2.setType(canLingNum);
						tempList.add(product2);
					}
				}
				int j = 1;
				while (tempList.size() < num) {
					String payNum = null;
					Product product2 = new Product();
					BeanUtils.copyProperties(product, product2);
					product2.setType(null);//spu的顺序标识
					product2.setRefContent(null);
					if (j < 10) {
						payNum = "00" + j;
					} else if (payNum.length() == 2) {
						payNum = "0" + j;
					}
					if (!can_ling_nums.contains(payNum) && tempList.size() < num) {
						product2.setType(payNum);
						String shareUrl = this.getShareUrl(userId, freebiePro.getOrderId(), freebiePro.getSpu(), freebiePro.getSku(), payNum);
						product2.setRefContent(shareUrl);
						tempList.add(product2);
					}
					j++;
				}
				Collections.sort(tempList, new Comparator<Product>() {
					@Override
					public int compare(Product ps1, Product ps2) {
						return Integer.parseInt(ps1.getType()) - Integer.parseInt(ps2.getType());
					}
				});
				for (Product product1 : tempList) {
					resultProList.add(product1);
				}
			}
		}
			return resultProList;
	}


	@Override
	public String checkSpuNum(String userId, String OrderId, String spu) {
		String data = freebieBaseService.getUserFreebieProductList(userId);
		List<FreebiePro> freebieProList = getFreebieProList(data);
		for (FreebiePro freebiePro : freebieProList) {
			if(OrderId.equals(freebiePro.getOrderId())&&spu.equals(freebiePro.getSku())){
				return freebiePro.getPayNum();
			}
		}
		return null;
	}

	
	@Override
	public boolean checkSpuIsTopic(String spu) {
		String data = searchService.searchSpuIsInTopic(Constants.FREEBIE_520_SHARE_TOPICID,spu);
		JSONObject jsonObject = JSONObject.fromObject(data);
		if(jsonObject!=null){
			String subjectContainProductNoModel = jsonObject.getString("subjectContainProductNoModel");
			SearchProductJson<ProductSreach> productJson = JsonUtil.fromJson(subjectContainProductNoModel, new TypeToken<SearchProductJson<ProductSreach>>(){}.getType());
			if(productJson!=null &&productJson.getTotal()==1){
				return true;
			}
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

	@Override
	public boolean getOrderStatus(String userId, String orderId) {
		String data = freebieBaseService.getOrderDeatil(userId,orderId);
		JSONObject jsonObj = JSONObject.fromObject(data);
		String code = jsonObj.getString("code");
		boolean flag = false;
		if(code!=null ||"0".equals(code)){
			if(jsonObj.getString("content")!=null && !"".equals(jsonObj.getString("content")) && jsonObj.getString("content").contains("productList")){
				JSONObject contentObj = jsonObj.getJSONObject("content");
				String status = contentObj.getString("status");
				//订单状态 0: "已取消"; 10: "等待客服确认";  11:"待支付"; 12: "已确认"; 13: "收款已确认";
				// 14:"配货中"; 15:"已部分发货"; 16: "已全部发货"; 18:"COD收款已确认"; 97:"交易异常终止";
				// 98: "交易部分完成";  99: "交易全部完成";
				if(status !=null && !"0".equals(status)&& !"11".equals(status) && !"97".equals(status)){
					flag = true;
				}
			}
		}
		return flag;
	}

	@Override
	public ProductDetail getProductDetail(String spu) {
		ProductDetail productDetail = spBizProductService.findProductDetail(null, spu, null,null);
	/*	if(productDetail!=null){
			ProductBasic basic = productDetail.getBasic();
			if(basic!=null){
				boolean isHasSize = checkHasShortSize(basic);
				if(isHasSize){//是否断码 0:没有断码 ，1:断码
					basic.setIsShortSize("1");
				}else{
					basic.setIsShortSize("0");
				}
			}
		}*/
		return productDetail;
	}
	
	/**
	 * 是否断码 true：断码， false：不断码
	 * @return
	 */
/*	private boolean checkHasShortSize(ProductBasic basic){
		List<ProductFirstProps> firstProps = basic.getFirstProps();
		for (ProductFirstProps productFirstProps : firstProps) {
			if("1".equals(productFirstProps.getIsSecondProp())){
				List<ProductSecondProps> secondProps = productFirstProps.getSecondProps();
				for (ProductSecondProps productSecondProps : secondProps) {
					if(Integer.parseInt(productSecondProps.getCount())<=0){
						return true;
					}
				}
			}
		}
		return false;
	}*/
	
	
	private SearchResult getSerachResult(String xmlData){
		SearchResult searchResult = null;
		try {
			searchResult = SearchUtil.converXmlToObj(xmlData, "1", null, null);
		} catch (UnsupportedEncodingException | DocumentException e) {
			e.printStackTrace();
		}
		return searchResult;
	}

}
