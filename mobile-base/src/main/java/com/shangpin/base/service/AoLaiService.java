package com.shangpin.base.service;

import com.shangpin.base.vo.ListOfGoods;

/**
 * 调用主站奥莱相关接口的Service
 * 
 * @author sunweiwei
 * 
 */
public interface AoLaiService {
	/**
	 * 奥莱广告频道
	 * @param channelNo
	 * @param picw
	 * @param pich
	 * @return
	 * @throws Exception
	 */
	public String findAoLaiADList(String channelNo, String picw, String pich) throws Exception;
	/**
	 * 奥莱首页信息
	 * @param type
	 * @param picw
	 * @param pich
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public String findAolaiIndex(String type, String picw, String pich, String pageIndex, String pageSize) throws Exception;
	
	/**
	 * 奥莱活动分组时间
	 * @return
	 * @throws Exception
	 */
	public String findGroupTime(String picw, String pich) throws Exception;
    /**
     * 奥莱按起止时间获取活动展示 说明：客户端用奥莱活动数据。按天归类，同一天开始的活动放一起，以开始时间倒序
     * 
     * @param gender
     *            性别 ： 1为男 0为女
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @return
     * @author zhanghongwei
     */
    public String findSubjectListByPeriod(String gender, String startTime, String endTime, String picw, String pich);

    /**
     * 奥莱iPhone用专题
     * 
     * @param gender
     *            性别 ： 1为男 0为女
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @return
     * @author zhanghongwei
     */
    public String findTopicListForiPhone(String gender, String picw, String pich);

    /**
     * 奥莱专题商品列表页面
     * 
     * @param topicId
     *            专题ID
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @param pageIndex
     *            当前页
     * @param pageSize
     *            每页条数 pageindex为空时同时忽略此参数
     * @return
     * @author zhanghongwei
     */
    public String findTopicList(String topicId, String picw, String pich, String pageIndex, String pageSize);

    /**
     * 奥莱活动商品列表页
     * 
     * @param subjectNo
     *            活动ID
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @param pageIndex
     *            当前页
     * @param pageSize
     *            每页条数 pageindex为空时同时忽略此参数
     * @return
     * @author zhanghongwei
     */
    public String findSubjectProductList(String subjectNo, String picw, String pich, String pageIndex, String pageSize);

    /**
     * 奥莱商品详细页面
     * 
     * @param goodId
     *            商品ID
     * @param type
     *            标识来自专题列表还是活动列表，从活动商品列表中跳进去（type=1）的和从专题商品列表进入的（type=0）
     * @param categoryNo
     *            活动子类编号
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @return
     * @author zhanghongwei
     */
    public String findProductDetail(String goodId, String type, String categoryNo, String picw, String pich);

    /**
     * 奥莱获取所有未结束和今天即将开始的活动 （包括开始时间是今天(含)之前，并且为未结束的活动。以开始时间倒序排列，最晚开始的在前面）
     * 
     * @param gender
     *            性别 ： 1为男 0为女
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @return
     * @author zhanghongwei
     */
    public String findTodaySubjectList(String gender, String picw, String pich);

    /**
     * 奥莱获取所有即将结束的活动 （包括结束时间离当前时间小于24小时的活动。以结束时间正序排列，最早结束的在前面）
     * 
     * @param gender
     *            性别 ： 1为男 0为女
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @return
     * @author zhanghongwei
     */
    public String findEndingSubjectList(String gender, String picw, String pich);

    /**
     * 奥莱获取频道中活动列表
     * 
     * @param listOfGoodsVO
     *            商品列表的参数
     * @return
     * @author liujie
     */
    public String findChannelActivity(ListOfGoods listOfGoodsVO);

    /**
     * 奥莱Mobile网站用专题
     * 
     * @param listOfGoodsVO
     *            商品列表的参数
     * @return
     * @author liujie
     */
    public String findMobileTopicList(ListOfGoods listOfGoodsVO);

    /**
     * 奥莱获取频道
     * 
     * @return
     * @author cuibinqiang
     */
    public String findChannel();
    
    /**
     *活动商品列表
     * 
     * @author wangfeng
     */
    public String querySubjectProductList(String sizeNo,String pageIndex,String pageSize,String subjectNo,String isHaveStock,String pich,String picw,String orderType,String categoryNo); 
       
    /**
     * 奥莱活动列表筛选尺码列表
     * 
     * @return
     * @author wangfeng
     */
    public String queryCategorySizeList(String subjectNo);
    
    /**
     * 查询用户购买信息
     * @param userId 用户ID
     * @param shopType 1代表尚品 2代表 奥莱
     * @return
     * @author zhanghongwei
     */
    public String findUserBuyInfo(String userId, String shopType);
}
