package com.shangpin.biz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangpin.biz.bo.Address;
import com.shangpin.biz.bo.base.ResultObjMapList;
import com.shangpin.biz.service.SPBizInvoiceService;
import com.shangpin.biz.service.abstraction.AbstractBizInvoiceService;

/**
 * @ClassName: BizInvoiceServiceImpl
 * @Description:发票地址实现类
 * @author qinyingchun
 * @date 2014年11月14日
 * @version 1.0
 */
@Service
public class SPBizInvoiceServiceImpl extends AbstractBizInvoiceService implements SPBizInvoiceService {

	// private static final Logger logger =
	// LoggerFactory.getLogger(SPBizInvoiceServiceImpl.class);

	@Override
	public boolean isAddInvoice(String userId, Address address) {
		try {
			ResultObjMapList<Address> obj = beAddInvoiceAddress(userId, address);
			if (!StringUtils.isEmpty(obj)) {
				return obj.isSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isModify(String invoiceId, String userId, Address address) {
		try {
			ResultObjMapList<Address> obj = beModifyInvoiceAddress(invoiceId, userId, address);
			if (!StringUtils.isEmpty(obj)) {
				return obj.isSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Address findAddressByListObj(String userId, String addrId) {
		ResultObjMapList<Address> obj = this.beInvoiceAddress(userId);
		if (!StringUtils.isEmpty(obj)) {
			List<Address> list = obj.getList("list");
			if (list != null) {
				for (Address address : list) {
					if (addrId.equals(address.getId())) {
						return address;
					}
				}
			}
		}
		return null;
	}
}
