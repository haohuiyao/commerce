package com.meeno.ext.product.template.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.tag.QueryPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.util.StringContant;

@Repository
public class TeamplateDaoImpl extends BaseDaoImpl implements TeamplateDao {

	@Override
	public MNParamTemplate findParamTemplateByName(String name) {
		List<MNParamTemplate> list = this.find("from MNParamTemplate p where p.name = ? and p.status='A' ", name);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public CutPageBean findParamTemplateList(CutPageBean pageBean, String condition) {
		List<Object> paramValues = Lists.newArrayList();
		StringBuilder queryCondition = new StringBuilder(" from MNParamTemplate m where m.status = ?");
		paramValues.add(StringContant.STATUS_AVA);
		if (CommonUtil.isNotZeroLengthTrimString(condition)) {
			Long id;
			try {
				id = Long.valueOf(condition);
			} catch (Exception e) {
				id = null;
			}
			if (id != null) {
				queryCondition.append(" and ( m.id = ? or m.name like ? )");
				paramValues.add(id);
				paramValues.add("%" + condition + "%");
			} else {
				queryCondition.append(" and m.name like ?");
				paramValues.add("%" + condition + "%");
			}
		}

		String resultSql = queryCondition.toString();
		String totalRowsHsql = " select count(m) " + queryCondition.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by m.createTime desc ", paramValues.toArray(new Object[] {}), pageBean, null);
	}

	@Override
	public boolean checkParamTemplate(Long id) {
		// TODO Auto-generated method stub
		List<MNProduct> list = this.find(" from MNProduct p where p.paramTemplate.id=?", id);
		if(!CollectionUtils.isEmpty(list)){
			return true;
		}
		
		List<MNSku> skuList=this.find(" from MNSku s where s.paramTemplate.id=?", id);
		if(!CollectionUtils.isEmpty(skuList)){
			return true;
		}
		return false;
	}

}
