package com.shangpin.biz.service;

import com.shangpin.biz.bo.Address;
import com.shangpin.biz.service.basic.IBizInvoiceService;

/**
 * @ClassName: BizInvoiceService
 * @Description:发票接口
 * @author qinyingchun
 * @date 2014年11月14日
 * @version 1.0
 */
public interface SPBizInvoiceService extends IBizInvoiceService {
	/**
	 * 
	 * @Title: isAddInvoice
	 * @Description:新增发票地址是否成功
	 * @param
	 * @return boolean
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月14日
	 */
	public boolean isAddInvoice(String userId, Address address);

	/**
	 * 
	 * @Title: isModify
	 * @Description:编辑发票地址
	 * @param
	 * @return boolean
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年11月14日
	 */
	public boolean isModify(String invoiceId, String userId, Address address);
	
	/**
	 * 设定根据地址id获取发票地址信息
	 * 
	 * @param userId
	 *            用户的唯一标识
	 * 
	 * @parm addrid 用户收货地址的唯一标识
	 * 
	 * @return
	 * @author zhanghongwei
	 */
	public Address findAddressByListObj(String userId, String addrId);

}
