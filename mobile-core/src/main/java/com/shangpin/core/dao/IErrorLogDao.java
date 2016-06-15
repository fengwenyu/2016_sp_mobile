package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.ErrorLog;

public interface IErrorLogDao extends JpaRepository<ErrorLog, Long>, JpaSpecificationExecutor<ErrorLog> {

    @Query("select a from ErrorLog a")
    public Page<ErrorLog> findByConditins(Pageable pageable);
}
