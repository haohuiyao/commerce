package com.meeno.ext.product.goods.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.meeno.ext.product.goods.entity.FrankingTemplet;
import com.meeno.framework.dao.BaseDaoImpl;

@Repository
public class FrankingDaoImpl extends BaseDaoImpl implements FrankingDao {

	@Override
	public List<FrankingTemplet> frankingTempletList() {
		return this.hibernateTemplate.find(" from FrankingTemplet");
	}

	@Override
	public FrankingTemplet findFrankByName(String name) {
		List<FrankingTemplet> list=this.find(" from FrankingTemplet f where f.fareName=?",name);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}

}