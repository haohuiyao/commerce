package com.meeno.ext.product.goods.subview.back;

import java.util.Date;

import com.meeno.ext.product.goods.entity.MNSku;

/**
 * sku实体
 * 
 * @author Administrator
 *
 */
public class SkuEntity {
	private Long id;
	// private Long productId;
	private String productName;
	private Date createTime;// 创建时间
	private Long paramId;
	private String paramName;
	private String paramsValue;// 参数模板的值
	private String adaptation;// 品牌和适配机型
	private String picture;// 图片列表
	private String code;// 商品编号
	private String status;// 状态
	private String detail;// 商品详情
	private Long brandId;
	private String brandName;
	private String skuName;// 商品名称
	private String weight;// 重量(g)
	private String displayImg;// 商品首图
	private String summary;// 简介
	private String volumn;

	public SkuEntity(MNSku sku) {
		this.id = sku.getId();
		// this.productId = sku.getProduct().getId();
		this.productName = sku.getProduct().getName();
		this.createTime = sku.getCreateTime();
		if (sku.getParamTemplate() != null) {
			this.paramId = sku.getParamTemplate().getId();
			this.paramName = sku.getParamTemplate().getName();
		}
		this.paramsValue = sku.getParamsValue();
		this.adaptation = sku.getAdaptation();
		this.picture = sku.getPicture();
		this.code = sku.getCode();
		this.status = sku.getStatus();
		this.detail = sku.getDetail();
		if (sku.getBrand() != null) {
			this.brandId = sku.getBrand().getId();
			this.brandName = sku.getBrand().getName();
		}
		this.skuName = sku.getSkuName();
		this.weight = sku.getWeight();
		this.displayImg = sku.getDisplayImg();
		this.summary = sku.getSummary();
		this.volumn = sku.getVolume();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getParamId() {
		return paramId;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamsValue() {
		return paramsValue;
	}

	public void setParamsValue(String paramsValue) {
		this.paramsValue = paramsValue;
	}

	public String getAdaptation() {
		return adaptation;
	}

	public void setAdaptation(String adaptation) {
		this.adaptation = adaptation;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDisplayImg() {
		return displayImg;
	}

	public void setDisplayImg(String displayImg) {
		this.displayImg = displayImg;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public SkuEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
