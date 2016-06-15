package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.PushconfAolai;

public interface IPushconfAolaiService {

    /**
     * 增加实体对象
     * 
     * @param pushconfAolai
     * @return
     * @author zhanghongwei
     */
    public PushconfAolai add(PushconfAolai pushconfAolai);

    /**
     * 更新实体对象
     * 
     * @param pushconfAolai
     * @return
     * @author zhanghongwei
     */
    public PushconfAolai modify(PushconfAolai pushconfAolai);

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
    public PushconfAolai findById(Long id);

    /**
     * 根据用户ID查询实体对象
     * 
     * @param userId
     * @return
     * @author zhanghongwei
     */
    public PushconfAolai findByUserId(String userId);

    /**
     * 根据token查询实体对象集合
     * 
     * @param token
     *            iPhone token
     * @return
     * @author zhanghongwei
     */
    public List<PushconfAolai> findByToken(String token);

    /**
     * 根据推送消息类型查询token集合
     * 
     * @param msgType
     *            0接收女士特卖信息1接收男士特卖信息2全部接收
     * @return
     * @author zhanghongwei
     */
    public List<String> findByMsgType(int msgType);

    /**
     * 根据推送消息类型查询token集合（和findByMsgType方法有区别具体看实现）
     * 
     * @param msgType
     *            0接收女士特卖信息1接收男士特卖信息2全部接收
     * @return
     * @author zhanghongwei
     */
    public List<String> findByMsgTypeContain(int msgType);

    /**
     * 保存Token
     * 
     * @param userId
     *            用户ID
     * @param gender
     *            性别
     * @param token
     *            iPhone token
     * @param imei
     *            手机imei
     * @author zhanghongwei
     */
    public void saveToken(String token, String userId, String imei, String gender);

    /**
     * 保存push设置
     * 
     * @param userId
     *            用户ID
     * @param gender
     *            性别
     * @param token
     *            iPhone token
     * @param imei
     *            手机imei
     * @param isOpen
     *            是否接收push信息
     * @param msgType
     *            接收push的消息类型
     * @author zhanghongwei
     */
    public void savePushConfig(String userId, String gender, String token, String imei, int isOpen, int msgType);

}
