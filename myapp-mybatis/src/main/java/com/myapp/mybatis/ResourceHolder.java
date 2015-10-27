package com.myapp.mybatis;

import java.util.HashMap;
import java.util.Map;

public class ResourceHolder
{
	private static ThreadLocal<Map<Object, Object>> resources = new ThreadLocal<Map<Object, Object>>();

	public static void addResource(Object key, Object value)
	{
		if (resources.get() == null)
		{
			resources.set(new HashMap<Object, Object>());
		}
		resources.get().put(key, value);
	}

	public static Object getResource(Object key)
	{

		return resources.get().get(key);
	}

	public static void clear()
	{
		resources.remove();
	}
}