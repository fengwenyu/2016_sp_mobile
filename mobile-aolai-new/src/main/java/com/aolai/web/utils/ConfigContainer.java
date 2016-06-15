package com.aolai.web.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.Navigation;
import com.shangpin.biz.bo.Navigations;

/**
 * 从配置(如xml)中读取到的对象
 * 
 * @author zghw
 *
 */
public class ConfigContainer {
    public static Logger logger = LoggerFactory.getLogger(ConfigContainer.class);

    private static final String NAV_XML = "bizconfig/nav.xml";
    private static final String COUPON_PERIOD_XML = "bizconfig/couponPeriod.xml";
    /** 所有导航对象集合 */
    private static final List<Navigation> allNavList = Collections.unmodifiableList(JAXBUtil.fromXML(NAV_XML, Navigations.class).getNavigation());
    /** 所有导航对象集合 副本 */
    private static List<Navigation> _allNavList = new ArrayList<Navigation>();
    /** 注册送券期间对象 */
    private static final CouponPeriod couponPeriod = JAXBUtil.fromXML(COUPON_PERIOD_XML, CouponPeriod.class);

    /**
     * 所有导航对象集合
     * 
     * @return
     * @author zghw
     */
    public static List<Navigation> getAllNavList() {
        List<Navigation> allNav = new ArrayList<Navigation>();
        for (Navigation nav : allNavList) {
            allNav.add((Navigation) nav.clone());
        }
        return allNav;
    }

    /**
     * 注册送券期间对象
     * 
     * @return
     * @author zghw
     */
    public static CouponPeriod getCouponPeriod() {
        return (CouponPeriod) couponPeriod.clone();
    }

    /**
     * 通过导航ID查询所有导航中包含的导航及父导航
     * 
     * @param _allNavList
     * @param navId
     * @return
     * @author zghw
     */
    public static List<Navigation> getNavList(String navId) {
        _allNavList = getAllNavList();
        List<Navigation> navList = null;
        Navigation currNav = null;
        if (_allNavList != null && _allNavList.size() > 0) {
            for (Navigation nav : _allNavList) {
                if (nav.getId().equals(navId)) {
                    currNav = nav;
                    break;
                }
            }
        }
        if (currNav != null) {
            navList = new ArrayList<Navigation>();
            navList = getParentList(navList, currNav);
            Collections.reverse(navList);
        }
        return navList;
    }

    /**
     * 查找父类集合导航对象
     * 
     * @param listNav
     * @param addNavList
     * @param currNav
     * @return
     * @author zghw
     */
    private static List<Navigation> getParentList(List<Navigation> navList, Navigation currNav) {
        navList.add(currNav);
        for (Navigation nav : _allNavList) {
            if (nav.getId().equals(currNav.getParentId())) {
                currNav = nav;
                getParentList(navList, currNav);
                break;
            }
        }
        return navList;
    }
}
