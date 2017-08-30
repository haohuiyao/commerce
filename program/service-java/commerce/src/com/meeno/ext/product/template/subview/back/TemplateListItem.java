package com.meeno.ext.product.template.subview.back;

import java.io.Serializable;
import java.util.Date;

public class TemplateListItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long templateId;// 参数模板ID
	private String name;// 参数模板名称
	private String status;// 状态
	private Date createTime;//创建时间

	public TemplateListItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
