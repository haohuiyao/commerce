package com.meeno.ext.product.goods.subview.back;

import com.meeno.ext.product.brand.entity.Brand;

/**
 * 品牌实体
 * @author Administrator
 *
 */
public class BrandEntity {
	private Long id;
	private String brandName;

	public BrandEntity(Brand brand) {
		this.id = brand.getId();
		this.brandName = brand.getName();
	}

	public BrandEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
