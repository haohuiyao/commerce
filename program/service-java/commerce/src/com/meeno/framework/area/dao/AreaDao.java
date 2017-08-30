package com.meeno.framework.area.dao;

import java.util.List;

import com.meeno.framework.area.entity.MNArea;
import com.meeno.framework.dao.BaseDao;


public interface AreaDao extends BaseDao{
	
	/**
	 * 获取子列表
	 * @return
	 */
	List<MNArea> getChildList(Long parentId);
	
	/**
	 * 获取所有省份信息
	 * @return
	 */
	List<MNArea> getAllProvinceInfoList();
	
	/**
	 * 通过名称查找省份
	 * @param provinceName
	 * @return
	 */
	MNArea findProvinceByName(String provinceName);
	
	List<MNArea> getMNAreaByLevel(Integer level);
}