package com.shangpin.biz.service.abstraction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ResultObjOne;
import com.shangpin.biz.bo.DynamicObj;
import com.shangpin.biz.bo.Fashion;
import com.shangpin.biz.bo.FashionDetail;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;

public abstract class AbstractBizFashionService {
	public static Logger logger = LoggerFactory.getLogger(AbstractBizEntranceService.class);
	@Autowired
	ShangPinService shangPinService;

	/**
	 * 尚潮流列表
	 * 
	 * @param type
	 *            1(完美搭配)；2（流行趋势）；3（明星新装）；4（品牌新品）
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数量
	 * @return
	 * @author liling
	 */
	public List<Fashion> fromFindFashionList(String type, String pageIndex, String pageSize) {
		try {
			String json = shangPinService.findFashionList(type, pageIndex, pageSize);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<DynamicObj<Fashion>> resultObjMapList = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<DynamicObj<Fashion>>>() {
				});
				String code = resultObjMapList.getCode();
				if (!Constants.SUCCESS.equals(code) || null == resultObjMapList) {
					return null;
				}
				List<Fashion> EntranceList = resultObjMapList.getContent().getList();
				return EntranceList;
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 尚潮流详情
	 * 
	 * @param id
	 *            id号
	 * @return
	 * @author liling
	 */
	public FashionDetail fromFindFashionDetail(String id) {
		try {
			String json = shangPinService.findFashionDetail(id);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				ResultObjOne<FashionDetail> obj = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<FashionDetail>>() {
				});
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
