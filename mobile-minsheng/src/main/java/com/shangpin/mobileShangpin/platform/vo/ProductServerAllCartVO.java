package com.shangpin.mobileShangpin.platform.vo;

import java.util.ArrayList;
import java.util.List;

import com.shangpin.mobileShangpin.common.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductServerAllCartVO {

    private String code;
    private String msg;
    private ProductAllCartVO productAllCartVO;

    /**
     * 返给客户端的json数据
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public ProductServerAllCartVO jsonObj(String json, String shopType) {
        JSONObject obj = JSONObject.fromObject(json);
        this.setCode(obj.getString("code"));
        this.setMsg(obj.getString("msg"));
        if ("0".equals(code)) {
            obj = JSONObject.fromObject(obj.getJSONObject("content"));            
            if(!obj.toString().equals("{}")){              
                double totalAmount = 0.0;
                int totalQuantity = 0;
                String singleTotalQuantity = null;
                String quantityAmount = null;
                JSONArray array = null;
                if ("1".equals(shopType)) {
                    array = obj.getJSONArray("SpList");
                } else if ("2".equals(shopType)) {
                    array = obj.getJSONArray("AlList");
                }
                List<ProductsPromotionVO> productsPromotionVOList = new ArrayList<ProductsPromotionVO>();
                StringBuffer shopIdsBuffer=new StringBuffer("");
                if (array != null && array.size() > 0) {
                    productAllCartVO = new ProductAllCartVO();
                    ProductsPromotionVO productsPromotionVO;
                    for (int i = 0; i < array.size(); i++) {
                        productsPromotionVO = new ProductsPromotionVO();
                        PromotionVO promotionVO = new PromotionVO();
                        List<ProductCartVO> prouctCartVOList = new ArrayList<ProductCartVO>();
                        JSONObject promotionObj = array.getJSONObject(i);
                        promotionVO.setIspromotion(promotionObj.getString("IsPromotion"));
                        promotionVO.setPromotionno(promotionObj.getString("PromotionNo"));
                        promotionVO.setPromotiontype(promotionObj.getString("PromotionType"));
                        promotionVO.setPromotioncontent(promotionObj.getString("PromotionContent"));
                        promotionVO.setPromotiondesc(promotionObj.getString("PromotionDesc"));
                        promotionVO.setPromotionurl(promotionObj.getString("PromotionUrl"));
                        promotionVO.setCouponprice(promotionObj.getString("CouponPrice"));
                        productsPromotionVO.setPromotion(promotionVO);
                        JSONArray productsArray = promotionObj.getJSONArray("CartItemList");
                        if (productsArray != null && productsArray.size() > 0) {
                            ProductCartVO productCartVO;
                            List<ProductPropVO> productPropVOList;
                            ProductPropVO productPropVO;
                            for (int j = 0; j < productsArray.size(); j++) {
                                productCartVO = new ProductCartVO();
                                JSONObject productObj = productsArray.getJSONObject(j);
                                productCartVO.setShopDetailId(productObj.getString("ShoppingCartDetailId"));
                                productCartVO.setProductName(productObj.getString("ProductName"));
                                productCartVO.setPrice(StringUtil.cutOffDecimal(productObj.getString("Price")));
                                productCartVO.setPromotionPrice(productObj.getString("PromotionPrice"));
                                quantityAmount = productObj.getString("TotalAmount");
                                productCartVO.setTotalQuantityAmount(quantityAmount);
                                totalAmount += Double.parseDouble(quantityAmount);
                                singleTotalQuantity = productObj.getString("Quantity");
                                productCartVO.setQuantity(singleTotalQuantity);
                                totalQuantity += Integer.parseInt(singleTotalQuantity);
                                productCartVO.setFavoritePrice(productObj.getString("FavoritePrice"));
                                productCartVO.setBrandName(productObj.getString("BrandEnName"));
                                productCartVO.setProductUrl(productObj.getString("ProductUrl"));
                                productCartVO.setImg(productObj.getString("MobileImg"));
                                productCartVO.setProductNo(productObj.getString("ProductNo"));
                                productCartVO.setSkuNo(productObj.getString("SkuNo"));
                                productCartVO.setCategoryNo(productObj.getString("CategoryNo"));
                                productCartVO.setAlCategoryNo(productObj.getString("AolaiSubjectNo"));
                                productCartVO.setDateTime(productObj.getString("DateAdd"));
                                productCartVO.setIslimitedOutlet(productObj.getString("IsLimitedOutlet"));
                                String msgType=productObj.getString("MsgType");
                                productCartVO.setMsgType(msgType);
                                if("1".equals(msgType)){  //（1:售罄 2：剩余量 3：活动过期 4:商品已下架）
                                    productCartVO.setMsg("售罄");
                                }
                                else if("3".equals(msgType)){
                                    productCartVO.setMsg("过期");
                                }
                                else if("4".equals(msgType)){
                                    productCartVO.setMsg("下架");
                                }else{                                    
                                    productCartVO.setMsg(productObj.getString("Msg"));
                                }
                                if("0".equals(msgType)||"2".equals(msgType)){
                                    shopIdsBuffer.append(productCartVO.getShopDetailId());
                                    shopIdsBuffer.append("|");
                                }
                                productCartVO.setMsgType(productObj.getString("MsgType"));
                                productCartVO.setMsg(productObj.getString("Msg"));
                                productCartVO.setStock(productObj.getString("Stock"));
                                String propStr = productObj.getString("MobileSkuAttrText");
                                productCartVO.setSkuAttrTextStr(propStr);
                                if (propStr != null && !"".equals(propStr)) {
                                    productPropVOList = new ArrayList<ProductPropVO>();
                                    String[] propArray = propStr.split("\\|");
                                    if (propArray != null && propArray.length > 0) {
                                        productPropVOList = new ArrayList<ProductPropVO>();
                                        for (int z = 0; z < propArray.length; z++) {
                                            productPropVO = new ProductPropVO();
                                            productPropVO.setName(propArray[z].substring(0, propArray[z].indexOf(":")));
                                            productPropVO.setValue(propArray[z].substring(propArray[z].indexOf(":") + 1));
                                            productPropVOList.add(productPropVO);
                                        }
                                        productCartVO.setSkuAttrText(productPropVOList);
                                    }
                                }
                                prouctCartVOList.add(productCartVO);
                            }
                        }
                        productsPromotionVO.setProductCartVO(prouctCartVOList);
                        productsPromotionVOList.add(productsPromotionVO);
                    }
                    productAllCartVO.setProductsPromotionVOList(productsPromotionVOList);
                    productAllCartVO.setShopIds(shopIdsBuffer.toString());
                    productAllCartVO.setTotalPrice(String.valueOf(totalAmount));
                    productAllCartVO.setTotalQuantity(String.valueOf(totalQuantity));
                }
                
            }
            
        }

        return this;
    }

    /**
     * 返给客户端的json数据
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public ProductServerAllCartVO jsonObj2(String json, String shopType) {
        JSONObject obj = JSONObject.fromObject(json);
                productAllCartVO = new ProductAllCartVO();
                double totalAmount = 0.0;
                int totalQuantity = 0;
                String singleTotalQuantity = null;
                String quantityAmount = null;
                JSONArray array = null;
                if ("1".equals(shopType)) {
                    array = obj.getJSONArray("SpList");
                } else if ("2".equals(shopType)) {
                    array = obj.getJSONArray("AlList");
                }
                List<ProductsPromotionVO> productsPromotionVOList = new ArrayList<ProductsPromotionVO>();
                StringBuffer shopIdsBuffer=new StringBuffer("");
                if (array != null && array.size() > 0) {
                    ProductsPromotionVO productsPromotionVO;
                    for (int i = 0; i < array.size(); i++) {
                        productsPromotionVO = new ProductsPromotionVO();
                        PromotionVO promotionVO = new PromotionVO();
                        List<ProductCartVO> prouctCartVOList = new ArrayList<ProductCartVO>();
                        JSONObject promotionObj = array.getJSONObject(i);
                        promotionVO.setIspromotion(promotionObj.getString("IsPromotion"));
                        promotionVO.setPromotionno(promotionObj.getString("PromotionNo"));
                        promotionVO.setPromotiontype(promotionObj.getString("PromotionType"));
                        promotionVO.setPromotioncontent(promotionObj.getString("PromotionContent"));
                        promotionVO.setPromotiondesc(promotionObj.getString("PromotionDesc"));
                        promotionVO.setPromotionurl(promotionObj.getString("PromotionUrl"));
                        promotionVO.setCouponprice(promotionObj.getString("CouponPrice"));
                        productsPromotionVO.setPromotion(promotionVO);
                        JSONArray productsArray = promotionObj.getJSONArray("CartItemList");
                        if (productsArray != null && productsArray.size() > 0) {
                            ProductCartVO productCartVO;
                            List<ProductPropVO> productPropVOList;
                            ProductPropVO productPropVO;
                            for (int j = 0; j < productsArray.size(); j++) {
                                productCartVO = new ProductCartVO();
                                JSONObject productObj = productsArray.getJSONObject(j);
                                productCartVO.setShopDetailId(productObj.getString("ShoppingCartDetailId"));
                                productCartVO.setProductName(productObj.getString("ProductName"));
                                productCartVO.setPrice(StringUtil.cutOffDecimal(productObj.getString("Price")));
                                productCartVO.setPromotionPrice(productObj.getString("PromotionPrice"));
                                quantityAmount = productObj.getString("TotalAmount");
                                productCartVO.setTotalQuantityAmount(quantityAmount);
                                totalAmount += Double.parseDouble(quantityAmount);
                                singleTotalQuantity = productObj.getString("Quantity");
                                productCartVO.setQuantity(singleTotalQuantity);
                                totalQuantity += Integer.parseInt(singleTotalQuantity);
                                productCartVO.setFavoritePrice(productObj.getString("FavoritePrice"));
                                productCartVO.setBrandName(productObj.getString("BrandEnName"));
                                productCartVO.setProductUrl(productObj.getString("ProductUrl"));
                                productCartVO.setImg(productObj.getString("MobileImg"));
                                productCartVO.setProductNo(productObj.getString("ProductNo"));
                                productCartVO.setSkuNo(productObj.getString("SkuNo"));
                                productCartVO.setCategoryNo(productObj.getString("CategoryNo"));
                                productCartVO.setAlCategoryNo(productObj.getString("AolaiSubjectNo"));
                                productCartVO.setDateTime(productObj.getString("DateAdd"));
                                productCartVO.setIslimitedOutlet(productObj.getString("IsLimitedOutlet"));
                                String msgType=productObj.getString("MsgType");
                                productCartVO.setMsgType(msgType);
                                if("1".equals(msgType)){  //（1:售罄 2：剩余量 3：活动过期 4:商品已下架）
                                    productCartVO.setMsg("售罄");
                                }
                                else if("3".equals(msgType)){
                                    productCartVO.setMsg("过期");
                                }
                                else if("4".equals(msgType)){
                                    productCartVO.setMsg("下架");
                                }else{                                    
                                    productCartVO.setMsg(productObj.getString("Msg"));
                                }
                                if("0".equals(msgType)){
                                    shopIdsBuffer.append(productCartVO.getShopDetailId());
                                    shopIdsBuffer.append("|");
                                }
                                productCartVO.setMsgType(productObj.getString("MsgType"));
                                productCartVO.setMsg(productObj.getString("Msg"));
                                productCartVO.setStock(productObj.getString("Stock"));
                                String propStr = productObj.getString("MobileSkuAttrText");
                                productCartVO.setSkuAttrTextStr(propStr);
                                if (propStr != null && !"".equals(propStr)) {
                                    productPropVOList = new ArrayList<ProductPropVO>();
                                    String[] propArray = propStr.split("\\|");
                                    if (propArray != null && propArray.length > 0) {
                                        productPropVOList = new ArrayList<ProductPropVO>();
                                        for (int z = 0; z < propArray.length; z++) {
                                            productPropVO = new ProductPropVO();
                                            productPropVO.setName(propArray[z].substring(0, propArray[z].indexOf(":")));
                                            productPropVO.setValue(propArray[z].substring(propArray[z].indexOf(":") + 1));
                                            productPropVOList.add(productPropVO);
                                        }
                                        productCartVO.setSkuAttrText(productPropVOList);
                                    }
                                }
                                prouctCartVOList.add(productCartVO);
                            }
                        }
                        productsPromotionVO.setProductCartVO(prouctCartVOList);
                        productsPromotionVOList.add(productsPromotionVO);
                    }
                    productAllCartVO.setProductsPromotionVOList(productsPromotionVOList);
                }
                productAllCartVO.setShopIds(shopIdsBuffer.toString());
                productAllCartVO.setTotalPrice(String.valueOf(totalAmount));
                productAllCartVO.setTotalQuantity(String.valueOf(totalQuantity));

        return this;
    }
    public ProductAllCartVO getProductAllCartVO() {
        return productAllCartVO;
    }

    public void setProductAllCartVO(ProductAllCartVO productAllCartVO) {
        this.productAllCartVO = productAllCartVO;
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
