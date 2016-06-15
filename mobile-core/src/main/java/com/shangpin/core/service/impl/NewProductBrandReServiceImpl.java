package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.INewProductBrandDAO;
import com.shangpin.core.entity.NewProductBrand;
import com.shangpin.core.service.INewProductBrandReService;

@Service
@Transactional
public class NewProductBrandReServiceImpl implements INewProductBrandReService {

    @Autowired
    private INewProductBrandDAO newProductBrandDAO;
    
    @Override
    public NewProductBrand addNewProductBrand(NewProductBrand newProductBrand) {
        NewProductBrand npdb=this.newProductBrandDAO.save(newProductBrand);
        return npdb;
    }

    @Override
    public NewProductBrand modifyNewProductBrandById(NewProductBrand newProductBrand) {
        NewProductBrand npdb=this.newProductBrandDAO.save(newProductBrand);
        return npdb;
    }

    @Override
    public NewProductBrand findNewProductBrandById(String categoryId) {
        NewProductBrand npdb=this.newProductBrandDAO.findOne(categoryId);
        return npdb;
    }

    @Override
    public void deleteNewProductBrandById(String categoryId) {
        this.newProductBrandDAO.delete(categoryId);

    }

    @Override
    public NewProductBrand findByCategoryId(String categoryId) {
        NewProductBrand npdb=this.newProductBrandDAO.findById(categoryId);
        return npdb;
    }

}
