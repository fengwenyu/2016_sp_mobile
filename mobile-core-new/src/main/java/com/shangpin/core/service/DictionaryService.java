package com.shangpin.core.service;

import java.util.List;

import com.shangpin.core.entity.main.Dictionary;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.util.dwz.Page;

public interface DictionaryService {

	Dictionary find(String code);
	
	List<Dictionary> findByParentId(Long parentId);
	
	List<Dictionary> findByParentCode(String parentCode);

	void save(Dictionary dictionary);

	Dictionary get(Long id);

	void update(Dictionary dictionary);

	void delete(Long id) throws ServiceException;
	
	void delete(Dictionary dictionary) throws ServiceException;

	List<Dictionary> find(Long parentId, Page page);

	List<Dictionary> find(Long parentId, String name, Page page);

	Dictionary getTree();
}
