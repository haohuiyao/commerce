package com.meeno.framework.tag;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


public class QueryPageBean {
	@SuppressWarnings("unchecked")
	public static CutPageBean getQueryPageBean(HibernateTemplate hibernateTemplate,final String totalRowsHsql,final String resultSql,final Object[] params,final CutPageBean pageBean){
		return (CutPageBean)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				CutPageBean newPage = new CutPageBean();
				newPage.setExport(pageBean.isExport());
				newPage.setPageSize(pageBean.getPageSize());
				
				Query query = session.createQuery(totalRowsHsql);
				if(params!=null){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				List<?> queryList = query.list();
				int totalRow = Integer.valueOf(queryList.size()==0?"0":queryList.get(0).toString());
				newPage.setTotalRows(totalRow);
				if(totalRow==0){
					return newPage;
				}
				pageBean.setTotalRows(totalRow);
				newPage.setCurrentPage(pageBean.getCurrentPage());
				query = session.createQuery(resultSql);
				if(!newPage.isExport()){
					query.setFirstResult(newPage.getPageSize()*(newPage.getCurrentPage() - 1));
					query.setMaxResults(newPage.getPageSize());
				}
				if(params!=null){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				//session.clear();
				newPage.setDataList(query.list());
				return newPage;
			}});
	}
	public static CutPageBean getQueryPageBean(HibernateTemplate hibernateTemplate,final String totalRowsHsql,final String resultSql,final CutPageBean pageBean){
		return getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql, null, pageBean);
	}
	public static CutPageBean getQueryPageBean(HibernateTemplate hibernateTemplate,final String totalRowsHsql,final String resultSql,final String groupOrderSql,final CutPageBean pageBean,final List<CommonBean> queryParams){
		return getQueryPageBean(hibernateTemplate, totalRowsHsql, resultSql,groupOrderSql, null, pageBean,queryParams);
	}
	@SuppressWarnings("unchecked")
	public static CutPageBean getQueryPageBean(HibernateTemplate hibernateTemplate,final String totalRowsHsql,final String resultSql,final String groupOrderSql,final Object[] params,final CutPageBean pageBean,final List<CommonBean> queryParams){
		return (CutPageBean)hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				CutPageBean newPage = new CutPageBean();
				newPage.setExport(pageBean.isExport());
				newPage.setPageSize(pageBean.getPageSize());
				String queryTotalRowsHsql = builderSql(totalRowsHsql,queryParams);

				Query query = null;
				if(groupOrderSql==null || (totalRowsHsql+groupOrderSql).toLowerCase().indexOf(" group ")<0){
					query = session.createQuery(queryTotalRowsHsql);
					query = setQueryParams(queryParams,query);
					if(params!=null){
						for(int i=0;i<params.length;i++){
							query.setParameter(i, params[i]);
						}
					}
				}else{
					QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(queryTotalRowsHsql+groupOrderSql, queryTotalRowsHsql+groupOrderSql,
							Collections.EMPTY_MAP, (SessionFactoryImplementor)session .getSessionFactory());
							queryTranslator.compile(Collections.EMPTY_MAP, false);
							String tempSQL = queryTranslator.getSQLString();
							String countSQL = "select count(*) from (" + tempSQL + ") t";
							query = session.createSQLQuery(countSQL);
							int i=0;
							if(params!=null){
								for(;i<params.length;i++){
									query.setParameter(i, params[i]);
								}
							}
							query = setSQLParams(queryParams,query,i);
				}
				List<?> queryList = query.list();
				int totalRow = Integer.valueOf(queryList.size()==0?"0":queryList.get(0).toString());
				newPage.setTotalRows(totalRow);
				if(totalRow==0){
					return newPage;
				}
				pageBean.setTotalRows(totalRow);
				newPage.setCurrentPage(pageBean.getCurrentPage());
				String queryResultSql = builderSql(resultSql,queryParams);
				query = session.createQuery(queryResultSql+(groupOrderSql==null?"":" "+groupOrderSql));
				query = setQueryParams(queryParams,query);
				if(!newPage.isExport()){
					query.setFirstResult(newPage.getPageSize()*(newPage.getCurrentPage() - 1));
					query.setMaxResults(newPage.getPageSize());
				}
				if(params!=null){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				//session.clear();
				newPage.setDataList(query.list());
				return newPage;
			}});
	}

	public static String builderSql(String sql,List<CommonBean> queryParams){

		if(queryParams==null||queryParams.size()==0){
			return sql;
		}
		StringBuffer sqlBuffer = new StringBuffer(sql);
		if(sql.indexOf("where")<0){
			sqlBuffer.append(" where 1=1 ");
		}
		for(int i=0;i<queryParams.size();i++){
			String operator = "=";
			if(queryParams.get(i).getArithmeticOpertor()!=null){
				operator = queryParams.get(i).getArithmeticOpertor();
			}
			if(operator.equalsIgnoreCase("sql")){
				sqlBuffer.append(queryParams.get(i).getValue());
				continue;
			}
			if(queryParams.get(i).getTypeValue()==null && queryParams.get(i).getValue()==null){
				continue;
			}
			if(queryParams.get(i).getTypeValue()==null&&queryParams.get(i).getValue()!=null){
				queryParams.get(i).setTypeValue(queryParams.get(i).getValue());
			}
			if( queryParams.get(i).getTypeValue() instanceof String &&
					(queryParams.get(i).isApplyLikeOperator || operator.equalsIgnoreCase("like"))){
				sqlBuffer.append(" and "+queryParams.get(i).fieldName+" like :ht_param_"+i);
			}
			else{
				sqlBuffer.append(" and "+queryParams.get(i).fieldName+operator+":ht_param_"+i);
			}
		}
		return sqlBuffer.toString();
	}

	public static Query setQueryParams(List<CommonBean> queryParams,Query query){
		if(queryParams==null||queryParams.size()==0){
			return query;
		}
		for(int i=0;i<queryParams.size();i++){
			Object value = queryParams.get(i).value;
			if(queryParams.get(i).getTypeValue()==null && queryParams.get(i).getValue()==null){
				continue;
			}
			String operator = "=";
			if(queryParams.get(i).getArithmeticOpertor()!=null){
				operator = queryParams.get(i).getArithmeticOpertor();
			}
			if(operator.equalsIgnoreCase("sql")){
				continue;
			}
			if(queryParams.get(i).getTypeValue()!=null){
				value = queryParams.get(i).getTypeValue();
			}
			if(queryParams.get(i).getTypeValue() instanceof String &&
					(queryParams.get(i).isApplyLikeOperator || operator.equalsIgnoreCase("like"))){
				query.setParameter("ht_param_"+i, "%"+value+"%");
			}else{
				query.setParameter("ht_param_"+i, value);
			}
		}
		return query;
	}
	public static Query setSQLParams(List<CommonBean> queryParams,Query query,int position){
		if(queryParams==null||queryParams.size()==0){
			return query;
		}
		for(int i=0;i<queryParams.size();i++){
			/*Object value = queryParams.get(i).value;
			if(queryParams.get(i).getTypeValue()!=null){
				value = queryParams.get(i).getTypeValue();
			}
			query.setParameter(position+i, value);*/

			Object value = queryParams.get(i).value;
			if(queryParams.get(i).getTypeValue()==null && queryParams.get(i).getValue()==null){
				continue;
			}
			String operator = "=";
			if(queryParams.get(i).getArithmeticOpertor()!=null){
				operator = queryParams.get(i).getArithmeticOpertor();
			}
			if(operator.equalsIgnoreCase("sql")){
				continue;
			}
			if(queryParams.get(i).getTypeValue()!=null){
				value = queryParams.get(i).getTypeValue();
			}
			if(queryParams.get(i).getTypeValue() instanceof String &&
					(queryParams.get(i).isApplyLikeOperator || operator.equalsIgnoreCase("like"))){
				query.setParameter(position+i, "%"+value+"%");
			}else{
				query.setParameter(position+i, value);
			}
		}
		return query;
	}
}
