package com.shangpin.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IWxFashionInformationDAO;
import com.shangpin.core.entity.WxFashionInformation;
import com.shangpin.core.service.IFashionService;
@Service
@Transactional
public class FashionServiceImpl implements IFashionService{
	  @Autowired
	   IWxFashionInformationDAO wxFashionInformationDAO;

	    @Override
	    public List<WxFashionInformation> findFashionList(Date date) {
	        List<WxFashionInformation> list = this.wxFashionInformationDAO.findFashionList(date);
	        return list;
	    }

		@Override
		public WxFashionInformation findByFashionId(Long id) {
			WxFashionInformation fashion=this.wxFashionInformationDAO.findOne(id);
			return fashion;
		}
}
