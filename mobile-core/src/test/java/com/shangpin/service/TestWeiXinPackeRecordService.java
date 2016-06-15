package com.shangpin.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.WeiXinPacketRecord;
import com.shangpin.core.service.IWeiXinPacketRecordService;

public class TestWeiXinPackeRecordService extends BaseTest{
	
	@Autowired
	private IWeiXinPacketRecordService weiXinPacketRecordService;
	
	@Test
	public void save(){
		WeiXinPacketRecord record = new WeiXinPacketRecord();
		record.setSendLoginName("李四");
		record.setSendNickName("nikelisi");
		record.setReceiveLoginName("张三sssssssssssss");
		record.setReceiveNickName("nickzhangsan");
		record.setPacketMoney(4.00);
		record.setCreateTime(new Timestamp(new Date().getTime()));
		weiXinPacketRecordService.save(record);
	}
	
	@Test
	public void modify(){
		
	}
	
	@Test
	public void findById(){
		
	}
	
	@Test
	public void delete(){
		
	}
	
	@Test
	public void findBySendLoginName(){
		List<WeiXinPacketRecord> records = weiXinPacketRecordService.sendPacket("李四");
		for(WeiXinPacketRecord record : records){
			System.out.println(record.getSendLoginName() + "," + record.getReceiveLoginName() + "," + record.getPacketMoney());
		}
	}
	
	@Test
	public void isDerc(){
		//System.out.println(weiXinPacketRecordService.isDiscern("oFHXijoZ0hlJDo7FCpye3d39GbOs", "oFHXijs8DAnA2OfSwOUH7rZtuv4U"));
	}

}
