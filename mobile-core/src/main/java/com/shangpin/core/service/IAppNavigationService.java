package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.AppNavigation;

/**
 * 
 * @author cuibinqiang
 * 
 */
public interface IAppNavigationService {

    // 添加实体
    public AppNavigation save(AppNavigation appNavigation);

    // 根据ID查询实体
    public AppNavigation findById(Long id);

    // 更新实体
    public AppNavigation modify(AppNavigation appNavigation);

    // 删除实体
    public void delete(Long id);

    // 查询列表
    public List<AppNavigation> findAll();
}
