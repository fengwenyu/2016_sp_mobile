package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.CustomerService;
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
import com.shangpin.biz.service.ALBizAddressService;
import com.shangpin.biz.service.abstraction.AbstractBizAddressService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.ParseJsonUtil;
import com.shangpin.utils.JsonUtil;

@Service
public class ALBizAddressServiceImpl extends AbstractBizAddressService implements ALBizAddressService {
	
	private static final Logger logger = LoggerFactory.getLogger(ALBizAddressServiceImpl.class);
    @Autowired
    private CommonService commonService;
    @Autowired
    private CustomerService userService;

    @Override
    public String findAddress(String userId) {
        String json = userService.findConsigneeAddress(userId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public List<Address> findAddressObj(String userId) {
        try {
            String result = findAddress(userId);
            if (!StringUtils.isEmpty(result)) {
                ResultObjMapList<Address> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjMapList<Address>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getList("list");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findProvinceList() {
        String json = commonService.findProvinceList();
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public List<Province> findProvinceListObj() {
        try {
            String result = findProvinceList();
            if (!StringUtils.isEmpty(result)) {
                ResultObjList<Province> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<Province>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findAddressById(String userId, String addrId) {
        String json = userService.findConsigneeAddressById(userId, addrId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public Address findAddressByIdObj(String userId, String addrId) {
        try {
            String result = findAddressById(userId, addrId);
            if (!StringUtils.isEmpty(result)) {
                ResultObjOne<Address> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<Address>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getObj();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findCityList(String provinceId) {
        String json = commonService.findCityList(provinceId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public List<City> findCityListObj(String provinceId) {
        try {
            String result = findCityList(provinceId);
            if (!StringUtils.isEmpty(result)) {
                ResultObjList<City> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<City>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findAreaList(String cityId) {
        String json = commonService.findAreaList(cityId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public List<Area> findAreaListObj(String cityId) {
        try {
            String result = findAreaList(cityId);
            if (!StringUtils.isEmpty(result)) {
                ResultObjList<Area> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<Area>>() {
                });
                if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
                    return obj.getList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String addAddress(ConsigneeAddress consigneeAddressVO) {
        String json = userService.addConsigneeAddress(consigneeAddressVO);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }
    
    

    @Override
    public boolean isAddAddress(Address address, String userId) {
        ConsigneeAddress ca = putContainer(address, userId);
        try {
            String result = addAddress(ca);
            if (!StringUtils.isEmpty(result)) {
                ResultBase obj = JsonUtil.fromJson(result, ResultBase.class);
                if (!StringUtils.isEmpty(obj)) {
                    return obj.isSuccess();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 提交订单：新增收货地址
     */
    @Override
	public List<Address> addAddr(Address address,String userId) {
    	ConsigneeAddress ca = putContainer(address, userId);
    	ObjectMapper  mapper = new ObjectMapper();
		JsonNode rootNode;
        try {
            String result = addAddress(ca);//主站返回的json
            rootNode = mapper.readTree(result);
			String code = rootNode.path("code").asText();
			String jsonList = rootNode.path("content").path("list").toString();
            if (!StringUtils.isEmpty(result) && Constants.SUCCESS.equals(code)) {//主站处理成功
            	JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class,Address.class);
				List<Address> addrList = mapper.readValue(jsonList, javaType);
				return addrList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

    @Override
    public String modifyAddress(ConsigneeAddress consigneeAddressVO) {
        String json = userService.modifyConsigneeAddress(consigneeAddressVO);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public boolean isModifyAddress(Address address, String userId) {
        ConsigneeAddress ca = putContainer(address, userId);

        try {
            String result = modifyAddress(ca);
            if (!StringUtils.isEmpty(result)) {
                ResultBase obj = JsonUtil.fromJson(result, ResultBase.class);
                if (!StringUtils.isEmpty(obj)) {
                    return obj.isSuccess();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String deleteAddress(String userId, String addrId) {
        String json = userService.deleteConsigneeAddress(userId, addrId);
        if (!StringUtils.isEmpty(json)) {
            return json;
        }
        return null;
    }

    @Override
    public boolean isDeleteAddress(String addrId, String userId) {
        try {
            String result = deleteAddress(userId, addrId);
            if (!StringUtils.isEmpty(result)) {
                ResultBase obj = JsonUtil.fromJson(result, ResultBase.class);
                if (!StringUtils.isEmpty(obj)) {
                    return obj.isSuccess();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置参数 装箱
     * 
     * @param address
     * @param userId
     * @return
     */
    private ConsigneeAddress putContainer(Address address, String userId) {
        ConsigneeAddress ca = new ConsigneeAddress();
        ca.setUserId(userId);
        ca.setAddrId(address.getId());
        ca.setConsigneeName(address.getName());
        ca.setProvince(address.getProvince());
        ca.setCity(address.getCity());
        ca.setArea(address.getArea());
        ca.setTown(address.getTown());
        ca.setAddress(address.getAddr());
        ca.setPostcode(address.getPostcode());
        ca.setTel(address.getTel());
        ca.setSetd(null != address.getIsd() && "on".equals(address.getIsd()) ? "true" : "false");
        return ca;
    }

	@Override
	public List<Town> findTownList(String areaId) {
		try {
			String json = commonService.findTownList(areaId);
			if(!StringUtils.isEmpty(json)){
				ResultObjList<Town> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjList<Town>>(){});
				if(!StringUtils.isEmpty(obj)){
					return obj.getList();
				}
			}
		} catch (Exception e) {
			logger.error("调用base接口获取街道信息数据错误！");
		}
		return null;
	}

	@Override
	public String findTowns(String areaId) {
		String json = commonService.findTownList(areaId);
		if(!StringUtils.isEmpty(json)){
			return json;
		}
		return null;
	}

	

}
