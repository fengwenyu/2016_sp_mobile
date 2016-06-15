package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.AppPictures;

/**
 * 
 * @author cuibinqiang
 *
 */
public interface IAppPicturesService {

    //查询列表
    public List<AppPictures> findAll();

    //按条件查询列表
    public List<AppPictures> findByCondition(String osType,Integer productType);

    //增加实体
    public AppPictures save(AppPictures appPictures);

    //根据ID查询实体
    public AppPictures findById(Long id);

    //修改实体
    public AppPictures modify(AppPictures appPictures);

    //根据ID删除实体
    public void delete(Long id);

}
