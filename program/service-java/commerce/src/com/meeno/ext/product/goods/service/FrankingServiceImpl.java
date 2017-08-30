package com.meeno.ext.product.goods.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.ext.product.goods.dao.FrankingDao;
import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.util.MeenoAssert;

@Service
public class FrankingServiceImpl extends BaseDaoImpl implements FrankingService {
	@Autowired
	private FrankingDao frankingDao;

	@Override
	public List<FrankingTemplet> frankingTempletList() {
		return this.frankingDao.frankingTempletList();
	}

	@Override
	public void addFranking(String name, String postage, Long pattern, String dispatching, BigDecimal baseWeight,
			BigDecimal basePrice, BigDecimal addWeight, BigDecimal addPrice, String scope) {
		MeenoAssert.notNull(name, "运费模板名称不能为空");
		MeenoAssert.notNull(pattern, "计费方式不能为空");
		MeenoAssert.notNull(dispatching, "配送方式 ：快递物流");
		MeenoAssert.notNull(baseWeight, "基础单位不能为空");
		MeenoAssert.notNull(basePrice, "基础计费不能为空");
		MeenoAssert.notNull(addWeight, "新增单位不能为空");
		MeenoAssert.notNull(addPrice, "新增计费不能为空");
		FrankingTemplet existFt = this.frankingDao.findFrankByName(name);
		if (existFt != null) {
			throw new BusinessRuntimeException(ProductError.PARAM_ALREADY_EXIST);
		}
		FrankingTemplet frankingTemplet = new FrankingTemplet();
		frankingTemplet.setFareName(name);
		frankingTemplet.setPattern(pattern);
		frankingTemplet.setDispatching(dispatching);
		frankingTemplet.setBaseWeight(baseWeight);
		frankingTemplet.setBasePrice(basePrice);
		frankingTemplet.setAddWeight(addWeight);
		frankingTemplet.setAddPrice(addPrice);
		frankingTemplet.setScope(scope);
		this.frankingDao.save(frankingTemplet);
	}

	@Override
	public void editFranking(Long id, String name, String postage, Long pattern, String dispatching,
			BigDecimal baseWeight, BigDecimal basePrice, BigDecimal addWeight, BigDecimal addPrice, String scope) {
		MeenoAssert.notNull(id, ProductError.ID_NULL);
		FrankingTemplet frankingTemplet = this.frankingDao.get(FrankingTemplet.class, id);
		MeenoAssert.notNull(frankingTemplet, ProductError.FRANKING_TEMPLET_NOT_EXIST);
		MeenoAssert.notNull(name, "运费模板名称不能为空");
		MeenoAssert.notNull(pattern, "计费方式不能为空");
		MeenoAssert.notNull(dispatching, "配送方式 ：快递物流");
		MeenoAssert.notNull(baseWeight, "基础单位不能为空");
		MeenoAssert.notNull(basePrice, "基础计费不能为空");
		MeenoAssert.notNull(addWeight, "新增单位不能为空");
		MeenoAssert.notNull(addPrice, "新增计费不能为空");
		if (!frankingTemplet.getFareName().equals(name)) {
			FrankingTemplet existFt = this.frankingDao.findFrankByName(name);
			if (existFt != null) {
				throw new BusinessRuntimeException(ProductError.PARAM_ALREADY_EXIST);
			}
			frankingTemplet.setFareName(name);
		}
		frankingTemplet.setPattern(pattern);
		frankingTemplet.setDispatching(dispatching);
		frankingTemplet.setBaseWeight(baseWeight);
		frankingTemplet.setBasePrice(basePrice);
		frankingTemplet.setAddWeight(addWeight);
		frankingTemplet.setAddPrice(addPrice);
		frankingTemplet.setScope(scope);
		frankingTemplet.setUpdateTime(new Date());
		this.frankingDao.update(frankingTemplet);
	}

}
