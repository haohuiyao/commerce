package com.newProduct.product.product.subview.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配件适配关系实体
 * @author Administrator
 *
 */
public class SkuRelBackItem {
	private Long skuId;
	private Long brandId;
	private String brandName;
	private Map<String, List> productRel=new HashMap<>();

	public Map<String, List> getProductRel() {
		return productRel;
	}

	public void setProductRel(Map<String, List> productRel) {
		this.productRel = productRel;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
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

	public SkuRelBackItem() {
		super();
		// TODO Auto-generated constructor stub
	}
}
