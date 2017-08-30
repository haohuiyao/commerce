package com.meeno.framework.area.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.meeno.framework.area.entity.MNArea;
import com.meeno.framework.area.service.AreaService;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

/**
 * 地区控制器
 * 
 * @author jeff_gao
 * @Comment AreaController
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	private static final Logger LOGGER = Logger.getLogger(AreaController.class);

	@Autowired
	private AreaService areaService;

	/**
	 * 获取子列表
	 * 
	 * @param response
	 * @param data
	 *            参数格式: 例 {"parentId" : 0}
	 */
	@RequestMapping("/getChildList.do")
	public void getChildList(HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {

		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Long parentId = json.getLong("parentId");
			List<MNArea> areaList = this.areaService.getChildList(parentId);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", areaList));
			LOGGER.debug("获取地区子列表--成功");
			
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}

	/**
	 * 获取所有信息
	 * 
	 * @param response
	 * @param data
	 */
	@RequestMapping("/getAllAreaInfo.do")
	public void getAllAreaInfo(HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {

		// JSON取值
		
		List<MNArea> areaList = this.areaService.getAllProvinceInfoList();
		CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "", areaList));
		LOGGER.debug("获取所有地区信息--成功");

	}
	
	/**
	 * 根据等级获取地区
	 * 
	 * @param response
	 * @param data
	 * 
	 * 	{"level":""}
	 */
	@RequestMapping("/getMNAreaByLevel.do")
	public void getMNAreaByLevel(HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data) {
		try {
			// JSON取值
			JSONObject json = JSONObject.parseObject(data);
			Integer level=json.getInteger("level");
			List<MNArea> areaList = this.areaService.getMNAreaByLevel(level);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", areaList));
			LOGGER.debug("根据等级获取地区--成功");
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}

	}
}
