package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.main.PushConfig;

public interface PushConfigService {
    
    public List<PushConfig> findAll();
    
    public PushConfig findByTypeAndUserid(int type, String userid);
    
    public List<String> tokens(int type, int gender, int start, int size);

}
