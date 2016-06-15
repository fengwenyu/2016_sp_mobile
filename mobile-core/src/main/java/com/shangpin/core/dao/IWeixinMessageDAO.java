package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.WeixinMessage;

public interface IWeixinMessageDAO extends JpaRepository<WeixinMessage, Long>, JpaSpecificationExecutor<WeixinMessage> {

}