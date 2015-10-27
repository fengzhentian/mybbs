package com.myapp.mybatis.dialect;

public enum DBDialectType
{
	MYSQL("mysql"), ORACLE("oracle"), SQLSERVER("sqlserver");

	private String type;

	private DBDialectType(String type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return type;
	}
}