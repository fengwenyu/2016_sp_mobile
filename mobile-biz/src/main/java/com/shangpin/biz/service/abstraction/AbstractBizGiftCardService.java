package com.shangpin.biz.service.abstraction;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.OrderService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.ComparatorGiftCardProductListItem;
import com.shangpin.biz.bo.GiftCardBuy;
import com.shangpin.biz.bo.GiftCardCommit;
import com.shangpin.biz.bo.GiftCardKeyt;
import com.shangpin.biz.bo.GiftCardProductList;
import com.shangpin.biz.bo.GiftCardProductListItem;
import com.shangpin.biz.bo.GiftCardRechargePasswd;
import com.shangpin.biz.bo.GiftCardRecordList;
import com.shangpin.biz.bo.GiftCardStatus;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMap;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;

/**
 * 礼品卡抽象类
 * 
 * @author zghw
 *
 */
public abstract class AbstractBizGiftCardService {
    @Autowired
    private ShangPinService shangPinService;
    @Autowired
    private OrderService orderService;
    public String fromGiftCardList(String categoryNO, String productNo, String start, String end, String userID, String userIP, String encode) {
        String xml = shangPinService.giftCardList(categoryNO, productNo, start, end, userID, userIP, encode);
        return xml;
    }

    /**
     * 礼品卡商品列表(6.1)
     * 
     * @param categoryNO
     * @param price
     * @param start
     * @param end
     * @param userID
     * @param userIP
     * @param encode
     * @return
     * @author zghw
     */
    public ResultObjOne<GiftCardProductList> beList(String categoryNO, String productNo, String start, String end, String userID, String userIP, String encode) {
        String xml = fromGiftCardList(categoryNO, productNo, start, end, userID, userIP, encode);
        // 设置礼品卡主题编号
        String type = "1";
        if ("A03B01C02".equals(categoryNO)) {
            type="1";
        } else if ("A03B01C03".equals(categoryNO)) {
            type="2";
        }
        // 解析xml为对象
        ResultObjOne<GiftCardProductList> obj = convertXmlToObj(xml,type);
        return obj;
    }

    public ResultObjOne<GiftCardProductList> beList(String userId, String type, String pageIndex, String pageSize) {
        // 设置礼品卡主题编号
        String categoryNO = "A03B01C02";
        if ("1".equals(type)) {
            categoryNO = "A03B01C02";
        } else if ("2".equals(type)) {
            categoryNO = "A03B01C03";
        }

        if (StringUtil.isNotEmpty(pageIndex) && StringUtil.isNotEmpty(pageSize)) {
            int pageStart = (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(pageSize);
            if(pageStart<=0){
                pageStart=0;
            }
            int pageEnd = pageStart + Integer.parseInt(pageSize) - 1;
            pageIndex = String.valueOf(pageStart);
            pageSize = String.valueOf(pageEnd);
        }
        String xml = fromGiftCardList(categoryNO, "", pageIndex, pageSize, userId, "", "");
        ResultObjOne<GiftCardProductList> obj = convertXmlToObj(xml,type);
        
        if (obj != null && obj.getContent() != null) {
            obj.getContent().setType(type);
            List<GiftCardProductListItem> giftCardProductListItem=obj.getContent().getList();
            ComparatorGiftCardProductListItem cgpli=new ComparatorGiftCardProductListItem();
            Collections.sort(giftCardProductListItem, cgpli);
            obj.getContent().setList(giftCardProductListItem);
        }
        return obj;
    }

    public String fromRecordList(String userId, String recordType, String pageIndex, String pageSize) {
        String json = shangPinService.giftCardRecordList(userId, recordType, pageIndex, pageSize);
        return json;
    }

    /**
     * 礼品卡记录列表
     * 
     * @param userId
     *            用户ID
     * @param recordType
     *            礼品卡订单记录状态1.购买记录 2.充值记录 3.消费记录
     * @param pageIndex
     *            当前页
     * @param pageSize
     *            每页条数
     * @return
     * @author zghw
     */
    public ResultObjOne<GiftCardRecordList> beRecordList(String userId, String recordType, String pageIndex, String pageSize) {
        String json = fromRecordList(userId, recordType, pageIndex, pageSize);
        if (!StringUtils.isEmpty(json)) {
            ResultObjOne<GiftCardRecordList> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardRecordList>>() {
            });
            return result;
        }
        return null;
    }

    public String fromElectronicRecharge(String userId, String orderId, String cardPasswd) {
        String json = shangPinService.giftCardElectronicRecharge(userId, orderId, cardPasswd);
        return json;
    }

    /**
     * 礼品卡电子卡充值接口
     * 
     * @param userId
     *            用户ID
     * @param cardPasswd
     *            礼品卡号
     * @return
     * @author zghw
     */
    public ResultObjMap<String> beElectronicRecharge(String userId, String orderId, String cardPasswd) {
        String json = fromElectronicRecharge(userId, orderId, cardPasswd);
        if (!StringUtils.isEmpty(json)) {
            ResultObjMap<String> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMap<String>>() {
            });
            return result;
        }
        return null;
    }

    public String fromGiftCardBuy(String userId, String skuId, String productId, String quantity, String categoryNo, String type, String eGiftCardAmount) {
        String json = shangPinService.giftCardBuy(userId, skuId, productId, quantity, categoryNo, type, eGiftCardAmount);
        return json;
    }

    /**
     * 礼品卡立即购买
     * 
     * @param userId用户ID
     * @param skuIdSKU
     * @param productId产品ID
     * @param amount购买数量
     * @return
     * @author zghw
     */
    public ResultObjOne<GiftCardBuy> beGiftCardBuy(String userId, String skuId, String productId, String quantity, String categoryNo, String type, String eGiftCardAmount) {
        String json = this.fromGiftCardBuy(userId, skuId, productId, quantity, categoryNo, type, eGiftCardAmount);
        if (!StringUtils.isEmpty(json)) {
            ResultObjOne<GiftCardBuy> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardBuy>>() {
            });
            return result;
        }
        return null;
    }

    public String fromGiftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle, String invoiceContent,
            String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType, String type,
                                     String invoiceEmail,String invoiceTel) {
        String json = shangPinService.giftCardCommit(userId, addressId, invoiceFlag, invoiceAddressId, invoiceType, invoiceTitle, invoiceContent, express, orderFrom, buysIds,
                payTypeId, payTypeChildId, orderType, type,invoiceEmail, invoiceTel);
        return json;
    }

    /**
     * 提交订单去结算
     * 
     * @param userId用户ID
     * @param addressId收货地址ID
     * @param invoiceFlag是否开发票
     * @param invoiceAddressId发票包含的收货地址
     * @param invoiceType发票类型0
     *            ：个人 1公司
     * @param invoiceTitle发票抬头
     * @param invoiceContent发票内容
     * @param express配送方式
     * @param orderFrom订单来源
     * @param buysIds购物车ID
     * @param payTypeId主支付方式编号
     * @param payTypeChildId子支付方式编号
     * @param orderType订单来源站点
     * @return
     * @author zghw
     */
    public ResultObjOne<GiftCardCommit> beGiftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle,
            String invoiceContent, String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType, String type,String invoiceEmail,String invoiceTel) {

        String json = fromGiftCardCommit(userId, addressId, invoiceFlag, invoiceAddressId, invoiceType, invoiceTitle, invoiceContent, express, orderFrom, buysIds, payTypeId,
                payTypeChildId, orderType, type,invoiceEmail, invoiceTel);
        if (!StringUtils.isEmpty(json)) {
            ResultObjOne<GiftCardCommit> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardCommit>>() {
            });
            return result;
        }
        return null;
    }

    public String fromGiftCardRechargePasswd(String userId, String orderId) {
        String json = shangPinService.giftCardRechargePasswd(userId, orderId);
        return json;
    }

    /**
     * 礼品卡--获取电子卡充值密码
     * 
     * @param userId
     *            用户ID
     * @param orderId
     *            订单号
     * @return
     * @author zghw
     */
    public ResultObjOne<GiftCardRechargePasswd> beGiftCardRechargePasswd(String userId, String orderId) {
        String json = fromGiftCardRechargePasswd(userId, orderId);
        if (!StringUtils.isEmpty(json)) {
            ResultObjOne<GiftCardRechargePasswd> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardRechargePasswd>>() {
            });
            return result;
        }
        return null;
    }
    
    /**
     *  查询账户礼品卡状态
     * @param userId
     * @return
     * @author zghw
     */
    public String fromGiftCardStatus(String userId){
        String json = shangPinService.giftCardStatus(userId);
        return json;
    }
    
    /**
     *  查询礼品卡状态
     * @param userId
     * @return
     * @author zghw
     */
    public String findGiftCardStatu(String cardNo){
        String json = shangPinService.cardStatus(cardNo);
        return json;
    }

	/**
	 * 礼品卡实物卡充值
	 * 
	 * @param userId用户ID
	 * @param cardNo
	 *            卡号
	 * @param cardPwd卡密
	 * @return
	 * @author zghw
	 */
	public String fromGiftCardEntityRecharge(String userId, String cardNo, String cardPwd){
		String json = shangPinService.giftCardEntityRecharge(userId, cardNo, cardPwd);
		return json;
	}
 public ResultObjOne<GiftCardStatus> beGiftCardStatus(String userId){
        String json = fromGiftCardStatus(userId);
        if(!StringUtils.isEmpty(json)){
            ResultObjOne<GiftCardStatus> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardStatus>>(){});
            return result;
        }
        return null;
    }
    public ResultObjMap<String> beGiftCardEntityRecharge(String userId, String cardNo, String cardPwd){
        String json = fromGiftCardEntityRecharge(userId,cardNo,cardPwd);
        if(!StringUtils.isEmpty(json)){
            ResultObjMap<String> result= JsonUtil.fromJson(json, new TypeToken<ResultObjMap<String>>(){});
            return result;
        }
        return null;
    }
    /**
     * 把礼品卡列表xml转换为礼品卡列表对象
     * 
     * @param xml
     * @return
     * @author zghw
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected ResultObjOne<GiftCardProductList> convertXmlToObj(String xml,String type) {
        ResultObjOne<GiftCardProductList> obj = new ResultObjOne<GiftCardProductList>();
        GiftCardProductList giftCardProductList = new GiftCardProductList();
        List<GiftCardProductListItem> list = new ArrayList<GiftCardProductListItem>();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element rootElement = iterator.next();
                String nameST = rootElement.getName();
                String textST = rootElement.getTextTrim();
                if ("Status".equals(nameST)) {
                    obj.setCode(textST);
                }
                if ("Discription".equals(nameST)) {
                    obj.setMsg(textST);
                }
                List<Element> elements = rootElement.elements();
                if (elements != null && elements.size() > 0) {
                    for (int i = 0; i < elements.size(); i++) {
                        GiftCardProductListItem giftCardProductListItem = new GiftCardProductListItem();
                        if("1".equals(type)){
                            giftCardProductListItem.setBrandNameCN("电子卡");
                        }else if("2".equals(type)){
                            giftCardProductListItem.setBrandNameCN("实物卡");
                        }
                        Element elementsND = elements.get(i);
                        String nameND = elementsND.getName();
                        if ("doc".equals(nameND)) {
                            List<Element> docElements = elementsND.elements();
                            if (docElements != null && docElements.size() > 0) {
                                for (int j = 0; j < docElements.size(); j++) {
                                    Element docElement = docElements.get(j);
                                    List docItemAttr = docElement.attributes();
                                    if (docItemAttr != null && docItemAttr.size() > 0) {
                                        DefaultAttribute docItemAttrDef = (DefaultAttribute) docItemAttr.get(0);
                                        String attrName = docItemAttrDef.getText();
                                        if ("ProductNo".equals(attrName)) {
                                            String productId = docElement.getTextTrim();
                                            giftCardProductListItem.setProductId(productId);
                                        }
                                        if ("ProductName".equals(attrName)) {
                                            String productName = docElement.getTextTrim();
                                            giftCardProductListItem.setProductName(productName);
                                        }
                                        if ("HasStock".equals(attrName)) {
                                            String hasStock = docElement.getTextTrim();
                                            giftCardProductListItem.setCount(hasStock);
                                        }
                                        if ("LimitedPrice".equals(attrName)) {
                                            String marketPrice =StringUtil.removePoint( docElement.getTextTrim());
                                            giftCardProductListItem.setMarketPrice(marketPrice);
                                            giftCardProductListItem.setLimitedPrice(marketPrice);
                                            giftCardProductListItem.setGoldPrice(marketPrice);
                                            giftCardProductListItem.setPlatinumPrice(marketPrice);
                                            giftCardProductListItem.setDiamondPrice(marketPrice);
                                            giftCardProductListItem.setLimitedVipPrice(marketPrice);
                                            giftCardProductListItem.setPromotionPrice(marketPrice);
                                            giftCardProductListItem.setIsSupportDiscount("0");
                                            giftCardProductListItem.setPromotionDesc("");
                                            giftCardProductListItem.setStatus("10000");
                                        }
                                        if ("ProductPicFile".equals(attrName)) {
                                            String picNo = docElement.getTextTrim();
                                            giftCardProductListItem.setPicNo(picNo);
                                            giftCardProductListItem.setPic(PicCdnHash.getPicUrl(picNo, "1"));
                                        }
                                        if ("CategoryNo".equals(attrName)) {
                                            Element categoryNoElement = (Element) docElement.elements().get(0);
                                            String categoryNo = categoryNoElement.getTextTrim();
                                            giftCardProductListItem.setCategoryNo(categoryNo);
                                        }
                                    }
                                }
                            }
                        }
                        list.add(giftCardProductListItem);
                    }
                }
                giftCardProductList.setList(list);
                obj.setContent(giftCardProductList);
            }
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (DocumentException e) {
            return null;
        }
        return obj;
    }
    public String fromSetGiftCardPassword(String userId, String password) {
        String json = orderService.setGiftCardPassword(userId, password);
        return json;
    }

    public ResultBase beSetGiftCardPassword(String userId, String password) {
        String json = fromSetGiftCardPassword(userId, password);
        if (!StringUtils.isEmpty(json)) {
            ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
            });
            return result;
        }
        return null;
    }
    
    public GiftCardKeyt queryGiftCardSecretKey(String mainOrderId, String userId){
        String json = orderService.queryGiftCardSecretKey(mainOrderId, userId);
        if (!StringUtils.isEmpty(json)) {
            ResultObjOne<GiftCardKeyt> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardKeyt>>(){});
            return result.getObj();
        }
        return null;
    }
    public String giftCardKeyt(String userId, String mainOrderId){
        String json = orderService.queryGiftCardSecretKey(mainOrderId, userId);
        if (!StringUtils.isEmpty(json)) {
            
            return json;
        }
        return null;
    }
    
    /**
     * 礼品卡卡号--获取电子卡充值密码
     * 
     * @param userId
     *            用户ID
     * @param giftCardId
     *            礼品卡卡号
     * @return
     * @author fengwenyu
     */
    public String fromGiftCardRechargePasswdByCardId(String userId, String giftCardId) {
        return shangPinService.giftCardRechargePasswdByCardId(userId, giftCardId);
    }
   
	public ResultObjOne<GiftCardStatus> findGetGiftCardStatus(String cardNo) {
		String json = findGiftCardStatu(cardNo);
        if(!StringUtils.isEmpty(json)){
            ResultObjOne<GiftCardStatus> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<GiftCardStatus>>(){});
            return result;
        }
        return null;

	}
}
