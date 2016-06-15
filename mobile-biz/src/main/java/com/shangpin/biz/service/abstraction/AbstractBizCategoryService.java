package com.shangpin.biz.service.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.SearchService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.CategoryItem;
import com.shangpin.biz.bo.CategoryOperationItem;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JSONUtils;

public abstract class AbstractBizCategoryService {
    public static Logger logger = LoggerFactory
            .getLogger(AbstractBizCategoryService.class);
    @Autowired
    SearchService searchService;
    @Autowired
    ShangPinService shangpinService;
    /**
     * 获取品类
     * 
     * @param parentId
     * @param type
     * @return
     * @author liling
     */
    public CategoryItem fromQueryCategory(String parentId, String type) {
        try {
            String json = searchService.querySearchCategory(parentId, type);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                ResultObjOne<CategoryItem> obj = JSONUtils
                        .toGenericsCollection(json, ResultObjOne.class,
                                CategoryItem.class);
                String code = obj.getCode();
                if (!Constants.SUCCESS.equals(code) || null == obj) {
                    return null;
                }
                return obj.getObj();
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取品类运营内容 shangpin<2.9.4>
     * 
     * @param id 品类id
     * @param userId 用户Id
     * @return
     * @author wh
     */
    public CategoryOperationItem fromQueryCategoryOperation(String id, String userId) {
        try {
            String json = shangpinService.queryCategoryOperation(id, userId);
            logger.debug("调用base接口返回数据:" + json);
            if (!StringUtils.isEmpty(json)) {
                ResultObjOne<CategoryOperationItem> obj = JSONUtils
                        .toGenericsCollection(json, ResultObjOne.class,
                                CategoryOperationItem.class);
                String code = obj.getCode();
                if (!Constants.SUCCESS.equals(code) || null == obj) {
                    return null;
                }
                return obj.getObj();
            }
        } catch (Exception e) {
            logger.error("调用base接口返回数据发生错误！");
            e.printStackTrace();
        }
        return null;
    }
	/**
	 * 获取品类
	 * 
	 * @param parentId
	 * @param type
	 * @return
	 * @author liling
	 */
	public CategoryItem fromQueryCategorys(String parentId, String type) {
		try {
			String json = searchService.queryCategorys(parentId, type);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<CategoryItem> obj = JSONUtils
						.toGenericsCollection(json, ResultObjOne.class,
								CategoryItem.class);
				String code = obj.getCode();
				if (!Constants.SUCCESS.equals(code) || null == obj) {
					return null;
				}
				return obj.getObj();
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取品类运营内容shangpin<2.9.4>
	 * 
	 * @param id 品类id
	 * @param userId 用户Id
	 * @return
	 * @author wh
	 */
	public CategoryOperationItem fromQueryCategoryOperations(String id, String userId) {
		try {
			String json = shangpinService.queryCategoryOperations(id, userId);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<CategoryOperationItem> obj = JSONUtils
						.toGenericsCollection(json, ResultObjOne.class,
								CategoryOperationItem.class);
				String code = obj.getCode();
				if (!Constants.SUCCESS.equals(code) || null == obj) {
					return null;
				}
				return obj.getObj();
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
}
