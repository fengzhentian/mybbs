package com.myapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ClassLoaderUtil
{
	public static Class<?> loadClass(String className)
	{
		try
		{
			return getClassLoader().loadClass(className);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException("class not found '" + className + "'", e);
		}
	}

	public static ClassLoader getClassLoader()
	{
		return ClassLoaderUtil.class.getClassLoader();
	}

	public static InputStream getStream(String relativePath)
			throws MalformedURLException, IOException
	{
		if (!relativePath.contains("../"))
		{
			return getClassLoader().getResourceAsStream(relativePath);
		}
		else
		{
			return ClassLoaderUtil.getStreamByExtendResource(relativePath);
		}
	}

	public static InputStream getStream(URL url) throws IOException
	{
		if (url != null)
		{
			return url.openStream();
		}
		else
		{
			return null;
		}
	}

	public static InputStream getStreamByExtendResource(String relativePath)
			throws MalformedURLException, IOException
	{
		return ClassLoaderUtil.getStream(ClassLoaderUtil
				.getExtendResource(relativePath));
	}

	public static Properties getProperties(String resource)
	{
		Properties properties = new Properties();
		try
		{
			properties.load(getStream(resource));
		}
		catch (IOException e)
		{
			throw new RuntimeException("couldn't load properties file '"
					+ resource + "'", e);
		}
		return properties;
	}

	public static String getAbsolutePathOfClassLoaderClassPath()
	{
		return ClassLoaderUtil.getClassLoader().getResource("").toString();
	}

	public static URL getExtendResource(String relativePath)
			throws MalformedURLException
	{
		if (!relativePath.contains("../"))
		{
			return ClassLoaderUtil.getResource(relativePath);
		}
		String classPathAbsolutePath = ClassLoaderUtil
				.getAbsolutePathOfClassLoaderClassPath();
		if (relativePath.substring(0, 1).equals("/"))
		{
			relativePath = relativePath.substring(1);
		}
		String wildcardString = relativePath.substring(0,
				relativePath.lastIndexOf("../") + 3);
		relativePath = relativePath
				.substring(relativePath.lastIndexOf("../") + 3);
		int containSum = ClassLoaderUtil.containSum(wildcardString, "../");
		classPathAbsolutePath = ClassLoaderUtil.cutLastString(
				classPathAbsolutePath, "/", containSum);
		String resourceAbsolutePath = classPathAbsolutePath + relativePath;
		URL resourceAbsoluteURL = new URL(resourceAbsolutePath);
		return resourceAbsoluteURL;
	}

	private static int containSum(String source, String dest)
	{
		int containSum = 0;
		int destLength = dest.length();
		while (source.contains(dest))
		{
			containSum = containSum + 1;
			source = source.substring(destLength);
		}
		return containSum;
	}

	private static String cutLastString(String source, String dest, int num)
	{
		// String cutSource=null;
		// for(int i=0;i< span="" />
		// source=source.substring(0, source.lastIndexOf(dest,
		// source.length()-2)+1); }
		return source;
	}

	public static URL getResource(String resource)
	{
		return ClassLoaderUtil.getClassLoader().getResource(resource);
	}

	public static void main(String[] args) throws MalformedURLException
	{
		// ClassLoaderUtil.getExtendResource("../spring/dao.xml");
		// //ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
		// ClassLoaderUtil.getExtendResource("log4j.properties");
		// System.out.println(ClassLoaderUtil.getClassLoader().getResource("log4j.properties").toString());
	}
}
