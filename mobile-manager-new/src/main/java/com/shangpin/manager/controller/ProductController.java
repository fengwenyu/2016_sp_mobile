package com.shangpin.manager.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.Product;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.ProductService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;

@Controller
@RequestMapping("/management/manager/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    private static final String LIST = "management/admin/product/list";
    private static final String CREATE = "management/admin/product/create";
    private static final String UPDATE = "management/admin/product/update";
    
    
    @RequiresPermissions("Product:save")
    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {
       int productNum= productService.findMax();
       productNum++;
       map.put("productNum", productNum);
        return CREATE;
    }
    
    
    @Log(message="添加了id={0}产品。")
    @RequiresPermissions("Product:save")
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public @ResponseBody String create(@Valid Product product) {
        product.setCreateTime(new Date());
        try {
            productService.save(product);
        } catch (ExistedException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{product.getId()}));
        return AjaxObject.newOk("产品添加成功！").toString();
    }
    
    @RequiresPermissions("Product:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST })
    public String list(Page page,ServletRequest request, Map<String, Object> map){
        Specification<Product> spec = DynamicSpecifications.bySearchFilter(request, Product.class);
        List<Product>  products = productService.findAll(spec, page);
        map.put("page", page);
        map.put("products", products);
        return LIST;
    }
    
    @RequiresPermissions("Product:edit")
    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        Product product = productService.get(id);
        map.put("product", product);
        return UPDATE;
    }
    
    @ModelAttribute("preloadProduct")
    public Product getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Product user = productService.get(id);
            return user;
        }
        return null;
    }
    
    @RequiresPermissions("Product:edit")
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public @ResponseBody String update(@Valid @ModelAttribute("preloadProduct")Product product) {
        productService.update(product);
        
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{product.getId()}));
        return  AjaxObject.newOk("修改产品信息！").toString(); 
    }
    
    
    
    @RequiresPermissions("Product:delete")
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String deleteMany(Long[] ids) {
        AjaxObject ajaxObject = new AjaxObject("删除产品成功！");
        for (Long id : ids) {
            productService.delete(id);
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{ids.length}));
        ajaxObject.setCallbackType("");
        return ajaxObject.toString();
    }

}
