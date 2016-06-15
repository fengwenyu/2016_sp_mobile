package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IFeedbackDAO;
import com.shangpin.core.entity.Feedback;
import com.shangpin.core.service.IFeedbackService;


@Service
@Transactional
public class FeedbackServiceImpl implements IFeedbackService {
    
    
    @Autowired
    private IFeedbackDAO feedbackDAO;
    
    @Override
    public Feedback addFeedback(Feedback feedback) {
        return this.feedbackDAO.save(feedback);
    }
    
    @Override
    public Feedback findFeedbackById(Long id) {
        return this.feedbackDAO.findOne(id);
    }
    
    @Override
    public void deleteFeedbackById(Long id){
        this.feedbackDAO.delete(id);
    }

}
