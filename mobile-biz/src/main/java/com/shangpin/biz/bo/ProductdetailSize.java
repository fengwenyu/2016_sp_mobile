package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/** 
* @ClassName: ProductdetailSize 
* @Description:商品尺码列表实体类 
* @author qinyingchun
* @date 2014年11月3日
* @version 1.0 
*/
public class ProductdetailSize implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String pic;
	private String tablessize;
	private List<String[]> table=new ArrayList<String[]>();
	private List<String> sizepiclist=new ArrayList<String>();
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTablessize() {
		return tablessize;
	}
	public void setTablessize(String tablessize) {
		this.tablessize = tablessize;
	}
	
	public List<String[]> getTable() {
		return table;
	}
	public void setTable(List<String[]> table) {
		this.table = table;
	}
	public List<String> getSizepiclist() {
		return sizepiclist;
	}
	public void setSizepiclist(List<String> sizepiclist) {
		this.sizepiclist = sizepiclist;
	}

}
