package com.shangpin.biz.service;

import java.util.List;
import java.util.Map;

import com.shangpin.biz.bo.ReceiveRequest;
import com.shangpin.core.entity.Receive;


public interface SPBizReceiveService {

  /**
   * 领取服务
   * receive:<br/>
   * (TODO 描述这个方法的作用). <br/>
   * @param request
   * @return
   */
  public Map<String, Object> pickFreebie(ReceiveRequest request);
  
    /**
     * 判断SKU是否可领取
     * isPicked:<br/>
     * (TODO 描述这个方法的作用). <br/>
     * @param orderId
     * @param skuId
     * @return
     */
	public String isPicked(String fuserid, String forderid,String fspuid,String fspuno);
	
	
	
	/**
	 * 传入订单号 去检索是不是撞衫订单
	 * isNeworder:<br/>
	 * (TODO 描述这个方法的作用). <br/>
	 * @param forderid
	 * @param lorderid
	 * @return
	 */
	public List <Receive> isNeworder(String forderid,String lorderid);
	
	
	public List <Receive> isorderdetail(String lorderid);
	
	/**
	 * 获取订单sku领取的序号 
	 * <br/>
	 * @param fuserid 分享出去的用户id
	 * @param forderid 分享出去的订单id
	 * @param fskuid 分享出去的skuid 
	 * @return sku领取的序号
	 */
	public List<String> receivedSkuSeq(String fuserid, String forderid,String fskuid);

}
