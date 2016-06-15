package com.shangpin.wireless.api.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.businessbean.CityBean;
import com.shangpin.wireless.api.domain.Constants;

/**
 * 关于地址的工具及缓存类
 * @author wenguan
 *
 */
public class AddressUtil {
	
	private static ArrayList<CityBean> ProvinceList = new ArrayList<CityBean>();
	private static HashMap<String, ArrayList<CityBean>> CityList = new HashMap<String, ArrayList<CityBean>>();
	private static HashMap<String, ArrayList<CityBean>> AreaList = new HashMap<String, ArrayList<CityBean>>();
	
	public static ArrayList<CityBean> getProvinceList () {
		if (ProvinceList.size() == 0) {
			HashMap<String, String> map = new HashMap<String, String>();
			String url = Constants.BASE_URL + "provincelist/";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				JSONArray array = obj.getJSONArray("content");
				for (int i=0;i<array.size();i++) {
					JSONObject ele = array.getJSONObject(i);
					CityBean bean = new CityBean();
					bean.json2Obj(ele);
					ProvinceList.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ProvinceList;
	}
	
	public static ArrayList<CityBean> getCityList (String provinceid) {
		if (!StringUtil.isNotEmpty(provinceid)) {
			return null;
		}
		ArrayList<CityBean> citylist = CityList.get(provinceid);
		if (citylist == null || citylist.size() == 0) {
			if (citylist == null) citylist = new ArrayList<CityBean>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", provinceid);
			String url = Constants.BASE_URL + "citylist/";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				JSONArray array = obj.getJSONArray("content");
				for (int i=0;i<array.size();i++) {
					JSONObject ele = array.getJSONObject(i);
					CityBean bean = new CityBean();
					bean.json2Obj(ele);
					citylist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CityList.put(provinceid, citylist);
		}
		
		return citylist;
	}
	
	public static ArrayList<CityBean> getAreaList (String cityid) {
		if (!StringUtil.isNotEmpty(cityid)) {
			return null;
		}
		ArrayList<CityBean> arealist = AreaList.get(cityid);
		if (arealist == null || arealist.size() == 0) {
			if (arealist == null) arealist = new ArrayList<CityBean>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", cityid);
			String url = Constants.BASE_URL + "arealist/";
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				JSONArray array = obj.getJSONArray("content");
				for (int i=0;i<array.size();i++) {
					JSONObject ele = array.getJSONObject(i);
					CityBean bean = new CityBean();
					bean.json2Obj(ele);
					arealist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			AreaList.put(cityid, arealist);
		}
		
		
		return arealist;
	}
	
	public static CityBean findBean (String testname, String parentid, int type) {
		ArrayList<CityBean> list = null;
		switch (type) {
		case CityBean.TYPE_PROVINCE:{
			list = getProvinceList();
		}break;
		case CityBean.TYPE_CITY:{
			list = getCityList(parentid);
		}break;
		case CityBean.TYPE_AREA:{
			list = getAreaList(parentid);
		}break;
		}
		
		if (list == null || !StringUtil.isNotEmpty(testname)) {
			return null;
		}
		
		for (int i=0;i<list.size();i++) {
			final CityBean bean = list.get(i);
			
			final String name = bean.getName();
			if (testname.equals(name)
					|| testname.startsWith(name)) {
				return bean;
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
