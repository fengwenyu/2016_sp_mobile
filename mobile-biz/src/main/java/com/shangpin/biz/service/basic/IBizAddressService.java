package com.shangpin.biz.service.basic;

import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.Area;
import com.shangpin.biz.bo.City;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.Town;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjList;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.bo.base.ResultObjOne;

/**
 * 收货地址基础接口
 * 
 * @author zghw
 *
 */
public interface IBizAddressService {
	/**
	 * 返回收货地址
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromAddress(String userId);

	/**
	 * 返回收货地址对象
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjMapList<Address> beAddress(String userId);

	/**
	 * 设定根据地址id获取收货地址信息
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromAddressById(String userId, String addrId);

	/**
	 * 设定根据地址id获取收货地址信息对象
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjOne<Address> beAddressById(String userId, String addrId);

	/**
	 * 获取省级行政区信息
	 * 
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromProvince();

	/**
	 * 获取省级行政区信息对象
	 * 
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjList<Province> beProvince();

	/**
	 * 根据省份id获得市列表
	 * 
	 * @param provinceId
	 *            省份ID
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromCity(String provinceId);

	/**
	 * 根据省份id获得市对象
	 * 
	 * @param provinceId
	 *            省份ID
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjList<City> beCity(String provinceId);

	/**
	 * 根据市ID得到所在的区域
	 * 
	 * @param cityId
	 *            城市ID
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromArea(String cityId);

	/**
	 * 根据市ID得到所在的区域对象
	 * 
	 * @param cityId
	 *            城市ID
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjList<Area> beArea(String cityId);

	/**
	 * 根据区ID得到所在的镇
	 * 
	 * @param areaId
	 *            区ID
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromTown(String areaId);

	/**
	 * 根据区ID得到所在的镇对象
	 * 
	 * @param areaId
	 *            区ID
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjList<Town> beTown(String areaId);

	/**
	 * 新增收货地址
	 * 
	 * @param consigneeAddressVO
	 *            请求参数
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromAddAddress(ConsigneeAddress consigneeAddressVO);

	/**
	 * 新增收货地址
	 * 
	 * @param consigneeAddressVO
	 *            请求参数
	 * @return json串
	 * @author zhanghongwei
	 */
	public ResultObjMapList<Address> beAddAddress(ConsigneeAddress consigneeAddressVO);

	/**
	 * 删除收货地址
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @param addrId
	 *            用户收货地址的唯一标识
	 * @return
	 * @author zhanghongwei
	 */
	public String fromDeleteAddress(String userId, String addrId);

	/**
	 * 删除收货地址
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @param addrId
	 *            用户收货地址的唯一标识
	 * @return
	 * @author zhanghongwei
	 */
	public ResultBase beDeleteAddress(String userId, String addrId);

	/**
	 * 编辑收货地址
	 * 
	 * @param consigneeAddressVO
	 *            请求参数
	 * @return
	 * @author zhanghongwei
	 */
	public String fromModifyAddress(ConsigneeAddress consigneeAddressVO);

	/**
	 * 编辑收货地址
	 * 
	 * @param consigneeAddressVO
	 *            请求参数
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjMapList<Address> beModifyAddress(ConsigneeAddress consigneeAddressVO);
}
