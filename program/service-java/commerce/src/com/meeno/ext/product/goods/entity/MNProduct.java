package com.meeno.ext.product.goods.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.bean.BaseEntity;

/**
 * 商品
 * 
 * @author yao
 *
 */
@Indexed
@Analyzer(impl = IKAnalyzer.class)
public class MNProduct extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@DocumentId
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
	
	@Field
	private String status;// 状态
	
	private FrankingTemplet franking;// 运费模板Id
	private MNCategory category;// 商品所属分类细类
	private MNParamTemplate paramTemplate;// 参数模板
	private Brand brand;// 商品品牌
	private Source source;// 商品货源

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public FrankingTemplet getFranking() {
		return franking;
	}

	public void setFranking(FrankingTemplet franking) {
		this.franking = franking;
	}

	public MNCategory getCategory() {
		return category;
	}

	public void setCategory(MNCategory category) {
		this.category = category;
	}

	public MNParamTemplate getParamTemplate() {
		return paramTemplate;
	}

	public void setParamTemplate(MNParamTemplate paramTemplate) {
		this.paramTemplate = paramTemplate;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public MNProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

}
