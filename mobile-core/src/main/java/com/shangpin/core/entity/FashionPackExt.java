package com.shangpin.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fashion_pack_ext")
public class FashionPackExt {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 主键id
	 */
	private Long packId;
	/**
	 * 尺码
	 */
	private String size;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 销量
	 */
	private Integer sellCount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "pack_id")
	public Long getPackId() {
		return packId;
	}
	public void setPackId(Long packId) {
		this.packId = packId;
	}
	@Column(name = "size",length=50)
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Column(name = "stock")
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	@Column(name = "sell_count")
	public Integer getSellCount() {
		return sellCount;
	}
	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
