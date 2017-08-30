package com.meeno.ext.product.goods.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.MustJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNProductRel;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.tag.QueryPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.util.StringContant;

@Repository
public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	@Override
	public CutPageBean findProductList(CutPageBean pageBean, String condition, String status) {
		StringBuffer queryHql = new StringBuffer(" from MNProduct p where 1=1 ");
		JSONObject json = JSONObject.parseObject(condition);

		String title = json.getString("title");
		Long brandId = json.getLong("brandId");
		Long categoryId = json.getLong("categoryId");
		List<Object> paramValues = Lists.newArrayList();

		if (CommonUtil.isNotZeroLengthTrimString(status)) {
			queryHql.append(" and p.status=?");
			paramValues.add(status);
		}

		if (CommonUtil.isNotZeroLengthTrimString(title)) {
			queryHql.append(" and ( p.name like ? or p.code like ? )");
			paramValues.add("%" + title + "%");
			paramValues.add("%" + title + "%");
		}

		if (brandId != null) {
			queryHql.append(" and p.machineBrand.id=?");
			paramValues.add(brandId);
		}

		if (categoryId != null) {
			queryHql.append(" and ( p.category.id=? or p.category.parent.id=? )");
			paramValues.add(categoryId);
			paramValues.add(categoryId);
		}

		String resultSql = " select p " + queryHql.toString();
		String totalRowsHsql = " select count(p) " + queryHql.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by p.createTime desc ", paramValues.toArray(new Object[] {}), pageBean, null);
	}

	@Override
	public CutPageBean findSkuList(CutPageBean pageBean, String condition, String status) {
		StringBuffer queryHql = new StringBuffer(" from MNSku s where 1=1 ");
		JSONObject json = JSONObject.parseObject(condition);

		String title = json.getString("title");
		Long productBrandId = json.getLong("productBrand");
		Long categoryId = json.getLong("categoryId");
		Long productId = json.getLong("productId");
		List<Object> paramValues = Lists.newArrayList();

		// queryHql.append(" and s.product.status=?");
		// paramValues.add(StringContant.STATUS_AVA);

		if (CommonUtil.isNotZeroLengthTrimString(status)) {
			queryHql.append(" and s.status=?");
			paramValues.add(status);
		}

		if (CommonUtil.isNotZeroLengthTrimString(title)) {
			queryHql.append(" and ( s.skuName like ? or s.code like ? )");
			paramValues.add("%" + title + "%");
			paramValues.add("%" + title + "%");
		}

		if (productId != null) {
			queryHql.append(" and s.product.id=?");
			paramValues.add(productId);
		}

		if (categoryId != null) {
			queryHql.append(" and ( s.product.category.id=? or s.product.category.parent.id=? )");
			paramValues.add(categoryId);
			paramValues.add(categoryId);
		}

		if (productBrandId != null) {
			queryHql.append(" and ( s.brand.id=? or s.product.machineBrand.id=? )");
			paramValues.add(productBrandId);
			paramValues.add(productBrandId);
		}
		String resultSql = " select s " + queryHql.toString();
		String totalRowsHsql = " select count(s) " + queryHql.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by s.createTime desc ", paramValues.toArray(new Object[] {}), pageBean, null);
	}

	@Override
	public List<MNProduct> findProductByTemplet(Long categoryId, Long brandId) {
		// TODO Auto-generated method stub
		StringBuffer queryHql = new StringBuffer(" from MNProduct p where 1=1");
		List<Object> params = new ArrayList<>();
		if (categoryId != null) {
			queryHql.append("and ( p.category.id=? or p.category.parent.id=? )");
			params.add(categoryId);
			params.add(categoryId);
		}

		if (brandId != null) {
			queryHql.append(" and p.brand.id=?");
			params.add(brandId);
		}
		List<MNProduct> list = this.find(queryHql.toString(), params.toArray());
		return list;
	}

	@Override
	public List<MNSku> findSkuByProductId(Long productId) {
		StringBuffer queryHql = new StringBuffer(" from MNSku s where s.product.id=?");
		List<MNSku> list = this.find(queryHql.toString(), productId);
		if (!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

	@Override
	public void putOutProduct(Long productId) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.bulkUpdate(" update MNProduct p set p.status='D' where p.id=? ", productId);
	}

	@Override
	public void putOutSku(Long productId, Long skuId) {
		// TODO Auto-generated method stub
		StringBuffer queryHql = new StringBuffer(" update MNSku s set s.status='D' where 1=1");
		List<Object> params = new ArrayList<>();
		if (productId != null) {
			queryHql.append(" and s.product.id=?");
			params.add(productId);
		}

		if (skuId != null) {
			queryHql.append(" and s.id=?");
			params.add(skuId);
		}
		this.hibernateTemplate.bulkUpdate(queryHql.toString(), params.toArray());
	}

	@Override
	public boolean checkExistPorductRel(Integer relType, Integer objType, Long objId, Integer objType2, Long objId2,Integer tag) {
		// TODO Auto-generated method stub
		
		StringBuffer queryHql = new StringBuffer("from MNProductRel p where 1=1");
		List<Object> params = new ArrayList<>();
		if (relType != null) {
			queryHql.append(" and p.relType=? ");
			params.add(relType);
		}
		if (objType != null) {
			queryHql.append(" and p.objType1=?");
			params.add(objType);
		}
		if (objId != null) {
			queryHql.append(" and p.objId1=?");
			params.add(objId);
		}
		if (objType2 != null) {
			queryHql.append(" and p.objType2=?");
			params.add(objType2);
		}
		if (tag != null) {
			queryHql.append(" and p.tag=?");
			params.add(tag);
		}
		List<MNProductRel> list =this.find(queryHql.toString(), params.toArray());
		if(!CollectionUtils.isEmpty(list)){
			return true;
		}
		
		return false;
	}

	@Override
	public Long[] findProductRelIdsByType(Integer relType, Integer objType1, Long objId1, Integer objType2,
			Integer tag) {
		StringBuffer queryHql = new StringBuffer("select p.objId2 as ids from MNProductRel p where 1=1");
		List<Object> params = new ArrayList<>();
		if (relType != null) {
			queryHql.append(" and p.relType=? ");
			params.add(relType);
		}
		if (objType1 != null) {
			queryHql.append(" and p.objType1=?");
			params.add(objType1);
		}
		if (objId1 != null) {
			queryHql.append(" and p.objId1=?");
			params.add(objId1);
		}
		if (objType2 != null) {
			queryHql.append(" and p.objType2=?");
			params.add(objType2);
		}
		if (tag != null) {
			queryHql.append(" and p.tag=?");
			params.add(tag);
		}
//		Query query=this.getSessionFactory().getCurrentSession().createQuery(queryHql.toString());
//		for(int i=0;i<params.size();i++){
//			query.setParameter(i, params.get(i));
//		}
		List<Long> idList = this.find(queryHql.toString(), params.toArray());
		if (CollectionUtils.isEmpty(idList)) {
			return null;
		}
		return idList.toArray(new Long[] {});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MNProductRel> findProductRelList(Integer relType, Integer objType1, Long objId1) {
		StringBuffer queryHql = new StringBuffer(
				"from MNProductRel p where p.objType1=? and p.objId1=? and p.relType = ?");

		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryHql.toString());
		query.setInteger(0, objType1);
		query.setLong(1, objId1);
		query.setInteger(2, relType);

		return query.list();
	}

	/**
	 * 查询SKU
	 * 
	 * @param pageBean
	 * @param keyword
	 * @param categoryStylePrefix
	 * @param sourceId
	 * @param proRels
	 * @return
	 */
	@Override
	public CutPageBean luceneSearchSku(CutPageBean pageBean, String keyword, String categoryStylePrefix, Long sourceId,
			List<String> proRels) {

		FullTextSession fts = Search.getFullTextSession(getSessionFactory().getCurrentSession());
		QueryBuilder qb = fts.getSearchFactory().buildQueryBuilder().forEntity(MNSku.class).get();

		MustJunction mustJunction = null;
		// sku status
		mustJunction = addMustCond(qb, mustJunction, "status", StringContant.STATUS_AVA);
		// goods status
		mustJunction = addMustCond(qb, mustJunction, "product.status", StringContant.STATUS_AVA);
		// brand status
		// mustJunction = addMustCond(qb, mustJunction, "brand.status",
		// StringContant.STATUS_AVA);
		// 分类style前缀
		if (categoryStylePrefix != null) {
			org.apache.lucene.search.Query query = qb.keyword().wildcard().onField("categoryStyle")
					.matching(categoryStylePrefix + "*").createQuery();

			mustJunction = mustJunction.must(query);

		}
		// product rel
		if (proRels.size() > 0) {
			BooleanJunction booleanJunction = null;
			for (String prStr : proRels) {
				booleanJunction = addShouldCond(qb, booleanJunction, "productRel", prStr);
			}
			mustJunction = mustJunction.must(booleanJunction.createQuery());
		}

		// source id
		if (sourceId != null) {
			mustJunction = addMustCond(qb, mustJunction, "productSourceId", String.valueOf(sourceId));
		}
		// keyword
		if (CommonUtil.isNotZeroLengthTrimString(keyword)) {
			mustJunction = addMustCond(qb, mustJunction, "fuzzySearchInfo", keyword);
		}

		org.apache.lucene.search.Query luceneQuery = mustJunction.createQuery();
		FullTextQuery query = fts.createFullTextQuery(luceneQuery, MNSku.class);

		CutPageBean newPage = new CutPageBean();
		newPage.setExport(pageBean.isExport());
		newPage.setPageSize(pageBean.getPageSize());
		newPage.setCurrentPage(pageBean.getCurrentPage());

		query.setFirstResult(newPage.getPageSize() * (newPage.getCurrentPage() - 1));
		query.setMaxResults(newPage.getPageSize());

		newPage.setDataList(query.list());
		newPage.setTotalRows(query.getResultSize());
		return newPage;
	}

	private MustJunction addMustCond(QueryBuilder qb, MustJunction mustJunction, String fieldName, Object matchingObj) {

		org.apache.lucene.search.Query query = qb.keyword().onField(fieldName).matching(matchingObj).createQuery();

		if (mustJunction == null) {
			mustJunction = qb.bool().must(query);
		} else {
			mustJunction = mustJunction.must(query);
		}
		return mustJunction;
	}

	private BooleanJunction addShouldCond(QueryBuilder qb, BooleanJunction booleanJunction, String fieldName,
			Object matchingObj) {

		org.apache.lucene.search.Query query = qb.keyword().onField(fieldName).matching(matchingObj).createQuery();

		if (booleanJunction == null) {
			booleanJunction = qb.bool().should(query);
		} else {
			booleanJunction = booleanJunction.should(query);
		}
		return booleanJunction;
	}

	@Override
	public <T> void hSearchIndex(Class<T> entity) {
		Session session = getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		// ������
		List<T> entities = session.createCriteria(entity).list();
		// ���ν�������
		for (T e : entities) {

			if (e instanceof MNSku) {
				MNSku sku = (MNSku) e;
				List<MNProductRel> prList = findProductRelList(ProductContant.OBJ_REL_PARTS,
						ProductContant.OBJ_TYPE_SKU, sku.getId());

				StringBuilder prStrBuilder = new StringBuilder();
				if (!CollectionUtils.isEmpty(prList)) {
					for (MNProductRel pr : prList) {
						if (prStrBuilder.length() > 0) {
							prStrBuilder.append(" ");
						}
						prStrBuilder.append(
								pr.getRelType() + "$" + pr.getObjType2() + "$" + pr.getObjId2() + "$" + pr.getTag());
					}
				}
				sku.setProductRel(prStrBuilder.toString());
			}
			fullTextSession.index(e);
		}
	}
}