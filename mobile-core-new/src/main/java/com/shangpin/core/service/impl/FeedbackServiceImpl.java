package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.FeedbackDAO;
import com.shangpin.core.entity.main.Feedback;
import com.shangpin.core.service.FeedbackService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/**
 * 反馈信息服务接口实现
 * 
 * @author zhanghongwei
 *
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    public void save(Feedback feedback) {
        feedbackDAO.save(feedback);
    }

    @Override
    public void update(Feedback feedback) {
        feedbackDAO.save(feedback);
    }

    @Override
    public void delete(Long id) {

        feedbackDAO.delete(id);
    }

    @Override
    public Feedback get(Long id) {
        return feedbackDAO.findOne(id);
    }

    @Override
    public List<Feedback> findAll(Page page) {
        org.springframework.data.domain.Page<Feedback> springDataPage = feedbackDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<Feedback> findAll(Specification<Feedback> spec, Page page) {
        org.springframework.data.domain.Page<Feedback> springDataPage = feedbackDAO.findAll(spec, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

}
