package com.shangpin.core.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.shangpin.core.entity.main.Dictionary;

public interface DictionaryDAO extends JpaRepository<Dictionary, Long>,
		JpaSpecificationExecutor<Dictionary> {

	Dictionary findByName(String name);

	Dictionary findByCode(String code);

	Dictionary findByNameAndCode(String name, String code);
	
	List<Dictionary> findByParentId(Long parentId);
	
	@Query("from Dictionary dic where dic.parent.code = ?1 order by dic.priority ASC")
	List<Dictionary> findByParentCode(String parentCode);

	Page<Dictionary> findByParentId(Long parentId, Pageable pageable);

	Page<Dictionary> findByParentIdAndNameContaining(Long parentId,
			String name, Pageable pageable);

	@QueryHints(value = {
			@QueryHint(name = "org.hibernate.cacheable", value = "true"),
			@QueryHint(name = "org.hibernate.cacheRegion", value = "com.shangpin.manager.entity.main") })
	@Query("from Dictionary")
	List<Dictionary> findAllWithCache();
}
