package com.shangpin.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.FindDao;
import com.shangpin.core.entity.main.Find;
import com.shangpin.core.service.FindService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class FindServiceImpl implements FindService{
	    
	    @Autowired
	    private FindDao findDao;
	    @Override
	    @OrderBy("sort ASC")
	    public List<Find> findByExample(Specification<Find> specification, Page page) {
			org.springframework.data.domain.Page<Find> springDatePage = findDao.findAll(specification, PageUtils.createPageable(page));
	        page.setTotalCount(springDatePage.getTotalElements());
	        return springDatePage.getContent();
		}
        @Override
        public void save(Find find) {
            findDao.save(find);
            
        }
        @Override
        public Long isExist(Date date, String activityId) {
            return findDao.isExist(date,activityId);
        }
        @Override
        public Find get(Long id) {
            return findDao.findOne(id);
        }
        @Override
        public void update(Find find) {
            findDao.save(find);
            
        }
        @Override
        public void delete(Long id) {
            findDao.delete(id);
            
        }
        @Override
        public String findMinSort(Date date,Long id) {
            return String.valueOf(findDao.findMinSort(date,id));
        }
        @Override
        public void sortRetrude(Date date, Long id) {
            findDao.sortRetrude(date,id);
            
        }
        @Override
        public int findMaxSort(Date Date, Long id) {
            return findDao.findMaxSort(Date,id);
        }
        @Override
        public int findBySort(Date Date, Integer sortBy) {
            return Integer.valueOf(String.valueOf(findDao.findBySort(Date, sortBy)));
        }
        @Override
        public void retrudeByEndSort(Date Date, Integer sortBy) {
            findDao.retrudeByEndSort(Date,sortBy);
            
        }
        @Override
        public void retrudeByStartSort(Date Date, Integer sortBy) {
            findDao.retrudeByStartSort(Date,sortBy);
            
        }
        @Override
        public void retrudeSortDel(Date Date,Integer sortBy) {
            findDao.retrudeSortDel(Date,sortBy);
            
        }

}
