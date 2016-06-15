package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeixinPayOrderDAO;
import com.shangpin.core.entity.WeixinPayOrder;
import com.shangpin.core.service.IWeixinPayOrderService;

@Service
@Transactional
public class WeixinPayOrderServiceImpl implements IWeixinPayOrderService {

    @Autowired
    private IWeixinPayOrderDAO dao;

    @Override
    public WeixinPayOrder add(WeixinPayOrder weixinPayOrder) {
        return dao.save(weixinPayOrder);
    }

    @Override
    public WeixinPayOrder modify(WeixinPayOrder weixinPayOrder) {
        return dao.save(weixinPayOrder);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public WeixinPayOrder findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public WeixinPayOrder findByOrderNo(String orderNo) {
        return dao.findByOrderNo(orderNo);
    }

    @Override
    public WeixinPayOrder findByTransId(String transId) {
        return dao.findByTransId(transId);
    }

}
