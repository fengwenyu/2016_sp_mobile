package com.shangpin.mobileShangpin.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Service;

import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DateUtil;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.PresaleService;
import com.shangpin.mobileShangpin.platform.vo.ActivitiesVO;
import com.shangpin.mobileShangpin.platform.vo.PresaleVO;

@Service("presaleService")
@SuppressWarnings("unchecked")
//@Transactional
public class PresaleServiceImpl implements PresaleService {
	/**
	 * 进入预售列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @Return
	 */
	@Override
	public List<ActivitiesVO> activityList(String startTime, String endTime) throws Exception {
		List<ActivitiesVO> list = new ArrayList<ActivitiesVO>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("starttime", startTime);
		map.put("endtime", endTime);
		map.put("picw", Constants.ACTIVITIES_PICTURE_WIDTH);
		map.put("pich", Constants.ACTIVITIES_PICTURE_HEIGHT);
		map.put("gender", "");
		String activityUrl = Constants.BASE_URL + "SubjectList/";
		String data = null;
		try {
			// 获取活动json格式字符串
			data = WebUtil.readContentFromGet(activityUrl, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"[]".equals(jsonObj.get("content").toString())) {
				JSONArray array = (JSONArray) jsonObj.get("content");
				// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
				list = JSONArray.toList(array.getJSONArray(0), new ActivitiesVO(), new JsonConfig());
			}
		}
		return list;
	}

	/**
	 * 日期列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-31
	 * @param date
	 *            当前时间
	 * @param num
	 *            天数
	 * @param startTime
	 *            起始时间
	 * @param endTime
	 *            结束时间
	 * @Return
	 */
	public List<PresaleVO> dateList(Date date, String id, String startTime, String endTime) throws Exception {
		List<PresaleVO> presaleList = new ArrayList<PresaleVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		int length = 6;
		for (int i = 1; i < length; i++) {
			PresaleVO presale = new PresaleVO();
			if (StringUtil.isNotEmpty(id, startTime, endTime)) {
				if ((String.valueOf(i).equals(id))) {
					presale.setCssFlag(1);// 控制CSS样式：选中状态
				} else {
					presale.setCssFlag(0);// 控制CSS样式：未选中状态
				}
			} else {
				// 默认选中明天
				if (i == 1)
					presale.setCssFlag(1);
				else
					presale.setCssFlag(0);
			}
			presale.setId(i + "");
			presale.setDate(sdf.format(DateUtil.getAfterAmountDay(date, i)));
			presale.setStartTime(sdf1.format(DateUtil.getAfterAmountDay(date, i)));
			presale.setEndTime(sdf1.format(DateUtil.getAfterAmountDay(date, i + 1)));
			presale.setWeekDay(DateUtil.dateToWeek(DateUtil.getAfterAmountDay(date, i)));
			presaleList.add(presale);
		}
		return presaleList;
	}
}
