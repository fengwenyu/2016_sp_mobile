package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.main.Product;

/** 
 *  
 * @author liujie
 * @version  2.1.0
 * @since   2014-8-6 下午14:05
 */
public interface ProductDAO extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select max(productNum) from Product a")
    public int findMax();
    

    @Query("select a.productName from Product a where a.productNum=?1")
    public String findProductNameByNum(int num);
}
