package com.shangpin.core.service;

/**
 * push业务逻辑层接口，提供，android、osi等平台push信息内容
 * 
 * @author zhanghongwei
 *
 */
public interface IPushManageService {

    /**
     * 获取24小时之内及定时发送时间大于当前时间的push信息(Android平台广播)
     * 
     * @param gender
     *            0：女；1：男；2全部
     * @param productNum
     *            产品号：101为奥莱；102为尚品
     * @return push信息字符串，使用","分割
     * @author zhanghongwei
     */
    public String findAndroidPushInfo(int gender, Long productNum);

    /**
     * 根据username，获取个人的push信息列表，及广播push信息列表（Android平台）
     * 
     * @param userId
     *            用户ID
     * @param gender
     *            0：女；1：男；2全部
     * @param productNum
     *            产品号：101为奥莱；102为尚品
     * @Return json字符串
     * @author zhanghongwei
     */
    public String modifyAndGetAndroidPushInfo(String userId, String gender, Long productNum);

    /**
     * 根据gender、productNum，发送push信息（ios平台）
     * 
     * @param gender
     *            0：女；1：男；2全部
     * @param productNum
     *            产品号：1为奥莱；2为尚品
     * @author zhanghongwei
     */
    public void modifyAndSendIOSPushInfo(int gender, Long productNum);

    /**
     * 根据productNum，发送push信息（个人相关，ios平台）
     * 
     * @param productNum
     *            产品号：1为奥莱；2为尚品
     * @author zhanghongwei
     */
    public void modifyAndSendIOSPersonPushInfo(Long productNum);
}
