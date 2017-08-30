package com.meeno.ext.product.goods.dao;

import java.util.List;

import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.framework.dao.BaseDao;

public interface FrankingDao extends BaseDao {
	
	/**
	 * 获取运费列表
	 * @return
	 */
	List<FrankingTemplet> frankingTempletList();
	
	/**
	 * 根据名称查询运费列表
	 * @param name
	 * @return
	 */
	FrankingTemplet findFrankByName(String name);
}