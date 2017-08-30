package com.meeno.ext.product.category.entity;

import java.io.Serializable;
import java.util.Date;

public class WPRecommendGenre implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long position;
	
	private String imgUrl;
	
	private MNCategory category;
	
	private Date createTime;
	
	private Long type;
	
	private String sign;//标记添加的是大类还是细类
	

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public MNCategory getCategory() {
		return category;
	}

	public void setCategory(MNCategory category) {
		this.category = category;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
