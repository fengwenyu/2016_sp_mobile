package com.aolai.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aolai.web.utils.MesConstants;
import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.Area;
import com.shangpin.biz.bo.City;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.Town;
import com.shangpin.biz.service.ALBizAddressService;

/**
 * 地址相关模块
 * 
 * @author zhanghongwei
 *
 */
@Controller
public class AddressController extends BaseController {
    // 收货地址列表页
    public static final String ADDRESS_LIST = "address/list";
    // 收货地址新增页
    public static final String ADDRESS_ADD = "address/add";
    // 收货地址修改页
    public static final String ADDRESS_EDIT = "address/edit";

    @Autowired
    ALBizAddressService addressService;

    /**
     * 跳转到收货地址列表
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/list")
    public String list(Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        List<Address> addressList = addressService.findAddressObj(userId);
        map.put("addressList", addressList);
        return ADDRESS_LIST;
    }

    /**
     * 跳转到新增收货地址页面
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/add")
    public String add(Map<String, Object> map) {
        List<Province> provinceList = addressService.findProvinceListObj();
        map.put("provinceList", provinceList);
        return ADDRESS_ADD;
    }

    /**
     * 跳转到修改收货地址页面
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/edit")
    public String edit(String addressId, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        Address address = addressService.findAddressByIdObj(userId, addressId);
        List<Province> provinceList = addressService.findProvinceListObj();
        List<City> cityList = addressService.findCityListObj(address.getProvince());
        List<Area> areaList = addressService.findAreaListObj(address.getCity());
        List<Town> townList = addressService.findTownList(address.getArea());
        map.put("address", address);
        map.put("provinceList", provinceList);
        map.put("cityList", cityList);
        map.put("areaList", areaList);
        map.put("townList", townList);
        return ADDRESS_EDIT;
    }

    /**
     * 保存收货地址
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/save")
    public String save(Address address, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        if (addressService.isAddAddress(address, userId)) {
            return "redirect:/user/address/list";
        }
        map.put("msg", MesConstants.ADDR_FAIL_SAVE);
        return add(map);
    }

    /**
     * 修改收货地址
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/update")
    public String update(Address address, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        if (addressService.isModifyAddress(address, userId)) {
            return "redirect:/user/address/list";
        }
        map.put("msg", MesConstants.ADDR_FAIL_UPDATE);
        return edit(address.getId(), map, request);
    }

    /**
     * ajax删除收货地址
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/del")
    @ResponseBody
    public String delete(String addressId, Map<String, Object> map, HttpServletRequest request) {
        String userId = getUserId(request);
        String success = "false";
        if (addressService.isDeleteAddress(addressId, userId)) {
            return success = "true";
        }
        return success;
    }

    /**
     * 请求市级返回json
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/city")
    @ResponseBody
    public String city(String id) {
        String result = addressService.findCityList(id);
        return result;
    }

    /**
     * 请求区域返回json
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/area")
    @ResponseBody
    public String area(String id) {
        String result = addressService.findAreaList(id);
        return result;
    }
    
    /**
     * 请求街道返回json
     * 
     * @author zhanghongwei
     */
    @RequestMapping(value = "/user/address/town")
    @ResponseBody
    public String town(String id) {
        String result = addressService.findTowns(id);
        return result;
    }

}
