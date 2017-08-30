package com.newProduct.product.machine.subview.back;

import com.meeno.ext.product.goods.entity.MNSku;

/**
 * 具体机型详情
 * 
 * @author Administrator
 *
 */
public class ModelBackItem {
	private Long id;
	private String skuName;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public ModelBackItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModelBackItem(MNSku sku) {
		this.id=sku.getId();
		this.skuName=sku.getSkuName();
		this.status=sku.getStatus();
	}
}
