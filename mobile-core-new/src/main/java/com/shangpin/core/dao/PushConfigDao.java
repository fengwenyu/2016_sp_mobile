package com.shangpin.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.PushConfig;

public interface PushConfigDao extends JpaRepository<PushConfig, Long>, JpaSpecificationExecutor<PushConfig>{
    
    public PushConfig findByTypeAndUserid(int type, String userid);
    
}
