package com.newProduct.product.machine.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface ModelService extends BaseDao {
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
	void addSku(Long productId, Long paramsId, String paramValue, String adaptation, String picture, String detail,
			Long brandId, String skuName, String weight, String displayImg, String summary, String volumn,String rel);

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
	 * Sku下架
	 * 
	 * @param skuId
	 */
	void putOutSku(JSONArray skuId);

	/**
	 * Sku上架
	 * 
	 * @param skuId
	 */
	void putUpSku(JSONArray skuId);

	/**
	 * 保存产品关系
	 * 
	 * @param relType
	 * @param objType
	 * @param objId
	 * @param rel
	 */
	void saveProductRel(Integer relType, Integer objType, Long objId, String rel);

	/**
	 * 查询产品适配关系
	 * 
	 * @param relType
	 * @param objType
	 * @param objId
	 * @param targetType
	 * @return
	 */
	Map<String, List> findProdcutRel(Integer relType, Integer objType, Long objId, Integer targetType);
}
