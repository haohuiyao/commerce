package com.meeno.framework.area.service;

import java.util.List;

import com.meeno.framework.area.entity.MNArea;

/**
 * 
 * @author Jeff_Gao
 * 
 * @date 2017年4月16日
 */
public interface AreaService {

	/**
	 * 获取子列表
	 * @return
	 */
	List<MNArea> getChildList(Long parentId);
	
	
	List<MNArea> getAllProvinceInfoList();
	
	/**
	 * 根据等级获取地区
	 * @param level
	 * @return
	 */
	List<MNArea> getMNAreaByLevel(Integer level);
	
	/**
	 * 获取指定地区
	 * @param areaId
	 * @return
	 */
	MNArea getMNAreaById(Long areaId);
}
