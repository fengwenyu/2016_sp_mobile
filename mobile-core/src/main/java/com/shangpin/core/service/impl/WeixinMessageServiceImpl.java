package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWeixinMessageDAO;
import com.shangpin.core.entity.WeixinMessage;
import com.shangpin.core.service.IWeixinMessageService;

@Service
@Transactional
public class WeixinMessageServiceImpl implements IWeixinMessageService {

    @Autowired
    private IWeixinMessageDAO dao;

    @Override
    public WeixinMessage add(WeixinMessage weixinMessage) {
        return dao.save(weixinMessage);
    }

    @Override
    public WeixinMessage modify(WeixinMessage weixinMessage) {
        return dao.save(weixinMessage);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public WeixinMessage findById(Long id) {
        return dao.findOne(id);
    }

}
