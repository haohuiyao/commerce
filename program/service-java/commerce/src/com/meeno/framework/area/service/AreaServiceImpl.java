package com.meeno.framework.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeno.ext.product.util.ProductError;
import com.meeno.framework.area.dao.AreaDao;
import com.meeno.framework.area.entity.MNArea;
import com.meeno.framework.util.MeenoAssert;

/**
 * 
 * @author Jeff_Gao
 * 
 * @date 2017年4月16日
 */

@Service
public class AreaServiceImpl implements AreaService {

	

	@Autowired
	private AreaDao areaDao;

	/**
	 * 获取子列表
	 * @return
	 */
	@Override
	public List<MNArea> getChildList(Long parentId) {
		//参数检查
		MeenoAssert.notNull(parentId, "父节点ID为空");
		
		return areaDao.getChildList(parentId);
	}

	@Override
	public List<MNArea> getAllProvinceInfoList() {
		return areaDao.getAllProvinceInfoList();
	}

	@Override
	public List<MNArea> getMNAreaByLevel(Integer level) {
		return this.areaDao.getMNAreaByLevel(level);
	}

	@Override
	public MNArea getMNAreaById(Long areaId) {
		MeenoAssert.notNull(areaId, ProductError.ID_NULL);
		return this.areaDao.get(MNArea.class, areaId);
	}

}
