package com.shangpin.mobileAolai.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.MerchandiseService;
import com.shangpin.mobileAolai.platform.vo.ActivityVO;
import com.shangpin.mobileAolai.platform.vo.MerchandiseVO;

@Service("merchandiseService")
@SuppressWarnings("unchecked")
//@Transactional
public class MerchandiseServiceImpl implements MerchandiseService {
	@Autowired
	private AoLaiService aoLaiService; 
	/**
	 * 商品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-01
	 * @param activityId
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return ActivityVO 活动传输对象
	 */
	public ActivityVO activityVO (String activityId, String typeFlag, Integer pageIndex, Integer pageSize) throws Exception {
		ActivityVO activityVO = new ActivityVO();
		List<MerchandiseVO> merchandiseList = new ArrayList<MerchandiseVO>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("picurlw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("picurlh", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pagesize", pageSize == null ? "" : pageSize + "");
		String url = "";
		
		String data = null;
		try {
			if ("1".equals(typeFlag)) {
				map.put("subjectNo", activityId);// 20120810340
				data = aoLaiService.findSubjectProductList(map.get("subjectNo"), map.get("picurlw"), map.get("picurlh"), map.get("pageindex"), map.get("pagesize"));
			} else if ("0".equals(typeFlag)) {
				url = Constants.BASE_URL + "GetTopic/";
				map.put("topicNo", activityId);// 20120810340
				// 获取活动json格式字符串
				data = WebUtil.readContentFromGet(url, map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				JSONArray array = obj.getJSONArray("list");
				activityVO.setName(obj.getString("name"));
				if (array.size() > 0) {
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					merchandiseList = JSONArray.toList(array, new MerchandiseVO(), new JsonConfig());
				}
				if ("1".equals(typeFlag)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startTime = obj.getString("starttime");
					activityVO.setStartTime(sdf.format(new Date(Long.valueOf(startTime))));
					String endTime = obj.getString("endtime");
					activityVO.setEndTime(sdf.format(new Date(Long.valueOf(endTime))));
					// 1:开启；0:未开启；3:结束
					if(System.currentTimeMillis() >= Long.valueOf(endTime)){
						activityVO.setOpenFlag("3");
					} else if (System.currentTimeMillis() >= Long.valueOf(startTime)) {
						activityVO.setOpenFlag("1");
					} else {
						activityVO.setOpenFlag("0");
					}
				} else if ("0".equals(typeFlag)) {
					activityVO.setOpenFlag("1");
				}
			}
		}
		activityVO.setMerchandiseList(merchandiseList);
		return activityVO;
	}

	/**
	 * 进入商品详情页
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-06
	 * @param goodsid
	 *            商品id
	 * @param typeFlag
	 *            代表来自专题还是活动
	 * @param categoryno
	 *            商品所在活动或者专题的id
	 * @Return List 商品集合
	 */
	public void merchandiseDetail(String goodsid, String typeFlag, String categoryno) throws Exception {
		String data = aoLaiService.findProductDetail(goodsid,typeFlag,categoryno,Constants.MERCHANDISE_DETAIL_PICTURE_WIDTH,Constants.MERCHANDISE_DETAIL_PICTURE_HEIGHT);
		MerchandiseVO merchandise = new MerchandiseVO();
		merchandise.json2Bean(data);
		ActionContext.getContext().getValueStack().set("merchandise", merchandise);
	}
}
