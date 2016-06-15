package com.shangpin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IFashionPackExtDAO;
import com.shangpin.core.entity.FashionPackExt;
import com.shangpin.core.service.IFashionPackExtService;

@Service
@Transactional
public class FashionPackExtServiceImpl implements IFashionPackExtService {
	@Autowired
	private IFashionPackExtDAO fashionPackExtDAO;

	@Override
	public FashionPackExt findById(Long id) {
		return fashionPackExtDAO.findOne(id);
	}

	@Override
	public List<FashionPackExt> findAll() {
		return fashionPackExtDAO.findAll();
	}

	@Override
	public List<Object[]> findPackExtSellCount() {
		// TODO Auto-generated method stub
		return fashionPackExtDAO.findPackExtSellCount();
	}


}
