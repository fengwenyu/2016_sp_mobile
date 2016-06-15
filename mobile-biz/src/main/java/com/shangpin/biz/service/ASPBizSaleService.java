package com.shangpin.biz.service;


import java.util.List;

import com.shangpin.biz.bo.Sale;

/**
 * 尚品客户端api的热卖接口
 * 
 * @author huangxiaoliang
 *
 */
public interface ASPBizSaleService {
	/**
	 * 获得热卖集合
	 * 
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * 
	 * @author huangxiaoliang
	 */
	public List<Sale> queryHomeSale();
	
	/**
     * 获取首页更多特卖
     * 
     * @param type
     *            1：今日新开2：昨日上新3：最后疯抢
     * @param pageIndex
     *            起始页
     * @param pageSize
     *            条数
     * @return
     * @throws Exception
     * 
     * @author huangxiaoliang
     */
    public String getMoreSale(String type, String pageIndex, String pageSize) throws Exception;
    public List<Sale> getHotSale(String type, String pageIndex, String pageSize) throws Exception;
}
