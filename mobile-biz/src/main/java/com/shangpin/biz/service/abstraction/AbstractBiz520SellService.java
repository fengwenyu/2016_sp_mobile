package com.shangpin.biz.service.abstraction;

import java.util.List;

import com.shangpin.biz.bo.base.ResultObjOne;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.FreebieBaseService;
import com.shangpin.biz.bo.Freebie;
import com.shangpin.biz.bo.freeBie.FreebiePro;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.FreeBie520Util;
import com.shangpin.utils.JsonUtil;


public abstract class AbstractBiz520SellService {
	
	@Autowired
	private FreebieBaseService freebieBaseService;
	
	@Autowired
	private CommonService commonService;
	
	
	/**
	 * 查询订单下是否有可参与活动的商品
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public Freebie getOrderFreeBie(String userId, String orderId) {
		Freebie fb = null;
		String data = freebieBaseService.getOrderDeatil(userId,orderId);
		List<FreebiePro> freebieProList = this.getFreebieProList(data);
		if(freebieProList!=null && !freebieProList.isEmpty()){
			int size = freebieProList.size();
			fb = new Freebie();
			fb.setShareName(Constants.FREEBIE_520_SHARE_BUTTON_NAME);//app按钮的文字
			String isMorethan=null;//是否多于1件，0否:本地分享,1是多件，跳wapurl"
			if(size!=0){
				isMorethan = "1";
				StringBuffer url = new StringBuffer();
				url.append(commonService.getShangpinDomain());
				url.append(Constants.FREEBIE_520_SHARE_M_LIST_URL);
				fb.setWapurl(url.toString());//m站分享列表url
				fb.setWapTitle(Constants.FREEBIE_520_SHARE_M_TITLE);
			}
			/*if(size==1){
				isMorethan = "0";
				FreebiePro freebiePro = freebieProList.get(0);
				fb.setUrl(this.getShareUrl(userId, orderId,freebiePro.getSpu(), freebiePro.getSku(),"001"));//分享的url
				fb.setDesc(Constants.FREEBIE_520_SHARE_DESC);//分享描述
				fb.setPic(Constants.FREEBIE_520_SHARE_PIC);//分享消息的图片
				fb.setTitle(Constants.FREEBIE_520_SHARE_M_TITLE);
			}else{
				isMorethan = "1";
				StringBuffer url = new StringBuffer();
				url.append(commonService.getShangpinDomain());
				url.append(Constants.FREEBIE_520_SHARE_M_LIST_URL);
				fb.setWapurl(url.toString());//m站分享列表url
				fb.setWapTitle(Constants.FREEBIE_520_SHARE_M_TITLE);
			}*/
			fb.setIsMorethan(isMorethan);
		}
		return fb;
	}
	
	/**
	 * 查询用户下是否有可参与活动的商品
	 * @param userId
	 * @return
	 */
	public Freebie getUserAllFreebies(String userId) {
 		Freebie fb = null;
		String data = freebieBaseService.getUserAllFreebies(userId);
		if(data!=null){
			JSONObject jsonObj = JSONObject.fromObject(data);
			String code = jsonObj.getString("code");
			if(code!=null &&"0".equals(code)){
				if(jsonObj.getString("content")!=null && !"".equals(jsonObj.getString("content"))&&jsonObj.getString("content").contains("isHaveShareSpu")){
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
		}
		return fb;
	}


	public String getShareUrl(String userId, String orderId,String spu, String sku,
			String sortNo) {
		StringBuffer url = new StringBuffer();
		url.append(commonService.getShangpinDomain());
		url.append(Constants.FREEBIE_520_SHARE_M_DETAIL_URL);
		url.append("?p=").append(FreeBie520Util.encodeFreebieUrlParam(userId, orderId, spu, sku, sortNo));
		return url.toString();
	}

	
	/**
	 * 获取app的活动按钮 信息
	 * @param data
	 * @return
	 */
	public List<FreebiePro> getFreebieProList(String data){
		JSONObject jsonObj = JSONObject.fromObject(data);
		String code = jsonObj.getString("code");
		if(code!=null && "0".equals(code)){
			if(jsonObj.getString("content")!=null && !"".equals(jsonObj.getString("content"))&& jsonObj.getString("content").contains("productList")){
				JSONObject contentObj = jsonObj.getJSONObject("content");
				String productListStr = contentObj.getString("productList");
				List<FreebiePro> productList = JsonUtil.fromJson(productListStr, new TypeToken<List<FreebiePro>>(){}.getType());
				return productList;
			}
		}
		return null;
	}
}
