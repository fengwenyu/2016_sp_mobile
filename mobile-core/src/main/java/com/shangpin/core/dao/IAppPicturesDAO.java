package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.AppPictures;

public interface IAppPicturesDAO extends JpaRepository<AppPictures, Long>, JpaSpecificationExecutor<AppPictures> {
    /**
    @Query("select a from AppPictures a")
    public List<AppPictures> findAll();
     */
    
    @Query("select a from AppPictures a where a.osType = ?1 and a.productType = ?2")
    public List<AppPictures> findByCondition(String osType,Integer productType);
}
