package com.shangpin.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shangpin.core.entity.ErrorLog;
/**
 * 错误日志管理
 * 
 * @Author: liujie
 * @CreatDate: 2014-06-30
 */
public interface IErrorLogService {
    
    public Page<ErrorLog> findByConditins(Pageable pageable);
    
    public long findCount();
    
    public ErrorLog addErrorLog(ErrorLog errorLog);
    
    public ErrorLog findErrorLogById(Long id);
}
