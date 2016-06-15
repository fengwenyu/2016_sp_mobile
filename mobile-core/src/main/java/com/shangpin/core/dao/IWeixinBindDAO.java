package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.AccountWeixinBind;

public interface IWeixinBindDAO extends JpaRepository<AccountWeixinBind, Long>, JpaSpecificationExecutor<AccountWeixinBind>{
	@Query("select a from AccountWeixinBind a where weixinid = ?1  and unbindTime is null and bindTime is not NULL and markup = 'hand'")
	List<AccountWeixinBind> findByWXId(String wxId);
	
	@Query("select a from AccountWeixinBind a where weixinid = ?1 and userId = ?2 and unbindTime is null and bindTime is not NULL and markup = 'hand'")
	AccountWeixinBind findByWXIdAndUserId(String wxId, String userId);

}
