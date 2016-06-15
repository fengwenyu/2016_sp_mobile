package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.BestpayorderShangpin;

public interface IBestpayorderShangpinDAO  extends JpaRepository<BestpayorderShangpin, Long>, JpaSpecificationExecutor<BestpayorderShangpin>  {
}
