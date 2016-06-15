package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.WeixinPayOrder;

public interface IWeixinPayOrderDAO extends JpaRepository<WeixinPayOrder, Long>, JpaSpecificationExecutor<WeixinPayOrder> {

    @Query("select bean from WeixinPayOrder bean where bean.orderNo = ?1")
    public WeixinPayOrder findByOrderNo(String orderNo);

    @Query("select bean from WeixinPayOrder bean where bean.transId = ?1")
    public WeixinPayOrder findByTransId(String transId);
}