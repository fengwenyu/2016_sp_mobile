package com.shangpin.wechat.service.impl;

import com.shangpin.wechat.service.MaterialService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* MaterialServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 14, 2015</pre> 
* @version 1.0 
*/ 
public class MaterialServiceImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getMaterialList(String type, String offset, String count) 
* 
*/ 
@Test
public void testGetMaterialList() throws Exception {
    MaterialService materialServiceImpl = new MaterialServiceImpl();
    materialServiceImpl.getMaterialList("image",0, 10);

} 

/** 
* 
* Method: getMaterial(String mediaId) 
* 
*/ 
@Test
public void testGetMaterial() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addMaterial(String type, String media) 
* 
*/ 
@Test
public void testAddMaterial() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delMaterial(String mediaId) 
* 
*/ 
@Test
public void testDelMaterial() throws Exception { 
//TODO: Test goes here... 
} 


} 
