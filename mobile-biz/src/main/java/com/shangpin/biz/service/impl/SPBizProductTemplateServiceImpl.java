package com.shangpin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.ProductTemplate;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.service.SPBizProductTemplateService;
import com.shangpin.biz.service.abstraction.AbstractBizProductTemplateService;
import com.shangpin.biz.utils.HtmlUtil;
import com.shangpin.biz.utils.StringUtil;

/**
 * @ClassName: SPBizProductTemplateServiceImpl
 * @Description:商品模板实现类
 * @author liling
 * @date 2015年3月16日
 * @version 1.0
 */
@Service
public class SPBizProductTemplateServiceImpl extends AbstractBizProductTemplateService implements SPBizProductTemplateService{
	
	@Override
	public ProductTemplate findProductTemplate(String type,String productId,SiteType siteType) {
		ProductTemplate productTemplate = new ProductTemplate();
		productTemplate = fromFindProductTemplates(type,productId);
		if (productTemplate == null) {
			return null;
		}
		String sysType="1";
		if (siteType.equals(SiteType.IOS_AOLAI) || siteType.equals(SiteType.IOS_SHANGPIN)) {
			sysType="3";
		}
		if (siteType.equals(SiteType.ANDRIOD_AOLAI) || siteType.equals(SiteType.ANDRIOD_SHANGPIN)) {
			sysType="2";
		}
		String html=productTemplate.getHtml();
		if(StringUtil.isNotEmpty(html)){
			productTemplate.setHtml(HtmlUtil.modifyFashionDetail(html, sysType));
		}
		return productTemplate;

	}

}
