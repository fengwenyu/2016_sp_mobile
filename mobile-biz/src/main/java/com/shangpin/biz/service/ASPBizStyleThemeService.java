package com.shangpin.biz.service;

import java.util.List;
import com.shangpin.biz.bo.StyleTheme;


public interface ASPBizStyleThemeService {
   
    /**
     *风格主题
     *
     * @param pageIndex
     *            当前页数
     * @param pageSize
     *            每页显示条数
     * @return
     * @author wangfeng
     */
    public List<StyleTheme> queryStyleTheme(String pageIndex, String pageSize);
    /**
     *风格主题
     *
     * @param pageIndex
     *            当前页数
     * @param pageSize
     *            每页显示条数
     * @return
     * @author wangfeng
     */
    public String queryStyleThemeToResult(String pageIndex, String pageSize);
    
	

}
