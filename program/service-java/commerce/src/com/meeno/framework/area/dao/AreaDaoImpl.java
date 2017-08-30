package com.meeno.framework.area.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.meeno.framework.area.entity.MNArea;
import com.meeno.framework.dao.BaseDaoImpl;

@Repository
public class AreaDaoImpl extends BaseDaoImpl implements AreaDao {

	/**
	 * 获取子列表
	 * @return
	 */
	@Override
	public List<MNArea> getChildList(Long parentId) {
		if (parentId == null) return new ArrayList<>();
		return this.find("from MNArea m where m.parentId = ?", parentId);
	}
	
	
	@Override
	public List<MNArea> getAllProvinceInfoList() {
		return this.find(" from MNArea m where m.level = 1 order by m.initial");
	}


	@Override
	public MNArea findProvinceByName(String provinceName) {
		List<MNArea> list = this.find(" from MNArea m where m.name = ? and m.level = 1", provinceName);
		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}
		return null;
	}


	@Override
	public List<MNArea> getMNAreaByLevel(Integer level) {
		StringBuffer queryHql=new StringBuffer(" from MNArea m where 1=1");
		List<Object> params=new ArrayList<>();
		if(level!=null){
			queryHql.append(" and m.level=?");
			params.add(level);
		}
		return this.find(queryHql.toString(),params.toArray());
	}
	
	
}