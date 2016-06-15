package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.main.Push;

public interface PushDao extends JpaRepository<Push, Long>, JpaSpecificationExecutor<Push>{
    
    @Query("select p from Push p where p.productCode = ?1 and p.platformType = ?2 and p.status = ?3 and p.userId != null")
    public List<Push> findSendPushs(int productCode, int platformType, int status);
    
    @Query("select p from Push p where p.productCode = ?1 and p.platformType = ?2 and p.sex = ?3 and p.status = ?4 and p.userId = null")
    public List<Push> findSendPushs(int productCode, int platformType, int gender, int status);

}
