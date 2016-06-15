package com.shangpin.core.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.PushManageIos;

public interface IPushManageIosDAO extends JpaRepository<PushManageIos, Long>, JpaSpecificationExecutor<PushManageIos> {
	
	public List<PushManageIos> findByPushTypeAndDictIdIn(Integer pushType, Set<Long> dictId, Pageable pageable);

}