package com.aolai.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aolai.web.utils.ConfigContainer;
import com.shangpin.biz.bo.Navigation;

@Controller
public class NavigationController {
    public static final Logger logger = LoggerFactory.getLogger(NavigationController.class);
    /** 导航地图 */
    private static final String NAVIGATION = "common/navigation";

    /**
     * 接受URL格式/nav?navId=4&parentParams=
     * 1_param1:ceshi1,param2:ceshi2|2_paramx:ceshi1,paramy:ceshi2
     * 
     * @param navId
     *            当前路径ID
     * @param parentParams
     *            没有则可以不传递 navId对应得 所有父类ID所需要的URL参数拼接字符串 拼接格式如下：
     *            父ID1_参数1:值1,参数2:值2|父ID2_参数1:值1,参数2:值2
     * @param map
     * @return
     */
    @RequestMapping("/nav")
    public String nav(String navId, String parentParams, Map<String, Object> map) {
        List<Navigation> navList = ConfigContainer.getNavList(navId);
        if (!StringUtils.isEmpty(parentParams)) {
            setParentParam(parentParams, navList);
        }
        map.put("navList", navList);
        return NAVIGATION;
    }

    /**
     * 设置对应的父导航参数值
     * 
     * @param parentParams
     * @param navList
     */
    private void setParentParam(String parentParams, List<Navigation> navList) {
        try {
            Map<String, String> parentParamMap = new HashMap<String, String>();
            String[] parentParamsArray = parentParams.split("\\|");
            for (String pp : parentParamsArray) {
                String[] parentParam = pp.split("_");
                String param = "?" + parentParam[1].replaceAll(":", "=").replaceAll(",", "&");
                parentParamMap.put(parentParam[0], param);
            }
            for (Map.Entry<String, String> entry : parentParamMap.entrySet()) {
                for (Navigation nav : navList) {
                    if (nav.getId().equals(entry.getKey())) {
                        String link = nav.getLink() + entry.getValue();
                        nav.setLink(link);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("set nav error :check parentParams " + ex.getMessage());
        }
    }
}
