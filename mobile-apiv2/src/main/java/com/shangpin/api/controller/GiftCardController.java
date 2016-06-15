package com.shangpin.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.StringUtil;

/**
 * 礼品卡功能接口
 * 
 * @author zghw
 *
 */
@Controller
public class GiftCardController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(GiftCardController.class);
	@Autowired
	private ASPBizGiftCardService giftCardService;
	/*@Autowired
	private SPBizGiftCardService spgiftCardService;*/
	/**
	 * 礼品卡列表
	 * 
	 * @author zghw
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardList")
	public Object list(String type, String pageIndex, String pageSize, HttpServletRequest request, HttpServletResponse response) {
		if(!StringUtil.isNotEmpty(type)){
			return returnParamError();
		}
		if(pageIndex==null && pageSize==null){
			//暂时定死 保证客户端不分页
			pageIndex="1";
			pageSize="1000";
		}
		try {
			ResultObjOne<GiftCardProductList> giftCardProductList = giftCardService.beList("", type, pageIndex, pageSize);
			String result = giftCardProductList.toJsonNullable();
			return toResult(result);
		} catch (Exception e) {
			logger.error("error:", e);
			return returnSystemError();
		}
	}

	/**
	 * 礼品卡立即购买
	 * 
	 * @author zghw
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardBuy")
	public Object buy(String skuId, String productId, String amount,String type, HttpServletRequest request, HttpServletResponse response) {
		String userId = getAppUId(request);
		final String version = request.getHeader("ver");
		if (!StringUtil.isNotEmpty(userId,productId,amount,type)) {
			return returnParamError();
		}
		try {
			ResultObjOne<GiftCardBuy> giftCardBuy = giftCardService.doGiftCardBuy(userId, skuId, productId, amount, "", type, "0", version);
			String result = giftCardBuy.toJsonNullable();
			return toResult(result);
		} catch (Exception e) {
			logger.error("error:",e);
			return returnSystemError();
		}
	}

/*	*//**
	 * 礼品卡提交订单
	 * 
	 * @author zghw
	 *//*
	@ResponseBody
	@RequestMapping(value = "/giftCardCommit")
	public Object commit(String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle, String invoiceContent, String express,
			String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType,String type, HttpServletRequest request, HttpServletResponse response) {
		String userId = getAppUId(request);
		ResultObjOne<GiftCardCommit> giftCardCommit=giftCardService.doGiftCardCommit(userId, addressId, invoiceFlag, invoiceAddressId, invoiceType, invoiceTitle, invoiceContent, express, orderFrom, buysIds, payTypeId, payTypeChildId, orderType,type);
		String result=giftCardCommit.toJsonNullable();
		return result;
	}*/

	/**
	 * 礼品卡--电子卡充值
	 * 
	 * @author zghw
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardElectronicRecharge")
	public Object electronicRecharge(String cardPasswd, String orderId,HttpServletRequest request, HttpServletResponse response) {
		final String ver = request.getHeader("ver");
		String userId = getAppUId(request);
		if(!StringUtil.isNotEmpty(userId)){
			return returnParamError();
		}
		if(!StringUtil.isNotEmpty(cardPasswd) && !StringUtil.isNotEmpty(orderId)){
			return returnParamError();
		}
		try {
			//进行版本兼容，如果是2.9.6以上版本 就传空orderId
			if (StringUtil.compareVersion("", "2.9.7", ver) == 1) {
				orderId = "";
			}
			String json = giftCardService.fromElectronicRecharge(userId, orderId, cardPasswd);
			return toResult(json);
		} catch (Exception e) {
			logger.error("error:",e);
			return returnSystemError();
		}
	}

	/**
	 * 礼品卡记录列表
	 * 
	 * @author zghw
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardRecordList")
	public Object recordList(String recordType, String pageIndex, String pageSize, HttpServletRequest request, HttpServletResponse response) {
		String userId = getAppUId(request);
		if(!StringUtil.isNotEmpty(userId,recordType,pageIndex,pageSize)){
			return returnParamError();
		}
		try {
			String json = giftCardService.getFromRecordList(userId, recordType, pageIndex, pageSize);
			return toResult(json);
		} catch (Exception e) {
			logger.error("error:",e);
			return returnSystemError();
		}
	}

	/**
	 * 获取电子卡充值密码
	 * 
	 * @return
	 * @author zghw
	 */
	@ResponseBody
	@RequestMapping(value = "/giftCardRechargePasswd")
	public Object recordList(String orderId, HttpServletRequest request, HttpServletResponse response) {
		String userId = getAppUId(request);
		String cardId=request.getParameter("cardId");
		final String ver = request.getHeader("ver");
		if(!StringUtil.isNotEmpty(userId)){
			return returnParamError();
		}
		if(StringUtils.isEmpty(cardId)&&StringUtils.isEmpty(orderId)){
			return returnParamError();
		}
		if(StringUtils.isNotEmpty(cardId)){
			String json=giftCardService.getGiftCardRechargePasswdByCardId(userId,cardId);
			//TODO 2.9.9加密 encryptCardPwd
			if (compareVersion("2.9.9", ver) == 1) {
			    json=encryptCardPwd(json,userId);
            }
			return toResult(json);
		}
		try {
			String json = giftCardService.fromGiftCardRechargePasswd(userId, orderId);
			//TODO 2.9.9加密 encryptCardPwd
            if (compareVersion("2.9.9", ver) == 1) {
                json=encryptCardPwd(json,userId);
            }
			return toResult(json);
		} catch (Exception e) {
			logger.error("error:",e);
			return returnSystemError();
		}
	}
	/**
	 * 2.9.9需要加密key
	 * @param keytJsonData
	 * @param userId
	 * @return
	 */
	private static String encryptCardPwd(String keytJsonData,String userId){
		try {
			String key="";
			if(userId.length()<16){
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<16-userId.length();i++){
					sb.append('0');
				}
				sb.append(userId);key=sb.toString();
			}else{
				key=userId.substring(0, 16);
			}
			Map<String,Object> jsonObj=JSONUtils.json2map(keytJsonData);
			if("0".equals(jsonObj.get("code"))){
				Map<String,Object> content=(Map<String, Object>) jsonObj.get("content");
				String pwd= (String)content.get("rechargePasswd");
				if(StringUtils.isNotBlank(pwd)){
					String enc=AESUtil.encrypt(pwd, key);
					content.put("rechargePasswd", enc);
				}
			}
			return JSONUtils.obj2json(jsonObj);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 版本比较
	 * @param baseVersion 基于比较的版本
	 * @param currentVersion 客户端请求头部过来的版本号
	 * 如baseVersion = "2.9.6", currentVersion = "2.9.9",
	 * 需要返回currentVersion大于baseVersion
	 * 如果大于等于则返回1，反之则返回0
	 */
	private int compareVersion(String baseVersion, String currentVersion){
		String[] base = baseVersion.split("\\.");
		String[] current = currentVersion.split("\\.");
		for(int i = 0; i < current.length; i++){
			int c = Integer.parseInt(current[i]);
			int b = Integer.parseInt(base[i]);
			if(c > b){
				return 1;
			}
		}
		//两个版本相同
		if(Integer.parseInt(baseVersion.replace(".", "").trim()) == Integer.parseInt(currentVersion.replace(".", "").trim())){
			return 1;
		}
		return 0;
	}
	/*
	public static void main(String[] args) throws Exception {
		String keytJsonData="{\"code\": \"0\",\"msg\": \"成功\",\"content\": {\"rechargePasswd\":\"23424324243sdfsdfsdwe23\"}}";
		String userId="1234567890123456122";
		System.out.println(encryptCardPwd(keytJsonData,userId));
		String en="W105p7FIAW1VMNU8478UhaXrm/lIpsm0q7DrDeWebFc\u003d";
		System.out.println(new String(en.getBytes(),"utf-8"));
		System.out.println(AESUtil.decrypt("W105p7FIAW1VMNU8478UhaXrm/lIpsm0q7DrDeWebFc\u003d", userId.substring(0,16)));
		
		
	}*/
}
