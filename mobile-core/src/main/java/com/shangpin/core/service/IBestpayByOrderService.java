package com.shangpin.core.service;

import com.shangpin.core.entity.BestpayorderShangpin;


public interface IBestpayByOrderService {
    
    public BestpayorderShangpin addBestpayorderShangpin(BestpayorderShangpin bestpayorderShangpin);
    
    public BestpayorderShangpin findBestpayorderShangpinById(Long id);
    
    public void deleteBestpayorderShangpinById(Long id);
    
}
