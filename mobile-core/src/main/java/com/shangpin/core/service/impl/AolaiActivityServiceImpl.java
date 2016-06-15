package com.shangpin.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IAolaiActivityDAO;
import com.shangpin.core.entity.AolaiActivity;
import com.shangpin.core.service.IAolaiActivityService;

@Service
@Transactional
public class AolaiActivityServiceImpl implements IAolaiActivityService {

    @Autowired
    private IAolaiActivityDAO aolaiActivityDAO;

    @Override
    public AolaiActivity save(AolaiActivity aolaiActivity) {
        return aolaiActivityDAO.save(aolaiActivity);
    }

    @Override
    public AolaiActivity findById(Long id) {
        return aolaiActivityDAO.findOne(id);
    }

    @Override
    public AolaiActivity modify(AolaiActivity aolaiActivity) {
        return aolaiActivityDAO.save(aolaiActivity);
    }

    @Override
    public void delete(Long id) {
        aolaiActivityDAO.delete(id);
    }

    @Override
    public List<AolaiActivity> findAolaiActivity(Date date,int display) {
        return aolaiActivityDAO.findByCondition(date,display); 
    }

}
