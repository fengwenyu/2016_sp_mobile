package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.SettlementServerOrderVO;
import com.shangpin.wireless.api.domain.AlPromotionVO;
import com.shangpin.wireless.api.domain.ProductAllCartVO;
import com.shangpin.wireless.api.domain.ProductCartVO;
import com.shangpin.wireless.api.domain.ProductPropVO;
import com.shangpin.wireless.api.domain.SpPromotionVO;

/**
 * 返给客户端的json数据
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */

public class SettlementOrderByOldAPIResponse {

    /**
     * 返给客户端的json数据新版本
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public String objJson(SettlementServerOrderVO settlementServerOrderVO) {
        JSONObject obj = new JSONObject();
        obj.put("code", settlementServerOrderVO.getCode());
        obj.put("msg", settlementServerOrderVO.getMsg());
        JSONObject content = new JSONObject();
        if ("0".equals(settlementServerOrderVO.getCode())) {
            SettlmentOrderVO settlmentOrderVO = settlementServerOrderVO.getSettlmentOrderVO();
            content.put("amount", settlmentOrderVO.getAmount());
            content.put("codincart", settlmentOrderVO.getCodincart());
            content.put("express", settlmentOrderVO.getLastDeliveryType());
            CarriageVO carriageVO = settlmentOrderVO.getCarriage();
            if (carriageVO != null) {
                content.put("carriage", carriageVO.getAmount());
            }

            List<AddressVO> receiveList = settlmentOrderVO.getReceive();
            JSONArray receiveArray = new JSONArray();
            if(receiveList!=null&&receiveList.size()>0){
                JSONObject receiveObj=null;
                AddressVO addressVO=null;
                for (int i = 0; i < receiveList.size(); i++) {
                    addressVO=receiveList.get(i);
                    receiveObj=new JSONObject();
                    receiveObj.put("id", addressVO.getId());
                    receiveObj.put("name", addressVO.getName());
                    receiveObj.put("province", addressVO.getProvince());
                    receiveObj.put("provname", addressVO.getProvName());
                    receiveObj.put("city", addressVO.getCity());
                    receiveObj.put("cityname", addressVO.getCityName());
                    receiveObj.put("area", addressVO.getArea());
                    receiveObj.put("areaname", addressVO.getAreaName());
                    receiveObj.put("addr", addressVO.getAddr());
                    receiveObj.put("postcode", addressVO.getZip());
                    receiveObj.put("isd", addressVO.getIsd());
                    receiveObj.put("tel", addressVO.getTel());
                    receiveObj.put("cod", addressVO.getCod());
                    receiveArray.add(receiveObj);
                }
            }
            content.put("receivemsg", receiveArray);
            
            List<AddressVO> invoiceaddrsList = settlmentOrderVO.getInvoiceaddrs();
            JSONArray invoiceArray = new JSONArray();
            if(invoiceaddrsList!=null&&invoiceaddrsList.size()>0){
                JSONObject invoiceObj=null;
                AddressVO addressVO=null;
                for (int i = 0; i < invoiceaddrsList.size(); i++) {
                    addressVO=invoiceaddrsList.get(i);
                    invoiceObj=new JSONObject();
                    invoiceObj.put("id", addressVO.getId());
                    invoiceObj.put("name", addressVO.getName());
                    invoiceObj.put("province", addressVO.getProvince());
                    invoiceObj.put("provname", addressVO.getProvName());
                    invoiceObj.put("city", addressVO.getCity());
                    invoiceObj.put("cityname", addressVO.getCityName());
                    invoiceObj.put("area", addressVO.getArea());
                    invoiceObj.put("areaname", addressVO.getAreaName());
                    invoiceObj.put("addr", addressVO.getAddr());
                    invoiceObj.put("postcode", addressVO.getZip());
                    invoiceObj.put("isd", addressVO.getIsd());
                    invoiceObj.put("tel", addressVO.getTel());
                    invoiceObj.put("cod", addressVO.getCod());
                    invoiceArray.add(invoiceObj);
                }
            }
            content.put("invoiceaddrs", invoiceArray);
           

            List<CouponVO> couponList = settlmentOrderVO.getCoupon();
            JSONArray couponArray = new JSONArray();
            if(couponList!=null&&couponList.size()>0){
                JSONObject couponObj=null;
                CouponVO couponVO=null;
                for (int i = 0; i < couponList.size(); i++) {
                    couponVO=couponList.get(i);
                    couponObj=new JSONObject();
                    couponObj.put("couponid", couponVO.getCouponno());
                    couponObj.put("desc", couponVO.getDesc());
                    couponObj.put("amount", couponVO.getAmount());
                    couponObj.put("discount", couponVO.getDiscount());
                    couponObj.put("endtime",couponVO.getExpiredate());                   
                    couponArray.add(couponObj);
                }
            }
            content.put("coupon", couponArray);
            JSONArray list = new JSONArray();
            JSONObject productJsonObject;
            ProductAllCartVO productAllCartVO = settlmentOrderVO.getProductAllCartVO();
            if (productAllCartVO != null) {
                
                List<AlPromotionVO> alPromotionVOList = productAllCartVO.getAlPromotionVO();
                List<SpPromotionVO> spPromotionVOList = productAllCartVO.getSpPromotionVO();
                List<ProductCartVO> productList = null;
                AlPromotionVO alPromotionVO = null;
                SpPromotionVO spPromotionVO = null;
                ProductCartVO productCartVO = null;
                List<ProductPropVO> propList;
                
                
                if (alPromotionVOList != null && alPromotionVOList.size() > 0) {
                    for (int i = 0; i < alPromotionVOList.size(); i++) {
                        alPromotionVO = alPromotionVOList.get(i);
                        productList = alPromotionVO.getProductCartVO();
                        if (productList != null && productList.size() > 0) {
                            for (int j = 0; j < productList.size(); j++) {
                                productJsonObject = new JSONObject();
                                productCartVO = productList.get(j);
                                productJsonObject.put("sku", productCartVO.getSkuNo());
                                productJsonObject.put("brand", productCartVO.getBrandName());
                                productJsonObject.put("cate", productCartVO.getBrandNo());
                                productJsonObject.put("count", productCartVO.getQuantity());
                                productJsonObject.put("amount", productCartVO.getPrice());
                                productJsonObject.put("totalamount", productCartVO.getTotalQuantityAmount());
                                productJsonObject.put("shoppingcartdetailid", productCartVO.getShopDetailId());
                                productJsonObject.put("gid", productCartVO.getShopDetailId());
                                productJsonObject.put("groupno","");
                                productJsonObject.put("groupprice", "");
                                productJsonObject.put("groupdiscount", "");
                                productJsonObject.put("groupid", "");
                                productJsonObject.put("categoryno", productCartVO.getCategoryNo());
                                productJsonObject.put("goodsname", productCartVO.getProductName());
                                productJsonObject.put("goodsid", productCartVO.getProductNo());
                                productJsonObject.put("productname", productCartVO.getProductName());
                                productJsonObject.put("errorcode", productCartVO.getMsgType());
                                productJsonObject.put("errormsg", productCartVO.getMsg());
                                productJsonObject.put("totalamount", productCartVO.getTotalQuantityAmount());
                                productJsonObject.put("hidden","0");
                                productJsonObject.put("pic", productCartVO.getImg());
                                propList = productCartVO.getSkuAttrText();
                                if(propList!=null&&propList.size()>0){
                                    for(int z=0;z<propList.size();z++){
                                        if(propList.size()==1){
                                            productJsonObject.put("firstpropname", propList.get(z).getName());
                                            productJsonObject.put("firstpropvalue", propList.get(z).getValue());
                                            productJsonObject.put("secondpropname", "");
                                            productJsonObject.put("secondpropvalue", "");
                                        }else{
                                            if(z==1){
                                                productJsonObject.put("firstpropname", propList.get(z).getName());
                                                productJsonObject.put("firstpropvalue", propList.get(z).getValue());
                                            }else if(z==2){
                                                productJsonObject.put("secondpropname", propList.get(z).getName());
                                                productJsonObject.put("secondpropvalue", propList.get(z).getValue());
                                            }
                                            
                                        }
                                    }
                                }   
                                
                                list.add(productJsonObject);
                            }
                        }
                    }
                } else if (spPromotionVOList != null && spPromotionVOList.size() > 0) {
                    for (int i = 0; i < spPromotionVOList.size(); i++) {
                        spPromotionVO = spPromotionVOList.get(i);
                        productList = spPromotionVO.getProductCartVO();
                        if (productList != null && productList.size() > 0) {
                            for (int j = 0; j < productList.size(); j++) {
                                productJsonObject = new JSONObject();
                                productCartVO = productList.get(j);
                                productJsonObject.put("sku", productCartVO.getSkuNo());
                                productJsonObject.put("brand", productCartVO.getBrandName());
                                productJsonObject.put("cate", productCartVO.getBrandNo());
                                productJsonObject.put("count", productCartVO.getQuantity());
                                productJsonObject.put("amount", productCartVO.getPrice());
                                productJsonObject.put("totalamount", productCartVO.getTotalQuantityAmount());
                                productJsonObject.put("shoppingcartdetailid", productCartVO.getShopDetailId());
                                productJsonObject.put("gid", productCartVO.getShopDetailId());
                                productJsonObject.put("groupno","");
                                productJsonObject.put("groupprice", "");
                                productJsonObject.put("groupdiscount", "");
                                productJsonObject.put("groupid", "");
                                productJsonObject.put("categoryno", productCartVO.getCategoryNo());
                                productJsonObject.put("goodsname", productCartVO.getProductName());
                                productJsonObject.put("goodsid", productCartVO.getProductNo());
                                productJsonObject.put("productname", productCartVO.getProductName());
                                productJsonObject.put("errorcode", productCartVO.getMsgType());
                                productJsonObject.put("errormsg", productCartVO.getMsg());
                                productJsonObject.put("totalamount", productCartVO.getTotalQuantityAmount());
                                productJsonObject.put("hidden","0");
                                productJsonObject.put("pic", productCartVO.getImg());
                                propList = productCartVO.getSkuAttrText();
                                if(propList!=null&&propList.size()>0){
                                    for(int z=0;z<propList.size();z++){
                                        if(propList.size()==1){
                                            productJsonObject.put("firstpropname", propList.get(z).getName());
                                            productJsonObject.put("firstpropvalue", propList.get(z).getValue());
                                            productJsonObject.put("secondpropname", "");
                                            productJsonObject.put("secondpropvalue", "");
                                        }else{
                                            if(z==1){
                                                productJsonObject.put("firstpropname", propList.get(z).getName());
                                                productJsonObject.put("firstpropvalue", propList.get(z).getValue());
                                            }else if(z==2){
                                                productJsonObject.put("secondpropname", propList.get(z).getName());
                                                productJsonObject.put("secondpropvalue", propList.get(z).getValue());
                                            }
                                            
                                        }
                                    }
                                }   
                                list.add(productJsonObject);
                            }
                        }
                    }
                }

            }
            content.put("list", list);
            obj.put("content", content);
        }
        return obj.toString();
    }

}
