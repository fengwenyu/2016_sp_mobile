package com.shangpin.core.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.admin.AppChannel;

public interface AppChannelDao extends JpaRepository<AppChannel, Long>, JpaSpecificationExecutor<AppChannel> {

    @Query("select max(num) from AppChannel")
    String findMaxNum();
    
    // 根据包含名称(类似like)查找app
    Page<AppChannel> findByNameContaining(String name, Pageable pageable);
}
