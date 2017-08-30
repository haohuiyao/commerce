package com.newProduct.product.machine.action;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.subview.back.ProductEntity;
import com.meeno.ext.product.goods.subview.back.SkuEntity;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;
import com.newProduct.product.machine.service.MachineService;
import com.newProduct.product.machine.service.ModelService;
import com.newProduct.product.machine.subview.back.MachineBackItem;
import com.newProduct.product.product.service.WPProductService;

/**
 * 挖机控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/machineManager")
public class MachineController {
	private static final Logger LOGGER = Logger.getLogger(MachineController.class);

	@Autowired
	private MachineService machineService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private WPProductService wpProductService;

	/**
	 * 获取所有产品组列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"condition":{"title":"","brandId":"","categoryId":""
	 *            },"status":"", "pageIndex":"", "pageSize":""}
	 */
	@RequestMapping("/findProductList.do")
	public void findProductList(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");
			String status = json.getString("status");
			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);

			pageBean = this.machineService.findProductList(pageBean, condition, status);
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
	 * 编辑产品组
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":"","name":"","detail":"","summary":"", "place":"",
	 *            "paramsValue":"","code":"",
	 *            "cost":"","displayImg":"","picture":"","common":"",
	 *            "currency":"","volume":"","weight":"","carriageId":"",
	 *            "paramTemplateId":"","brandId":"","sourceId": ""}
	 */
	@RequestMapping("/editProduct.action")
	public void editProduct(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			LOGGER.debug(json);
			Long id = json.getLong("id");
			String name = json.getString("name");
			String summary = json.getString("summary");
			String detail = json.getString("detail");
			String place = json.getString("place");
			String paramValue = json.getString("paramsValue");
			String code = json.getString("code");
			BigDecimal cost = json.getBigDecimal("cost");
			String displayImg = json.getString("displayImg");
			String picture = json.getString("picture");
			String common = json.getString("common");
			String currency = json.getString("currency");
			String volume = json.getString("volume");
			String weight = json.getString("weight");
			Long carriageId = json.getLong("carriageId");
			Long paramsId = json.getLong("paramTemplateId");
			Long brandId = json.getLong("brandId");
			Long sourceId = json.getLong("sourceId");
			this.machineService.editProduct(id, name, summary, detail, paramValue, place, code, cost, displayImg,
					picture, common, currency, volume, weight, carriageId, paramsId, brandId, sourceId);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员编辑商品--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 下架产品组
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            []
	 * 
	 */
	@RequestMapping("/putOutProduct.action")
	public void putOutProduct(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONArray ids = JSONArray.parseArray(data);
			this.machineService.putOutProduct(ids);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员下架产品组--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 上架产品组
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            []
	 * 
	 */
	@RequestMapping("/putUpProduct.action")
	public void putUpProduct(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONArray ids = JSONArray.parseArray(data);
			this.machineService.putUpProduct(ids);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员上架产品组--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 新增sku
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"productId":"","paramTemplateId":"","paramValue":"",
	 *            "adaptation":"","picture":"","detail":"","brandId":"",
	 *            "skuName":"","weight":"","displayImg":"","summary":"","volumn"
	 *            :""}
	 * 
	 */
	@RequestMapping("/addSku.action")
	public void addSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long productId = json.getLong("productId");
			Long paramsId = json.getLong("paramTemplateId");
			String paramValue = json.getString("paramValue");
			String adaptation = json.getString("adaptation");
			String picture = json.getString("picture");
			String detail = json.getString("detail");
			Long brandId = json.getLong("brandId");
			String skuName = json.getString("skuName");
			String weight = json.getString("weight");
			String displayImg = json.getString("displayImg");
			String summary = json.getString("summary");
			String volumn = json.getString("volumn");
			String rel = json.getString("rel");
			this.modelService.addSku(productId, paramsId, paramValue, adaptation, picture, detail, brandId, skuName,
					weight, displayImg, summary, volumn, rel);
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
	 * 编辑Sku
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":"","productId":"","paramTemplateId":"","paramValue":"",
	 *            "adaptation":"","picture":"","detail":"","brandId":"",
	 *            "skuName":"","weight":"","displayImg":"","summary":"","volumn"
	 *            :""}
	 */
	@RequestMapping("/editSku.action")
	public void editSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			Long productId = json.getLong("productId");
			Long paramsId = json.getLong("paramTemplateId");
			String paramValue = json.getString("paramValue");
			String adaptation = json.getString("adaptation");
			String picture = json.getString("picture");
			String detail = json.getString("detail");
			Long brandId = json.getLong("brandId");
			String skuName = json.getString("skuName");
			String weight = json.getString("weight");
			String displayImg = json.getString("displayImg");
			String summary = json.getString("summary");
			String volumn = json.getString("volumn");
			this.modelService.editSku(id, productId, paramsId, paramValue, adaptation, picture, detail, brandId,
					skuName, weight, displayImg, summary, volumn);
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
	 * 下架SKU
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            []
	 * 
	 */
	@RequestMapping("/putOutSku.action")
	public void putOutSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONArray ids = JSONArray.parseArray(data);
			this.modelService.putOutSku(ids);
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
	 * 上架SKU
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            []
	 * 
	 */
	@RequestMapping("/putUpSku.action")
	public void putUpSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONArray ids = JSONArray.parseArray(data);
			this.modelService.putUpSku(ids);
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
	 * 获取所有sku列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"condition":{"title":"","productBrand":"","categoryId":"",
	 *            "productId":""}, "status":"", "pageIndex":"", "pageSize":""}
	 * 
	 */
	@RequestMapping("/findSkuList.do")
	public void findSkuList(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");
			String status = json.getString("status");
			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);

			pageBean = this.modelService.findSkuList(pageBean, condition, status);
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
	 * 根据品牌获取商品
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":""}
	 */
	@RequestMapping("/findProductByBrand.do")
	public void findProductByBrand(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			Map<String, Object> resultMap = this.machineService.findProductByBrand(id);
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
	 * 保存产品关系
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"objType":"","objId":"","rel":[{"objType":"","relType":"",
	 *            "id":""},{"objType":"","relType":"","id":""}]}
	 * 
	 */
	@RequestMapping("/saveProductRel.do")
	public void saveProductRel(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Integer relType = json.getInteger("relType");
			Integer objType = json.getInteger("objType");
			Long objId = json.getLong("objId");
			String rel = json.getString("rel");
			this.modelService.saveProductRel(relType, objType, objId, rel);
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
	 * 查询产品关系
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"relType":"","objType":"","objId":"","targetType":""}
	 */
	@RequestMapping("/findProductRel.do")
	public void findProductRel(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Integer relType = json.getInteger("relType");
			Integer objType = json.getInteger("objType");
			Long objId = json.getLong("objId");
			Integer targetType = json.getInteger("targetType");
			Map<String, List> resultMap = this.modelService.findProdcutRel(relType, objType, objId, targetType);
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
	 * 获取挖机Product列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"pageSize":"","pageIndex":""}
	 */
	@RequestMapping("/getMachineProduct.do")
	public void getMachineProduct(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");
			String status = json.getString("status");
			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);
			pageBean = this.wpProductService.getProductPageBean(pageBean, condition, status,
					ProductContant.CATEGORY_PRODUCT);
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
	 * 获取挖机sku列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"pageSize":"","pageIndex":"","condition":{"productId":""}}
	 */
	@RequestMapping("/getMachineModel.do")
	public void getMachineModel(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String condition = json.getString("condition");
			String status = json.getString("status");
			
			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);
			pageBean = this.wpProductService.getSkuPageBean(pageBean, condition, status,
					ProductContant.CATEGORY_PRODUCT);
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
	 * 获取挖机详情
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":""}
	 */
	@RequestMapping("/getMachineDetail.do")
	public void getMachineDetail(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id=json.getLong("id");
			MachineBackItem machineBackItem=this.machineService.getMachineDetailById(id);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok",machineBackItem));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	/**
	 * 新增产品组
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"name":"","detail":"","summary":"", "place":"",
	 *            "paramsValue":"","code":"",
	 *            "cost":"","displayImg":"","picture":"","common":"",
	 *            "currency":"","volume":"","weight":"","carriageId":"",
	 *            "categoryId":"","paramTemplateId":"","brandId":"","sourceId": ""}
	 */
	@RequestMapping("/addMachineProduct.action")
	public void addMachineProduct(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			LOGGER.debug(json);
			String name = json.getString("name");
			String summary = json.getString("summary");
			String detail = json.getString("detail");
			String place = json.getString("place");
			String paramValue = json.getString("paramsValue");
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
			MNProduct product = this.machineService.addProduct(name, summary, detail, paramValue, place, code, cost,
					displayImg, picture, common, currency, volume, weight, carriageId, categoryId, paramsId, brandId,
					sourceId);
			Map<String, Object> resultMap=new HashMap<>();
			resultMap.put("id", product.getId());
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
	 * 新增sku
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"productId":"","skus":[{"name":""}]}
	 * 
	 */
	@RequestMapping("/addMachineSku.action")
	public void addMachineSku(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long productId = json.getLong("productId");
			JSONArray skus=json.getJSONArray("skus");
			this.machineService.addMachineSku(productId, skus);
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
