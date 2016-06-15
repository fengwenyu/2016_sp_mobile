package com.shangpin.biz.service;

import com.shangpin.biz.bo.ProductTemplate;
import com.shangpin.biz.bo.SiteType;

public interface SPBizProductTemplateService {
	public ProductTemplate findProductTemplate(String type, String productId,SiteType siteType);
}
