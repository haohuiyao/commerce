package com.meeno.ext.product.search;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;

public class SkuFSInfoBridge implements TwoWayFieldBridge{

	
	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		
		MNSku sku = (MNSku) value;
		MNProduct product = sku.getProduct();
		MNCategory category = product.getCategory();

		StringBuilder info = new StringBuilder();
		info.append(sku.getAdaptation());
		info.append(" ");
		info.append(sku.getProduct().getName());// 产品组名称
		info.append(" ");
		info.append(sku.getProduct().getCommon());// 产品组通用名
		info.append(" ");
		info.append(category.getName());// 分类名
		
		if (category.getParent() != null) {
			info.append(" ");
			info.append(category.getParent().getName());// 父级分类名
		}
		// fuzzySearchInfo
		luceneOptions.addFieldToDocument(name, info.toString(), document);
		
	}

	@Override
	public Object get(String name, Document document) {
		return document.get(name);
	}

	@Override
	public String objectToString(Object object) {
		if (object instanceof String) {
			return (String) object;
		}
		if (object instanceof Long) {
			return String.valueOf(object);
		}
		return null;
	}

}
