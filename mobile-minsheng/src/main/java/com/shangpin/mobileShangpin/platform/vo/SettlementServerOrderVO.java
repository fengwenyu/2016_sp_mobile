package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class SettlementServerOrderVO {

    private String code;
    private String msg;
    private SettlmentOrderVO settlmentOrderVO;
    /**
     * 返给客户端的json数据
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public  SettlementServerOrderVO jsonObj(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        this.setCode(obj.getString("code"));
        this.setMsg(obj.getString("msg"));
        if ("0".equals(code)) {
            obj = JSONObject.fromObject(obj.getJSONObject("content"));
            settlmentOrderVO=new SettlmentOrderVO();
            String amount=obj.getString("amount");
            settlmentOrderVO.setAmount(StringUtil.cutOffDecimal(amount));
            settlmentOrderVO.setCodincart(obj.getString("codincart"));
            settlmentOrderVO.setLastInvoiceTitle(obj.getString("lastInvoiceTitle"));
            settlmentOrderVO.setLastReceiveId(obj.getString("lastReceiveId"));
            settlmentOrderVO.setLastInvoiceConsigneeID(obj.getString("lastInvoiceConsigneeID"));
            settlmentOrderVO.setLastDeliveryType(obj.getString("lastDeliveryType"));
            settlmentOrderVO.setGiftCardBalance(obj.getString("giftCardBalance"));        
            JSONArray receiveArray=obj.getJSONArray("receive");
            List<ConsigneeAddressVO> receive=new ArrayList<ConsigneeAddressVO>();
            if(receiveArray!=null&&receiveArray.size()>0){
                JSONObject receiveObj=null;
                ConsigneeAddressVO consigneeAddressVO=null;
                for (int i = 0; i < receiveArray.size(); i++) {
                    consigneeAddressVO=new ConsigneeAddressVO();
                    receiveObj=receiveArray.getJSONObject(i);
                    consigneeAddressVO.setId(receiveObj.getString("id"));
                    consigneeAddressVO.setName(receiveObj.getString("name"));
                    consigneeAddressVO.setProvince(receiveObj.getString("province"));
                    consigneeAddressVO.setProvname(receiveObj.getString("provName"));
                    consigneeAddressVO.setCity(receiveObj.getString("city"));
                    consigneeAddressVO.setCityname(receiveObj.getString("cityName"));
                    consigneeAddressVO.setArea(receiveObj.getString("area"));
                    consigneeAddressVO.setAreaname(receiveObj.getString("areaName"));
                    consigneeAddressVO.setTown(receiveObj.getString("town"));
                    consigneeAddressVO.setTownname(receiveObj.getString("townName"));
                    consigneeAddressVO.setAddr(receiveObj.getString("addr"));
                    consigneeAddressVO.setPostcode(receiveObj.getString("zip"));
                    consigneeAddressVO.setIsd(receiveObj.getString("isd"));
                    consigneeAddressVO.setTel(receiveObj.getString("tel"));
                    consigneeAddressVO.setCod(receiveObj.getString("cod"));
                    receive.add(consigneeAddressVO);
                }                 
            }
            settlmentOrderVO.setReceive(receive);
            List<ConsigneeAddressVO> invoiceaddrs=new ArrayList<ConsigneeAddressVO>(); 
            JSONArray invoiceArray=obj.getJSONArray("invoiceaddrs");
            if(invoiceArray!=null&&invoiceArray.size()>0){
                JSONObject invoiceObj=null;
                ConsigneeAddressVO consigneeAddressVO=null;
                for (int i = 0; i < invoiceArray.size(); i++) {
                    consigneeAddressVO=new ConsigneeAddressVO();
                    invoiceObj=invoiceArray.getJSONObject(i);
                    consigneeAddressVO.setId(invoiceObj.getString("id"));
                    consigneeAddressVO.setName(invoiceObj.getString("name"));
                    consigneeAddressVO.setProvince(invoiceObj.getString("province"));
                    consigneeAddressVO.setProvname(invoiceObj.getString("provName"));
                    consigneeAddressVO.setCity(invoiceObj.getString("city"));
                    consigneeAddressVO.setCityname(invoiceObj.getString("cityName"));
                    consigneeAddressVO.setArea(invoiceObj.getString("area"));
                    consigneeAddressVO.setAreaname(invoiceObj.getString("areaName"));
                    consigneeAddressVO.setTown(invoiceObj.getString("town"));
                    consigneeAddressVO.setTownname(invoiceObj.getString("townName"));
                    consigneeAddressVO.setAddr(invoiceObj.getString("addr"));
                    consigneeAddressVO.setPostcode(invoiceObj.getString("zip"));
                    consigneeAddressVO.setIsd(invoiceObj.getString("isd"));
                    consigneeAddressVO.setTel(invoiceObj.getString("tel"));
                    invoiceaddrs.add(consigneeAddressVO);
                }                 
            }
            settlmentOrderVO.setInvoiceaddrs(invoiceaddrs);
            JSONObject carriageObj=obj.getJSONObject("carriage");
            CarriageVO carriageVO;
            if(carriageObj!=null){
                carriageVO=new CarriageVO();
                carriageVO.setAmount(carriageObj.getString("amount"));
                carriageVO.setReductionAmount(carriageObj.getString("reductionAmount"));
                carriageVO.setDesc(carriageObj.getString("desc"));
                settlmentOrderVO.setCarriage(carriageVO);
            }
            JSONObject lastPaymentObj=obj.getJSONObject("lastPayMent");
            LastPaymentVO lastPaymentVO;
            if(lastPaymentObj!=null){
                lastPaymentVO=new LastPaymentVO();
                lastPaymentVO.setMainPayCode(lastPaymentObj.getString("mainPayCode"));
                lastPaymentVO.setSubPayCode(lastPaymentObj.getString("subPayCode"));
                settlmentOrderVO.setLastPayment(lastPaymentVO);
            }
            List<CouponVO> couponList=new ArrayList<CouponVO>(); 
            JSONArray couponArray=obj.getJSONArray("coupon");
            if(couponArray!=null&&couponArray.size()>0){
                couponList = couponArray.toList(couponArray, new CouponVO(),new JsonConfig());                   
            }
            settlmentOrderVO.setCoupon(couponList);
            String listStr=obj.getString("list");
            ProductAllCartVO productAllCartVO;
            if(listStr!=null){         
                productAllCartVO=new ProductServerAllCartVO().jsonObj2(listStr,"1").getProductAllCartVO();
                settlmentOrderVO.setProductAllCartVO(productAllCartVO);
            }
            
        }
        return this;
    }
    
    
    

    public SettlmentOrderVO getSettlmentOrderVO() {
        return settlmentOrderVO;
    }




    public void setSettlmentOrderVO(SettlmentOrderVO settlmentOrderVO) {
        this.settlmentOrderVO = settlmentOrderVO;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
}
