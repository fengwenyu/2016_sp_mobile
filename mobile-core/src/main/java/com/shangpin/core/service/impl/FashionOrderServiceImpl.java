package com.shangpin.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IFashionOrderDAO;
import com.shangpin.core.dao.IFashionPackExtDAO;
import com.shangpin.core.entity.FashionOrder;
import com.shangpin.core.entity.FashionPackExt;
import com.shangpin.core.service.IFashionOrderService;

@Service
@Transactional
public class FashionOrderServiceImpl implements IFashionOrderService {
	public static final Logger logger = LoggerFactory.getLogger(FashionOrderServiceImpl.class);
	@Autowired
	private IFashionOrderDAO fashionOrderDAO;
	@Autowired
	private IFashionPackExtDAO fashionPackExtDAO;

	@Override
	public FashionOrder saveOrUpdate(FashionOrder fashionOrder) {

		return this.fashionOrderDAO.save(fashionOrder);
	}
	
	@Override
	public FashionPackExt findFashionPackExtBySize(String size) {
		 return fashionPackExtDAO.findBySize(size);
	}

	/*
	 * @Override public FashionOrder update(FashionOrder fashionOrder) { String
	 * size=fashionOrder.getSize(); fashionPackExtDAO.update(size); return
	 * this.fashionOrderDAO.save(fashionOrder); }
	 */
	@Override
	public boolean updateFashionPackExt(FashionPackExt fashionPackExt) {
		if(fashionPackExt == null) return false;
		int sellCount = fashionPackExt.getSellCount();
		int stock = fashionPackExt.getStock();
		fashionPackExt.setSellCount(sellCount+1);
		fashionPackExt.setStock(stock-1);
		this.fashionPackExtDAO.save(fashionPackExt);
		return true;
	}
	
	@Override
	public FashionOrder updateFashionOrder(FashionOrder fashionOrder) {
		if(fashionOrder==null)return null;
		return this.fashionOrderDAO.save(fashionOrder);
	}


	@Override
	public FashionOrder findById(Long id) {
		return this.fashionOrderDAO.findOne(id);
	}

	@Override
	public void delete(Long id) {
		this.fashionOrderDAO.delete(id);

	}

	@Override
	public List<FashionOrder> findByConditions(
			Specification<FashionOrder> specification, Sort sort) {
		return fashionOrderDAO.findAll(specification, sort);
	}

	@Override
	public Long countNotStatus(String status) {
		return fashionOrderDAO.countNotStatus(status);
	}

	@Override
	public FashionOrder findByOrderId(String orderId) {
		return this.fashionOrderDAO.findByOrderId(orderId).get(0);
	}

	@Override
	public List<FashionOrder> findByPhone(String phone) {
		return fashionOrderDAO.findByPhone(phone);
	}

	@Override
	public List<Object[]> findOrderSuccessCount() {
		return fashionOrderDAO.findOrderSuccessCount();
	}

}
