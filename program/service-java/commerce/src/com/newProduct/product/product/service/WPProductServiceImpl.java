package com.newProduct.product.product.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeno.ext.product.brand.dao.BrandDao;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.dao.CategoryDao;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNProductRel;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.entity.Source;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.ext.product.util.EnumObjType;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;
import com.newProduct.product.machine.subview.back.MachineBackItem;
import com.newProduct.product.product.dao.WPProductDao;
import com.newProduct.product.product.subview.back.SkuRelBackItem;

@Service
public class WPProductServiceImpl extends BaseDaoImpl implements WPProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductService productService;
	@Autowired
	private WPProductDao wpproductDao;
	@Autowired
	private BrandDao brandDao;

	@Override
	public void addWPProduct(String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId, String skuName,
			String brandStr) {
		MeenoAssert.notNull(name, "产品名称不能为空");
		// MeenoAssert.notNull(cost, "产品成本价不能为空");
		MeenoAssert.notNull(categoryId, "请选择产品分类");
		MeenoAssert.notNull(paramsId, "请选择参数模板");
		MeenoAssert.notNull(brandId, "请选择产品品牌");
		// MeenoAssert.notNull(sourceId, "请选择产品货源");
		// MeenoAssert.notNull(currency, "是否为统一价");

		MNCategory category = this.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
		MNParamTemplate paramTemplate = this.get(MNParamTemplate.class, paramsId);
		MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
		Brand brand = this.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);

		// 添加机型组
		MNProduct product = new MNProduct();
		product.setName(name);
		product.setSummary(summary);
		product.setDetail(detail);
		product.setParamsValue(params);
		product.setPlace(place);
		product.setCode(code);
		product.setCost(cost);
		product.setDisplayImg(displayImg);
		product.setPicture(picture);
		product.setCommon(common);
		product.setCurrency(currency);
		product.setVolume(volume);
		product.setWeight(weight);
		product.setCategory(category);
		product.setParamTemplate(paramTemplate);
		product.setBrand(brand);
		product.setCreateTime(new Date());
		product.setStatus(StringContant.STATUS_AVA);
		this.save(product);

		// 添加具体机型
		JSONArray brandAry = JSONArray.parseArray(brandStr);
		if (brandAry != null && brandAry.size() > 0) {
			for (int i = 0; i < brandAry.size(); i++) {
				JSONObject obj = brandAry.getJSONObject(i);
				// 无brand 修改会很复杂
				Long skuBrandId = obj.getLong("brandId");
				// 通用
				Brand skuBrand = null;
				if (!ProductContant.OBJ_TYPE_ALLBARND_ID.equals(skuBrandId)) {
					skuBrand = this.productDao.get(Brand.class, skuBrandId);
					MeenoAssert.notNull(skuBrand, ProductError.BRAND_NOT_EXIST);
				}

				MNSku sku = new MNSku();
				sku.setProduct(product);
				sku.setParamTemplate(paramTemplate);
				sku.setParamsValue(params);
				sku.setPicture(picture);
				sku.setDetail(detail);
				sku.setSkuName(skuName);
				sku.setWeight(weight);
				sku.setDisplayImg(displayImg);
				sku.setSummary(summary);
				sku.setVolume(volume);
				sku.setCreateTime(new Date());
				sku.setBrand(skuBrand);
				sku.setStatus(StringContant.STATUS_AVA);
				sku.setCode(code + "-" + (i + 1));

				StringBuffer adaptation = new StringBuffer(skuBrand == null ? "通用" : (skuBrand.getName() + " "));
				// 适配关联关系
				JSONArray json = obj.getJSONArray("rel");
				String rel = json.toJSONString();
				for (int j = 0; j < json.size(); j++) {
					JSONObject jsonRel = json.getJSONObject(j);
					int objType2 = jsonRel.getIntValue("objType");
					Long objId2 = jsonRel.getLong("id");

					if (EnumObjType.BRAND.getType() == objType2) {
						continue;
					}
					adaptation.append(this.productService.getAdatation(objType2, objId2) + " ");

				}

				adaptation.append(brand.getName() + " " + product.getName());
				sku.setAdaptation(adaptation.toString());
				this.productDao.save(sku);
				if (CommonUtil.isNotZeroLengthTrimString(rel)) {
					this.productService.saveProductRel(ProductContant.OBJ_TYPE_SKU, sku.getId(), rel);
				}
			}
		}
	}

	@Override
	public void editWPProduct(Long id, String name, String summary, String detail, String params, String place,
			String code, BigDecimal cost, String displayImg, String picture, String common, String currency,
			String volume, String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId,
			String skuName, String brandStr) {
		MNProduct product = this.productDao.get(MNProduct.class, id);
		MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		MeenoAssert.notNull(name, "产品名称不能为空");
		// MeenoAssert.notNull(cost, "产品成本价不能为空");
		MeenoAssert.notNull(categoryId, "请选择产品分类");
		MeenoAssert.notNull(paramsId, "请选择参数模板");
		MeenoAssert.notNull(brandId, "请选择产品品牌");
		// MeenoAssert.notNull(sourceId, "请选择产品货源");
		// MeenoAssert.notNull(currency, "是否为统一价");

		MNCategory category = this.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
		MNParamTemplate paramTemplate = this.get(MNParamTemplate.class, paramsId);
		MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
		Brand brand = this.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		// Source source = this.get(Source.class, sourceId);
		// MeenoAssert.notNull(source, ProductError.SOURCE_NOT_EXIST);
		product.setName(name);
		product.setSummary(summary);
		product.setDetail(detail);
		product.setParamsValue(params);
		product.setPlace(place);
		product.setCode(code);
		product.setCost(cost);
		product.setDisplayImg(displayImg);
		product.setPicture(picture);
		product.setCommon(common);
		product.setCurrency(currency);
		product.setVolume(volume);
		product.setWeight(weight);
		product.setCategory(category);
		product.setParamTemplate(paramTemplate);
		// product.setSource(source);
		product.setBrand(brand);
		product.setCreateTime(new Date());
		product.setStatus(StringContant.STATUS_AVA);
		this.update(product);

		// 下架所有Sku
		wpproductDao.putOutSkuByProduct(id);
		// 删除所有Sku关联 重新添加
		Long[] skuIds = this.wpproductDao.getSkuIdsByProduct(id);
		this.wpproductDao.delSkuRelByProduct(skuIds);

		JSONArray brandAry = JSONArray.parseArray(brandStr);
		if (brandAry != null && brandAry.size() > 0) {
			for (int i = 0; i < brandAry.size(); i++) {
				JSONObject json = brandAry.getJSONObject(i);
				Long skuBrandId = json.getLong("brandId");
				// 通用
				Brand skuBrand = null;
				if (!ProductContant.OBJ_TYPE_ALLBARND_ID.equals(skuBrandId)) {
					skuBrand = this.productDao.get(Brand.class, skuBrandId);
					MeenoAssert.notNull(skuBrand, ProductError.BRAND_NOT_EXIST);
				}
				MNSku sku = this.wpproductDao.findSkuByBrandAndPro(id, skuBrandId);

				// StringBuffer adaptation = new StringBuffer(brand.getName() +
				// " ");
				StringBuffer adaptation = new StringBuffer(skuBrand == null ? "通用 " : (skuBrand.getName() + " "));
				// 适配关联关系
				String rel = json.getString("rel");
				JSONArray jsonArray = JSONArray.parseArray(rel);
				for (int j = 0; j < jsonArray.size(); j++) {
					JSONObject jsonRel = jsonArray.getJSONObject(j);
					int objType2 = jsonRel.getIntValue("objType");
					Long objId2 = jsonRel.getLong("id");

					adaptation.append(this.productService.getAdatation(objType2, objId2) + " ");
				}
				if (sku != null) {
					sku.setAdaptation(adaptation.toString());
					sku.setStatus(StringContant.STATUS_AVA);
					this.productDao.update(sku);
				} else {
					sku = new MNSku();
					sku.setProduct(product);
					sku.setParamTemplate(paramTemplate);
					sku.setParamsValue(params);
					sku.setAdaptation(adaptation.toString());
					sku.setPicture(picture);
					sku.setDetail(detail);
					sku.setSkuName(skuName);
					sku.setWeight(weight);
					sku.setDisplayImg(displayImg);
					sku.setSummary(summary);
					sku.setVolume(volume);
					sku.setCreateTime(new Date());
					sku.setStatus(StringContant.STATUS_AVA);
					sku.setSkuName(skuName);
					sku.setBrand(skuBrand);
					sku.setCode(code + "-" + (i + 1));
					this.productDao.save(sku);
				}

				if (CommonUtil.isNotZeroLengthTrimString(rel)) {
					this.productService.saveProductRel(ProductContant.OBJ_TYPE_SKU, sku.getId(), rel);
				}
			}
		}
	}

	@Override
	public void editPartsSku(Long skuId, String rel,String detail,String params,String displayImg,String picture) {
		MNSku sku = this.productDao.get(MNSku.class, skuId);
		MeenoAssert.notNull(sku, ProductError.SKU_NOT_EXIST);
		// 删除所有Sku关联 重新添加
		Long[] skuIds=new Long[]{skuId};
		this.wpproductDao.delSkuRelByProduct(skuIds);
		sku.setDetail(detail);
		sku.setParamsValue(params);
		sku.setDisplayImg(displayImg);
		sku.setPicture(picture);
		
		StringBuffer adaptation = new StringBuffer(sku.getBrand() == null ? "通用 " : sku.getBrand().getName() + " ");
		// 适配关联关系
		JSONArray jsonArray = JSONArray.parseArray(rel);
		for (int j = 0; j < jsonArray.size(); j++) {
			JSONObject jsonRel = jsonArray.getJSONObject(j);
			int objType2 = jsonRel.getIntValue("objType");
			Long objId2 = jsonRel.getLong("id");

			adaptation.append(this.productService.getAdatation(objType2, objId2) + " ");
		}
		sku.setAdaptation(adaptation.toString());
		this.productDao.update(sku);
		// 保存关联关系
		if (CommonUtil.isNotZeroLengthTrimString(rel)) {
			this.productService.saveProductRel(ProductContant.OBJ_TYPE_SKU, sku.getId(), rel);
		}
	}

	@Override
	public List<SkuRelBackItem> getProductRelById(Long productId) {
		// TODO Auto-generated method stub
		List<SkuRelBackItem> backItems = new ArrayList<>();
		List<MNSku> list = this.wpproductDao.getSkuByProduct(productId);
		if (list != null) {
			for (MNSku sku : list) {
				SkuRelBackItem item = new SkuRelBackItem();
				item.setSkuId(sku.getId());
				if (sku.getBrand() != null) {
					item.setBrandId(sku.getBrand().getId());
					item.setBrandName(sku.getBrand().getName());
				} else {
					// 品牌通用
					item.setBrandId(Long.valueOf(0));
					item.setBrandName(ProductContant.OBJ_REL_ALLBAND);
					break;
				}
				Map<String, List> productRel = this.productService.findProdcutRel(ProductContant.OBJ_TYPE_SKU,
						sku.getId(), null);
				item.setProductRel(productRel);
				backItems.add(item);
			}
		}
		return backItems;
	}

	@Override
	public CutPageBean getSkuPageBean(CutPageBean pageBean, String condition, String status, String categoryName) {
		// TODO Auto-generated method stub
		String categoryStyle = null;
		if (CommonUtil.isNotZeroLengthTrimString(categoryName)) {
			MNCategory category = this.categoryDao.findCategoryByName(categoryName);
			if (category.getStyle() != null && category.getLevel() != null) {
				categoryStyle = category.getStyle().substring(0, category.getLevel() * 2);
			}
		}
		return this.wpproductDao.getSkuPageBean(pageBean, condition, status, categoryStyle);
	}

	@Override
	public CutPageBean getProductPageBean(CutPageBean pageBean, String condition, String status, String categoryName) {
		MNCategory category = this.categoryDao.findCategoryByName(categoryName);
		String categoryStyle = null;
		if (category.getStyle() != null && category.getLevel() != null) {
			categoryStyle = category.getStyle().substring(0, category.getLevel() * 2);
		}
		return this.wpproductDao.getProductPageBean(pageBean, condition, status, categoryStyle);
	}

	@Override
	public List<Brand> frontGetBrandByCategoryName(String categoryName) {
		// TODO Auto-generated method stub
		MNCategory category = this.categoryDao.findCategoryByName(categoryName);
		MeenoAssert.notNull(category, categoryName + "不存在");
		String style = category.getStyle().substring(0, category.getLevel() * 2);
		return this.brandDao.getBrandLikeCategoryStyle(style);
	}

	@Override
	public List<MNCategory> frontGetPartsCategory() {
		// TODO Auto-generated method stub
		MNCategory category = this.categoryDao.findCategoryByName(ProductContant.CATEGORY_PARTS);
		MeenoAssert.notNull(category, "配件分类不存在");
		return this.categoryDao.getAllCategories(category.getId());
	}

	@Override
	public Map<String, List> findProdcutRel(Integer objType, Long objId, Integer targetType) {
		return this.productService.findProdcutRel(objType, objId, targetType);
	}
}
