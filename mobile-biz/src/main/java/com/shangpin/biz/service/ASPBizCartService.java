package com.shangpin.biz.service;

/**
 * 客户端购物车service
 *
 * @author huangxiaoliang
 *
 */
public interface ASPBizCartService {

	/**
	 * 用于选中、取消选中显示购物车商品的接口
	 *
	 * @param userId
	 * @param isChecked
	 *            此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @return
	 */
	String doShowCart(String userId, String isChecked);

	/**
	 * 用于修改购物车商品数量后展示的接口
	 *
	 * @param userId
	 * @param cartItem
	 * @param isChecked
	 * @return
	 */
	String doModifyCart(String userId, String cartItem, String isChecked,String region);

	/**
	 * 用于删除购物车商品后展示的接口
	 *
	 * @param userId
	 * @param cartDetailId
	 * @param isChecked
	 * @return
	 */
	String doDeleteCart(String userId, String cartDetailId, String isChecked);


	/////////////////////////////////////////520//////////////////////////////////
	/**
	 * 用于选中、取消选中显示购物车商品的接口
	 *
	 * @param userId
	 * @param isChecked
	 *            此用户选中的单品，通过“|”线分割组装多个选中单品
	 * @return
	 */
	String doShowCartV2(String userId, String isChecked);

	/**
	 * 用于修改购物车商品数量后展示的接口
	 *
	 * @param userId
	 * @param cartItem
	 * @param isChecked
	 * @return
	 */
	String doModifyCartV2(String userId, String cartItem, String isChecked);

	/**
	 * 用于删除购物车商品后展示的接口
	 *
	 * @param userId
	 * @param cartDetailId
	 * @param isChecked
	 * @return
	 */
	String doDeleteCartV2(String userId, String cartDetailId, String isChecked);


}
