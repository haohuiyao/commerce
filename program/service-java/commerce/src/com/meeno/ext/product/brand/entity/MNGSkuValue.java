package com.meeno.ext.product.brand.entity;

import java.io.Serializable;

public class MNGSkuValue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long valueId;
	
	private MNGSkuKey mngSkuKey;
	
	public MNGSkuKey getMngSkuKey() {
		return mngSkuKey;
	}

	public void setMngSkuKey(MNGSkuKey mngSkuKey) {
		this.mngSkuKey = mngSkuKey;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}


	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	private String words;
	
	private String imgPath;
}
