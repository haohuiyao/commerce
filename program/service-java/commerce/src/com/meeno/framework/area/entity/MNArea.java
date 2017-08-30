package com.meeno.framework.area.entity;

import java.io.Serializable;

/**
 * 
 * @author jeff_gao
 *
 */
public class MNArea implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long parentId;//父节点Id
	private Integer level;//等级
	private String initial;
	//private Set<MNArea> childSet;//子地区
	
	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public MNArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	/*public Set<MNArea> getChildSet() {
		return childSet;
	}

	public void setChildSet(Set<MNArea> childSet) {
		this.childSet = childSet;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
