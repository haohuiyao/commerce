package com.meeno.ext.product.goods.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.meeno.ext.product.goods.entity.Source;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.tag.QueryPageBean;

@Repository
public class SourceDaoImpl extends BaseDaoImpl implements SourceDao {

	@Override
	public CutPageBean findbyPartsList(CutPageBean pageBean) {
		List<Object> paramValues = Lists.newArrayList();
		StringBuilder queryCondition = new StringBuilder("from Source s where s.status='A' ");

		String resultSql = queryCondition.toString();
		String totalRowsHsql = " select count(s) " + queryCondition.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by s.sourceName desc ", paramValues.toArray(new Object[] {}), pageBean, null);
	}

	@Override
	public Source findSourceByName(String sourceName) {
		List<Source> list=this.find(" from Source s where s.status='A' and s.sourceName=? ", sourceName);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}
}