package com.shangpin.service;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.service.IWeiXinPacketAccountService;

public class TestWeiXinPacketAccountService extends BaseTest{
	
	@Autowired
	private IWeiXinPacketAccountService weiXinPacketAccountService;
	
	@Test
	public void save(){
		WeiXinPacketAccount account = new WeiXinPacketAccount();
		account.setLoginName("一枝梅");
		account.setOpenId("1111111111");
		account.setNickName("尼恩");
		account.setReceivePacketNum(2);
		account.setReceivePacketMoney(25.00);
		account.setSendPacketNum(7);
		account.setSendPacketMoney(56.32);
		account.setBalance(25.02);
		account.setCreateTime(new Timestamp(new Date().getTime()));
		weiXinPacketAccountService.save(account);
	}
	
	@Test
	public void modify(){
		WeiXinPacketAccount account = new WeiXinPacketAccount();
		account.setId(1);
		account.setLoginName("一枝梅");
		account.setNickName("尼恩");
		account.setReceivePacketNum(2);
		account.setReceivePacketMoney(25.00);
		account.setSendPacketNum(7);
		account.setSendPacketMoney(56.32);
		account.setBalance(25.02);
		account.setUpdateTime(new Timestamp(new Date().getTime()));
		weiXinPacketAccountService.modify(account);
	}
	
	@Test
	public void findById(){
		WeiXinPacketAccount account = weiXinPacketAccountService.findById(1);
		System.out.println("登录名：" + account.getLoginName() + ",昵称：" + account.getNickName());
	}
	
	@Test
	public void delete(){
		weiXinPacketAccountService.delete(2);
	}
	
	@Test
	public void findByOpenId(){
		WeiXinPacketAccount account = weiXinPacketAccountService.findByOpenId("1111111111");
		System.out.println("nickName:" + account.getNickName());
	}

}
