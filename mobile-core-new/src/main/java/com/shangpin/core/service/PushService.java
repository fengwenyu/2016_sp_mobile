package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.Push;
import com.shangpin.core.util.dwz.Page;

public interface PushService {
    
    public List<Push> findAll(Page page);
    
    public List<Push> findByExample(Specification<Push> specification, Page page);
    
    public Push get(Long id);
    
    public void save(Push push);
    
    public void update(Push push);
    
    public void delete(Long id);

    public void updateAndSendIOSPersonPushInfo(int productCode);

    public void updateAndSendIOSPushInfo(int productCode, int gender);
    
    public List<Push> findSendPushs(int productCode, int platformType, int status);
    
    public List<Push> findSendPushs(int productCode, int platformType, int gender, int status);
    
    public List<String> tokens(final int productCode, int gender, int pageNum, int pageSize);
    

}
