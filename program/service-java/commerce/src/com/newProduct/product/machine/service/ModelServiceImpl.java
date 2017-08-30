package com.newProduct.product.machine.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.meeno.ext.product.goods.dao.ProductDao;
import com.meeno.ext.product.goods.entity.MNSku;
import com.meeno.ext.product.goods.service.ProductService;
import com.meeno.ext.product.util.ProductContant;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.util.StringContant;

@Service
public class ModelServiceImpl extends BaseDaoImpl implements ModelService {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductDao productDao;

	@Override
	public void addSku(Long productId, Long paramsId, String paramValue, String adaptation, String picture,
			String detail, Long brandId, String skuName, String weight, String displayImg, String summary,
			String volumn, String rel) {
		MNSku sku = this.productService.addSku(productId, paramsId, paramValue, adaptation, picture, detail, brandId,
				skuName, weight, displayImg, summary, volumn);

		// 适配关联关系
		if (CommonUtil.isNotZeroLengthTrimString(rel)) {
			this.productService.saveProductRel(ProductContant.OBJ_TYPE_SKU,
					sku.getId(), rel);
		}
	}

	@Override
	public void editSku(Long id, Long productId, Long paramsId, String paramValue, String adaptation, String picture,
			String detail, Long brandId, String skuName, String weight, String displayImg, String summary,
			String volumn) {
		this.productService.editSku(id, productId, paramsId, paramValue, adaptation, picture, detail, brandId, skuName,
				weight, displayImg, summary, volumn);
	}

	@Override
	public CutPageBean findSkuList(CutPageBean pageBean, String condition, String status) {
		return this.productService.findSkuList(pageBean, condition, status);
	}

	@Override
	public void putOutSku(JSONArray skuId) {
		if (skuId == null || skuId.size() == 0) {
			throw new BusinessRuntimeException(ProductError.PRODUCT_ID_NULL);
		}
		Long[] ids = CommonUtil.getJsonToLongArray(skuId);
		this.productDao.updateStatusByIds(MNSku.class, StringContant.STATUS_DIS, ids);
	}

	@Override
	public void putUpSku(JSONArray skuId) {
		if (skuId == null || skuId.size() == 0) {
			throw new BusinessRuntimeException(ProductError.PRODUCT_ID_NULL);
		}
		Long[] ids = CommonUtil.getJsonToLongArray(skuId);
		this.productDao.updateStatusByIds(MNSku.class, StringContant.STATUS_AVA, ids);
	}

	@Override
	public void saveProductRel(Integer relType, Integer objType, Long objId, String rel) {
		this.productService.saveProductRel(objType, objId, rel);
	}

	@Override
	public Map<String, List> findProdcutRel(Integer relType, Integer objType, Long objId, Integer targetType) {
		return this.productService.findProdcutRel(objType, objId, targetType);
	}

}
