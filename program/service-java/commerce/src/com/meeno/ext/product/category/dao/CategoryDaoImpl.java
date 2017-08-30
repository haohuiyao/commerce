package com.meeno.ext.product.category.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.entity.MNCategoryBrand;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.tag.QueryPageBean;
import com.meeno.framework.tree.TreeUtil;
import com.meeno.framework.util.CommonUtil;
import com.meeno.util.StringContant;
import com.sun.tracing.dtrace.StabilityLevel;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

	/**
	 * 根据分类名称查找分类
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public MNCategory findCategoryByName(String name) {
		List<MNCategory> list = this.find("from MNCategory b where b.name = ?", name);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取所有分类列表
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public List<MNCategory> getAllCategories(Long parentId) {
		List<Object> paramValues = Lists.newArrayList();
		StringBuilder queryCondition = new StringBuilder(" from MNCategory b where b.status = ?");
		paramValues.add(StringContant.STATUS_AVA);
		if (parentId != null) {
			queryCondition.append(" and b.parent.id = ?");
			paramValues.add(parentId);
		} else {
			queryCondition.append(" and b.parent.id is null");
		}
		// return this.find("from MNCategory b where b.status = ?",
		// StringContant.STATUS_AVA);
		return this.find(queryCondition.toString(), paramValues.toArray(new Object[] {}));
	}

	/**
	 * 分页获取分类列表
	 * 
	 * @param pageBean
	 * @param categoryId
	 *            分类ID
	 * @param name
	 *            分类名称
	 * @return
	 */
	@Override
	public List<MNCategory> findCategoriesList(String categoryId, String name) {
		List<Object> paramValues = Lists.newArrayList();
		StringBuilder queryCondition = new StringBuilder(" from MNCategory m where 1 = 1 ");

		Long id;
		try {
			id = Long.valueOf(categoryId);
		} catch (Exception e) {
			id = null;
		}
		if ((id != null) && CommonUtil.isNotZeroLengthTrimString(name)) {
			queryCondition.append(" and ( m.id = ? or m.name like ? )");
			paramValues.add(id);
			paramValues.add("%" + name + "%");
		} else if (id != null) {
			queryCondition.append(" and m.id = ?");
			paramValues.add(id);
		} else if (CommonUtil.isNotZeroLengthTrimString(name)) {
			queryCondition.append(" and m.name like ?");
			paramValues.add("%" + name + "%");
		}

		return this.find(queryCondition.toString(), paramValues.toArray(new Object[] {}));
	}

	@Override
	public void putOutCategory(Long parentId) {
		this.hibernateTemplate.bulkUpdate(" update MNCategory m set m.status='D' where m.parent.id=? ", parentId);
	}

	@Override
	public List<Brand> getMachineBrandByCategory(List<String> styleList) {
		// TODO Auto-generated method stub
		StringBuffer queryHql = new StringBuffer(" select c.brand from MNCategoryBrand c where 1=1");
		if (styleList.size() > 0) {
			queryHql.append(" and c.category.style in (");
			for (int i = 0; i < styleList.size(); i++) {
				if (i > 0) {
					queryHql.append(" ,");
				}
				queryHql.append(styleList.get(i));
			}
			queryHql.append(" )");
		}
		return this.find(queryHql.toString());
	}

	@Override
	public MNCategoryBrand getCategoryBrand(Long categoryId, Long brandId) {
		// TODO Auto-generated method stub
		List<MNCategoryBrand> list = this.find(" from MNCategoryBrand c where c.category.id=? and c.brand.id=?",
				categoryId, brandId);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void saveCategory(MNCategory category) {
		// TODO Auto-generated method stub
		TreeUtil.setLeveAndStyle(category, this.hibernateTemplate);
		this.save(category);
	}

	@Override
	public List<MNCategory> getAllChildCategory(Long id, String style) {
		// TODO Auto-generated method stub
		StringBuffer queryHql = new StringBuffer(" from MNCategory c where 1=1");
		List<Object> params = new ArrayList<>();
		if (id != null) {
			queryHql.append(" and c.id=?");
			params.add(id);
		}

		if (CommonUtil.isNotZeroLengthTrimString(style)) {
			queryHql.append(" and c.style like ?");
			params.add(style + "%");
		}
		List<MNCategory> list = this.find(queryHql.toString(), params.toArray());
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

	@Override
	public List<MNCategory> getCategoryByBrand(Long id) {
		List<MNCategory> list=this.find("select c.category from MNCategoryBrand c where c.brand.id=?", id);
		if(!CollectionUtils.isEmpty(list)){
			return list;
		}
		return null;
	}
}