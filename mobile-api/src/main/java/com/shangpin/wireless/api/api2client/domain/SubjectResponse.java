package com.shangpin.wireless.api.api2client.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.MathExtend;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 活动列表
 * 
 * @author xupengcheng
 * 
 */
public class SubjectResponse extends CommonAPIResponse {
	private String userId;
	private String subjectNo;

	@Override
	public String obj2Json(String data) {
		JSONObject result = new JSONObject();
		JSONObject obj = JSONObject.fromObject(data);
		if (obj != null) {
			try {
				String code = obj.getString("code");
				String msg = obj.getString("msg");
				JSONObject content = obj.getJSONObject("content");// content对象
				result.put("code", code);
				result.put("msg", msg);
				content.put("sysTime", new Date().getTime());
				content.put("startTime", content.getString("starttime"));
				content.put("endTime", content.getString("endtime"));
				content.put("activePromotionDesc", content.getString("salesinfo"));
				content.put("shareUrl", content.getString("shareurl"));
				content.put("isCollect", checkCollected(subjectNo));
				content.remove("starttime");
				content.remove("endtime");
				content.remove("salesinfo");
				content.remove("systime");
				content.remove("shareurl");
				JSONArray list = content.getJSONArray("list");
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						JSONObject target = list.getJSONObject(i);
						String now = target.getString("now");
						now = MathExtend.multiply(now, "10");
						String past = target.getString("past");
						target.put("subjectNo", subjectNo == null ? "" : subjectNo);
						String rebate = "";
						try{
							rebate = MathExtend.divide(now, past, 1) + "折";
						}catch(Exception e){
							rebate = "0折";
						}
						target.put("rebate", rebate);
						target.put("productNo", target.getString("goodsid"));
						target.put("categoryNo", target.getString("categoryno"));
						target.put("productName", target.getString("productname"));
						target.put("brandName", target.getString("brand"));
						target.remove("goodsid");
						target.remove("categoryno");
						target.remove("productname");
						target.remove("brand");

					}
				}
				result.put("content", content);
			} catch (Exception e) {
				result.put("code", "1");
				result.put("msg", "数据异常");
				result.put("content", new JSONObject());
			}
		} else {
			result.put("code", "1");
			result.put("msg", "数据异常");
			result.put("content", new JSONObject());
		}
		return result.toString();
	}

	/**
	 * 验证活动是否收藏过
	 * 
	 * @param subjectNo
	 * @return
	 */
	private String checkCollected(String subjectNo) {
		if (subjectNo == null || "".equals(subjectNo) || userId == null || "".equals(userId)) {
			return "0";
		}
		String flag = "0";
		String url = Constants.BASE_URL + "queryCollection";
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userId);
		map.put("type", "0");
		map.put("pich", "111");
		map.put("picw", "222");
		String data = "";
		try {
			data = WebUtil.readContentFromGet(url, map);
			if (data != null) {
				JSONObject dataObj = JSONObject.fromObject(data);
				if (dataObj != null) {
					Object obj = dataObj.get("content");
					if (obj != null && obj instanceof JSONArray) {
						JSONArray list = (JSONArray) obj;
						if (list.size() > 0) {
							for (int i = 0; i < list.size(); i++) {
								JSONObject temp = list.getJSONObject(i);
								String subjectNO = temp.getString("activityid");
								if (subjectNO != null && !"".equals(subjectNO) && subjectNo.equals(subjectNO)) {
									flag = "1";
									break;
								}
							}
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

}
