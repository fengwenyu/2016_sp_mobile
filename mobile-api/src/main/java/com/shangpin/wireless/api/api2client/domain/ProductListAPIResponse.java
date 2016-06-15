package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.api2server.domain.ProductServerAllCartVO;
import com.shangpin.wireless.api.domain.AlPromotionVO;
import com.shangpin.wireless.api.domain.ProductAllCartVO;
import com.shangpin.wireless.api.domain.ProductCartVO;
import com.shangpin.wireless.api.domain.ProductPropVO;
import com.shangpin.wireless.api.domain.PromotionVO;
import com.shangpin.wireless.api.domain.SpPromotionVO;
import com.shangpin.wireless.api.util.StringUtil;

/**
 * 返给客户端的json数据
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */

public class ProductListAPIResponse {

    /**
     * 返给客户端的json数据新版本
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public String objJson(ProductServerAllCartVO productServerAllCartVO) {
        JSONObject obj = new JSONObject();
        obj.put("code", productServerAllCartVO.getCode());
        obj.put("msg", productServerAllCartVO.getMsg());
        JSONObject content = new JSONObject();
        if ("0".equals(productServerAllCartVO.getCode())) {
            ProductAllCartVO all = productServerAllCartVO.getProductAllCartVO();
            content.put("totalamount", StringUtil.isNotEmptybyStr(all.getTotalPrice()));
            content.put("totalpayamount", StringUtil.isNotEmptybyStr(all.getPracticalPrice()));
            content.put("totalcount", StringUtil.isNotEmptybyStr(all.getTotalQuantity()));
            content.put("totalpromotionamount", StringUtil.isNotEmptybyStr(all.getTotalPromotionPrice()));
            List<SpPromotionVO> spProList = all.getSpPromotionVO();
            JSONArray spcart = new JSONArray();
            if (spProList != null && spProList.size() > 0) {
                PromotionVO spPromotion;
                List<ProductCartVO> spCart;
                List<ProductPropVO> propList;
                JSONObject spProJson;
                JSONObject spproduct;
                String type = "";
                for (int i = 0; i < spProList.size(); i++) {
                    spProJson = new JSONObject();
                    spPromotion = spProList.get(i).getPromotion();
                    spProJson.put("ispromotion", StringUtil.isNotEmptybyStr(spPromotion.getIspromotion()));
                    spProJson.put("promotionno", StringUtil.isNotEmptybyStr(spPromotion.getPromotionno()));
                    spProJson.put("promotiontype", StringUtil.isNotEmptybyStr(spPromotion.getPromotiontype()));
                    type = StringUtil.isNotEmptybyStr(spPromotion.getPromotiontype());
                    if (!"".equals(type)) {
                        if ("1".equals(type)) {
                            spProJson.put("promotiontypedesc", "满额减");
                        } else if ("2".equals(type)) {
                            spProJson.put("promotiontypedesc", "多件折");
                        }
                    }
                    spProJson.put("promotioncontent", StringUtil.isNotEmptybyStr(spPromotion.getPromotioncontent()));
                    spProJson.put("promotiondesc", StringUtil.isNotEmptybyStr(spPromotion.getPromotiondesc()));
                    spProJson.put("promotionurl", StringUtil.isNotEmptybyStr(spPromotion.getPromotionurl()));
                    spProJson.put("couponprice", StringUtil.isNotEmptybyStr(spPromotion.getCouponprice()));
                    spProJson.put("promotionsign", "0".equals(spPromotion.getCouponprice()) ? "" : "优惠 ￥" + spPromotion.getCouponprice());
                    spCart = spProList.get(i).getProductCartVO();
                    JSONArray list = new JSONArray();
                    for (int j = 0; j < spCart.size(); j++) {
                        spproduct = new JSONObject();
                        spproduct.put("shopdetailid", StringUtil.isNotEmptybyStr(spCart.get(j).getShopDetailId()));
                        spproduct.put("productname", StringUtil.isNotEmptybyStr(spCart.get(j).getProductName()));
                        spproduct.put("price", StringUtil.isNotEmptybyStr(spCart.get(j).getPrice()));
                        spproduct.put("promotionprice", StringUtil.isNotEmptybyStr(spCart.get(j).getPromotionPrice()));
                        spproduct.put("favoriteprice", StringUtil.isNotEmptybyStr(spCart.get(j).getFavoritePrice()));
                        spproduct.put("count", StringUtil.isNotEmptybyStr(spCart.get(j).getQuantity()));
                        spproduct.put("sku", StringUtil.isNotEmptybyStr(spCart.get(j).getSkuNo()));
                        spproduct.put("brandname", StringUtil.isNotEmptybyStr(spCart.get(j).getBrandName()));
                        spproduct.put("topicid", StringUtil.isNotEmptybyStr(spCart.get(j).getCategoryNo()));
                        spproduct.put("alcategoryno", StringUtil.isNotEmptybyStr(spCart.get(j).getAlCategoryNo()));
                        spproduct.put("pic", StringUtil.isNotEmptybyStr(spCart.get(j).getImg()));
                        spproduct.put("url", StringUtil.isNotEmptybyStr(spCart.get(j).getProductUrl()));
                        spproduct.put("productno", StringUtil.isNotEmptybyStr(spCart.get(j).getProductNo()));
                        spproduct.put("datetime", StringUtil.isNotEmptybyStr(spCart.get(j).getDateTime()));
                        propList = spCart.get(j).getSkuAttrText();
                        JSONArray prop = new JSONArray();
                        if (propList != null && propList.size() > 0) {
                            for (int z = 0; z < propList.size(); z++) {
                                JSONObject propObj = new JSONObject();
                                propObj.put("name", propList.get(z).getName());
                                propObj.put("value", propList.get(z).getValue());
                                prop.add(propObj);
                            }
                        }
                        spproduct.put("prop", prop);
                        spproduct.put("propstr", StringUtil.isNotEmptybyStr(spCart.get(j).getSkuAttrTextStr()));
                        spproduct.put("fromtype", StringUtil.isNotEmptybyStr(spCart.get(j).getIslimitedOutlet()));
                        spproduct.put("errorcode", StringUtil.isNotEmptybyStr(spCart.get(j).getMsgType()));
                        if ("2".equals(spCart.get(j).getMsgType())) {
                            spproduct.put("errormsg", "抱歉商品数量有限您只能购买" + StringUtil.isNotEmptybyStr(spCart.get(j).getMsg()) + "件");
                        } else {
                            spproduct.put("errormsg", StringUtil.isNotEmptybyStr(spCart.get(j).getMsg()));
                        }

                        list.add(spproduct);

                    }
                    spProJson.put("list", list);
                    spcart.add(spProJson);
                }

            }
            content.put("spcart", spcart);

            List<AlPromotionVO> alProList = all.getAlPromotionVO();
            JSONArray alcart = new JSONArray();
            if (alProList != null && alProList.size() > 0) {
                PromotionVO alPromotion;
                List<ProductCartVO> alCart;
                List<ProductPropVO> propList;
                JSONObject alProJson;
                JSONObject alproduct;
                for (int i = 0; i < alProList.size(); i++) {
                    alProJson = new JSONObject();
                    alPromotion = alProList.get(i).getPromotion();
                    alProJson.put("ispromotion", StringUtil.isNotEmptybyStr(alPromotion.getIspromotion()));
                    alProJson.put("promotionno", StringUtil.isNotEmptybyStr(alPromotion.getPromotionno()));
                    alProJson.put("promotiontype", StringUtil.isNotEmptybyStr(alPromotion.getPromotiontype()));
                    alProJson.put("promotioncontent", StringUtil.isNotEmptybyStr(alPromotion.getPromotioncontent()));
                    alProJson.put("promotiondesc", StringUtil.isNotEmptybyStr(alPromotion.getPromotiondesc()));
                    alProJson.put("promotionurl", StringUtil.isNotEmptybyStr(alPromotion.getPromotionurl()));
                    alProJson.put("couponprice", StringUtil.isNotEmptybyStr(alPromotion.getCouponprice()));
                    alProJson.put("promotionsign", "0".equals(alPromotion.getCouponprice()) ? "" : "优惠 ￥" + alPromotion.getCouponprice());
                    alCart = alProList.get(i).getProductCartVO();
                    JSONArray list = new JSONArray();
                    for (int j = 0; j < alCart.size(); j++) {
                        alproduct = new JSONObject();
                        alproduct.put("shopdetailid", StringUtil.isNotEmptybyStr(alCart.get(j).getShopDetailId()));
                        alproduct.put("productname", StringUtil.isNotEmptybyStr(alCart.get(j).getProductName()));
                        alproduct.put("price", StringUtil.isNotEmptybyStr(alCart.get(j).getPrice()));
                        alproduct.put("promotionprice", StringUtil.isNotEmptybyStr(alCart.get(j).getPromotionPrice()));
                        alproduct.put("favoriteprice", StringUtil.isNotEmptybyStr(alCart.get(j).getFavoritePrice()));
                        alproduct.put("count", StringUtil.isNotEmptybyStr(alCart.get(j).getQuantity()));
                        alproduct.put("sku", StringUtil.isNotEmptybyStr(alCart.get(j).getSkuNo()));
                        alproduct.put("brandname", StringUtil.isNotEmptybyStr(alCart.get(j).getBrandName()));
                        alproduct.put("activityid", StringUtil.isNotEmptybyStr(alCart.get(j).getCategoryNo()));
                        alproduct.put("categoryno", StringUtil.isNotEmptybyStr(alCart.get(j).getAlCategoryNo()));
                        alproduct.put("pic", StringUtil.isNotEmptybyStr(alCart.get(j).getImg()));
                        alproduct.put("url", StringUtil.isNotEmptybyStr(alCart.get(j).getProductUrl()));
                        alproduct.put("productno", StringUtil.isNotEmptybyStr(alCart.get(j).getProductNo()));
                        alproduct.put("datetime", StringUtil.isNotEmptybyStr(alCart.get(j).getDateTime()));
                        propList = alCart.get(j).getSkuAttrText();
                        JSONArray prop = new JSONArray();
                        if (propList != null && propList.size() > 0) {
                            for (int z = 0; z < propList.size(); z++) {
                                JSONObject propObj = new JSONObject();
                                propObj.put("name", propList.get(z).getName());
                                propObj.put("value", propList.get(z).getValue());
                                prop.add(propObj);
                            }
                        }
                        alproduct.put("prop", prop);
                        alproduct.put("propstr", StringUtil.isNotEmptybyStr(alCart.get(j).getSkuAttrTextStr()));
                        alproduct.put("fromtype", StringUtil.isNotEmptybyStr(alCart.get(j).getIslimitedOutlet()));
                        alproduct.put("errorcode", StringUtil.isNotEmptybyStr(alCart.get(j).getMsgType()));
                        if ("2".equals(alCart.get(j).getMsgType())) {
                            alproduct.put("errormsg", "抱歉商品数量有限您只能购买" + StringUtil.isNotEmptybyStr(alCart.get(j).getMsg()) + "件");
                        } else {
                            alproduct.put("errormsg", StringUtil.isNotEmptybyStr(alCart.get(j).getMsg()));
                        }

                        list.add(alproduct);

                    }
                    alProJson.put("list", list);
                    alcart.add(alProJson);
                }

            }
            content.put("alcart", alcart);

        }
        obj.put("content", content);
        return obj.toString();
    }

    /**
     * 返给客户端的json数据新版本兼容版本
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public String objJson2(String shoptype, ProductServerAllCartVO productServerAllCartVO) {
        JSONObject obj = new JSONObject();
        obj.put("code", productServerAllCartVO.getCode());
        obj.put("msg", productServerAllCartVO.getMsg());
        JSONObject content = new JSONObject();
        Double totalAmount = 0.0;
        Double totalcouponPrice = 0.0;
        if ("0".equals(productServerAllCartVO.getCode())) {
            ProductAllCartVO all = productServerAllCartVO.getProductAllCartVO();
            List<SpPromotionVO> spProList = all.getSpPromotionVO();
            JSONArray spcart = new JSONArray();
            PromotionVO promotion = new PromotionVO();
            if ("1".equals(shoptype)) {
                if (spProList != null && spProList.size() > 0) {
                    List<ProductCartVO> spCart;
                    List<ProductPropVO> propList;
                    JSONObject spproduct;
                    for (int i = 0; i < spProList.size(); i++) {
                        promotion = spProList.get(i).getPromotion();
                        totalcouponPrice += Double.parseDouble(promotion.getCouponprice());
                        spCart = spProList.get(i).getProductCartVO();
                        for (int j = 0; j < spCart.size(); j++) {
                            spproduct = new JSONObject();
                            spproduct.put("sku", StringUtil.isNotEmptybyStr(spCart.get(j).getSkuNo()));
                            spproduct.put("brand", StringUtil.isNotEmptybyStr(spCart.get(j).getBrandName()));
                            spproduct.put("cate", StringUtil.isNotEmptybyStr(spCart.get(j).getBrandNo()));
                            propList = spCart.get(j).getSkuAttrText();
                            if (propList != null && propList.size() > 0) {
                                for (int z = 0; z < propList.size(); z++) {
                                    if (propList.size() == 1) {
                                        spproduct.put("firstpropname", propList.get(z).getName());
                                        spproduct.put("firstpropvalue", propList.get(z).getValue());
                                        spproduct.put("secondpropname", "");
                                        spproduct.put("secondpropvalue", "");
                                    } else {
                                        if (z == 0) {
                                            spproduct.put("firstpropname", propList.get(z).getName());
                                            spproduct.put("firstpropvalue", propList.get(z).getValue());
                                        } else if (z == 1) {
                                            spproduct.put("secondpropname", propList.get(z).getName());
                                            spproduct.put("secondpropvalue", propList.get(z).getValue());
                                        }

                                    }
                                }
                            }
                            spproduct.put("count", StringUtil.isNotEmptybyStr(spCart.get(j).getQuantity()));
                            spproduct.put("amount", StringUtil.isNotEmptybyStr(spCart.get(j).getPrice()));
                            if ("0".equals(spCart.get(j).getMsgType())) {

                                totalAmount += Double.parseDouble(spCart.get(j).getTotalQuantityAmount());
                            }
                            spproduct.put("pic", StringUtil.isNotEmptybyStr(spCart.get(j).getImg()));
                            spproduct.put("productname", StringUtil.isNotEmptybyStr(spCart.get(j).getProductName()));
                            spproduct.put("errorcode", StringUtil.isNotEmptybyStr(spCart.get(j).getMsgType()));
                            if ("2".equals(spCart.get(j).getMsgType())) {
                                spproduct.put("errormsg", "抱歉商品数量有限您只能购买" + StringUtil.isNotEmptybyStr(spCart.get(j).getMsg()) + "件");
                            } else {
                                spproduct.put("errormsg", StringUtil.isNotEmptybyStr(spCart.get(j).getMsg()));
                            }
                            spproduct.put("goodsid", StringUtil.isNotEmptybyStr(spCart.get(j).getProductNo()));
                            spproduct.put("goodsname", StringUtil.isNotEmptybyStr(spCart.get(j).getProductName()));
                            spproduct.put("categoryno", StringUtil.isNotEmptybyStr(spCart.get(j).getCategoryNo()));
                            spproduct.put("hidden", "0");
                            spproduct.put("gid", StringUtil.isNotEmptybyStr(spCart.get(j).getShopDetailId()));
                            spproduct.put("groupno", "");
                            spproduct.put("groupprice", "");
                            spproduct.put("groupdiscount", "");
                            spproduct.put("groupid", "");
                            spproduct.put("shoppingcartdetailid", StringUtil.isNotEmptybyStr(spCart.get(j).getShopDetailId()));
                            spcart.add(spproduct);
                        }
                    }
                }
                content.put("list", spcart);
            } else {
                List<AlPromotionVO> alProList = all.getAlPromotionVO();
                JSONArray alcart = new JSONArray();
                if (alProList != null && alProList.size() > 0) {
                    List<ProductCartVO> alCart;
                    List<ProductPropVO> propList;
                    JSONObject alproduct;
                    for (int i = 0; i < alProList.size(); i++) {
                        promotion = alProList.get(i).getPromotion();
                        totalcouponPrice += Double.parseDouble(promotion.getCouponprice());
                        alCart = alProList.get(i).getProductCartVO();
                        for (int j = 0; j < alCart.size(); j++) {
                            alproduct = new JSONObject();
                            alproduct.put("sku", StringUtil.isNotEmptybyStr(alCart.get(j).getSkuNo()));
                            alproduct.put("brand", StringUtil.isNotEmptybyStr(alCart.get(j).getBrandName()));
                            alproduct.put("cate", StringUtil.isNotEmptybyStr(alCart.get(j).getBrandNo()));

                            propList = alCart.get(j).getSkuAttrText();
                            if (propList != null && propList.size() > 0) {
                                for (int z = 0; z < propList.size(); z++) {
                                    if (propList.size() == 1) {
                                        alproduct.put("firstpropname", propList.get(z).getName());
                                        alproduct.put("firstpropvalue", propList.get(z).getValue());
                                        alproduct.put("secondpropname", "");
                                        alproduct.put("secondpropvalue", "");
                                    } else {
                                        if (z == 0) {
                                            alproduct.put("firstpropname", propList.get(z).getName());
                                            alproduct.put("firstpropvalue", propList.get(z).getValue());
                                        } else if (z == 1) {
                                            alproduct.put("secondpropname", propList.get(z).getName());
                                            alproduct.put("secondpropvalue", propList.get(z).getValue());
                                        }

                                    }
                                }
                            }
                            alproduct.put("count", StringUtil.isNotEmptybyStr(alCart.get(j).getQuantity()));
                            alproduct.put("amount", StringUtil.isNotEmptybyStr(alCart.get(j).getPrice()));
                            if ("0".equals(alCart.get(j).getMsgType())) {

                                totalAmount += Double.parseDouble(alCart.get(j).getTotalQuantityAmount());
                            }

                            alproduct.put("pic", StringUtil.isNotEmptybyStr(alCart.get(j).getImg()));
                            alproduct.put("productname", StringUtil.isNotEmptybyStr(alCart.get(j).getProductName()));
                            alproduct.put("errorcode", StringUtil.isNotEmptybyStr(alCart.get(j).getMsgType()));
                            if ("2".equals(alCart.get(j).getMsgType())) {
                                alproduct.put("errormsg", "抱歉商品数量有限您只能购买" + StringUtil.isNotEmptybyStr(alCart.get(j).getMsg()) + "件");
                            } else {
                                alproduct.put("errormsg", StringUtil.isNotEmptybyStr(alCart.get(j).getMsg()));
                            }
                            alproduct.put("goodsid", StringUtil.isNotEmptybyStr(alCart.get(j).getProductNo()));
                            alproduct.put("goodsname", StringUtil.isNotEmptybyStr(alCart.get(j).getProductName()));
                            alproduct.put("categoryno", StringUtil.isNotEmptybyStr(alCart.get(j).getCategoryNo()));
                            alproduct.put("hidden", "0");
                            alproduct.put("gid", StringUtil.isNotEmptybyStr(alCart.get(j).getShopDetailId()));
                            alproduct.put("groupno", "");
                            alproduct.put("groupprice", "");
                            alproduct.put("groupdiscount", "");
                            alproduct.put("groupid", "");
                            alproduct.put("shoppingcartdetailid", StringUtil.isNotEmptybyStr(alCart.get(j).getShopDetailId()));
                            alcart.add(alproduct);
                        }
                    }

                }
                content.put("list", alcart);
            }
            content.put("totalamount", StringUtil.cutOffDecimal(StringUtil.isNotEmptybyStr(String.valueOf(totalAmount))));
        }
        obj.put("content", content);
        return obj.toString();
    }

}
