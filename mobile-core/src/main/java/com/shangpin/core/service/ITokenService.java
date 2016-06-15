package com.shangpin.core.service;

import com.shangpin.core.entity.Token;

public interface ITokenService {

    /**
     * 增加实体对象
     * 
     * @param token
     * @return
     * @author zhanghongwei
     */
    public Token add(Token token);

    /**
     * 更新实体对象
     * 
     * @param token
     * @return
     * @author zhanghongwei
     */
    public Token modify(Token token);

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
    public Token findById(Long id);

    /**
     * 根据token状态得到查询结果token集合中的第一个token对象
     * 
     * @param status
     *            token状态(比如：normal)
     * @return
     * @author zhanghongwei
     */
    public Token findByStatus(String status);

    /**
     * 重置数据库token状态
     * 
     * @author zhanghongwei
     */
    public void resetTokenStatus();

    /**
     * 验证token是否失效
     * 
     * @param code
     *            token号
     * @return
     * @author zhanghongwei
     */
    public Boolean isInvalidCode(String code);

}
