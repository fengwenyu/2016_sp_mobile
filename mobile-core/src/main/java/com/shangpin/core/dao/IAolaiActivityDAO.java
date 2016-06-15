package com.shangpin.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.AolaiActivity;

public interface IAolaiActivityDAO extends JpaRepository<AolaiActivity, Long>, JpaSpecificationExecutor<AolaiActivity> {

   @Query("select a from AolaiActivity a where ?1 between startTime and endTime and display=?2 order by startTime desc")
   public List<AolaiActivity> findByCondition(Date date,int display);
  
}
