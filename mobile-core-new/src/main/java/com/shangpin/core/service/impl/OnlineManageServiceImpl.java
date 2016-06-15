package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.OnlineManageDAO;
import com.shangpin.core.entity.main.OnlineManage;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.service.OnlineManageService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class OnlineManageServiceImpl implements OnlineManageService {
    
    @Autowired
    private OnlineManageDAO onlineMangeDAO;
    
    @Override
    public void save(OnlineManage onlineManage) throws ExistedException {
        onlineMangeDAO.save(onlineManage);
    }

    @Override
    public void update(OnlineManage onlineManage) {
        onlineMangeDAO.save(onlineManage);
    }

    @Override
    public void delete(Long id) throws ServiceException {
        onlineMangeDAO.delete(id);
    }

    @Override
    public List<OnlineManage> findAll(Page page) {
        org.springframework.data.domain.Page<OnlineManage> springDataPage = onlineMangeDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
    
    @Override
    public OnlineManage findOnline(int productNum,Long channelNum,int inuse){
        OnlineManage springDataPage = onlineMangeDAO.findOnline(productNum, channelNum, inuse);
        return springDataPage;
    }

}
