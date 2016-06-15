package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.AppPic;
import com.shangpin.core.util.dwz.Page;

/**
 * app上传的图片信息业务接口
 * 
 * @author zhanghongwei
 * 
 */
public interface AppPicService {

    public void save(AppPic appPic);

    public void update(AppPic appPic);

    public void delete(Long id);

    public AppPic get(Long id);

    public List<AppPic> findAll(Specification<AppPic> spec, Page page);

}