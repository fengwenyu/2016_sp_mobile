package com.shangpin.core.service;

import java.util.Date;
import java.util.List;

import com.shangpin.core.entity.FindManage;

public interface IFindManageService {

    public FindManage addFindManage(FindManage findManage);

    public FindManage findManageById(Long id);

    public void deleteFindManage(Long id);

    public List<FindManage> findManageData(Date date);

    public List<FindManage> findByActivityManage(Date date, int isSlider, String type);

    public List<FindManage> findStaticActivity(Date date, int display, String type);

    public List<FindManage> findByConditions(Date date, String type);

    /**
     * 根据时间得到正在进行显示信息活动信息
     * 
     * @param date
     * @param slider
     *            1为滑动 其他为不滑动
     * @return
     * @author zhanghongwei
     */
    public List<FindManage> findFocus(Date date, int slider);

    /**
     * 根据时间得到正在进行显示信息活动默认信息
     * 
     * @param date时间
     * @return
     * @author zhanghongwei
     */
    public List<FindManage> findDefaultSlider(Date date);

}
