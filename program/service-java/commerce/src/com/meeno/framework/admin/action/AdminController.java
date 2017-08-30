package com.meeno.framework.admin.action;

import java.util.ArrayList;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.meeno.framework.admin.entity.AdminEntry;
import com.meeno.framework.admin.entity.AdminGroup;
import com.meeno.framework.admin.service.AdminService;
import com.meeno.framework.admin.view.AdminEntryChildItemList;
import com.meeno.framework.admin.view.AdminEntryItemList;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.passport.entity.MNPLoginRecord;
import com.meeno.framework.passport.util.MNPLoginCallback;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.user.entity.BaseUser;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

/**
* @description 管理员控制器
* @time 2017年7月20日 下午3:38:39
* @author Saturn
* @version 1.0
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	
	/**
	 * @Description: 管理员登录
	 * @param session
	 * @param response
	 * @param data
	 * @return void
	 */
	@RequestMapping("/loginNormal.do")
	public void loginNormal(final HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data ){
		try {
			//JSON取值
			JSONObject json = JSONObject.parseObject(data);
			String username = json.getString("username");//用户名
			String password = json.getString("password");//密码
			LOGGER.info("管理员登录账户信息为：" + username + ":" + password);
			
			
			
			this.adminService.loginNormal(username, password, new MNPLoginCallback() {
				@Override
				public void onSuccess(MNPLoginRecord loginRecord, BaseUser baseUser) {
					session.setAttribute("baseUser", baseUser);
					//生成返回数据
					Map<String, Object> resultData = Maps.newHashMap();
					resultData.put("token", loginRecord.getToken());
					Map<String, Object> userInfoMap = Maps.newHashMap();
					userInfoMap.put("name", baseUser.getName());
					userInfoMap.put("sessionId",session.getId());
					LOGGER.info("管理员GroupId:" + baseUser.getGroupId());
					userInfoMap.put("list", getEntryItemListByGroupId(baseUser.getGroupId()));
					resultData.put("userInfo", userInfoMap);
					// 返回结果
					CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS,"",resultData));
					LOGGER.info("管理员登录成功!");
				}
			});
			
		} catch (BusinessRuntimeException e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
			LOGGER.info("管理员登录失败！ 错误信息为：" + e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR,"系统异常"));
			LOGGER.info("管理员登录失败！ 错误信息为：" + e.getMessage());
		}
	}
	
	/**
	 * @Description: 获取管理员列表
	 * @param session
	 * @param response
	 * @param data
	 * @return void
	 */
	@RequestMapping("/getAdminList.do")
	public void getAdminList(final HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data){
		try {
			JSONObject json = JSONObject.parseObject(data);
			CutPageBean pageBean = CommonUtil.getCutPageBean(json);
			
			pageBean = this.adminService.getAdminList(pageBean);
			Map<String, Object> resultMap = Maps.newHashMap();
			resultMap.put("adminList", pageBean.getDataList());
			resultMap.put("totalCount", pageBean.getTotalRows());
			
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultMap));
			LOGGER.info("管理员列表获取成功!");
			
		} catch (BusinessRuntimeException e){
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
			LOGGER.info("管理员列表获取失败！ 错误信息为：" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR,"系统异常"));
			LOGGER.info("管理员列表获取失败！ 错误信息为：" + e.getMessage());
		}
	}
	
	/**
	 * @Description: 添加管理员
	 * @param session
	 * @param response
	 * @param data
	 * @return void
	 */
	@RequestMapping("/addAdmin.do")
	public void addAdmin(final HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data){
		try {
			JSONObject json = JSONObject.parseObject(data);
			String username = json.getString("username");//用户名
			String password = json.getString("password");//密码
			Long groupId = json.getLong("groupId");
			
			this.adminService.addAdmin(username, password, groupId);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "添加成功"));
			LOGGER.info("添加管理员成功!" + username + ":" + password + ":" + groupId);
		} catch (BusinessRuntimeException e){
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
			LOGGER.info("添加管理员失败！ 错误信息为：" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR,"系统异常"));
			LOGGER.info("添加管理员失败！ 错误信息为：" + e.getMessage());
		}
	}
	
	/**
	 * @Description: 修改管理员
	 * @param session
	 * @param response
	 * @param data
	 * @return void
	 */
	@RequestMapping("/modAdmin.do")
	public void modAdmin(final HttpSession session, final HttpServletResponse response, 
			@RequestParam(value = "Data", required = false) String data){
		try {
			JSONObject json = JSONObject.parseObject(data);
			String id = json.getString("id");
			String username = json.getString("username");
			String password = json.getString("password");
			Long groupId = json.getLong("groupId");
			
			this.adminService.modAdmin(id, username, password, groupId);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "修改成功"));
			LOGGER.info("修改管理员成功！" + id + ":" + username + ":" + password + ":" + groupId);
		} catch (BusinessRuntimeException e){
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
			LOGGER.info("修改管理员失败！ 错误信息为：" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR,"系统异常"));
			LOGGER.info("修改管理员失败！ 错误信息为：" + e.getMessage());
		}
	}
	
	/**
	 * @Description: 删除管理员
	 * @param session
	 * @param response
	 * @param data
	 * @return void
	 */
	@RequestMapping("/delAdmin.do")
	public void delAdmin(final HttpSession session, final HttpServletResponse response,
			@RequestParam(value = "Data", required = false) String data){
		try {
			JSONObject json = JSONObject.parseObject(data);
			JSONArray jsonArray=JSONArray.parseArray(json.getString("ids"));
			this.adminService.delAdmin(jsonArray);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok"));
			LOGGER.info("删除管理员成功！");
		} catch (BusinessRuntimeException e){
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, e.getErrorKey()));
			LOGGER.info("删除管理员失败！ 错误信息为：" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR,"系统异常"));
			LOGGER.info("删除管理员失败！ 错误信息为：" + e.getMessage());
		}
	}
	
	/**
	 * @description 根据管理员分组获取管理后台页面信息
	 * @param groupId
	 * @return
	 */
	private List<AdminEntryItemList> getEntryItemListByGroupId(Long groupId) {
		AdminGroup adminGroup = this.adminService.findAdminGroupById(groupId);
		JSONArray jsonArray = JSONArray.parseArray(adminGroup.getPrivileges());
		List<AdminEntryItemList> list = Lists.newArrayList();
		for(int i = 0; i < jsonArray.size(); ++i){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			AdminEntryItemList item = new AdminEntryItemList();
			AdminEntry parentEntry = this.adminService.findAdminEntryById(jsonObject.getLong("parent"));
			item.setId(parentEntry.getId());
			item.setIcon(parentEntry.getIcon());
			item.setTitle(parentEntry.getTitle());
			
			JSONArray childs = jsonObject.getJSONArray("childs");
			List<AdminEntryChildItemList> childList = new ArrayList<>();
			for(int j = 0; j < childs.size(); ++j){
				AdminEntry childEntry = this.adminService.findAdminEntryById(childs.getLong(j));
				childList.add(new AdminEntryChildItemList(childEntry));
			}
			item.setChilds(childList);
			
			list.add(item);
		}
		return list;
	}
}
