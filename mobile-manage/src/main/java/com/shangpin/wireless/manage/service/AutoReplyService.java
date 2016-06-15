package com.shangpin.wireless.manage.service;

import com.shangpin.wireless.domain.AutoReply;
import com.shangpin.wireless.util.HqlHelper;

import java.util.List;

/**
 * Created by cuibinqiang on 2015/12/4.
 */
public interface AutoReplyService {

    public final static String SERVICE_NAME = "com.shangpin.wireless.manage.service.impl.AppNavigationServiceImpl";

    public List<AutoReply> findAll() throws Exception ;

    public AutoReply getByCondition(HqlHelper hqlHelper) throws Exception;

    public List<AutoReply> getListByCondition(HqlHelper hqlHelper) throws Exception ;

    public void save(AutoReply autoReply) throws Exception;

    public void update(AutoReply autoReply) throws Exception;

    public AutoReply getById(Long id) throws Exception;

    public void deletebyId(Long id) throws Exception;




}
