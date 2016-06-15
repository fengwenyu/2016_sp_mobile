package com.shangpin.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "fashion_pack")
public class FashionPack {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 装备名称
	 */
	private String name;
	/**
	 * 装备描述
	 */
	private String desc;
	/**
	 * 装备价格
	 */
	private BigDecimal price;
	/**
	 * 创建时间
	 */
	private Date createTime;
	private String packType;
	public FashionPack(Long id, String name, String desc, BigDecimal price,
			Date createTime,String packType) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.createTime = createTime;
		this.packType = packType;
	}
	
	public FashionPack() {
		super();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "desc", length = 200)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name="price",precision=12,scale=2)  
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "pack_type", length = 50)
	public String getPackType() {
		return packType;
	}
	public void setPackType(String packType) {
		this.packType = packType;
	}

}
