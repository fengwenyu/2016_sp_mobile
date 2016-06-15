package com.shangpin.biz.bo;

import java.io.Serializable;

public class SupplierSkuNoInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7542927215320065804L;
	
	  /// <summary>
    /// 供应商编号
    /// </summary>
    public String SupplierNo;
    /// <summary>
    /// 仓库编号
    /// </summary>
    public String WarehouseNo;

    /// <summary>
    /// Sku编号
    /// </summary>
    public String SkuNo;
    /// <summary>
    /// 操作数
    /// </summary>
    public String Quantity;
	public String getSupplierNo() {
		return SupplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		SupplierNo = supplierNo;
	}
	public String getWarehouseNo() {
		return WarehouseNo;
	}
	public void setWarehouseNo(String warehouseNo) {
		WarehouseNo = warehouseNo;
	}
	public String getSkuNo() {
		return SkuNo;
	}
	public void setSkuNo(String skuNo) {
		SkuNo = skuNo;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
    
    


}
