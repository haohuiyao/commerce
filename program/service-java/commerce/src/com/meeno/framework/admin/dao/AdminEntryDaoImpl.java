package com.meeno.framework.admin.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.meeno.framework.admin.entity.AdminEntry;
import com.meeno.framework.dao.BaseDaoImpl;

/**
 * @description 管理页面Dao实现
 * @time 2017年7月21日 下午7:56:01
 * @author Saturn
 * @version 1.0
 */
@Repository
public class AdminEntryDaoImpl extends BaseDaoImpl implements AdminEntryDao {

	@Override
	public AdminEntry findAdminEntryById(Long id) {
		List<AdminEntry> list = this.find(" from AdminEntry m where m.id = ? ", id);
		if (!CollectionUtils.isEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
