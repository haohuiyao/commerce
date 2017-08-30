package com.newProduct.product.machine.subview.back;

import java.util.ArrayList;
import java.util.List;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.util.ProductContant;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

/**
 * 选择品牌获取的机型组实体
 * 
 * @author Administrator
 *
 */
public class ProductBackItem {
	private Long id;
	private String name;
	private Integer type;
	private String img;
	private List<ProductBackItem> childItem = new ArrayList<>();

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<ProductBackItem> getChildItem() {
		return childItem;
	}

	public void setChildItem(List<ProductBackItem> childItem) {
		this.childItem = childItem;
	}

	public ProductBackItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductBackItem(MNProduct product) {
		this.id = product.getId();
		this.name = product.getName();
		this.type = ProductContant.OBJ_TYPE_PRODUCT;
	}

	public ProductBackItem(MNSku sku) {
		this.id = sku.getId();
		this.name = sku.getSkuName();
		this.type = ProductContant.OBJ_TYPE_SKU;
	}

	public ProductBackItem(Brand brand) {
		this.id = brand.getId();
		this.name = brand.getName();
		this.type = ProductContant.OBJ_TYPE_BRAND;
		this.img = brand.getImgUrl();
	}
}
