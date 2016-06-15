package com.shangpin.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.Find;
import com.shangpin.core.util.dwz.Page;

public interface FindService {

	List<Find> findByExample(Specification<Find> specification, Page page);

    void save(Find find);

    Long isExist(Date date, String activityId);

    Find get(Long id);

    void update(Find find);

    void delete(Long id);

    String findMinSort(Date date,Long id);

    void sortRetrude(Date parse, Long id);

    int findMaxSort(Date parse, Long id);

    int findBySort(Date parse, Integer sortBy);

    void retrudeByEndSort(Date parse, Integer sortBy);

    void retrudeByStartSort(Date parse, Integer sortBy);

    void retrudeSortDel(Date Date,Integer sortBy);

}
