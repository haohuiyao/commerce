package com.newProduct.product.machine.service;

import java.math.BigDecimal;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;
import com.newProduct.product.machine.subview.back.MachineBackItem;

public interface MachineService extends BaseDao {
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
	 * @param paramsId
	 * @param brandId
	 * @param sourceId
	 */
	MNProduct addProduct(String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId,Long paramsId, Long brandId, Long sourceId);

	/**
	 * 添加挖机sku
	 * @param productId
	 * @param skus
	 */
	void addMachineSku(Long productId,JSONArray skus);
	
	/**
	 * 编辑产品
	 */
	void editProduct(Long id, String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long paramsId, Long brandId, Long sourceId);

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
	 * 下架产品组
	 * @param id
	 */
	void putOutProduct(JSONArray ids);
	
	/**
	 * 上架产品组
	 * @param ids
	 */
	void putUpProduct(JSONArray ids);
	
	/**
	 * 根据品牌获取商品
	 * @param id
	 * @return
	 */
	Map<String, Object> findProductByBrand(Long id);
	
	/**
	 * 获取机型组详情
	 * @param id
	 * @return
	 */
	MachineBackItem getMachineDetailById(Long id);
}
