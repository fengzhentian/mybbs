package com.myapp.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @Description: 流格式转换
 */
public class InputStreamUtils
{

	final static int BUFFER_SIZE = 4096;

	/**
	 * 将InputStream转换成String
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamToString(InputStream in) throws Exception
	{

		return InputStreamToString(in, "ISO-8859-1");
	}

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamToString(InputStream in, String encoding)
			throws Exception
	{

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return new String(outStream.toByteArray(), encoding);
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream StringToInputStream(String in) throws Exception
	{

		ByteArrayInputStream is = new ByteArrayInputStream(
				in.getBytes("ISO-8859-1"));
		return is;
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] InputStreamToByte(InputStream in) throws IOException
	{

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream byteToInputStream(byte[] in) throws Exception
	{

		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String byteToString(byte[] in) throws Exception
	{

		InputStream is = byteToInputStream(in);
		return InputStreamToString(is);
	}

	/**
	 * 写文件
	 * @param newStr
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static boolean writeTxtFile(String newStr, String filename)
			throws IOException
	{
		// 先读取原有文件内容，然后进行写入操作
		boolean flag = false;
		String filein = newStr + "\r\n";
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		FileOutputStream fos = null;
		PrintWriter pw = null;
		try
		{
			// 文件路径
			File file = new File(filename);
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			// 保存该文件原有的内容
			while ((temp = br.readLine()) != null)
			{
				buf = buf.append(temp);
				buf = buf.append(System.getProperty("line.separator"));
			}

			buf.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		}
		catch (IOException e1)
		{
			// TODO 自动生成 catch 块
			throw e1;
		}
		finally
		{
			if (pw != null)
			{
				pw.close();
			}
			if (fos != null)
			{
				fos.close();
			}
			if (br != null)
			{
				br.close();
			}
			if (isr != null)
			{
				isr.close();
			}
			if (fis != null)
			{
				fis.close();
			}
		}
		return flag;
	}

}