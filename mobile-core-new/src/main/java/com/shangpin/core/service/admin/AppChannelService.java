package com.shangpin.core.service.admin;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.admin.AppChannel;
import com.shangpin.core.util.dwz.Page;

public interface AppChannelService {

    public void save(AppChannel appChannel);

    public void update(AppChannel appChannel);

    public void delete(Long id);

    public AppChannel findById(Long id);
    
    public Long findMaxNum();

    public List<AppChannel> find(Page page, String name);
    
    public List<AppChannel> findByExample(Specification<AppChannel> specification, Page page);
    
    public List<AppChannel> findAll(Page page);
}
