package com.myapp.utils;

import java.util.ArrayList;
import java.util.List;

public class ListDataUtils
{
	/**
	 * 根据父标识获取子信息列表
	 * @param <T>
	 * @param list 列表
	 * @param parentGuid 父唯一标识
	 * @return
	 */
	public static <T> List<T> getListByParentGuid(List<T> list,
			String parentGuid)
	{
		List<T> childList = new ArrayList<T>();

		if (list != null && list.size() > 0)
		{
			for (T obj : list)
			{
				if (parentGuid.equals(ReflectUtils.getter(obj, "parentguid")))
				{
					childList.add(obj);
				}
			}
		}

		return childList;
	}
}