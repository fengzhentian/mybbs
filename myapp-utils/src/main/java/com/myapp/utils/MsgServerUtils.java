package com.myapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class MsgServerUtils
{
	// /**
	// * 声明officeserver
	// */
	// private iMsgServer2000 MsgObj;
	//
	// public MsgServerUtils(iMsgServer2000 msgObj)
	// {
	// super();
	// MsgObj = msgObj;
	// }

	/**
	 * 加载根目录文件下的内容
	 * @param fileName 文件名
	 */
	public void loadFileBodyByFileName(String fileName)
	{
		String docPath = "";
		try
		{
			docPath = ClassLoaderUtil.getExtendResource(fileName).getPath();
			docPath = URLDecoder.decode(docPath, "UTF-8");
		}
		catch (Exception e)
		{
		}

		this.loadFileBodyByFilePath(docPath);
	}

	/**
	 * 加载物理路径下的内容
	 * @param filePath 文件路径
	 */
	public void loadFileBodyByFilePath(String filePath)
	{
		File file = new File(filePath);
		long fileLength = file.length();
		byte[] blobData = new byte[(int) fileLength];

		try
		{
			FileInputStream fis = new FileInputStream(file);
			fis.read(blobData);
			fis.close();
		}
		catch (IOException e)
		{
		}

		this.setFileBody(blobData);
	}

	public void loadBookmarks(Object object, String[] bookmarkArr)
	{
		if (object == null)
		{
			return;
		}

		if (ArrayUtils.isNotEmpty(bookmarkArr))
		{
			// 获取对象中的字段列表
			List<String> fieldList = ReflectUtils.getFieldList(object);

			// 遍历模板中的字段
			for (String bookmark : bookmarkArr)
			{
				bookmark = bookmark.toLowerCase();

				// 模板字段是对象字段
				if (fieldList.contains(bookmark))
				{
					// 取出对象中该字段下的值
					Object value = ReflectUtils.getter(object, bookmark);
					if (value == null)
					{
						continue;
					}

					String content = value.toString();

					content = content.replace("[br]", "\r\n").replace(
							"0:00:00", "");
					// 填充标签
					this.setMsgByName(bookmark, content);// 替换回车
				}
			}
		}
	}

	private void setFileBody(byte[] data)
	{
		// this.MsgObj.MsgFileBody(data);
	}

	private void setMsgByName(String fieldName, String fieldValue)
	{
		// this.MsgObj.SetMsgByName(fieldName, fieldValue);
	}

}