package com.meeno.ext.product.search;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

import com.meeno.ext.product.category.entity.MNCategory;
import com.meeno.ext.product.goods.entity.MNSku;

public class SkuCategoryBridge implements TwoWayFieldBridge {

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {

		MNSku sku = (MNSku) value;
		MNCategory category = sku.getProduct().getCategory();

		// category id
		luceneOptions.addFieldToDocument(name, category.getId()+"", document);
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
