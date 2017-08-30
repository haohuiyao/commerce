package com.meeno.framework.user.entity;

import java.util.Date;

import com.meeno.framework.bean.BaseEntity;
import com.meeno.framework.passport.entity.MNAccount;

public class BaseUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;// 姓名
	private String gender;// 性别
	private Date createTime;// 创建时间
	private String userType;// 用户类型
	private Long groupId;//用户分组
	private MNAccount account;

	public BaseUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public MNAccount getAccount() {
		return account;
	}

	public void setAccount(MNAccount account) {
		this.account = account;
	}

}
