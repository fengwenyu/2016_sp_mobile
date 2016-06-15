package com.shangpin.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.StaticActivityDAO;
import com.shangpin.core.entity.main.StaticActivity;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.service.StaticActivityService;
import com.shangpin.core.shiro.ShiroDbRealm;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;
@Service
@Transactional
public class StaticActivityServiceImpl implements StaticActivityService{

    @Autowired
    private StaticActivityDAO staticActivityDAO;

    @Autowired
    private ShiroDbRealm shiroRealm;


    @Override
    public List<StaticActivity> find(Page page, String name) {
        return null;
//        org.springframework.data.domain.Page<StaticActivity> springDataPage = staticActivityDAO.findByBrandNameContaining(name, PageUtils.createPageable(page));
//        page.setTotalCount(springDataPage.getTotalElements());
//        return springDataPage.getContent();
    }

    @Override
    public void update(StaticActivity staticActivity) {
        staticActivityDAO.save(staticActivity);
//        shiroRealm.clearCachedAuthorizationInfo(staticActivity.getBrandName());
    }

    @Override
    public void save(StaticActivity staticActivity) throws ExistedException {
//        if (staticActivityDAO.findByBrandName(staticActivity.getBrandName()) != null) {
//            throw new ExistedException("品牌添加失败，品牌名：" + staticActivity.getBrandName() + "已存在。");
//        }
        staticActivityDAO.save(staticActivity);
//        shiroRealm.clearCachedAuthorizationInfo(staticActivity.getBrandName());
    }

    @Override
    public StaticActivity get(Long id) {
        return staticActivityDAO.findOne(id);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        StaticActivity staticActivity = staticActivityDAO.findOne(id);
        staticActivityDAO.delete(staticActivity.getId());
//        shiroRealm.clearCachedAuthorizationInfo(staticActivity.getBrandName());
    }

    @Override
    public List<StaticActivity> findAll(Page page) {
        org.springframework.data.domain.Page<StaticActivity> springDataPage = staticActivityDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<StaticActivity> findByExample(Specification<StaticActivity> specification, Page page) {
        page.setOrderField("createTime");
        page.setOrderDirection(Page.ORDER_DIRECTION_DESC);
        org.springframework.data.domain.Page<StaticActivity> springDatePage = staticActivityDAO.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDatePage.getTotalElements());
        return springDatePage.getContent();
    }

    @Override
    public List<StaticActivity> findStaticActivities(Date date, String keywords, Page page) {
        return staticActivityDAO.findStaticActivities(date,keywords,page);
    }
}
