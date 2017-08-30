package com.meeno.ext.product.template.dao;

import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface TeamplateDao extends BaseDao {
	/**
	 * 根据模板名称查找参数模板
	 * 
	 * @param name
	 * @return
	 */
	MNParamTemplate findParamTemplateByName(String name);

	/**
	 * 分页获取参数模板列表
	 * 
	 * @param pageBean
	 * @param condition
	 *            条件（ID或参数模板名称）
	 * @return
	 */
	CutPageBean findParamTemplateList(CutPageBean pageBean, String condition);
	
	/**
	 * 检查参数模板是否有关联
	 * @param id
	 */
	boolean checkParamTemplate(Long id);
}
