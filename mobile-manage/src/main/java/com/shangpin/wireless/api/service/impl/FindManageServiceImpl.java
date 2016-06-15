package com.shangpin.wireless.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.FindManageService;
import com.shangpin.wireless.dao.FindManageDao;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.FindManage;
import com.shangpin.wireless.util.StringUtils;
import com.shangpin.wireless.util.WebUtil;
import com.shangpin.wireless.vo.FindManageVO;

@Service(FindManageService.SERVICE_NAME)
public class FindManageServiceImpl implements FindManageService {
	@Resource(name = FindManageDao.DAO_NAME)
	private FindManageDao findManageDao;

//	@Override
//	public FindManageVO findActivity(ShowPositionEnum showPosition) {
//		FindManageVO FindManageVO = new FindManageVO();
////		FindManageVO = FindManageVO.doToVo(findManageDao.findActivity(showPosition));
//		return FindManageVO;
//	}

//	@Override
//	public List<FindManageVO> findNotCurrentActivities(ShowPositionEnum showPosition) throws Exception {
//		List<FindManage> list = findManageDao.findNotCurrentActivities(showPosition);
//		List<FindManageVO> FindManageList = new ArrayList<FindManageVO>();
//		if (list.size() == 0) {
//			return null;
//		}
//		for (int i = 0; i < list.size(); i++) {
//			FindManageVO findManageVO = new FindManageVO();
//			findManageVO = FindManageVO.doToVo(list.get(i));
//			FindManageList.add(findManageVO);
//		}
//		return FindManageList;
//	}

	@Override
	public List<FindManageVO> subjectList(String startDate, String endDate, String keyWord) {
		Map<String, Object> res = new HashMap<String, Object>();
		List<FindManageVO> list = new ArrayList<FindManageVO>();
		// 组装参数
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotEmpty(keyWord)) {
			String url = Constants.BASE_URL_SP_SP + "searchSubject/";
			map.put("keyword", keyWord);
			String json = null;
			try {
				// 获取json格式字符串
				json = WebUtil.readContentFromGet(url, map);
			} catch (Exception e) {
				e.printStackTrace();
				json = null;
			}
			if (null != json && !"".equals(json)) {
				JSONObject jsonObj = JSONObject.fromObject(json);
				if (null != jsonObj && Constants.CODE_SUCCESS.equals(jsonObj.get("code"))) {
					// list = JSONArray.toList((JSONArray) jsonObj.get("content"), new FindManageVO(), new JsonConfig());
					JSONArray array = (JSONArray) jsonObj.get("content");
					list=GetFindManageVOList(array);
				}
			}
		} else if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate) && !startDate.equals("起始时间") && !endDate.equals("结束时间")) {
			String url = Constants.BASE_URL_SP_SP + "SubjectList/";
			map.put("starttime", startDate);
			map.put("endtime", endDate);
			String json = null;
			try {
				// 获取json格式字符串
				json = WebUtil.readContentFromGet(url, map);
			} catch (Exception e) {
				e.printStackTrace();
				json = null;
			}
			if (null != json && !"".equals(json)) {
				JSONObject jsonObj = JSONObject.fromObject(json);
				if (null != jsonObj && Constants.CODE_SUCCESS.equals(jsonObj.get("code"))) {
					JSONArray array = (JSONArray) jsonObj.get("content");
					list=GetFindManageVOList(array);
				}
			}
		} else {
			String url = Constants.BASE_URL_SP_SP + "SubjectList/";
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			map.put("starttime", sdf.format(new Date())+" 00:00:00");
			map.put("endtime", "");
			String json = null;
			try {
				// 获取json格式字符串
				json = WebUtil.readContentFromGet(url, map);
			} catch (Exception e) {
				e.printStackTrace();
				json = null;
			}
			if (null != json && !"".equals(json)) {
				JSONObject jsonObj = JSONObject.fromObject(json);
				if (null != jsonObj && Constants.CODE_SUCCESS.equals(jsonObj.get("code"))) {
					JSONArray array = (JSONArray) jsonObj.get("content");
					list=GetFindManageVOList(array);
				}
			}
		}
		return list;
	}

	@Override
	public void save(FindManage model) throws Exception {
		findManageDao.save(model);
	}

	@Override
	public Map<String, Object> isExist(Date showStartDate, Date showEndDate) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer res = new StringBuffer("SELECT * FROM findManage t WHERE (('");
		res.append(sdf.format(showStartDate));
		res.append("' between t.showStartDate and t.showEndDate) OR  ('");
		res.append(sdf.format(showEndDate));
		res.append("' between t.showStartDate and showEndDate) OR ('");
		res.append(sdf.format(showStartDate));
		res.append("' <= t.showStartDate and '");
		res.append(sdf.format(showEndDate));
		res.append("'>=t.showEndDate)) and t.type='");
		res.append("STATICATC");
		res.append("'");
		res.append("and t.display='");
		res.append("1");
		res.append("'");
		List recorList = findManageDao.executeSqlToMap(res.toString());
		if (recorList.size() <= 0) {
			map.put("isExist", "false");
		}else{
			map.put("isExist", "true");
		}
		return map;
	}
	@Override
	public Map<String, Object> isExist(Date showStartDate, Date showEndDate,Long id) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer res = new StringBuffer("SELECT * FROM findManage t where t.id!=");
		res.append(id);
		res.append(" and (('");
		res.append(sdf.format(showStartDate));
		res.append("' between t.showStartDate and t.showEndDate) OR  ('");
		res.append(sdf.format(showEndDate));
		res.append("' between t.showStartDate and showEndDate) OR ('");
		res.append(sdf.format(showStartDate));
		res.append("' <= t.showStartDate and '");
		res.append(sdf.format(showEndDate));
		res.append("'>=t.showEndDate)) and t.type='");
		res.append("STATICATC");
		res.append("'");
		res.append("and t.display='");
		res.append("1");
		res.append("'");
		List recorList = findManageDao.executeSqlToMap(res.toString());
		if (recorList.size() <= 0) {
			map.put("isExist", "false");
		}else{
			map.put("isExist", "true");
		}
		return map;
		
		
		
	}
	public List<FindManageVO> GetFindManageVOList(JSONArray array){
		List<FindManageVO> list = new ArrayList<FindManageVO>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject oj = (JSONObject) array.get(i);
			if (!oj.isEmpty()) {
				FindManageVO findManageVO = new FindManageVO();
				findManageVO.setActivityID(oj.get("id").toString());
				findManageVO.setName(oj.get("name").toString());
				findManageVO.setSubtitle(oj.get("subtitle").toString());
				findManageVO.setDesc(oj.get("desc").toString());
				findManageVO.setStarttime(oj.get("starttime").toString());
				findManageVO.setEndtime(oj.get("endtime").toString());
				findManageVO.setPretime(oj.get("pretime").toString());
				findManageVO.setIphonepic(oj.get("iphonepic").toString());
				findManageVO.setMobilepic(oj.get("mobilepic").toString());
				findManageVO.setShareurl(oj.get("shareurl").toString());
				findManageVO.setStatus(oj.get("status").toString());
				findManageVO.setTimequantum(new StringBuilder().append("活动开始时间：").append(oj.get("starttime")).append("<br/>").append("活动结束时间：").append(oj.get("endtime")).append("<br/>").toString());
				list.add(findManageVO);
			}
		}
		return list;
		
	}

	@Override
	public FindManage findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return findManageDao.getById(id);
	}

	@Override
	public void update(FindManage findManage) throws Exception {
		findManageDao.update(findManage);
		
	}

	@Override
	public void delete(Long id) throws Exception {
		findManageDao.delete(id);
		
	}

	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		// TODO Auto-generated method stub
		return findManageDao.executeSqlCount(queryListSql);
	}

	@Override
	public List<HashMap> executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		return findManageDao.executeSqlToMap(queryListSql, page, rows);
	}

	@Override
	public void updateTopPostion() {
	 findManageDao.updateTopPostion();
		
	}

	@Override
	public void sortRetrude(Long id) {
		findManageDao.sortRetrude(id);
		
	}

	@Override
	public String findMinSort(Long id) {
		// TODO Auto-generated method stub
		return findManageDao.findMinSort(id);
	}

	@Override
	public String findBySort(String sort) {
		// TODO Auto-generated method stub
		return findManageDao.findBySort(sort);
	}

	@Override
	public void retrudeByStartSort(String startSort,String sort) {
		// TODO Auto-generated method stub
		findManageDao.retrudeByStartSort(startSort,sort);
	}

	@Override
	public void retrudeSortDel(String sort) {
		// TODO Auto-generated method stub
		findManageDao.retrudeSortDel(sort);
	}

	@Override
	public void retrudeByEndSort(String startSort,String sort) {
		findManageDao.retrudeByEndSort(startSort,sort);
		
	}

	@Override
	public String findMaxSort(Long id) {
		// TODO Auto-generated method stub
		return findManageDao.findMaxSort(id);
	}
}