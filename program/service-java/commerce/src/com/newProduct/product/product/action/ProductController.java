package com.newProduct.product.product.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.subview.back.CategoryEntity;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.goods.subview.back.SkuEntity;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;
import com.meeno.framework.util.MeenoAssert;
import com.newProduct.product.machine.action.MachineController;
import com.newProduct.product.product.service.WPProductService;
import com.newProduct.product.product.subview.front.FrontModelSkuDetailItem;
import com.newProduct.product.product.subview.front.FrontSkuListItem;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger LOGGER = Logger.getLogger(MachineController.class);

	@Autowired
	private WPProductService wpProductService;

	@Autowired
	private ProductService productService;

	/**
	 * 根据条件获取sku列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"condition":{"keyword":"关键字", "categoryId":
	 *            "分类Id","sourceId":"货源Id",
	 *            "pRelArr":[{"objType2":"关联obj类型","objId2":"关联objId"}]},
	 *            "pageIndex":"", "pageSize":""}
	 *
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/frontFindSkuList.do")
	public void frontFindSkuList(HttpServletRequest request, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition"); // 查询条件
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);

			//
			pageBean = productService.luceneSearchSku(pageBean, condition);

			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", pageBean.getTotalRows());
			// 列表
			List<FrontSkuListItem> list = new ArrayList<>();
			if ((pageBean.getDataList() != null) && (pageBean.getDataList().size() > 0)) {
				List<MNSku> skus = (List<MNSku>) pageBean.getDataList();
				for (MNSku sku : skus) {
					list.add(new FrontSkuListItem(sku));
				}
			}
			resultData.put("list", list);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
			LOGGER.debug("根据条件查询SKU列表——请求成功");
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 根据条件获取sku详情
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"skuId":""}
	 *
	 */
	@RequestMapping("/frontFindSkuDetail.do")
	public void frontFindSkuDetail(HttpServletRequest request, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Map<String, Object> resultMap = new HashMap<>();
			Long id = json.getLong("skuId");
			MNSku sku = this.wpProductService.get(MNSku.class, id);
			MeenoAssert.notNull(sku, ProductError.SKU_NOT_EXIST);
			FrontModelSkuDetailItem skuEntity = new FrontModelSkuDetailItem(sku);
			resultMap.put("sku", skuEntity);
			Map<String, List> rel = this.wpProductService.findProdcutRel(ProductContant.OBJ_TYPE_SKU, sku.getId(),
					null);
			resultMap.put("rel", rel);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultMap));
			LOGGER.debug("根据条件查询SKU详情——请求成功");
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
	 * @param response
	 * @param data
	 *
	 */
	@RequestMapping("/frontGetMachineBrand.do")
	public void frontGetMachineBrand(HttpServletRequest request, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			List<Brand> list = this.wpProductService.frontGetBrandByCategoryName(ProductContant.CATEGORY_PRODUCT);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", list));
			LOGGER.debug("获取挖机品牌列表成功:" + JSONObject.parse(list.toString()));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	/**
	 * 获取配件品牌列表
	 * 
	 * @param response
	 * @param data
	 *
	 */
	@RequestMapping("/frontGetPartsBrand.do")
	public void frontGetPartsBrand(HttpServletRequest request, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			List<Brand> list = this.wpProductService.frontGetBrandByCategoryName(ProductContant.CATEGORY_PARTS);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", list));
			LOGGER.debug("获取配件品牌列表成功:" + JSONObject.parse(list.toString()));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取配件二级分类列表
	 * 
	 * @param response
	 * @param data
	 *
	 */
	@RequestMapping("/frontGetPartsCategory.do")
	public void frontGetPartsCategory(HttpServletRequest request, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			List<MNCategory> categories = this.wpProductService.frontGetPartsCategory();

			List<CategoryEntity> entities = new ArrayList<>();
			for (MNCategory category : categories) {
				CategoryEntity entity = new CategoryEntity(category);
				entities.add(entity);
			}

			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", entities.size());
			resultData.put("list", entities);

			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
			LOGGER.debug("获取分类列表--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
}
