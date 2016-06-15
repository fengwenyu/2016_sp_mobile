package com.shangpin.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shangpin.core.entity.OnlineManage;

public interface IOnlineManageService {

    public OnlineManage addOnlineManage(OnlineManage onlineManage);
    
    public OnlineManage modifyOnlineManage(OnlineManage onlineManage);
    
    public OnlineManage findOnlineManageById(Long id);
    
    public void deleteOnlineManageById(Long id);
    
    public Page<OnlineManage> findByOnlineManage(Pageable pageable);
}
