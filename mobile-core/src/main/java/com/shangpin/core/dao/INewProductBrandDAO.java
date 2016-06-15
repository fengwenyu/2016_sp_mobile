package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.NewProductBrand;

public interface INewProductBrandDAO extends JpaRepository<NewProductBrand, String>, JpaSpecificationExecutor<NewProductBrand> {

    
    @Query("select t from NewProductBrand t where t.categoryId=?1")
    public NewProductBrand findById(String categoryId);
    
}
