package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.PushManageAndroid;

public interface IPushManageAndroidService {

    /**
     * 增加实体对象
     * 
     * @param pushManageAndroid
     * @return
     * @author zhanghongwei
     */
    public PushManageAndroid add(PushManageAndroid pushManageAndroid);

    /**
     * 更新实体对象
     * 
     * @param pushManageAndroid
     * @return
     * @author zhanghongwei
     */
    public PushManageAndroid modify(PushManageAndroid pushManageAndroid);

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
    public PushManageAndroid findById(Long id);

    /**
     * 通过ID查询不同来源的实体对象
     * 
     * @param userId
     *            用户ID
     * @param productNum
     *            产品号：101为奥莱；102为尚品
     * @return
     * @author zhanghongwei
     */
    public List<PushManageAndroid> findByUserId(String userId, Long productNum);

    /**
     * 根据id，更新push状态为已发送
     * 
     * @param id
     *            主键ID
     * @author zhanghongwei
     */
    public PushManageAndroid modifyTypeById(Long id);

    /**
     * 获取24小时之内及定时发送时间大于当前时间的实体对象集合(Android平台广播)
     * 
     * @param gender
     *            0：女；1：男；2全部
     * @param productNum产品号
     *            ：101为奥莱；102为尚品
     * @return
     * @author zhanghongwei
     */
    public List<PushManageAndroid> findByGender(int gender, Long productNum);
    
    /**
     * 分页查询push消息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<PushManageAndroid> findByPage(int pageIndex, int pageSize);

}
