package com.meeno.framework.common.dao;

import com.meeno.framework.common.entity.MNConfig;
import com.meeno.framework.dao.BaseDao;

public interface CommonDao extends BaseDao {

	/**
	 * 根据参数名查找参数
	 * 
	 * @param name
	 * @return
	 */
	MNConfig findConfigByName(String name);
}