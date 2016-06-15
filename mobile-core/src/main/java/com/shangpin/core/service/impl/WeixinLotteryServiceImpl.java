package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeixinLotteryDAO;
import com.shangpin.core.entity.WeixinLottery;
import com.shangpin.core.service.IWeixinLotteryService;

@Service
@Transactional
public class WeixinLotteryServiceImpl implements IWeixinLotteryService {

    @Autowired
    private IWeixinLotteryDAO dao;

    @Override
    public WeixinLottery add(WeixinLottery weixinLottery) {
        return dao.save(weixinLottery);
    }

    @Override
    public WeixinLottery modify(WeixinLottery weixinLottery) {
        return dao.save(weixinLottery);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public WeixinLottery findById(Long id) {
        return dao.findOne(id);
    }

}
