package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shangpin.biz.bo.Fashion;
import com.shangpin.biz.bo.FashionDetail;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.service.SPBizFashionService;
import com.shangpin.biz.service.abstraction.AbstractBizFashionService;
import com.shangpin.biz.utils.HtmlUtil;
import com.shangpin.biz.utils.StringUtil;

@Service
public class SPBizFashionServiceImpl extends AbstractBizFashionService implements SPBizFashionService {
	@Override
	public List<Fashion> findFashionList(String type, String pageIndex, String pageSize) {
		List<Fashion> fashionList = new ArrayList<Fashion>();
		fashionList = fromFindFashionList(type, pageIndex, pageSize);
		if (fashionList == null) {
			return null;
		}
		return fashionList;
	}

	@Override
	public FashionDetail findFashionDetail(String id,SiteType siteType) {
		FashionDetail fashionDetail = new FashionDetail();
		fashionDetail = fromFindFashionDetail(id);
		if (fashionDetail == null) {
			return null;
		}
		String type="1";
		if (siteType.equals(SiteType.IOS_AOLAI) || siteType.equals(SiteType.IOS_SHANGPIN)) {
			type="3";
		}
		if (siteType.equals(SiteType.ANDRIOD_AOLAI) || siteType.equals(SiteType.ANDRIOD_SHANGPIN)) {
			type="2";
		}
		String article=fashionDetail.getArticle();
		if(StringUtil.isNotEmpty(article)){
			fashionDetail.setArticle(HtmlUtil.modifyFashionDetail(fashionDetail.getArticle(), type));
		}
		return fashionDetail;
	}

    @Override
    public FashionDetail findFashionDetail(String id, SiteType siteType, boolean flag) {
        FashionDetail fashionDetail = new FashionDetail();
        fashionDetail = fromFindFashionDetail(id);
        if (fashionDetail == null) {
            return null;
        }
        String type="1";
        if (siteType.equals(SiteType.IOS_AOLAI) || siteType.equals(SiteType.IOS_SHANGPIN)) {
            type="3";
        }
        if (siteType.equals(SiteType.ANDRIOD_AOLAI) || siteType.equals(SiteType.ANDRIOD_SHANGPIN)) {
            type="2";
        }
        String article=fashionDetail.getArticle();
        if(StringUtil.isNotEmpty(article)){
            if(flag){
                fashionDetail.setArticle(HtmlUtil.modifyFashionDetail(fashionDetail.getArticle(), type));
            }else{
                fashionDetail.setArticle(HtmlUtil.modifyOldFashionDetail(fashionDetail.getArticle(), type));
            }
        }
        return fashionDetail;
    }
}
