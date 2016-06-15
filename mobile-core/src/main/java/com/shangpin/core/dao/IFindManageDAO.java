package com.shangpin.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.FindManage;

public interface IFindManageDAO extends JpaRepository<FindManage, Long>, JpaSpecificationExecutor<FindManage> {

    @Query("select f from FindManage f where ?1<showEndDate order by showStartDate desc")
    public List<FindManage> findManageData(Date date);

    @Query("select f from FindManage f where ?1<showEndDate and isSlider=?2 and type!=?3 order by sort asc,showStartDate desc")
    public List<FindManage> findByActivityManage(Date date, int isSlider, String type);

    @Query("select f from FindManage f where ?1 between showStartDate and showEndDate and display=?2 and type=?3 order by sort asc,showStartDate desc")
    public List<FindManage> findStaticActivity(Date date, int display, String type);

    @Query("select f from FindManage f where  type!='STATICATC' and ((?1 between showStartDate and showEndDate) or (?1 between mobilePreTime and showEndDate)) order by ABS(sort) asc,showStartDate desc")
    public Page<FindManage> findDefaultSlider(Date date, Pageable pageable);
}
