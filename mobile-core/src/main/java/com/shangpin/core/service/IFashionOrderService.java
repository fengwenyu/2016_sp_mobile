package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.FashionOrder;
import com.shangpin.core.entity.FashionPackExt;

public interface IFashionOrderService {

/*	public FashionOrder addOrder(FashionOrder fashionOrder);*/
	public FashionOrder saveOrUpdate(FashionOrder fashionOrder);
	public FashionOrder findById(Long id);

	public void delete(Long id);

	public List<FashionOrder> findByConditions(Specification<FashionOrder> specification,Sort sort);

	public Long countNotStatus(String status);
	
	public FashionOrder findByOrderId(String orderId) ;
	
	public List<FashionOrder> findByPhone(String phone);
	
	public boolean updateFashionPackExt(FashionPackExt fashionPackExt);
	
	public FashionOrder updateFashionOrder(FashionOrder fashionOrder);
	
	public FashionPackExt findFashionPackExtBySize(String size);
	
	public List<Object[]> findOrderSuccessCount();
}
