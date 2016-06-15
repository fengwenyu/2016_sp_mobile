package com.shangpin.base.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.service.impl.AoLaiServiceImpl;
import com.shangpin.base.service.impl.CommonServiceImpl;
import com.shangpin.base.service.impl.ShangPinServiceImpl;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.utils.JedisUtil;

/**
 * 缓存数据管理工具类 主要生成测试数据或者删除指定数据
 * 
 * @author xupengcheng
 * 
 */
@SuppressWarnings("unused")
public class CacheDataUtil {

	private JedisUtil jedisUtil;
	private AoLaiService aoLaiService;
	private ShangPinService shangPinService;
	private CommonService commonService;
	public CacheDataUtil() {
		jedisUtil = JedisUtil.getInstance();
		aoLaiService = new AoLaiServiceImpl();
		shangPinService = new ShangPinServiceImpl();
		commonService = new CommonServiceImpl();
	}
	
	/**
	 * 生成测试数据
	 */
	public void createAllTestData() {
		try {
//			System.out.println("create groupTime data : " + aoLaiService.findGroupTime("100", "100"));
//			System.out.println("create queryADList data : " + aoLaiService.findAoLaiADList("32", "100", "100"));
//			System.out.println("create aolaiIndex data : " + aoLaiService.findAolaiIndex("1", "1", "2", "1", "1000"));
//			ListOfGoods listOfGoodsVO = new ListOfGoods();
//			listOfGoodsVO.setGender("1");
//			listOfGoodsVO.setPich("100");
//			listOfGoodsVO.setPicw("100");
//			System.out.println("create MobileTopicList data : " + aoLaiService.findMobileTopicList(listOfGoodsVO ));
//			System.out.println("create getChannel data : " + aoLaiService.findChannel());
//			System.out.println("create getuserinfo data : " + commonService.findUserInfo("EBBD79659BDC63F15C78D1C9EF7CBDD7"));
//			System.out.println("create SPBrands data : " + shangPinService.findCapitalBrandList("-1"));
//			
//			System.out.println("create subjectproductlist data : " + aoLaiService.findSubjectProductList("20120810328", "100", "80", null, null));
//			 ListOfGoods listOfGoodsVO1 = new ListOfGoods();
//			 listOfGoodsVO1.setGender("0");
//			 listOfGoodsVO1.setPicw("30");
//			 listOfGoodsVO1.setPich("50");
//			 listOfGoodsVO1.setPageIndex("1");
//			 listOfGoodsVO1.setPageSize("10");
//			 listOfGoodsVO1.setUserId("916E897CFD298AB703E45EC3A425FE8c");
//			 listOfGoodsVO1.setCategoryId("A01B01");
//			 listOfGoodsVO1.setFilterSold("1");
//			 listOfGoodsVO1.setBrandId("");
//			System.out.println("create SPProductsInCateAndBrand data : " + shangPinService.findSPProductsAndBrand(listOfGoodsVO1));
//			System.out.println("create flagship data :　" + shangPinService.findFlagShip("B007"));
//			System.out.println("create orderMoreLogistic data: " + shangPinService.findOrderMoreLogistic("566537929BB692F41C445544EAD8F0E8", "2013012807654"));
//			System.out.println("create SelectEndingSubjectList data: " + aoLaiService.findEndingSubjectList("0", "100", "50"));
//			 ListOfGoods listOfGoodsVO2 = new ListOfGoods();
//		        listOfGoodsVO2.setChId("1");
//		        listOfGoodsVO2.setPageIndex("1");
//		        listOfGoodsVO2.setPageSize("10");
//		        listOfGoodsVO2.setPicw("20");
//		        listOfGoodsVO2.setPich("30");
//			System.out.println("create aolaiChannelActivity data: " + aoLaiService.findChannelActivity(listOfGoodsVO2));
//			System.out.println("create SubjectList data: " + aoLaiService.findSubjectListByPeriod("1", "2012-09-08", "2012-11-12", "100", "80"));
			//jedisUtil.getJedis().flushDB();//清楚缓存数据
			//System.out.println("create provincelist data : " + commonService.findProvinceList());
			//System.out.println("create citylist data : " + commonService.findCityList("3"));
			//System.out.println("create arealist data : " + commonService.findAreaList("6"));
			System.out.println("create townlist data : " + commonService.findTownList("76"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CacheDataUtil cacheDataUtil = new CacheDataUtil();
		cacheDataUtil.createAllTestData();
	}
	
	/**
	 * 删除所有数据
	 */
	public void delAllTestData() {
		jedisUtil.new Keys().delKeysLike(GlobalConstants.CACHE_BASE + "*");
	}

	/**
	 * 删除指定base方法的数据
	 */
	public  void delBaseMethodOfName(String name) {
		jedisUtil.new Keys().delKeysLike(GlobalConstants.CACHE_BASE + name + "*");
	}

	/**
	 * 删除指定业务方法数据
	 */
	public  void delBusinessMethodOfName() {

	}

	/**
	 * 删除Base所有数据
	 */
	public  void delAllBaseTestData() {
		jedisUtil.new Keys().delKeysLike(GlobalConstants.CACHE_BASE + "*");
	}

	/**
	 * 删除所有业务数据
	 */
	public  void delAllBusinessTestData() {

	}

}
