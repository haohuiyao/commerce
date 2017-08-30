package com.meeno.ext.product.goods.entity;

import java.util.Date;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.ClassBridge;
import org.hibernate.search.annotations.ClassBridges;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.ext.product.search.SkuCategoryStyleBridge;
import com.meeno.ext.product.search.SkuFSInfoBridge;
import com.meeno.ext.product.search.SkuParentCategoryBridge;
import com.meeno.ext.product.search.SkuProductRelBridge;
import com.meeno.ext.product.search.SkuProductSourceBridge;
import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.bean.BaseEntity;

/**
 * 
 * 商品SKU
 * 
 * @author yao
 *
 */

@Indexed
@Analyzer(impl = IKAnalyzer.class)
@ClassBridges({ @ClassBridge(name = "fuzzySearchInfo", impl = SkuFSInfoBridge.class),
		@ClassBridge(name = "productSourceId", impl = SkuProductSourceBridge.class),
		@ClassBridge(name = "categoryStyle", impl = SkuCategoryStyleBridge.class) })
public class MNSku extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@DocumentId
	private Long id;

	@IndexedEmbedded(depth = 1)
	private MNProduct product;// 所属商品
	private Date createTime;// 创建时间
	private MNParamTemplate paramTemplate;// 参数模板
	private String paramsValue;// 参数模板的值
	private String adaptation;// 品牌和适配机型
	private String picture;// 图片列表
	private String code;// 商品编号

	@Field
	private String status;// 状态
	private String detail;// 商品详情

	@IndexedEmbedded(depth = 1)
	private Brand brand;// 所属机型品牌
	private String skuName;// 商品名称
	private String weight;// 重量(g)
	private String displayImg;// 商品首图
	private String summary;// 简介
	private String volume;
	
	@Field
	@Analyzer(impl = WhitespaceAnalyzer.class)
	private String productRel;

	public MNProduct getProduct() {
		return product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProduct(MNProduct product) {
		this.product = product;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MNParamTemplate getParamTemplate() {
		return paramTemplate;
	}

	public void setParamTemplate(MNParamTemplate paramTemplate) {
		this.paramTemplate = paramTemplate;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getProductRel() {
		return productRel;
	}

	public void setProductRel(String productRel) {
		this.productRel = productRel;
	}
	
	
}
