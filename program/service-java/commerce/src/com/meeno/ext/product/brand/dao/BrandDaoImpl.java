package com.meeno.ext.product.brand.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.meeno.ext.product.brand.entity.Brand;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.util.StringContant;

@Repository
public class BrandDaoImpl extends BaseDaoImpl implements BrandDao {

	@Override
	public List<Brand> getAllMachineBrand() {
		return this.find("from Brand m where m.status=? order by m.id ", StringContant.STATUS_AVA);
	}

	@Override
	public Brand findBrandByName(String name) {
		List<Brand> list = this.find(" from Brand m where m.status=? and m.name=?", StringContant.STATUS_AVA, name);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void delBrandCategory(Long brandId) {
		this.hibernateTemplate.bulkUpdate(" delete MNCategoryBrand c where c.brand.id=?", brandId);
	}

	@Override
	public List<Brand> getBrandLikeCategoryStyle(String style) {
		// TODO Auto-generated method stub
		List<Brand> list=this.find("select b.brand from MNCategoryBrand b where b.category.style like ?", style+"%");
		if(!CollectionUtils.isEmpty(list)){
			return list;
		}
		return null;
	}

}
