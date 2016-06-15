package com.shangpin.core.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.admin.App;

public interface AppDao extends JpaRepository<App, Long>, JpaSpecificationExecutor<App> {

    @Query("select max(num) from App")
    String findMaxNum();

    // 根据包含名称(类似like)查找app
    Page<App> findByNameContaining(String name, Pageable pageable);
}
