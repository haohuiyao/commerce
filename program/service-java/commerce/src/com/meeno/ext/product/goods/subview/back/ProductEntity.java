package com.meeno.ext.product.goods.subview.back;

import java.math.BigDecimal;
import java.util.Date;

import com.meeno.ext.product.goods.entity.MNProduct;

/**
 * 产品实体
 * @author Administrator
 *
 */
public class ProductEntity {
	private Long id;
	private String name;// 商品名
	private String summary;// 商品简介
	private String detail;// 商品详情
	private Date createTime;// 创建时间
	private Date modifyTime;// 更新时间
	private String paramsValue;// 参数值
	private String place;// 商品产地
	private String code;// 机型编码
	private BigDecimal cost;// 商品成本价
	private String displayImg;// 产品首图
	private String picture;// 商品图片
	private String common;// 商品通用名
	private String currency;// 标识是否为全国统一价
	private String volume;// 体积
	private String weight;// 重量
	private Long frinkingId;// 运费模板Id
	private String frinkingName;
	private Long categoryId;// 商品所属分类细类
	private String categoryName;
	private Long paramTemplateId;// 参数模板
	private String paramName;
	private Long brandId;// 商品品牌
	private String brandName;
	private Long sourceId;// 商品货源
	private String sourceName;
	private String status;

	public ProductEntity(MNProduct product) {
		this.id = product.getId();
		this.name = product.getName();
		this.summary = product.getSummary();
		this.detail = product.getDetail();
		this.createTime = product.getCreateTime();
		this.modifyTime = product.getModifyTime();
		this.paramsValue = product.getParamsValue();
		this.place = product.getPlace();
		this.code = product.getCode();
		this.cost = product.getCost();
		this.displayImg = product.getDisplayImg();
		this.picture = product.getPicture();
		this.common = product.getCommon();
		this.currency = product.getCurrency();
		this.volume = product.getVolume();
		this.weight = product.getWeight();
		if (product.getCategory() != null) {
			this.categoryId = product.getCategory().getId();
			this.categoryName = product.getCategory().getName();
		}
		if (product.getFranking() != null) {
			this.frinkingId = product.getFranking().getId();
			this.frinkingName = product.getFranking().getFareName();
		}
		if (product.getParamTemplate() != null) {
			this.paramTemplateId = product.getParamTemplate().getId();
			this.paramName = product.getParamTemplate().getName();
		}
		if (product.getBrand() != null) {
			this.brandId = product.getBrand().getId();
			this.brandName = product.getBrand().getName();
		}
		if (product.getSource() != null) {
			this.sourceId = product.getSource().getId();
			this.sourceName = product.getSource().getSourceName();
		}
		this.status = product.getStatus();
	}

	public Long getFrinkingId() {
		return frinkingId;
	}

	public void setFrinkingId(Long frinkingId) {
		this.frinkingId = frinkingId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getParamsValue() {
		return paramsValue;
	}

	public void setParamsValue(String paramsValue) {
		this.paramsValue = paramsValue;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getDisplayImg() {
		return displayImg;
	}

	public void setDisplayImg(String displayImg) {
		this.displayImg = displayImg;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFrinkingName() {
		return frinkingName;
	}

	public void setFrinkingName(String frinkingName) {
		this.frinkingName = frinkingName;
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

	public Long getParamTemplateId() {
		return paramTemplateId;
	}

	public void setParamTemplateId(Long paramTemplateId) {
		this.paramTemplateId = paramTemplateId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
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

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
