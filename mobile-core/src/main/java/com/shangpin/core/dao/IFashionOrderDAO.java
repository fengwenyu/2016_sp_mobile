package com.shangpin.core.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.FashionOrder;

public interface IFashionOrderDAO extends JpaRepository<FashionOrder, Long>,JpaSpecificationExecutor<FashionOrder> {
	@Query("select count(*)  from FashionOrder f where ?1!=status")
	Long countNotStatus(String status);
	@Query("select f from FashionOrder f where ?1=orderId")
	List<FashionOrder> findByOrderId(String orderId);
	@Query("select f from FashionOrder f where ?1=phone")
	List<FashionOrder> findByPhone(String phone);
/*	SELECT STATUS,COUNT(*) FROM fashion_order f GROUP BY STATUS HAVING f.status!='001';*/
	@Query("SELECT f.status,count(*) from FashionOrder f where f.status!='001' GROUP BY f.status")
	List<Object[]> findOrderSuccessCount();
}
