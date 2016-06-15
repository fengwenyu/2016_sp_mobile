package com.shangpin.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.NewProductBrand;
import com.shangpin.core.service.INewProductBrandReService;


public class TestNewProductBrandReService extends BaseTest{
    
    @Autowired
    INewProductBrandReService newProductBrandReService;
    
    @Test
    public void testAddNewProductBrand(){
        NewProductBrand bean=new NewProductBrand();
        bean.setBrandProductId("111111");
        bean.setCategoryId("1222");
        bean.setFifthBrandId("12311");
        bean.setFifthBrandName("1212121");
        NewProductBrand newProductBrand=this.newProductBrandReService.addNewProductBrand(bean);
        Assert.notNull(newProductBrand.getCategoryId());
    }
    
    @Test
    public void testModifyNewProductBrandById(){
        NewProductBrand bean=new NewProductBrand();
        bean.setBrandProductId("111111");
        bean.setCategoryId("1222");
        bean.setFifthBrandId("12311");
        bean.setFifthBrandName("1212121");
        NewProductBrand newProductBrand=this.newProductBrandReService.modifyNewProductBrandById(bean);
        Assert.notNull(newProductBrand.getCategoryId());
    }
    
    @Test
    public void testFindNewProductBrandById(){
        NewProductBrand newProductBrand=this.newProductBrandReService.findNewProductBrandById("1222");
        Assert.notNull(newProductBrand);
    }
    
    @Test
    public void testDeleteNewProductBrandById(){
        this.newProductBrandReService.deleteNewProductBrandById("A02B01");
        NewProductBrand newProductBrand=this.newProductBrandReService.findNewProductBrandById("A02B01");
        Assert.isNull(newProductBrand);
    }
    
    @Test
    public void testFindByCategoryId(){
        NewProductBrand newProductBrand=this.newProductBrandReService.findByCategoryId("1222");
        Assert.notNull(newProductBrand.getBrandProductId());
    }

}
