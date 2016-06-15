package com.shangpin.core.service;

import java.util.Date;
import java.util.List;

import com.shangpin.core.entity.AolaiActivity;

/**
 * 获取奥莱活动
 * 
 * @author cuibinqiang
 * 
 */
public interface IAolaiActivityService {

    // 添加实体
    public AolaiActivity save(AolaiActivity aolaiActivity);

    // 根据ID查询实体
    public AolaiActivity findById(Long id);

    // 更新实体
    public AolaiActivity modify(AolaiActivity aolaiActivity);

    // 删除实体
    public void delete(Long id);

    // 查询列表
    public List<AolaiActivity> findAolaiActivity(Date date,int display);
}
