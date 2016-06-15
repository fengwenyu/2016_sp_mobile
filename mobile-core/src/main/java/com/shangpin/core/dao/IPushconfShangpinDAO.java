package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.PushconfShangpin;

public interface IPushconfShangpinDAO extends JpaRepository<PushconfShangpin, Long>, JpaSpecificationExecutor<PushconfShangpin> {

    public PushconfShangpin findByUserId(String userId);

    public List<PushconfShangpin> findByToken(String token);
}