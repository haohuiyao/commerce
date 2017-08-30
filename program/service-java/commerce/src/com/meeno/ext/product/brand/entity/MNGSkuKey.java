package com.meeno.ext.product.brand.entity;

import java.io.Serializable;

public class MNGSkuKey implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long skuKeyId;//参数的Id
	
	private String keyName;//参数名称
	
	private Long goodsId;//商品编号

	public Long getSkuKeyId() {
		return skuKeyId;
	}

	public void setSkuKeyId(Long skuKeyId) {
		this.skuKeyId = skuKeyId;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
}
