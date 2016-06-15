package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.FashionPack;

public interface IFashionPackDAO  extends JpaRepository<FashionPack, Long>,JpaSpecificationExecutor<FashionPack>{
	@Query("select f from FashionPack f where ?1=packType")
	List<FashionPack> findByPackType(String packType);

}
