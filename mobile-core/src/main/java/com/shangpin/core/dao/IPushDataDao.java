package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.PushData;

/**
 * pushDao
 * @author qinyingchun
 *
 */
public interface IPushDataDao extends JpaRepository<PushData, Long>, JpaSpecificationExecutor<PushData>{
	
	public List<PushData> findByTokenAndStatus(String token, String status);

}
