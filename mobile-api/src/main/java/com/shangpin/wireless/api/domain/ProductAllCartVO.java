package com.shangpin.wireless.api.domain;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




/**
 * 购物车商品信息
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-04-24
 */
public class ProductAllCartVO{

    private String totalPrice ;//总金额
    private String totalQuantity;//购买商品总数
    private String totalPromotionPrice;//总优惠金额
    private String practicalPrice;//优惠后总价格
    

    
    private List<SpPromotionVO> spPromotionVO;
    private List<AlPromotionVO> alPromotionVO;
    /**
     * 返给客户端的json数据
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public ProductAllCartVO jsonObj(String json) {
        JSONObject obj = JSONObject.fromObject(json);
            this.setTotalPrice(obj.getString("TotalPrice"));
            this.setPracticalPrice(String.valueOf(Double.parseDouble(obj.getString("TotalPrice"))-Double.parseDouble(obj.getString("TotalPromotionPrice"))));
            this.setTotalQuantity(obj.getString("TotalQuantity"));
            this.setTotalPromotionPrice(obj.getString("TotalPromotionPrice"));
            JSONArray spArray=obj.getJSONArray("SpList");       
            List<SpPromotionVO> spPromotionVOList=new ArrayList<SpPromotionVO>();
            if(spArray!=null&&spArray.size()>0){
                SpPromotionVO spPromotionVO;    
                for(int i=0;i<spArray.size();i++){  
                    spPromotionVO=new SpPromotionVO();  
                    PromotionVO promotionVO=new PromotionVO();  
                    List<ProductCartVO> spProuctCartVOList=new ArrayList<ProductCartVO>();
                    JSONObject promotionObj=spArray.getJSONObject(i);
                    promotionVO.setIspromotion(promotionObj.getString("IsPromotion"));
                    promotionVO.setPromotionno(promotionObj.getString("PromotionNo"));
                    promotionVO.setPromotiontype(promotionObj.getString("PromotionType"));
                    promotionVO.setPromotioncontent(promotionObj.getString("PromotionContent"));
                    promotionVO.setPromotiondesc(promotionObj.getString("PromotionDesc"));
                    promotionVO.setPromotionurl(promotionObj.getString("PromotionUrl"));
                    promotionVO.setCouponprice(promotionObj.getString("CouponPrice"));
                    spPromotionVO.setPromotion(promotionVO);
                    JSONArray spProductsArray=promotionObj.getJSONArray("CartItemList");
                    if(spProductsArray!=null&&spProductsArray.size()>0){
                        ProductCartVO productCartVO;
                        List<ProductPropVO> productPropVOList;
                        ProductPropVO productPropVO;
                        for(int j=0;j<spProductsArray.size();j++){
                            productCartVO=new ProductCartVO();
                            JSONObject productObj=spProductsArray.getJSONObject(j);
                            productCartVO.setShopDetailId(productObj.getString("ShoppingCartDetailId"));
                            productCartVO.setProductName(productObj.getString("ProductName"));
                            productCartVO.setPrice(productObj.getString("Price"));
                            productCartVO.setPriceTitle(productObj.getString("priceTitle"));
                            productCartVO.setCountryPic(productObj.getString("countryPic"));
                            productCartVO.setQuantityTitle("数量");
                            productCartVO.setPromotionPrice(productObj.getString("PromotionPrice"));
                            productCartVO.setTotalQuantityAmount(productObj.getString("TotalAmount"));
                            productCartVO.setFavoritePrice(productObj.getString("FavoritePrice"));
                            productCartVO.setQuantity(productObj.getString("Quantity"));
                            productCartVO.setBrandName(productObj.getString("BrandEnName"));
                            productCartVO.setBrandNameEN(productObj.getString("BrandEnName"));
                            productCartVO.setBrandNameCN(productObj.getString("BrandCnName"));
                            productCartVO.setProductUrl(productObj.getString("ProductUrl"));
                            productCartVO.setImg(productObj.getString("MobileImg"));
                            productCartVO.setProductNo(productObj.getString("ProductNo"));
                            productCartVO.setSkuNo(productObj.getString("SkuNo"));
                            productCartVO.setCategoryNo(productObj.getString("CategoryNo"));
                            productCartVO.setAlCategoryNo(productObj.getString("AolaiSubjectNo"));
                            productCartVO.setDateTime(productObj.getString("DateAdd"));
                            productCartVO.setIslimitedOutlet(productObj.getString("IsLimitedOutlet"));
                            productCartVO.setMsgType(productObj.getString("MsgType"));                          
                            productCartVO.setMsg(productObj.getString("Msg"));                          
                            String propStr=productObj.getString("MobileSkuAttrText");
                            productCartVO.setSkuAttrTextStr(propStr);
                            if(propStr!=null&&!"".equals(propStr)){
                                productPropVOList=new ArrayList<ProductPropVO>();
                                String [] propArray=propStr.split("\\|");
                                if(propArray!=null&&propArray.length>0){
                                    productPropVOList=new ArrayList<ProductPropVO>();
                                    for(int z=0;z<propArray.length;z++){
                                        productPropVO=new ProductPropVO();
                                        productPropVO.setName(propArray[z].substring(0,propArray[z].indexOf(":")));
                                        productPropVO.setValue(propArray[z].substring(propArray[z].indexOf(":")+1));    
                                        productPropVOList.add(productPropVO);
                                    }
                                    productCartVO.setSkuAttrText(productPropVOList);
                                }
                            }                           
                            spProuctCartVOList.add(productCartVO);
                        }
                    }
                    spPromotionVO.setProductCartVO(spProuctCartVOList);
                    spPromotionVOList.add(spPromotionVO);
                }
                this.setSpPromotionVO(spPromotionVOList);
            }
            JSONArray alArray=obj.getJSONArray("AlList");   
            List<AlPromotionVO> alPromotionVOList=new ArrayList<AlPromotionVO>();
            if(alArray!=null&&alArray.size()>0){
                AlPromotionVO alPromotionVO;    
                for(int i=0;i<alArray.size();i++){  
                    alPromotionVO=new AlPromotionVO();  
                    PromotionVO promotionVO=new PromotionVO();  
                    List<ProductCartVO> alProuctCartVOList=new ArrayList<ProductCartVO>();
                    JSONObject promotionObj=alArray.getJSONObject(i);
                    promotionVO.setIspromotion(promotionObj.getString("IsPromotion"));
                    promotionVO.setPromotionno(promotionObj.getString("PromotionNo"));
                    promotionVO.setPromotiontype(promotionObj.getString("PromotionType"));
                    promotionVO.setPromotioncontent(promotionObj.getString("PromotionContent"));
                    promotionVO.setPromotiondesc(promotionObj.getString("PromotionDesc"));
                    promotionVO.setPromotionurl(promotionObj.getString("PromotionUrl"));
                    promotionVO.setCouponprice(promotionObj.getString("CouponPrice"));
                    alPromotionVO.setPromotion(promotionVO);
                    JSONArray alProductsArray=promotionObj.getJSONArray("CartItemList");
                    if(alProductsArray!=null&&alProductsArray.size()>0){
                        ProductCartVO productCartVO;
                        List<ProductPropVO> productPropVOList;
                        ProductPropVO productPropVO;
                        for(int j=0;j<alProductsArray.size();j++){
                            productCartVO=new ProductCartVO();
                            JSONObject productObj=alProductsArray.getJSONObject(j);
                            productCartVO.setShopDetailId(productObj.getString("ShoppingCartDetailId"));
                            productCartVO.setProductName(productObj.getString("ProductName"));
                            productCartVO.setPrice(productObj.getString("Price"));
                            productCartVO.setPriceTitle(productObj.getString("priceTitle"));
                            productCartVO.setQuantityTitle("数量");
                            productCartVO.setPromotionPrice(productObj.getString("PromotionPrice"));
                            productCartVO.setTotalQuantityAmount(productObj.getString("TotalAmount"));
                            productCartVO.setFavoritePrice(productObj.getString("FavoritePrice"));
                            productCartVO.setQuantity(productObj.getString("Quantity"));
                            productCartVO.setBrandName(productObj.getString("BrandEnName"));
                            productCartVO.setBrandNameEN(productObj.getString("BrandEnName"));
                            productCartVO.setBrandNameCN(productObj.getString("BrandCnName"));
                            productCartVO.setProductUrl(productObj.getString("ProductUrl"));
                            productCartVO.setImg(productObj.getString("MobileImg"));
                            productCartVO.setProductNo(productObj.getString("ProductNo"));
                            productCartVO.setSkuNo(productObj.getString("SkuNo"));
                            productCartVO.setCategoryNo(productObj.getString("CategoryNo"));
                            productCartVO.setAlCategoryNo(productObj.getString("AolaiSubjectNo"));
                            productCartVO.setDateTime(productObj.getString("DateAdd"));
                            productCartVO.setIslimitedOutlet(productObj.getString("IsLimitedOutlet"));
                            productCartVO.setMsgType(productObj.getString("MsgType"));
                            productCartVO.setMsg(productObj.getString("Msg"));  
                            String propStr=productObj.getString("MobileSkuAttrText");
                            productCartVO.setSkuAttrTextStr(propStr);
                            if(propStr!=null&&!"".equals(propStr)){
                                productPropVOList=new ArrayList<ProductPropVO>();
                                String [] propArray=propStr.split("\\|");
                                if(propArray!=null&&propArray.length>0){
                                    productPropVOList=new ArrayList<ProductPropVO>();
                                    for(int z=0;z<propArray.length;z++){
                                        productPropVO=new ProductPropVO();
                                        if(propArray[z].indexOf(":")>-1){
                                            productPropVO.setName(propArray[z].substring(0,propArray[z].indexOf(":")));
                                            productPropVO.setValue(propArray[z].substring(propArray[z].indexOf(":")+1));
                                        }else{
                                            productPropVO.setName(propArray[z]);
                                            productPropVO.setValue("");
                                        }
                                           
                                        productPropVOList.add(productPropVO);
                                    }
                                    productCartVO.setSkuAttrText(productPropVOList);
                                }
                            }                           
                            alProuctCartVOList.add(productCartVO);
                        }
                    }
                    alPromotionVO.setProductCartVO(alProuctCartVOList);
                    alPromotionVOList.add(alPromotionVO);

                }
                this.setAlPromotionVO(alPromotionVOList);
            }                       
        return this;
    }
    
    
    public List<SpPromotionVO> getSpPromotionVO() {
        return spPromotionVO;
    }
    public void setSpPromotionVO(List<SpPromotionVO> spPromotionVO) {
        this.spPromotionVO = spPromotionVO;
    }
    public List<AlPromotionVO> getAlPromotionVO() {
        return alPromotionVO;
    }
    public void setAlPromotionVO(List<AlPromotionVO> alPromotionVO) {
        this.alPromotionVO = alPromotionVO;
    }
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getTotalQuantity() {
        return totalQuantity;
    }
    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    public String getTotalPromotionPrice() {
        return totalPromotionPrice;
    }
    public void setTotalPromotionPrice(String totalPromotionPrice) {
        this.totalPromotionPrice = totalPromotionPrice;
    }
    public String getPracticalPrice() {
        return practicalPrice;
    }
    public void setPracticalPrice(String practicalPrice) {
        this.practicalPrice = practicalPrice;
    }
    
    
    
    
}
