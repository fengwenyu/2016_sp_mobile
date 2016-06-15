package com.shangpin.biz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.Look;
import com.shangpin.base.vo.LookForProduct;
import com.shangpin.biz.common.page.Page;
import com.shangpin.biz.service.SPBizStyleService;
import com.shangpin.biz.service.abstraction.AbstractBizUserService;
@Service
public class SPBizStyleServiceImpl extends AbstractBizUserService implements SPBizStyleService{
	public static Logger logger = LoggerFactory.getLogger(SPBizStyleServiceImpl.class);
	@Autowired
	private ShangPinService shangPinService;
	@Override
	public LookForProduct getLookProducts(String id) {
		 LookForProduct products=shangPinService.getLookProducts(id);
	     return products;
	}

	@Override
	public List<Look> getLooks(String pageIndex) {
	    List<Look> list=shangPinService.getLooks(pageIndex, String.valueOf(Page.DEFAULT_PAGE_SIZE));
        return list;
	}

}
