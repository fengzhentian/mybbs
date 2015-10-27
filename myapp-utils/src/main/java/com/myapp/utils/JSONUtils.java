package com.myapp.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtils
{
	/**
	 * @Description: 从json向目标对象中，添加属性值
	 * @param object 目标对象
	 * @param text json串
	 */
	public static void setObjectByJSONString(Object object, String text)
	{
		JSONObject jsonObject = JSONObject.parseObject(text);
		if (jsonObject != null)
		{
			setObjectByJSON(object, jsonObject);
		}
	}

	/**
	 * @Description: 从json对象向目标对象中，添加属性值
	 * @param object 目标对象
	 * @param jsonObject json对象
	 */
	public static void setObjectByJSON(Object object, JSONObject jsonObject)
	{
		List<String> fieldList = ReflectUtils.getFieldList(object);

		for (String field : fieldList)
		{
			if (jsonObject.containsKey(field))
			{
				ReflectUtils.setter(object, field, jsonObject.getString(field),
						String.class);
			}
		}
	}

	/**
	 * @Description: 通过字段获取信息
	 * @param text json串
	 * @param fieldName 字段值
	 * @return
	 */
	public static String getByField(String text, String fieldName)
	{
		JSONObject jsonObject = JSONObject.parseObject(text);
		return getByField(jsonObject, fieldName);
	}

	/**
	 * @Description: 通过字段获取信息
	 * @param json json对象
	 * @param fieldName 字段值
	 * @return
	 */
	public static String getByField(JSONObject jsonObject, String fieldName)
	{
		if (jsonObject != null)
		{
			return jsonObject.getString(fieldName);
		}

		return null;
	}

	/**
	 * @Description: 通过字段获取信息列表
	 * @param list json对象列表
	 * @param field 字段值
	 * @return
	 */
	public static ArrayList<String> getListByField(List<JSONObject> list,
			String field)
	{
		ArrayList<String> guidList = new ArrayList<String>();

		for (JSONObject jsonObject : list)
		{
			guidList.add(jsonObject.getString(field));
		}

		return guidList;
	}

	/**
	 * @Description: 通过字段获取信息列表
	 * @param json json串
	 * @param fieldName 字段值
	 * @return
	 */
	public static ArrayList<String> getListByField(String json, String fieldName)
	{
		ArrayList<String> guidList = new ArrayList<String>();
		JSONArray jsonArray = JSONArray.parseArray(json);

		for (int i = 0; i < jsonArray.size(); i++)
		{
			// 获取每一个JsonObject对象
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			guidList.add(jsonObject.getString(fieldName));
		}

		return guidList;
	}

	public static ArrayList<String> getGuidList(String json)
	{
		return getListByField(json, "guid");
	}

	public static ArrayList<String> getGuidListFromGuids(String json)
	{
		ArrayList<String> guidList = new ArrayList<String>();
		JSONArray jsonArray = JSONArray.parseArray(json);

		for (int i = 0; i < jsonArray.size(); i++)
		{
			guidList.add(jsonArray.getString(i));
		}

		return guidList;
	}

}
