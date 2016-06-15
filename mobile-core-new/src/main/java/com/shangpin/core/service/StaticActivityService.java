package com.shangpin.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.StaticActivity;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;

public interface StaticActivityService {
    List<StaticActivity> find(Page page, String name);

    void update(StaticActivity user);

    void save(StaticActivity user) throws ExistedException;

    StaticActivity get(Long id);

    void delete(Long id) throws ServiceException;

    List<StaticActivity> findAll(Page page);

    List<StaticActivity> findByExample(Specification<StaticActivity> specification, Page page);

    List<StaticActivity> findStaticActivities(Date date, String keywords, Page page);

}
