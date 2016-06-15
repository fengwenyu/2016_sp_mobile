package com.shangpin.core.service;

import java.util.List;
import java.util.Set;

import com.shangpin.core.entity.PushManageIos;

public interface IPushManageIosService {

    /**
     * 增加实体对象
     * 
     * @param pushManageIos
     * @return
     * @author zhanghongwei
     */
    public PushManageIos add(PushManageIos pushManageIos);

    /**
     * 更新实体对象
     * 
     * @param pushManageIos
     * @return
     * @author zhanghongwei
     */
    public PushManageIos modify(PushManageIos pushManageIos);

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
    public PushManageIos findById(Long id);
    
    /**
     * 查询发送push消息集合
     * @param dictIds
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<PushManageIos> findByDictIds(Set<Long> dictIds, int pageIndex, int pageSize);

}
