/**
 * 
 */
package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.RolePermissionDataControl;

public interface RolePermissionDataControlDAO extends JpaRepository<RolePermissionDataControl, Long>, JpaSpecificationExecutor<RolePermissionDataControl> {
	List<RolePermissionDataControl> findByRolePermissionId(Long rolePermissionId);
	
	List<RolePermissionDataControl> findByRolePermissionRoleId(Long roleId);
}