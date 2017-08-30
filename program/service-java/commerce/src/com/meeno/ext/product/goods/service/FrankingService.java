package com.meeno.ext.product.goods.service;

import java.math.BigDecimal;
import java.util.List;

import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.framework.dao.BaseDao;
import com.meeno.framework.tag.CutPageBean;

public interface FrankingService extends BaseDao {
	/**
	 * 获取运费列表
	 * 
	 * @return
	 */
	List<FrankingTemplet> frankingTempletList();

	/**
	 * 新增运费模板
	 * 
	 * @param name
	 * @param pattern
	 * @param dispatching
	 * @param baseWeight
	 * @param basePrice
	 * @param addWeight
	 * @param addPrice
	 * @param scope
	 */
	void addFranking(String name, String postage, Long pattern, String dispatching, BigDecimal baseWeight,
			BigDecimal basePrice, BigDecimal addWeight, BigDecimal addPrice, String scope);

	void editFranking(Long id, String name, String postage, Long pattern, String dispatching, BigDecimal baseWeight,
			BigDecimal basePrice, BigDecimal addWeight, BigDecimal addPrice, String scope);
}
