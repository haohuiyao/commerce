package com.meeno.ext.product.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FrankingTemplet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String fareName;// 模板名称

	private String postage;// 是否包邮 1:包邮 2:不包邮

	private Long pattern;// 计费方式 1:按重量 2:按体积 3:按件数

	private String dispatching;// 配送方式 ：快递物流

	private BigDecimal baseWeight;

	private BigDecimal basePrice;

	private BigDecimal addWeight;

	private BigDecimal addPrice;

	private String scope;// 配送范围

	private Date updateTime;// 最后更新时间

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFareName() {
		return fareName;
	}

	public void setFareName(String fareName) {
		this.fareName = fareName;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public Long getPattern() {
		return pattern;
	}

	public void setPattern(Long pattern) {
		this.pattern = pattern;
	}

	public String getDispatching() {
		return dispatching;
	}

	public void setDispatching(String dispatching) {
		this.dispatching = dispatching;
	}

	public BigDecimal getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(BigDecimal baseWeight) {
		this.baseWeight = baseWeight;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getAddWeight() {
		return addWeight;
	}

	public void setAddWeight(BigDecimal addWeight) {
		this.addWeight = addWeight;
	}

	public BigDecimal getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(BigDecimal addPrice) {
		this.addPrice = addPrice;
	}
}
