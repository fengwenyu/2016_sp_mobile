package com.shangpin.biz.service.impl;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.ProductSize;
import com.shangpin.biz.service.SPBizProductSizeService;
import com.shangpin.biz.service.abstraction.AbstractBizProductSizeService;

/**
 * @ClassName: SPProductServiceImpl
 * @Description:商品尺码接口实现类
 * @author liling
 * @date 2015年3月16日
 * @version 1.0
 */
@Service
public class SPBizProductSizeServiceImpl extends AbstractBizProductSizeService implements SPBizProductSizeService {
	@Override
	public ProductSize findProductSize(String productId) {
		ProductSize productSize = new ProductSize();
		productSize = fromFindProductSize(productId);
		if (productSize == null) {
			return null;
		}
		return productSize;

	}
}
