package com.shangpin.biz.service;

import com.shangpin.biz.bo.Red;


/**
 * 红包业务处理
 * @author 李永桥
 *
 */
public interface RedBizService{
   
   //校验红包是否存在
    public Boolean checkisred(String keywords);

    //红包返回信息
    public Red findRedList(String keywords);
}
