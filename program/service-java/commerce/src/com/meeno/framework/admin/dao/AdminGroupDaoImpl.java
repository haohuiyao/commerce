package com.meeno.framework.admin.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.meeno.framework.admin.entity.AdminGroup;
import com.meeno.framework.dao.BaseDaoImpl;

/**
 * @description 管理员分组Dao实现
 * @time 2017年7月21日 下午5:43:10
 * @author Saturn
 * @version 1.0
 */
@Repository
public class AdminGroupDaoImpl extends BaseDaoImpl implements AdminGroupDao {

	@Override
	public AdminGroup findAdminGroupById(Long id) {
		List<AdminGroup> list = this.find(" from AdminGroup m where m.id = ? ", id);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
