package com.shangpin.mobileAolai.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;




import com.shangpin.mobileAolai.common.page.PageContext;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.CouponService;
import com.shangpin.mobileAolai.platform.vo.CouponVO;
import com.shangpin.mobileAolai.platform.vo.OrderVO;

/**
 * 优惠券业务逻辑接口实现类，用于订单相关操作
 * 
 * @author yumeng
 */
@Service("couponService")
// @Transactional
@SuppressWarnings({ "unchecked" })
public class CouponServiceImpl implements CouponService {
	// 分页每页默认获取10条记录
	private final String COUPON_PAGE_SIZE = "10";

	@Override
	public JSONObject getCouponJson(String userid, String coupontype, String shoptype) {
		String url = Constants.BASE_URL_AL_SP + "coupons/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("coupontype", coupontype);
		map.put("pageindex", String.valueOf(PageContext.getOffset() + 1));
		map.put("pagesize", COUPON_PAGE_SIZE);
		map.put("shoptype", shoptype);
		String json = null;
		try {
			// 获取订单json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		JSONObject res = null;
		if (null != json && !"".equals(json)) {
			res = JSONObject.fromObject(json);
			if (null != res && Constants.SUCCESS.equals(res.get("code"))) {
				if (null != (JSONObject) res.get("content") && !"[]".equals(((JSONObject) res.get("content")).getString("list"))) {
					JSONObject contentJson = (JSONObject) res.get("content");
					List<OrderVO> list = JSONArray.toList((JSONArray) contentJson.get("list"), new OrderVO(), new JsonConfig());
					String haveMore = "1";
					if (list.size() < Integer.valueOf(COUPON_PAGE_SIZE)) {
						haveMore = "0";
					}
					res.put("haveMore", haveMore);// 0表示没有更多数据；1表示有更多
					res.put("pageindex", String.valueOf(PageContext.getOffset() + 1));
					return res;
				}
			}
		}
		if (null == res) {
			res = new JSONObject();
			res.put("code", "-1");
			res.put("msg", "数据错误，请重试");
		}
		return res;
	}

	@Override
	public List<CouponVO> getCouponList(String userid, String shoptype) {
		List<CouponVO> list = null;
		String url = Constants.BASE_URL_AL_SP + "selectCoupon/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("pageindex", "1");
		map.put("pagesize", "1000");
		map.put("shoptype", shoptype);
		String json = null;
		try {
			// 获取订单json格式字符串
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		try {
			if (null != json && !"".equals(json)) {
				JSONObject jsonObj = JSONObject.fromObject(json);
				if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
					if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
						JSONObject contentObj = (JSONObject) jsonObj.get("content");
						if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
							JSONArray array = (JSONArray) contentObj.get("list");
							list = JSONArray.toList(array, new CouponVO(), new JsonConfig());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	public void sendCoupon(String userid, String shoptype, String type) {
		String url = Constants.BASE_URL + "codemactchedphone/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("shoptype", shoptype);
		map.put("type", type);
		try {
			WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject sendCoupon(String userid, String shoptype, String type,String coupontype) {
		String url = Constants.BASE_URL + "codemactchedphone/";
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("shoptype", shoptype);
		map.put("type", type);
		map.put("coupontype", coupontype);
		String json = null;
		try {
			json=WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		JSONObject jsonObj = null;
		try {
			if (null != json && !"".equals(json)) {
				jsonObj  = JSONObject.fromObject(json);
			}
		} catch (Exception e) {
			//  Auto-generated catch block
			e.printStackTrace();
			jsonObj = null;
		}
		return jsonObj;
	}

	@Override
	public JSONObject sendCouponCode(String userid, String shoptype, String type) {
			JSONObject jsonObj=null;
			String url = Constants.BASE_URL + "codemactchedphone/";
			// 组装参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("shoptype", shoptype);
			map.put("type", type);
			String json = null;
			try {
				json=WebUtil.readContentFromGet(url, map);
			} catch (Exception e) {
				e.printStackTrace();
				json = null;
			}
			try {
				if (null != json && !"".equals(json)) {
					jsonObj = JSONObject.fromObject(json);
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonObj = null;
			}
			return jsonObj;
	}
	
}