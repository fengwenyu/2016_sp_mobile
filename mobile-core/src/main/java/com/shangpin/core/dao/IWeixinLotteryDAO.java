package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.WeixinLottery;

public interface IWeixinLotteryDAO extends JpaRepository<WeixinLottery, Long>, JpaSpecificationExecutor<WeixinLottery> {

}