package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.Area;
import com.shangpin.biz.bo.City;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.Town;
import com.shangpin.biz.service.basic.IBizAddressService;

/**
 * 收货地址相关的Service
 * 
 * @author zhanghongwei
 *
 */
public interface ALBizAddressService extends IBizAddressService {
    /**
     * 查询收货地址
     * 
     * @param userId
     *            用户的唯一标识
     * @return
     * @author zhanghongwei
     */

    public String findAddress(String userId);

    /**
     * 查询收货地址
     * 
     * @param userId
     *            用户ID
     * @return
     * @author zhanghongwei
     */
    public List<Address> findAddressObj(String userId);

    /**
     * 获取省级行政区信息
     * 
     * @return
     * @author zhanghongwei
     */
    public String findProvinceList();

    /**
     * 获取省级行政区信息
     * 
     * @return
     * @author zhanghongwei
     */
    public List<Province> findProvinceListObj();

    /**
     * 设定根据地址id获取收货地址信息
     * 
     * @param userId
     *            用户的唯一标识
     * 
     * @parm addrid 用户收货地址的唯一标识
     * 
     * @return
     * @author zhanghongwei
     */
    public String findAddressById(String userId, String addrId);

    /**
     * 设定根据地址id获取收货地址信息
     * 
     * @param userId
     *            用户的唯一标识
     * 
     * @parm addrid 用户收货地址的唯一标识
     * 
     * @return
     * @author zhanghongwei
     */
    public Address findAddressByIdObj(String userId, String addrId);

    /**
     * 根据省份id获得市列表
     * 
     * @param provinceId
     *            省份ID
     * @return
     * @author zhanghongwei
     */
    public String findCityList(String provinceId);

    /**
     * 根据省份id获得市列表
     * 
     * @param provinceId
     *            省份ID
     * @return
     * @author zhanghongwei
     */
    public List<City> findCityListObj(String provinceId);

    /**
     * 根据市ID得到所在的区域列表
     * 
     * @param cityId
     *            城市ID
     * @return
     * @author zhanghongwei
     */
    public String findAreaList(String cityId);

    /**
     * 
    * @Title: findTownList 
    * @Description:获取区下面的街道
    * @param 
    * @return List<Town>
    * @throws 
    * @Create By qinyingchun
    * @Create Date 2014年11月10日
     */
    public List<Town> findTownList(String areaId);
    
    public String findTowns(String areaId);
    /**
     * 根据市ID得到所在的区域列表
     * 
     * @param cityId
     *            城市ID
     * @return
     * @author zhanghongwei
     */
    public List<Area> findAreaListObj(String cityId);

    /**
     * 新增收货地址（尚品、奥莱完全相同）
     * 
     * @param consigneeAddressVO
     *            请求参数
     * @return
     * @author zhanghongwei
     */
    public String addAddress(ConsigneeAddress consigneeAddressVO);

    /**
     * 新增收货地址并返回是否成功添加成功
     * 
     * @param address
     * @return
     * @author zhanghongwei
     */
    public boolean isAddAddress(Address address, String userId);

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
    public String deleteAddress(String userId, String addrId);

    /**
     * 编辑收货地址
     * 
     * @param consigneeAddressVO
     *            请求参数
     * @return
     * @author zhanghongwei
     */
    public String modifyAddress(ConsigneeAddress consigneeAddressVO);

    /**
     * 编辑收货地址并返回是否成功添加成功
     * 
     * @param address
     * @return
     * @author zhanghongwei
     */
    public boolean isModifyAddress(Address address, String userId);

    /**
     * 删除收货地址并返回是否成功添加成功
     * 
     * @param addrId
     *            地址id
     * @param userId
     *            用户id
     * @return
     * @author zhanghongwei
     */
    public boolean isDeleteAddress(String addrId, String userId);
    
    /**
     * 提交订单：新增收货地址
     * 
     * @param address 地址封装对象
     * @param userId 用户ID
     * @return
     *
     * @author cuibinqiang
     */
    public List<Address> addAddr(Address address,String userId);

}
