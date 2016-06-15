package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.ApiFashionInfo;

public interface FashionInfoDAO extends JpaRepository<ApiFashionInfo, Long>, JpaSpecificationExecutor<ApiFashionInfo> {

}
