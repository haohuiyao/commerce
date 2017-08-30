package com.newProduct.product.product.subview.front;

import java.util.Date;

import com.meeno.ext.product.goods.entity.MNSku;

/**
 * 前端机型详情
 * 
 * @author Administrator
 *
 */
public class FrontModelSkuDetailItem {
	private Long skuId;
	private String productName;
	private Date createTime;// 创建时间
	private Long paramId;
	private String paramName;
	private String paramsValue;// 参数模板的值
	private String picture;// 图片列表
	private String code;// 商品编号
	private String status;// 状态
	private String detail;// 商品详情
	private Long partsBrandId;
	private String partsBrand;
	private String modelBrand;// 适用品牌
	private String skuName;// 商品名称
	private String weight;// 重量(g)
	private String displayImg;// 商品首图
	private String summary;// 简介
	private String volumn;
	private String common;// 通用名
	private String title;// 标题
	private String place;// 产地
	private Long categoryId;// 分类ID
	private String categoryName;// 分类名称

	public FrontModelSkuDetailItem(MNSku sku) {
		this.skuId = sku.getId();
		this.productName = sku.getProduct().getName();
		this.createTime = sku.getCreateTime();
		if (sku.getParamTemplate() != null) {
			this.paramId = sku.getParamTemplate().getId();
			this.paramName = sku.getParamTemplate().getName();
		}
		this.paramsValue = sku.getParamsValue();
		this.picture = sku.getPicture();
		this.code = sku.getCode();
		this.status = sku.getStatus();
		this.detail = sku.getDetail();
		if (sku.getProduct().getBrand() != null) {
			this.partsBrandId = sku.getProduct().getBrand().getId();
			this.partsBrand = sku.getProduct().getBrand().getName();
		}
		this.modelBrand = sku.getBrand() == null ? "通用" : sku.getBrand().getName();
		this.skuName = sku.getSkuName();
		this.weight = sku.getWeight();
		this.displayImg = sku.getDisplayImg();
		this.summary = sku.getSummary();
		this.volumn = sku.getVolume();
		this.common = sku.getProduct().getCommon();
		this.place = sku.getProduct().getPlace();
		this.title = sku.getAdaptation();
		this.categoryId = sku.getProduct().getCategory().getId();
		this.categoryName = sku.getProduct().getCategory().getName();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(String modelBrand) {
		this.modelBrand = modelBrand;
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

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getPartsBrandId() {
		return partsBrandId;
	}

	public void setPartsBrandId(Long partsBrandId) {
		this.partsBrandId = partsBrandId;
	}

	public String getPartsBrand() {
		return partsBrand;
	}

	public void setPartsBrand(String partsBrand) {
		this.partsBrand = partsBrand;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
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

	public FrontModelSkuDetailItem() {
		super();
		// TODO Auto-generated constructor stub
	}
}
