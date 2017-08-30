package com.meeno.framework.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.meeno.framework.bean.BaseEntity;
import com.meeno.framework.exception.BusinessRuntimeException;
import com.meeno.framework.util.CommonUtil;

/**
 * 基础Dao.
 *
 * @author leon_zhang
 *
 */
@Service
public class BaseDaoImpl implements BaseDao {
	// private static final Logger logger =
	// Logger.getLogger(BaseServiceImpl.class);

	/**
	 * HibernateTemplate调用.
	 */
	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;

	/**
	 * getHibernateTemplate.
	 * @return HibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * getSessionFactory.
	 * @return SessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return hibernateTemplate.getSessionFactory();
	}

	/**
	 * delete a object which HBM configuration file contains all-delete-orphan
	 * Must get it from database
	 * **/
	/**
	 * 删除.
	 *
	 * @param obj
	 *            Object对象
	 */
	public void delete(final Object obj) {
		hibernateTemplate.delete(obj);
	}

	/**
	 *保存.
	 *
	 * @param obj
	 *            Object对象
	 */
	public void save(final Object obj) {
		hibernateTemplate.save(obj);
	}

	/**
	 * 保存或更新.
	 *
	 * @param obj
	 *            Object对象
	 */
	public void saveOrupdate(final Object obj) {
		hibernateTemplate.saveOrUpdate(obj);

	}

	/**
	 * 更新.
	 *
	 * @param obj
	 *            Object对象
	 */
	public void merge(final Object obj) {
		hibernateTemplate.merge(obj);
	}

	/**
	 * 保存或更新.
	 *
	 * @param obj
	 *            Object对象
	 */
	public void update(final Object obj) {
		hibernateTemplate.update(obj);
	}

	/**
	 * 删除全部.
	 *
	 * @param objects
	 *            Object对象
	 */
	public void deleteAll(final Collection<?> objects) {
		hibernateTemplate.deleteAll(objects);
	}

	/**
	 * 判断重名.
	 *
	 * @param hsql
	 *            查询语句
	 * @param param
	 *            参数数组
	 * @param id
	 *            Object对象Id
	 * @return 成功或失败
	 */
	@SuppressWarnings("unchecked")
	public boolean isDuplicate(final String hsql, final Object[] param,
			final Long id) {
		List<? extends BaseEntity> list = hibernateTemplate.find(hsql, param);
		if (list.size() > 1) {
			return true;
		}
		if (list.size() == 1) {
			BaseEntity inputBean = list.get(0);
			if (id == null) {
				return true;
			}
			if (id.longValue() != inputBean.getId().longValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 通过Id找到数据.
	 *
	 * @param entityClass
	 *            实体
	 * @param ids
	 *            Object对象Id
	 * @return 查询出来的List
	 */
	@Override
	public <T extends BaseEntity> List<T> findDatasByIds(Class<T> entityClass, Long[] ids) {
		String param = CommonUtil.getIdSQLParam(ids);
		if (param != null) {
			return this.find("from " + entityClass.getName() + " t where t.id in (" + param + ")", (Object[])ids);
		}
		return null;
	}
	
	/**
	 * 批量更新状态
	 *
	 * @param entityClass
	 *            实体
	 * @param ids
	 *            Object对象Id
	 */
	@Override
	public void updateStatusByIds(Class<? extends BaseEntity> entityClass, String status, Long[] ids) {
		String value = CommonUtil.getIdSQLValue(ids);
		if (value != null) {
			this.hibernateTemplate.bulkUpdate("update " + entityClass.getName() + " t set t.status = ? where t.id in (" + value + ")", new Object[]{status});
		}
	}

	/**
	 * 通过Id找到数据.
	 *
	 * @param entityClass
	 *            实体
	 * @param id
	 *            Object对象Id
	 * @return 查询出来的对象
	 */
	public Object getObjectById(final Class<? extends BaseEntity> entityClass,
			final Long id) {
		BaseEntity baseEntity = (BaseEntity) hibernateTemplate.get(entityClass,
				id);
		if (baseEntity == null) {
			throw new BusinessRuntimeException("error.data.deleted");
		}
		return baseEntity;
	}

	/**
	 * setHibernateTemplate.
	 * @param hibernateTemplate HibernateTemplate
	 */
	public void setHibernateTemplate(final HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 执行调用存储过程的回调方法.
	 * @param callSql 执行语句
	 * @param callBackStatement CallBackStatement
	 * @throws SQLException 异常
	 * @return 对象
	 */
	@SuppressWarnings("deprecation")
	public Object executeProcedure(final String callSql,
			final CallBackStatement callBackStatement) throws SQLException {
		/***
		 * ConnectionProvider cp =
		 * ((SessionFactoryImplementor)getSessionFactory(
		 * )).getConnectionProvider(); Connection conn = cp.getConnection();
		 */
		Connection conn = getSessionFactory().getCurrentSession().connection();
		CallableStatement call = null;
		try {
			call = conn.prepareCall(callSql);
			return callBackStatement.inStatement(call);
		} finally {
			if (call != null) {
				call.close();
			}

		}
	}

	/**
	 * 清空session 缓存.
	 */
	public void clearSession() {
		this.getSessionFactory().getCurrentSession().clear();
	}

	/**
	 * 查询列表
	 * 
	 * @param queryString
	 *            HSQL
	 * @return 查询结果列表
	 */
	@Override
	public <T> List<T> find(String queryString) {
		return this.find(queryString, (Object[])null);
	}
	
	/**
	 * 查询列表
	 * 
	 * @param queryString
	 *            HSQL
	 * @param params
	 *            参数值数组
	 * @return 查询结果列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(String queryString, Object... params) {
		return this.hibernateTemplate.find(queryString, params);
	}

	@Override
	public <T> T get(Class<T> entityClass, Long id) {
		return this.hibernateTemplate.get(entityClass, id);
	}

	/**
	 * 执行sql
	 * @param sql
	 * @return
	 */
	@Override
	public void execSQL(String sql) {

		final String tmpSql = sql;
		this.hibernateTemplate.execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				
				session.createQuery(tmpSql).executeUpdate();
				return null;
			}
		});
	}
}