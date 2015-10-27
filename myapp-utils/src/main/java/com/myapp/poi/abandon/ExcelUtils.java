package com.myapp.poi.abandon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myapp.object.ExcelObject;
import com.myapp.poi.ExcelHelper;
import com.myapp.utils.DateHelper;
import com.myapp.utils.ReflectUtils;

public class ExcelUtils<T>
{

	public List<Map<String, Object>> get(List<T> list, ExcelObject exObj)
	{
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();

		for (T object : list)
		{
			maplist.add(ExcelHelper.getFieldMap(object, exObj.getFieldArr(),
					exObj.getShowArr()));
		}

		return maplist;
	}

	public static Map<String, Object> getFieldMap(Object obj,
			String[] fieldArr, String[] typeArr)
	{
		Map<String, Object> map = new HashMap<String, Object>();

		for (int index = 0; index < fieldArr.length; index++)
		{
			Object value = ReflectUtils.getter(obj, fieldArr[index]);
			if (value == null)
			{
				map.put(fieldArr[index], "");
				continue;
			}

			String valueStr = value.toString();
			if ("3".equals(typeArr[index]))
			{
				// 将long型转为yyyy-MM-dd
				valueStr = DateHelper.getLongMillisToShort(Long
						.parseLong(valueStr));
			}
			map.put(fieldArr[index], valueStr);
		}

		return map;
	}

}
