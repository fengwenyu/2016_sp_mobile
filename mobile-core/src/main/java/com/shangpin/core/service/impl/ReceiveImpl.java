package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IReceiveDAO;
import com.shangpin.core.entity.Receive;
import com.shangpin.core.service.IReceiveService;

@Service
public class ReceiveImpl implements IReceiveService {
    @Autowired
    private IReceiveDAO iReceiveDAO;
    
	 public Long countrecord(String fuserid,String forderId,String fspuid){
		 return iReceiveDAO.countrecord(fuserid, forderId, fspuid);
	 }
	
	//增加领取记录
	public Receive save(Receive receive) {
		return iReceiveDAO.save(receive);
	}

	@Override
	public Long countrecordAndSpuNo(String fuserid, String forderId,
			String fspuid, String fspuNo) {
		return iReceiveDAO.countrecordAndSpuNo(fuserid, forderId, fspuid, fspuNo);
	}
	
	public Receive query (String fuserid,String forderId,String fspuid,String fspuNo){
		
	   return iReceiveDAO.query(fuserid, forderId, fspuid, fspuNo);
	}
	
	 public  Long isNewOrderid(String oldorderId){
		 
		 return iReceiveDAO.isNewOrderid(oldorderId);
	 }
	
	 public  List<Receive> querynewOrderId(String forderId,String lorderId){
		 
		 return iReceiveDAO.querynewOrderId(forderId,lorderId);
	 }
	 
	 
 public  List <Receive> querydetailOrderId(String lorderId){
		 
		 return iReceiveDAO.querynewOrderId(lorderId,lorderId);
	 }
 /**
  * 查询用户某个订单某个sku总的领取的记录 
  * <br/>比如有订单有，5个sku，但是领取了3次，就会有3次记录
  * @param fuserid 用户id
  * @param forderId 订单id
  * @param fskuid skuid订单中sku编号
  * @return
  */
 public  List <Receive> queryReceivedSku(String fuserid,String forderId,String fskuid){
	 
	 return iReceiveDAO.queryReceivedSku(fuserid,forderId,fskuid);
 }
 
 
	 
	 
}
