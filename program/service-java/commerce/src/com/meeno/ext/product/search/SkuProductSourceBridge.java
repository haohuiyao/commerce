package com.meeno.ext.product.search;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

import com.meeno.ext.product.goods.entity.MNProduct;
import com.meeno.ext.product.goods.entity.MNSku;

public class SkuProductSourceBridge implements TwoWayFieldBridge {

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {

		MNSku sku = (MNSku) value;
		MNProduct product = sku.getProduct();

		// goods source id
		String sourceIdStr = (product.getSource() == null) ? "" : (product.getSource().getId()+"");
		luceneOptions.addFieldToDocument(name, sourceIdStr, document);

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
