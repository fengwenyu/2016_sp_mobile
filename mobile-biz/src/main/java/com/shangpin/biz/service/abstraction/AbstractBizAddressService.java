package com.shangpin.biz.service.abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizAddressService {

	@Autowired
	private CommonService commonService;
	@Autowired
	private CustomerService userService;

	public String fromAddress(String userId) {
		String json = userService.findConsigneeAddress(userId);
		return json;
	}

	public ResultObjMapList<Address> beAddress(String userId) {
		String json = fromAddress(userId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
			});
			return result;
		}
		return null;
	}

	public String fromAddressById(String userId, String addrId) {
		String json = userService.findConsigneeAddressById(userId, addrId);
		return json;
	}

	public ResultObjOne<Address> beAddressById(String userId, String addrId) {
		String json = fromAddressById(userId, addrId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjOne<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Address>>() {
			});
			return result;
		}
		return null;
	}

	public String fromProvince() {
		String json = commonService.findProvinceList();
		return json;
	}

	public ResultObjList<Province> beProvince() {
		String json = fromProvince();
		if (!StringUtils.isEmpty(json)) {
			ResultObjList<Province> result = JsonUtil.fromJson(json, new TypeToken<ResultObjList<Province>>() {
			});
			return result;
		}
		return null;
	}

	public String fromCity(String provinceId) {
		String json = commonService.findCityList(provinceId);
		return json;
	}

	public ResultObjList<City> beCity(String provinceId) {
		String json = fromCity(provinceId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjList<City> result = JsonUtil.fromJson(json, new TypeToken<ResultObjList<City>>() {
			});
			return result;
		}
		return null;
	}

	public String fromArea(String cityId) {
		String json = commonService.findAreaList(cityId);
		return json;
	}

	public ResultObjList<Area> beArea(String cityId) {
		String json = fromArea(cityId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjList<Area> result = JsonUtil.fromJson(json, new TypeToken<ResultObjList<Area>>() {
			});
			return result;
		}
		return null;
	}

	public String fromTown(String areaId) {
		String json = commonService.findTownList(areaId);
		return json;
	}

	public ResultObjList<Town> beTown(String areaId) {
		String json = fromTown(areaId);
		if (!StringUtils.isEmpty(json)) {
			ResultObjList<Town> result = JsonUtil.fromJson(json, new TypeToken<ResultObjList<Town>>() {
			});
			return result;
		}
		return null;
	}

	public String fromAddAddress(ConsigneeAddress consigneeAddressVO) {
		String json = userService.addConsigneeAddress(consigneeAddressVO);
		return json;
	}

	public ResultObjMapList<Address> beAddAddress(ConsigneeAddress consigneeAddressVO) {
		String json = fromAddAddress(consigneeAddressVO);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
			});
			return result;
		}
		return null;
	}

	public String fromDeleteAddress(String userId, String addrId) {
		String json = userService.deleteConsigneeAddress(userId, addrId);
		return json;
	}

	public ResultBase beDeleteAddress(String userId, String addrId) {
		String json = fromDeleteAddress(userId, addrId);
		if (!StringUtils.isEmpty(json)) {
			ResultBase result = JsonUtil.fromJson(json, new TypeToken<ResultBase>() {
			});
			return result;
		}
		return null;
	}

	public String fromModifyAddress(ConsigneeAddress consigneeAddressVO) {
		String json = userService.modifyConsigneeAddress(consigneeAddressVO);
		return json;
	}

	public ResultObjMapList<Address> beModifyAddress(ConsigneeAddress consigneeAddressVO) {
		String json = fromModifyAddress(consigneeAddressVO);
		if (!StringUtils.isEmpty(json)) {
			ResultObjMapList<Address> result = JsonUtil.fromJson(json, new TypeToken<ResultObjMapList<Address>>() {
			});
			return result;
		}
		return null;
	}

}
