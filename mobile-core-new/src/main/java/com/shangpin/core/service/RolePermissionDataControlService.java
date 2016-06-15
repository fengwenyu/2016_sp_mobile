/**
 * 
 */
package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.RolePermissionDataControl;
import com.shangpin.core.util.dwz.Page;

public interface RolePermissionDataControlService {
	RolePermissionDataControl get(Long id);

	void saveOrUpdate(RolePermissionDataControl rolePermissionDataControl);

	void delete(Long id);
	
	List<RolePermissionDataControl> findAll(Page page);
	
	List<RolePermissionDataControl> findByExample(Specification<RolePermissionDataControl> specification, Page page);
	
	void save(Iterable<RolePermissionDataControl> entities);
	
	void deleteInBatch(Iterable<RolePermissionDataControl> entities);

	List<RolePermissionDataControl> findByRolePermissionId(Long rolePermissionId);
	
	List<RolePermissionDataControl> findByRoleId(Long roleId);
}
