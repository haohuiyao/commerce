package com.meeno.ext.product.category.dao;

import java.util.List;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.entity.MNCategoryBrand;
import com.meeno.framework.dao.BaseDao;

public interface CategoryDao extends BaseDao {

	/**
	 * 根据分类名称查找分类
	 * 
	 * @param name
	 * @return
	 */
	MNCategory findCategoryByName(String name);

	/**
	 * 获取所有分类列表
	 * 
	 * @param name
	 * @return
	 */
	List<MNCategory> getAllCategories(Long parentId);

	/**
	 * 根据条件获取分类列表
	 * 
	 * @param pageBean
	 * @param categoryId
	 *            分类ID
	 * @param name
	 *            分类名称
	 * @return
	 */
	List<MNCategory> findCategoriesList(String categoryId, String name);
	
	/**
	 * 下架所有子分类
	 * @param parentId
	 */
	void putOutCategory(Long parentId);
	
	/**
	 * 获取分类品牌是否存在
	 * @param categoryId
	 * @param brandId
	 * @return
	 */
	MNCategoryBrand getCategoryBrand(Long categoryId,Long brandId);
	
	/**
	 * 根据ID获取品牌
	 * @param id
	 * @return
	 */
	List<Brand> getMachineBrandByCategory(List<String> styleList);
	
	/**
	 * 保存分类
	 * @param category
	 */
	void saveCategory(MNCategory category);
	
	/**
	 * 查询所有子类
	 * @param id
	 * @param style
	 * @return
	 */
	List<MNCategory> getAllChildCategory(Long id,String style);
	
	/**
	 * 获取品牌分类列表
	 * 
	 * @param id
	 * @return
	 */
	List<MNCategory> getCategoryByBrand(Long id);
}