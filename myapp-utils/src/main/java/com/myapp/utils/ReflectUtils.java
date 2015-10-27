package com.myapp.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtils
{
	/**
	 * @param obj ：要操作的对象
	 * @param att ：要操作的属性
	 * @param value ：要设置的属性内容
	 * @param type：要设置的属性类型
	 */
	public static void setter(Object obj, String att, Object value,
			Class<?> type)
	{
		try
		{
			// 得到setter方法
			Method m = obj.getClass().getMethod("set" + initStr(att), type);
			m.invoke(obj, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param obj ：要操作的对象
	 * @param att ：要操作的属性
	 */
	public static Object getter(Object obj, String att)
	{
		try
		{
			// 得到getter方法
			Method m = obj.getClass().getMethod("get" + initStr(att));
			Object result = m.invoke(obj);
			return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String initStr(String oldStr)
	{
		String newStr = oldStr.substring(0, 1).toUpperCase()
				+ oldStr.substring(1).toLowerCase();
		return newStr;
	}

	/**
	 * 获取字段列表
	 * @param obj 对象
	 * @return
	 */
	public static List<String> getFieldList(Object obj)
	{
		try
		{
			List<String> fieldList = new ArrayList<String>();

			Class<?> classType = Class.forName(obj.getClass().getName());

			Field[] fields = classType.getDeclaredFields();

			for (Field field : fields)
			{
				fieldList.add(field.getName().toLowerCase());
			}

			return fieldList;
		}
		catch (ClassNotFoundException e)
		{
			return null;
		}
	}
}
