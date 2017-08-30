package com.meeno.framework.user.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.meeno.framework.dao.BaseDaoImpl;
import com.meeno.framework.passport.entity.MNAccount;
import com.meeno.framework.tag.CutPageBean;
import com.meeno.framework.tag.QueryPageBean;
import com.meeno.framework.user.entity.BaseUser;
import com.meeno.framework.util.CommonUtil;
import com.meeno.util.StringContant;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	
	/**
	 * 查找用户信息
	 * @param account
	 * @param userType
	 * @return
	 */
	@Override
	public BaseUser findUser(MNAccount account, String userType) {
		StringBuffer queryHql=new StringBuffer("from BaseUser b where b.account.uuid = ?");
		List<Object> params=Lists.newArrayList();
		params.add(account.getUuid());
		if(CommonUtil.isNotZeroLengthTrimString(userType)){
			queryHql.append("and b.userType = ? ");
			params.add(userType);
		}
		List<BaseUser> list = this.find(queryHql.toString(), params.toArray());
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增用户
	 * @param account
	 * @param name
	 * @param gender
	 * @param userType
	 * @return
	 */
	@Override
	public BaseUser saveBaseUser(MNAccount account, String name, String gender, String userType, Long groupId) {
		BaseUser baseUser = new BaseUser();
		baseUser.setName(name);
		baseUser.setGender(gender);
		baseUser.setUserType(userType);
		baseUser.setCreateTime(new Date());
		baseUser.setGroupId(groupId);
		baseUser.setAccount(account);
		this.save(baseUser);
		return baseUser;
	}

	/**
	 * 获取用户列表
	 * @param pageBean
	 * @param userType
	 * @return CutPageBean
	 */
	@Override
	public CutPageBean geUserList(CutPageBean pageBean, String userType) {
		StringBuilder queryCondition = new StringBuilder("from BaseUser m where m.userType = ? ");
		
		String resultSql = queryCondition.toString();
		String totalRowsHsql = " select count(m) " + queryCondition.toString();

		return QueryPageBean.getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,
				" order by m.createTime desc ", new Object[] {userType}, pageBean, null);
	}
}