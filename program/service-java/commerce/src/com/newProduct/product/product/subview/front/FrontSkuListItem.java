package com.newProduct.product.product.subview.front;

import java.io.Serializable;

import com.meeno.ext.product.goods.entity.MNSku;

public class FrontSkuListItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long productId;
	private Long skuId;
	private String imgUrls;// 商品图片地址
	private String goodsName;// 商品名称
	private String title;// 标题
	private String displayImg;

	public FrontSkuListItem(MNSku sku) {
		super();
		this.productId = sku.getProduct().getId();
		this.skuId = sku.getId();
		this.imgUrls = sku.getProduct().getPicture();
		this.goodsName = sku.getProduct().getName();
		this.displayImg = sku.getDisplayImg();
		this.title=sku.getAdaptation();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDisplayImg() {
		return displayImg;
	}

	public void setDisplayImg(String displayImg) {
		this.displayImg = displayImg;
	}

}
