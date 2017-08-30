package com.meeno.ext.product.goods.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNProductRel;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.entity.Source;
import com.meeno.ext.product.goods.subview.back.BrandEntity;
import com.meeno.ext.product.goods.subview.back.ProductEntity;
import com.meeno.ext.product.goods.subview.back.SkuEntity;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.ext.product.util.EnumObjType;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.ErrMsg;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

@Service
public class ProductServiceImpl extends BaseDaoImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public MNProduct addProduct(String name, String summary, String detail, String params, String place, String code,
			BigDecimal cost, String displayImg, String picture, String common, String currency, String volume,
			String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId, Long sourceId) {
		MeenoAssert.notNull(name, "产品名称不能为空");
		MeenoAssert.notNull(cost, "产品成本价不能为空");
		MeenoAssert.notNull(categoryId, "请选择产品分类");
		MeenoAssert.notNull(paramsId, "请选择参数模板");
		MeenoAssert.notNull(brandId, "请选择产品品牌");
		MeenoAssert.notNull(sourceId, "请选择产品货源");
		MeenoAssert.notNull(currency, "是否为统一价");

		MNCategory category = this.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
		MNParamTemplate paramTemplate = this.get(MNParamTemplate.class, paramsId);
		MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
		Brand brand = this.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		Source source = this.get(Source.class, sourceId);
		MeenoAssert.notNull(source, ProductError.SOURCE_NOT_EXIST);
		MNProduct product = new MNProduct();
		if (carriageId != null) {
			FrankingTemplet frankingTemplet = this.get(FrankingTemplet.class, carriageId);
			MeenoAssert.notNull(frankingTemplet, ProductError.FRANKING_TEMPLET_NOT_EXIST);
			product.setFranking(frankingTemplet);
		}
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
		product.setSource(source);
		product.setBrand(brand);
		product.setCreateTime(new Date());
		product.setStatus(StringContant.STATUS_AVA);
		this.save(product);
		return product;
	}

	@Override
	public void editProduct(Long id, String name, String summary, String detail, String params, String place,
			String code, BigDecimal cost, String displayImg, String picture, String common, String currency,
			String volume, String weight, Long carriageId, Long categoryId, Long paramsId, Long brandId,
			Long sourceId) {
		MeenoAssert.notNull(id, ProductError.ID_NULL);
		MNProduct product = this.get(MNProduct.class, id);
		// MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		// MeenoAssert.notNull(name, "产品名称不能为空");
		// MeenoAssert.notNull(cost, "产品成本价不能为空");
		// MeenoAssert.notNull(categoryId, "请选择产品分类");
		// MeenoAssert.notNull(paramsId, "请选择参数模板");
		// MeenoAssert.notNull(brandId, "请选择产品品牌");
		// MeenoAssert.notNull(sourceId, "请选择产品货源");
		// MeenoAssert.notNull(currency, "是否为统一价");
		//
		MNCategory category = this.get(MNCategory.class, categoryId);
		// MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
		// MNParamTemplate paramTemplate = this.get(MNParamTemplate.class,
		// paramsId);
		// MeenoAssert.notNull(paramTemplate,
		// ProductError.PARAMS_TEMPLET_NOT_EXIST);
		Brand brand = this.get(Brand.class, brandId);
		// MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		// Source source = this.get(Source.class, sourceId);
		// MeenoAssert.notNull(source, ProductError.SOURCE_NOT_EXIST);

		if (carriageId != null) {
			FrankingTemplet frankingTemplet = this.get(FrankingTemplet.class, carriageId);
			MeenoAssert.notNull(frankingTemplet, ProductError.FRANKING_TEMPLET_NOT_EXIST);
			product.setFranking(frankingTemplet);
		}
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
		// product.setParamTemplate(paramTemplate);
		// product.setSource(source);
		product.setBrand(brand);
		product.setModifyTime(new Date());
		this.productDao.update(product);
	}

	@Override
	public CutPageBean findProductList(CutPageBean pageBean, String condition, String status) {
		return this.productDao.findProductList(pageBean, condition, status);
	}

	@Override
	public MNSku addSku(Long productId, Long paramsId, String paramValue, String adaptation, String picture,
			String detail, Long brandId, String skuName, String weight, String displayImg, String summary,
			String volumn) {
		// TODO Auto-generated method stub
		MeenoAssert.notNull(productId, ProductError.ID_NULL);
		MNProduct product = this.get(MNProduct.class, productId);
		MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		MeenoAssert.notNull(paramsId, "请选择参数模板");
		MNParamTemplate paramTemplate = this.get(MNParamTemplate.class, paramsId);
		MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
		MeenoAssert.notNull(brandId, "请选择产品品牌");
		Brand brand = this.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		MNSku sku = new MNSku();
		sku.setProduct(product);
		sku.setParamTemplate(paramTemplate);
		sku.setParamsValue(paramValue);
		sku.setAdaptation(adaptation);
		sku.setPicture(picture);
		sku.setDetail(detail);
		sku.setBrand(brand);
		sku.setSkuName(skuName);
		sku.setWeight(weight);
		sku.setDisplayImg(displayImg);
		sku.setSummary(summary);
		sku.setVolume(volumn);
		sku.setCreateTime(new Date());
		sku.setStatus(StringContant.STATUS_AVA);
		this.save(sku);
		return sku;
	}

	@Override
	public CutPageBean findSkuList(CutPageBean pageBean, String condition, String status) {
		return this.productDao.findSkuList(pageBean, condition, status);
	}

	@Override
	public void editSku(Long id, Long productId, Long paramsId, String paramValue, String adaptation, String picture,
			String detail, Long brandId, String skuName, String weight, String displayImg, String summary,
			String volumn) {
		MeenoAssert.notNull(id, ProductError.ID_NULL);
		MNSku sku = this.get(MNSku.class, id);
		MeenoAssert.notNull(sku, ProductError.SKU_NOT_EXIST);
		MeenoAssert.notNull(productId, ProductError.ID_NULL);
		MNProduct product = this.get(MNProduct.class, productId);
		MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		MeenoAssert.notNull(paramsId, "请选择参数模板");
		MNParamTemplate paramTemplate = this.get(MNParamTemplate.class, paramsId);
		MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
		MeenoAssert.notNull(brandId, "请选择产品品牌");
		Brand brand = this.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		sku.setProduct(product);
		sku.setParamTemplate(paramTemplate);
		sku.setParamsValue(paramValue);
		sku.setAdaptation(adaptation);
		sku.setPicture(picture);
		sku.setDetail(detail);
		sku.setBrand(brand);
		sku.setSkuName(skuName);
		sku.setWeight(weight);
		sku.setDisplayImg(displayImg);
		sku.setSummary(summary);
		sku.setVolume(volumn);
		this.productDao.update(sku);
	}

	@Override
	public void putOutProduct(Long productId) {
		// TODO Auto-generated method stub
		MeenoAssert.notNull(productId, ProductError.ID_NULL);
		// 下架产品组
		this.productDao.putOutProduct(productId);
		// 下架产品组下的SKU
		this.productDao.putOutSku(productId, null);
	}

	@Override
	public void putUpProduct(Long productId) {
		MeenoAssert.notNull(productId, ProductError.ID_NULL);
		MNProduct product = this.productDao.get(MNProduct.class, productId);
		MeenoAssert.notNull(product, ProductError.PRODUCT_NOT_EXIST);
		MeenoAssert.isTrue(StringContant.STATUS_DIS.equals(product.getStatus()), ProductError.PRODUCT_ALREADY_PUTUP);
		product.setStatus(StringContant.STATUS_AVA);
		this.productDao.update(product);
	}

	@Override
	public void putOutProduct(List<Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void putOutSku(Long skuId) {
		MeenoAssert.notNull(skuId, ProductError.ID_NULL);
		MNSku mnSku = this.productDao.get(MNSku.class, skuId);
		MeenoAssert.notNull(mnSku, ProductError.SKU_NOT_EXIST);
		MeenoAssert.isTrue(StringContant.STATUS_AVA.equals(mnSku.getStatus()), ProductError.PRODUCT_ALREADY_PUTOUT);
		mnSku.setStatus(StringContant.STATUS_DIS);
		this.productDao.update(mnSku);
	}

	@Override
	public void putUpSku(Long skuId) {
		MeenoAssert.notNull(skuId, ProductError.ID_NULL);
		MNSku mnSku = this.productDao.get(MNSku.class, skuId);
		MeenoAssert.notNull(mnSku, ProductError.SKU_NOT_EXIST);
		MeenoAssert.isTrue(StringContant.STATUS_DIS.equals(mnSku.getStatus()), ProductError.PRODUCT_ALREADY_PUTUP);
		mnSku.setStatus(StringContant.STATUS_AVA);
		this.productDao.update(mnSku);
	}

	@Override
	public String saveProductRel(Integer objType, Long objId, String rel) {
		// TODO Auto-generated method stub
		Date now = new Date();
		if (!MNProductRel.checkProductRelFormat(rel)) {
			throw new BusinessRuntimeException(ProductError.PRODUCT_REL_FORMAT_ERROR);
		}
		MeenoAssert.notNull(objId, ProductError.ID_NULL);
		this.getAdatation(objType, objId);
		JSONArray jsonArray = JSONArray.parseArray(rel);

		// 检查
		Map<Long, Integer> typeIds = new HashMap<>();
		typeIds.put(objId, objType);

		StringBuffer adpatation = new StringBuffer();

		// 品牌是否添加
		boolean brandExist = false;
		boolean productExit = false;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);
			int objType2 = json.getIntValue("objType");
			Long objId2 = json.getLong("id");

			if (typeIds.containsKey(objId2)) {
				if (typeIds.get(objId2).equals(objType2)) {
					throw new BusinessRuntimeException(ProductError.PRODUCT_REL_REPEAT);
				}
			}

			adpatation.append(this.getAdatation(objType2, objId2) + " ");
//			MeenoAssert.isTrue(!this.productDao.checkExistPorductRel(ProductContant.OBJ_REL_PARTS, objType, objId,
//					objType2, objId2), ProductError.PRODUCT_REL_EXIST);

			MNProductRel productRel = new MNProductRel();
			productRel.setRelType(ProductContant.OBJ_REL_PARTS);
			productRel.setObjType1(objType);
			productRel.setObjId1(objId);
			productRel.setObjType2(objType2);
			productRel.setObjId2(objId2);
			productRel.setCreateTime(now);
			// 品牌通用
			if (EnumObjType.ALLBRAND.getType() == objType2) {
				productRel.setObjId2(ProductContant.OBJ_TYPE_ALLBARND_ID);
				productRel.setTag(ProductContant.OBJ_REL_CURRENCY);
			}

			// 品牌通用
			if (EnumObjType.BRAND.getType() == objType2) {
				productRel.setTag(ProductContant.OBJ_REL_CURRENCY);
			}

			// 系列通用
			if (EnumObjType.PRODUCT.getType() == objType2) {
				productRel.setTag(ProductContant.OBJ_REL_CURRENCY);

				// 添加品牌
				if (!brandExist) {
					// 保存品牌
					Brand brand = this.getBrand(objType2, objId2);
					MeenoAssert.notNull(brand, ProductError.PRODUCT_NOT_EXIST);
					MNProductRel brandRel = new MNProductRel();
					brandRel.setRelType(ProductContant.OBJ_REL_PARTS);
					brandRel.setObjType1(objType);
					brandRel.setObjId1(objId);
					brandRel.setObjType2(ProductContant.OBJ_TYPE_BRAND);
					brandRel.setObjId2(brand.getId());
					brandRel.setCreateTime(now);
					brandRel.setTag(ProductContant.OBJ_REL_UN_CURRENCY);
					brandExist = true;
					this.productDao.save(brandRel);
				}
			}

			// 具体机型
			if (ProductContant.OBJ_TYPE_SKU == objType2) {

				// 添加品牌
				if (!brandExist) {
					// 保存品牌
					Brand brand = this.getBrand(objType2, objId2);
					MeenoAssert.notNull(brand, ProductError.PRODUCT_NOT_EXIST);
					MNProductRel brandRel = new MNProductRel();
					brandRel.setRelType(ProductContant.OBJ_REL_PARTS);
					brandRel.setObjType1(objType);
					brandRel.setObjId1(objId);
					brandRel.setObjType2(ProductContant.OBJ_TYPE_BRAND);
					brandRel.setObjId2(brand.getId());
					brandRel.setCreateTime(now);
					brandRel.setTag(ProductContant.OBJ_REL_UN_CURRENCY);
					brandExist = true;
					this.productDao.save(brandRel);
				}

				// 添加系列
				if (!productExit) {
					MNSku sku = (MNSku) this.get(EnumObjType.SKU.getObjClass(), objId2);
					MeenoAssert.notNull(sku, ProductError.SKU_NOT_EXIST);

					MNProductRel machineRel = new MNProductRel();
					machineRel.setRelType(ProductContant.OBJ_REL_PARTS);
					machineRel.setObjType1(objType);
					machineRel.setObjId1(objId);
					machineRel.setObjType2(EnumObjType.PRODUCT.getType());
					machineRel.setObjId2(sku.getProduct().getId());
					machineRel.setCreateTime(now);
					machineRel.setTag(ProductContant.OBJ_REL_UN_CURRENCY);
					productExit = true;
					this.productDao.save(machineRel);
				}
			}

			this.productDao.save(productRel);
			typeIds.put(objId2, objType2);

		}
		return adpatation.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List> findProdcutRel(Integer objType, Long objId, Integer targetType) {
		// TODO Auto-generated method stub
		Map<String, List> map = new HashMap<>();
		if (targetType == null) {
			targetType = 0;
		}
		if (EnumObjType.PRODUCT.getType() == targetType) {
			// 机型系列
			List<ProductEntity> productEntitie = this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
					EnumObjType.PRODUCT.getType(), EnumObjType.PRODUCT.getObjClass(), ProductContant.OBJ_REL_CURRENCY);
			map.put("product", productEntitie);
		} else if (EnumObjType.SKU.getType() == targetType) {
			// 具体机型
			List<SkuEntity> skuEntitie = this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
					EnumObjType.SKU.getType(), EnumObjType.SKU.getObjClass(), null);
			map.put("sku", skuEntitie);
		} else if (EnumObjType.BRAND.getType() == targetType) {
			// 品牌
			List<BrandEntity> brandEntitie = this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
					EnumObjType.BRAND.getType(), EnumObjType.BRAND.getObjClass(), ProductContant.OBJ_REL_CURRENCY);
			map.put("brand", brandEntitie);
		} else if (EnumObjType.ALLBRAND.getType() == targetType) {
			map.put("allBrand", new ArrayList<>());
		} else {
			List<ProductEntity> productEntities = this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
					ProductContant.OBJ_TYPE_PRODUCT, ProductContant.OBJ_TYPE_ENTITY_PRODUCT,
					ProductContant.OBJ_REL_CURRENCY);
			map.put("product", productEntities);

			List<SkuEntity> skuEntities = this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
					ProductContant.OBJ_TYPE_SKU, ProductContant.OBJ_TYPE_ENTITY_SKU, null);
			map.put("sku", skuEntities);

			List<BrandEntity> brandEntities = this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
					EnumObjType.BRAND.getType(), EnumObjType.BRAND.getObjClass(), ProductContant.OBJ_REL_CURRENCY);
			map.put("brand", brandEntities);
		}

		// switch (targetType) {
		// case ProductContant.OBJ_TYPE_PRODUCT:
		// // 机型系列
		// List<ProductEntity> productEntitie =
		// this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
		// ProductContant.OBJ_TYPE_PRODUCT, EnumObjType.PRODUCT.getObjClass());
		// map.put("product", productEntitie);
		// break;
		// case ProductContant.OBJ_TYPE_SKU:
		// // 具体机型
		// List<SkuEntity> skuEntitie =
		// this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
		// ProductContant.OBJ_TYPE_SKU, EnumObjType.SKU.getObjClass());
		// map.put("sku", skuEntitie);
		// break;
		// case ProductContant.OBJ_TYPE_BRAND:
		// // 品牌
		// List<BrandEntity> brandEntitie =
		// this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
		// ProductContant.OBJ_TYPE_SKU, EnumObjType.BRAND.getObjClass());
		// map.put("brand", brandEntitie);
		// break;
		// default:
		// List<ProductEntity> productEntities =
		// this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
		// ProductContant.OBJ_TYPE_PRODUCT,
		// ProductContant.OBJ_TYPE_ENTITY_PRODUCT);
		// map.put("product", productEntities);
		//
		// List<SkuEntity> skuEntities =
		// this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
		// ProductContant.OBJ_TYPE_SKU, ProductContant.OBJ_TYPE_ENTITY_SKU);
		// map.put("sku", skuEntities);
		//
		// List<BrandEntity> brandEntities =
		// this.findListByType(ProductContant.OBJ_REL_PARTS, objType, objId,
		// ProductContant.OBJ_TYPE_SKU, ProductContant.OBJ_TYPE_ENTITY_BRAND);
		// map.put("brand", brandEntities);
		// break;
		// }
		return map;
	}

	public String getAdatation(int objType, Long objId) {
		Object obj = null;
		String name = "";
		switch (objType) {
		case ProductContant.OBJ_TYPE_PRODUCT:
			obj = this.getObjectById(ProductContant.OBJ_TYPE_ENTITY_PRODUCT, objId);
			MeenoAssert.notNull(obj, ProductError.PRODUCT_NOT_EXIST);
			MNProduct product = (MNProduct) obj;
			name = product.getName();
			break;
		case ProductContant.OBJ_TYPE_SKU:
			obj = this.getObjectById(ProductContant.OBJ_TYPE_ENTITY_SKU, objId);
			MeenoAssert.notNull(obj, ProductError.PRODUCT_NOT_EXIST);
			MNSku sku = (MNSku) obj;
			name = sku.getSkuName();
			break;
		case ProductContant.OBJ_TYPE_BRAND:
			obj = this.getObjectById(ProductContant.OBJ_TYPE_ENTITY_BRAND, objId);
			MeenoAssert.notNull(obj, ProductError.PRODUCT_NOT_EXIST);
			Brand brand = (Brand) obj;
			name = brand.getName();
			break;
		case ProductContant.OBJ_TYPE_BASE_ENTITY:
			name = "";
			break;
		default:
			break;
		}
		return name;
	}

	public Brand getBrand(int objType, Long objId) {
		Brand brand = null;
		String name = "";
		switch (objType) {
		case ProductContant.OBJ_TYPE_PRODUCT:
			MNProduct product = (MNProduct) this.getObjectById(ProductContant.OBJ_TYPE_ENTITY_PRODUCT, objId);
			brand = product.getBrand();
			break;
		case ProductContant.OBJ_TYPE_SKU:
			MNSku sku = (MNSku) this.getObjectById(ProductContant.OBJ_TYPE_ENTITY_SKU, objId);
			brand = sku.getProduct().getBrand();
			break;
		default:
			break;
		}
		return brand;
	}

	private List findListByType(Integer relType, Integer objType, Long objId, Integer targetType, Class entityClass,
			Integer tag) {
		Long[] ids = this.productDao.findProductRelIdsByType(relType, objType, objId, targetType, tag);
		if (ids != null) {
			List list = this.findDatasByIds(entityClass, ids);
			switch (targetType) {
			case ProductContant.OBJ_TYPE_PRODUCT:
				List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
				for (MNProduct product : (List<MNProduct>) list) {
					ProductEntity entity = new ProductEntity(product);
					productEntities.add(entity);
				}
				return productEntities;

			case ProductContant.OBJ_TYPE_SKU:
				List<SkuEntity> skuEntities = new ArrayList<SkuEntity>();
				for (MNSku sku : (List<MNSku>) list) {
					SkuEntity entity = new SkuEntity(sku);
					skuEntities.add(entity);
				}
				return skuEntities;
			case ProductContant.OBJ_TYPE_BRAND:
				List<BrandEntity> brandEntities = new ArrayList<BrandEntity>();
				for (Brand brand : (List<Brand>) list) {
					BrandEntity entity = new BrandEntity(brand);
					brandEntities.add(entity);
				}
				return brandEntities;
			default:
				break;
			}
		}
		return new ArrayList<>();
	}

	@Override
	public CutPageBean luceneSearchSku(CutPageBean pageBean, String condition) {

		JSONObject json = JSONObject.parseObject(condition);
		String keyword = json.getString("keyword");
		Long categoryId = json.getLong("categoryId");
		Long sourceId = json.getLong("sourceId");

		// 查询分类
		String categoryStylePrefix = "";
		if (categoryId != null) {
			MNCategory category = productDao.get(MNCategory.class, categoryId);
			if (category != null) {
				categoryStylePrefix = category.getStyle().substring(0, 2 * category.getLevel());
			}
		}

		JSONArray pRelArr = json.getJSONArray("pRelArr");
		List<String> prStrList = new ArrayList<>();
		if ((pRelArr != null) && (pRelArr.size() > 0)) {
			for (int i = 0; i < pRelArr.size(); i++) {
				JSONObject relObj = pRelArr.getJSONObject(i);
				Integer objType2 = relObj.getInteger("objType2");
				Long objId2 = relObj.getLong("objId2");

				prStrList.addAll(getAllContainedProRels(ProductContant.OBJ_REL_PARTS, objType2, objId2, false));
			}
		}

		return productDao.luceneSearchSku(pageBean, keyword, categoryStylePrefix, sourceId, prStrList);
	}

	/**
	 *
	 * @param objType2
	 * @param objId2
	 * @return
	 */
	private List<String> getAllContainedProRels(Integer relType, Integer objType2, Long objId2, boolean onlyCurrency) {

		//
		List<String> proRels = new ArrayList<>();
		if ((relType == null) || (objType2 == null) || (objId2 == null)) {
			return proRels;
		}
		proRels.add(relType + "$" + objType2 + "$" + objId2 + "$" + ProductContant.OBJ_REL_CURRENCY);
		if (!onlyCurrency) {
			// 不仅要通用
			proRels.add(relType + "$" + objType2 + "$" + objId2 + "$" + ProductContant.OBJ_REL_UN_CURRENCY);
		}

		if (objType2 == EnumObjType.SKU.getType()) {
			// 类型为SKU，向上查询PRODUCT关系
			MNSku sku = (MNSku) productDao.get(EnumObjType.SKU.getObjClass(), objId2);
			if (sku != null) {
				MNProduct product = sku.getProduct();
				proRels.addAll(getAllContainedProRels(relType, EnumObjType.PRODUCT.getType(), product.getId(), true));
			}
		} else if (objType2 == EnumObjType.PRODUCT.getType()) {
			// 类型为PRODUCT, 向上查询BRAND关系
			MNProduct product = (MNProduct) productDao.get(EnumObjType.PRODUCT.getObjClass(), objId2);
			if (product != null) {
				Brand brand = product.getBrand();
				proRels.addAll(getAllContainedProRels(relType, EnumObjType.BRAND.getType(), brand.getId(), true));
			}
		} else if (objType2 == EnumObjType.BRAND.getType()) {
			// 类型为BRAND, 向上查询通用品牌关系
			Brand brand = (Brand) productDao.get(EnumObjType.BRAND.getObjClass(), objId2);
			if (brand != null) {
				proRels.addAll(getAllContainedProRels(relType, EnumObjType.ALLBRAND.getType(), 0L, true));
			}
		}
		return proRels;
	}

	@Override
	public void hSearchReindex() {
		productDao.hSearchIndex(MNProduct.class);
		productDao.hSearchIndex(Brand.class);
		productDao.hSearchIndex(MNSku.class);
	}
}
