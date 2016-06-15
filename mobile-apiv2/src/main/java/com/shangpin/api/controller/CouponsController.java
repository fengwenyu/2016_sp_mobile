package com.shangpin.api.controller;

import com.shangpin.biz.bo.*;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBiz520SellService;
import com.shangpin.biz.service.ASPBizCouponsService;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CouponsController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CouponsController.class);

	@Autowired
	private ASPBizCouponsService couponsService;
	
	@Autowired
	private ASPBizGiftCardService aspBizGiftCardService;
	
	@Autowired
	private ASPBiz520SellService freebieSrv;
	/**
	 * 使用优惠券，折扣码接口
	 * @param userId
	 * @param couponType 1使用优惠券； 2使用优惠码；
	 * @param couponCode couponType为1时，coupon为优惠券id；couponType为2时，coupon为优惠码串
	 * @param buyIds 购买商品的shodDetailId串，多个用“|”分割
	 * @param orderSource 从何处来到提交订单 1：从购物车 2：立即购买
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/useCoupons", method = { RequestMethod.POST, RequestMethod.GET })
	public String useCoupons( HttpServletRequest request,String type,String totalAmount,String promoAmount,String ticketAmount,String carriageAmount,String discountCode,String giftCardAmount,String postArea,String buyIds,String orderSource) {
	    final String userId = request.getHeader("userid");
	    if (!StringUtil.isNotEmpty(userId, totalAmount,promoAmount,carriageAmount,giftCardAmount, orderSource)) {
			return returnParamError();
		}
		String data = couponsService.useCoupons(userId, type, totalAmount,promoAmount,ticketAmount,carriageAmount,discountCode,giftCardAmount,postArea, buyIds, orderSource);
		return data;
	}
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/giveCoupons", method = { RequestMethod.POST, RequestMethod.GET })
	public String giveCoupons( HttpServletRequest request,String orderId,String payAmount, @RequestParam(required = false) String type) {
	    String userId = request.getHeader("userid");
	    String version = request.getHeader("ver");
	    if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
	    
	    ReturnGoods returnGoods = new ReturnGoods();
	    List<ReturnGoodsText> texDesc = new ArrayList<ReturnGoodsText>();
	    returnGoods.setTips("为保证发货效率，如您的订单包含多件商品且属于不同的发货地，则将会被拆分成多个子订单进行配送。");
	    //为了能让商品尽快送达您的手中，如您的订单中商品为多件，且属于不同仓库，则将会拆分多个子订单进行配送，产生的运费将由尚品网承担。
	    returnGoods.setTips("如订单中有多件商品，有可能拆分为多个子订单进行配送，由此产生的运费将由尚品网承担。\n\n"
	            + "由于奢侈品的特殊性质，极个别情况下，会出现缺码或缺货情况，一旦出现系统将为您自动退款，款项会原渠道返回。\n\n"
	    		+ "温馨提示：尚品网不会以订单无效等原因主动要求您提供银行卡信息操作退款，谨防诈骗！");
	    returnGoods.setTexDesc(texDesc);
	    returnGoods.setIsShow("1");
	    ReturnGoodsText text1 = new ReturnGoodsText();
	    ReturnGoodsText text2 = new ReturnGoodsText();
	    ReturnGoodsText text3 = new ReturnGoodsText();
	    ReturnGoodsText text4 = new ReturnGoodsText();
	    texDesc.add(text1);
	    texDesc.add(text2);
	    texDesc.add(text3);
	    texDesc.add(text4);
	   
	    int payAmounts =  (int) Double.parseDouble(payAmount);
	    if(0<=payAmounts&&payAmounts<500){
	    	payAmounts = 50;
	    }else if(500<=payAmounts&&payAmounts<1000){
	    	payAmounts = 100;
	    }else if(1000<=payAmounts&&payAmounts<2000){
	    	payAmounts = 200;
	    }else if(2000<=payAmounts){
	    	payAmounts = 400;
	    }
//	    if(StringUtil.compareVersion("", "2.9.7", version) == 1){
//	    	if(0<=payAmounts&&payAmounts<500){
//		    	payAmounts = 50;
//		    }else if(500<=payAmounts&&payAmounts<1000){
//		    	payAmounts = 100;
//		    }else if(1000<=payAmounts&&payAmounts<2000){
//		    	payAmounts = 200;
//		    }else if(2000<=payAmounts){
//		    	payAmounts = 400;
//		    }
//		}else{
//			if(0<=payAmounts&&payAmounts<500){
//		    	payAmounts = 170;
//		    }else if(500<=payAmounts&&payAmounts<1000){
//		    	payAmounts = 370;
//		    }else if(1000<=payAmounts&&payAmounts<2000){
//		    	payAmounts = 770;
//		    }else if(2000<=payAmounts){
//		    	payAmounts = 1270;
//		    }
//		}
	    
	    text1.setDesc("感谢您对尚品网的支持，特赠送您");
	    text1.setIsClick("0");
	    text1.setFontSize("12");
	    text1.setColor("#888888");
	    text2.setDesc("¥"+payAmounts+"优惠券");
	    text2.setIsClick("0");
	    text2.setFontSize("12");
	    text2.setColor("#c62026");
	    text3.setDesc("，将在5分钟内充值到您的账户。");
	    text3.setIsClick("0");
	    text3.setFontSize("12");
	    text3.setColor("#888888");
	    text4.setDesc("查看优惠券 >");
	    text4.setIsClick("1");
	    text4.setFontSize("12");
	    text4.setColor("#000000");
	    Gift gift = new Gift();
	    if ("1".equals(type)) {
	    	gift.setUrl(Constants.WAP_URL + "giftCard/skipToGiftCardSend");
	    	List<GiftCardKeytContent> list = aspBizGiftCardService.getGiftCardKeyt(userId, orderId);
	    	gift.setList(list);
	    }
	    returnGoods.setGift(gift);
	    if(StringUtil.compareVersion("", "2.9.12", version)==1){
			long end= DateUtils.strToDate("2016-05-26 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime();
			if(System.currentTimeMillis()<end){
				returnGoods.setFreebie(freebieSrv.getOrderFreeBie(userId, orderId));
			}
	    }
	    ResultObjOne<ReturnGoods> obj = new ResultObjOne<ReturnGoods>();
	    obj.setContent(returnGoods);
	    obj.setCode("0");
	    obj.setMsg("成功");
	    
		String data = obj.toJsonNullable();
		return data;
	}
	
	/**
	 * 使用优惠券，折扣码接口
	 * @param userId
	 * @param couponType 1使用优惠券； 2使用优惠码；
	 * @param couponCode couponType为1时，coupon为优惠券id；couponType为2时，coupon为优惠码串
	 * @param buyIds 购买商品的shodDetailId串，多个用“|”分割
	 * @param orderSource 从何处来到提交订单 1：从购物车 2：立即购买
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/useCouponProductList", method = { RequestMethod.POST, RequestMethod.GET })
	public String useCouponProductList( HttpServletRequest request,String payAmount,String pageIndex,String pageSize,String userLv) throws Exception {
		final String imei = "11111";
	    if (!StringUtil.isNotEmpty( pageIndex,pageSize,payAmount,imei)) {
	    	return returnParamError();
		}
	    String result=couponsService.searchUseCouponProductList( payAmount,pageIndex, pageSize, null,null,"1",null,null,null,null,null,null,userLv);
	    if(result==null){
	    	return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	    }
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/giftCardKeyt", method = { RequestMethod.POST, RequestMethod.GET })
	public String giftCardKeyt( HttpServletRequest request,String orderId) {
		String userId = request.getHeader("userid");
	    if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
	    String data  = aspBizGiftCardService.giftCardKeyt(userId, orderId);
	    if(data!=null){
	    	data=aesKeyt(data,userId);
	    }
		return data;
	}
	/**
	 * 密钥加密数据
	 * @param keytJsonData
	 * @param userId 用户id，没有16为则是前面补0，如果超过16位直接取前面16位作为key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String aesKeyt(String keytJsonData,String userId){
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
				List<Object> conentList=(List<Object>) content.get("list");
				for (int i = 0; i < conentList.size(); i++) {
					Map<String,Object> mos=(Map<String,Object>)conentList.get(i);
					String keyt=(String)mos.get("keyt");
					if(keyt!=null){
						String enc=AESUtil.encrypt(keyt, key);
						mos.put("keyt", enc);
					}
				}
			}
			
			return JSONUtils.obj2json(jsonObj);
			//System.out.println(AESUtil.decrypt("v2+VGdirjHm3sV9a1IvvBftsWX8JhrfvryrGcl5tfPE\u003d", userId));
		} catch (Exception e) {
			logger.error("aes加密密钥出错：",e);
			return null;
		}
	}
	/*public static void main(String[] args) {
		String keytJsonData="{\"code\": \"0\",\"msg\": \"成功\",\"content\": {\"list\": [{\"id\": \"788787\",\"orderId\": \"32132156332153215\",\"faceValue\": \"200\",\"desc\": \"200元面值\",\"keyt\": \"23424324243sdfsdfsdwe23\"}]}}";
		String key="";
		String userId="1234567890123456122";
		try {
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
				List<Object> conentList=(List<Object>) content.get("list");
				for (int i = 0; i < conentList.size(); i++) {
					Map<String,Object> mos=(Map<String,Object>)conentList.get(i);
					String keyt=(String)mos.get("keyt");
					if(keyt!=null){
						String enc=AESUtil.encrypt(keyt, key);
						mos.put("keyt", enc);
					}
				}
			}
			
			//return JsonUtil.toJson(jsonObj);
			System.out.println(JsonUtil.toJson(jsonObj));
			System.out.println(AESUtil.decrypt("W105p7FIAW1VMNU8478UhaXrm/lIpsm0q7DrDeWebFc\u003d", "1234567890123456"));
		} catch (Exception e) {
			logger.error("aes加密密钥出错：",e);
			//return null;
		}
	}*/
	
	

	/**
	 * 根据优惠劵批次号获得卡图片信息接口
	 * @param bId 批次号
	 * @param request
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "/findCouponInfo")
    public String returnDestination(@RequestParam String bId,HttpServletRequest request){
        final String imei = request.getHeader("imei");
        if (!StringUtil.isNotEmpty(imei, bId)) {
            return returnParamError();
        }
        try {
            return couponsService.getCouponsInfo(bId);
        } catch (Exception e) {
            logger.error("error:", e);
            return returnSystemError();
        }
    }

    /**
     * 
     * @Title: recharge
     * @Description: 一键充值
     * @paramc ardNo
     * @return String
     * @throws
     * @Create By wh
     * @Create Date 2016年01月13日
     */
    @RequestMapping(value = "/quickRecharge")
    @ResponseBody
    public String recharge(@RequestParam("cardNo") String cardNo, 
            HttpServletRequest request) {
        final String imei = request.getHeader("imei");
        String userId = request.getHeader("userid");
        if(userId==null||"".equals(userId)){
            userId = request.getParameter("userId");
        }
        if (!StringUtil.isNotEmpty(imei,userId,cardNo)) {
            return returnParamError();
        }
        try {
            return couponsService.activateCoupon(userId, "1", "coupon:" + cardNo, "30");
        } catch (Exception e) {
            logger.error("error:", e);
            return returnSystemError();
        }
    }
    
    /**
     * 提交订单页点击可选优惠券列表
     * @param buyId 购买ID
     * @param orderSource 订单来源：1表示购物车，2表示立即购买
     * @param pageIndex 页码
     * @param pageSize 大小
     * @param couponNo 本次已选中的优惠券编号，可选
     * @param useCouponNo 国内海外一起结算时,已被选中的优惠券,在此次请求时不能再选
     * @param request
     * @return
     */
    @RequestMapping(value = "/canUseCoupons", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String canUseCoupons(String buyId, String orderSource, String pageIndex, String pageSize, String couponNo, String useCouponNo, HttpServletRequest request){
    	String userId = request.getHeader("userid");
    	return couponsService.canUseCoupons(userId, pageIndex, pageSize, buyId, orderSource, couponNo, useCouponNo);
    }
    
}
