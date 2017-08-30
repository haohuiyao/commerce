package com.meeno.ext.product.template.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.ext.product.template.service.TeamplateService;
import com.meeno.ext.product.template.subview.back.TemplateDetailEntity;
import com.meeno.ext.product.template.subview.back.TemplateListItem;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;
import com.meeno.framework.util.ErrMsg;

/**
 * 商品参数模板控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/teamplate")
public class TemplateController {
	private static final Logger LOGGER = Logger.getLogger(TemplateController.class);

	@Autowired
	private TeamplateService teamplateService;

	/**
	 * 根据条件获取参数模板列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"condition":"", "pageIndex":"", "pageSize":""}
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/findTemplateList.do")
	public void findTemplateList(HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");// 查询条件

			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);

			pageBean = this.teamplateService.findTemplateParamList(pageBean, condition);

			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", pageBean.getTotalRows());
			// 列表
			List<TemplateListItem> list = Lists.newArrayList();
			if (pageBean.getDataList() != null && pageBean.getDataList().size() > 0) {
				List<MNParamTemplate> templateList = (List<MNParamTemplate>) pageBean.getDataList();

				for (MNParamTemplate template : templateList) {
					TemplateListItem item = new TemplateListItem();
					item.setTemplateId(template.getId());
					item.setName(template.getName());
					item.setCreateTime(template.getCreateTime());
					item.setStatus(template.getStatus());
					list.add(item);
				}
			}
			resultData.put("list", list);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
			LOGGER.debug("根据条件获取参数模板列表——请求成功：" + JSON.toJSONString(resultData));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取参数模板详情
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"templateId":""}
	 */
	@RequestMapping("/getTemplateDetail.do")
	public void getTemplateDetail(HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long templateId = json.getLong("templateId");// 参数模板ID
			MNParamTemplate template = this.teamplateService.getTemplateParam(templateId);
			if (template == null) {
				CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, ErrMsg.TEMPLATE_NOT_EXIST));
				LOGGER.debug("获取参数模板详情--参数模板不存在");
				return;
			}

			// 封装数据
			TemplateDetailEntity detailEntity = new TemplateDetailEntity();
			detailEntity.setTemplateId(template.getId());
			detailEntity.setName(template.getName());
			detailEntity.setContent(template.getContent());
			detailEntity.setCreateTime(template.getCreateTime());
			detailEntity.setStatus(template.getStatus());

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", detailEntity));
			LOGGER.debug("获取参数模板详情--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 新增参数模板
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"name":"", "content":""}
	 */
	@RequestMapping("/addTemplateParam.action")
	public void addTemplateParam(HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String name = json.getString("name");// 模板名称
			String content = json.getString("content");// 模板内容

			this.teamplateService.addTemplateParam(name, content);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, ""));
			LOGGER.debug("新增参数模板--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 修改参数模板
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"templateId":"", "name":"", "content":""}
	 */
	@RequestMapping("/editTemplateParam.action")
	public void editTemplateParam(HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long templateId = json.getLong("templateId");// 模板ID
			String name = json.getString("name");// 模板名
			String content = json.getString("content");// 模板内容
			this.teamplateService.editTemplateParam(templateId, name, content);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("修改参数模板--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 删除参数模板
	 * 
	 * @param response
	 * @param data
	 *            {"id":""}
	 */
	@RequestMapping("delTeamplate.action")
	public void delParamTemplate(HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			this.teamplateService.delTemplateParam(id);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
}
