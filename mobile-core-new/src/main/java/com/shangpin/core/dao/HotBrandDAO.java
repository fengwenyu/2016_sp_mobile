package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.HotBrand;

public interface HotBrandDAO extends JpaRepository<HotBrand, Long>, JpaSpecificationExecutor<HotBrand> {

	HotBrand findByBrandName(String brandName);

	Page<HotBrand> findByBrandNameContaining(String name, Pageable createPageable);

}
