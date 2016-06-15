package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.BuyNow;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.Pay;
import com.shangpin.biz.bo.PayType;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizBuyNowService;
import com.shangpin.biz.service.abstraction.AbstractBizBuyNowService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JsonUtil;

/**
 * 立即购买接口的实现类
 * 
 * @author qinyingchun
 */

@Service
public class ASPBizBuyNowServiceImpl extends AbstractBizBuyNowService implements ASPBizBuyNowService {

	@Override
	public String queryBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region, String version) throws Exception {
		String type = "0";
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.4", version) == 1) {
			type = "1";
		}
		/*//版本2.9.6，默认使用优惠券功能(开始)
		String isDefaultUseCoupon = "0";
		if(StringUtil.compareVersion("", "2.9.6", version) == 1){
			isDefaultUseCoupon = "1";
		}
		//版本2.9.6，默认使用优惠券功能(结束)*/	
		//版本2.9.8，默认使用优惠券功能(开始) 因2.9.6线上ios有问题 控制到2.9.8版本
		String isDefaultUseCoupon = "0";
		if(com.shangpin.utils.StringUtil.compareVersion("", "2.9.8", version) == 1){
			isDefaultUseCoupon = "1";
		}
		//版本2.9.8，默认使用优惠券功能(结束)
		String baseData = buyNowStr(userId, skuId, productId, activityId, amount, region, type,isDefaultUseCoupon);
		if (StringUtils.isEmpty(baseData)) {
			return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
		}
		ResultObjOne<BuyNow> resultObj = JsonUtil.fromJson(baseData, new TypeToken<ResultObjOne<BuyNow>>() {
		});
		if (Constants.SUCCESS.equals(resultObj.getCode())) {
			BuyNow buyNow = resultObj.getObj();
			String codFlag = buyNow.getCodFlag();
			if("2".equals(region)){
				String isHaveDirect = buyNow.getIsHaveDirect();
				String productAmount = buyNow.getProductAmount();
				boolean isDir = "1".equals(isHaveDirect);//有直发 走海外支付
				boolean isProAmount = productAmount!=null && Double.parseDouble(productAmount) >= Double.parseDouble(com.shangpin.biz.utils.Constants.PAY_OVERSEA_PRICE_LINE);
				//判断跳转海外支付还是国内支付
				if(!isDir&&isProAmount){
					region="1";
				}
			}
			List<Pay> payList = payWay(region, codFlag, version);
			if ("2".equals(region)) {
				PayType PayType=new PayType();
				PayType.setMainPayCode("30");
				PayType.setSubPayCode("107");
				buyNow.setLastPayType(PayType);
			}else if("1".equals(region)){//匹配国内支付宝
				PayType lastPayType = buyNow.getLastPayType();
				String mainPayCode = lastPayType.getMainPayCode();
				if(lastPayType==null||mainPayCode==null||"".equals(mainPayCode)||"32".equals(mainPayCode)){//32是主站写死数据，海外直接32
					PayType PayType=new PayType();
					PayType.setMainPayCode("27");
					PayType.setSubPayCode("57");
					buyNow.setLastPayType(PayType);
				}
			}
			buyNow.setPayment(payList);
			return ApiBizData.spliceData(buyNow, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		} else {
			return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), resultObj.getMsg()==null?CodeMsgEnum.MSG_BUY_ERROR.getInfo():resultObj.getMsg());
		}
	}

	/**
	 * 客户端公共支付方式
	 * 
	 * @param region
	 *            1:国内;2:海外
	 * @param codFlag
	 *            1:支持货到付款;2:不支持货到付款
	 * @return
	 */
	public static List<Pay> payWay(String region, String codFlag, String version) {
		List<Pay> payList = new ArrayList<Pay>();
		if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.4", version) == 1) {
			if ("2".equals(region)) {
				Pay pay0 = new Pay();
				pay0.setId("30");
				pay0.setName("支付宝钱包支付");
				pay0.setEnable("1");
				payList.add(pay0);
				Pay pay4 = new Pay();
				pay4.setId("32");
				pay4.setName("微信支付");
				pay4.setEnable("1");
				payList.add(pay4);

			} else if ("1".equals(region)) {
				Pay pay1 = new Pay();
				pay1.setId("27");
				pay1.setName("微信支付");
				pay1.setEnable("1");
				payList.add(pay1);

				Pay pay3 = new Pay();
				pay3.setId("20");
				pay3.setName("支付宝");
				pay3.setEnable("1");
				payList.add(pay3);

				Pay pay2 = new Pay();
				pay2.setId("19");
				pay2.setName("银联支付");
				pay2.setEnable("1");
				payList.add(pay2);

				Pay pay4 = new Pay();
				pay4.setId("2");
				pay4.setName("货到付款");
				if ("1".equals(codFlag)) {
					pay4.setEnable("1");
				} else {
					pay4.setEnable("0");
				}
				payList.add(pay4);
			}
		} else {
			if ("2".equals(region)) {
				Pay pay0 = new Pay();
				pay0.setId("30");
				pay0.setName("支付宝钱包支付");
				pay0.setEnable("1");
				payList.add(pay0);
				Pay pay4 = new Pay();
				pay4.setId("32");
				pay4.setName("微信支付");
				pay4.setEnable("1");
				payList.add(pay4);

			} else if ("1".equals(region)) {
				Pay pay1 = new Pay();
				pay1.setId("20");
				pay1.setName("支付宝");
				pay1.setEnable("1");
				payList.add(pay1);

				Pay pay3 = new Pay();
				pay3.setId("27");
				pay3.setName("微信支付");
				pay3.setEnable("1");
				payList.add(pay3);

				Pay pay2 = new Pay();
				pay2.setId("19");
				pay2.setName("银联支付");
				pay2.setEnable("1");
				payList.add(pay2);

				Pay pay4 = new Pay();
				pay4.setId("2");
				pay4.setName("货到付款");
				if ("1".equals(codFlag)) {
					pay4.setEnable("1");
				} else {
					pay4.setEnable("0");
				}
				payList.add(pay4);
			}
		}

		return payList;
	}

}
