package com.meeno.framework.admin.dao;

import com.meeno.framework.admin.entity.AdminGroup;
import com.meeno.framework.dao.BaseDao;

/**
 * @description 管理员分组Dao
 * @time 2017年7月21日 下午5:30:17
 * @author Saturn
 * @version 1.0
 */
public interface AdminGroupDao extends BaseDao{

	AdminGroup findAdminGroupById(Long id);
}
