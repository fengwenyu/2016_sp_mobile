package com.shangpin.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shangpin.core.dao.IErrorLogDao;
import com.shangpin.core.entity.ErrorLog;
import com.shangpin.core.service.IErrorLogService;
/**
 * 错误日志管理
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-07-31
 */
@Service
@Transactional
public class ErrorLogServiceImpl implements IErrorLogService {
    @Autowired
	private IErrorLogDao errorLogDao;
	
    @Override
    public Page<ErrorLog> findByConditins(Pageable pageable) {
        return this.errorLogDao.findByConditins(pageable);
    }
    
    @Override
    public long findCount() {
        return this.errorLogDao.count();
    }
    
    @Override
    public  ErrorLog addErrorLog(ErrorLog errorLog){
        return this.errorLogDao.save(errorLog);
    }
    
    @Override
    public  ErrorLog findErrorLogById(Long id){
        return this.errorLogDao.findOne(id);
    }
}
