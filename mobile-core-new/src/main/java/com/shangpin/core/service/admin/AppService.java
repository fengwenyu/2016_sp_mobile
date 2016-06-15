package com.shangpin.core.service.admin;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.admin.App;
import com.shangpin.core.util.dwz.Page;

public interface AppService {
    
    public void save(App app);

    public void update(App app);

    public void delete(Long id);

    public App findById(Long id);
    
    public Long findMaxNum();

    public List<App> find(Page page, String name);
    
    public List<App> findByExample(Specification<App> specification, Page page);
    
    public List<App> findAll(Page page);
}
