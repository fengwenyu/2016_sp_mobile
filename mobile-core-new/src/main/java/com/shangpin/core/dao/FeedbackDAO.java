package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.main.Feedback;

/**
 * 反馈信息DAO接口
 * 
 * @author zhanghongwei
 *
 */
public interface FeedbackDAO extends JpaRepository<Feedback, Long>, JpaSpecificationExecutor<Feedback> {

}
