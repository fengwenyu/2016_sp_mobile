package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.PushconfAolai;

public interface IPushconfAolaiDAO extends JpaRepository<PushconfAolai, Long>, JpaSpecificationExecutor<PushconfAolai> {

    public PushconfAolai findByUserId(String userId);

    public List<PushconfAolai> findByToken(String token);
}