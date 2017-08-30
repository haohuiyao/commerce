package com.meeno.ext.product.template.service;

import com.alibaba.fastjson.JSONObject;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.tag.CutPageBean;

/**
 * 商品
 * 
 * @author Jeff_Gao
 * 
 * @date 2017年4月14日
 */
public interface TeamplateService {

	/**
	 * 分页获取参数模板列表
	 * 
	 * @param pageBean
	 * @param condition
	 *            条件（ID或参数模板名称）
	 * @return
	 */
	CutPageBean findTemplateParamList(CutPageBean pageBean, String condition);

	/**
	 * 获取参数模板
	 * 
	 * @param templateId
	 * @return
	 */
	MNParamTemplate getTemplateParam(Long templateId);
	
	/**
	 * 新增参数模板
	 * @param name
	 * @param content
	 */
	void addTemplateParam(String name, String content);
	
	/**
	 * 编辑参数模板
	 * 
	 * @param templateId
	 * @param name
	 * @param content
	 */
	void editTemplateParam(Long templateId, String name, String content);

	/**
	 * 删除参数模板
	 * @param id
	 */
	void delTemplateParam(Long id);
}
