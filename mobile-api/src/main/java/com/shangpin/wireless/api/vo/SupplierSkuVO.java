package com.shangpin.wireless.api.vo;

public class SupplierSkuVO {
    /// <summary>
    /// 供应商编号
    /// </summary>
    private String SupplierNo;
    /// <summary>
    /// 仓库编号
    /// </summary>
    private String WarehouseNo;

    /// <summary>
    /// Sku编号
    /// </summary>
    private String SkuNo ;
    /// <summary>
    /// 操作数
    /// </summary>
    private String Quantity ;
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
