package com.meeno.ext.product.util;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;

public interface ProductContant {
	String NOstr = "0";

	String YESstr = "1";

	int MAXLEVEL = 6;

	/**
	 * product类型
	 */
	int OBJ_TYPE_PRODUCT = 1;

	/**
	 * product实体
	 */
	Class OBJ_TYPE_ENTITY_PRODUCT = MNProduct.class;

	/**
	 * sku类型
	 */
	int OBJ_TYPE_SKU = 2;

	/**
	 * sku实体
	 */
	Class OBJ_TYPE_ENTITY_SKU = MNSku.class;

	/**
	 * brand类型
	 */
	int OBJ_TYPE_BRAND = 3;

	/**
	 * brand实体
	 */
	Class OBJ_TYPE_ENTITY_BRAND = Brand.class;

	/**
	 * 通用所有品牌
	 */
	int OBJ_TYPE_BASE_ENTITY=4;
	
	/**
	 * 所有品牌Id
	 */
	Long OBJ_TYPE_ALLBARND_ID=Long.valueOf(0);
	
	/**
	 * 配件关系
	 */
	int OBJ_REL_PARTS = 1;
	
	/**
	 * 通用
	 */
	int OBJ_REL_CURRENCY=1;
	
	/**
	 * 不通用
	 */
	int OBJ_REL_UN_CURRENCY=0;

	String CATEGORY_PRODUCT = "挖机";
	String CATEGORY_PARTS = "配件";
	String OBJ_REL_ALLBAND="通用";
}
