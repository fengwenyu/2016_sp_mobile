package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.Channel;
import com.shangpin.core.util.dwz.Page;

/**
 * 渠道管理的服务
 * 
 * @author cuibinqiang
 * 
 */
public interface ChannelService {

    // 添加
    void save(Channel channel);

    // 修改
    void update(Channel channel);

    // 删除
    void delete(Long id);

    // 组合条件查询列表
    List<Channel> findByExample(Specification<Channel> specification, Page page);

    // 查询最大的渠道编码
    Long findMaxCode();

    // 通过ID查询
    Channel findById(Long id);
    
    //通过上线产品的渠道编号查询渠道名称
    String findChannelNameByNum(Long num);
}
