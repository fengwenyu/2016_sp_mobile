package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.HotBrands;

public interface IHotBrandsDAO extends JpaRepository<HotBrands, Long>, JpaSpecificationExecutor<HotBrands> {

	@Query("select h from HotBrands h  order by sort asc ,createDate desc")
	public List<HotBrands> findHotBrandsList();
	//@Query("select bean from HotBrands bean where bean.brandId=?1")
	public HotBrands findByBrandId(String brandNo);
}
