package com.shangpin.core.service;

import com.shangpin.core.entity.FashionPack;


public interface IFashionPackService {
	public FashionPack findById(Long id);

	public FashionPack findByPackType(String string);
}
