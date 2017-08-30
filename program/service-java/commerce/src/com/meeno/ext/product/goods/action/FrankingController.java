package com.meeno.ext.product.goods.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.ietf.jgss.Oid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.ext.product.goods.service.FrankingService;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

/**
 * 运费控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/franking")
public class FrankingController {
	private static final Logger LOGGER = Logger.getLogger(FrankingController.class);

	@Autowired
	private FrankingService frankingService;

	/**
	 * 添加运费模板
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"name":"","postage":"","pattern":"","dispatching":"",
	 *            "baseWeight":"","basePrice":"","addWeight":"","addPrice":"",
	 *            "scope":""}
	 */
	@RequestMapping("/addFranking.action")
	public void addFranking(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String name = json.getString("name");
			String postage = json.getString("postage");
			String dispatching = json.getString("dispatching");
			Long pattern = json.getLong("pattern");
			String scope = json.getString("scope");
			BigDecimal baseWeight = json.getBigDecimal("baseWeight");
			BigDecimal basePrice = json.getBigDecimal("basePrice");
			BigDecimal addWeight = json.getBigDecimal("addWeight");
			BigDecimal addPrice = json.getBigDecimal("addPrice");
			this.frankingService.addFranking(name, postage, pattern, dispatching, baseWeight, basePrice, addWeight,
					addPrice, scope);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员运费模板--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取运费列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 */
	@RequestMapping("/findFrankingList.action")
	public void findFrankingList(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			List<FrankingTemplet> templets = this.frankingService.frankingTempletList();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", templets));
			LOGGER.debug("管理员获取运费模板--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 修改运费模板
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":"","name":"","postage":"","pattern":"","dispatching":"",
	 *            "baseWeight":"","basePrice":"","addWeight":"","addPrice":"",
	 *            "scope":""}
	 */
	@RequestMapping("/editFranking.action")
	public void editFranking(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			String name = json.getString("name");
			String postage = json.getString("postage");
			String dispatching = json.getString("dispatching");
			Long pattern = json.getLong("pattern");
			String scope = json.getString("scope");
			BigDecimal baseWeight = json.getBigDecimal("baseWeight");
			BigDecimal basePrice = json.getBigDecimal("basePrice");
			BigDecimal addWeight = json.getBigDecimal("addWeight");
			BigDecimal addPrice = json.getBigDecimal("addPrice");
			this.frankingService.editFranking(id, name, postage, pattern, dispatching, baseWeight, basePrice, addWeight,
					addPrice, scope);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员获取运费模板--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 删除运费模板
	 * 
	 * @param response
	 * @param data
	 */
	@RequestMapping("/delFranking.action")
	public void delFranking(HttpServletResponse response, @RequestParam(value = "Data", required = false) String data) {
		try {
			JSONObject json = JSONObject.parseObject(data);

		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
}
