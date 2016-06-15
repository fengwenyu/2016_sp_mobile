package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.core.dao.PushConfigDao;
import com.shangpin.core.entity.main.PushConfig;
import com.shangpin.core.service.PushConfigService;

@Service
public class PushConfigServiceImpl implements PushConfigService{
    
    @Autowired
    private PushConfigDao pushConfigDao;

    @Override
    public List<PushConfig> findAll() {
        return pushConfigDao.findAll();
    }

    @Override
    public PushConfig findByTypeAndUserid(int type, String userid) {
        return pushConfigDao.findByTypeAndUserid(type, userid);
    }

    @Override
    public List<String> tokens(int type, int gender, int start, int size) {
        
        return null;
    }

}
