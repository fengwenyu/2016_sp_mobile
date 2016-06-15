package com.shangpin.core.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.admin.AppDownloadDao;
import com.shangpin.core.entity.admin.AppDownload;
import com.shangpin.core.service.admin.AppDownloadService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class AppDownloadServiceImpl implements AppDownloadService {

    @Autowired
    private AppDownloadDao appDownloadDao;

    @Override
    public void save(AppDownload appDownload) {
        this.appDownloadDao.save(appDownload);
    }

    @Override
    public void update(AppDownload appDownload) {
        this.appDownloadDao.save(appDownload);
    }

    @Override
    public void delete(Long id) {
        this.appDownloadDao.delete(id);
    }

    @Override
    public AppDownload findById(Long id) {
        return this.appDownloadDao.findOne(id);
    }
    
    @Override
    public List<AppDownload> find(Page page, String appName) {
        org.springframework.data.domain.Page<AppDownload> springDataPage = appDownloadDao.findByAppNameContaining(appName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
    
    @Override
    public List<AppDownload> findByExample(Specification<AppDownload> specification, Page page) {
        org.springframework.data.domain.Page<AppDownload> springDataPage = appDownloadDao.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<AppDownload> findAll(Page page) {
        org.springframework.data.domain.Page<AppDownload> springDataPage = appDownloadDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

}
