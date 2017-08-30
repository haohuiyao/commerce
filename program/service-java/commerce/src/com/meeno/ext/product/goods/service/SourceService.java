package com.meeno.ext.product.goods.service;

import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface SourceService extends BaseDao{

	/**
	 * 分页获取SourceService列表
	 */
	public CutPageBean findbyPartsList(CutPageBean pageBean);
	
	/**
	 * 新增货源
	 * @param sourceName
	 */
	void addSource(String sourceName);
	
	/**
	 * 编辑货源
	 * @param id
	 * @param sourceName
	 */
	void editSource(Long id,String sourceName);
}
