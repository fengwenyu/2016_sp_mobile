package com.shangpin.biz.service.impl;

import java.util.List;




import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.GiftCard;
import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.GiftCardRechargePasswd;
import com.shangpin.biz.bo.GiftCardRecordList;
import com.shangpin.biz.bo.GiftCardStatus;
import com.shangpin.biz.bo.Pay;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizGiftCardService;
import com.shangpin.biz.service.abstraction.AbstractBizGiftCardService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.utils.JedisUtilFactory;
import com.shangpin.utils.JsonUtil;
@Service
public class SPBizGiftCardServiceImpl extends AbstractBizGiftCardService implements SPBizGiftCardService {
	@Autowired
	private ShangPinService shangPinService;
	
	private static final Logger logger = LoggerFactory.getLogger(SPBizGiftCardServiceImpl.class);
	public GiftCardRecordList getRecordList(String userId, String recordType, String pageIndex, String pageSize){
		ResultObjOne<GiftCardRecordList> obj= this.beRecordList(userId, recordType, pageIndex, pageSize);
		if(obj!=null && obj.isSuccess()){
			GiftCardRecordList list=obj.getObj();
			return list;
		}
		return null;
	}
	@Override
	public String getGiftCardRechargePasswd(String userId, String orderId) {
	 ResultObjOne<GiftCardRechargePasswd>  giftCardRechargePasswd=beGiftCardRechargePasswd(userId, orderId);
	 if(giftCardRechargePasswd!=null && giftCardRechargePasswd.isSuccess()){
		 return giftCardRechargePasswd.getContent().getRechargePasswd();
	 }
		return "";
	}
	@Override
	public GiftCardProductList getGiftCardProductList(String userId, String type, String pageIndex, String pageSize) {
		ResultObjOne<GiftCardProductList> obj=beList(userId, type, pageIndex, pageSize);
		if(obj!=null && obj.isSuccess()){
			return obj.getObj();
		}
		return null;
	}
	@Override
	public ResultObjOne<GiftCardBuy> doGiftCardBuy(String userId, String skuId, String productId, String quantity,String categoryNo,String type,String eGiftCardAmount) {
		ResultObjOne<GiftCardBuy> giftCardBuy = this.beGiftCardBuy(userId, skuId, productId, quantity, categoryNo, type, eGiftCardAmount);
		if(giftCardBuy!=null&&giftCardBuy.getContent()!=null){
			giftCardBuy.getContent().setType(type);
			//设置默认支付宝支付方式
			if(giftCardBuy.getContent().getLastPayType()!=null){
				giftCardBuy.getContent().getLastPayType().setMainPayCode("20");
				giftCardBuy.getContent().getLastPayType().setSubPayCode("38");
			}
			List<Pay> payList = ApiBizData.payWay("1", "1");
			giftCardBuy.getContent().setPayment(payList);
			//设置品牌为空
			giftCardBuy.getContent().getList().get(0).setBrandNameCN("");
			giftCardBuy.getContent().getList().get(0).setBrandNameEN("");
		}
		return giftCardBuy;
	}
	
	@Override
	public String saveGiftCardToDb(GiftCard giftCard) {
		String giftOrder = giftCard.getGiftOrder();
		String giftCardId = giftCard.getGiftCardId();
    	String buyerName = giftCard.getBuyerName();
        String sendPhoneNum = giftCard.getSendPhoneNum();
        String wishMsg = giftCard.getWishMsg();
        String wishPic = giftCard.getWishPic();
        String time = giftCard.getSendTime();
        String userid = giftCard.getUserId();
        String faceValue = giftCard.getFaceValue();
		//获取到秘钥
		String giftCatdPass = this.getGiftCardRechargePasswdByCardId(userid, giftCardId);
		if(StringUtils.isBlank(giftCatdPass)){
			return JsonUtil.toJson(ResultBaseNew.build("1", "无此订单信息"));
		}
		//拼装数据 存入redis 设置时效
		String key1 = "GIFTCARD:"+giftCardId+":"+sendPhoneNum;
		String key2 = "GIFTCARDID:"+giftCardId;
		String value1 = JsonUtil.toJson(giftCard);
		String value2 = sendPhoneNum+","+giftCatdPass;
		
		JedisUtilFactory.getDefaultJedisUtil().set(key1, value1);
		JedisUtilFactory.getDefaultJedisUtil().set(key2, value2);
		JedisUtilFactory.getDefaultJedisUtil().expire(key1, 48*60*60);
		JedisUtilFactory.getDefaultJedisUtil().expire(key2, 48*60*60);
		//JedisUtilFactory.getDefaultJedisUtil().expire(key1, 5*60);
		//JedisUtilFactory.getDefaultJedisUtil().expire(key2, 5*60);
		//调用主站 储存到数据库
		String result = shangPinService.saveGiftCardToDb(userid, giftOrder, giftCardId, buyerName, sendPhoneNum, time, wishMsg, wishPic);
		ResultBaseNew resultBaseNew = ResultBaseNew.format(result);
			
		if(resultBaseNew!=null && "0".equals(resultBaseNew.getCode())){
			return JsonUtil.toJson(ResultBaseNew.build("0", "添加成功"));
		}else{
			return JsonUtil.toJson(ResultBaseNew.build("1", "主站保存异常"));
		}
		
	}
	
    @Override
    public String getGiftCardPicAndMsg(GiftCard giftCard) {
    	String giftCardId = giftCard.getGiftCardId();
        String sendPhoneNum = giftCard.getSendPhoneNum();
        //拼装数据 读取redis 
        String key1 = "GIFTCARD:"+giftCardId+":"+sendPhoneNum;
		String key2 = "GIFTCARDID:"+giftCardId;
        String value1 = JedisUtilFactory.getDefaultJedisUtil().get(key1);//获取到giftCard
        String value2 = JedisUtilFactory.getDefaultJedisUtil().get(key2);//获取到giftCard
        logger.info("value1:"+value1+"value2:"+value2);
        if(StringUtils.isBlank(value2)){
    		return JsonUtil.toJson(ResultBaseNew.build("1", "该链接已过时！",new GiftCard()));
        }
        String[] val2 = value2.split(",");
        String val2PhoneNum = val2[0];
        if(!val2PhoneNum.equals(sendPhoneNum)){
        	return JsonUtil.toJson(ResultBaseNew.build("1", "该手机号链接已失效！",new GiftCard()));
        }
        if(StringUtils.isBlank(value1)){
        	return JsonUtil.toJson(ResultBaseNew.build("2", "该礼品卡已被领取！",new GiftCard()));
        }
        giftCard = JsonUtil.fromJson(value1, GiftCard.class);
        return JsonUtil.toJson(ResultBaseNew.success(giftCard));
    }
    
	@Override
	public String getGiftCardRechargePasswdByCardId(String userId,String giftCardId) {
		String json = fromGiftCardRechargePasswdByCardId(userId, giftCardId);
		logger.info("查询秘钥json："+json+"giftCardId:"+giftCardId);
		ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(json, Map.class);
		if(resultBaseNew!=null && resultBaseNew.isSuccess()){
			Map map =(Map<String, String>)resultBaseNew.getContent();
			return (String) map.get("rechargePasswd");
		 }
		return null;
	}
	
	@Override
	public String oneKeyRechargeGetPass(GiftCard giftCard) {
		String userId = giftCard.getUserId();
        String giftCardId = giftCard.getGiftCardId();
        String cardPasswd=null;
        String key ="GIFTCARDID:"+giftCardId;
        String value = JedisUtilFactory.getDefaultJedisUtil().get(key);
        if(org.apache.commons.lang3.StringUtils.isBlank(value)){
        	logger.info("链接已过去，秘钥已不存在");
        	return null;
        }
        String[] val = value.split(",");
        cardPasswd = val[1];
		return cardPasswd;
	}
	
	@Override
	public String oneKeyRechargeUpdateDb(String userId,String sendPhoneNum, String giftCardId,String recgargeTime){
		//移除redis中的键
		String key = "GIFTCARD:"+giftCardId+":"+sendPhoneNum;
		JedisUtilFactory.getDefaultJedisUtil().expire(key, 1);
		String data = shangPinService.updateGiftCardToDb(userId,giftCardId,recgargeTime);
		return data;
	}
	
	@Override
	public String getGiftCardStatusByGiftCardId(String giftCardId) {
		return shangPinService.getGiftCardStatusByGiftCardId(giftCardId);
	}
	
}
