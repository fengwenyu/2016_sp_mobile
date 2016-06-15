package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IFashionPackDAO;
import com.shangpin.core.entity.FashionPack;
import com.shangpin.core.service.IFashionPackService;

@Service
@Transactional
public class FashionPackServiceImpl implements IFashionPackService {
	@Autowired
	private IFashionPackDAO fashionPackDAO;

	@Override
	public FashionPack findById(Long id) {
		return fashionPackDAO.findOne(id);
	}

	@Override
	public FashionPack findByPackType(String packType) {
		List<FashionPack> list=fashionPackDAO.findByPackType(packType);
		if(list==null||list.size()==0){
			return null;
		}
		return list.get(0);
	}

}
