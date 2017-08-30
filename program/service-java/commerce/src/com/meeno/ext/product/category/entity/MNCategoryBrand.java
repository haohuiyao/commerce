package com.meeno.ext.product.category.entity;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.framework.bean.BaseEntity;

public class MNCategoryBrand extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private MNCategory category;
	private Brand brand;

	public MNCategory getCategory() {
		return category;
	}

	public void setCategory(MNCategory category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public MNCategoryBrand() {
		super();
		// TODO Auto-generated constructor stub
	}

}
