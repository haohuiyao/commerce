package com.newProduct.product.machine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.dao.CategoryDao;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;
import com.newProduct.product.machine.subview.back.MachineBackItem;
import com.newProduct.product.machine.subview.back.ModelBackItem;
import com.newProduct.product.machine.subview.back.ProductBackItem;
import com.newProduct.product.product.dao.WPProductDao;

@Service
public class MachineServiceImpl extends BaseDaoImpl implements MachineService {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private WPProductDao wpproductDao;
	@Override
	public MNProduct addProduct(String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId) {
		// TODO Auto-generated method stub
		// this.productService.addProduct(name, summary, detail, params, place,
		// code, cost, displayImg, picture, common,
		// currency, volume, weight, carriageId, categoryId, paramsId, brandId,
		// sourceId);
		MNCategory category = this.categoryDao.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
		MeenoAssert.notNull(name, "产品名称不能为空");
		MeenoAssert.notNull(brandId, "请选择产品品牌");
		Brand brand = this.productDao.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);

		MNProduct product = new MNProduct();
		product.setName(name);
		product.setCategory(category);
		product.setBrand(brand);
		product.setCreateTime(new Date());
		product.setStatus(StringContant.STATUS_AVA);
		this.productDao.save(product);
		return product;
	}

	@Override
	public void addMachineSku(Long productId, JSONArray skus) {
		MeenoAssert.notNull(productId, "产品组ID为空");
		MNProduct product = this.productDao.get(MNProduct.class, productId);
		MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		Date now = new Date();
		for (int i = 0; i < skus.size(); i++) {
			JSONObject json = skus.getJSONObject(i);
			String skuName = json.getString("name");
			MNSku sku = new MNSku();
			sku.setProduct(product);
			sku.setCreateTime(now);
			sku.setSkuName(skuName);
			sku.setStatus(StringContant.STATUS_AVA);
			sku.setBrand(product.getBrand());
			this.productDao.save(sku);
		}
	}

	@Override
	public void editProduct(Long id, String name, String summary, String detail, String params, String place,
			String code, BigDecimal cost, String displayImg, String picture, String common, String currency,
			String volume, String weight, Long carriageId, Long paramsId, Long brandId, Long sourceId) {
		MNCategory category = this.categoryDao.findCategoryByName(ProductContant.CATEGORY_PRODUCT);
		MeenoAssert.notNull(category, "挖机分类已修改 请联系管理员");
		this.productService.editProduct(id, name, summary, detail, params, place, code, cost, displayImg, picture,
				common, currency, volume, weight, carriageId, category.getId(), paramsId, brandId, sourceId);
	}

	@Override
	public CutPageBean findProductList(CutPageBean pageBean, String condition, String status) {
		return this.productService.findProductList(pageBean, condition, status);
	}

	@Override
	public void putOutProduct(JSONArray ids) {
		if (ids == null || ids.size() == 0) {
			throw new BusinessRuntimeException(ProductError.PRODUCT_ID_NULL);
		}
		Long[] id = CommonUtil.getJsonToLongArray(ids);
		this.productDao.updateStatusByIds(MNProduct.class, StringContant.STATUS_DIS, id);
	}

	@Override
	public void putUpProduct(JSONArray ids) {
		if (ids == null || ids.size() == 0) {
			throw new BusinessRuntimeException(ProductError.PRODUCT_ID_NULL);
		}
		Long[] id = CommonUtil.getJsonToLongArray(ids);
		this.productDao.updateStatusByIds(MNProduct.class, StringContant.STATUS_AVA, id);
	}

	@Override
	public Map<String, Object> findProductByBrand(Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		// 品牌
		Brand brand = this.productDao.get(Brand.class, id);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		ProductBackItem brandItem = new ProductBackItem(brand);

		// 挖机分类
		MNCategory category=this.categoryDao.findCategoryByName(ProductContant.CATEGORY_PRODUCT);
		MeenoAssert.notNull(category, ProductError.CATEGORY_NAME_ERR);
		List<MNCategory> categories=this.categoryDao.getAllChildCategory(null, category.getStyle().substring(0, 2));
		Long[] ids = new Long[categories.size()];
		if(categories!=null){
			for(int i=0;i<categories.size();i++){
				ids[i]=categories.get(i).getId();
			}
		}
		String idStr = CommonUtil.getIdSQLValue(ids);
		List<MNProduct> list = this.wpproductDao.getProuctList(brand.getId(), idStr);
		// 产品
		List<ProductBackItem> items = new ArrayList<>();
		if (!CollectionUtils.isEmpty(list)) {
			for (MNProduct product : list) {
				ProductBackItem productItem = new ProductBackItem(product);
				// 具体机型
				List<MNSku> skus = this.productDao.findSkuByProductId(product.getId());
				if (!CollectionUtils.isEmpty(skus)) {
					List<ProductBackItem> childs = new ArrayList<>();
					for (MNSku sku : skus) {
						ProductBackItem skuItem = new ProductBackItem(sku);
						childs.add(skuItem);
					}
					productItem.setChildItem(childs);
				}
				items.add(productItem);
			}
		}

		brandItem.setChildItem(items);
		resultMap.put("brand", brandItem);
		return resultMap;
	}

	@Override
	public MachineBackItem getMachineDetailById(Long id) {
		// TODO Auto-generated method stub
		MNProduct product = this.productDao.get(MNProduct.class, id);
		MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		MachineBackItem item = new MachineBackItem(product);
		// 具体机型列表
		List<MNSku> list = this.productDao.findSkuByProductId(product.getId());
		List<ModelBackItem> skuList = new ArrayList<>();
		if (list != null) {
			for (MNSku sku : list) {
				ModelBackItem skuItem = new ModelBackItem(sku);
				skuList.add(skuItem);
			}
		}
		item.setSkuList(skuList);
		return item;
	}
}
