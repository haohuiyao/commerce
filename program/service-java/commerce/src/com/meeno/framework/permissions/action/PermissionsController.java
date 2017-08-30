package com.meeno.framework.permissions.action;

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
import com.google.common.collect.Maps;
import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.permissions.entity.Action;
import com.meeno.framework.permissions.entity.Role;
import com.meeno.framework.permissions.service.PermissionService;
import com.meeno.framework.permissions.subview.RoleItem;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;

@Controller
@RequestMapping("/user")
public class PermissionsController {
	private static final Logger LOGGER = Logger.getLogger(PermissionsController.class);
	
	@Autowired
	private PermissionService permissionService;

	/**
	 * 获得权限列表
	 * 
	 * @param response
	 * @param data
	 *            例:{"type":"No1"}
	 */
	@RequestMapping("/ActionList.do")
	public void getActionList(HttpServletResponse response,
			@RequestParam(required = false, value = "Data") String data) {
		try {
			JSONObject json = JSONObject.parseObject(data);
			String type = json.getString("type");
			List<Action> list = this.permissionService.getActionList(type);
			Map<String, Object> resultMap=Maps.newHashMap();
			List<ActionBackInfo> actions=new ArrayList<>();
			if(list!=null){
				for(Action a:list){
					List<Action> childs=new ArrayList<>();
					ActionBackInfo info=new ActionBackInfo();
					if(a.getParentId()==null){
						childs=this.permissionService.getActionChildrens(a.getId());
						info.setId(a.getId());
						info.setHref(a.getHref());
						info.setChilds(childs);
						info.setTitle(a.getTitle());
						info.setIcon(a.getIcon());
						actions.add(info);
					}
				}
			}
			resultMap.put("list", actions);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok", resultMap));
			LOGGER.debug("获取权限列表 成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	/**
	 * 添加导航栏
	 * 
	 * @param response
	 * @param data
	 *            例:{"title":"","href":"","parentId":"","icon":""}
	 */
	@RequestMapping("/addAction.action")
	public void addAction(HttpSession session,HttpServletResponse response,
			@RequestParam(required = false, value = "Data") String data) {
		try {
			JSONObject json = JSONObject.parseObject(data);
			String title = json.getString("title");
			String href= json.getString("href");
			String parent=json.getString("parentId");
			String icon=json.getString("icon");
			if(CommonUtil.isZeroLengthTrimString(title)){
				CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "导航标题不能为空"));
				return;
			}
			Action action=new Action();
			action.setTitle(title);
			action.setHref(href);
			action.setIcon(icon);
			if(CommonUtil.isZeroLengthTrimString(parent)){
				action.setParentId(null);
			}else{
				action.setParentId(Long.parseLong(parent));
			}
			this.permissionService.save(action);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "添加成功", action));
			LOGGER.debug("添加导航栏成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	
	/**
	 * 
	 * @param response
	 * @param data
	 * 		{"ids":"[]"}
	 */
	@RequestMapping("/delAction.action")
	public void deleteAction(HttpServletResponse response,
			@RequestParam(required=false,value="Data") String data){
		try {
			JSONObject json=JSONObject.parseObject(data);
			JSONArray ids=JSONArray.parseArray(json.getString("ids"));
			if(!this.permissionService.delAction(ids.toArray())){
				CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "删除失败"));
				return;
			}
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "删除成功"));
			LOGGER.debug("导航栏删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	/**
	 * 编辑权限
	 * 
	 * @param response
	 * @param data
	 * 		{"id":"","title":"","href":"","icon":"","parentId":""}
	 */
	@RequestMapping("/modifyAction.action")
	public void modifyAction(HttpServletResponse response,
			@RequestParam(required=false,value="Data") String data){
		try {
			JSONObject json = JSONObject.parseObject(data);
			String id = json.getString("id");
			
			if(CommonUtil.isZeroLengthTrimString(id)){
				CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "权限Id不能为空"));
				return;
			}
			Action action=this.permissionService.get(Action.class, Long.parseLong(id));
			if(action==null){
				CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "权限不存在"));
				return;
			}
			
			action.setTitle(json.getString("title"));
			action.setHref(json.getString("href"));
			action.setIcon(json.getString("icon"));
			String parent=json.getString("parentId");
			if(CommonUtil.isZeroLengthTrimString(parent)){
				action.setParentId(null);
			}else{
				action.setParentId(Long.parseLong(parent));
			}
			this.permissionService.update(action);
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "编辑成功"));
			LOGGER.debug("导航栏编辑成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	/**
	 * 管理员角色列表
	 * 
	 * @param response
	 * @param data
	 * 
	 */
	@RequestMapping("/getAdminRole.action")
	public void getAdminRole(HttpServletResponse response,
			@RequestParam(required=false,value="Data") String data){
		try {
			List<Role> roles=this.permissionService.getRoleList();
			List<RoleItem> items=new ArrayList<>();
			if(roles!=null){
				for(Role role:roles){
					RoleItem item=new RoleItem();
					item.setId(role.getId());
					item.setRoleName(role.getRoleName());
					item.setRoleNo(role.getRoleNo());
					items.add(item);
				}
			}
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_SUCCESS, "ok",items));
			LOGGER.debug("获取管理员角色列表 成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
	}
	
	public class ActionBackInfo {
		private Long id;
		private String title;
		private String href;
		private String icon;
		/**
		 * @return the icon
		 */
		public String getIcon() {
			return icon;
		}

		/**
		 * @param icon the icon to set
		 */
		public void setIcon(String icon) {
			this.icon = icon;
		}

		private List<Action> childs;

		public ActionBackInfo() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public List<Action> getChilds() {
			return childs;
		}

		public void setChilds(List<Action> childs) {
			this.childs = childs;
		}

	}
}
