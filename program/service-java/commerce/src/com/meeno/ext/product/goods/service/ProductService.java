package com.meeno.ext.product.goods.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.framework.bean.BaseEntity;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface ProductService extends BaseDao {

	/**
	 * 新增产品
	 * 
	 * @param name
	 * @param summary
	 * @param detail
	 * @param params
	 * @param place
	 * @param code
	 * @param cost
	 * @param displayImg
	 * @param picture
	 * @param common
	 * @param currency
	 * @param volume
	 * @param weight
	 * @param carriageId
	 * @param categoryId
	 * @param paramsId
	 * @param brandId
	 * @param sourceId
	 */
	MNProduct addProduct(String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId);

	/**
	 * 编辑产品
	 */
	void editProduct(Long id, String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId);

	/**
	 * 后台查询产品
	 * 
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @return
	 */
	CutPageBean findProductList(CutPageBean pageBean, String condition, String status);

	/**
	 * 新增sku
	 * 
	 * @param productId
	 * @param paramsId
	 * @param paramValue
	 * @param adaptation
	 * @param picture
	 * @param code
	 * @param detail
	 * @param brandId
	 * @param skuName
	 * @param weight
	 * @param displayImg
	 * @param summary
	 * @param volumn
	 */
	MNSku addSku(Long productId, Long paramsId, String paramValue, String adaptation, String picture, String detail,
			Long brandId, String skuName, String weight, String displayImg, String summary, String volumn);

	/**
	 * 编辑Sku
	 */
	void editSku(Long id, Long productId, Long paramsId, String paramValue, String adaptation, String picture,
			String detail, Long brandId, String skuName, String weight, String displayImg, String summary,
			String volumn);

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
	 * 产品下架
	 * 
	 * @param productId
	 */
	void putOutProduct(Long productId);

	/**
	 * 产品上架
	 * 
	 * @param productId
	 */
	void putUpProduct(Long productId);

	/**
	 * 产品下架
	 * 
	 * @param productId
	 */
	void putOutProduct(List<Long> ids);

	/**
	 * Sku下架
	 * 
	 * @param skuId
	 */
	void putOutSku(Long skuId);

	/**
	 * Sku上架
	 * 
	 * @param skuId
	 */
	void putUpSku(Long skuId);

	/**
	 * 保存产品关系
	 * 
	 * @param relType
	 * @param objType
	 * @param objId
	 * @param rel
	 */
	String saveProductRel(Integer objType, Long objId, String rel);

	/**
	 * 查询产品适配关系
	 * 
	 * @param relType
	 * @param objType
	 * @param objId
	 * @param targetType
	 * @return
	 */
	Map<String, List> findProdcutRel(Integer objType, Long objId, Integer targetType);

	/**
	 * 适配机型
	 * @param objType
	 * @param objId
	 * @return
	 */
	String getAdatation(int objType, Long objId);
	
	/**
	 * 查询SKU
	 * 
	 * @param pageBean
	 * @param condition
	 * @return
	 */
	CutPageBean luceneSearchSku(CutPageBean pageBean, String condition);

	/**
	 * 重生成hibernate search 索引
	 */
	void hSearchReindex();
}
