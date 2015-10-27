package com.myapp.object;

public class SelectData2
{
	private String value;
	private String text;
	private Boolean selected;

	public SelectData2(String value, String text)
	{
		super();
		this.value = value;
		this.text = text;
	}

	public SelectData2(String value, String text, Boolean selected)
	{
		super();
		this.value = value;
		this.text = text;
		this.selected = selected;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Boolean getSelected()
	{
		return selected;
	}

	public void setSelected(Boolean selected)
	{
		this.selected = selected;
	}

}
