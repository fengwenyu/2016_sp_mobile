package com.shangpin.core.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shangpin.core.dao.admin.AppDao;
import com.shangpin.core.entity.admin.App;
import com.shangpin.core.service.admin.AppService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    @Autowired
    private AppDao appDao;

    @Override
    public void save(App app) {
        this.appDao.save(app);
    }

    @Override
    public void update(App app) {
        this.appDao.save(app);
    }

    @Override
    public void delete(Long id) {
        this.appDao.delete(id);
    }

    @Override
    public App findById(Long id) {
        return this.appDao.findOne(id);
    }

    @Override
    public Long findMaxNum() {
        Long maxNum = new Long(0);
        String maxStr = this.appDao.findMaxNum();
        if (!StringUtils.isEmpty(maxStr)) {
            maxNum = Long.valueOf(maxStr);
        }
        return maxNum;
    }

    @Override
    public List<App> find(Page page, String name) {
        org.springframework.data.domain.Page<App> springDataPage = appDao.findByNameContaining(name, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
    
    @Override
    public List<App> findByExample(Specification<App> specification, Page page) {
        org.springframework.data.domain.Page<App> springDataPage = appDao.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<App> findAll(Page page) {
        org.springframework.data.domain.Page<App> springDataPage = appDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

}
