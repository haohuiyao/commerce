package com.meeno.ext.product.brand.dao;

import java.util.List;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.framework.dao.BaseDao;

public interface BrandDao extends BaseDao {
	/**
	 * 获取所有商品品牌
	 * 
	 * @return
	 */
	List<Brand> getAllMachineBrand();
	
	/**
	 * 根据名称查询产品组品牌
	 * @param name
	 * @return                                                                                        
	 */
	Brand findBrandByName(String name);
	
	/**
	 * 删除品牌分类
	 * @param brandId
	 */
	void delBrandCategory(Long brandId);
	
	/**
	 * 根据style模糊查询
	 * @param style
	 * @return
	 */
	List<Brand> getBrandLikeCategoryStyle(String style);
}
