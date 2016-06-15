package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.AppPic;

public interface AppPicDAO extends JpaRepository<AppPic, Long>, JpaSpecificationExecutor<AppPic> {

}
