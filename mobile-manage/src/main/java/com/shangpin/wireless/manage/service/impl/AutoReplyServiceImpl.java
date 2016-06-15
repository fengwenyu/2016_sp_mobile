package com.shangpin.wireless.manage.service.impl;

import com.shangpin.wireless.dao.AutoReplyDao;
import com.shangpin.wireless.domain.AutoReply;
import com.shangpin.wireless.manage.service.AutoReplyService;
import com.shangpin.wireless.util.HqlHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zrs on 2015/12/4.
 */
@Service(AutoReplyService.SERVICE_NAME)
public class AutoReplyServiceImpl implements AutoReplyService {

    @Resource(name = AutoReplyDao.DAO_NAME)
    private AutoReplyDao autoReplyDao;


    public List<AutoReply> findAll() throws Exception {

        return autoReplyDao.findAll();
    }

    public AutoReply getByCondition(HqlHelper hqlHelper) throws Exception{
        return autoReplyDao.getByCondition(hqlHelper);
    }

    public List<AutoReply> getListByCondition(HqlHelper hqlHelper) throws Exception {
        return autoReplyDao.getListByCondition(hqlHelper);
    }

    public void save(AutoReply autoReply) throws Exception{
        autoReplyDao.save(autoReply);
    }

    public void update(AutoReply autoReply) throws Exception{
        autoReplyDao.update(autoReply);
    }

    public AutoReply getById(Long id) throws Exception{
        return autoReplyDao.getById(id);
    }

    public void deletebyId(Long id) throws Exception{
        autoReplyDao.delete(id);
    }



}
