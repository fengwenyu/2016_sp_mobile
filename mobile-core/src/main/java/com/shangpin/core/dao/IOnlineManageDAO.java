package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.OnlineManage;

public interface IOnlineManageDAO extends JpaRepository<OnlineManage, Long>, JpaSpecificationExecutor<OnlineManage> {

    
    @Query("select a from OnlineManage a")
    public Page<OnlineManage> findByOnlineManage(Pageable pageable);
    
}
