package com.meeno.ext.product.template.entity;

import java.util.Date;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeno.framework.bean.BaseEntity;
import com.meeno.framework.util.CommonUtil;

/**
 * 
 * 参数模板
 * 
 * @author yao
 *
 */
public class MNParamTemplate extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;// 模板名
	private String content;// 模板内容
	private Date createTime;// 创建时间

	public MNParamTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 检查参数模板格式
	 * 
	 * @param src
	 * @return
	 */
	public static boolean checkTemplateFormat(String src) {
		try {
			JSONArray jsonArray = JSONArray.parseArray(src);
			if (jsonArray.size() == 0) {
				return false;
			}
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				if (object.containsKey("id") && object.containsKey("name") && object.containsKey("type")
						&& object.containsKey("hint") && object.containsKey("options")
						&& object.containsKey("optional"))
					continue;
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 检查参数格式
	 * 
	 * @param object
	 * @return
	 */
	public static boolean checkParamFormat(JSONObject object) {
		if (object == null)
			return false;

		if (object.containsKey("id") && object.containsKey("name") && object.containsKey("type")
				&& object.containsKey("hint") && object.containsKey("options") && object.containsKey("optional"))
			return true;
		return false;

	}
	
	/**
	 * 检查参数是否已存在
	 * 
	 * @param src
	 * @return
	 */
	public boolean paramIdExist(String id) {
		try {
			JSONArray jsonArray = JSONArray.parseArray(content);
			for (int i = 0; i < jsonArray.size(); i++) {
				if (jsonArray.getJSONObject(i).getString("id").equals(id)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * 校验参数模板value的格式
	 * 
	 * @param valueMap
	 * @return
	 */
	public boolean checkValueFormat(Map<String, String> valueMap) {
		try {
			JSONArray jsonArray = JSONArray.parseArray(content);
			if (jsonArray.size() == 0) {
				return false;
			}
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				// 是否必填参数
				String optional = object.getString("optional");
				if ("0".equals(optional)) {
					// 是必填参数
					if (CommonUtil.isZeroLengthTrimString(valueMap.get(object.getString("id")))) {
						// 必填参数未填写
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
