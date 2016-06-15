package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.SearchService;
import com.shangpin.biz.bo.base.ResultSuggestion;
import com.shangpin.biz.bo.base.Suggestion;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JaxbUtil;
import com.shangpin.utils.JaxbUtil.CollectionWrapper;

public class AbstractBizSearchService {

	public static Logger logger = LoggerFactory.getLogger(AbstractBizSearchService.class);

	@Autowired
	private SearchService searchService;

	protected ResultSuggestion getSearchResult(String keyword, String userID, String userIP, String encode) {
		ResultSuggestion resultSuggestion = new ResultSuggestion();
		String xmlStr = searchService.searchSuggestion(keyword, userID, userIP, encode);
		if (StringUtils.isEmpty(xmlStr)) {
			return resultSuggestion;
		}
		JaxbUtil resultBinder = new JaxbUtil(ResultSuggestion.class, CollectionWrapper.class);
		resultSuggestion = resultBinder.fromXml(xmlStr);
		return resultSuggestion;
	}

	protected List<Suggestion> getSuggestion(String keyword, String userID, String userIP, String encode) {
		ResultSuggestion resultSuggestion = getSearchResult(keyword, userID, userIP, encode);
		List<Suggestion> suggestions = new ArrayList<Suggestion>();
		if (Constants.SUCCESS.equals(resultSuggestion.getStatus())) {
			suggestions = resultSuggestion.getSuggestions();
		}

		return suggestions;
	}
}
