package com.shangpin.core.service;

import com.shangpin.core.entity.WeixinMessage;

public interface IWeixinMessageService {

    /**
     * 增加实体对象
     * 
     * @param weixinMessage
     * @return
     * @author zhanghongwei
     */
    public WeixinMessage add(WeixinMessage weixinMessage);

    /**
     * 更新实体对象
     * 
     * @param weixinMessage
     * @return
     * @author zhanghongwei
     */
    public WeixinMessage modify(WeixinMessage weixinMessage);

    /**
     * 删除实体对象
     * 
     * @param id
     * @author zhanghongwei
     */
    public void deleteById(Long id);

    /**
     * 根据ID查询实体对象
     * 
     * @param id
     * @return
     * @author zhanghongwei
     */
    public WeixinMessage findById(Long id);

}
