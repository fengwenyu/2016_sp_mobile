package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAppNavigationDAO;
import com.shangpin.core.entity.AppNavigation;
import com.shangpin.core.service.IAppNavigationService;

@Service
@Transactional
public class AppNavigationServiceImpl implements IAppNavigationService {

    @Autowired
    private IAppNavigationDAO appNavigationDAO;
    
    @Override
    public AppNavigation save(AppNavigation appNavigation) {
        return appNavigationDAO.save(appNavigation);
    }

    @Override
    public AppNavigation findById(Long id) {
        return appNavigationDAO.findOne(id);
    }

    @Override
    public AppNavigation modify(AppNavigation appNavigation) {
        return appNavigationDAO.save(appNavigation);
    }

    @Override
    public void delete(Long id) {
        appNavigationDAO.delete(id);
    }

    @Override
    public List<AppNavigation> findAll() {
        return appNavigationDAO.findAll();
    }

}
