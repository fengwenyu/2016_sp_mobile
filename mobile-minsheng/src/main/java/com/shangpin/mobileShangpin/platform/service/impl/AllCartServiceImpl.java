package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.AllCartService;
import com.shangpin.mobileShangpin.platform.vo.ProductServerAllCartVO;

/**
 * 新购物车service实现类
 * 
 * @Author wangfeng
 * @CreatDate 2014-07-02
 */
@Service("allCartService")
public class AllCartServiceImpl implements AllCartService{
    /**
     * 获取购物商品
     * 
     * @param userId
     *            用户id
     * @param pich
     *            购物车图片高度
     * @param picw
     *            购物车图片宽度
     * @param shopType
     *            来源（1尚品，2奥莱）
     * 
     * @return 返回活动列表
     */
    @Override
    public ProductServerAllCartVO cartProducts(String userId, String pich, String picw, String shopType,String isPromotion){
        String url = Constants.BASE_TRADE_URL + "trade/cartlist/";
        Map<String, String> map=new HashMap<String, String>();
        map.put("userid", userId);
        map.put("width", picw);
        map.put("height", pich);
        map.put("isPromotion", isPromotion);
        String data = WebUtil.readContentFromGet(url, map);
        ProductServerAllCartVO productServerAllCartVO=new ProductServerAllCartVO().jsonObj(data,shopType);
        return productServerAllCartVO;
    }
    /**
     * 获取购物商品
     * 
     * @param userId
     *            用户id
     * @param shopDetailId
     *           购物车id

     * @return 返回结果
     */
    @Override
    public JSONObject delPrdoucts(String userId, String shopDetailId) {
        String url = Constants.BASE_TRADE_URL + "trade/deletecartbydetailId/";
        Map<String, String> map=new HashMap<String, String>();
        map.put("userId", userId);
        map.put("shopCartDetailIds", shopDetailId);
        String data = WebUtil.readContentFromPost(url, map);
        return JSONObject.fromObject(data);
    }
    /**
     * 修改购物商品数量
     * 
     * @param userId
     *            用户id
     * @param shopDetailId
     *           购物车id
     * @param quantity
     *           购物车quantity

     * @return 返回结果
     */
    @Override
    public JSONObject updateCartCount(String userId, String shopDetailId, String quantity) {
        String url = Constants.BASE_TRADE_URL + "trade/updatecartbyquantity/";
        Map<String, String> map=new HashMap<String, String>();
        map.put("shopCartDetailId", shopDetailId);
        map.put("userId", userId);
        map.put("quantity", quantity);
        String data = WebUtil.readContentFromPost(url, map);
        return JSONObject.fromObject(data);
    }
    /**
     * 加入购物袋
     * 
     * @param userId
     *            用户id
     * @param productNo
     *           商品编号
     * @param quantity
     *          数量
     * @param sku
     *          sku
     *@param categoryNo
     *          专题编号或者活动子编号
     * @return 返回结果
     */
    @Override
    public String addCart(String userId, String productNo, String quantity, String sku, String categoryNo) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("productNo", productNo);
        map.put("quantity", quantity);
        map.put("skuNo", sku);
        map.put("siteNo", "1");
        map.put("dynamicattributetext", "");
        if(categoryNo==null||"".equals(categoryNo)){
            map.put("categoryNo", "0");
            map.put("topicSubjectFlag", "1");
        }else{  
            map.put("categoryNo", categoryNo);
            map.put("topicSubjectFlag", "2");
        }
        map.put("skuFrom", "3");
        map.put("vipNo", "0");
        map.put("userId", userId);
        String url = Constants.BASE_TRADE_URL + "trade/addproducttocart/";
        String data = WebUtil.readContentFromPost(url, map);
        return data;
    }

}
