package com.shangpin.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.HotBrands;
import com.shangpin.core.service.IHotBrandsService;


public class TestHotBrandsService extends BaseTest {
    
    @Autowired
    IHotBrandsService hotBrandsService;
    
    @Test
    public void testAddHotBrands(){
        HotBrands bean=new HotBrands();
        bean.setBrandId("1211111");
        bean.setBrandName("1111111");
        //bean.setEncodeName("1111111");
        bean.setImgHeight("111");
        bean.setImgUrl("111111111");
        HotBrands list=hotBrandsService.addHotBrands(bean);
        Assert.notNull(list.getId());
    }
    
    @Test
    public void testModifyHotBrands(){
        HotBrands bean=new HotBrands();
        bean.setBrandId("1211111");
        bean.setBrandName("1111111");
        //bean.setEncodeName("1111111");
        bean.setId(43L);
        bean.setImgHeight("111");
        bean.setImgUrl("111111111");
        HotBrands list=hotBrandsService.modifyHotBrands(bean);
        Assert.notNull(list.getId());
    }
    
    @Test
    public void testFindManageById(){
        HotBrands list=hotBrandsService.findHotBrandsById(43L);
        Assert.notNull(list.getId());
    }
    
    @Test
    public void testDeleteFindManageData(){
        hotBrandsService.deleteHotBrandsById(23L);
        HotBrands list=hotBrandsService.findHotBrandsById(23L);
        Assert.isNull(list);
    }
    
    @Test
    public void testFindHotBrandsList(){
        List<HotBrands> list=hotBrandsService.findHotBrandsList();
        Assert.notNull(list);
    }

}
