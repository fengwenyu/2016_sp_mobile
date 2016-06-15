package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class GetAddressServerResponse {
	private String code;
	private String msg;
	private int size;
	private Address[] list;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Address[] getList() {
		return list;
	}

	public void setList(Address[] list) {
		this.list = list;
	}

	/**
	 * 解析返回的json数据
	 * 
	 * @Author: wangwenguan
	 * @CreatDate: 2013-08-08
	 * @param jsonStr
	 *            返回的json数据
	 * @Return
	 */
	public GetAddressServerResponse json2Obj(String jsonStr) {
		JSONObject obj = JSONObject.fromObject(jsonStr);
		this.setCode(obj.getString("code"));
		this.setMsg(obj.getString("msg"));
		if (Constants.SUCCESS.equals(code)) {
			obj = JSONObject.fromObject(obj.getJSONObject("content"));
			JSONArray array = JSONArray.fromObject(obj.getJSONArray("list"));
			setSize(array.size());
			Address[] list = new Address[array.size()];
			for (int i=0;i<array.size();i++) {
				Address address = new Address();
				address.json2Obj(array.getJSONObject(i));
				list[i] = address;
			}
		}
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	static class Address {
		private String id;
		private String name;
		private String province;
		private String provname;
		private String city;
		private String cityname;
		private String area;
		private String areaname;
		private String address;
		private String postcode;
		private String isdefault;
		private String tel;
		private String cod;

		public Address json2Obj(JSONObject obj) {
			this.setId(obj.getString("id"));
			this.setName(obj.getString("name"));
			this.setProvince(obj.getString("province"));
			this.setProvname(obj.getString("provname"));
			this.setCity(obj.getString("city"));
			this.setCityname(obj.getString("cityname"));
			this.setArea(obj.getString("area"));
			this.setAreaname(obj.getString("areaname"));
//			this.setAddress(obj.getString("address"));
			this.setAddress(obj.getString("addr"));
			this.setPostcode(obj.getString("postcode"));
			this.setIsdefault(obj.getString("isd"));
			this.setTel(obj.getString("tel"));
			this.setCod(obj.getString("cod"));
			return this;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getProvname() {
			return provname;
		}
		public void setProvname(String provname) {
			this.provname = provname;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCityname() {
			return cityname;
		}
		public void setCityname(String cityname) {
			this.cityname = cityname;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getAreaname() {
			return areaname;
		}
		public void setAreaname(String areaname) {
			this.areaname = areaname;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getIsdefault() {
			return isdefault;
		}
		public void setIsdefault(String isdefault) {
			this.isdefault = isdefault;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getCod() {
			return cod;
		}
		public void setCod(String cod) {
			this.cod = cod;
		}
	}
}
