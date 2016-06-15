package com.shangpin.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.Token;

public interface ITokenDAO extends JpaRepository<Token, Long>, JpaSpecificationExecutor<Token> {

    @Query("select bean from Token bean where bean.status = ?1")
    public List<Token> findByStatus(String status);

    @Query("select bean from Token bean where bean.code = ?1 and unix_timestamp(efftime) > unix_timestamp(now())")
    public List<Token> findByCodeAndEfftimeGtNow(String code);
}