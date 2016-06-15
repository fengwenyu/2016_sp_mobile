package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.AppNavigation;

public interface IAppNavigationDAO extends JpaRepository<AppNavigation, Long>, JpaSpecificationExecutor<AppNavigation> {
   /** 
    @Query("select a from AppNavigation a")
    public List<AppNavigation> findAll();
    */
    
}
