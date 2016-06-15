package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.Receive;


/**
 * 领取相关业务
 * @author 李永桥
 *
 */
public interface IReceiveService {

	public Long countrecord(String fuserid,String forderId,String fspuid);
	
	public Long countrecordAndSpuNo(String fuserid,String forderId,String fspuid,String FspuNo);
	
	
	public Receive query (String fuserid,String forderId,String fspuid,String FspuNo);
	
	 // 保存实体
    public Receive save(Receive receive);
    
    
    
     public  Long isNewOrderid(String oldorderId);
     
     
     public  List<Receive> querynewOrderId(String forderId,String lorderId); 
     
     
     public  List <Receive> querydetailOrderId(String lorderId); 
     
     /**
      * 查询用户某个订单某个sku总的领取的记录 
      * <br/>比如有订单有，5个sku，但是领取了3次，就会有3次记录
      * @param fuserid 用户id
      * @param forderId 订单id
      * @param fskuid skuid订单中sku编号
      * @return
      */
     public  List <Receive> queryReceivedSku(String fuserid,String forderId,String fskuid);
}
