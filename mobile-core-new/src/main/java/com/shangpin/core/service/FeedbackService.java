package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.Feedback;
import com.shangpin.core.util.dwz.Page;

/**
 * 反馈信息服务接口
 * 
 * @author zhanghongwei
 *
 */
public interface FeedbackService {

    public void save(Feedback feedback);

    public void update(Feedback feedback);

    public void delete(Long id);

    public Feedback get(Long id);

    public List<Feedback> findAll(Page page);

    public List<Feedback> findAll(Specification<Feedback> spec, Page page);

}
