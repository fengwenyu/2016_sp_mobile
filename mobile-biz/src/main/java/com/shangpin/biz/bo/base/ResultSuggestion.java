package com.shangpin.biz.bo.base;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class ResultSuggestion extends SearchCommon {

	private static final long serialVersionUID = -4561577062595690865L;

	private List<Suggestion> suggestions = new ArrayList<Suggestion>();

	@XmlElementWrapper(name = "suggestions")
	@XmlElement(name = "suggestion")
	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	@Override
	public String toString() {
		return "ResultSuggestion [suggestions=" + suggestions + "]";
	}
}
