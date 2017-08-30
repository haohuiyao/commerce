package com.meeno.ext.product.category.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.subview.back.CategoryEntity;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.tag.CutPageBean;

/**
 * 商品
 * 
 * @author yao
 * 
 * @date 2017年4月14日
 */
public interface CategoryService {

	/**
	 * 新增商品分类
	 * 
	 * @param name
	 * @param imgUrl
	 */
	MNCategory addCategory(String name, String abbreviation, String imgUrl, String parentId, Long paramsTeampletId);

	/**
	 * 编辑商品分类信息
	 * 
	 * @param categoryId
	 * @param name
	 * @param imgUrl
	 */
	void editCategory(Long categoryId, String name, String abbreviation, String imgUrl, Long paramsTeampletId);

	/**
	 * 获取所有分类列表
	 * 
	 * @param name
	 * @return
	 */
	List<MNCategory> getAllCategories(Long parentId);

	/**
	 * 修改分类的参数模板
	 * 
	 * @param categoryId
	 * @param templateId
	 */
	void editCategoryTemplate(Long categoryId, Long templateId);

	/**
	 * 删除分类
	 * 
	 * @param categoryId
	 */
	void delCategory(Long categoryId);
	
	/**
	 * 获取所有子集分类
	 * @param id
	 * @return
	 */
	List<CategoryEntity> getAllChildCategory(Long id);
}
