package com.meeno.ext.product.category.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.entity.WPRecommendGenre;
import com.meeno.ext.product.category.service.CategoryService;
import com.meeno.ext.product.category.subview.back.CategoryEntity;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

/**
 * 商品分类控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	private static final Logger LOGGER = Logger.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	/**
	 * 管理员新增商品分类
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"name":"",
	 *            "imgUrl":"","abbreviation":"","parentId":"","paramsTempletId":
	 *            ""}
	 */
	@RequestMapping("/addCategory.action")
	public void addCategory(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String name = json.getString("name");// 分类名
			String abbreviation = json.getString("abbreviation");// 首字母缩写
			String imgUrl = json.getString("imgUrl");// 图片地址
			String parentId = json.getString("parentId");// 细类还是大类
			Long paramsTempletId = json.getLong("paramsTempletId");
			this.categoryService.addCategory(name, abbreviation, imgUrl, parentId, paramsTempletId);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员新增商品分类--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 管理员删除商品分类
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"categoryId":"1"}
	 */
	@RequestMapping("/delCategory.action")
	public void delCategory(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long categoryId = json.getLong("categoryId");
			this.categoryService.delCategory(categoryId);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 管理员修改商品分类顺序
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"categoryId":"1", "newOrder":""}
	 */
	@RequestMapping("/changeCategoryOrder.do")
	public void changeCategoryOrder(HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			// Long categoryId = json.getLong("categoryId");// 分类Id
			// Integer newOrder = json.getInteger("newOrder");// 目标位置
			//
			// this.categoryService.changeCategoryOrder(categoryId, newOrder);
			//
			// CommonUtil.toJson(response, new
			// ResponseBean(Constant.RESPONSE_SUCCESS, ""));
			LOGGER.debug("修改商品分类顺序--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 管理员编辑商品分类信息
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"categoryId":"1"}
	 */
	@RequestMapping("/editCategory.action")
	public void editCategory(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long categoryId = json.getLong("categoryId");// 分类Id
			String name = json.getString("name");// 分类名
			String abbreviation = json.getString("abbreviation");// 首字母缩写
			String imgUrl = json.getString("imgUrl");// 图片地址
			Long paramsTempletId = json.getLong("paramsTempletId");

			this.categoryService.editCategory(categoryId, name, abbreviation, imgUrl, paramsTempletId);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员编辑商品分类信息--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取下一级分类列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"pageIndex":"", "pageSize":"","parentId":""}
	 */
	@RequestMapping("/getAllCategories.do")
	public void getAllCategories(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Integer pageIndex = json.getInteger("pageIndex");
			Integer pageSize = json.getInteger("pageSize");
			Long parentId = json.getLong("parentId");

			List<MNCategory> categories = this.categoryService.getAllCategories(parentId);

			List<CategoryEntity> entities = new ArrayList<>();
			for (MNCategory category : categories) {
				CategoryEntity entity = new CategoryEntity(category);
				// entity.setCategoryId(category.getId());
				// entity.setName(category.getName());
				// entity.setImgUrl(category.getImgUrl());
				// entity.setAbbreviation(category.getAbbreviation());
				// Set<WPRecommendGenre> genres = category.getGenres();
				// if (genres != null && genres.size() > 0) {
				// entity.setType(genres.iterator().next().getCategory().getId());
				// } else {
				// entity.setType(null);
				// }
				// entity.setTemplateId((category.getParamTemplate() != null) ?
				// category.getParamTemplate().getId() : -1);
				entities.add(entity);
			}

			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", entities.size());

			entities = CommonUtil.subList(entities, pageIndex, pageSize);
			resultData.put("list", entities);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
			LOGGER.debug("获取分类列表--成功");

		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取所有子类分类列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"pageIndex":"", "pageSize":"","parentId":""}
	 */
	@RequestMapping("/getAllChildCategory.do")
	public void getAllChildCategory(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Integer pageIndex = json.getInteger("pageIndex");
			Integer pageSize = json.getInteger("pageSize");
			Long parentId = json.getLong("parentId");

			List<CategoryEntity> entities = this.categoryService.getAllChildCategory(parentId);

//			List<CategoryEntity> entities = new ArrayList<>();
//			for (MNCategory category : categories) {
//				CategoryEntity entity = new CategoryEntity(category);
//				entities.add(entity);
//			}

			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", entities.size());

			entities = CommonUtil.subList(entities, pageIndex, pageSize);
			resultData.put("list", entities);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
			LOGGER.debug("获取分类列表--成功");

		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 修改商品分类的参数模板
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"categoryId":"1", "templateId":"1"}
	 */
	@RequestMapping("/editCategoryTemplate.action")
	public void editCategoryTemplate(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long categoryId = json.getLong("categoryId");// 分类Id
			Long templateId = json.getLong("templateId");// 分类模板Id

			this.categoryService.editCategoryTemplate(categoryId, templateId);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, ""));
			LOGGER.debug("修改商品分类的参数模板--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
}
