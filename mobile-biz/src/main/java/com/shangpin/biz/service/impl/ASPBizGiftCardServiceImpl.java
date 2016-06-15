package com.shangpin.biz.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Carriage;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.DeliveryVo;
import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardBuyProduct;
import com.shangpin.biz.bo.GiftCardCommit;
import com.shangpin.biz.bo.GiftCardKeyt;
import com.shangpin.biz.bo.GiftCardKeytContent;
import com.shangpin.biz.bo.GiftCardRechargePasswd;
import com.shangpin.biz.bo.GiftCardRecordList;
import com.shangpin.biz.bo.Pay;
import com.shangpin.biz.bo.PayType;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.orderUnion.AddressWrapper;
import com.shangpin.biz.bo.orderUnion.DeliveryWrapper;
import com.shangpin.biz.bo.orderUnion.Invoice;
import com.shangpin.biz.bo.orderUnion.InvoiceWrapper;
import com.shangpin.biz.bo.orderUnion.Product;
import com.shangpin.biz.bo.orderUnion.ProductLabel;
import com.shangpin.biz.bo.orderUnion.ProductWraper;
import com.shangpin.biz.bo.orderUnion.SettlementUnion;
import com.shangpin.biz.service.ASPBizGiftCardService;
import com.shangpin.biz.service.abstraction.AbstractBizGiftCardService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PayTypeUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class ASPBizGiftCardServiceImpl extends AbstractBizGiftCardService implements ASPBizGiftCardService {
	private static final Logger logger = LoggerFactory.getLogger(ASPBizGiftCardServiceImpl.class);
	@Override
	public ResultObjOne<GiftCardCommit> doGiftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle,
			String invoiceContent, String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType,String type,
														 String invoiceEmail,String invoiceTel) {

		ResultObjOne<GiftCardCommit> giftCardCommit = beGiftCardCommit(userId, addressId, invoiceFlag, invoiceAddressId, invoiceType, invoiceTitle, invoiceContent, express,
				orderFrom, buysIds, payTypeId, payTypeChildId, orderType,type, invoiceEmail, invoiceTel);
		if(giftCardCommit!=null&&giftCardCommit.getContent()!=null){
			giftCardCommit.getContent().setType(type);
			giftCardCommit.getContent().setSkucounts("1");
			giftCardCommit.getContent().setGiftcard("0");
			giftCardCommit.getContent().setGiftcardbalance("0");
		}
		return giftCardCommit;
	}

	@Override
	public ResultObjOne<GiftCardBuy> doGiftCardBuy(String userId, String skuId, String productId, String quantity,String categoryNo,String type,String eGiftCardAmount,String version) {
		ResultObjOne<GiftCardBuy> giftCardBuy = this.beGiftCardBuy(userId, skuId, productId, quantity, categoryNo, type, eGiftCardAmount);
		if(giftCardBuy!=null&&giftCardBuy.getContent()!=null&&giftCardBuy.getContent().getBuyId()!=null){
			giftCardBuy.getContent().setType(type);
			//设置默认支付宝支付方式
			PayType payType = null;
			if(giftCardBuy.getContent().getLastPayType()==null){
				payType= new PayType();
			}else if(StringUtils.isBlank(giftCardBuy.getContent().getLastPayType().getMainPayCode())){
				payType = giftCardBuy.getContent().getLastPayType();
			}
			if(payType!=null){
				payType.setMainPayCode("27");
				payType.setSubPayCode("57");
			}
			List<Pay> payList = ApiBizData.payWay("1", "1", version);
			giftCardBuy.getContent().setPayment(payList);
			//设置品牌为空
			giftCardBuy.getContent().getList().get(0).setBrandNameCN("");
			giftCardBuy.getContent().getList().get(0).setBrandNameEN("");
			if("1".equals(type)){
				//如果为电子卡 则修改运费减免信息为 运费减免（电子卡免运费）
				Carriage carriage=giftCardBuy.getContent().getCarriage();
				if(carriage!=null){
					giftCardBuy.getContent().getCarriage().setDesc("运费减免（电子卡免运费）");
				}
			}
			
		}
		return giftCardBuy;
	}

	@Override
	public String doGiftCardRechargePasswd(String userId, String orderId) {
	 ResultObjOne<GiftCardRechargePasswd>  giftCardRechargePasswd=beGiftCardRechargePasswd(userId, orderId);
	 if(giftCardRechargePasswd!=null && giftCardRechargePasswd.isSuccess()){
		 return giftCardRechargePasswd.getContent().getRechargePasswd();
	 }
		return "";
	}

	@Override
	public List<GiftCardKeytContent> getGiftCardRechargePasswd(String userId, String orderId) {
		ResultObjOne<GiftCardRechargePasswd> obj = beGiftCardRechargePasswd(userId, orderId);
		List<GiftCardKeytContent> list = new ArrayList<GiftCardKeytContent>();
		if (obj != null) {
			list =  (List<GiftCardKeytContent>) obj.getObj();
		}
		return list;
	}

	@Override
	public String getFromRecordList(String userId, String recordType, String pageIndex, String pageSize) throws Exception {
		GiftCardRecordList obj = beRecordList(userId, recordType, pageIndex, pageSize).getObj();
		obj.setUrl(Constants.WAP_URL + "giftCard/skipToGiftCardSend");
		return ApiBizData.spliceData(obj, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
	}

	@Override
	public List<GiftCardKeytContent> getGiftCardKeyt(String userId,
			String orderId) {
		GiftCardKeyt giftCardKeyt=queryGiftCardSecretKey(orderId,userId);
		if(giftCardKeyt!=null){
			return giftCardKeyt.getList();
		}
		return null;
	}

	@Override
	public String getGiftCardRechargePasswdByCardId(String userId, String cardId) {
		String json = fromGiftCardRechargePasswdByCardId(userId, cardId);
		logger.info("查询秘钥json："+json+"giftCardId:"+cardId);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		return json;
	}

	@Override
	public String doGiftCardBuyToSettlementUnion(ResultObjOne<GiftCardBuy> giftCardBuy,String ver) {
		ResultObjOne<SettlementUnion> objOne = new ResultObjOne<>();
		if(giftCardBuy==null||giftCardBuy.getObj()==null){
			return null;
		}
		GiftCardBuy cardBuy = giftCardBuy.getObj();
		SettlementUnion union = new SettlementUnion();
		union = cardBuyToSettlementUnion(cardBuy,union,ver);
		objOne.setCode(giftCardBuy.getCode());
		objOne.setMsg(giftCardBuy.getMsg());
		objOne.setContent(union);
		return JsonUtil.toJson(objOne);
	}
	
	private SettlementUnion cardBuyToSettlementUnion(GiftCardBuy cardBuy, SettlementUnion union,String ver) {
		union.setBuyId(cardBuy.getBuyId()==null?"":cardBuy.getBuyId());
		union.setIsCod("0");//礼品卡不支持货到付款
		union.setPayCategory("1");//礼品卡走国内支付
		union.setTotalSettlement(cardBuy.getTotalSettlement()==null?"":cardBuy.getTotalSettlement());
		union.setTotalAmount(cardBuy.getTotalAmount()==null?"":cardBuy.getTotalAmount());
		
		AddressWrapper received = new AddressWrapper();
		String type = cardBuy.getType();
		received.setValid("0");
		if("1".equals(type)){
			received.setPrompt("您选购的是电子礼品卡，订单支付成功后，您可以在支付成功的页面或 “我的尚品-礼品卡” 查看购买记录。");
		}else if("2".equals(type)){
			received.setValid("1");
		}
		Receive lastReceived = null;
		String lastReceiveId = cardBuy.getLastReceiveId();
		if(StringUtils.isNotBlank(lastReceiveId)){
			List<Receive> receives = cardBuy.getReceive();
			if(receives!=null && receives.size()>0){
				for (Receive receive : receives) {
					if(lastReceiveId.equals(receive.getId())){
						lastReceived = receive;
						break;
					}
				}
				//增加默认地址逻辑 如果匹配不上，则取第一个
				if(lastReceived==null){
					lastReceived = receives.get(0);
				}
			}
		}
		received.setLastReceived((lastReceived==null?new Receive():lastReceived));
		union.setReceived(received);
		
		DeliveryWrapper deliveryWrapper = new DeliveryWrapper();
		String lastDeliveryType = cardBuy.getLastDeliveryType();
		DeliveryVo lasetDeliveryVo = null;
		List<DeliveryVo> delivery = cardBuy.getDelivery();
		if(delivery!=null && delivery.size()>0&&StringUtils.isNotBlank(lastDeliveryType)){
			for (DeliveryVo deliveryVo : delivery) {
				if(lastDeliveryType.equals(deliveryVo.getId())){
					lasetDeliveryVo = deliveryVo;
					break;
				}
			}
		}
		deliveryWrapper.setLastDelivery(lasetDeliveryVo==null?new DeliveryVo():lasetDeliveryVo);
		deliveryWrapper.setList(delivery);
		received.setDelivery(deliveryWrapper);
		union.setReceived(received);
		
		ProductWraper domesticProduct = new ProductWraper();
		domesticProduct.setTitle("国内商品");
		domesticProduct.setBuyId(cardBuy.getBuyId()==null?"":cardBuy.getBuyId());
		List<Product> list = new ArrayList<>();
		List<GiftCardBuyProduct> giftCardList = cardBuy.getList();
		for (GiftCardBuyProduct giftCard : giftCardList) {
			Product product = new Product();
			try {
				BeanUtils.copyProperties(product, giftCard);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(product.getPriceDesc()==null){
				product.setPriceDesc("");
			}
			list.add(product);
		}
		domesticProduct.setList(list);
		Carriage carriage = cardBuy.getCarriage();
		ProductLabel carriageProductLabel = new ProductLabel();
		carriageProductLabel.setTitle("运费");
		String desc = "";
		if("1".equals(type)){
			desc = "(免运费)";
		}else if("2".equals(type)){
			if(cardBuy.getTotalAmount()!=null &&Integer.parseInt(cardBuy.getTotalAmount())>=499){
				desc = "(会员免运费)";
			}else{
				desc = carriage.getDesc();
			}
		}
		carriageProductLabel.setDesc("￥"+carriage.getAmount()+" "+desc);
		domesticProduct.setCarriage(carriageProductLabel);
		union.setDomesticProduct(domesticProduct);

		InvoiceWrapper invoice = new InvoiceWrapper();
		invoice.setInvoiceButtonStatus("0");
		invoice.setValid("1");
		invoice.setTitle("是否开发票");
		invoice.setPrompt("");
		Invoice lastInvoice = new Invoice();
		lastInvoice.setInvoiceType("1");
		lastInvoice.setInvoiceTitle("个人");
		lastInvoice.setInvoiceContent("商品全称");
		lastInvoice.setInvoiceEmail("");
		lastInvoice.setInvoiceTel("");
		lastInvoice.setInvoiceDesc("温馨提示：根据国家税务政策规定，将全面使用电子发票。电子发票具有纸制发票的所有法律效力、用途及使用规定，成功开票后您可以到个人中心下载电子发票文档。原机打纸制发票停用，如有疑问可在线咨询或致电客服！");
		String[] contentList = { "商品全称","箱包","饰品","钟表","化妆品","服装","鞋帽","眼镜"};
		lastInvoice.setContentList(contentList);
		invoice.setLastInvoice(lastInvoice);
		union.setInvoice(invoice);
		
		/*GiftCardWrapper cardWrapper = new GiftCardWrapper();
		cardWrapper.setGiftCardButtonStatus("0");
		cardWrapper.setValid("1");
		cardWrapper.setTitle("礼品卡");
		union.setGiftCard(cardWrapper);*/
		
		//设置订单支付方式
		PayType lastPay = cardBuy.getLastPayType();
		List<com.shangpin.biz.bo.orderUnion.Payment> paymentList = PayTypeUtil.getPaymentList(lastPay.getMainPayCode(),"2", "1", "0", "", ver);
		union.setPayments(paymentList);
		
		List<PriceShowVo> priceShowVos = cardBuy.getPriceShow();
		union.setPriceShow(priceShowVos);
		
		return union;
	}

	
}
