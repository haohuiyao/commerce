package com.meeno.ext.product.category.entity;

import java.util.Date;
import java.util.Set;

import com.meeno.ext.product.template.entity.MNParamTemplate;
import com.meeno.framework.tree.TreeEntity;

/**
 * 
 * 商品分类
 * 
 * @author yao
 *
 */
public class MNCategory extends TreeEntity {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;// 分类名
	private String imgUrl;// 图片地址
	private String abbreviation;// 首字母缩写
	private MNCategory parent;
	private MNParamTemplate paramTemplate;// 参数模板
	private Set<WPRecommendGenre> genres;
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<WPRecommendGenre> getGenres() {
		return genres;
	}

	public void setGenres(Set<WPRecommendGenre> genres) {
		this.genres = genres;
	}

	public MNCategory getParent() {
		return parent;
	}

	public void setParent(MNCategory parent) {
		this.parent = parent;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public MNCategory() {
		super();
		// TODO Auto-generated constructor stub
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

	public MNParamTemplate getParamTemplate() {
		return paramTemplate;
	}

	public void setParamTemplate(MNParamTemplate paramTemplate) {
		this.paramTemplate = paramTemplate;
	}

}
