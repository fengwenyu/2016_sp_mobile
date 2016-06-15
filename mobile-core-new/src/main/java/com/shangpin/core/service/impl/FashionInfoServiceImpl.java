package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.FashionInfoDAO;
import com.shangpin.core.entity.main.ApiFashionInfo;
import com.shangpin.core.service.FashionInfoService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class FashionInfoServiceImpl implements FashionInfoService {

    @Autowired
    private FashionInfoDAO fashionInfoDAO;
    
    @Override
    public void save(ApiFashionInfo apiFashionInfo) {
        fashionInfoDAO.save(apiFashionInfo);
    }

    @Override
    public void update(ApiFashionInfo apiFashionInfo) {
        fashionInfoDAO.save(apiFashionInfo);
    }

    @Override
    public void delete(Long id) {
        fashionInfoDAO.delete(id);
    }

    @Override
    public List<ApiFashionInfo> findByExample(Specification<ApiFashionInfo> specification, Page page) {
        org.springframework.data.domain.Page<ApiFashionInfo> springDataPage = fashionInfoDAO.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public ApiFashionInfo findById(Long id) {
        return fashionInfoDAO.findOne(id);
    }
}
