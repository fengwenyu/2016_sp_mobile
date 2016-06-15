package com.shangpin.core.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.main.Find;

public interface FindDao extends JpaRepository<Find, Long>, JpaSpecificationExecutor<Find>{
	@Query("SELECT count(*) FROM Find WHERE showEndTime>=?1 AND type='ACTIVITY' AND activityId=?2 ")
    Long isExist(Date date, String activityId);
    @Query("SELECT MIN(sortBy) FROM Find WHERE ?1 <showEndTime and id!=?2")
    int findMinSort(Date date,Long id);
    @Modifying 
    @Query("UPDATE Find SET sortBy=(sortBy+1) WHERE ?1 <showEndTime and id!=?2  ")
    void sortRetrude(Date date, Long id);
   
    @Query("SELECT MAX(sortBy) FROM Find WHERE ?1 <showEndTime and id!=?2")
    int findMaxSort(Date date, Long id);
   
    @Query("SELECT  COUNT(*)  FROM Find as o WHERE  ?1 <showEndTime and sortBy=?2 ")        
    long findBySort(Date date, Integer sortBy);
    @Modifying
    @Query("UPDATE Find SET sortBy=(sortBy+1) WHERE  ?1 <showEndTime and sortBy<?2")    
    void retrudeByEndSort(Date date, Integer sortBy);
    @Modifying
    @Query("UPDATE Find SET sortBy=(sortBy-1) WHERE ?1 <showEndTime and sortBy>?2 ")  
    void retrudeByStartSort(Date date,  Integer sortBy);
//    UPDATE findManage SET sort=ABS(sort)-1 WHERE ABS(sort)>?  and ?<showEndDate and type!=?
    @Modifying 
    @Query("UPDATE Find SET sortBy=(sortBy-1) WHERE ?1 <showEndTime and sort>?2  ")
    void retrudeSortDel(Date date, Integer sortBy);
}
