package com.myapp.mybatis.plugin;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.myapp.mybatis.dialect.Dialect;
import com.myapp.mybatis.dialect.DialectParser;

/**
 * 支持物理分页。
 * sql的解析是在StatementHandler里完成的，所以为了重写sql需要拦截StatementHandler
 */
@Intercepts(
{ @Signature(type = StatementHandler.class, method = "prepare", args =
{ Connection.class }) })
public class PageInterceptor implements Interceptor
{

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static String DEFAULT_PAGE_SQL_ID = ".*Page$"; // 需要拦截的ID(正则匹配)
	// 方言
	Dialect dialect;

	public Object intercept(Invocation invocation) throws Throwable
	{
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		// BoundSql boundSql = statementHandler.getBoundSql();
		// MetaObject是Mybatis提供的一个的工具类，通过它包装一个对象后可以获取或设置该对象的原本不可访问的属性（比如那些私有属性）
		MetaObject metaStatementHandler = MetaObject.forObject(
				statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h"))
		{
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target"))
		{
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		if ((rowBounds != null) && (rowBounds != RowBounds.DEFAULT))
		{
			// property在mybatis配置文件内配置
			Configuration configuration = (Configuration) metaStatementHandler
					.getValue("delegate.configuration");
			// 获取方言
			dialect = DialectParser.parse(configuration);
			// 设置pageSqlId
			String pageSqlId = configuration.getVariables().getProperty(
					"pageSqlId");
			if (null == pageSqlId || "".equals(pageSqlId))
			{
				pageSqlId = DEFAULT_PAGE_SQL_ID;
			}
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
					.getValue("delegate.mappedStatement");
			BoundSql boundSql = (BoundSql) metaStatementHandler
					.getValue("delegate.boundSql");
			// 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
			if (mappedStatement.getId().matches(pageSqlId))
			{
				Object parameterObject = boundSql.getParameterObject();
				if (parameterObject == null)
				{
					throw new NullPointerException("parameterObject is null!");
				}
				else
				{
					String sql = boundSql.getSql();
					// 重写sql
					String pageSql = dialect.getLimitString(sql,
							rowBounds.getOffset(), rowBounds.getLimit());
					metaStatementHandler.setValue("delegate.boundSql.sql",
							pageSql);
					// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
					metaStatementHandler.setValue("delegate.rowBounds.offset",
							RowBounds.NO_ROW_OFFSET);
					metaStatementHandler.setValue("delegate.rowBounds.limit",
							RowBounds.NO_ROW_LIMIT);
				}
			}
		}
		return invocation.proceed();
	}

	public Object plugin(Object target)
	{
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties)
	{
	}

}
