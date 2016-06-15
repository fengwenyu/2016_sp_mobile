package com.shangpin.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.DictionaryDAO;
import com.shangpin.core.entity.main.Dictionary;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.service.DictionaryService;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.dwz.PageUtils;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {

	@Autowired
	private DictionaryDAO dictionaryDAO;

	@Override
	public Dictionary find(String code) {
		return dictionaryDAO.findByCode(code);
	}

	@Override
	public List<Dictionary> findByParentId(Long parentId) {
		return this.dictionaryDAO.findByParentId(parentId);
	}
	
	@Override
	public List<Dictionary> findByParentCode(String parentCode) {
		return this.dictionaryDAO.findByParentCode(parentCode);
	}

	@Override
	public void save(Dictionary dictionary) {
		dictionaryDAO.save(dictionary);
	}

	@Override
	public Dictionary get(Long id) {
		return dictionaryDAO.findOne(id);
	}

	@Override
	public void update(Dictionary dictionary) {
		dictionaryDAO.save(dictionary);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根字典。");
		}

		Dictionary dictionary = this.get(id);

		// 先判断是否存在子模块，如果存在子模块，则不允许删除
		if (dictionary.getChildren().size() > 0) {
			throw new ServiceException(dictionary.getName() + "字典下存在子字典，不允许删除。");
		}
		dictionary.setParent(null);
		dictionaryDAO.delete(dictionary);
	}

	@Override
	public void delete(Dictionary dictionary) throws ServiceException {
		if (isRoot(dictionary.getId())) {
			throw new ServiceException("不允许删除根字典。");
		}
		// 先判断是否存在子模块，如果存在子模块，则不允许删除
		if (dictionary.getChildren().size() > 0) {
			throw new ServiceException(dictionary.getName() + "字典下存在子字典，不允许删除。");
		}
		dictionary.setParent(null);
		dictionaryDAO.delete(dictionary);

	}

	@Override
	public List<Dictionary> find(Long parentId, Page page) {
		org.springframework.data.domain.Page<Dictionary> springDataPage = dictionaryDAO
				.findByParentId(parentId, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public List<Dictionary> find(Long parentId, String name, Page page) {
		org.springframework.data.domain.Page<Dictionary> springDataPage = dictionaryDAO
				.findByParentIdAndNameContaining(parentId, name,
						PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public Dictionary getTree() {
		List<Dictionary> list = dictionaryDAO.findAllWithCache();

		List<Dictionary> rootList = makeTree(list);

		return rootList.get(0);
	}

	private List<Dictionary> makeTree(List<Dictionary> list) {
		List<Dictionary> parent = new ArrayList<Dictionary>();
		// get parentId = null;
		for (Dictionary e : list) {
			if (e.getParent() == null) {
				e.setChildren(new ArrayList<Dictionary>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		makeChildren(parent, list);
		return parent;
	}

	private void makeChildren(List<Dictionary> parent, List<Dictionary> children) {
		if (children.isEmpty()) {
			return;
		}

		List<Dictionary> tmp = new ArrayList<Dictionary>();
		for (Dictionary c1 : parent) {
			for (Dictionary c2 : children) {
				c2.setChildren(new ArrayList<Dictionary>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}

		children.removeAll(tmp);
		makeChildren(tmp, children);
	}

	/**
	 * 判断是否是根组织.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

}
