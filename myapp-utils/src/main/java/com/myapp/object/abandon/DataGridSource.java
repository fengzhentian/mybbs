package com.myapp.object.abandon;

import java.util.List;

public class DataGridSource<T>
{
	private List<T> rows;
	private Integer total;

	public DataGridSource()
	{
		super();
	}

	public DataGridSource(List<T> rows, Integer total)
	{
		super();
		this.rows = rows;
		this.total = total;
	}

	public List<T> getRows()
	{
		return rows;
	}

	public void setRows(List<T> rows)
	{
		this.rows = rows;
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}
}