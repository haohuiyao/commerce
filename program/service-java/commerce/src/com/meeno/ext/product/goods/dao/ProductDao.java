package com.meeno.ext.product.goods.dao;

import java.util.List;

import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNProductRel;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface ProductDao extends BaseDao {
	/**
	 * 后台查询产品组
	 * 
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @return
	 */
	CutPageBean findProductList(CutPageBean pageBean, String condition, String status);

	/**
	 * 后台查询sku
	 * 
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @return
	 */
	CutPageBean findSkuList(CutPageBean pageBean, String condition, String status);

	/**
	 * 查询分类下所有的产品组
	 * 
	 * @param categoryId
	 * @return
	 */
	List<MNProduct> findProductByTemplet(Long categoryId, Long brandId);
	
	/**
	 * 查询sku
	 * @param skuId
	 * @return
	 */
	List<MNSku> findSkuByProductId(Long productId);

	/**
	 * 产品组下架
	 * 
	 * @param productId
	 */
	void putOutProduct(Long productId);

	/**
	 * Sku下架
	 * 
	 * @param productId
	 * @param skuId
	 */
	void putOutSku(Long productId, Long skuId);

	/**
	 * 检查关系是否存在
	 * 
	 * @param relType
	 * @param objType
	 * @param objId
	 * @param rel
	 * @return
	 */
	boolean checkExistPorductRel(Integer relType, Integer objType1, Long objId1, Integer objType2, Long objId2,Integer tag);
	
	/**
	 * 根据类型查询Ids
	 * @param relType
	 * @param objType1
	 * @param objId1
	 * @param objType2
	 * @return
	 */
	Long[] findProductRelIdsByType(Integer relType,Integer objType1,Long objId1,Integer objType2,Integer tag);
	
	/**
	 * 根据关系类型查询ProductRel
	 * @param relType
	 * @param objType1
	 * @param objId1
	 * @return
	 */
	List<MNProductRel> findProductRelList(Integer relType,Integer objType1,Long objId1);
	
	/**
	 * 查询SKU
	 * @param pageBean
	 * @param keyword
	 * @param categoryStylePrefix
	 * @param sourceId
	 * @param proRels
	 * @return
	 */
	CutPageBean luceneSearchSku(CutPageBean pageBean, String keyword, String categoryStylePrefix,
			Long sourceId, List<String> proRels);
	
	/**
	 * 生成hibernate search 索引
	 * @param entity
	 */
	<T> void hSearchIndex(Class<T> entity);
}