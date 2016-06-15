package com.shangpin.core.dao.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.admin.AppDownload;

public interface AppDownloadDao extends JpaRepository<AppDownload, Long>, JpaSpecificationExecutor<AppDownload> {

    // 根据包含名称(类似like)查找app
    Page<AppDownload> findByAppNameContaining(String appName, Pageable pageable);
}
