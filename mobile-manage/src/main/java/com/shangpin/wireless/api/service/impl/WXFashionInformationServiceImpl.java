package com.shangpin.wireless.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;





import com.shangpin.wireless.api.service.WXFashionInformationService;
import com.shangpin.wireless.dao.WXFashionInformationDao;
import com.shangpin.wireless.domain.WXFashionInformation;
@Service(WXFashionInformationService.SERVICE_NAME)
public class WXFashionInformationServiceImpl implements WXFashionInformationService{
	@Resource(name = WXFashionInformationDao.DAO_NAME)
	private WXFashionInformationDao wxFashionInformationDao;

	@Override
	public void add(WXFashionInformation model) throws Exception {
		wxFashionInformationDao.save(model);
		
	}

	@Override
	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		return wxFashionInformationDao.executeSqlToMap(queryListSql, page, rows);
	}

	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		return wxFashionInformationDao.executeSqlCount(queryListSql);
	}

	@Override
	public WXFashionInformation findById(Long id) throws Exception {
		return wxFashionInformationDao.getById(id);
	}

	@Override
	public void delete(Long id) throws Exception {
		wxFashionInformationDao.delete(id);
	}

	@Override
	public void update(WXFashionInformation wxFashionInformation) throws Exception {
		wxFashionInformationDao.update(wxFashionInformation);
		
	}

	@Override
	public String findMinSort(Long id) {
		// TODO Auto-generated method stub
		return wxFashionInformationDao.findMinSort(id);
	}

	@Override
	public void sortRetrude(Long id) {
		wxFashionInformationDao.sortRetrude(id);
		
	}

	@Override
	public void retrudeSortDel(Integer sort) {
		wxFashionInformationDao.retrudeSortDel(sort);
		
	}

	@Override
	public String findMaxSort() {
		return wxFashionInformationDao.findMaxSort();
	}

	@Override
	public String findBySort(Integer sort) {
		// TODO Auto-generated method stub
		return wxFashionInformationDao.findBySort(sort);
	}

	@Override
	public void retrudeByEndSort(Integer startSort, Integer sort) {
		wxFashionInformationDao.retrudeByEndSort(startSort,sort);
		
	}

	@Override
	public void retrudeByStartSort(Integer startSort, Integer sort) {
		wxFashionInformationDao.retrudeByStartSort(startSort,sort);
		
	}

}
