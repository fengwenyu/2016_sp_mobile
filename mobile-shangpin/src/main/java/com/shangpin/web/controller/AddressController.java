package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.biz.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.IDCard;
import com.shangpin.web.utils.Constants;

/**
 * @ClassName: AddressController
 * @Description: 地址管理
 * @author qinyingchun
 * @date 2014年11月8日
 * @version 1.0
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

	private static final String ADDRESS_LIST = "address/list";
	private static final String ADDRESS_ADD = "address/add";
	private static final String ADDRESS_UPDATE = "address/update";

	@Autowired
	private SPBizAddressService bizAddressService;

	/**
	 * 
	 * @Title: list
	 * @Description:收货地址列表页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月8日
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		User user = getSessionUser(request);
		List<Address> addresses = null;
		if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getUserid())) {
			addresses = bizAddressService.findAddressObj(user.getUserid());
		}
		// 解密身份证号码
		decryptCardID(addresses);
		model.addAttribute("addresses", addresses);
		return ADDRESS_LIST;
	}

	/**
	 * 
	 * @Title: add
	 * @Description:跳转到新增收货地址页
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月8日
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(String msg, Model model) {
		List<Province> provinces = bizAddressService.findProvinceListObj();
		model.addAttribute("provinces", provinces);
		model.addAttribute("msg", msg);
		return ADDRESS_ADD;
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 保存地址
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月8日
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Address address, HttpServletRequest request, ModelMap map) {
		String cardID = address.getCardID();
		if ((!StringUtils.isEmpty(cardID)) && !IDCard.checkIDCard(cardID)) {
			map.put("msg", "身份证号码不正确！");
			return "redirect:/address/add";
		}
		String userId = getUserId(request);
		ResultObjMapList<Address> obj = bizAddressService.addAddrObj(address, userId);
		if (obj != null && obj.isSuccess()) {
			map.put("msg", "添加收货地址成功！");
			return "redirect:/address/list";
		} else {
			map.put("msg", obj.getMsg());
			return "redirect:/address/add";
		}
	}

	/**
	 * 
	 * @Title: orderAddress
	 * @Description: 提交订单页添加收货地址
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月14日
	 */
	@RequestMapping(value = "/order/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> orderAddress(Address address, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = getSessionUser(request);
		if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getUserid())) {
			List<Address> addresses = bizAddressService.addAddr(address, user.getUserid());
			if (null != addresses) {
				map.put("code", Constants.SUCCESS);
				map.put("addresses", addresses);
			} else {
				map.put("code", Constants.FAILE);
			}
		}
		return map;
	}



	/**
	 * 
	 * @Title: city
	 * @Description: 获取省下面的市
	 * @param
	 * @return List<City>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月8日
	 */
	@RequestMapping(value = "/city", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public List<City> city(String proviceId) {
		List<City> cities = bizAddressService.findCityListObj(proviceId);
		return cities;
	}
	
	/**
	 * 
	 * @Title: area
	 * @Description:获取市下面的区
	 * @param
	 * @return List<Area>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月8日
	 */
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	@ResponseBody
	public List<Area> area(String cityId) {
		List<Area> areas = bizAddressService.findAreaListObj(cityId);
		return areas;
	}

	@RequestMapping(value = "/town", method = RequestMethod.POST)
	@ResponseBody
	public List<Town> town(String areaId) {
		List<Town> towns = bizAddressService.findTownList(areaId);
		return towns;
	}

	/**
	 * 
	 * @Title: update
	 * @Description:跳转到修改页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月10日
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(String addressId, String msg, Model model, HttpServletRequest request) {
		String userId = getUserId(request);
		Address address = null;
		List<Province> provinces = null;
		if (isUser(request)) {
			address = bizAddressService.findAddressByIdObj(userId, addressId);
			provinces = bizAddressService.findProvinceListObj();
			for (Province province : provinces) {
				if (province.getId().equals(address.getProvince())) {
					address.setProvname(province.getName());
					break;
				}
			}
			for (City city : bizAddressService.findCityListObj(address.getProvince())) {
				if (city.getId().equals(address.getCity())) {
					address.setCityname(city.getName());
					break;
				}
			}
			for (Area area : bizAddressService.findAreaListObj(address.getCity())) {
				if (area.getId().equals(address.getArea())) {
					address.setAreaname(area.getName());
					break;
				}

			}
			for (Town town : bizAddressService.findTownList(address.getArea())) {
				if (town.getId().equals(address.getTown())) {
					address.setTownname(town.getName());
					break;
				}
			}
		}
		decryptCardID(address);
		model.addAttribute("msg", msg);
		model.addAttribute("address", address);
		model.addAttribute("provinces", provinces);
		return ADDRESS_UPDATE;
	}

	/**
	 * 
	 * @Title: update
	 * @Description:地址编辑完成
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月10日
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Address address, HttpServletRequest request, ModelMap map) {
		String cardID = address.getCardID();
		if ((!StringUtils.isEmpty(cardID)) && !IDCard.checkIDCard(cardID)) {
			map.put("msg", "身份证号码不正确！");
			return "redirect:/address/update?addressId=" + address.getId();
		}
		String userId = getUserId(request);
		ResultObjMapList<Address> obj = bizAddressService.updateAddrObj(address, userId);
		if (obj != null && obj.isSuccess()) {
			map.put("msg", "编辑收货地址成功！");
			return "redirect:/address/list";
		} else {
			map.put("msg", obj.getMsg());
			return "redirect:/address/update?addressId=" + address.getId();
		}
	}

	/**
	 * 
	 * @Title: del
	 * @Description:删除收货地址
	 * @param
	 * @return String
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月10日
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	@ResponseBody
	public String del(String addressId, HttpServletRequest request) {
		if (isUser(request)) {
			String userId = getUserId(request);
			boolean flag = bizAddressService.isDeleteAddress(addressId, userId);
			if (flag) {
				return Constants.SUCCESS;
			} else {
				return Constants.FAILE;
			}
		}
		return Constants.FAILE;
	}

	/**
	 * ajax添加地址
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/add", method = RequestMethod.POST)
	@ResponseBody
	public ContentBuilder<Object> ajaxAddAddress(Address address, HttpServletRequest request) {
		ContentBuilder<Object> cb = new ContentBuilder<Object>();
		String cardID = address.getCardID();
		String userId = getUserId(request);
		if ((!StringUtils.isEmpty(cardID)) && !IDCard.checkIDCard(cardID)) {
			return cb.buildDefError("身份证号码不正确！", null);
		}
		ResultObjMapList<Address> obj = bizAddressService.addAddrObj(address, userId);
		if (obj != null && obj.isSuccess()) {
			List<Address> list = obj.getList("list");
			decryptCardID(list);
			if (list != null) {
				return cb.buildDefSuccess(list);
			}
		}

		return cb.buildDefError(obj.getMsg(), null);
	}

	/**
	 * ajax请求更新地址的信息
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/update", method = RequestMethod.GET)
	@ResponseBody
	public Address ajaxUpdate(String addressId, HttpServletRequest request) {
		String userId = getUserId(request);
		Address address = bizAddressService.findAddressByListObj(userId, addressId);

		decryptCardID(address);
		return address;
	}

	/**
	 * ajax编辑地址
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/edit", method = RequestMethod.POST)
	@ResponseBody
	public ContentBuilder<Object> ajaxEditAddress(Address address, HttpServletRequest request) {
		ContentBuilder<Object> cb = new ContentBuilder<Object>();
		String cardID = address.getCardID();
		String userId = getUserId(request);
		if ((!StringUtils.isEmpty(cardID)) && !IDCard.checkIDCard(cardID)) {
			return cb.buildDefError("身份证号码不正确！", null);
		}
		ResultObjMapList<Address> obj = bizAddressService.updateAddrObj(address, userId);
		if (obj != null && obj.isSuccess()) {
			List<Address> list = obj.getList("list");
			if (list != null) {
				decryptCardID(list);
				return cb.buildDefSuccess(list);
			}
		}
		return cb.buildDefError(obj.getMsg(), null);
	}

	/**
	 * ajax请求是否存在身份证号码
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/hasCardID", method = RequestMethod.GET)
	@ResponseBody
	public String ajaxHasCardID(String addressId, HttpServletRequest request) {
		String userId = getUserId(request);
		Address address = bizAddressService.findAddressByListObj(userId, addressId);
		if (address != null && !StringUtils.isEmpty(address.getCardID())) {
			return "true";
		}
		return "false";
	}
	/**
	 * ajax请求是否存在身份证号码
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/ajax/addCardID", method = RequestMethod.GET)
	@ResponseBody
	public ContentBuilder<Object> ajaxAddCardID(String addressId, String cardID, HttpServletRequest request) {
		String userId = getUserId(request);
		Address address = bizAddressService.findAddressByListObj(userId, addressId);
		address.setCardID(cardID);
		ContentBuilder<Object> cb = ajaxEditAddress(address, request);
		return cb;
	}

	/**
	 * 解密身份证号码
	 * 
	 * @param addresses
	 * @author zghw
	 */
	private void decryptCardID(Address address) {
		try {
			if (!StringUtils.isEmpty(address)) {
				String cardID = address.getCardID();
				if (!StringUtils.isEmpty(cardID)) { // 身份证号解密
					String originalCardID = AESUtil.decrypt(cardID, AESUtil.IDCARD_KEY);
					address.setCardID(originalCardID);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解密身份证号码
	 * 
	 * @param addresses
	 * @author zghw
	 */
	private void decryptCardID(List<Address> addresses) {
		try {
			if (addresses != null) {
				for (Address address : addresses) {
					decryptCardID(address);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
