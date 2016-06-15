package com.shangpin.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.WxFashionInformation;

public interface IWxFashionInformationDAO  extends JpaRepository<WxFashionInformation, Long>, JpaSpecificationExecutor<WxFashionInformation>{
	@Query("select f from WxFashionInformation f where ?1>releaseDate order by sort asc ,releaseDate desc")
	List<WxFashionInformation> findFashionList(Date date);


}
