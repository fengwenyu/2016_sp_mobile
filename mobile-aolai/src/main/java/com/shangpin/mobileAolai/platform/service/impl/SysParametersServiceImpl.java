package com.shangpin.mobileAolai.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.SysParametersService;
import com.shangpin.mobileAolai.platform.vo.ProvinceVO;

/**
 * 系统参数业务逻辑接口实现类，用于获取系统数据，如：省、市、区
 * 
 * @author yumeng
 * @date:2012-11-9
 */
@Service("sysParametersService")
public class SysParametersServiceImpl implements SysParametersService {

	@Override
	public List<ProvinceVO> getProvinceList() {
		return getList("provincelist/",null);
	}

	@Override
	public JSONObject getCityJson(String id) {
		return getJson("citylist/",id);
	}

	@Override
	public JSONObject getAreaJson(String id) {
		return getJson("arealist/",id);
	}
	@Override
	public JSONObject getTownJson(String id) {
		return getJson("townlist/",id);
	}
	@Override
	public List<ProvinceVO> getCityList(String id) {
		return getList("citylist/",id);
	}

	@Override
	public List<ProvinceVO> getAreaList(String id) {
		return getList("arealist/",id);
	}
	
	@Override
	public List<ProvinceVO> getTownList(String id) {
		return getList("townlist/",id);
	}
	
	/**
	 * 根据访问地址、和省市区父类ID，获取省市区Json数据
	 * 
	 * @param url 访问地址
	 * @param id 省市区父类ID
	 * 
	 * @return 省市区Json数据
	 */
	private JSONObject getJson(String url, String id) {
		JSONObject res = null;
		if(url.equals("townlist/")){
			url = Constants.SP_BASE_URL + url;
		}else{
			url = Constants.BASE_URL + url;
		}
		String json = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isEmpty(id))
			map.put("id", id);
		try {
			// 获取json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			res = JSONObject.fromObject(json);
		}
		return res;
	}

	/**
	 * 根据访问地址、和省市区父类ID，获取省市区数据列表
	 * 
	 * @param url 访问地址
	 * @param id 省市区父类ID
	 * 
	 * @return 省市区数据列表
	 */
	@SuppressWarnings("unchecked")
	private List<ProvinceVO> getList(String url, String id) {
		List<ProvinceVO> list = null;
		if(url.equals("townlist/")){
			url = Constants.SP_BASE_URL + url;
		}else{
			url = Constants.BASE_URL + url;
		}
		String json = null;
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtils.isEmpty(id))
			map.put("id", id);
		try {
			// 获取json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj
					&& Constants.SUCCESS.equals(jsonObj.get("code"))
					&& !"[]".equals(jsonObj.get("content").toString())) {
				JSONArray array = (JSONArray) jsonObj.get("content");
				// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
				list = JSONArray.toList(array, new ProvinceVO(),
						new JsonConfig());
			}
		}
		return list;
	}



	
}
