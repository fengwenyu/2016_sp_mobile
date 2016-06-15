package com.shangpin.base.service;

import org.junit.Test;

import com.shangpin.base.service.impl.CustomerServiceImpl;
import com.shangpin.base.vo.ConsigneeAddress;

public class TestCustomerService {
	@Test
	public void testModifyDefaultConsigneeAddress() {
//		CustomerService cs=new CustomerServiceImpl();
	}

	@Test
	public void testAddConsigneeAddress() {
		CustomerService cs=new CustomerServiceImpl();
		ConsigneeAddress ca=new ConsigneeAddress();
		ca.setUserId("916E897CFD298AB703E45EC3A425FE8C");
		ca.setConsigneeName("lisi");
		ca.setProvince("1");
		ca.setCity("2");
		ca.setArea("3");
		ca.setTown("1");
		ca.setAddress("3组");
		ca.setPostcode("102000");
		ca.setTel("13699120345");
		ca.setSetd("false");
		String json=cs.addConsigneeAddress(ca);
		System.out.println(json);
	}

	@Test
	public void testDeleteConsigneeAddress() {
		CustomerService cs=new CustomerServiceImpl();
		String userId="916E897CFD298AB703E45EC3A425FE8C";
		//String addrId="168891";
		//String addrId="83813";
		//String addrId="84116";
		//String addrId="84115";
		String addrId="83816";
		String json =cs.deleteConsigneeAddress(userId, addrId);
		System.out.println(json);
	}

	@Test
	public void modifyConsigneeAddress() {
//		CustomerService cs=new CustomerServiceImpl();
	}

	@Test
	public void modifyFindConsigneeAddress() {
		CustomerService cs=new CustomerServiceImpl();
		
		String userId="916E897CFD298AB703E45EC3A425FE8C";
		String json =cs.findConsigneeAddress(userId);
		System.out.println(json);
	}

}
