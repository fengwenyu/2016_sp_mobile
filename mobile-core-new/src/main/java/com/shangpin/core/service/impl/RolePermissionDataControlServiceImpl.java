/**
 * 
 */
package	com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.RolePermissionDataControlDAO;
import com.shangpin.core.entity.main.RolePermissionDataControl;
import com.shangpin.core.service.RolePermissionDataControlService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class RolePermissionDataControlServiceImpl implements RolePermissionDataControlService {
	
	@Autowired
	private RolePermissionDataControlDAO rolePermissionDataControlDAO;

	/**   
	 * @param id
	 * @return  
	 * @see com.shangpin.core.service.RolePermissionDataControlService#get(java.lang.Long)  
	 */ 
	@Override
	public RolePermissionDataControl get(Long id) {
		return rolePermissionDataControlDAO.findOne(id);
	}

	/**   
	 * @param rolePermissionDataControl  
	 * @see com.shangpin.core.service.RolePermissionDataControlService#saveOrUpdate(com.shangpin.core.entity.main.RolePermissionDataControl)  
	 */
	@Override
	public void saveOrUpdate(RolePermissionDataControl rolePermissionDataControl) {
		rolePermissionDataControlDAO.save(rolePermissionDataControl);
	}

	/**   
	 * @param id  
	 * @see com.shangpin.core.service.RolePermissionDataControlService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		rolePermissionDataControlDAO.delete(id);
	}
	
	/**   
	 * @param page
	 * @return  
	 * @see com.shangpin.core.service.RolePermissionDataControlService#findAll(com.shangpin.core.util.dwz.Page)  
	 */
	@Override
	public List<RolePermissionDataControl> findAll(Page page) {
		org.springframework.data.domain.Page<RolePermissionDataControl> springDataPage = rolePermissionDataControlDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 
	 * @param specification
	 * @param page
	 * @return
	 * @see com.shangpin.core.service.RolePermissionDataControlService#findByExample(org.springframework.data.jpa.domain.Specification, com.shangpin.core.util.dwz.Page)	
	 */
	@Override
	public List<RolePermissionDataControl> findByExample(
			Specification<RolePermissionDataControl> specification, Page page) {
		org.springframework.data.domain.Page<RolePermissionDataControl> springDataPage = rolePermissionDataControlDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/* (non-Javadoc)
	 * @see com.shangpin.manager.service.RolePermissionDataControlService#save(java.lang.Iterable)
	 */
	@Override
	public void save(Iterable<RolePermissionDataControl> entities) {
		rolePermissionDataControlDAO.save(entities);
	}

	/* (non-Javadoc)
	 * @see com.shangpin.manager.service.RolePermissionDataControlService#deleteInBatch(java.lang.Iterable)
	 */
	@Override
	public void deleteInBatch(Iterable<RolePermissionDataControl> entities) {
		rolePermissionDataControlDAO.delete(entities);
	}

	/* (non-Javadoc)
	 * @see com.shangpin.manager.service.RolePermissionDataControlService#findByRolePermissionId(java.lang.Long)
	 */
	@Override
	public List<RolePermissionDataControl> findByRolePermissionId(
			Long rolePermissionId) {
		return rolePermissionDataControlDAO.findByRolePermissionId(rolePermissionId);
	}

	/* (non-Javadoc)
	 * @see com.shangpin.manager.service.RolePermissionDataControlService#findByRoleId(java.lang.Long)
	 */
	@Override
	public List<RolePermissionDataControl> findByRoleId(Long roleId) {
		return rolePermissionDataControlDAO.findByRolePermissionRoleId(roleId);
	}

}
