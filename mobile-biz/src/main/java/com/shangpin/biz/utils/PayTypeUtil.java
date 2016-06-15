package com.shangpin.biz.utils;

import com.shangpin.biz.bo.orderUnion.Payment;
import com.shangpin.biz.enums.PayType;
import com.shangpin.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class PayTypeUtil {
    /**
     * 获取支付方式集合
     * @param type 1国内 2海外 3分账
     * @param platform 支持平台:101:安卓, 102:IOS 0:全部
     * @param channel 渠道号
     * @param version app版本号
     * @return
     */
    public static List<PayType> getPaymentType(String type, String platform, /*String hasCod,*/ String channel, String version){
        PayType[] values = PayType.values();
        List<PayType> list = new ArrayList<>();
        for (PayType payType : values) {
            //关卡1：国内 海外 分账区分
            if (!payType.getType().equals(type)) {
                continue;
            }
            //关卡2：支持的平台
            if (!"0".equals(payType.getPlatform()) && !payType.getPlatform().equals(platform)) {
                continue;
            }
            //关卡3：是否包含线下支付  暂时不要
/*            if ("2".equals(payType.getWey()) && !"1".equals(hasCod)) {
                continue;
            }*/
            //关卡4：比较渠道号  不知渠道为何物
/*            if (!payType.getChannel().equals(channel)) {
                continue;
            }*/
            //关卡5：比较版本号 
            if (!StringUtil.verifyVersion(payType.getStartVersion(), payType.getEndVersion(), version)) {
                continue;
            }

            //通关者
            list.add(payType);
        }
        return list;
    }
    

     /**
      * 
      * @param mainPayId 默认支付主支付方式
      * @param isCod	是否支持货到付款   1是 2否
      * @param type		1国内 2海外 3分账
      * @param platform  支持平台:101:安卓, 102:IOS 0:全部
      * @param channel  渠道号
      * @param version  版本号
      * @return
      */
    public static List<Payment> getPaymentList(String mainPayId,String isCod,String type, String platform, /*String hasCod,*/ String channel, String version){
    	List<PayType> paymentType = PayTypeUtil.getPaymentType(type, platform, channel, version);
    	List<Payment> payments = null;
    	if(paymentType!=null && paymentType.size()>0){
    		payments = new ArrayList<>();
    		for (PayType payType : paymentType) {
    			Payment payment = new Payment();
    			payment.setId(payType.getId());
    			payment.setSubpayCode(payType.getSubId());
    			if(mainPayId.equals(payType.getId())){
    				payment.setIsSelected("1");
    			}else{
    				payment.setIsSelected("0");
    			}
    			//设置货到付款是否有效
                String enable = "1";
                if ("2".equals(payType.getWey()) && !"1".equals(isCod)) {
                    enable = "0";
                }
    			payment.setEnable(enable);
    			payment.setName(payType.getName());
    			payments.add(payment);
    		}
    	}
    	return payments;
    }
}
