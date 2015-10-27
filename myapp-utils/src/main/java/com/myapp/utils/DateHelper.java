package com.myapp.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateHelper
{
	/**
	 * 获取短日期
	 * @param dateString 日期格式
	 * @return
	 */
	public static String getShortDate(String dateString)
	{
		if (StringUtils.isEmpty(dateString))
		{
			return "";
		}

		try
		{
			return dateToStr(getDate(dateString));
		}
		catch (Exception e)
		{
			return "";
		}
	}

	private static Date getDate(String dateString)
	{
		if (RegexHelper.isShortDate(dateString))
		{
			return strToDate(dateString);
		}
		else if (RegexHelper.isLongDate(dateString))
		{
			return strToDateLong(dateString);
		}

		return null;
	}

	/**
	 * 返回当前日期在特殊格式下的日期字符串
	 * @param formatString 日期格式
	 * @return
	 */
	public static String getFormatDate(String formatString)
	{
		return getFormatDate(formatString, new Date());
	}

	/**
	 * 返回指定日期在特殊格式下的日期字符串
	 * @param formatString 日期格式
	 * @param date 日期对象
	 * @return
	 */
	protected static String getFormatDate(String formatString, Date date)
	{
		if (date == null)
		{
			return "";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		return formatter.format(date);
	}

	protected static Date parseDate(String formatString, String strDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(formatString);
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(strDate, pos);
	}

	/**
	 * 返回年份
	 */
	public static String getCurrentYear()
	{
		return getFormatDate("yyyy");
	}

	/**
	 * 返回月份
	 */
	public static String getCurrentMonth()
	{
		return getFormatDate("MM");
	}

	/**
	 * 返回天
	 */
	public static String getCurrentDay()
	{
		return getFormatDate("dd");
	}

	/**
	 * 返回小时
	 */
	public static String getCurrentHour()
	{
		return getFormatDate("HH");
	}

	/**
	 * 返回当前分钟数
	 */
	public static String getCurrentMinute()
	{
		return getFormatDate("mm");
	}

	/**
	 * 返回当前秒数
	 */
	public static String getCurrentSecond()
	{
		return getFormatDate("ss");
	}

	/**
	 * 返回当前毫秒数
	 */
	public static String getCurrentMillisecond()
	{
		return getFormatDate("SSS");
	}

	/**
	 * 获取字符串类型的当前时间yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate()
	{
		return getFormatDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取字符串类型的当前时间yyyy-MM-dd
	 */
	public static String getCurrentShortDate()
	{
		return getFormatDate("yyyy-MM-dd");
	}

	/**
	 * 获取字符串类型的当前时间HH:mm:ss
	 */
	public static String getTimeShort()
	{
		return getFormatDate("HH:mm:ss");
	}

	/**
	 * 获取当前时间yyyy-MM-dd
	 */
	public static Date getNowDateShort()
	{
		String strDateShort = getCurrentShortDate();
		return strToDate(strDateShort);
	}

	/**
	 * 获取当前日期转换为数字型：yyyyMMddHHmmss(年月日时分秒)
	 * @return long类型的时间串
	 */

	public static Long getLongNowDate()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		return Long.parseLong(dateString);
	}
	/**
	 * 得到某个时间下的长时间类型：
	 * @param date 时间类型
	 * @return 数字型：yyyyMMddHHmmss(年月日时分秒)
	 */
	public static Long getLongDate(Date date)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(date);
		return Long.parseLong(dateString);
	}
	
	/**
	 * 将java.util.Date类型转为字符串类型yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStrLong(Date date)
	{
		return getFormatDate("yyyy-MM-dd HH:mm:ss", date);
	}

	/**
	 * 将java.util.Date类型转为字符串类型yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date)
	{
		return getFormatDate("yyyy-MM-dd", date);
	}

	/**
	 * 根据日期信息，得到中文短日期【5月12日】
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStrShortChinaSimple(Date date)
	{
		return getFormatDate("MM月dd日", date);
	}

	/**
	 * 将字符串类型的日期转为java.util.date
	 * "yyyy-MM-dd HH:mm:ss"
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate)
	{
		if (StringUtils.isEmpty(strDate))
		{
			return null;
		}
		return parseDate("yyyy-MM-dd HH:mm:ss", strDate);
	}

	/**
	 * 将字符串类型的日期转为java.util.date
	 * "yyyy-MM-dd"
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate)
	{
		if (StringUtils.isEmpty(strDate))
		{
			return null;
		}
		return parseDate("yyyy-MM-dd", strDate);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getNow()
	{
		Date currentTime = new Date();
		return currentTime;
	}

	public static String getStringToday()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getHour()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	public static String getTime()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据格式获取时间字符串
	 * 
	 * @param sformat
	 * @return
	 */
	public static String getUserDate(String sformat)
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取毫秒的时间戳 1436513976595  共13位（近期）
	 * 为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数
	 * 
	 * @return
	 */
	public static Long getCurrentTimeMillis()
	{
		return System.currentTimeMillis();
	}

	/**
	 * 得到Unix时间戳，1436513976 共10位（近期）
	 * 是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数，不考虑闰秒
	 * @return
	 */
	public static Long getCurrentTimeSeconds()
	{
		return System.currentTimeMillis() / 1000L;
	}

	/**
	 * 返回特定格式的日期
	 * @param mill 时间戳
	 * @param formatString 日期格式
	 * @return
	 */
	public static String getFormatDate(Long mill, String formatString)
	{
		if (mill == null || mill == 0L)
		{
			return "";
		}

		Date date = new Date(mill);
		String dateString = "";
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(formatString);
			dateString = formatter.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dateString;
	}

	/**
	 * long型时间戳转为时间字符串yyyy-MM-dd HH:mm:ss
	 * @param mill 时间戳
	 * @return
	 */
	public static String getLongMillisToString(Long mill)
	{
		return getFormatDate(mill, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * long型时间戳转为时间字符串yyyy-MM-dd
	 * @param mill 时间戳
	 * @return
	 */
	public static String getLongMillisToShort(Long mill)
	{
		return getFormatDate(mill, "yyyy-MM-dd");
	}

	/**
	 * 将"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"格式的字符串时间
	 * 转成总毫秒数Long
	 * @param dateStr
	 * @return
	 */
	public static Long getDateStringToLongMillis(String dateStr)
	{
		if (StringUtils.isEmpty(dateStr))
		{
			return null;
		}

		Date date = getDate(dateStr);
		if(date != null)
		{
			return date.getTime();
		}

		return null;
	}

	/**
	 * 将"yyyy-MM-dd HH:mm:ss"格式的字符串时间转成总毫秒数Long
	 * @param dateStr
	 * @return
	 */
	public static Long getStringToLongMillis(String dateStr)
	{
		Date date = strToDateLong(dateStr);
		if(date != null)
		{
			return date.getTime();
		}

		return null;
	}

	/**
	 * 将"yyyy-MM-dd"格式的字符串时间转成总毫秒数Long
	 * @param dateStr
	 * @return
	 */
	public static Long getShortToLongMillis(String dateStr)
	{
		Date date = strToDate(dateStr);
		if(date != null)
		{
			return date.getTime();
		}

		return null;
	}

	/**
	 * 获取几天后的时间
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfterDay(Date d, int day)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 返回几个小时后的时间
	 * @param d
	 * @param hour
	 * @return
	 */
	public static Date getDateAfterHour(Date d, int hour)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
		return now.getTime();
	}

	/**
	 * 返回几分钟后的某个时间
	 * @param d
	 * @param minutes
	 * @return
	 */
	public static Date getDateAfterMinute(Date d, int minutes)
	{
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minutes);
		return now.getTime();
	}

	/**
	 * 比较两个日期的大小
	 * true date1比date2前
	 * false date1比date2后
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean dateCompare(Date date1, Date date2)
	{
		boolean dateComPareFlag = true;
		if (date2.compareTo(date1) != 1)
		{
			dateComPareFlag = false;
		}
		return dateComPareFlag;
	}

	/**
	 * 返回两时间之差
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long dateMinus(Date startTime, Date endTime)
	{
		return endTime.getTime() - startTime.getTime();
	}

	// 计算两个string类的时间差
	public static String time(String startTime, String endTime)
			throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
		Date outdate = sdf.parse(endTime);
		Date indate = sdf.parse(startTime);
		long totalhours = (outdate.getTime() - indate.getTime())
				/ (1000 * 60 * 60);// 时
		long totalminutes = (outdate.getTime() - indate.getTime())
				/ (1000 * 60) - totalhours * 60;// 分
		long totalseconds = (outdate.getTime() - indate.getTime()) / (1000)
				- totalminutes * 60;// 秒
		String total_time = totalhours + "时" + totalminutes + "分"
				+ totalseconds + "秒";
		return total_time;
	}

	// 计算考试的剩余时间
	public static String examRemainTime(String startTime, String endTime)
			throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
		Date outdate = sdf.parse(endTime);
		Date indate = sdf.parse(startTime);
		long totalminutes = (outdate.getTime() - indate.getTime())
				/ (1000 * 60);// 分
		long totalseconds = (outdate.getTime() - indate.getTime()) / (1000)
				- totalminutes * 60;// 秒
		String remainTime = totalminutes + "#" + totalseconds;
		return remainTime;
	}
}
