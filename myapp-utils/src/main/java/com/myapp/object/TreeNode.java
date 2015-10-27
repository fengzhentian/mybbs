package com.myapp.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TreeNode
{
	private String id;
	private String text;
	private String iconCls;
	private String state;
	private String url;
	private boolean checked;
	private Map<String, String> attributes;
	private List<TreeNode> children = new ArrayList<TreeNode>();

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

	public String getIconCls()
	{
		return iconCls;
	}

	public void setIconCls(String iconCls)
	{
		this.iconCls = iconCls;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public boolean isChecked()
	{
		return checked;
	}

	public void setChecked(boolean checked)
	{
		this.checked = checked;
	}

	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren()
	{
		return children;
	}

	public void setChildren(List<TreeNode> children)
	{
		this.children = children;
	}

}
