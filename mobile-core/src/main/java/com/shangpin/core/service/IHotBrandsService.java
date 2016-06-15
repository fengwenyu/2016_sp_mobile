package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.HotBrands;

public interface IHotBrandsService {

	public HotBrands addHotBrands(HotBrands hotBrands);

	public HotBrands modifyHotBrands(HotBrands hotBrands);

	public HotBrands findHotBrandsById(Long id);

	public void deleteHotBrandsById(Long id);

	public List<HotBrands> findHotBrandsList();

	public HotBrands findHotBrands(String brandNo);

}
