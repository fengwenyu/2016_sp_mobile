package com.shangpin.core.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IPushManageIosDAO;
import com.shangpin.core.entity.PushManageIos;
import com.shangpin.core.service.IPushManageIosService;

@Service
@Transactional
public class PushManageIosServiceImpl implements IPushManageIosService {

    @Autowired
    private IPushManageIosDAO dao;

    @Override
    public PushManageIos add(PushManageIos pushManageIos) {
        return dao.save(pushManageIos);
    }

    @Override
    public PushManageIos modify(PushManageIos pushManageIos) {
        return dao.save(pushManageIos);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public PushManageIos findById(Long id) {
        return dao.findOne(id);
    }

	@Override
	public List<PushManageIos> findByDictIds(Set<Long> dictIds, int pageIndex, int pageSize) {
		return dao.findByPushTypeAndDictIdIn(1, dictIds, new PageRequest(pageIndex -1, pageSize, new Sort(Direction.DESC, "showTime")));
	}

}
