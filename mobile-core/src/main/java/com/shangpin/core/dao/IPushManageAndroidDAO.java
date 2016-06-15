package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.PushManageAndroid;

public interface IPushManageAndroidDAO extends JpaRepository<PushManageAndroid, Long>, JpaSpecificationExecutor<PushManageAndroid> {
    @Query("select bean from PushManageAndroid bean where bean.pushType = 0 and bean.userId = ?1 and bean.productNum = ?2 order by bean.createTime")
    public List<PushManageAndroid> findByUserIdAndProductNum(String userId, Long productNum);
    
    public List<PushManageAndroid> findByPushType(Integer pushType, Pageable pageable);
}