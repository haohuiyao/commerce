package com.newProduct.product.machine.subview.back;

import java.util.List;

import com.meeno.ext.product.goods.entity.MNProduct;

/**
 * 机型组详情
 * 
 * @author Administrator
 *
 */
public class MachineBackItem {
	private Long id;
	private String name;
	private Long categoryId;
	private String categoryName;
	private Long brandId;
	private String brandName;
	private List<ModelBackItem> skuList;// 具体机型集合

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<ModelBackItem> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<ModelBackItem> skuList) {
		this.skuList = skuList;
	}

	public MachineBackItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MachineBackItem(MNProduct product) {
		this.id=product.getId();
		this.name=product.getName();
		if(product.getCategory()!=null){
			this.categoryId=product.getCategory().getId();
			this.categoryName=product.getCategory().getName();
		}
		if(product.getBrand()!=null){
			this.brandId=product.getBrand().getId();
			this.brandName=product.getBrand().getName();
		}
	}
	
}
