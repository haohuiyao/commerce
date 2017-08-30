package com.meeno.ext.product.template.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.ext.product.template.dao.TeamplateDao;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.MeenoAssert;
import com.meeno.util.StringContant;

/**
 * 商品
 * 
 * @author Jeff_Gao
 * 
 * @date 2017年4月14日
 */

@Service
public class TeamplateServiceImpl implements TeamplateService {

	@Autowired
	private TeamplateDao teamplateDao;

	/**
	 * 分页获取参数模板列表
	 * 
	 * @param pageBean
	 * @param condition
	 *            条件（ID或参数模板名称）
	 * @return
	 */
	@Override
	public CutPageBean findTemplateParamList(CutPageBean pageBean, String condition) {
		return this.teamplateDao.findParamTemplateList(pageBean, condition);
	}

	/**
	 * 获取参数模板
	 * 
	 * @param templateId
	 * @return
	 */
	@Override
	public MNParamTemplate getTemplateParam(Long templateId) {
		// 参数检查
		MeenoAssert.notNull(templateId, ProductError.TEMPLATE_ID_IS_NULL);
		return this.teamplateDao.get(MNParamTemplate.class, templateId);
	}

	/**
	 * 编辑参数模板
	 * 
	 * @param templateId
	 * @param name
	 * @param content
	 */
	@Override
	public void editTemplateParam(Long templateId, String name, String content) {
		// 参数检查
		MeenoAssert.notNull(templateId, ProductError.TEMPLATE_ID_IS_NULL);
		MeenoAssert.hasLength(name, ProductError.TEMPLATE_NAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(content, ProductError.TEMPLATE_CONTENT_CANT_BE_EMPTY);

		// 检查模板是否存在
		MNParamTemplate paramTemplate = this.teamplateDao.get(MNParamTemplate.class, templateId);
		if (paramTemplate == null) {
			// 参数模板不存在
			throw new BusinessRuntimeException(ProductError.TEMPLATE_NOT_EXIST);
		}

		// 检查是否有同名模板存在
		MNParamTemplate existPt = this.teamplateDao.findParamTemplateByName(name);
		if (existPt != null && !existPt.getId().equals(paramTemplate.getId())) {
			throw new BusinessRuntimeException(ProductError.DUPLICATED_NAME_TEMPLATE_EXIST);
		}

		// 检查模板格式
		if (!MNParamTemplate.checkTemplateFormat(content)) {
			throw new BusinessRuntimeException(ProductError.TEMPLATE_FORMAT_ERR);
		}

		// 保存新的模板信息
		paramTemplate.setName(name);
		paramTemplate.setContent(content);
		this.teamplateDao.update(paramTemplate);
	}

	@Override
	public void addTemplateParam(String name, String content) {
		// 参数检查
		MeenoAssert.hasLength(name, ProductError.TEMPLATE_NAME_CANT_BE_EMPTY);
		MeenoAssert.hasLength(content, ProductError.TEMPLATE_CONTENT_CANT_BE_EMPTY);

		// 检查是否有同名模板存在
		MNParamTemplate paramTemplate = this.teamplateDao.findParamTemplateByName(name);
		if (paramTemplate != null) {
			throw new BusinessRuntimeException(ProductError.DUPLICATED_NAME_TEMPLATE_EXIST);
		}

		// 检查模板格式
		if (!MNParamTemplate.checkTemplateFormat(content)) {
			throw new BusinessRuntimeException(ProductError.TEMPLATE_FORMAT_ERR);
		}

		//
		MNParamTemplate template = new MNParamTemplate();
		template.setName(name);
		template.setContent(content);
		template.setCreateTime(new Date());
		template.setStatus(StringContant.STATUS_AVA);

		this.teamplateDao.save(template);
	}

	@Override
	public void delTemplateParam(Long id) {
		MeenoAssert.isTrue(!this.teamplateDao.checkParamTemplate(id), "参数模板有关联");
		
	}
}
