package com.shangpin.wireless.api.domain;

import java.util.List;

/**
 * @Author zhouyu
 * @CreateDate: 2012-07-12
 *  分页数据实体
 */
public class PageBean {

	// 用户指定的或是管理员配置的值
	private int currentPage; // 当前页
	public PageBean() {
	}

	private int pageSize; // 每页显示多少条数据

	// 查询数据库
	private int recordCount; // 总记录数
	private List recordList; // 本页的数据列表

	// 计算
	private int pageCount; // 总页数
	private int beginPageIndex; // 页码列表的开始索引
	private int endPageIndex; // 页码列表的结束索引

	/**
	 * 只接受前4个必要的参数，会自动的计算出后3个属性的值。
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordCount
	 * @param recordList
	 */
	public PageBean(int currentPage, int pageSize, int recordCount, List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;

		// 一、计算pageCount
		this.pageCount = (recordCount + pageSize - 1) / pageSize;

		// 二、计算beginPageIndex与endPageIndex（最多显示当前页附近的10个页码）
		// 总页数小于等于10页，则全部显示
		if (pageCount <= 10) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// 总页数大于10页
		else {
			// 默认显示当前页附件近的10个页码（前4个 + 当前页 + 后5个）
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;

			// 前面不足4个页码时，就显示前10个页码
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			// 后面不足5个页码时，就显示后10个页码
			else if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 10 + 1; // 显示时两个边界都包含
			}
		}
	}

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
}
