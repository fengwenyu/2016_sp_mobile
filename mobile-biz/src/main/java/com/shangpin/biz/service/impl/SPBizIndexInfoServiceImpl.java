package com.shangpin.biz.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.AdvertNewTitle;
import com.shangpin.biz.bo.AdvertTitle;
import com.shangpin.biz.bo.AppFirstIndex;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.CommonRules;
import com.shangpin.biz.bo.ModelTitle;
import com.shangpin.biz.bo.NewGoodsTitle;
import com.shangpin.biz.bo.ReleasesSPActivity;
import com.shangpin.biz.bo.RevealLabel;
import com.shangpin.biz.bo.WorthTitle;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizIndexInfoService;
import com.shangpin.biz.service.abstraction.AbstractBizIndexInfoService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.utils.JSONUtils;

@Service
public class SPBizIndexInfoServiceImpl extends AbstractBizIndexInfoService implements SPBizIndexInfoService {

    @Override
	public String getAppFirstIndex(HttpServletRequest request) throws Exception {
		AppFirstIndex appFirstIndex = null;

		final String p = request.getHeader("p");
		final String version = request.getHeader("ver");
		final String userId = request.getHeader("userid");

		if ("102".equals(p) || "2".equals(p)) {
			appFirstIndex = new AppFirstIndex();
			if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.0", version) == 1) {
				CommonRules moreFashion = getFashion();
				appFirstIndex.setGallery(queryGallery("3", "6"));
				appFirstIndex.setAdvert(doBaseAdvertTitle("2","1", "3"));
				appFirstIndex.setAdvertNew(doBaseAdvertNewTitle());
				appFirstIndex.setEntrance(doBaseEntranceTitle("2", "1", "10"));
				appFirstIndex.setReleases(doBaseReleaseTitle("1", "3", ""));
				appFirstIndex.setWorth(doBaseWorthTitle("2", userId, "1", "6"));
				appFirstIndex.setFashion(doBaseFashionTitle("0", "1", "12"));
				appFirstIndex.setMoreFashion(moreFashion);
				appFirstIndex.setNewGoods(doBaseNewGoodsTitle());
				appFirstIndex.setOperation(doBaseAdvertTitle("1","1", "1"));
			}
		}
		
		if (appFirstIndex != null) {
			
			return ApiBizData.spliceData(appFirstIndex, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}
		
		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}
	@Override
	public RevealLabel getRevealLabel(String pageIndex, String pageSize) {
		RevealLabel revealLabel = null;
		try {
			String json = getBaseLabel(pageIndex,pageSize);
			if (StringUtils.isNotEmpty(json)) {
				ResultObjOne<RevealLabel> obj = JSONUtils.toGenericsCollection(json, ResultObjOne.class, RevealLabel.class);
				if (obj.getObj() != null) {
					revealLabel = obj.getObj();
				}

			}
		} catch (Exception e) {
			logger.error("标签调用base接口返回数据发生错误！" + e);
			e.printStackTrace();
		}
		return revealLabel;
	}
	@Override
	public AdvertTitle getAdvertTitle(String type, String pageIndex,
			String pageSize) throws Exception {
		return doBaseAdvertTitle(type,pageIndex, pageSize);
	}
	@Override
	public AdvertNewTitle getAdvertNewTitle() throws Exception {
		return doBaseAdvertNewTitle();
	}
	@Override
	public ReleasesSPActivity getReleaseTitle(String pageIndex,
			String pageSize, String origin) throws Exception {
		return doBaseReleaseTitle(pageIndex,pageSize,origin);
	}
	@Override
	public NewGoodsTitle getNewGoodsTitle() throws Exception {
		return doBaseNewGoodsTitle();
	}
	@Override
	public WorthTitle getWorthTitle(String type, String userId,
			String pageIndex, String pageSize) throws Exception {
		return doBaseWorthTitle(type,  userId,
				pageIndex, pageSize);
	}
    @Override
    public ModelTitle findModelInfo() throws Exception {
        return doBaseModelTitle();
    }
}
