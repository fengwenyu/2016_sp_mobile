package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IHotBrandsDAO;
import com.shangpin.core.entity.HotBrands;
import com.shangpin.core.service.IHotBrandsService;

@Service
@Transactional
public class HotBrandsServiceImpl implements IHotBrandsService {

    @Autowired
    private IHotBrandsDAO hotBrandsDAO;
    
    @Override
    public HotBrands addHotBrands(HotBrands hotBrands) {
        HotBrands list=this.hotBrandsDAO.save(hotBrands);
        return list;
    }

    @Override
    public HotBrands modifyHotBrands(HotBrands hotBrands) {
        HotBrands list=this.hotBrandsDAO.save(hotBrands);
        return list;
    }

    @Override
    public HotBrands findHotBrandsById(Long id) {
        HotBrands list=this.hotBrandsDAO.findOne(id);
        return list;
    }

    @Override
    public void deleteHotBrandsById(Long id) {
        this.hotBrandsDAO.delete(id);

    }

    @Override
    public List<HotBrands> findHotBrandsList() {
        List<HotBrands> list=this.hotBrandsDAO.findHotBrandsList();
        return list;
    }

	@Override
	public HotBrands findHotBrands(String brandNo) {
		HotBrands hotBrands = this.hotBrandsDAO.findByBrandId(brandNo);
		return hotBrands;
	}

}
