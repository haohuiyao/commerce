package com.newProduct.product.product.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.goods.subview.back.ProductEntity;
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
import com.newProduct.product.machine.subview.back.MachineBackItem;
import com.newProduct.product.machine.subview.back.ProductBackItem;
import com.newProduct.product.product.service.WPProductService;
import com.newProduct.product.product.subview.back.SkuRelBackItem;
import com.newProduct.product.product.subview.front.FrontModelSkuDetailItem;
import com.newProduct.product.product.subview.front.FrontSkuListItem;

@Controller
@RequestMapping("/productManager")
public class ProductManagerController {
	private static final Logger LOGGER = Logger.getLogger(MachineController.class);

	@Autowired
	private WPProductService wpProductService;

	@Autowired
	private ProductService productService;

	/**
	 * 新增产品配件
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"name":"","detail":"","summary":"", "place":"",
	 *            "paramsValue":"","code":"",
	 *            "cost":"","displayImg":"","picture":"","common":"",
	 *            "currency":"","volume":"","weight":"","carriageId":"",
	 *            "categoryId":"","paramTemplateId":"","brandId":"","sourceId":
	 *            "","skuName":"","brand":[{"brandId":"","rel":[{"objType":"",
	 *            "id":""},{"objType":"","id":""}]}]}
	 */
	@RequestMapping("/addParts.action")
	public void addParts(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			System.out.println(data);
			JSONObject json = JSONObject.parseObject(data);
			String name = json.getString("name");
			String summary = json.getString("summary");
			String detail = json.getString("detail");
			String place = json.getString("place");
			String params = json.getString("paramsValue");
			String code = json.getString("code");
			BigDecimal cost = json.getBigDecimal("cost");
			String displayImg = json.getString("displayImg");
			String picture = json.getString("picture");
			String common = json.getString("common");
			String currency = json.getString("currency");
			String volume = json.getString("volume");
			String weight = json.getString("weight");
			Long carriageId = json.getLong("carriageId");
			Long categoryId = json.getLong("categoryId");
			Long paramsId = json.getLong("paramTemplateId");
			Long brandId = json.getLong("brandId");
			Long sourceId = json.getLong("sourceId");
			String skuName = json.getString("skuName");
			String brandStr = json.getString("brand");
			this.wpProductService.addWPProduct(name, summary, detail, params, place, code, cost, displayImg, picture,
					common, currency, volume, weight, carriageId, categoryId, paramsId, brandId, sourceId, skuName,
					brandStr);
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
	 * 修改WP商品
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":"","name":"","detail":"","summary":"", "place":"",
	 *            "paramsValue":"","code":"",
	 *            "cost":"","displayImg":"","picture":"","common":"",
	 *            "currency":"","volume":"","weight":"","carriageId":"",
	 *            "categoryId":"","paramTemplateId":"","brandId":"","sourceId":
	 *            "","skuName":"","brand":[{"brandId":"","rel":[{"objType":"",
	 *            "id":"","relType":""}]}]}
	 */
	@RequestMapping("/editParts.action")
	public void editParts(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			System.out.println(data);
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			String name = json.getString("name");
			String summary = json.getString("summary");
			String detail = json.getString("detail");
			String place = json.getString("place");
			String params = json.getString("paramsValue");
			String code = json.getString("code");
			BigDecimal cost = json.getBigDecimal("cost");
			String displayImg = json.getString("displayImg");
			String picture = json.getString("picture");
			String common = json.getString("common");
			String currency = json.getString("currency");
			String volume = json.getString("volume");
			String weight = json.getString("weight");
			Long carriageId = json.getLong("carriageId");
			Long categoryId = json.getLong("categoryId");
			Long paramsId = json.getLong("paramTemplateId");
			Long brandId = json.getLong("brandId");
			Long sourceId = json.getLong("sourceId");
			String skuName = json.getString("skuName");
			String brandStr = json.getString("brand");
			this.wpProductService.editWPProduct(id, name, summary, detail, params, place, code, cost, displayImg,
					picture, common, currency, volume, weight, carriageId, categoryId, paramsId, brandId, sourceId,
					skuName, brandStr);
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
	 * 获取配件详情
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":""}
	 */
	@RequestMapping("/getPartsDetailById.do")
	public void getPartsById(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			System.out.println(data);
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			MNProduct product = this.wpProductService.get(MNProduct.class, id);
			ProductEntity entity = new ProductEntity(product);

			Map<String, Object> resultMap = new HashMap<>();
			List<SkuRelBackItem> items = this.wpProductService.getProductRelById(id);
			resultMap.put("entity", entity);
			resultMap.put("productRel", items);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultMap));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取挖机配件Product列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"pageSize":"","pageIndex":""}
	 */
	@RequestMapping("/getPartsProduct.do")
	public void getPartsProduct(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");
			String status = json.getString("status");
			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);
			pageBean = this.wpProductService.getProductPageBean(pageBean, condition, status,
					ProductContant.CATEGORY_PARTS);
			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			List<ProductEntity> list = new ArrayList<>();
			if (pageBean.getDataList() != null) {
				for (MNProduct product : (List<MNProduct>) pageBean.getDataList()) {
					ProductEntity entity = new ProductEntity(product);
					list.add(entity);
				}
			}
			resultData.put("totalCount", pageBean.getTotalRows());
			resultData.put("list", list);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultData));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取挖机配件sku列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"pageSize":"","pageIndex":"","condition":"{"productId":""}"}
	 */
	@RequestMapping("/getPartsSku.do")
	public void getPartsSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");
			String status = json.getString("status");
			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);
			pageBean = this.wpProductService.getSkuPageBean(pageBean, condition, status, ProductContant.CATEGORY_PARTS);
			// 封装数据
			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			List<SkuEntity> list = new ArrayList<>();
			if (pageBean.getDataList() != null) {
				for (MNSku sku : (List<MNSku>) pageBean.getDataList()) {
					SkuEntity skuEntity = new SkuEntity(sku);
					list.add(skuEntity);
				}
			}
			resultData.put("totalCount", pageBean.getTotalRows());
			resultData.put("list", list);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultData));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取挖机配件sku详情
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"skuId":""}
	 */
	@RequestMapping("/getPartsSkuDetail.do")
	public void getPartsSkuDetail(HttpSession session, HttpServletResponse response,
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
	 * 修改挖机配件sku
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"skuId":"","rel":[{"objType":"",
	 *            "id":""},{"objType":"","id":""}],"paramsValue":"","picture":""
	 *            ,"displayImg":"","detail":""}
	 */
	@RequestMapping("/editPartsSku.action")
	public void editPartsSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long skuId = json.getLong("skuId");
			String rel = json.getString("rel");
			String detail = json.getString("detail");
			String params = json.getString("paramsValue");
			String displayImg = json.getString("displayImg");
			String picture = json.getString("picture");
			this.wpProductService.editPartsSku(skuId, rel,detail,params,displayImg,picture);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("修改挖机配件sku——修改成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
}
