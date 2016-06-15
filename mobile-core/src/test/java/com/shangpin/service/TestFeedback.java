package com.shangpin.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shangpin.BaseTest;
import com.shangpin.core.entity.Feedback;
import com.shangpin.core.service.IFeedbackService;


public class TestFeedback  extends BaseTest{

    @Autowired
    IFeedbackService feedbackService;
    
    @Test
    public void testAddFeedback(){
        Feedback bean =new Feedback();
        bean.setId(43L);
        bean.setCreateTime(new Date());
        bean.setId(43L);
        bean.setMsg("1222222");
        bean.setPhone("111111");
        bean.setPhoneModel("1111111");
        bean.setProduct(11111L);
        Feedback feedback=feedbackService.addFeedback(bean);
        Assert.notNull(feedback.getId());
    }
    
    @Test
    public void testFindFeedbackById(){
        Feedback bean=feedbackService.findFeedbackById(43L);
        Assert.notNull(bean.getId());
    }
    
    @Test
    public void deleteFeedbackById(){
        feedbackService.deleteFeedbackById(43L);
    }
}
