package com.shangpin.core.entity.main;

// Generated 2013-5-15 15:58:25

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;

import com.shangpin.core.entity.Idable;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * Dictionary
 */
@Entity
@Table(name = "security_dictionary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.shangpin.core.entity.main.Dictionary")
public class Dictionary implements Comparable<Dictionary>, Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parentId")
	private Dictionary parent;

	@Column(name = "name", length = 20)
	private String name;

	@Column(name = "code", length = 20)
	private String code;

	@Column(name = "description", length = 100)
	private String description;
	
	@NotNull
	@Range(min=1, max=99)
	@Column(length=2)
	private Integer priority = 99;

	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="parent",fetch=FetchType.EAGER)
	@OrderBy("priority ASC")
	private List<Dictionary> children = Lists.newArrayList();

	public Dictionary() {
	}

	public Dictionary(Dictionary parent, String name, String code,
			String description, List<Dictionary> children) {
		this.parent = parent;
		this.name = name;
		this.code = code;
		this.description = description;
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}

	public Dictionary getParent() {
		return this.parent;
	}

	public void setParent(Dictionary parent) {
		this.parent = parent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Dictionary> getChildren() {
		return this.children;
	}

	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	/*
	 * 
	 */
	@Override
	public int compareTo(Dictionary dic) {
		if (dic == null) {
			return -1;
		} else if (dic == this) {
			return 0;
		} else if (this.priority < dic.getPriority()) {
			return -1;
		} else if (this.priority > dic.getPriority()) {
			return 1;
		}

		return 0;	
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.addValue(id)
				.addValue(name)
				.addValue(code)
				.addValue(parent == null ? null:parent.getName())
				.addValue(children.size())
				.toString();
	}
	

}
