package com.meeno.framework.permissions.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.permissions.entity.Action;
import com.meeno.framework.permissions.entity.Role;
import com.meeno.framework.util.CommonUtil;

@Repository
public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {

	@Override
	public List<Action> getActionList(String type) {
		StringBuffer query = new StringBuffer(" from Action a ");
		if (CommonUtil.isNotZeroLengthTrimString(type)) {
			String roleActionSql="select r.action from RoleAction r where r.role.roleNo=? ";
			return this.find(roleActionSql,type);
		}else{
			return this.find(query.toString());
		}
	}

	@Override
	public List<Action> getActionChildrens(Long id) {
		return this.find(" from Action a where a.parentId=?",id);
	}

	@Override
	public boolean delAction(Object[] ids) {
		Query query=this.getSessionFactory().getCurrentSession().createQuery("delete Action a where a.id in(:ids)");
		Long[] id=new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			id[i]=Long.valueOf((Integer)ids[i]);
		}
		query.setParameterList("ids", id);
		if(query.executeUpdate()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Role> getRoleList() {
		return this.find(" from Role");
	}

}
