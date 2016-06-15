package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.ApiFashionInfo;
import com.shangpin.core.util.dwz.Page;

/**
 * 微信潮流资讯服务
 * 
 * @author cuibinqiang
 * 
 */
public interface FashionInfoService {

    // 添加
    void save(ApiFashionInfo apiFashionInfo);

    // 修改
    void update(ApiFashionInfo apiFashionInfo);

    // 删除
    void delete(Long id);

    // 组合条件查询列表
    List<ApiFashionInfo> findByExample(Specification<ApiFashionInfo> specification, Page page);

    // 通过ID查询
    ApiFashionInfo findById(Long id);
}
