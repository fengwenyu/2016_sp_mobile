/**
 * 
 */
package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.DataControl;

public interface DataControlDAO extends JpaRepository<DataControl, Long>, JpaSpecificationExecutor<DataControl> {
	DataControl getByName(String name);
}