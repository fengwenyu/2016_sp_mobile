package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.Receive;

public interface IReceiveDAO extends JpaRepository<Receive, Long>, JpaSpecificationExecutor<Receive> {
    
	@Query("select count(fuserid) from Receive a where a.fuserid = ?1 and a.forderId = ?2 and a.fspuId = ?3")
    Long countrecord(String fuserid,String forderId,String fspuId);
    
	@Query("select count(fuserid) from Receive a where a.fuserid = ?1 and a.forderId = ?2 and a.fspuId = ?3 and fspuNo = ?4")
    Long countrecordAndSpuNo(String fuserid,String forderId,String fspuId,String fspuNo); 
         
	@Query("select a from Receive a where a.fuserid = ?1 and a.forderId = ?2 and a.fspuId = ?3 and fspuNo = ?4")
    Receive query(String fuserid,String forderId,String fspuId,String fspuNo); 
	
	
	@Query("select count(fuserid) from Receive a where a.forderId = ?1")
    Long isNewOrderid(String forderId);
	
	@Query("select a from Receive a where a.forderId = ?1 or lorderId = ?2")
    List<Receive> querynewOrderId(String forderId,String lorderId); 
	
	@Query("select a from Receive a where a.lorderId = ?1")
	List <Receive> querydetailOrderId(String lorderId); 
	
	@Query("select a from Receive a where a.fuserid=?1 and  a.forderId=?2 and a.fspuId=?3")
	List <Receive> queryReceivedSku(String fuserId,String forderId,String fskuid);
	
	
	
}
