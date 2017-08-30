package com.meeno.ext.product.category.subview.back;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.category.entity.WPRecommendGenre;
import com.meeno.framework.tree.TreeEntity;

/**
 * 
 * 商品分类
 * 
 * @author jeff_gao
 *
 */
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long categoryId;// 分类ID
	private String name;// 分类名
	private String imgUrl;// 图片地址
	private String abbreviation;// 首字母缩写
	private Long templateId;// 参数模板ID
	private Long type;
	private String style;
	private Integer level;
	private List<CategoryEntity> childs;// 子级实体

	public CategoryEntity(MNCategory category) {
		this.categoryId = category.getId();
		this.name = category.getName();
		this.imgUrl = category.getImgUrl();
		this.abbreviation = category.getAbbreviation();

		Set<WPRecommendGenre> genres = category.getGenres();
		if (genres != null && genres.size() > 0) {
			this.type = (genres.iterator().next().getCategory().getId());
		} else {
			this.type = null;
		}
		if(category.getParamTemplate()!=null){
			this.templateId = category.getParamTemplate().getId();
		}
		this.style = category.getStyle();
		this.level = category.getLevel();
	}

	public List<CategoryEntity> getChilds() {
		return childs;
	}

	public void setChilds(List<CategoryEntity> childs) {
		this.childs = childs;
	}

	public CategoryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
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

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}
