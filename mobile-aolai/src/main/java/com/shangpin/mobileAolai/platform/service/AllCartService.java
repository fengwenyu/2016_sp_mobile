package com.shangpin.mobileAolai.platform.service;


import com.shangpin.mobileAolai.platform.vo.ProductServerAllCartVO;

import net.sf.json.JSONObject;


/**
 * 新购物车service
 * 
 * @Author wangfeng
 * @CreatDate 2014-07-02
 */
public interface AllCartService {
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
    public ProductServerAllCartVO cartProducts(String userId, String pich, String picw, String shopType,String isPromotion);
    /**
     * 获取购物商品
     * 
     * @param userId
     *            用户id
     * @param shopDetailId
     *           购物车id

     * @return 返回结果
     */
    public JSONObject delPrdoucts(String userId, String shopDetailId);
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
    public JSONObject updateCartCount(String userId, String shopDetailId, String quantity);
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
    public String addCart(String userId,String productNo, String quantity, String sku, String categoryNo);

}
