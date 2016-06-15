package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Fashion;
import com.shangpin.biz.bo.FashionDetail;
import com.shangpin.biz.bo.SiteType;

public interface SPBizFashionService {

    public List<Fashion> findFashionList(String type, String pageIndex, String pageSize);

    public FashionDetail findFashionDetail(String id, SiteType siteType);

    public FashionDetail findFashionDetail(String id, SiteType siteType, boolean flag);

}
