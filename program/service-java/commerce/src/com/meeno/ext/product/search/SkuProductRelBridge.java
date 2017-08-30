package com.meeno.ext.product.search;

import java.util.List;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.MNProductRel;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.util.ProductContant;

@Component
public class SkuProductRelBridge implements TwoWayFieldBridge {

	@Autowired
	private ProductDao productDao;

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {

		MNSku sku = (MNSku) value;

		// 查找SKU相关的关联关系
		//Integer[] relTypeArr = new Integer[] {ProductContant.OBJ_REL_PARTS_BRAND, ProductContant.OBJ_REL_PARTS_MODEL_GROUP, ProductContant.OBJ_REL_PARTS_MODEL};
		List<MNProductRel> prList = productDao.findProductRelList(null, ProductContant.OBJ_TYPE_SKU, sku.getId());
		
		StringBuilder prStrBuilder = new StringBuilder();
		if (!CollectionUtils.isEmpty(prList)) {
			for (MNProductRel pr : prList) {
				if (prStrBuilder.length() > 0) {
					prStrBuilder.append(" ");
				}
				prStrBuilder.append(pr.getRelType()+"*"+pr.getObjId2());
			}
		}
		
		luceneOptions.addFieldToDocument(name, prStrBuilder.toString(), document);
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
