package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONObject;


/**
 * 返给客户端的json数据
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */

public class PayOrderAPIResponse {

    /**
     * 返给客户端的json数据新版本
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public JSONObject objJson(JSONObject orderDetailObj,String giftcard) {
        JSONObject obj = new JSONObject();
        obj.put("code", orderDetailObj.getString("code"));
        obj.put("cod", orderDetailObj.getString("code"));
        obj.put("msg", orderDetailObj.getString("msg"));
        JSONObject content = new JSONObject();
        JSONObject giftcardinfo=new JSONObject();
        if ("0".equals(orderDetailObj.getString("code"))){
            JSONObject contentObj=JSONObject.fromObject(orderDetailObj.getString("content"));
            content.put("payamount", contentObj.getString("onlineamount"));
            content.put("status", contentObj.getString("status"));
            content.put("statusDesc", contentObj.getString("statusDesc"));
            content.put("canPay", contentObj.getString("canPay"));
            JSONObject carriageObj=JSONObject.fromObject(contentObj.getString("carriage"));
            content.put("carriage", carriageObj.getString("amount"));
            content.put("cod",  contentObj.getString("cod"));
            content.put("codmsg", contentObj.getString("codMsg"));
            content.put("giftcard",  giftcard);
            content.put("isusablegiftcardpay",  contentObj.getString("isusablegiftcardpay")==null?"":contentObj.getString("isusablegiftcardpay"));
            content.put("isEverUsedGiftCard",  contentObj.getString("isEverUsedGiftCard")==null?"":contentObj.getString("isEverUsedGiftCard"));


            //2.9.12添加
            content.put("payCategory",  contentObj.get("payCategory")==null?"":contentObj.getString("payCategory"));
            content.put("internalAmount",  contentObj.get("internalAmount")==null?"":contentObj.getString("internalAmount"));
            //2.9.12添加

          /*  JSONArray order=contentObj.getJSONArray("order");
            StringBuffer orderNo=new StringBuffer("");
            for(int i=0;i<order.size();i++){
            	String jsonOrderNo=order.getJSONObject(i).getString("orderno");
            	orderNo.append(jsonOrderNo);
            	if(i!=0&&i!=order.size()-1){
            		orderNo.append("|");
            	}
            }*/
            
            content.put("orderNo",  JSONObject.fromObject(contentObj.getString("shangpinOrder")).getString("orderId"));
            
            if("1".equals(giftcard)){
                giftcardinfo.put("systime", contentObj.getString("sysTime"));
                giftcardinfo.put("expiretime", contentObj.getString("expiryDate"));
                giftcardinfo.put("orderid", contentObj.getString("orderId"));
                giftcardinfo.put("mainOrderId", contentObj.getString("orderId"));//v2.9.0以后版本用
                giftcardinfo.put("date",  contentObj.getString("date"));
                giftcardinfo.put("giftcardbalance", contentObj.getString("giftCardBalance"));
            }
            content.put("giftcardinfo", giftcardinfo);
        }
        obj.put("content", content);
        return obj;
    }

}
