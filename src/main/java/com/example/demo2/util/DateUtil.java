package com.example.demo2.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具方法类。
 */
public class DateUtil {
	/**
	 * 格式化日期
	 * 
	 * @param format 日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String now(String format) {
		return format(new Date(), format);
	}

	/**
	 * 格式化日期。
	 * 
	 * @param date 需要格式化的日期。
	 * @param format 日期格式。
	 * @return 格式化后的日期字符串。如果date为空则返回空字符串。
	 */
	public static String format(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 格式化日期。
	 * 
	 * @param pattern
	 * @param dateStr
	 * @return A Date parsed from the string.
	 * @throws ParseException
	 */
	public static Date format(String pattern, String dateStr) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(dateStr);
	}

	/**
	 * 把以长整数表示的时间戳转换为Timestamp对象。
	 * 
	 * @param time 时间戳值。
	 * @return 转换后的Timestamp对象。
	 */
	public static Timestamp getTimestamp(long time) {
		return new Timestamp(time);
	}

	/**
	 * 获得当前时间的Timestamp对象。
	 * 
	 * @return 当前时间。
	 */
	public static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取指定日期所在月的天数。
	 * 
	 * @param date 日期。
	 * @return 位于所在月的天数。
	 */
	public static int daysInMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期在所在月位于第几天。
	 * 
	 * @param date 日期。
	 * @return 位于所在月的第天几。
	 */
	public static int dayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取指定日期的年部分值。
	 * 
	 * @param date 日期。
	 * @return 年部分值。
	 */
	public static int yearOf(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取指定日期在所在年位于第几天。
	 * 
	 * @param date 日期。
	 * @return 位于所在年的第几天。
	 */
	public static int dayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定日期在所在周位于第几天。
	 * 
	 * @param date 日期。
	 * @return 位于所在周的第几天。
	 */
	public static int dayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 把日期转换成yyyy-MM-dd HH:mm:ss.fffffffff格式的字符串。
	 * 
	 * @param value 需要转换的日期。
	 * @return 转换后的字符串。
	 */
	public static String dateToStr(Date value) {
		if (value == null)
			return null;
		Timestamp t = new Timestamp(value.getTime());
		return t.toString();
	}

	/**
	 * 把以 yyyy-MM-dd HH:mm:ss[.f...] 格式的字符串转换成时间戳。如果value为null或空串返回null。
	 * 
	 * @param value 需要转换的字符串。
	 * @return 转换后的时间戳。
	 */
	public static Timestamp strToDate(String value) {
		if (StringUtil.isEmpty(value))
			return null;
		return Timestamp.valueOf(value);
	}

	/**
	 * 判断指定的字符串是否为一个有效的标准日期格式。
	 * @return true有效的日期格式，false不是日期格式。
	 */
	public static boolean isDate(String dateStr) {
		char ch;
		int i, len = dateStr.length();

		if (len < 19)
			return false;
		for (i = 0; i < len; i++) {
			ch = dateStr.charAt(i);
			switch (i) {
			case 4:
			case 7:
				if (ch != '-')
					return false;
				break;
			case 10:
				if (ch != ' ')
					return false;
				break;
			case 13:
			case 16:
				if (ch != ':')
					return false;
				break;
			case 19:
				if (ch != '.')
					return false;
				break;
			default:
				if (ch < '0' || ch > '9')
					return false;
			}
		}
		return true;
	}

	/**
	 * 在指定日期上增加年数。
	 * 
	 * @param date 日期。
	 * @param years 增加的年数。
	 * @return 增加年数后的日期。
	 */
	public static Date incYear(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * 在指定日期上增加月数。
	 * 
	 * @param date 日期。
	 * @param months 增加的月数。
	 * @return 增加月数后的日期。
	 */
	public static Date incMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 获取指定日期的小时部分值。
	 * 
	 * @param date 日期。
	 * @return 小时值。
	 */
	public static int hourOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 把指定毫秒数格式化成 HH:mm:ss.fff形式显示的字符串。小时值允许大于24小时显示。
	 * @param milliSecs 毫秒数。
	 * @return 格式化后的文本。
	 */
	public static String format(long milliSecs) {
		long h = milliSecs / 3600000, hm = milliSecs % 3600000;
		long m = hm / 60000, mm = hm % 60000;
		long s = mm / 1000, sm = mm % 1000;
		return StringUtil.concat(Long.toString(h), ":", Long.toString(m), ":", Long.toString(s), ".",
				Long.toString(sm));
	}

	/**
	 * 在指定日期上增加天数。
	 * 
	 * @param date 日期。
	 * @param days 增加的天数。
	 * @return 增加天数后的日期。
	 */
	public static Date incDay(Date date, long days) {
		return new Date(date.getTime() + 86400000 * days);
	}

	/**
	 * 在指定日期上增加秒数。
	 * 
	 * @param date 日期。
	 * @param seconds 增加的秒数。
	 * @return 增加秒数后的日期。
	 */
	public static Date incSecond(Date date, long seconds) {
		return new Date(date.getTime() + 1000 * seconds);
	}

	/**
	 * 计算两个日期相差的天数
	 * @param beginDate 开始日期。
	 * @param endDate 结束日期。
	 * @return 去掉小数后的相差天数。
	 */
	public static int getElapsedDays(Date beginDate, Date endDate) {
		return (int) ((endDate.getTime() - beginDate.getTime()) / 86400000);
	}

	/**
	 * 计算两个日期相差的天数
	 * @param beginDate 开始日期。
	 * @param endDate 结束日期。
	 * @return 去掉小数后的相差天数。
	 */
	public static int getElapsedDays(String beginDate, String endDate) {
		Date begin = formatStrToDate(beginDate, "yyyyMMdd");
		Date end = formatStrToDate(endDate, "yyyyMMdd");
		return (int) ((end.getTime() - begin.getTime()) / 86400000);
	}

	/**
	 * 修正以字符串形式表示的时间值，如果时间不符合格式将修正该值。
	 * @param str 以字符串形式表达的时间值。
	 * @return 修正后的值。
	 */
	public static String fixTime(String str) {
		if (str.indexOf(':') == -1)
			return "00:00:00";
		int b = str.indexOf(' '), e = str.indexOf('.');
		if (b == -1)
			b = 0;
		else
			b++;
		if (e == -1)
			e = str.length();
		return str.substring(b, e);
	}

	/**
	 * 修正以字符串形式表示的日期时间值，如果日期时间不符合格式将修正该值。
	 * @param str 以字符串形式表达的日期时间值。
	 * @param dateOnly 是否只返回日期部分字符串。
	 * @return 修正后的值。
	 */
	public static String fixTimestamp(String str, boolean dateOnly) {
		int pos = str.indexOf(' ');
		String datePart, timePart = null, sec[];
		if (pos == -1) {
			datePart = str;
			if (!dateOnly)
				timePart = "00:00:00";
		} else {
			datePart = str.substring(0, pos);
			if (!dateOnly)
				timePart = str.substring(pos + 1);
		}
		sec = StringUtil.split(datePart, "-");
		if (sec.length == 3) {
			StringBuilder buf = new StringBuilder(dateOnly ? 10 : 30);
			buf.append(sec[0]);
			buf.append('-');
			if (sec[1].length() == 1)
				buf.append('0');
			buf.append(sec[1]);
			buf.append('-');
			if (sec[2].length() == 1)
				buf.append('0');
			buf.append(sec[2]);
			if (!dateOnly) {
				buf.append(' ');
				buf.append(timePart);
			}
			return buf.toString();
		} else
			return str;
	}
	
	/**
	 * 把格式化日期字符串转出日期。
	 * 
	 * @param date 需要转成日期的字符串。
	 * @param format 日期格式。
	 * @return 格式化后的日期。如果date为空则返回null。
	 */
	public static Date formatStrToDate(String date, String format) {
		if (StringUtil.isEmpty(date))
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 计算两个日期相差天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long daysOfTwo(Date startDate, Date endDate){
        long days=(endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24);
        return days;
    }
	
	/**
	 * 计算两个日期相差天数
	 * @param startDate
	 * @param endDate
	 * @param formate
	 * @return
	 * @throws ParseException
	 */
	public static long daysOfTwo(String startDate, String endDate, String formate) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(formate);
		Date fDate=sdf.parse(startDate);
        Date oDate=sdf.parse(endDate);
		return daysOfTwo(fDate,oDate);
	}
	
	/**
	 * 计算当前日期加天数后的日期
	 * @param date 当前日期
	 * @param days 天数（可负）
	 * @param formate 日期格式(默认yyyyMMdd)
	 * @return
	 * @throws ParseException 
	 */
	public static String addDays(String date, int days, String formate) throws ParseException{
		if (StringUtil.isEmpty(date)) {
			return "";
		}
		if (StringUtil.isEmpty(formate)) {
			formate = "yyyyMMdd";
		}
		SimpleDateFormat f = new SimpleDateFormat(formate);  
        Date day = f.parse(date);
        Calendar c = Calendar.getInstance();  
        c.setTime(day);  
        c.add(Calendar.DAY_OF_MONTH, days);
        Date aDay = c.getTime();  
        return f.format(aDay);
	}

	/**
	 * 计算当前日期加天数后的日期
	 * @param date 当前日期
	 * @param months 天数（可负）
	 * @param formate 日期格式(默认yyyyMMdd)
	 * @return
	 * @throws ParseException
	 */
	public static String addMonths(String date, int months, String formate) throws ParseException{
		if (StringUtil.isEmpty(date)) {
			return "";
		}
		if (StringUtil.isEmpty(formate)) {
			formate = "yyyyMMdd";
		}
		SimpleDateFormat f = new SimpleDateFormat(formate);
		Date day = f.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.MONTH, months);
		Date aDay = c.getTime();
		return f.format(aDay);
	}

	/**
	 * 计算当前日期加年数后的日期
	 * @param date 当前日期（默认系统当前时间）
	 * @param years 年数（可负）
	 * @param formate 返回日期格式(默认yyyyMMdd)
	 * @return
	 * @throws ParseException 
	 */
	public static String addYears(Date date, int years, String formate) throws ParseException{
		if (date == null) {
			date = new Date();
		}
		if (StringUtil.isEmpty(formate)) {
			formate = "yyyyMMdd";
		}
		SimpleDateFormat f = new SimpleDateFormat(formate);  
		Calendar c = Calendar.getInstance();  
		c.setTime(date);  
		c.add(Calendar.YEAR, years);
		Date aDay = c.getTime();  
		return f.format(aDay);
	}
	
	/**
	 * 计算当前日期加年数后的日期
	 * @param date 当前日期（yyyyMMdd,默认值系统当前时间）
	 * @param years 年数（可负）
	 * @param formate 返回日期格式(默认yyyyMMdd)
	 * @return
	 * @throws ParseException 
	 */
	public static String addYears(String date, int years, String formate) throws ParseException {
		if (StringUtil.isEmpty(formate)) {
			formate = "yyyyMMdd";
		}
		SimpleDateFormat f = new SimpleDateFormat(formate); 
		Date d = new Date();
		if (StringUtil.isEmpty(date)) {
			d = f.parse(date);
		}
		return addYears(d,years,formate);
	}

	public static String lastSeason(String date) {
		if(date == null || date.trim().equals("")) { date = now("yyyyMMdd"); }
		String year = date.substring(0,4);
		String monthDay = date.substring(4, 8);
		if(Integer.valueOf(monthDay) > 930) { return year + "0930"; }
		else if(Integer.valueOf(monthDay) > 630) { return year + "0630"; }
		else if(Integer.valueOf(monthDay) > 331) { return year + "0331"; }
		else {
			year = Integer.toString(Integer.parseInt(year) - 1);
			return year + "1231";
		}
	}
}
