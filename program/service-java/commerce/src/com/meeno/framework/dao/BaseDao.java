package com.meeno.framework.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.meeno.framework.bean.BaseEntity;

/**
 * 基础dao.
 *
 * @author leon_zhang
 *
 */
public interface BaseDao {
	/**
	 *保存.
	 *
	 * @param obj
	 *            Object对象
	 */
	void save(Object obj);

	/**
	 * 保存或更新.
	 *
	 * @param obj
	 *            Object对象
	 */
	void update(Object obj);

	/**
	 * 保存或更新.
	 *
	 * @param obj
	 *            Object对象
	 */
	void saveOrupdate(Object obj);

	/**
	 * 保存或更新.
	 *
	 * @param obj
	 *            Object对象
	 */
	void merge(Object obj);

	/**
	 * 删除.
	 *
	 * @param obj
	 *            Object对象
	 */
	void delete(Object obj);

	/**
	 * 删除全部.
	 *
	 * @param objects
	 *            Object对象
	 */
	void deleteAll(Collection<?> objects);
	
	/**
	 * 批量更新状态
	 *
	 * @param entityClass
	 *            实体
	 * @param ids
	 *            Object对象Id
	 */
	void updateStatusByIds(Class<? extends BaseEntity> entityClass, String status, Long[] ids);

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
	boolean isDuplicate(String hsql, Object[] param, Long id);

	/**
	 * 通过Id找到数据.
	 *
	 * @param entityClass
	 *            实体
	 * @param ids
	 *            Object对象Id
	 * @return 查询出来的List
	 */
	<T extends BaseEntity> List<T> findDatasByIds(Class<T> entityClass, Long[] ids);

	/**
	 * 通过Id找到数据.
	 *
	 * @param entityClass
	 *            实体
	 * @param id
	 *            Object对象Id
	 * @return 查询出来的对象
	 */
	Object getObjectById(Class<? extends BaseEntity> entityClass, Long id);


	/**
	 * 执行调用存储过程的回调方法.
	 * @param callSql 执行语句
	 * @param callBackStatement CallBackStatement
	 * @throws SQLException 异常
	 * @return 对象
	 */
	Object executeProcedure(final String callSql,
			CallBackStatement callBackStatement) throws SQLException;

	/**
	 * 清空session 缓存.
	 */
	void clearSession();
	
	/**
	 * 查询列表
	 * 
	 * @param queryString
	 *            HSQL
	 * @return 查询结果列表
	 */
	<T> List<T> find(String queryString);
	
	/**
	 * 查询列表
	 * 
	 * @param queryString
	 *            HSQL
	 * @param params
	 *            参数值数组
	 * @return 查询结果列表
	 */
	<T> List<T> find(String queryString, Object... params);
	
	<T> T get(Class<T> entityClass, Long id);
	
	
	/**
	 * 执行sql
	 * @param sql
	 * @return
	 */
	void execSQL(String sql);
}