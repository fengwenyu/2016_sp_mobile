package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.ChannelDAO;
import com.shangpin.core.entity.main.Channel;
import com.shangpin.core.service.ChannelService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;

    @Override
    public void save(Channel channel) {
        channelDAO.save(channel);
    }

    @Override
    public void update(Channel channel) {
        channelDAO.save(channel);
    }

    @Override
    public void delete(Long id) {
        channelDAO.delete(id);
    }

    @Override
    public List<Channel> findByExample(Specification<Channel> specification, Page page) {
        org.springframework.data.domain.Page<Channel> springDataPage = channelDAO.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public Long findMaxCode() {
        Long maxChannelNo = channelDAO.findMaxChannelNo();
        return maxChannelNo;
    }

    @Override
    public Channel findById(Long id) {
        return channelDAO.findOne(id);
    }
    
    //通过上线产品的渠道编号查询渠道名称
    @Override
    public String findChannelNameByNum(Long num){
        return channelDAO.findChannelNameByNum(num);
    }

}
