package com.meeno.ext.product.brand.entity;

import java.util.Date;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.meeno.framework.bean.BaseEntity;

@Indexed
@Analyzer(impl = IKAnalyzer.class)
public class Brand extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DocumentId
	private Long id;

	private String name;// 品牌

	private String abbreviation;// 品牌缩写

	private String imgUrl;// 品牌图片

	@Field
	private String status;// 品牌状态

	private String groom;// 是否设为推荐品牌

	private Date groomTime;// 设置时间

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getGroom() {
		return groom;
	}

	public void setGroom(String groom) {
		this.groom = groom;
	}

	public Date getGroomTime() {
		return groomTime;
	}

	public void setGroomTime(Date groomTime) {
		this.groomTime = groomTime;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
