package com.meeno.framework.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
/***
 * 回调函数用于执行CallableStatement.
 * @author winner
 *
 */
public interface CallBackStatement {
	/**
	 *执行回调.
	 * @param callStatement 存储过程回调函数.
	 * @throws SQLException SQL异常.
	 * @return 返回调用结果.
	 */
	Object inStatement(CallableStatement callStatement) throws SQLException;
}
