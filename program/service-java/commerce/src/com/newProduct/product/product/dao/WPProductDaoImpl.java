package com.newProduct.product.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.tag.QueryPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.util.StringContant;

@Repository
public class WPProductDaoImpl extends BaseDaoImpl implements WPProductDao {

	@Override
	public void putOutSkuByProduct(Long productId) {
		this.hibernateTemplate.bulkUpdate(" update MNSku s set s.status=? where s.product.id=?",
				StringContant.STATUS_DIS, productId);
	}

	@Override
	public MNSku findSkuByBrandAndPro(Long productId, Long brandId) {
		StringBuffer queryHql = new StringBuffer(" from MNSku s where 1=1");
		List<Object> params = new ArrayList<>();
		if (productId != null) {
			queryHql.append(" and s.product.id=?");
			params.add(productId);
		}
		if (brandId != null) {
			if (ProductContant.OBJ_TYPE_ALLBARND_ID.equals(brandId)) {
				queryHql.append(" and s.brand is null");
			} else {
				queryHql.append(" and s.brand.id=?");
				params.add(brandId);
			}
		}
		List<MNSku> list = this.find(queryHql.toString(), params.toArray());
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void delSkuRelByProduct(Long[] skuIds) {
		String ids = CommonUtil.getIdSQLValue(skuIds);
		this.hibernateTemplate.bulkUpdate(" delete MNProductRel p where p.objType1=? and p.objId1 in(" + ids + ")",
				ProductContant.OBJ_TYPE_SKU);
	}

	@Override
	public Long[] getSkuIdsByProduct(Long productId) {
		List<Long> list = this.find(" select s.id from MNSku s where s.product.id=? ", productId);
		if (!CollectionUtils.isEmpty(list)) {
			return list.toArray(new Long[list.size()]);
		}
		return null;
	}

	@Override
	public List<MNSku> getSkuByProduct(Long productId) {
		List<MNSku> list = this.find(" from MNSku s where s.product.id=? and s.status=?", productId,
				StringContant.STATUS_AVA);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

	@Override
	public CutPageBean getSkuPageBean(CutPageBean pageBean, String condition, String status, String categoryStyle) {
		StringBuffer queryHql = new StringBuffer(" from MNSku s where 1=1 ");
		JSONObject json = JSONObject.parseObject(condition);
		Long productId = json.getLong("productId");
		List<Object> paramValues = Lists.newArrayList();

		if (CommonUtil.isNotZeroLengthTrimString(status)) {
			queryHql.append(" and s.status=?");
			paramValues.add(status);
		}

		if (productId != null) {
			queryHql.append(" and s.product.id=?");
			paramValues.add(productId);
		}

		if (CommonUtil.isNotZeroLengthTrimString(categoryStyle)) {
			queryHql.append(" and s.product.category.style like ?");
			paramValues.add(categoryStyle + "%");
		}

		String resultSql = " select s " + queryHql.toString();
		String totalRowsHsql = " select count(s) " + queryHql.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by s.createTime desc ", paramValues.toArray(new Object[] {}), pageBean, null);
	}

	@Override
	public CutPageBean getProductPageBean(CutPageBean pageBean, String condition, String status, String categoryStyle) {
		StringBuffer queryHql = new StringBuffer(" from MNProduct p where 1=1 ");
		JSONObject json = JSONObject.parseObject(condition);
		List<Object> paramValues = Lists.newArrayList();

		if (CommonUtil.isNotZeroLengthTrimString(status)) {
			queryHql.append(" and p.status=?");
			paramValues.add(status);
		}

		if (CommonUtil.isNotZeroLengthTrimString(categoryStyle)) {
			queryHql.append(" and p.category.style like ?");
			paramValues.add(categoryStyle + "%");
		}

		String resultSql = " select p " + queryHql.toString();
		String totalRowsHsql = " select count(p) " + queryHql.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by p.createTime desc ", paramValues.toArray(new Object[] {}), pageBean, null);
	}

	@Override
	public List<MNProduct> getProuctList(Long brandId, String categoryIds) {
		StringBuffer queryHql = new StringBuffer(" from MNProduct p where 1=1 ");
		List<Object> params = Lists.newArrayList();

		if (brandId != null) {
			queryHql.append(" and p.brand.id=?");
			params.add(brandId);
		}

		if (categoryIds != null) {
			queryHql.append(" and p.category.id in (" + categoryIds + ")");
		}
		return this.find(queryHql.toString(), params.toArray());
	}

}
