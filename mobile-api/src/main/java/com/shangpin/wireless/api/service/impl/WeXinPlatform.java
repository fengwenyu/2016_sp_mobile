package com.shangpin.wireless.api.service.impl;

import org.springframework.stereotype.Service;

import com.shangpin.cache.Cacheable;
import com.shangpin.wireless.api.dao.AutoReplyDao;
import com.shangpin.wireless.api.domain.AutoReply;
import com.shangpin.wireless.api.domain.Keywords;
import com.shangpin.wireless.api.util.HqlHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring AOP在同一个类里自身方法相互调用时执行this是本地方法,不会被aop代理增强
 * 增加此类,暂时救急
 */
@Service
public class WeXinPlatform {

    private final Log log = LogFactory.getLog(WeXinPlatform.class);
    @Resource(name = AutoReplyDao.DAO_NAME)
    private AutoReplyDao autoReplyDao;

    /**
     * 缓存查询规则数据
     * @return
     * @throws Exception
     */
    @Cacheable(key="weChatAutoReply",expire=300)
    public Map<String, Map<String, AutoReply>> findAll() throws Exception {

    	HqlHelper hqlHelper = new HqlHelper(AutoReply.class, "a");
    	hqlHelper.addWhereCondition("a.status = 1", null);
    	
        List<AutoReply> autoReplyList = autoReplyDao.getListByCondition(hqlHelper, "mysql");
        log.debug("查询匹配规则autoReplyList="+autoReplyList);


        Map<String, Map<String, AutoReply>> map = new HashMap<>();
        Map<String, AutoReply> allMap = new HashMap<>();
        Map<String, AutoReply> haveMap = new HashMap<>();
        map.put("allMap",allMap);
        map.put("haveMap",haveMap);

        for (AutoReply autoReply : autoReplyList) {
            List<Keywords> keywordsList = autoReply.getKeywordsList();
            for (Keywords keyword : keywordsList) {

                //精确匹配
                allMap.put(keyword.getKeyword(), autoReply);

                //包含匹配
                if("1".equals(keyword.getMode())){
                    haveMap.put(keyword.getKeyword(), autoReply);
                }
            }
        }
        return map;
    }
}