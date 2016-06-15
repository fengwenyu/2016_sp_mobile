package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.main.Channel;

public interface ChannelDAO extends JpaRepository<Channel, Long>, JpaSpecificationExecutor<Channel>{

    @Query("select max(channelNum) from Channel")
    Long findMaxChannelNo();
    
    @Query("select a.channelName from Channel a where a.channelNum=?1")
    String findChannelNameByNum(Long num);//通过上线产品的渠道编号查询渠道名称
}
