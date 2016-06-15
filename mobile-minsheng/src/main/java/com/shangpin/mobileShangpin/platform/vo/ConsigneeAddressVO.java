package com.shangpin.mobileShangpin.platform.vo;

import net.sf.json.JSONObject;

/**
 * 收货人地址数据传输对象，用于前台展示
 * 
 * @Author yumeng
 * @CreateDate 2012-10-31
 */
public class ConsigneeAddressVO {
	/** 收货人地址ID */
	private String id;
	/** 收货人姓名 */
	private String name;
	/** 省 */
	private String province;
	private String provname;
	/** 市 */
	private String city;
	private String cityname;
	/** 区 */
	private String area;
	private String areaname;
	/** 乡镇*/
    private String town;
    private String townname;
	/** 详细地址 */
	private String addr;
	/** 邮编 */
	private String postcode;
	/** 联系电话 */
	private String tel;
	/** 是否支持货到付款；0不支持 */
	private String cod;
	/** 默认地址；默认为1 */
	private String isd;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public String getIsd() {
		return isd;
	}

	public void setIsd(String isd) {
		this.isd = isd;
	}

	public String getProvname() {
		return provname;
	}

	public void setProvname(String provname) {
		this.provname = provname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTownname() {
        return townname;
    }

    public void setTownname(String townname) {
        this.townname = townname;
    }

    /**
	 * 将json对象转化为OrderVO对象
	 * 
	 * @param json json对象
	 * 
	 * @return OrderVO对象
	 */
	public static ConsigneeAddressVO json2Bean(JSONObject json) {
		ConsigneeAddressVO consigneeAddressVO = new ConsigneeAddressVO();
		consigneeAddressVO.setId(json.getString("id"));
		consigneeAddressVO.setName(json.getString("name"));
		consigneeAddressVO.setAddr(json.getString("addr"));
		consigneeAddressVO.setProvince(json.getString("province"));
		consigneeAddressVO.setCity(json.getString("city"));
		consigneeAddressVO.setArea(json.getString("area"));
		consigneeAddressVO.setTown(json.getString("town"));
//		consigneeAddressVO.setCod(json.getString("cod"));
		consigneeAddressVO.setIsd(json.getString("isd"));
		consigneeAddressVO.setPostcode(json.getString("postcode"));
		consigneeAddressVO.setTel(json.getString("tel"));
		return consigneeAddressVO;
	}
}
