package com.meeno.framework.tree;

import com.meeno.framework.bean.BaseEntity;

public class TreeEntity extends BaseEntity implements java.io.Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private TreeEntity parent;
	private Integer level;
	private String style;
	
	public TreeEntity getParent() {
		return parent;
	}

	public void setParent(TreeEntity parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/*public String getTreePrefixCode(){
		return getStyle().substring(0,getLevel()*2);
	}*/

}
