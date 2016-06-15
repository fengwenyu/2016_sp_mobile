package com.shangpin.core.service;

import com.shangpin.core.entity.NewProductBrand;

public interface INewProductBrandReService {

    public NewProductBrand addNewProductBrand(NewProductBrand newProductBrand);
    
    public NewProductBrand modifyNewProductBrandById(NewProductBrand newProductBrand);
    
    public NewProductBrand findNewProductBrandById(String categoryId);
    
    public void deleteNewProductBrandById(String categoryId);
    
    public NewProductBrand findByCategoryId(String categoryId);
}
