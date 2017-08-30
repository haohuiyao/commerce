package com.newProduct.product.product.dao;

import java.util.List;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface WPProductDao extends BaseDao {
	
	/**
	 * 下架SKU
	 * @param product
	 */
	void putOutSkuByProduct(Long productId);
	
	/**
	 * 
	 * @param productId
	 * @param brandId
	 * @return
	 */
	MNSku findSkuByBrandAndPro(Long productId,Long brandId);
	
	/**
	 * 删除sku的关联关系
	 * @param skuIds
	 */
	void delSkuRelByProduct(Long[] skuIds);
	
	/**
	 * 获取product的SKU
	 * @param productId
	 * @return
	 */
	Long[] getSkuIdsByProduct(Long productId);
	
	/**
	 * 获取sku
	 * @param productId
	 * @return
	 */
	List<MNSku> getSkuByProduct(Long productId);
	
	/**
	 * 查询产品sku
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @param categoryStyle
	 * @return
	 */
	CutPageBean getSkuPageBean(CutPageBean pageBean, String condition, String status,String categoryStyle);

	/**
	 * 查询产品Product
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @param categoryStyle
	 * @return
	 */
	CutPageBean getProductPageBean(CutPageBean pageBean, String condition, String status,String categoryStyle);
	
	/**
	 * 根据分类获取所有产品
	 * @param categoryIds
	 * @return
	 */
	List<MNProduct> getProuctList(Long brandId,String categoryIds);
}
