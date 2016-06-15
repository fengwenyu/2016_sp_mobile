package com.shangpin.core.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.shangpin.core.entity.main.Product;
import com.shangpin.core.util.dwz.Page;

public interface ProductService {
    
    void save(Product product);
    
    void update(Product product);
    
    void delete(Long id);
    
    Product get(Long id);
    
    List<Product> findAll(Specification<Product> specification, Page page);
    
    int findMax();
    
    String findProductNameByNum(int num);
    
    List<Product> findAll();

}
