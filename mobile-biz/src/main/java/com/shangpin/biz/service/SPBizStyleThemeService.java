package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.StyleTheme;

public interface SPBizStyleThemeService {
	
	public String queryStyleThemeStr(String pageIndex, String pageSize);
	
	public List<StyleTheme> styleThemes(String pageIndex, String pageSize);

}
