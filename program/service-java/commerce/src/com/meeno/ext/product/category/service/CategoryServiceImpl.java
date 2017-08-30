package com.meeno.ext.product.category.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Patch;
import javax.swing.plaf.TreeUI;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.dao.CategoryDao;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.entity.MNCategoryBrand;
import com.meeno.ext.product.category.subview.back.CategoryEntity;
import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.common.dao.CommonDao;
import com.meeno.framework.common.entity.MNConfig;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tree.TreeEntity;
import com.meeno.framework.tree.TreeUtil;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.ErrMsg;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

import sun.reflect.generics.tree.Tree;

/**
 * 商品
 * 
 * @author Jeff_Gao
 * 
 * @date 2017年4月14日
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductService productService;

	/**
	 * 新增商品分类
	 * 
	 * @param name
	 * @param imgUrl
	 */
	@Override
	public MNCategory addCategory(String name, String abbreviation, String imgUrl, String parentId,
			Long paramsTeampletId) {
		// 参数检查
		MeenoAssert.hasLength(name, ErrMsg.CATEGORY_NAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(abbreviation, ErrMsg.CATEGORY_NAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(imgUrl, ErrMsg.CATEGORY_NAME_CANT_BE_EMPTY);

		// 检查是否已有同名分类存在
		MNCategory category = categoryDao.findCategoryByName(name);
		if (category != null) {
			throw new BusinessRuntimeException(ErrMsg.DUPLICATED_CATEGORY_NAME);
		}

		// 添加新分类
		MNCategory newCategory = new MNCategory();
		if (paramsTeampletId != null) {
			MNParamTemplate paramTemplate = this.categoryDao.get(MNParamTemplate.class, paramsTeampletId);
			MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
			newCategory.setParamTemplate(paramTemplate);
		}
		newCategory.setName(name);
		newCategory.setImgUrl(imgUrl);
		newCategory.setAbbreviation(abbreviation);
		newCategory.setCreateTime(new Date());
		newCategory.setLevel(1);
		if (!CommonUtil.isZeroLengthTrimString(parentId)) {
			MNCategory mnCategory = categoryDao.get(MNCategory.class, Long.valueOf(parentId));
			newCategory.setParent(mnCategory);
			newCategory.setLevel(mnCategory.getLevel() + 1);
		}
		newCategory.setStatus(StringContant.STATUS_AVA);
		this.categoryDao.saveCategory(newCategory);

		return newCategory;
	}

	@Override
	public void editCategory(Long categoryId, String name, String abbreviation, String imgUrl, Long paramsTeampletId) {

		// 参数检查
		// 分类ID
		MeenoAssert.notNull(categoryId, ErrMsg.CATEGORY_ID_IS_NULL);
		// 分类名称
		MeenoAssert.hasLength(name, ErrMsg.CATEGORY_NAME_CANT_BE_EMPTY);
		// 图片地址
		MeenoAssert.hasLength(abbreviation, ErrMsg.CATEGORY_IMGURL_CANT_BE_EMPTY);
		// 图片地址
		MeenoAssert.hasLength(imgUrl, ErrMsg.CATEGORY_IMGURL_CANT_BE_EMPTY);

		// 检查分类是否存在
		MNCategory category = categoryDao.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ErrMsg.CATEGORY_NOT_EXIST);

		// “全部” 分类不允许修改名称
//		if (StringContant.CATEGORY_NAME_ALL.equals(category.getName()) && !name.equals(category.getName())) {
//			throw new BusinessRuntimeException(ErrMsg.CANT_CHANGE_NAME_OF_THIS_CATEGORY);
//		}
		if ((ProductContant.CATEGORY_PRODUCT.equals(category.getName()) 
				|| ProductContant.CATEGORY_PARTS.equals(category.getName()))&& !name.equals(category.getName())) {
			throw new BusinessRuntimeException(ErrMsg.CANT_CHANGE_NAME_OF_THIS_CATEGORY);
		}
		
		// 修改名称时检查分类名是否已被使用
		if (!category.getName().equals(name)) {
			// 检查是否已有同名分类存在
			MNCategory existCg = categoryDao.findCategoryByName(name);
			if (existCg != null) {
				throw new BusinessRuntimeException(ErrMsg.DUPLICATED_CATEGORY_NAME);
			}
		}

		if (paramsTeampletId != null) {
			MNParamTemplate paramTemplate = this.categoryDao.get(MNParamTemplate.class, paramsTeampletId);
			MeenoAssert.notNull(paramTemplate, ProductError.PARAMS_TEMPLET_NOT_EXIST);
			category.setParamTemplate(paramTemplate);
		}
		category.setName(name);
		category.setAbbreviation(abbreviation);
		category.setImgUrl(imgUrl);
		categoryDao.update(category);
	}

	/**
	 * 获取所有分类列表
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public List<MNCategory> getAllCategories(Long parentId) {

		List<MNCategory> categories = this.categoryDao.getAllCategories(parentId);
		List<MNCategory> newCategories = new ArrayList<>();

		// 根据分类排序参数重新进行排序
		MNConfig orderConfig = this.commonDao.findConfigByName(StringContant.CONFIG_CATEGORY_ORDER);

		if ((orderConfig == null) || (orderConfig.getValue() == null)) {
			return categories;
		}

		Map<String, MNCategory> map = new HashMap<>();
		for (MNCategory category : categories) {
			map.put(String.valueOf(category.getId()), category);
		}

		String[] orderArr = orderConfig.getValue().split(",");
		for (String id : orderArr) {
			if (map.get(id) != null) {
				// 取出的部分分类中有此分类，将其加入新列表中
				newCategories.add(map.get(id));
			}
		}

		return newCategories;
	}

	/**
	 * 修改分类的参数模板
	 * 
	 * @param categoryId
	 * @param templateId
	 */
	@Override
	public void editCategoryTemplate(Long categoryId, Long templateId) {

		// 参数检查
		MeenoAssert.notNull(categoryId, ErrMsg.CATEGORY_ID_IS_NULL);
		MeenoAssert.notNull(templateId, ErrMsg.TEMPLATE_ID_IS_NULL);

		// 检查分类是否存在
		MNCategory category = categoryDao.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ErrMsg.CATEGORY_NOT_EXIST);

		// 检查是否有修改的内容
		MNParamTemplate preTemplate = category.getParamTemplate();
		if (preTemplate != null && preTemplate.getId().equals(templateId)) {
			// 没有任何修改
			throw new BusinessRuntimeException(ProductError.NO_MODIFICATION);
		}

		// 检查模板是否存在且可用
		MNParamTemplate newTemplate = this.categoryDao.get(MNParamTemplate.class, templateId);
		if (newTemplate == null) {
			throw new BusinessRuntimeException(ErrMsg.TEMPLATE_NOT_EXIST);
		}
		if (!StringContant.STATUS_AVA.equals(newTemplate.getStatus())) {
			throw new BusinessRuntimeException(ErrMsg.TEMPLATE_NOT_AVA);
		}

		// 设置分类模板
		category.setParamTemplate(newTemplate);
		categoryDao.update(category);
	}

	@Override
	public void delCategory(Long categoryId) {
		// TODO Auto-generated method stub
		MeenoAssert.notNull(categoryId, ErrMsg.CATEGORY_ID_IS_NULL);
		// 检查分类是否存在
		MNCategory category = categoryDao.get(MNCategory.class, categoryId);
		MeenoAssert.notNull(category, ErrMsg.CATEGORY_NOT_EXIST);

		List<MNProduct> list = this.productDao.findProductByTemplet(categoryId, null);

		if ("0".equals(category.getLevel().toString())) {
			// 一级分类下架所有子分类
			this.categoryDao.putOutCategory(categoryId);
		} else {
			// 产品组判断
			if (CollectionUtils.isEmpty(list)) {
				this.productDao.delete(category);
				return;
			}
		}

		if (!CollectionUtils.isEmpty(list)) {
			for (MNProduct product : list) {
				this.productService.putOutProduct(product.getId());
			}
		}
		category.setStatus(StringContant.STATUS_DIS);
		this.categoryDao.update(category);
	}

	@Override
	public List<CategoryEntity> getAllChildCategory(Long id) {
		// TODO Auto-generated method stub
		List<CategoryEntity> entities=this.getChildCategory(id);
//		String style = category.getStyle().substring(0, category.getLevel() * 2);
//		List<MNCategory> list = this.categoryDao.getAllChildCategory(null, style);
		return entities;
	}
	
	private List<CategoryEntity> getChildCategory(Long id){
		List<MNCategory> list=this.categoryDao.getAllCategories(id);
		List<CategoryEntity> entities=new ArrayList<>();
		if(list!=null && list.size()>0){
			for(MNCategory category:list){
				CategoryEntity entity=new CategoryEntity(category);
				entity.setChilds(this.getChildCategory(category.getId()));
				entities.add(entity);
			}	
		}
		return entities;
	}
}
