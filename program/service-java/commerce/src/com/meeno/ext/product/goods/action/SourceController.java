package com.meeno.ext.product.goods.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.meeno.ext.product.goods.service.SourceService;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

/**
 * 货源控制器
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/source")
public class SourceController {
	private static final Logger LOGGER = Logger.getLogger(SourceController.class);

	@Autowired
	private SourceService sourceService;

	/**
	 * 添加货源信息
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"sourceName":""}
	 */
	@RequestMapping("/addSource.action")
	public void addSource(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String sourceName = json.getString("sourceName");
			this.sourceService.addSource(sourceName);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.debug("管理员添加货源--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取货源列表
	 * 
	 * @param session
	 * @param response
	 * @param data
	 */
	@RequestMapping("/findSourceList.do")
	public void findSourceList(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {

			// JSON取值
			JSONObject json = JSONObject.parseObject(data);

			// 分页
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);

			pageBean = sourceService.findbyPartsList(pageBean);

			// 封装数据

			Map<String, Object> resultData = Maps.newHashMap();
			// 总记录数
			resultData.put("totalCount", pageBean.getTotalRows());
			resultData.put("list", pageBean.getDataList());
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", resultData));
			LOGGER.debug("获取货源列表——请求成功：" + JSON.toJSONString(resultData));
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 修改货源信息
	 * 
	 * @param session
	 * @param response
	 * @param data
	 *            {"id":"","sourceName":""}
	 */
	@RequestMapping("/editSource.action")
	public void editSource(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long id = json.getLong("id");
			String sourceName = json.getString("sourceName");
			this.sourceService.editSource(id, sourceName);
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
	 * 删除货源
	 * 
	 * @param response
	 * @param data
	 *            {"id":""}
	 */
	@RequestMapping("/delSource.do")
	public void delSource(HttpServletResponse response, @RequestParam(value = "Data", required = false) String data) {
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
