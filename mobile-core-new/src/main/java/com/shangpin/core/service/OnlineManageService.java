package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.main.OnlineManage;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;

public interface OnlineManageService {

    void save(OnlineManage product)  throws ExistedException;
    
    void update(OnlineManage product);
    
    void delete(Long id) throws ServiceException;
    
    List<OnlineManage> findAll(Page page);
    
    OnlineManage findOnline(int productNum,Long channelNum,int inuse);
}
