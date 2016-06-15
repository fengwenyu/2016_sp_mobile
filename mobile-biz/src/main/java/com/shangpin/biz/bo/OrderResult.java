package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import com.shangpin.biz.utils.StringUtil;

public class OrderResult implements Serializable{
	private static final long serialVersionUID = 1L;
	//订单信息集合
	private List<OrderItem> Items ;
	private List<OrderItem> _items ;
	
	private String __type ;
    private String CurrentPage;
    private String ItemsPerPage;
    private String RowNum;
    private String TotalItems;
    private String msg;
    private String code;
    //总页数
    private String TotalPages;
    //是否有获取更多
    private String haveMore;
    private boolean isMore;
    private String pageIndex;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<OrderItem> getItems() {
		return Items;
	}
	public void setItems(List<OrderItem> items) {
		Items = items;
	}
	public String get__type() {
		return __type;
	}
	public void set__type(String __type) {
		this.__type = __type;
	}
	public String getCurrentPage() {
		return checkFiled(CurrentPage);
	}
	public void setCurrentPage(String currentPage) {
		CurrentPage = currentPage;
	}
	public String getItemsPerPage() {
		return checkFiled(ItemsPerPage);
	}
	public void setItemsPerPage(String itemsPerPage) {
		ItemsPerPage = itemsPerPage;
	}
	public String getRowNum() {
		
		return checkFiled(RowNum);
	}
	public void setRowNum(String rowNum) {
		RowNum = rowNum;
	}
	public String getTotalItems() {
		return checkFiled(TotalItems);
	}
	public void setTotalItems(String totalItems) {
		TotalItems = totalItems;
	}
	
	public String getHaveMore() {
		return haveMore;
	}
	public void setHaveMore(String haveMore) {
		this.haveMore = haveMore;
	}
	public List<OrderItem> getOrderItem() {
		return Items;
	}
	public void setOrderItem(List<OrderItem> orderItem) {
		this.Items = orderItem;
	}
	public String getTotalPages() {
	    return checkFiled(TotalPages);
	}
	public void setTotalPages(String totalPages) {
		this.TotalPages = totalPages;
	}
    public List<OrderItem> get_items() {
        _items = Items;
        return _items;
    }
    public void set_items(List<OrderItem> _items) {
        this._items = _items;
    }
    public boolean isMore() {
        Integer pageNo = Integer.valueOf(getCurrentPage());
        Integer totalPage = Integer.valueOf(getTotalPages());
        isMore=pageNo < totalPage;
        return isMore;
    }
    public void setMore(boolean isMore) {
        this.isMore = isMore;
    }
    
    /**
     * 判断属性值是否为null或者"null"
     * @param filedVal
     * @return
     */
    private String checkFiled(String filedVal) {
        if( !StringUtil.isNotEmpty(filedVal)||"null".equals(filedVal)){
            return "0";
        }
        return filedVal;
    }
    
}
