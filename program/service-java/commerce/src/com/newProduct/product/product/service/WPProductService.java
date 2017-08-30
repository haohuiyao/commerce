package com.newProduct.product.product.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;
import com.newProduct.product.machine.subview.back.MachineBackItem;
import com.newProduct.product.product.subview.back.SkuRelBackItem;

public interface WPProductService extends BaseDao {

	/**
	 * 添加WP商品
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
	void addWPProduct(String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId, String skuName,
			String brandStr);

	/**
	 * 修改WP商品
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
	void editWPProduct(Long id, String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId, String skuName,
			String brandStr);
	
	
	/**
	 * 修改配件
	 * @param skuId
	 * @param rel
	 * @param detail	详情
	 * @param params	参数
	 * @param displayImg	首图
	 * @param picture	图片
	 */
	void editPartsSku(Long skuId,String rel,String detail,String params,String displayImg,String picture);

	/**
	 * 获取产品关联关系
	 * 
	 * @param productId
	 * @return
	 */
	List<SkuRelBackItem> getProductRelById(Long productId);

	/**
	 * 获取产品sku列表
	 * 
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @return
	 */
	CutPageBean getSkuPageBean(CutPageBean pageBean, String condition, String status,String categoryName);
	
	/**
	 * 获取产品product列表
	 * 
	 * @param pageBean
	 * @param condition
	 * @param status
	 * @return
	 */
	CutPageBean getProductPageBean(CutPageBean pageBean, String condition, String status,String categoryName);
	
	/**
	 * 根据分类名称获取品牌
	 * @return
	 */
	List<Brand> frontGetBrandByCategoryName(String categoryName);
	
	/**
	 * 获取配件二级分类列表
	 * @return
	 */
	List<MNCategory> frontGetPartsCategory();
	
	/**
	 * 配件关系
	 * @return
	 */
	Map<String, List> findProdcutRel(Integer objType, Long objId, Integer targetType);
}
