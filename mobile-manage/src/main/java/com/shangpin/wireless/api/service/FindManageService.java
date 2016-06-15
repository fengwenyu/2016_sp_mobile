package com.shangpin.wireless.api.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.shangpin.wireless.domain.FindManage;
import com.shangpin.wireless.vo.FindManageVO;

public interface FindManageService {
	public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.FindManageServiceImpl";

//	public FindManageVO findActivity(ShowPositionEnum showPosition);
//
//	public List<FindManageVO> findNotCurrentActivities(ShowPositionEnum first) throws Exception;

	public List<FindManageVO> subjectList(String startDate, String endDate, String keyWord);

	public void save(FindManage model) throws Exception;

	public Map<String, Object> isExist(Date showStartDate, Date showEndDate) throws Exception;

	public FindManage findById(Long id) throws Exception;

	public void update(FindManage findManage) throws Exception;

	public Map<String, Object> isExist(Date showStartDate, Date showEndDate, Long id) throws Exception;

	public void delete(Long id) throws Exception;

	public Integer executeSqlCount(String queryListSql) throws Exception;

	public List<HashMap> executeSqlToMap(String queryListSql, int page, int rows) throws Exception;

	public void updateTopPostion();

	public void sortRetrude(Long id);

	public String findMinSort(Long id);

	public String findBySort(String sort);

	public void retrudeByStartSort(String startSort,String sort);

	public void retrudeSortDel(String sort);

	public void retrudeByEndSort(String startSort,String sort);

	public String findMaxSort(Long id);

}
