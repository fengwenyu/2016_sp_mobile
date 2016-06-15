package com.shangpin.biz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.abstraction.AbstractBizAddressService;
import com.shangpin.utils.AESUtil;

@Service
public class SPBizAddressServiceImpl extends AbstractBizAddressService implements SPBizAddressService {

	private static final Logger logger = LoggerFactory.getLogger(SPBizAddressServiceImpl.class);

	@Override
	public List<Address> findAddressObj(String userId) {
		try {
			ResultObjMapList<Address> obj = beAddress(userId);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getList("list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Province> findProvinceListObj() {
		try {
			ResultObjList<Province> obj = beProvince();
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Address findAddressByIdObj(String userId, String addrId) {
		try {
			ResultObjOne<Address> obj = beAddressById(userId, addrId);

			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getObj();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<City> findCityListObj(String provinceId) {
		try {
			ResultObjList<City> obj = beCity(provinceId);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Area> findAreaListObj(String cityId) {
		try {
			ResultObjList<Area> obj = beArea(cityId);
			if (!StringUtils.isEmpty(obj) && obj.isSuccess()) {
				return obj.getList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isAddAddress(Address address, String userId) {
		try {
			ConsigneeAddress ca = putContainer(address, userId);
			ResultBase obj = beAddAddress(ca);
			if (!StringUtils.isEmpty(obj)) {
				return obj.isSuccess();
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * 提交订单：新增收货地址
	 */
	@Override
	public List<Address> addAddr(Address address, String userId) {
		try {
			ConsigneeAddress ca = putContainer(address, userId);
			ResultObjMapList<Address> obj = beAddAddress(ca);
			if (!StringUtils.isEmpty(obj)) {
				return obj.getList("list");
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 提交订单：新增收货地址
	 */
	@Override
	public ResultObjMapList<Address> addAddrObj(Address address, String userId) {
		try {
			ConsigneeAddress ca = putContainer(address, userId);
			ResultObjMapList<Address> obj = beAddAddress(ca);
			return obj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean isModifyAddress(Address address, String userId) {

		try {
			ConsigneeAddress ca = putContainer(address, userId);
			ResultBase obj = beModifyAddress(ca);
			if (!StringUtils.isEmpty(obj)) {
				return obj.isSuccess();
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean isDeleteAddress(String addrId, String userId) {
		try {
			ResultBase obj = beDeleteAddress(userId, addrId);
			if (!StringUtils.isEmpty(obj)) {
				return obj.isSuccess();
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * 设置参数 装箱
	 * 
	 * @param address
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private ConsigneeAddress putContainer(Address address, String userId) throws Exception {
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
		// ca.setCardID(address.getCardID());
		if (!StringUtils.isEmpty(address.getCardID())) { // 身份证号加密
			ca.setCardID(AESUtil.encrypt(address.getCardID(), AESUtil.IDCARD_KEY));
		}

		ca.setSetd(null != address.getIsd() && "on".equals(address.getIsd()) ? "true" : "false");
		return ca;
	}

	@Override
	public List<Town> findTownList(String areaId) {
		try {
			ResultObjList<Town> obj = beTown(areaId);
			if (!StringUtils.isEmpty(obj)) {
				return obj.getList();
			}
		} catch (Exception e) {
			logger.error("调用base接口获取街道信息数据错误！");
		}
		return null;
	}

	@Override
	public Address findAddressByListObj(String userId, String addrId) {
		List<Address> list = findAddressObj(userId);
		if (list != null) {
			for (Address address : list) {
				if (addrId.equals(address.getId())) {
					return address;
				}
			}
		}
		return null;
	}

	@Override
	public List<Address> updateAddr(Address address, String userId) {
		try {
			ConsigneeAddress ca = putContainer(address, userId);
			ResultObjMapList<Address> obj = beModifyAddress(ca);
			if (!StringUtils.isEmpty(obj)) {
				return obj.getList("list");
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public ResultObjMapList<Address> updateAddrObj(Address address, String userId) {
		try {
			ConsigneeAddress ca = putContainer(address, userId);
			ResultObjMapList<Address> obj = beModifyAddress(ca);
			return obj;
		} catch (Exception e) {
			return null;
		}
	}
}
