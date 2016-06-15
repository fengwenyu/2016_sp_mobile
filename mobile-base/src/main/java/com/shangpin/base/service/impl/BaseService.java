package com.shangpin.base.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseService {

    /**
     * 生成参数传递的Map
     * 
     * @return
     */
    protected Map<String, String> genParamMap() {
        return new LinkedHashMap<String, String>();
    }
    
}
