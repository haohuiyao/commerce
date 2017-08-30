package com.meeno.ext.product.util;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;

public enum EnumObjType {

	PRODUCT(1, MNProduct.class),
	SKU(2, MNSku.class),
	BRAND(3, Brand.class),
	ALLBRAND(4, null);

	private int type;
	private Class<?> objClass;

	private EnumObjType(int type, Class<?> objClass) {
		this.type = type;
		this.objClass = objClass;
	}

	public int getType() {
		return type;
	}

	public Class<?> getObjClass() {
		return objClass;
	}

}
