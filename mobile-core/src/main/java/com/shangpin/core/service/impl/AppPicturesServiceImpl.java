package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAppPicturesDAO;
import com.shangpin.core.entity.AppPictures;
import com.shangpin.core.service.IAppPicturesService;

@Service
@Transactional
public class AppPicturesServiceImpl implements IAppPicturesService {

    @Autowired
    private IAppPicturesDAO appPicturesDAO;
    
    @Override
    public List<AppPictures> findAll() {
        return appPicturesDAO.findAll();
    }

    @Override
    public List<AppPictures> findByCondition(String osType,Integer productType) {
        return appPicturesDAO.findByCondition(osType,productType);
    }

    @Override
    public AppPictures save(AppPictures appPictures) {
        return appPicturesDAO.save(appPictures);
    }

    @Override
    public AppPictures findById(Long id) {
        return appPicturesDAO.findOne(id);
    }

    @Override
    public AppPictures modify(AppPictures appPictures) {
        return appPicturesDAO.save(appPictures);
    }

    @Override
    public void delete(Long id) {
        appPicturesDAO.delete(id);
    }

}
