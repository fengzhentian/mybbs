package com.myapp.object;

import java.util.ArrayList;
import java.util.List;

/**
 * 树列表属性类
 */
public class TreeObj
{
	private String name;
	private String url;
	private List<TreeObj> children = new ArrayList<TreeObj>();

	public TreeObj(String name, String url)
	{
		this.name = name;
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<TreeObj> getChildren()
	{
		return children;
	}

	public void addChildren(TreeObj tree)
	{
		this.children.add(tree);
	}

}
