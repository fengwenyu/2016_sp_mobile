package com.shangpin.biz.bo;

import java.util.Comparator;
/**
 * 礼品卡排序
 * @author zghw
 *
 */
public class ComparatorGiftCardProductListItem implements Comparator<GiftCardProductListItem> {

	@Override
	public int compare(GiftCardProductListItem item1, GiftCardProductListItem item2) {
		/**
		 * 按价格高低排序
		 */
		Double item1Price=Double.valueOf(item1.getMarketPrice());
		Double item2Price=Double.valueOf(item2.getMarketPrice());
		int flag =item2Price.compareTo(item1Price);
		return flag;
	}

}
