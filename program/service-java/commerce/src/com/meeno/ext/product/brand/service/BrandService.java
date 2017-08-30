package com.meeno.ext.product.brand.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.framework.dao.BaseDao;

public interface BrandService extends BaseDao{

	/**
	 * 新增商品品牌
	 * @param brandName
	 * @param img
	 * @param abb
	 */
	void addBrand(String brandName,String img,String abb);
	
	/**
	 * 获取所有商品品牌
	 * @return
	 */
	List<Brand> getAllMachineBrand();
	
	/**
	 * 编辑产品组品牌
	 * @param id
	 * @param name
	 * @param img
	 * @param abb
	 */
	void editBrand(Long id,String name,String img,String abb);
	
	/**
	 * 删除产品组品牌
	 * @param id
	 */
	void delBrand(Long id);
	
	/**
	 * 编辑品牌分类
	 * 
	 * @param categoryId
	 * @param brandId
	 */
	void editCategoryBrand(Long brandId,JSONArray categoryId);

	/**
	 * 根据ID获取品牌
	 * 
	 * @param id
	 * @return
	 */
	List<Brand> getBrandByCategory(Long id);
	
	/**
	 * 获取品牌分类列表
	 * 
	 * @param id
	 * @return
	 */
	List<MNCategory> getCategoryByBrand(Long id);
}
