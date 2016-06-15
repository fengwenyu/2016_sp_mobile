package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IBestpayorderShangpinDAO;
import com.shangpin.core.entity.BestpayorderShangpin;
import com.shangpin.core.service.IBestpayByOrderService;

@Service
@Transactional
public class BestpayByOrderServiceImpl  implements IBestpayByOrderService {
    
    @Autowired
    private IBestpayorderShangpinDAO bestpayorderShangpinDAO;
    
    @Override
    public BestpayorderShangpin addBestpayorderShangpin(BestpayorderShangpin bestpayorderShangpin) {
        return this.bestpayorderShangpinDAO.save(bestpayorderShangpin);
    }
    
    @Override
    public BestpayorderShangpin findBestpayorderShangpinById(Long id) {
        return this.bestpayorderShangpinDAO.findOne(id);
    }
    
    @Override
    public void deleteBestpayorderShangpinById(Long id){
        this.bestpayorderShangpinDAO.delete(id);
    }
}
