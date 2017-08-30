package com.meeno.ext.product.brand.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.brand.service.BrandService;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.subview.back.CategoryEntity;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

@Controller
@RequestMapping("/machineBrand")
public class BrandController {
	private static final Logger LOGGER = Logger.getLogger(BrandController.class);

	@Autowired
	private BrandService brandService;

	/**
	 * 管理员新增商品分类
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"name":"", "imgUrl":"","abbreviation":""}
	 */
	@RequestMapping("/addBrand.action")
	public void addBrand(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String name = json.getString("name");// 分类名
			String abbreviation = json.getString("abbreviation");// 首字母缩写
			String imgUrl = json.getString("imgUrl");// 图片地址
			this.brandService.addBrand(name, imgUrl, abbreviation);

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
	 * 获取挖机品牌列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"pageSize","","pageIndex":""}
	 */
	@RequestMapping("/findMachineBrandList.do")
	public void findMachineBrandList(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Integer pageIndex = json.getInteger("pageIndex");
			Integer pageSize = json.getInteger("pageSize");

			List<Brand> list = this.brandService.getAllMachineBrand();
			list = CommonUtil.subList(list, pageIndex, pageSize);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("list", list);
			resultMap.put("totalCount", list.size());
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultMap));
			LOGGER.debug("获取MachineBrand列表——请求成功：" + JSON.toJSONString(list));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 管理员编辑产品组品牌信息
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"brandId":"","name":"",
	 *            "imgUrl":"","abbreviation":""}
	 */
	@RequestMapping("/editBrand.action")
	public void editBrand(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long brandId = json.getLong("brandId");// 分类Id
			String name = json.getString("name");// 分类名
			String abbreviation = json.getString("abbreviation");// 首字母缩写
			String imgUrl = json.getString("imgUrl");// 图片地址
			this.brandService.editBrand(brandId, name, imgUrl, abbreviation);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员编辑产品组品牌信息--成功");

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 删除品牌
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"brandId":""}
	 */
	@RequestMapping("/delBrand.action")
	public void delBrand(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("brandId");// 分类Id
			this.brandService.delBrand(id);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员删除产品组品牌--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 编辑品牌分类
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"brandId":"1","categoryId":[]}
	 */
	@RequestMapping("/editCategoryBrand.action")
	public void editCategoryBrand(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			JSONArray categoryId = json.getJSONArray("categoryId");
			Long brandId = json.getLong("brandId");// 品牌Id
			this.brandService.editCategoryBrand(brandId, categoryId);
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
	 * 根据分类获取品牌列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"id":"1"}
	 */
	@RequestMapping("/getBrandByCategory.do")
	public void getBrandByCategory(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			List<Brand> list = this.brandService.getBrandByCategory(id);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", list));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 根据品牌获取分类列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"id":"1"}
	 */
	@RequestMapping("/getCategoryByBrand.do")
	public void getCategoryByBrand(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			List<MNCategory> categories = this.brandService.getCategoryByBrand(id);
			List<CategoryEntity> entities = new ArrayList<>();
			if (categories != null && categories.size() > 0) {
				for (MNCategory category : categories) {
					CategoryEntity entity = new CategoryEntity(category);
					entities.add(entity);
				}
			}
			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", entities.size());

			// entities = CommonUtil.subList(entities, pageIndex, pageSize);
			resultData.put("list", entities);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
}
