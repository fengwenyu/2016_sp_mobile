package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IOnlineManageDAO;
import com.shangpin.core.entity.OnlineManage;
import com.shangpin.core.service.IOnlineManageService;


@Service
@Transactional
public class OnlineManageServiceImpl implements IOnlineManageService {

    
    @Autowired
    private IOnlineManageDAO onlineManageDAO;
    
    @Override
    public OnlineManage addOnlineManage(OnlineManage onlineManage) {
        OnlineManage om=this.onlineManageDAO.saveAndFlush(onlineManage);
        return om;
    }

    @Override
    public OnlineManage modifyOnlineManage(OnlineManage onlineManage) {
        OnlineManage om=this.onlineManageDAO.saveAndFlush(onlineManage);
        return om;
    }

    @Override
    public OnlineManage findOnlineManageById(Long id) {
        OnlineManage om=this.onlineManageDAO.findOne(id);
        return om;
    }

    @Override
    public void deleteOnlineManageById(Long id) {
        this.onlineManageDAO.delete(id);

    }

    @Override
    public Page<OnlineManage> findByOnlineManage(Pageable pageable) {
        return this.onlineManageDAO.findByOnlineManage(pageable);
    }

}
