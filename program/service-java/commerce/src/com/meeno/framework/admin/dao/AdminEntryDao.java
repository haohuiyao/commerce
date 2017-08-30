package com.meeno.framework.admin.dao;

import com.meeno.framework.admin.entity.AdminEntry;
import com.meeno.framework.dao.BaseDao;

/**
 * @description 管理页面Dao
 * @time 2017年7月21日 下午7:55:25
 * @author Saturn
 * @version 1.0
 */
public interface AdminEntryDao extends BaseDao {
	
	AdminEntry findAdminEntryById(Long id);
}
