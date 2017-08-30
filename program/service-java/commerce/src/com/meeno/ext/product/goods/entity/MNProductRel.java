package com.meeno.ext.product.goods.entity;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meeno.framework.bean.BaseEntity;

public class MNProductRel extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Integer relType;
	private Integer objType1;
	private Long objId1;
	private Integer objType2;
	private Long objId2;
	private Integer tag = 0;// 通用标签 0:不通过 1:通用
	private Date createTime;

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRelType() {
		return relType;
	}

	public void setRelType(Integer relType) {
		this.relType = relType;
	}

	public Integer getObjType1() {
		return objType1;
	}

	public void setObjType1(Integer objType1) {
		this.objType1 = objType1;
	}

	public Integer getObjType2() {
		return objType2;
	}

	public void setObjType2(Integer objType2) {
		this.objType2 = objType2;
	}

	public MNProductRel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getObjId1() {
		return objId1;
	}

	public void setObjId1(Long objId1) {
		this.objId1 = objId1;
	}

	public Long getObjId2() {
		return objId2;
	}

	public void setObjId2(Long objId2) {
		this.objId2 = objId2;
	}

	public static boolean checkProductRelFormat(String str) {
		try {
			JSONArray jsonArray = JSONArray.parseArray(str);
			if (jsonArray == null || jsonArray.size() == 0) {
				return false;
			}
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if (jsonObject.containsKey("objType") && jsonObject.containsKey("id"))
					continue;
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
