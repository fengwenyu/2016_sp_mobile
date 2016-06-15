package com.shangpin.core.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.FashionPackExt;

public interface IFashionPackExtDAO  extends JpaRepository<FashionPackExt, Long>,JpaSpecificationExecutor<FashionPackExt>{
	
	@Modifying
	@Query("UPDATE FashionPackExt f SET stock = f.stock-1,sellCount=f.sellCount+1 where size=?1")
	void update(String size);
	
	@Query("from FashionPackExt f where size=?1")
	FashionPackExt findBySize(String size);
	
	@Query("select f.size,f.sellCount from FashionPackExt f")
	List<Object[]> findPackExtSellCount();

}
