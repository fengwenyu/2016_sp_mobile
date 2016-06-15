package com.shangpin.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.AppNavigation;

/**
 * 奥莱客户端导航编辑
 * 
 * @author yangtongchui
 * @since 2014-8-12
 * 
 */
public interface AppNavigationDAO extends JpaRepository<AppNavigation, Long>,
		JpaSpecificationExecutor<AppNavigation> {

	AppNavigation findByNavName(String name);

	Page<AppNavigation> findByNavNameContaining(String name, Pageable createPageable);
}
