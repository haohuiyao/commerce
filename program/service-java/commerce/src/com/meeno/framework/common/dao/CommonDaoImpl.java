package com.meeno.framework.common.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.meeno.framework.common.entity.MNConfig;
import com.meeno.framework.dao.BaseDaoImpl;

@Repository
public class CommonDaoImpl extends BaseDaoImpl implements CommonDao {

	/**
	 * 根据参数名查找参数
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public MNConfig findConfigByName(String name) {
		List<MNConfig> list = this.find("from MNConfig m where m.name = ?", name);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}