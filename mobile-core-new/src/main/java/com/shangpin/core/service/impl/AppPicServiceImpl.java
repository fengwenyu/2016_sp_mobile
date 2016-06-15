package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.AppPicDAO;
import com.shangpin.core.entity.main.AppPic;
import com.shangpin.core.service.AppPicService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class AppPicServiceImpl implements AppPicService {

    @Autowired
    private AppPicDAO appPicDAO;

    @Override
    public void save(AppPic appPic) {
        appPicDAO.save(appPic);
    }

    @Override
    public void update(AppPic appPic) {
        appPicDAO.save(appPic);
    }

    @Override
    public void delete(Long id) {
        appPicDAO.delete(id);
    }

    @Override
    public AppPic get(Long id) {
        return appPicDAO.findOne(id);
    }

    @Override
    public List<AppPic> findAll(Specification<AppPic> spec, Page page) {
        org.springframework.data.domain.Page<AppPic> springDataPage = appPicDAO.findAll(spec, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
