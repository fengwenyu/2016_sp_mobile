package com.shangpin.biz.service;

import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.basic.IBizInvoiceService;

/**
 * @ClassName: BizInvoiceService
 * @Description:发票接口
 * @author qinyingchun
 * @date 2014年11月14日
 * @version 1.0
 */
public interface ALBizInvoiceService extends IBizInvoiceService {
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
	 * @Title: invoices
	 * @Description: 新增发票地址返回发票地址列表
	 * @param
	 * @return List<Address>
	 * @throws
	 * @Create By qinyingchun
	 * @Create Date 2014年12月1日
	 */
	public ResultObjMapList<Address> invoices(String userId, Address address);

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

}
