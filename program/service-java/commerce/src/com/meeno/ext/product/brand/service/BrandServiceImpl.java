package com.meeno.ext.product.brand.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.meeno.ext.product.brand.dao.BrandDao;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.dao.CategoryDao;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.entity.MNCategoryBrand;
import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tree.TreeUtil;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

@Service
public class BrandServiceImpl extends BaseDaoImpl implements BrandService {
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductService productService;

	@Override
	public void addBrand(String brandName, String img, String abb) {
		// TODO Auto-generated method stub
		MeenoAssert.notNull(brandName, "品牌类型为空");
		MeenoAssert.notNull(img, "品牌类型图片为空");
		Brand existBd = this.brandDao.findBrandByName(brandName);
		if (existBd != null) {
			throw new BusinessRuntimeException("产品品牌已存在");
		}
		Brand brand = new Brand();
		brand.setName(brandName);
		brand.setImgUrl(img);
		brand.setAbbreviation(abb);
		brand.setStatus(StringContant.STATUS_AVA);
		this.save(brand);
	}

	@Override
	public List<Brand> getAllMachineBrand() {
		return this.brandDao.getAllMachineBrand();
	}

	@Override
	public void editBrand(Long id, String name, String img, String abb) {
		// TODO Auto-generated method stub
		MeenoAssert.notNull(id, ProductError.ID_NULL);
		Brand brand = this.get(Brand.class, id);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		MeenoAssert.notNull(name, "品牌类型为空");
		MeenoAssert.notNull(img, "品牌类型图片为空");
		if (!brand.getName().equals(name)) {
			Brand existBd = this.brandDao.findBrandByName(name);
			if (existBd != null) {
				throw new BusinessRuntimeException("产品品牌已存在");
			}
			brand.setName(name);
		}
		brand.setImgUrl(img);
		brand.setAbbreviation(abb);
		// this.update(brand);
		this.brandDao.update(brand);
	}

	@Override
	public void delBrand(Long id) {
		MeenoAssert.notNull(id, ProductError.ID_NULL);
		Brand brand = this.get(Brand.class, id);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		List<MNProduct> list = this.productDao.findProductByTemplet(null, brand.getId());
		if (CollectionUtils.isEmpty(list)) {
			this.productDao.delete(brand);
			return;
		}
		for (MNProduct product : list) {
			this.productService.putOutProduct(product.getId());
		}
		brand.setStatus(StringContant.STATUS_DIS);
		this.brandDao.update(brand);
	}

	@Override
	public void editCategoryBrand(Long brandId, JSONArray categoryId) {
		if (categoryId == null || categoryId.size() == 0) {
			throw new BusinessRuntimeException("请选择分类");
		}
		Brand brand = this.categoryDao.get(Brand.class, brandId);
		MeenoAssert.notNull(brand, ProductError.BRAND_NOT_EXIST);
		// 删除品牌所有分类
		this.brandDao.delBrandCategory(brandId);
		for (int i = 0; i < categoryId.size(); i++) {
			Long Id = Long.parseLong(categoryId.get(i).toString());
			MNCategory category = this.categoryDao.get(MNCategory.class, Id);
			MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
			MeenoAssert.isTrue((this.categoryDao.getCategoryBrand(Id, brandId) == null), "分类品牌已存在");
			MNCategoryBrand categoryBrand = new MNCategoryBrand();
			categoryBrand.setCategory(category);
			categoryBrand.setBrand(brand);
			this.categoryDao.save(categoryBrand);
		}
	}

	@Override
	public List<Brand> getBrandByCategory(Long id) {
		List<String> styleList = new ArrayList<>();
		MNCategory category = this.categoryDao.get(MNCategory.class, id);
		MeenoAssert.notNull(category, ProductError.CATEGORY_NOT_EXIST);
		Integer level = category.getLevel();
		if (level != null) {
			for (int i = 0; i < level; i++) {
				String style = category.getStyle().substring(0, (i + 1) * 2);
				styleList.add(TreeUtil.fillZero(style, (i + 1), ProductContant.MAXLEVEL));
			}
		}
		return this.categoryDao.getMachineBrandByCategory(styleList);
	}

	@Override
	public List<MNCategory> getCategoryByBrand(Long id) {
		return this.categoryDao.getCategoryByBrand(id);
	}
}
