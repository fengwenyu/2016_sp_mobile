package com.shangpin.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.main.StaticActivity;
import com.shangpin.core.util.dwz.Page;

public interface StaticActivityDAO extends JpaRepository<StaticActivity, Long>, JpaSpecificationExecutor<StaticActivity>{

   
    @Query("select f from StaticActivity f where  app!=?2 and startTime >?1")
    List<StaticActivity> findStaticActivities(Date date, String keywords, Page page);


}
