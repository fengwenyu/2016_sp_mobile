package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.Feedback;

public interface IFeedbackDAO  extends JpaRepository<Feedback, Long>, JpaSpecificationExecutor<Feedback>  {

}
