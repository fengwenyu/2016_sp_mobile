package com.shangpin.api.controller;

import com.shangpin.biz.bo.DeliveryVo;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.bo.orderUnion.*;
import com.shangpin.biz.domain.settlement.BuyNowDo;
import com.shangpin.biz.domain.settlement.RefreshDo;
import com.shangpin.biz.enums.PayType;
import com.shangpin.biz.service.ASPBizSettlementService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PayTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * <br/>
 * Date: 2016/4/21<br/>
 *
 * @author ZRS
 * @since JDK7
 */
@Controller
@RequestMapping("/")
public class SettlementController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SettlementController.class);

    @Autowired
    private ASPBizSettlementService spBizSettlementService;

    /**
     * 购物车去结算页
     * 从2.9.12 （520）版本开始使用
     *
     * @param userid 用户Id
     * @param buyId  购物车id
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/cartSettlement", method = {RequestMethod.POST, RequestMethod.GET})
    public ContentBuilder<SettlementUnion> cartSettlement(
            @RequestHeader("userid") String userid,
            @RequestHeader("p") String product,
            @RequestHeader("ch") String channel,
            @RequestHeader("ver") String version,
            @RequestParam("buyId") String buyId) {

        logger.info("购物车结算,传入参数:userid={}&p={}&ch={}&ver={}&buyId={}", userid, product, channel, version, buyId);
        ContentBuilder<SettlementUnion> builder = new ContentBuilder<>();

        //到主站去结算
        ResultObjOne<SettlementUnion> resultObjOne = spBizSettlementService.cartSettlement(userid, buyId, "1");
        if (resultObjOne == null) {
            return builder.buildDefError();
        }
        logger.info("购物车结算,主站返回数据:resultObjOne={}",resultObjOne.toJsonNullable());

        if (!Constants.SUCCESS.equals(resultObjOne.getCode())) {
            return builder.buildDefError(resultObjOne.getMsg(),null);
        }

        SettlementUnion settlementUnion = resultObjOne.getContent();

//        try {
//            settlementUnion = JSONUtils.json2pojo("{\"received\":{\"valid\":\"1\",\"prompt\":\"\",\"lastReceived\":{\"id\":\"170641\",\"name\":\"这里\",\"province\":\"1\",\"provName\":\"北京\",\"city\":\"1\",\"cityName\":\"北京市\",\"area\":\"5\",\"areaName\":\"朝阳区\",\"town\":\"44139\",\"townName\":\"五环里\",\"addr\":\"三间房19号\",\"zip\":\"100026\",\"isd\":\"1\",\"tel\":\"13811176772\",\"cod\":\"1\",\"cardID\":\"\",\"isPos\":\"1\"},\"delivery\":{\"lastDelivery\":{\"id\":\"1\",\"desc\":\"工作日收货\"},\"list\":[{\"id\":\"1\",\"desc\":\"工作日收货\",\"isSelected\":\"1\"},{\"id\":\"2\",\"desc\":\"所有日期均可收货\",\"isSelected\":\"0\"},{\"id\":\"3\",\"desc\":\"双休日、节假日收货\",\"isSelected\":\"0\"}]}},\"domesticProduct\":{\"title\":\"国内商品\",\"buyId\":\"201603101626355133\",\"list\":[{\"id\":\"30003997\",\"name\":\"yy男装直发商品测试\",\"sku\":\"30003997001\",\"price\":\"100\",\"brandNameCN\":\"Candy_cn101501\",\"brandNameEN\":\"Candy_en101501\",\"pic\":\"http://192.168.9.71/group1/M00/2D/FF/wKgJR0tVXueASPNLAAAaAFraaJQ781.jpg\",\"quantity\":\"3\",\"priceTitle\":\"价格\",\"priceDesc\":\"\",\"countryPic\":\"\",\"attribute\":[{\"name\":\"颜色\",\"value\":\"黑色\"},{\"name\":\"尺码\",\"value\":\"29\"}],\"carriage\":{}}],\"carriage\":{\"title\":\"运费\",\"desc\":\"￥20\"},\"coupon\":{\"title\":\"现金券\",\"desc\":\"已优惠300元\",\"data\":\"070024081513\",\"count\":\"2\"},\"discount\":{\"title\":\"折扣码\",\"desc\":\"\",\"data\":\"\"}},\"invoice\":{\"valid\":\"1\",\"title\":\"是否开发票\",\"prompt\":\"\",\"lastInvoice\":{\"invoiceType\":\"1\",\"invoiceTitle\":\"个人\",\"invoiceContent\":\"商品全称\",\"contentList\":[\"商品全称\",\"箱包\",\"饰品\",\"钟表\",\"化妆品\",\"服装\",\"鞋帽\",\"眼镜\"],\"invoiceEmail\":\"\",\"invoiceTel\":\"\",\"invoiceDesc\":\"温馨提示：根据国家税务政策规定，将全面使用电子发票。电子发票具有纸制发票的所有法律效力、用途及使用规定；成功开票后您可以到个人中心--下载电子发票文档。原机打纸制发票停用，如有疑问可在线咨询或致电客服！\"}},\"giftCard\":{},\"priceShow\":[{\"type\":\"1\",\"amount\":\"300\",\"title\":\"商品金额\"},{\"type\":\"5\",\"amount\":\"20\",\"title\":\"运费\"},{\"type\":\"3\",\"amount\":\"300\",\"title\":\"优惠\"},{\"type\":\"4\",\"amount\":\"0\",\"title\":\"礼品卡\"},{\"type\":\"7\",\"amount\":\"20\",\"title\":\"应付金额\"}],\"buyId\":\"201603101626355133\",\"totalSettlement\":\"3\",\"totalAmount\":\"20\",\"prompt\":\"温馨提示:海外商品单件超过2000元,将使用国内支付方式进行支付\",\"isCod\":\"0\",\"payCategory\":\"1\"}",SettlementUnion.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //设置支付方式
        setPayment(settlementUnion,product,channel,version);

        //设置发票和礼品卡开关
        setInvoiceAndGiftCartButton(settlementUnion);

        return builder.buildDefSuccess(settlementUnion);

    }
    /**
     * 刷新结算页
     * 从2.9.12 （520）版本开始使用
     *
     * @param userid 用户Id
     * @param buyId 购物车id
     * @param orderSource 来源1购物车 2立即购买
     * @param deliveryId 收货方式ID
     * @param receivedId 收货地址ID
     * @param invoiceButtonStatus 发票是否打开
     * @param invoiceType 发票标识(1个人,2单位)
     * @param invoiceTitle 发票名称
     * @param invoiceContent 发票内容
     * @param invoiceEmail 发票邮箱
     * @param invoiceTel 发票手机号
     *
     * @param giftCardButtonStatus 礼品是否打开

     * @param subpayCode 当前用户选择的支付方式
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/refreshSettlement", method = { RequestMethod.POST, RequestMethod.GET })
    public ContentBuilder<SettlementUnion> refreshSettlement(
            @RequestHeader("userid") String userid,
            @RequestHeader("p") String product,
            @RequestHeader("ch") String channel,
            @RequestHeader("ver") String version,
            @RequestParam("buyId") String buyId,
            @RequestParam("orderSource") String orderSource,
            @RequestParam(value ="deliveryId",          required = false) String deliveryId,
            @RequestParam(value ="receivedId",          required = false) String receivedId,
            @RequestParam(value ="domesticCouponflag",  required = false) String domesticCouponflag,
            @RequestParam(value ="domesticCoupon",      required = false) String domesticCoupon,
            @RequestParam(value ="abroadCouponflag",    required = false) String abroadCouponflag,
            @RequestParam(value ="abroadCoupon",        required = false) String abroadCoupon,
            @RequestParam(value ="invoiceButtonStatus", required = false, defaultValue = "0") String invoiceButtonStatus,
            @RequestParam(value ="invoiceType",         required = false) String invoiceType,
            @RequestParam(value ="invoiceTitle",        required = false) String invoiceTitle,
            @RequestParam(value ="invoiceContent",      required = false) String invoiceContent,
            @RequestParam(value ="invoiceEmail",        required = false) String invoiceEmail,
            @RequestParam(value ="invoiceTel",          required = false) String invoiceTel,
//            @RequestParam(value ="invoiceDesc",         required = false) String invoiceDesc,
            @RequestParam(value ="giftCardButtonStatus",required = false, defaultValue = "0") String giftCardButtonStatus,
            @RequestParam(value ="subpayCode",          required = false) String subpayCode,
            @RequestParam(value ="type", required = false) String type
    ){

        logger.info("刷新购物车,传入参数:userid={}&p={}&ch={}&ver={}&buyId={}&orderSource={}&deliveryId={}&receivedId={}&domesticCouponflag={}&domesticCoupon={}&abroadCouponflag={}&abroadCoupon={}&" +
                "invoiceButtonStatus={}&invoiceType={}&invoiceTitle={}&invoiceContent={}&invoiceEmail={}&invoiceTel={}&giftCardButtonStatus={}&subpayCode={}",
                userid, product, channel, version, buyId,orderSource,deliveryId,receivedId,domesticCouponflag,domesticCoupon,abroadCouponflag,abroadCoupon,
                invoiceButtonStatus, invoiceType, invoiceTitle, invoiceContent, invoiceEmail, invoiceTel,giftCardButtonStatus, subpayCode);
        //参数校验
        ContentBuilder<SettlementUnion> builder = new ContentBuilder<>();
        if(!type.equals("0") && !type.equals("2") ){
            return builder.buildDefError("type参数错误",null);
        }

        String invoiceDesc = com.shangpin.api.utils.Constants.INVOCE_DESC;


        //TODO
        RefreshDo refreshDo = new RefreshDo();
        refreshDo.setUserId(userid);
        refreshDo.setBuyId(buyId);
        refreshDo.setOrderSource(orderSource);
        refreshDo.setDomesticCoupon(domesticCoupon);
        refreshDo.setDomesticCouponflag(domesticCouponflag);
        refreshDo.setAbroadCoupon(abroadCoupon);
        refreshDo.setAbroadCouponflag(abroadCouponflag);
        refreshDo.setIsUseGiftCard(giftCardButtonStatus); //没有传就默认的是0
        refreshDo.setReceivedId(receivedId);
        refreshDo.setReceivedId(receivedId);
        refreshDo.setType(type);

        //到主站去刷新结算
        ResultObjOne<SettlementUnion> resultObjOne = spBizSettlementService.refreshSettlement(refreshDo);

        if (resultObjOne == null) {
            return builder.buildDefError();
        }
        logger.info("刷新结算页，主站返回数据:resultObjOne={}",resultObjOne.toJsonNullable());

        if (!Constants.SUCCESS.equals(resultObjOne.getCode())) {
            return builder.buildDefError(resultObjOne.getMsg(),null);
        }

        SettlementUnion settlementUnion = resultObjOne.getContent();
        if(settlementUnion == null){
            return builder.buildDefError();
        }

        //线下支付方式 (货到付款) 1是 2否
        String isCod = settlementUnion.getIsCod();
        //线上支付方式: 1:国内，2：国外，3：支付宝分帐
        String payCategory = settlementUnion.getPayCategory();

        //设置收货日期
        AddressWrapper addressWrapper = settlementUnion.getReceived();
        if (!StringUtils.isBlank(deliveryId) && addressWrapper != null) {
            //地址可以选
            if ("1".equals(addressWrapper.getValid())) {
                List<DeliveryVo> deliveryList = addressWrapper.getDelivery().getList();
                for (DeliveryVo delivery : deliveryList) {
                    String select = "0";
                    //设置默认 重新设定选中
                    if(deliveryId.equals(delivery.getId())){
                        addressWrapper.getDelivery().getLastDelivery().setId(delivery.getId());
                        addressWrapper.getDelivery().getLastDelivery().setDesc(delivery.getDesc());
                        select = "1";
                    }
                    delivery.setIsSelected(select);
                }
            }
        }

        //设置发票
        InvoiceWrapper invoiceWrapper = settlementUnion.getInvoice();
        if(invoiceWrapper != null){
            //发票可以开
            if ("1".equals(invoiceWrapper.getValid())) {
                invoiceWrapper.setInvoiceButtonStatus(invoiceButtonStatus);
                Invoice lastInvoice = invoiceWrapper.getLastInvoice();
                if(lastInvoice == null){
                    lastInvoice = new Invoice();
                }
                lastInvoice.setInvoiceType(invoiceType);
                lastInvoice.setInvoiceTitle(invoiceTitle);
                lastInvoice.setInvoiceContent(invoiceContent);
                lastInvoice.setInvoiceEmail(invoiceEmail);
                lastInvoice.setInvoiceTel(invoiceTel);
                lastInvoice.setInvoiceDesc(invoiceDesc);
            }
        }

        //设置礼品卡开关
        GiftCardWrapper giftCardWrapper = settlementUnion.getGiftCard();
        if (giftCardWrapper != null) {
            //礼品卡可以用
            if ("1".equals(giftCardWrapper.getValid())) {
                giftCardWrapper.setGiftCardButtonStatus(giftCardButtonStatus);
            }
        }

        //获取支付方式列表
        List<PayType> paymentTypeList = PayTypeUtil.getPaymentType(payCategory, product, channel, version);
        List<Payment> list = new ArrayList<>();
        for (PayType payType : paymentTypeList) {
            Payment payment = new Payment();
            payment.setId(payType.getId());
            payment.setName(payType.getName());

            //设置货到付款是否有效
            String enable = "1";
            if ("2".equals(payType.getWey()) && !"1".equals(isCod)) {
                enable = "0";
            }
            payment.setEnable(enable);

            //设置用户选中的支付方式
            String select = "0";
            if(payType.getSubId().equals(subpayCode)){
                select = "1";
            }
            payment.setIsSelected(select);

            //设置子支付方式
            payment.setSubpayCode(payType.getSubId());
            list.add(payment);
        }
        settlementUnion.setPayments(list);

        return builder.buildDefSuccess(settlementUnion);
    }

    /**
     * 从2016.520版本开始使用
     * @param userId 用户id header中取
     * @param skuId skuId
     * @param productId 商品id
     * @param activityId 活动id
     * @param amount 购买商品数量
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/buyNowSettlement", method = { RequestMethod.POST, RequestMethod.GET })
    public ContentBuilder<SettlementUnion> buyNowSettlement(
            @RequestHeader("userid") String userId,
            @RequestHeader("p") String product,
            @RequestHeader("ch") String channel,
            @RequestHeader("ver") String version,
            @RequestParam("skuId") String skuId,
            @RequestParam("productId") String productId,
            @RequestParam("activityId") String activityId,
            @RequestParam("amount") Integer amount
    ) {

        logger.info("立即购买结算,传入参数:userid={}&p={}&ch={}&ver={}&skuId={}&productId={}&activityId={}&amount={}",
                userId, product, channel, version, skuId,productId,activityId,amount);

        BuyNowDo buyNowDo = new BuyNowDo();
        buyNowDo.setUserId(userId);
        buyNowDo.setActivityId(activityId);
        buyNowDo.setAmount(amount+"");
        buyNowDo.setSkuId(skuId);
        buyNowDo.setProductId(productId);
        buyNowDo.setIsDefaultUseCoupon("1");//默认使用优惠券

        //到主站去结算
        ResultObjOne<SettlementUnion> resultObjOne = spBizSettlementService.buyNowSettlement(buyNowDo);
        ContentBuilder<SettlementUnion> builder = new ContentBuilder<>();
        if (resultObjOne == null) {
            return builder.buildDefError();
        }
        logger.info("立即购买结算,主站返回数据:resultObjOne={}",resultObjOne.toJsonNullable());

        if (!Constants.SUCCESS.equals(resultObjOne.getCode())) {
            return builder.buildDefError(resultObjOne.getMsg(),null);
        }

        SettlementUnion settlementUnion = resultObjOne.getContent();
        if(settlementUnion == null){
            return builder.buildDefError();
        }

        //设置支付方式
        setPayment(settlementUnion,product,channel,version);

        //设置发票和礼品卡开关
        setInvoiceAndGiftCartButton(settlementUnion);

        return builder.buildDefSuccess(settlementUnion);

    }

    /**
     * 设置支付方式
     * @param settlementUnion
     * @param product
     * @param channel
     * @param version
     */
    private void setPayment(SettlementUnion settlementUnion,String product,String channel,String version){

        //线下支付方式 (货到付款) 1是 2否
        String isCod = settlementUnion.getIsCod();
        //线上支付方式: 1:国内，2：国外，3：支付宝分帐
        String payCategory = settlementUnion.getPayCategory();

        //获取支付方式列表
        List<PayType> paymentTypeList = PayTypeUtil.getPaymentType(payCategory, product, channel, version);

        List<Payment> list = new ArrayList<>();

        boolean isSelected = false;
        for (PayType payType : paymentTypeList) {
            Payment payment = new Payment();
            payment.setId(payType.getId());
            payment.setName(payType.getName());

            //设置货到付款是否有效
            String enable = "1";
            if ("2".equals(payType.getWey()) && !"1".equals(isCod)) {
                enable = "0";
            }
            payment.setEnable(enable);

            //设置默认选中 微信 海外微信 分账阿里
            String select = "0";
            if(!isSelected && (payType.equals(PayType.WEIXINAPP) || payType.equals(PayType.WEIXINAPPSEA) || payType.equals(PayType.ALIFZAPP))){
                select = "1";
                isSelected = true;
            }
            payment.setIsSelected(select);

            //设置子支付方式
            payment.setSubpayCode(payType.getSubId());

            list.add(payment);

        }

        settlementUnion.setPayments(list);
    }

    /**
     * 设置发票和礼品卡开关
     * @param settlementUnion
     */
    private void setInvoiceAndGiftCartButton(SettlementUnion settlementUnion){
        //设置发票开关
        InvoiceWrapper invoiceWrapper = settlementUnion.getInvoice();
        if(invoiceWrapper != null && !StringUtils.isBlank(invoiceWrapper.getValid())){
            //默认关闭
            invoiceWrapper.setInvoiceButtonStatus("0");

            Invoice lastInvoice = invoiceWrapper.getLastInvoice();

            if(lastInvoice != null){
                lastInvoice.setInvoiceDesc(com.shangpin.api.utils.Constants.INVOCE_DESC);
            }
        }

        //设置礼品卡开关
        GiftCardWrapper giftCardWrapper = settlementUnion.getGiftCard();
        if(giftCardWrapper !=null && !StringUtils.isBlank(giftCardWrapper.getValid())){
            //默认关闭
            giftCardWrapper.setGiftCardButtonStatus("0");
        }
    }



}
