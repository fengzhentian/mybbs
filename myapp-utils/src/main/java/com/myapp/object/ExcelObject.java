package com.myapp.object;

public class ExcelObject
{
	String sheetName;// 表名

	String titleName;

	String topnum;

	String colName;
	
	String order;

	String[] colNameArr;

	String filedValue;

	String[] fieldArr;

	String[] showArr;

	boolean isHasValue;

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public String getTitleName()
	{
		return titleName;
	}

	public void setTitleName(String titleName)
	{
		this.titleName = titleName;
	}

	public String getTopnum()
	{
		return topnum;
	}

	public void setTopnum(String topnum)
	{
		this.topnum = topnum;
	}

	public String getColName()
	{
		return colName;
	}

	public void setColName(String colName)
	{
		this.colName = colName;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String[] getColNameArr()
	{
		return colNameArr;
	}

	public void setColNameArr(String[] colNameArr)
	{
		this.colNameArr = colNameArr;
	}

	public String getFiledValue()
	{
		return filedValue;
	}

	public void setFiledValue(String filedValue)
	{
		this.filedValue = filedValue;
	}

	public String[] getFieldArr()
	{
		return fieldArr;
	}

	public void setFieldArr(String[] fieldArr)
	{
		this.fieldArr = fieldArr;
	}

	public String[] getShowArr()
	{
		return showArr;
	}

	public void setShowArr(String[] showArr)
	{
		this.showArr = showArr;
	}

	public boolean isHasValue()
	{
		return isHasValue;
	}

	public void setHasValue(boolean isHasValue)
	{
		this.isHasValue = isHasValue;
	}

}
