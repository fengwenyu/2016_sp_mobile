package com.shangpin.core.service;


import java.util.List;

import com.shangpin.core.entity.FashionPackExt;


public interface IFashionPackExtService {
	public FashionPackExt findById(Long id);

	public List<FashionPackExt> findAll();

	public List<Object[]> findPackExtSellCount();

}
