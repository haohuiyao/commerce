package com.meeno.ext.product.goods.dao;

import com.meeno.ext.product.goods.entity.Source;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface SourceDao extends BaseDao {
	
	/**
	 * 分页获取SourceService列表
	 * @param pageBean
	 * @return
	 */
	CutPageBean findbyPartsList(CutPageBean pageBean);
	
	/**
	 * 根据名称查询货源
	 * @param sourceName
	 * @return
	 */
	Source findSourceByName(String sourceName);
}