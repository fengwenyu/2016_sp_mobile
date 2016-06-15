package com.shangpin.core.service;

import com.shangpin.core.entity.Feedback;

public interface IFeedbackService {
    
    public Feedback addFeedback(Feedback feedback);
    
    public Feedback findFeedbackById(Long id);
    
    public void deleteFeedbackById(Long id);

}
