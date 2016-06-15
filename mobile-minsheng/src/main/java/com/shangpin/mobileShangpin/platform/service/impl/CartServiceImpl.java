package com.shangpin.mobileShangpin.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;

import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.CartService;
import com.shangpin.mobileShangpin.platform.vo.MerchandiseVO;

/**
 * 购物袋业务逻辑接口实现类
 * 
 * @author yumeng
 * @date:2012-11-5
 */
@Service("cartService")
@SuppressWarnings({ "unchecked" })
public class CartServiceImpl implements CartService {
	@Override
	public String addcart(Map<String, String> map) throws Exception {
		String url = Constants.BASE_URL + "AddProductToShopping/";
		String data = WebUtil.readContentFromGet(url, map);
		return data;
	}

	@Override
	public List<MerchandiseVO> getCartOfMerchandiseList(String userid, String shoptype) {
		List<MerchandiseVO> list = null;
		String url = Constants.BASE_URL + "SelectShoppingCartList/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		map.put("detailpich", Constants.MERCHANDISE_DETAIL_PICTURE_HEIGHT);
		map.put("detailpicw", Constants.MERCHANDISE_DETAIL_PICTURE_WIDTH);
		map.put("shoptype", shoptype);
		String json = null;
		try {
			// 获取购物袋中商品json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code")) && !"[]".equals(jsonObj.get("content").toString())) {
				JSONObject contentObj = (JSONObject) jsonObj.get("content");
				if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					list = JSONArray.toList((JSONArray) contentObj.get("list"), new MerchandiseVO(), new JsonConfig());
				}
			}
		}
		return list;
	}

	
	@Override
	public String removeCartOfMerchandise(String userid, String skuid,String gid, String groupno, String shoppingcartdetailid, String count,String shoptype,String flag) {
		String url = Constants.BASE_URL + "delshoppingcartdetail/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("skuid", skuid);
		map.put("gid", gid);
		map.put("groupno", groupno);
		map.put("shoppingcartdetailid", shoppingcartdetailid);
		map.put("quantity", count);
		map.put("shoptype",shoptype);
		map.put("flag",flag);
		map.put("pich", Constants.SAMLL_PICTURE_HEIGHT);
		map.put("picw", Constants.SAMLL_PICTURE_WIDTH);
		map.put("detailpich", Constants.MERCHANDISE_DETAIL_PICTURE_HEIGHT);
		map.put("detailpicw", Constants.MERCHANDISE_DETAIL_PICTURE_WIDTH);
		String json = null;
		try {
			// 获取活动json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj) {
				return jsonObj.get("code").toString();
			}
		}
		return null;
	}
}
