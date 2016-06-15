package com.shangpin.biz.service.basic;

import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultObjMapList;
/**
 * 发票地址基础接口
 * 
 * @author zghw
 *
 */
public interface IBizInvoiceService {
	/**
	 * 新增发票地址
	 * 
	 * @param userId
	 *            用户ID
	 * @param address
	 *            发票地址
	 * @return
	 * @author zghw
	 */
	public String fromAddInvoiceAddress(String userId, Address address);

	/**
	 * 新增发票地址
	 * 
	 * @param userId
	 *            用户ID
	 * @param address
	 *            发票地址
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<Address> beAddInvoiceAddress(String userId, Address address);

	/**
	 * 修改发票地址
	 * 
	 * @param invoiceId发票ID
	 * @param userId
	 *            用户ID
	 * @param address
	 *            发票地址
	 * @return
	 * @author zghw
	 */
	public String fromModifyInvoiceAddress(String invoiceId, String userId, Address address);

	/**
	 * 修改发票地址
	 * 
	 * @param invoiceId发票ID
	 * @param userId
	 *            用户ID
	 * @param address
	 *            发票地址
	 * @return
	 * @author zghw
	 */
	public ResultObjMapList<Address> beModifyInvoiceAddress(String invoiceId, String userId, Address address);
	
	/**
	 * 删除发票地址
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author zghw
	 */
	public String fromDeleteInvoiceAddress(String userId, String addrId);
	
	/**
	 * 删除发票地址
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author zghw
	 */
	public ResultBase beDeleteInvoiceAddress(String userId, String addrId);
	/**
	 * 返回发票地址
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @return json串
	 * @author zhanghongwei
	 */
	public String fromInvoiceAddress(String userId);

	/**
	 * 返回发票地址列表对象
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * @return
	 * @author zhanghongwei
	 */
	public ResultObjMapList<Address> beInvoiceAddress(String userId);
	
}
