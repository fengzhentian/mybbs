package com.myapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegexHelper
{
	/**
	 * 清除HTML
	 * @param html
	 * @return
	 */
	public static String clearHtml(String html)
	{
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		return matcher.replaceAll("");
	}

	/**
	 * 判断是否为合法的{+GUID+}（38位，包含大括号）
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean is38Guid(String input)
	{
		// String regex =
		// "\\{[A-Z0-9a-z]{8}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{12}\\}";
		String regex = "\\{[A-Z0-9a-z]{8}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{6}-[A-Z0-9a-z]{10}\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	/**
	 * 判断是否是主键
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isPrimaryKey(String input)
	{
		Pattern pattern = Pattern.compile("\\d{18}");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	/**
	 * 判断是否为数字
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isNum(String input)
	{
		Pattern pattern = Pattern.compile("\\d{1,}");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	/**
	 * 判断是否为合法的人员GUID
	 * @param strSource 人员字符串入参
	 * @return 是否合法
	 */
	public static boolean isUserGuid(String strSource)
	{
		String guidRegPatt = "[A-Z0-9a-z]{8}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{4}-[A-Z0-9a-z]{12}";
		Pattern pattern = Pattern.compile(guidRegPatt);
		Matcher matcher = pattern.matcher(strSource);
		return matcher.matches();
	}

	/**
	 * 判断是否是短日期 yyyy-MM-dd
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isShortDate(String input)
	{
		if (StringUtils.isEmpty(input))
		{
			return false;
		}

		Pattern pattern = Pattern.compile("\\d{1,4}-\\d{1,2}-\\d{1,2}");
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	/**
	 * 判断是否是长日期 yyyy-MM-dd HH:mm:ss
	 * @param input 要判断的字符串
	 * @return
	 */
	public static boolean isLongDate(String input)
	{
		String regex = "\\d{1,4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}
}