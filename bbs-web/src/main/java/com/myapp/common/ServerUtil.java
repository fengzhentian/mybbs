package com.myapp.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * control用到的类
 */
public class ServerUtil
{
	/**
	 * 输出json
	 * @param response
	 * @param jsonStr
	 * @throws IOException
	 */
	public static void ResponseOutJson(HttpServletResponse response,
			String jsonStr) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(jsonStr);
	}

	// /**
	// * 下载Excel
	// * @param response
	// * @param filename
	// * @param wb
	// * @throws IOException
	// */
	// public static void downloadExcel(HttpServletResponse response,
	// String filename, Workbook wb) throws IOException
	// {
	// // 设置response参数，可以打开下载页面
	// response.reset();
	// response.setContentType("application/vnd.ms-excel;charset=utf-8");
	// response.setHeader("Content-Disposition", "attachment;filename="
	// + new String((filename + ".xls").getBytes("gb2312"),
	// "iso-8859-1"));
	// try
	// {
	// OutputStream os = response.getOutputStream();
	// wb.write(os);
	// os.flush();
	// os.close();
	// }
	// catch (IOException e)
	// {
	// }
	// }
}
