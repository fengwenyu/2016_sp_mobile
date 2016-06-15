package com.shangpin.core.service.admin;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.admin.AppDownload;
import com.shangpin.core.util.dwz.Page;

public interface AppDownloadService {
    public void save(AppDownload appDownload);

    public void update(AppDownload appDownload);

    public void delete(Long id);

    public AppDownload findById(Long id);

    public List<AppDownload> find(Page page, String name);
    
    public List<AppDownload> findByExample(Specification<AppDownload> specification, Page page) ;

    public List<AppDownload> findAll(Page page);
}
