package com.shangpin.base.service;

import java.util.List;

import com.shangpin.base.vo.ActivityOfMain;
import com.shangpin.base.vo.CapitalBrand;
import com.shangpin.base.vo.LatestProduct;
import com.shangpin.base.vo.LatestProductMore;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.base.vo.Look;
import com.shangpin.base.vo.LookForProduct;
import com.shangpin.base.vo.MallBrandList;
import com.shangpin.base.vo.MallCategory;
import com.shangpin.base.vo.Merchandise;
import com.shangpin.base.vo.Picture;
import com.shangpin.base.vo.Promotion;
import com.shangpin.base.vo.SPMerchandise;
import com.shangpin.base.vo.Topic;

/**
 * 调用主站尚品相关接口的Service
 * 
 * @author sunweiwei
 * 
 */
public interface ShangPinService {

    /**
     * 全部品牌
     * 
     * @return
     */
    public String queryBrandList();

    /**
     * 尚品专题商品列表
     * 
     * @param listOfGoodsVO
     *            请求参数
     * @return
     * @author cuibinqiang
     */
    public String findSPTopicProducts(ListOfGoods listOfGoodsVO);

    /**
     * 尚品品类新品商品列表
     * 
     * @param listOfGoodsVO
     *            请求参数
     * @return
     * @author cuibinqiang
     */
    public String findSPNewProducts(ListOfGoods listOfGoodsVO);

    /**
     * 尚品获取品类品牌商品列表
     * 
     * @param listOfGoodsVO
     *            请求参数
     * @return
     * @author cuibinqiang
     */
    public String findSPProductsAndBrand(ListOfGoods listOfGoodsVO);

    /**
     * 尚品商品详情
     * 
     * @param listOfGoodsVO
     *            请求参数
     * @return
     * @author cuibinqiang
     */
    public String findSPProductDetail(ListOfGoods listOfGoodsVO);

    /**
     * 获取CMS尚品专题商品列表
     * 
     * @param listOfGoodsVO
     *            商品列表的参数
     * @return
     * @author liujie
     */
    public String findTopicProducts(ListOfGoods listOfGoodsVO);

    /**
     * 获取尚品男士9宫格活动
     * 
     * @param listOfGoodsVO
     *            商品列表的参数
     * @return
     * @author liujie
     */
    public String findMan9Grids(ListOfGoods listOfGoodsVO);

    /**
     * 批量获取图片链接
     * 
     * @param listOfGoodsVO
     *            商品列表的参数
     * @return
     * @author liujie
     */
    public String findPicUrls(ListOfGoods listOfGoodsVO);

    /**
     * 根据开始结束时间获取尚品活动列表
     * 
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     * @author liujie
     */
    public String findSubjectList(String gender, String picw, String pich, String startTime, String endTime);

    /**
     * 获取品牌旗舰店数据
     * 
     * @param brandNo
     *            品牌编号
     * @return
     * @author liujie
     */
    public String findFlagShip(String brandNo);

    /**
     * 根据活动名称或者活动编号获取符合条件的尚品活动列表
     * 
     * @param keyWord
     *            关键词，活动名称或者活动编号
     * @return
     * @author liujie
     */
    public String findSearchSubject(String keyWord);

    /**
     * 根据性别查询品牌列表
     * 
     * @param gender
     * @return
     * @author zhanghongwei
     */
    public String findCapitalBrandList(String gender);

    /**
     * 查询以大写字母分类的品牌列表
     * 
     * @param gender性别
     *            ：0：女 1：男
     * @return
     * @author zhanghongwei
     */
    public List<CapitalBrand> findCapitalBrandListObj(String gender);

    /**
     * 品牌对应的产品列表 paramVo包含如下参数具体
     * 
     * @param brandId
     *            品牌ID
     * @param categoryId
     *            类别ID
     * @param gender
     *            性别： 0:女 1：男
     * @param userId
     *            用户ID
     * @param picw
     *            图片宽度
     * @param pich
     *            图片高度
     * @param filterSold表示是否获取已售罄的商品
     *            ，1为不获取；0、null为获取
     * @param pageNo
     *            当前页
     * @param pageSize
     *            每页显示数
     * @return
     * @author zhanghongwei
     */
    public List<Merchandise> findMerchandiseListObj(ListOfGoods paramVo);

    /**
     * 尚品品类新品商品列表
     * 
     * @param paramVo
     * @return
     * @author zhanghongwei
     */
    public List<Merchandise> findSPNewProductsObj(ListOfGoods paramVo);

    /**
     * 获取CMS尚品专题商品列表
     * 
     * @param listOfGoodsVO
     * @return
     * @author zhanghongwei
     */
    public Topic findTopicProductsObj(ListOfGoods listOfGoodsVO);

    /**
     * 尚品一级商品调用主站入口
     * 
     * @param categoryId
     * @param gender
     * @param userId
     * @return
     * @author cuibinqiang
     */
    public List<SPMerchandise> getCategoryByProducts(String categoryId, String gender, String userId);

    /**
     * 男士首页品牌调用方法
     * 
     * @param categoryId
     * @param gender
     * @param userId
     * @param pageindex
     * @param pagesize
     * @return
     * @author liujie
     */
    public List<SPMerchandise> getCategoryByBrand(String pageindex, String pagesize, String categoryId, String gender, String userId);

    /**
     * 新品首頁列表
     * 
     * @param userId
     *            用戶id
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页显示条数
     * @return
     * @author huangxiaoliang
     */
    public String findLatestProductList(String userId, String pageIndex, String pageSize);

    /**
     * 查询新品首頁列表的LatestProduct对象集合
     * 
     * @param userId
     *            用戶id
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页显示条数
     * @return
     * @author huangxiaoliang
     */
    public List<LatestProduct> findLatestProductListObj(String userId, String pageIndex, String pageSize);

    /**
     * 新品更多列表
     * 
     * @param userId
     *            用户id
     * @param brandId
     *            品牌id
     * @param sortId
     *            品牌下分类id
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页显示条数
     * @return
     * @author huangxiaoliang
     */
    public String findLatestProductMoreList(String userId, String brandId, String sortId, String pageIndex, String pageSize);

    /**
     * 查询新品更多列表的ProductMore对象
     * 
     * @param userId
     *            用户id
     * @param brandId
     *            品牌id
     * @param sortId
     *            品牌下分类id
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页显示条数
     * @return
     * @author huangxiaoliang
     */
    public LatestProductMore findLatestProductMoreObj(String userId, String brandId, String sortId, String pageIndex, String pageSize);

    /**
     * 新品品牌列表筛选
     * 
     * @param brandId
     *            品牌id
     * @return
     * @author huangxiaoliang
     */
    public String findLatestProductSortList(String brandId);

    /**
     * 商城品牌
     * 
     * @param pageIndex
     *            起始页
     * @param pageSize
     *            条数
     * @return
     * @author wangfeng
     */
    public String queryBrandList(String pageIndex, String pageSize);

    /**
     * 商城品类
     * 
     * @author wangfeng
     */
    public String queryMallCategoryList();

    /**
     * 商城推广位
     * 
     * @author wangfeng
     */
    public String queryMallPromotionList();

    /**
     * 
     * @param type
     *            1：新品顶部轮播图; 2：商城顶部轮播图
     * 
     * @author wangfeng
     */
    public String queryGalleryList(String type, String frames);
    
    public String queryGalleryList(String type, String frames, String brandId);

    /**
     * 返回轮播图对象集合
     * 
     * @param type
     * @return
     * @author huangxiaoliang
     */
    public List<Picture> findGalleryList(String type, String frames);

    /**
     * 验证V码是否与活动匹配并且该V码是否和账户绑定
     * 
     * @param vcode
     * @param subjectNo
     * @param userId
     * @return
     * @author wangfeng
     */
    public String checkVcode(String vcode, String subjectNo, String userId);

    /**
     * V码绑定到指定用户
     * 
     * @param vcode
     * @param userId
     * @return
     * @author wangfeng
     */
    public String bindVcode(String vcode, String userId);

    /**
     * 用户是否绑定活动V码接口
     * 
     * @param userId
     * @param subjectNo
     * @return
     * @author wangfeng
     */
    public String queryVcode(String userId, String subjectNo);

    /**
     * 商城品类对象
     * 
     * @return MallCategory
     * @author liling
     * 
     */
    public MallCategory findMallCategory();

    /**
     * 商城品牌对象
     * 
     * @return MallBrandList
     * @author liling
     * 
     */
    public MallBrandList findMallBrandList(String pageIndex, String pageSize);

    /**
     * 商城推广位列表
     * 
     * @return List<Promotion>
     * @author liling
     */
    public List<Promotion> findMallPromotionList();

    /**
     * 获取look列表接口
     * 
     * @return List<Look>
     * @author wangfeng
     */
    public List<Look> getLooks(String pageIndex, String pageSize);

    /**
     * 获取lookL商品列表
     * 
     * @return List<Promotion>
     * @author wangfeng
     */
    public LookForProduct getLookProducts(String id);

    /**
     * 
     * @Description: 收藏活动
     * @auther Huang Xiaoliang
     * @param userId
     *            用户id
     * @param activityId
     *            活动id
     * @param type
     *            1.尚品; 2.奥莱
     * @return
     */
    public String createCollectActivity(String userId, String activityId, String type);

    /**
     * 
     * @Title:findFashionDetail
     * @Description:尚潮流详情页接口
     * @param id
     *            编号
     * @return
     * @author liling
     * @date 2015年3月10日
     */
    public String findFashionDetail(String id);

    /**
     * 
     * findFashionList
     * 
     * @Description:尚潮流列表接口
     * @param type
     *            1(完美搭配)；2（流行趋势）；3（明星新装）；4（品牌新品）
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页记录数
     * @return
     * @author liling
     * @date 2015年3月10日
     */
    public String findFashionList(String type, String pageIndex, String pageSize);

    /**
     * 
     * @Description: 查询收藏活动列表
     * @auther Huang Xiaoliang
     * @param userId
     *            用户id
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页记录数
     * @param type
     *            1.尚品; 2.奥莱
     * @return
     */
    public String findCollectActivityList(String userId, String pageIndex, String pageSize, String type);

    /**
     * 
     * @Description: 查询收藏商品列表
     * @auther qinyingchun
     * @param userId
     *            用户id
     * @param pageIndex
     *            页码
     * @param pageSize
     *            每页记录数
     * @param type
     *            1.尚品; 2.奥莱
     * @param postArea
     *            商品来源，0全部，1国内，2海外
     * @return
     */
    public String findCollectProductList(String userId, String pageIndex, String pageSize, String type, String postArea);

    /**
     * 获取活动头部运营位
     * 
     * @return String
     * @author wangfeng
     */
    public String getTopAdver(String subjectno);

    /**
     * 查询订单物流跟踪
     * 
     * @param userId
     *            用户ID
     * @param orderId
     *            订单号
     * @param postArea
     *            1国内 2海外
     * @return
     * @author zhanghongwei
     */
    public String findOrderMoreLogistic(String userId, String orderId, String postArea, String isNew);

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
     * 按起止时间获取活动展示 说明：客户端用奥莱活动数据。按天归类，同一天开始的活动放一起，以开始时间倒序
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
     * 活动商品列表页
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
     * 
     * @Title: findBrandProductList
     * @Description:初始化品牌商品列表
     * @param start
     * @param end
     * @param brandNo
     * @param
     * @return String
     * @throws
     * @Create By qinyingchun
     * @Create Date 2014年10月27日
     */
    public String findBrandProductList(String start, String end, String brandNo);

    /**
     * 
     * @Title: findBrandProductList
     * @Description:品牌商品列表
     * @param start
     *            开始索引
     * @param num
     *            显示条数
     * @param brandNo
     *            品牌ID
     * @param categoryNo
     *            品类ID
     * @param color
     *            颜色
     * @param size
     *            尺寸
     * @param price
     *            价格
     * @param order
     *            排序
     * @param
     * @return String
     * @throws
     * @Create By qinyingchun
     * @Create Date 2014年10月30日
     */
    public String findBrandProductList(String start, String num, String brandNo, String categoryNo, String color, String size, String price, String order);

    /**
     * 根据活动名称或者活动编号获取符合条件的尚品活动列表
     * 
     * @param keyWord
     *            关键词，活动名称或者活动编号
     * @return
     * @author liling
     */
    public List<ActivityOfMain> findSearchSubjectList(String keyWord);

    /**
     * 根据开始结束时间获取尚品活动列表
     * 
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     * @author liujie
     */
    public List<ActivityOfMain> findSubjectListByTime(String startTime, String endTime);

    /**
     * 根据开始结束时间获取尚品活动列表
     * 
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     * @author liujie
     */
    public String findSubjectList(String startTime, String endTime);

    /**
     * 获取最新上架列表
     * 
     * @param pageIndex
     *            起始页
     * @param pageSize
     *            条数
     * @param origin
     *            来源
     * @return
     * @author wangfeng
     */
    public String queryNewReleases(String pageIndex, String pageSize, String origin);

    /**
     * 获取风格主题列表
     * 
     * @param pageIndex
     *            起始页
     * @param pageSize
     *            条数
     * @return
     * @author wangfeng
     */
    public String queryStyleTheme(String pageIndex, String pageSize);

    /**
     * 获取首页今日特卖
     * 
     * @param type
     *            1：最新特卖;2：即将开始
     * @param pageIndex
     *            起始页
     * @param pageSize
     *            条数
     * @return
     * @author huangxiaoliang
     */
    public String findHomeSale();

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
     * @author huangxiaoliang
     */
    public String findMoreSale(String type, String pageIndex, String pageSize);

    /**
     * 获取首页人气排行榜和今日值得买
     * 
     * @param type
     *            1：人气榜;2：值得买
     * @param pageIndex
     *            起始页
     * @param pageSize
     *            条数
     * @return
     * @author huangxiaoliang
     */
    public String findPopularityAndWorth(String type, String userId, String pageIndex, String pageSize);

    /**
     * 
     * @Title:findHeadInfo
     * @Description:品牌、活动头部信息接口
     * @param userid
     *            用户表示id，非必填
     * @param id
     *            活动、品牌id ， 必填
     * @param type
     *            类型 0表示品牌；1表示活动，必填
     * @return
     * @author qinyingchun
     * @date 2015年1月12日
     */
    public String findHeadInfo(String userid, String id, String type);

    /**
     * 
     * @Title:findCouponInfo
     * @Description:品牌、活动优惠券信息接口
     * @param userid
     *            用户表示id，非必填
     * @param id
     *            活动、品牌id ， 必填
     * @param type
     *            类型 0表示品牌；1表示活动，必填
     * @return
     * @author qinyingchun
     * @date 2015年1月12日
     */
    public String findCouponInfo(String userId, String id, String type);

    /**
     * 
     * @Title:findPromotionInfo
     * @Description:品牌、活动推广位接口
     * @param id
     *            活动、品牌id ， 必填
     * @param type
     *            类型 0表示品牌；1表示活动，必填
     * @return
     * @author qinyingchun
     * @date 2015年1月12日
     */
    public String findPromotionInfo(String id, String type);

    /**
     * 
     * @Title:findProductTemplate
     * @Description:商品获取模板信息接口(5.7)
     * @param type
     *            1：品牌风尚；2：保养及售后 必传
     * @param productId
     *            商品编号 必传
     * @return
     * @author wangfeng
     * @date 2015年3月10日
     */
    public String findProductTemplate(String type, String productId);

    /**
     * 
     * @Title:findProductTemplate
     * @Description:商品获取模板信息接口(5.7)
     * @param type
     *            1：M站 2：app 必传
     * @param pageIndex
     *            起始页 必传
     * @param pageSize
     *            页码 必传
     * @return
     * @author wangfeng
     * @date 2015年3月10日
     */
    public String findEntranceIndex(String type, String pageIndex, String pageSize);

    /**
     * 
     * @Title:findProductDetail
     * @Description:商品详情页接口(5.4)
     * @param activityId
     *            活动编号
     * @param productId
     *            商品编号
     * @param userId
     *            用户id
     * @return
     * @author wangfeng
     * @date 2015年3月10日
     */
    public String findProductDetail(String activityId, String productId, String userId, String picNo);

    /**
     * 
     * @Title:findProductDetail
     * @Description:商品详情页接口(5.4)
     * @param activityId
     *            活动编号
     * @param productId
     *            商品编号
     * @param userId
     *            用户id
     * @return
     * @author wangfeng
     * @date 2015年3月10日
     */
    public String findProductDetailNew(String activityId, String productId, String userId, String picNo);

    /**
     * 2.9.3以后商品详情页
     * 
     * @param activityId
     * @param productId
     * @param userId
     * @param picNo
     * @param isNew
     *            版本2.9.3后传 1
     * @return
     */
    public String findProductDetailNew(String activityId, String productId, String userId, String picNo, String isNew);

    /**
     * 
     * @Title:findProductDetail
     * @Description:商品详情页接口(5.3)
     * @param productId
     *            商品编号
     * @param pageIndex
     *            起始页 必传
     * @param pageSize
     *            页码 必传
     * @return
     * @author wangfeng
     * @date 2015年3月10日
     */
    public String findCommentList(String productId, String pageIndex, String pageSize, String tagId);


	/**
	 * 获取主站立即购买数据（v5.13）
	 * 
	 * @param userId
	 * @param skuId
	 * @param activityId
	 * @param amount
	 * @param region
	 * @return
	 */
	public String findBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region);
	
	String findBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region, String type);
	String findBuyNowThrid(String userId, String skuId, String productId, String activityId, String amount, String region,String chanelNo,String chanelId);
	
	String findBuyNow(String userId, String skuId, String productId, String activityId, String amount, String region, String type,String isDefaultUseCoupon);
    /**
     * 
     * @Title:findProductSize
     * @Description:商品尺码接口(5.3)
     * @param productId
     *            商品编号
     * @return
     * @author 李灵
     * @date 2015年3月16日
     */
    public String findProductSize(String productId);

    /**
     * 
     * @Title:queryCategoryOperation
     * @Description:分类运营数据接口(5.14)
     * @param id
     *            编号
     * @param userId
     *            用户id
     * @return
     * @author wangfeng
     * @date 2015年3月15日
     */
    public String queryCategoryOperation(String id, String userId);

    /**
     * 2.9.0版本以后查询分类运营数据
     * 
     * @param id
     * @param userId
     * @return
     */
    String queryCategoryOperations(String id, String userId);

    /**
     * 分类运营子分类接口（因图片尺寸兼容问题添加标识mark，区分版本。）
     * 
     * @param id
     * @param userId
     * @param mark
     * @return
     */
    public String queryCategoryOperation(String id, String userId, String mark);

    /**
     * 
     * @Title:collectProduct
     * @Description:收藏商品接口
     * @param skuId
     *            编号
     * @param userId
     *            用户id
     * @return
     * @author liling
     * @date 2015年3月18日
     */
    public String collectProduct(String shopType, String skuId, String userId, String picw, String pich, String detailPicw, String detailpich);

    /**
     * 
     * @Title:cancleCollectProduct
     * @Description:取消收藏商品接口
     * @param collectId
     *            编号
     * @param userId
     *            用户id
     * @return
     * @author liling
     * @date 2015年3月18日
     */
    public String cancleCollectProduct(String shopType, String collectId, String userId);

    /**
     * 用于展示用户自己的定制的品牌接口
     * 
     * @param userId
     * @param vuId
     *            app传imei、pc传quark_uv
     * @param num
     *            品牌数量
     * @return
     */
    String getRecBrand(String userId, String vuId, String num);

    /**
     * 用于供用户在品牌池选择自定制品牌的数据接口
     * 
     * @param userId
     * @param vuId
     * @return
     */
    String getFavBrandList(String userId, String vuId, String num);

    /**
     * 用于获取用户保存自己定制的品牌是否成功接口。
     * 
     * @param brandId
     *            多个品牌按“,”分割
     * @param userId
     * @param vuId
     * @return
     */
    String getCollectFavBrand(String brandId, String userId, String vuId);

    /**
     * 用于查询运营内容接口
     * 
     * @param pageIndex
     * @param pageSize
     * @return
     */
    String getOperation(String type, String pageIndex, String pageSize);

    /**
     * 礼品卡商品列表(6.1)
     * 
     * @param categoryNO
     * @param productId
     * @param start
     * @param end
     * @param userID
     * @param userIP
     * @param encode
     * @return
     * @author zghw
     */
    public String giftCardList(String categoryNO, String productId, String start, String end, String userID, String userIP, String encode);

    /**
     * 礼品卡记录列表(6.6)
     * 
     * @param userId
     *            用户ID
     * @param recordType
     *            礼品卡订单记录状态1.购买记录 2.充值记录 3.消费记录
     * @param pageIndex
     *            当前页
     * @param pageSize
     *            每页条数
     * @return
     * @author zghw
     */
    public String giftCardRecordList(String userId, String recordType, String pageIndex, String pageSize);

    /**
     * 礼品卡电子卡充值接口(6.5)
     * 
     * @param userId
     *            用户ID
     * @param cardPasswd
     *            礼品卡号
     * @param orderId
     *            订单号
     * @return
     * @author zghw
     */
    public String giftCardElectronicRecharge(String userId, String orderId, String cardPasswd);

    /**
     * 礼品卡立即购买(6.3)
     * 
     * @param userId用户ID
     * @param skuIdSKU
     * @param productId产品ID
     * @param amount购买数量
     * @return
     * @author zghw
     */
    public String giftCardBuy(String userId, String skuId, String productId, String quantity, String categoryNo, String type, String eGiftCardAmount);

    /**
     * 提交订单去结算(6.4)
     * 
     * @param userId用户ID
     * @param addressId收货地址ID
     * @param invoiceFlag是否开发票
     * @param invoiceAddressId发票包含的收货地址
     * @param invoiceType发票类型0
     *            ：个人 1公司
     * @param invoiceTitle发票抬头
     * @param invoiceContent发票内容
     * @param express配送方式
     * @param orderFrom订单来源
     * @param buysIds购物车ID
     * @param payTypeId主支付方式编号
     * @param payTypeChildId子支付方式编号
     * @param orderType订单来源站点
     * @param type
     *            1.实物卡 2电子卡
     * @return
     * @author zghw
     */
    public String giftCardCommit(String userId, String addressId, String invoiceFlag, String invoiceAddressId, String invoiceType, String invoiceTitle, String invoiceContent,
            String express, String orderFrom, String buysIds, String payTypeId, String payTypeChildId, String orderType, String type,
                                 String invoiceEmail,String invoiceTel);

    /**
     * 礼品卡--获取电子卡充值密码
     * 
     * @param userId
     *            用户ID
     * @param orderId
     *            订单号
     * @return
     * @author zghw
     */
    public String giftCardRechargePasswd(String userId, String orderId);

    /**
     * 查询账户礼品卡状态
     * 
     * @param userId
     * @return
     * @author zghw
     */
    public String giftCardStatus(String userId);

    /**
     * 礼品卡实物卡充值
     * 
     * @param userId用户ID
     * @param cardNo
     *            卡号
     * @param passwd
     *            卡密
     * @return
     * @author zghw
     */
    public String giftCardEntityRecharge(String userId, String cardNo, String cardPwd);

    /**
     * 品牌收藏列表
     * 
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String collectBrandList(String userId, String pageIndex, String pageSize);

    /**
     * 上传图片到主站图片服务器
     * 
     * @param extension
     *            图片格式
     * @param picturetype
     *            图片类型
     * @param picFileContent
     *            图片内容，是byte数组经过base64编码之后的字符串
     * @return
     */
    public String uploadPic(String extension, String picturetype, String picFileContent);

    /**
     * 获得新品到货
     * 
     * @param userId
     * @return
     */
    String findNewGoods(String userId);

    /**
     * 获得首页新品到货
     * 
     * @param userId
     * @return
     */
    String findFirstNewGoods(String userId);

    /**
     * 获取首页广告接口
     * 
     * @param pageSize
     * @param pageIndex
     * @return
     */
    String getHeadAdverts(String pageIndex, String pageSize);

    /**
     * 获取2.8.2首页新品到货
     * 
     * @return
     */
    String findHeadNewGoods();

    /**
     * 获取首页广告模板接口
     * 
     * @param pageSize
     * @param pageIndex
     * @return
     */
    String getHeadAdverts();

    /**
     * 获得首页标签
     * 
     * @param pageIndex
     * @param pageSize
     * @return
     */
    String getLabel(String pageIndex, String pageSize);

    /**
     * 获得首页标签
     * 
     * @param pageIndex
     * @param pageSize
     * @param type
     *            1:新版
     * @return
     */
    String getLabel(String pageIndex, String pageSize, String type);

    /**
     * 获取商品的库存量
     * 
     * @param productNo
     * @return
     */
    public String productCount(String productNo);

    /**
     * 
     * @Title:findModelInfo
     * @Description:品牌、活动模板广告
     * @param id
     *            活动、品牌id ， 必填
     * @param type
     *            类型 2表示品牌；1表示活动，必填
     * @return
     * @author 
     * @date 2015年11月6日18:34:10
     */
    public String findModelInfo(String id, String type);
    
    /**
     * 首页弹窗
     * 
     * @param pageIndex
     * @param pageSize
     * @return
     */
    String getShellWindow(String pageIndex, String pageSize);

    
    /** 获取底部导航栏图标 */
    String getBottomNavigatePic(String plateForm);

    public String LockSku(String supplierNo, String orderNo,
            String orderItemList) throws Exception;
    
    public String getOrderLockSupplierInfo(String orderNo) throws Exception;

    public String stockAbnormalSyncZero(String warehouseNo, String supplierNo,
            String formNo, String operateUser, String skuDetailDtos) throws Exception;
    
    /**
     * 根据礼品卡卡号获取秘钥
     * @param userId
     * @param giftCardId
     * @return
     */
    public String giftCardRechargePasswdByCardId(String userId,String giftCardId);
    
    /**
     *  保存数据到数据库
     * @param userId 用户id
     * @param giftOrder 礼品卡订单号
     * @param giftCardId 礼品卡id
     * @param buyerName 购买者名称
     * @param sendPhoneNum 发送给人的手机号
     * @param sendTime 发送时间
     * @param wishMsg 祝福语句
     * @param wishPic 祝福图片url
     * @return
     */
    public String saveGiftCardToDb(String userId,String giftOrder, String giftCardId,String buyerName,String sendPhoneNum,String sendTime,String wishMsg,String wishPic);

    /**
     * 更新数据库中的礼品卡信息
     * @param userId 充值用户id
     * @param giftCardId 礼品卡id
     * @param recgargeTime 充值成功时间
     * @return
     */
    public String updateGiftCardToDb(String userId, String giftCardId,String recgargeTime);
    
    /**
     * 获取礼品卡充值状态 0为未充值  1为已充值
     * @param giftCardId
     * @return
     */
    public String getGiftCardStatusByGiftCardId(String giftCardId); 
    public String cardStatus(String cardNo);
    
    /**
     * 从主站获取活动楼层信息
     * @param topicId 活动Id
     * @return
     */
	public String subjectFloorInfo(String topicId);
    /***
     * 
     * @param phoneNum
     * @param actityId
     * @param time
     * @return
     */
    public String activityStartRemind(String phoneNum, String actityId, String time);
}