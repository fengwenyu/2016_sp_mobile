package com.shangpin.wireless.api.api2client.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.SubmitOrderServerVO;

/**
 * 返给客户端的json数据
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */

public class SubmitOrderAPIResponse {

	/**
	 *订单确定页返给客户端的json数据新版本
	 * 
	 * @Author: wangfeng
	 * @CreatDate: 2014-04-24
	 * @Return
	 */
	public JSONObject objJson(SubmitOrderServerVO submitOrderServerVO) {
		JSONObject obj = new JSONObject();
		obj.put("code", submitOrderServerVO.getCode());
		obj.put("msg", submitOrderServerVO.getMsg());
		JSONObject content=new JSONObject();
		SubmitOrderVO submitOrderVO=null;
		JSONObject giftcardinfo=new JSONObject();
		if ("0".equals(submitOrderServerVO.getCode())){
		    submitOrderVO=submitOrderServerVO.getSubmitOrderVO();
		    content.put("payamount", submitOrderVO.getAmount());
		    content.put("carriage", submitOrderVO.getCarriage());
		    content.put("cod", submitOrderVO.getCod());
		    content.put("codmsg", submitOrderVO.getCodmsg());
		    content.put("giftcard", submitOrderVO.getGiftcard());
		    if("1".equals(submitOrderVO.getGiftcard())){
		        giftcardinfo.put("systime", submitOrderVO.getSystime());             
		        giftcardinfo.put("expiretime", submitOrderVO.getExpiretime());
		        giftcardinfo.put("orderid", submitOrderVO.getOrderid());
		        giftcardinfo.put("mainOrderId", submitOrderVO.getOrderid());
		        giftcardinfo.put("date", submitOrderVO.getDate());
		        giftcardinfo.put("giftcardbalance", submitOrderVO.getGiftcardbalance());
		    }
		    content.put("giftcardinfo", giftcardinfo);
		}
		obj.put("content", content);
		return obj;
	}
	
	
	
	
}
