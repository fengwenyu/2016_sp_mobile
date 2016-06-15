package com.shangpin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.ProductDAO;
import com.shangpin.core.entity.main.Product;
import com.shangpin.core.service.ProductService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

/** 
 *  
 * @author liujie
 * @version  2.1.0
 * @since   2014-8-6 下午14:10
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDAO productDAO;

    @Override
    public void save(Product product) {
        productDAO.save(product);
    }

    @Override
    public void update(Product product) {
        productDAO.save(product);

    }

    @Override
    public void delete(Long id) {
        productDAO.delete(id);

    }

    @Override
    public List<Product> findAll(Specification<Product> specification,Page page) {
        org.springframework.data.domain.Page<Product> springDataPage = productDAO.findAll(specification,PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public Product get(Long id) {
        return productDAO.findOne(id);
    }

    @Override
    public int findMax() {
        return productDAO.findMax();
    }
    //通过上线管理的产品编号查询产品名称
    @Override
    public String findProductNameByNum(int num){
        return productDAO.findProductNameByNum(num);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }
    
    

}
