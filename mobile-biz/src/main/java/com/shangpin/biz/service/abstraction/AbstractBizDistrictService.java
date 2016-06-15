package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.vo.ResultObjList;
import com.shangpin.biz.bo.Region;
import com.shangpin.biz.bo.TownReturn;
import com.shangpin.biz.bo.base.CommonObj;
import com.shangpin.utils.JsonUtil;

/**
 * 四级地址行政区域（尚品、奥莱相同）
 * 
 * @author cuibinqiang
 *
 */
public abstract class AbstractBizDistrictService {

	public static final Logger logger = LoggerFactory.getLogger(AbstractBizDistrictService.class);

	@Autowired
	CommonService commonService;

	/**
	 * 获取省级列表
	 * 
	 * @return
	 * @author cuibinqiang
	 */
	public String provinceList() {
		String json = commonService.findProvinceList();
		// 将主站返回的json重构拼装处理
		String jsonNew = jsonRefactor(json, "province");

		return jsonNew;
	}

	/**
	 * 获取市级列表
	 * 
	 * @return
	 * @author cuibinqiang
	 */
	public String cityList(String id) {
		String json = commonService.findCityList(id);
		// 将主站返回的json重构拼装处理
		String jsonNew = jsonRefactor(json, "city");

		return jsonNew;
	}

	/**
	 * 获取区级列表
	 * 
	 * @return
	 * @author cuibinqiang
	 */
	public String areaList(String id) {
		String json = commonService.findAreaList(id);
		// 将主站返回的json重构拼装处理
		String jsonNew = jsonRefactor(json, "area");

		return jsonNew;
	}

	/**
	 * 获取县级列表
	 * 
	 * @return
	 * @author cuibinqiang
	 */
	public String townList(String id) {
		String json = commonService.findTownList(id);
		// 将主站返回的json重构拼装处理
		String jsonNew = jsonRefactor(json, "town");

		return jsonNew;
	}

	/**
	 * 省市区通用处理json方法
	 * 
	 * @param json
	 *            主站返回的JSON
	 * @param flag
	 *            标记
	 * @return
	 * @author cuibinqiang
	 */
	public static String jsonRefactor(String json, String flag) {
		CommonObj<Map<String, List<Region>>> newObj = new CommonObj<Map<String, List<Region>>>();
		if ("town".equals(flag)) {// 县镇行政区
			ResultObjList<TownReturn> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjList<TownReturn>>() {
			});
			List<TownReturn> townList = obj.getList();
			List<Region> townNewList = new ArrayList<Region>();
			if (townList != null && townList.size() > 0) {
				for (TownReturn town : townList) {
					Region newTown = new Region();
					newTown.setId(town.getId());
					newTown.setParentid(town.getAreaId());
					newTown.setName(town.getName());
					townNewList.add(newTown);
				}
			}
			newObj.setCode(obj.getCode());
			newObj.setMsg(obj.getMsg());
			Map<String, List<Region>> map = new HashMap<String, List<Region>>();
			map.put(flag, townNewList);
			newObj.setContent(map);
		} else {// 省市区行政区
			ResultObjList<Region> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjList<Region>>() {
			});
			List<Region> objList = obj.getList();
			newObj.setCode(obj.getCode());
			newObj.setMsg(obj.getMsg());
			Map<String, List<Region>> map = new HashMap<String, List<Region>>();
			map.put(flag, objList);
			newObj.setContent(map);
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			String json1 = mapper.writeValueAsString(newObj);
			return json1;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 测试代码
	public static void main(String[] args) {
		String json = "{\"code\":\"0\",\"msg\":\"\",\"content\":[{\"id\":2,\"parentid\":1,\"name\":\"内环到二环里\"},{\"id\":44225,\"parentid\":1,\"name\":\"二环到三环\"}]}";
		String json1 = "{\"code\":\"0\",\"msg\":\"\",\"content\":[{\"id\":2,\"areaId\":1,\"name\":\"内环到二环里\"},{\"id\":44225,\"areaId\":1,\"name\":\"二环到三环\"}]}";
		jsonRefactor(json1, "town");
		System.out.println(json);
	}

}
