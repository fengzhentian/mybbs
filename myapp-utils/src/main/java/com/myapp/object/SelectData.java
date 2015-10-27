package com.myapp.object;

public class SelectData
{
	private String id;
	private String text;
	private Boolean selected;

	public SelectData(String id, String text)
	{
		super();
		this.id = id;
		this.text = text;
	}

	public SelectData(String id, String text, Boolean selected)
	{
		super();
		this.id = id;
		this.text = text;
		this.selected = selected;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Boolean isSelected()
	{
		return selected;
	}

	public void setSelected(Boolean selected)
	{
		this.selected = selected;
	}
}