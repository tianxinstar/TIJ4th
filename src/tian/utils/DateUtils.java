/**
 * feiniu.com Inc.
 * Copyright (c) 2013-2014 All Rights Reserved.
 */
package tian.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2016年12月1日 上午10:19:52 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public class DateUtils {

	public static final String datetimeFormat = "yyyy-MM-dd HH:mm:ss";

	public static final String dateFormat = "yyyy-MM-dd";

	public static final String timeFormat = "HH:mm:ss";

	public static final String yearFormat = "yyyy";

	public static final String dateChineseFormat = "yyyy年MM月dd日";

	public static final String week = "EEEE";

	public static final String dateShortFormat = "yyyyMMdd";

	public static final String dateTimeLongFormat = "yyyyMMddHHmmss";

	public static final String dateTimeSlashFormat = "yyyy/MM/dd HH:mm:ss";

	public static final String dateSlashFormat = "yyyy/MM/dd";

	public static long ONE_DAY_SECONDS = 86400;

	public static long ONE_DAY_MILLISECONDS = 86400000;

	public static final String dateDayFormatShort = "yyMMdd";

	/**
	 * 转换date -> timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 
	 * Description:获取当前时间 "yyyy-MM-dd HH:mm"
	 *
	 * @return String
	 */
	public static String datetimeFormatNow() {
		return getFormat(datetimeFormat).format(new Date());
	}

	/**
	 * 
	 * Description:获取当前时间 "yyyy/MM/dd/HH/mm/ss"
	 *
	 * @return String
	 */
	public static String datetimeallFormatNow() {
		return getFormat("yyyy/MM/dd/HH/mm/ss").format(new Date());
	}

	/**
	 * 
	 * Description:获取当前时间
	 *
	 * @return java.util.Date
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 
	 * <B>Description:</B> 当天(只有年月日) <br>
	 * <B>Create on:</B> 2015年4月22日 下午2:48:49<br>
	 *
	 * @return
	 */
	public static Date today() {
		Date date = now();
		String s = dateFormat(date);
		try {
			date = toDate(s);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param date
	 *            yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static final Date toShortDate(String date) throws ParseException {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		return getFormat(dateShortFormat).parse(date);
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param date
	 *            yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static final Date toDate(String strDate) throws ParseException {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		return getFormat(dateFormat).parse(strDate);
	}

	/**
	 * 
	 * Description:标准日期格式字符串转换为日期类型
	 *
	 * @param dateTime
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @return
	 * @throws ParseException
	 */
	public static final Date toDateTime(String strDateTime) throws ParseException {
		if (StringUtils.isBlank(strDateTime)) {
			return null;
		}
		return getFormat(datetimeFormat).parse(strDateTime);
	}

	public static final Date toLongDateTime(String longDateTime) throws ParseException {
		if (StringUtils.isBlank(longDateTime)) {
			return null;
		}
		return getFormat(dateTimeLongFormat).parse(longDateTime);
	}

	/**
	 * 通过格式化字符串(如"yyyy-MM-dd HH:mm")获取format对象
	 * 
	 * @param format
	 *            格式化字符串
	 * @return DateFormat format对象
	 */
	public static final DateFormat getFormat(String format) {
		return new SimpleDateFormat(format);
	}

	/**
	 * 
	 * 将日期对象(Date)转换为字符串类型(yyyy-MM-dd HH:mm:ss)输出。
	 * 
	 * @param date
	 *            日期对象
	 * @return String 输出的字符串
	 */
	public static final String datetimeFormat(Date date) {
		if (date == null) {
			return "";
		}
		return getFormat(datetimeFormat).format(date);
	}

	/**
	 * 将日期对象(Date)转换为字符串类型(yyyy-MM-dd)输出。
	 * 
	 * @param date
	 *            日期对象
	 * @return String 输出的字符串
	 */
	public static final String dateFormat(Date date) {
		if (date == null) {
			return "";
		}
		return getFormat(dateFormat).format(date);
	}

	/**
	 * 将日期对象(Date)转换为字符串类型(YYYY/MM/DD HH24:Mi:ss)输出。
	 * 
	 * @param date
	 *            日期对象
	 * @return String 输出的字符串
	 */
	public static final String dateTimeSlashFormat(Date date) {
		if (date == null) {
			return "";
		}
		return getFormat(dateTimeSlashFormat).format(date);
	}

	/**
	 * 将日期对象(Date)转换为字符串类型(YYYY/MM/DD)输出。
	 * 
	 * @param date
	 *            日期对象
	 * @return String 输出的字符串
	 */
	public static final String dateSlashFormat(Date date) {
		if (date == null) {
			return "";
		}
		return getFormat(dateSlashFormat).format(date);
	}

	/**
	 * 根据formate获取时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static final String format(Date date, String formate) {
		if (date == null) {
			return "";
		}
		return getFormat(formate).format(date);
	}

	/**
	 * yyyy-mm-dd 日期格式转换为日期
	 * 
	 * @param strDate
	 * @return
	 */
	public static final Date strToDtSimpleFormat(String strDate) {
		if (strDate == null) {
			return null;
		}

		try {
			return getFormat(dateFormat).parse(strDate);
		} catch (Exception e) {
		}

		return null;
	}

	/**
	 * 获取输入日期的相差日期
	 * 
	 * @param dt
	 * @param idiff
	 * 
	 * @return
	 */
	public static final String getDiffDate(Date dt, int idiff) {
		Calendar c = Calendar.getInstance();

		c.setTime(dt);
		c.add(Calendar.DATE, idiff);
		return dateFormat(c.getTime());
	}

	/**
	 * 获取输入日期月份的相差日期
	 * 
	 * @param dt
	 * @param idiff
	 * @return
	 */
	public static final String getDiffMon(Date dt, int idiff) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.MONTH, idiff);
		return dateFormat(c.getTime());
	}

	/**
	 * 获得指定时间当天起点时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayBegin(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false);

		String dateString = df.format(date);

		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 
	 * <B>Description:</B> 是否是一天的零点<br>
	 * <B>Create on:</B> 2015年5月17日 下午5:17:33<br>
	 *
	 * @param date
	 * @return
	 * @author yuan.qin
	 */
	public static boolean isOneDayZeroPoint(Date date) {
		if (date != null) {
			long time = date.getTime() + ONE_DAY_MILLISECONDS / 3;
			if (time % ONE_DAY_MILLISECONDS == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * yyyy年MM月dd日
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static final String dateChineseFormat(Date date) {
		if (date == null) {
			return "";
		}

		return getFormat(dateChineseFormat).format(date);
	}

	/**
	 * yyyy-MM-dd到 yyyy年MM月dd日 转换
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static final String dtSimpleChineseFormatStr(String date) throws ParseException {
		if (date == null) {
			return "";
		}

		return getFormat(dateChineseFormat).format(toDate(date));
	}

	/**
	 * 返回日期时间(Add by Sunzy)
	 * 
	 * @param stringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final Date string2DateTimeByAutoZero(String stringDate) throws ParseException {
		if (stringDate == null) {
			return null;
		}
		if (stringDate.length() == 11)
			stringDate = stringDate + "00:00:00";
		else if (stringDate.length() == 13)
			stringDate = stringDate + ":00:00";
		else if (stringDate.length() == 16)
			stringDate = stringDate + ":00";
		else if (stringDate.length() == 10)
			stringDate = stringDate + " 00:00:00";

		return getFormat(datetimeFormat).parse(stringDate);
	}

	/**
	 * 返回日期时间(Add by wangjl)(时分秒：23:59:59)
	 *
	 * @param stringDate
	 *            String 型
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final Date string2DateTimeBy23(String stringDate) throws ParseException {
		if (stringDate == null) {
			return null;
		}
		if (stringDate.length() == 11) {
			stringDate = stringDate + "23:59:59";
		} else if (stringDate.length() == 13) {
			stringDate = stringDate + ":59:59";
		} else if (stringDate.length() == 16) {
			stringDate = stringDate + ":59";
		} else if (stringDate.length() == 10) {
			stringDate = stringDate + " 23:59:59";
		}

		return getFormat(datetimeFormat).parse(stringDate);
	}

	/**
	 * 返回日期时间(Add by wangjl)(时分秒：23:59:59)
	 *
	 * @param date
	 *            Date 型
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static final Date dateTimeTo23(Date date) {
		if (date == null) {
			return null;
		}
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);

		return date;
	}

	/**
	 * 返回日期时间(时分秒：00:00:00 000)
	 * 
	 * @param date
	 * @return
	 */
	public static final Date dateTimeToZero(Date date) {
		if (date == null) {
			return null;
		}
		return strToDtSimpleFormat(dateFormat(date));
	}

	/**
	 * 计算日期差值
	 * 
	 * @param String
	 * @param String
	 * @return int(天数)
	 */
	public static final int calculateDecreaseDate(String beforDate, String afterDate) throws ParseException {
		Date date1 = getFormat(dateFormat).parse(beforDate);
		Date date2 = getFormat(dateFormat).parse(afterDate);
		return (int) getDateBetween(date1, date2) / 1000 / 3600 / 24;
	}

	/**
	 * 计算日期差值
	 *
	 * @param one
	 * @param two
	 * @return
	 */
	public static int getDiffDays2(Date one, Date two) {
		Calendar sysDate = new GregorianCalendar();

		sysDate.setTime(one);

		Calendar failDate = new GregorianCalendar();

		failDate.setTime(two);
		return (int) ((sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 计算时间差
	 * 
	 * @param dBefor
	 *            首日
	 * @param dAfter
	 *            尾日
	 * @return 时间差(毫秒)
	 */
	public static final long getDateBetween(Date dBefor, Date dAfter) {
		long lBefor = 0;
		long lAfter = 0;
		long lRtn = 0;

		/** 取得距离 1970年1月1日 00:00:00 GMT 的毫秒数 */
		lBefor = dBefor.getTime();
		lAfter = dAfter.getTime();

		lRtn = lAfter - lBefor;

		return lRtn;
	}

	/**
	 * 返回日期时间(Add by Gonglei)
	 * 
	 * @param stringDate
	 *            (yyyyMMdd)
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final Date shortstring2Date(String stringDate) throws ParseException {
		if (stringDate == null) {
			return null;
		}

		return getFormat(dateShortFormat).parse(stringDate);
	}

	public static final String shortStringToString(String stringDate) throws ParseException {
		if (stringDate == null) {
			return null;
		}
		return shortDate(strToDtSimpleFormat(stringDate));
	}

	/**
	 * 返回短日期格式(yyyyMMdd格式)
	 * 
	 * @param stringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final String shortDate(Date Date) {
		if (Date == null) {
			return null;
		}

		return getFormat(dateShortFormat).format(Date);
	}

	/**
	 * 返回长日期格式(yyyyMMddHHmmss格式)
	 * 
	 * @param stringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final String longDateTime(Date Date) {
		if (Date == null) {
			return null;
		}

		return getFormat(dateTimeLongFormat).format(Date);
	}

	/**
	 * yyyy-MM-dd 日期字符转换为长整形
	 * 
	 * @param stringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final Long string2DateLong(String stringDate) throws ParseException {
		Date d = toDate(stringDate);

		if (d == null) {
			return null;
		}

		return Long.valueOf(d.getTime());
	}

	/**
	 * 日期转换为字符串 HH:mm:ss
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static final String hmsFormat(Date date) {
		if (date == null) {
			return "";
		}

		return getFormat(timeFormat).format(date);
	}

	/**
	 * 获取当前日期的日期差 now= 2005-07-19 diff = 1 -> 2005-07-20 diff = -1 -> 2005-07-18
	 * 
	 * @param diff
	 * 
	 * @return
	 */
	public static final String getDiffDate(int diff) {
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.DATE, diff);
		return dateFormat(c.getTime());
	}

	/**
	 * 获取明天凌晨的时间
	 * <p>
	 * 例子：now=2010-11-11 -> 2010-11-12 00:00:00
	 * </p>
	 * 
	 * @return
	 */
	public static Date getTomorrowAMDate() {
		// 获取明天的时间
		Date tomorrowDate = increaseDate(now(), 1);
		// 时间清零
		return dateTimeToZero(tomorrowDate);
	}

	/**
	 * 获取当前日期 + diff(天)的日期
	 *
	 * @param diff
	 * @return
	 */
	public static final Date getDiffDateTime(int diff) {
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.DATE, diff);
		return c.getTime();
	}

	public static final String getDiffDate(String srcDate, String format, int diff) {
		DateFormat f = new SimpleDateFormat(format);

		try {
			Date source = f.parse(srcDate);
			Calendar c = Calendar.getInstance();

			c.setTime(source);
			c.add(Calendar.DATE, diff);
			return f.format(c.getTime());
		} catch (Exception e) {
			return srcDate;
		}
	}

	/**
	 * add by shengyong 20050808 获取前一天
	 * 
	 * @param StringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static String getBeforeDay(String StringDate) throws ParseException {
		Date tempDate = toDate(StringDate);
		Calendar cad = Calendar.getInstance();

		cad.setTime(tempDate);
		cad.add(Calendar.DATE, -1);
		return DateUtils.dateFormat(cad.getTime());
	}

	/**
	 * add by shengyong 获取前一天 返回 dtSimple 格式字符
	 * 
	 * @param date
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static String getBeforeDay(Date date) throws ParseException {
		Calendar cad = Calendar.getInstance();
		cad.setTime(date);
		cad.add(Calendar.DATE, -1);
		return DateUtils.dateFormat(cad.getTime());
	}

	/**
	 * 获取指定时间的指定前几天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getBeforeDate(Date date, int day) {
		Calendar cad = Calendar.getInstance();
		cad.setTime(date);
		cad.add(Calendar.DATE, -day);
		return DateUtils.dateFormat(cad.getTime());
	}

	/**
	 * 返回指定日期的后n天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getAfterDate(Date date, int day) {
		if (null == date) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.DATE, day); // 日期加1天
		date = c.getTime();
		return date;
	}

	/**
	 * 获取指定时间前n小时
	 * 
	 * @return
	 */
	public static String getBeforeHour(Date date, int hour) {
		Calendar cad = Calendar.getInstance();
		cad.setTime(date);
		cad.set(Calendar.HOUR_OF_DAY, cad.get(Calendar.HOUR_OF_DAY) - hour);
		return DateUtils.datetimeFormat(cad.getTime());
	}

	/**
	 * 获取指定前n分钟
	 */
	public static String getBeforeMinute(Date date, int minute) {
		Calendar cad = Calendar.getInstance();
		cad.setTime(date);
		cad.set(Calendar.MINUTE, cad.get(Calendar.MINUTE) - minute);
		return DateUtils.datetimeFormat(cad.getTime());
	}

	/**
	 * 获取当前日期的日期时间差
	 * 
	 * @param diff
	 * @param hours
	 * 
	 * @return
	 */
	public static final String getDiffDateTime(int diff, int hours) {
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.DATE, diff);
		c.add(Calendar.HOUR, hours);
		return dateFormat(c.getTime());
	}

	/**
	 * 把日期类型的日期换成数字类型 YYYYMMDD类型
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static final Long dateToNumber(Date date) {
		if (date == null) {
			return null;
		}

		Calendar c = Calendar.getInstance();

		c.setTime(date);

		String month;
		String day;

		if ((c.get(Calendar.MONTH) + 1) >= 10) {
			month = "" + (c.get(Calendar.MONTH) + 1);
		} else {
			month = "0" + (c.get(Calendar.MONTH) + 1);
		}

		if (c.get(Calendar.DATE) >= 10) {
			day = "" + c.get(Calendar.DATE);
		} else {
			day = "0" + c.get(Calendar.DATE);
		}

		String number = c.get(Calendar.YEAR) + "" + month + day;

		return new Long(number);
	}

	/**
	 * 获取每月的某天到月末的区间
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static Map<String, String> getLastWeek(String StringDate, int interval) throws ParseException {
		Map<String, String> lastWeek = new HashMap<String, String>();
		Date tempDate = DateUtils.shortstring2Date(StringDate);
		Calendar cad = Calendar.getInstance();

		cad.setTime(tempDate);

		int dayOfMonth = cad.getActualMaximum(Calendar.DAY_OF_MONTH);

		cad.add(Calendar.DATE, (dayOfMonth - 1));
		lastWeek.put("endDate", DateUtils.shortDate(cad.getTime()));
		cad.add(Calendar.DATE, interval);
		lastWeek.put("startDate", DateUtils.shortDate(cad.getTime()));

		return lastWeek;
	}

	/**
	 * 获取下月
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static String getNextMon(String StringDate) throws ParseException {
		Date tempDate = DateUtils.shortstring2Date(StringDate);
		Calendar cad = Calendar.getInstance();

		cad.setTime(tempDate);
		cad.add(Calendar.MONTH, 1);
		return DateUtils.shortDate(cad.getTime());
	}

	/**
	 * add by daizhixia 20050808 获取下一天
	 * 
	 * @param StringDate
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static String getNextDay(String StringDate) throws ParseException {
		Date tempDate = toDate(StringDate);
		Calendar cad = Calendar.getInstance();

		cad.setTime(tempDate);
		cad.add(Calendar.DATE, 1);
		return DateUtils.dateFormat(cad.getTime());
	}

	/**
	 * add by chencg 获取下一天 返回 dtSimple 格式字符
	 * 
	 * @param date
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static String getNextDay(Date date) throws ParseException {
		Calendar cad = Calendar.getInstance();
		cad.setTime(date);
		cad.add(Calendar.DATE, 1);
		return DateUtils.dateFormat(cad.getTime());
	}

	/**
	 * add by chencg 获取下一天 返回 dtshort 格式字符
	 * 
	 * @param StringDate
	 *            "20061106"
	 * 
	 * @return String "2006-11-07"
	 * 
	 * @throws ParseException
	 */
	public static Date getNextDayDtShort(String StringDate) throws ParseException {
		Date tempDate = DateUtils.shortstring2Date(StringDate);
		Calendar cad = Calendar.getInstance();

		cad.setTime(tempDate);
		cad.add(Calendar.DATE, 1);
		return cad.getTime();
	}

	/**
	 * add by daizhixia 20050808 取得相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * @return
	 */
	public static long countDays(String startDate, String endDate) {
		Date tempDate1 = null;
		Date tempDate2 = null;
		long days = 0;

		try {
			tempDate1 = DateUtils.toDate(startDate);

			tempDate2 = DateUtils.toDate(endDate);
			days = (tempDate2.getTime() - tempDate1.getTime()) / (1000 * 60 * 60 * 24);
		} catch (ParseException e) {
			return 0;
		}

		return days;
	}

	/**
	 * add by daizhixia 20050808 取得相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date addMonth(Date date, int month) {
		Date retDate = (Date) date.clone();
		retDate.setMonth(retDate.getMonth() + month);
		return retDate;

	}

	/**
	 * add by wb_jiayw 20101029 取得相差的天数 计算当前时间几分钟之后的时间
	 * 
	 * @param startDate
	 * @param endDate
	 * 
	 * @return
	 */
	public static Date addDays(Date date1, long days) {
		return addSeconds(date1, days * ONE_DAY_SECONDS);
	}

	/**
	 * @param date1
	 * @param secs
	 *
	 * @return
	 */

	public static Date addSeconds(Date date1, long secs) {
		return new Date(date1.getTime() + (secs * 1000));
	}

	/**
	 * 返回日期相差天数，向下取整数
	 * 
	 * @param dateStart
	 *            一般前者小于后者dateEnd
	 * @param dateEnd
	 * 
	 * @return
	 */
	public static int countDays(Date dateStart, Date dateEnd) {
		if ((dateStart == null) || (dateEnd == null)) {
			return -1;
		}

		return (int) ((dateEnd.getTime() - dateStart.getTime()) / (1000 * 60 * 60 * 24));
	}

	/**
	 * 校验start与end相差的天数，是否满足end-start lessEqual than days
	 * 
	 * @param start
	 * @param end
	 * @param days
	 * 
	 * @return
	 */
	public static boolean checkDays(Date start, Date end, int days) {
		int g = countDays(start, end);

		return g <= days;
	}

	/**
	 * alahan add 20050825 获取传入时间相差的日期
	 * 
	 * @param dt
	 *            传入日期，可以为空
	 * @param diff
	 *            需要获取相隔diff天的日期 如果为正则取以后的日期，否则时间往前推
	 * 
	 * @return
	 */
	public static String getDiffStringDate(Date dt, int diff) {
		Calendar ca = Calendar.getInstance();

		if (dt == null) {
			ca.setTime(new Date());
		} else {
			ca.setTime(dt);
		}

		ca.add(Calendar.DATE, diff);
		return dateFormat(ca.getTime());
	}

	/**
	 * 校验输入的时间格式是否合法，但不需要校验时间一定要是8位的
	 * 
	 * @param statTime
	 * 
	 * @return alahan add 20050901
	 */
	public static boolean checkTime(String statTime) {
		if (statTime.length() > 8) {
			return false;
		}

		String[] timeArray = statTime.split(":");

		if (timeArray.length != 3) {
			return false;
		}

		for (int i = 0; i < timeArray.length; i++) {
			String tmpStr = timeArray[i];

			try {
				Integer tmpInt = new Integer(tmpStr);

				if (i == 0) {
					if ((tmpInt.intValue() > 23) || (tmpInt.intValue() < 0)) {
						return false;
					} else {
						continue;
					}
				}

				if ((tmpInt.intValue() > 59) || (tmpInt.intValue() < 0)) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 返回日期时间(Add by Gonglei)
	 * 
	 * @param stringDate
	 *            (yyyyMMdd)
	 * 
	 * @return
	 * 
	 * @throws ParseException
	 */
	public static final String stringToStringDate(String stringDate) {
		if (StringUtils.isBlank(stringDate)) {
			return null;
		}

		if (stringDate.length() != 8) {
			return null;
		}

		return stringDate.substring(0, 4) + stringDate.substring(4, 6) + stringDate.substring(6, 8);
	}

	/**
	 * 将字符串按format格式转换为date类型
	 * 
	 * @param str
	 * @param format
	 * 
	 * @return
	 */
	public static Date string2Date(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 加减天数
	 * 
	 * @param date
	 * @return Date
	 * @author shencb 2006-12 add
	 */
	public static final Date increaseDate(Date aDate, int days) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(aDate);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * 加减天数
	 * 
	 * @param date
	 * @return Date
	 * @author shencb 2006-12 add
	 */
	public static final Date increaseDateByHour(Date aDate, int hour) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(aDate);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 * @return
	 */
	public static final boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

	}

	/**
	 * 判断是否是默认工作日，一般默认工作日是星期一都星期五， 所以，这个函数本质是判断是否是星期一到星期五
	 * 
	 * @param date
	 * @return
	 */
	public static final boolean isDefaultWorkingDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		return !(week == 7 || week == 1);
	}

	/**
	 * 根据年份获取所有的Date
	 * 
	 * @param year
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static final List<Date> getAllDates(int year) {
		Date firstDay = new Date(year - 1900, 0, 1);
		int dayCount = 365;
		if (DateUtils.isLeapYear(year)) {
			dayCount = 366;
		}
		List<Date> dateList = new ArrayList<Date>();
		for (int i = 1; i <= dayCount; i++) {
			dateList.add(DateUtils.increaseDate(firstDay, i - 1));
		}

		return dateList;
	}

	/**
	 * 根据年份月份获取所有的Date
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static final List<Date> getAllDates(int year, int month) {
		Date firstDay = new Date(year - 1900, month, 1);
		int dayCount = 30;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			dayCount = 31;
		} else if (month == 2) {
			if (DateUtils.isLeapYear(year)) {
				dayCount = 29;
			} else {
				dayCount = 28;
			}
		}

		List<Date> dateList = new ArrayList<Date>();
		for (int i = 1; i <= dayCount; i++) {
			dateList.add(DateUtils.increaseDate(firstDay, i - 1));
		}
		return dateList;
	}

	/**
	 * 构造Date
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static final Date newDate(int year, int month, int day) {
		return new Date(year - 1900, month - 1, day);
	}

	/**
	 * 获取星期名，如“星期一”、“星期二”
	 * 
	 * @param date
	 * @return
	 */
	public static final String getWeekDay(Date date) {
		return getFormat(week).format(date);
	}

	@SuppressWarnings("deprecation")
	public static final Date getCurrentYearMonthDay() {
		Date date = new Date();
		return new Date(date.getYear(), date.getMonth(), date.getDate());
	}

	public static Date parseDateNoTime(String sDate, String format) throws ParseException {
		if (StringUtils.isBlank(format)) {
			throw new ParseException("Null format. ", 0);
		}

		DateFormat dateFormat = new SimpleDateFormat(format);

		if ((sDate == null) || (sDate.length() < format.length())) {
			throw new ParseException("length too little", 0);
		}

		return dateFormat.parse(sDate);
	}

	/**
	 * 获取当前时间的Date，以半个小时为单位<br>
	 * 当前时间2007-02-02 22:23 则返回 2007-02-02 22:30 当前时间2007-02-02 22:33 则返回
	 * 2007-02-02 23:00
	 * 
	 * @return
	 */
	public static final Date getNowDateForPageSelectBehind_Date() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.MINUTE) < 30) {
			cal.set(Calendar.MINUTE, 30);
		} else {
			cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
			cal.set(Calendar.MINUTE, 0);
		}
		return cal.getTime();
	}

	/**
	 * 获取当前时间的Date，以半个小时为单位<br>
	 * 当前时间2007-02-02 22:23 则返回 2007-02-02 22:00 当前时间2007-02-02 22:33 则返回
	 * 2007-02-02 22:30
	 * 
	 * @return
	 */
	public static final Date getNowDateForPageSelectAhead_Date() {

		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.MINUTE) < 30) {
			cal.set(Calendar.MINUTE, 0);
		} else {
			cal.set(Calendar.MINUTE, 30);
		}
		return cal.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.himalayas.biz.core.system.DailyFacade#getWeekStartEnd()
	 */
	@SuppressWarnings("deprecation")
	public static Date[] getWeekStartEnd(Date date) {

		if (date == null) {
			date = DateUtils.now();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);

		Date startDate = DateUtils.increaseDate(date, -weekDay + 2);
		Date endDate = DateUtils.increaseDate(date, 8 - weekDay);
		return new Date[] { new Date(startDate.getYear(), startDate.getMonth(), startDate.getDate()),
				new Date(endDate.getYear(), endDate.getMonth(), endDate.getDate(), 23, 59) };

	}

	@SuppressWarnings("deprecation")
	public static void initDateStart(Date date) {
		if (date == null) {
			return;
		}
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public static void initDateEnd(Date date) {
		if (date == null) {
			return;
		}
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
	}

	/**
	 * 把日期2007/06/14转换为20070614
	 * 
	 * @author Yufeng 2007
	 * @method formatDateString
	 * @param date
	 * @return
	 */
	public static String formatDateString(String date) {
		String result = "";
		if (StringUtils.isBlank(date)) {
			return "";
		}
		if (date.length() == 10) {
			result = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
		}
		return result;
	}

	/**
	 * 获得日期是周几
	 * 
	 * @author xiang.zhaox
	 * @param date
	 * @return dayOfWeek
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * add by zhengsb 2008-7-14<br>
	 * 取得当月首日，今天如果是2008-07-14，那这个函数返回的应该是2008-07-01<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @return
	 */
	public static String getCurMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		Date d = new Date();
		cal.setTime(d);
		cal.set(Calendar.DATE, 1);
		return dateFormat(cal.getTime());
	}

	/**
	 * add by peng.lanqp 2009-05-13<br>
	 * 取得某个日期所在月份的第一天，今天如果是2009-05-13，那这个函数返回的应该是2009-05-01<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @return
	 */
	public static String getCurMonthFirstDay(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DATE, 1);
		return dateFormat(cal.getTime());
	}

	/**
	 * <pre>
	 * add by peng.lanqp 2009-05-09
	 * 取得本季度的首日
	 * 比如今天是2009-05-09，那这个函数返回的应该是2009-04-01
	 * </pre>
	 *
	 * @return
	 */
	public static String getCurSeasonFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int season = cal.get(Calendar.MONTH);
		if (season >= 0 && season <= 2) {
			cal.set(Calendar.MONTH, 0);
		}
		if (season >= 3 && season <= 5) {
			cal.set(Calendar.MONTH, 3);
		}
		if (season >= 6 && season <= 8) {
			cal.set(Calendar.MONTH, 6);
		}
		if (season >= 9 && season <= 11) {
			cal.set(Calendar.MONTH, 9);
		}
		cal.set(Calendar.DATE, 1);
		return dateFormat(cal.getTime());
	}

	/**
	 * <pre>
	 * add by peng.lanqp 2009-05-13
	 * 取得某个日期所在季度的第一天，比如今天是2009-05-13，那这个函数返回的应该是2009-04-01
	 * 仅支持YYYY-MM-DD日期格式
	 * </pre>
	 *
	 * @return
	 */
	public static String getCurSeasonFirstDay(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int season = cal.get(Calendar.MONTH);
		if (season >= 0 && season <= 2) {
			cal.set(Calendar.MONTH, 0);
		}
		if (season >= 3 && season <= 5) {
			cal.set(Calendar.MONTH, 3);
		}
		if (season >= 6 && season <= 8) {
			cal.set(Calendar.MONTH, 6);
		}
		if (season >= 9 && season <= 11) {
			cal.set(Calendar.MONTH, 9);
		}
		cal.set(Calendar.DATE, 1);
		return DateUtils.dateFormat(cal.getTime());
	}

	/**
	 * add by zhengsb 2008-7-14<br>
	 * 根据给定的日期，得到下个月首日的日期,今天如果是2008-07-14，那返回的应该是2008-08-01<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextMonthFirstDay(String date) {
		Calendar cal = Calendar.getInstance();
		Date dt = strToDtSimpleFormat(date);
		cal.setTime(dt);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		return dateFormat(cal.getTime());
	}

	/**
	 * add by zhengsb 2008-7-14<br>
	 * 根据给定的日期，得到该周一的日期<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonday(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return dateFormat(cal.getTime());
	}

	/**
	 * add by peng.lanqp 2009-05-10<br>
	 * <br>
	 * 以星期一作为一周的起点<br>
	 * 假如2009-05-10(星期天)，那么返回2009-05-04(星期一)<br>
	 * 根据给定的日期，得到该周一的日期<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getMondayEx(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return dateFormat(cal.getTime());
	}

	/**
	 * <pre>
	 *  add by peng.lanqp 2009-05-28
	 *  判断是否是周末
	 *  仅支持YYYY-MM-DD日期格式
	 * </pre>
	 *
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(String date) {
		Calendar cal = Calendar.getInstance();
		Date d = strToDtSimpleFormat(date);
		cal.setTime(d);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
			return true;
		}

		return false;
	}

	/**
	 * add by zhengsb 2008-7-14<br>
	 * 根据给定的日期，得到该周日的日期<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getSunday(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return dateFormat(cal.getTime());
	}

	/**
	 * add by zhengsb 2008-7-14<br>
	 * 根据给定的日期，得到该周四的日期<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getThursday(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		return dateFormat(cal.getTime());
	}

	/**
	 * add by zhengsb 2008-7-14<br>
	 * 根据给定的日期，得到该上周五的日期<br>
	 * 仅支持YYYY-MM-DD日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getPreviousFriday(String date) {
		Date d = strToDtSimpleFormat(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		return dateFormat(cal.getTime());
	}

	/**
	 * @author ran.taor
	 * @since 2008-08-28
	 * @Description 获取上个月的1号
	 * @return yyyy-mm-dd hh:mm:ss
	 * 
	 */
	public static Date getPreviousMonthFirstDay() {
		// 获取上个月一号的日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		// 获取上一个月，如果是1月份，则获取上一年度的12月份
		if (month == 0) {
			year = year - 1;
			month = 11;
		} else {
			month = month - 1;
		}
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		return calendar.getTime();
	}

	/**
	 * 返回上个月的最后一天
	 * 
	 * @return
	 */
	public static Date getPreviousMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);// 减一个月
		calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return calendar.getTime();
	}

	/**
	 * @author ran.taor
	 * @since 2008-09-22
	 * @description 获取当天在本月的天数
	 */
	public static int getDayNum() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @author ran.taor
	 * @since 2008-09-22
	 * @description 根据输入的在每月中的天数,得到需要的日期
	 */
	public static Date getDateFromDayNum(int dayNum) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, dayNum);
		return calendar.getTime();
	}

	/**
	 * 返回当前年份
	 * 
	 * @return
	 */
	public static String getYearString() {
		return getFormat(yearFormat).format(new Date());
	}

	/**
	 * 返回当前时间是否在输入时间之内，判断有效期
	 *
	 * @param startDate
	 * @param endDate
	 * @author wb-yuank
	 * @return true/false
	 */
	public static boolean checkBetween(Date startDate, Date endDate) {
		if (getDateBetween(startDate, new Date()) >= 0 && getDateBetween(new Date(), endDate) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 按指定的格式返回上个月1号的字符串格式时间
	 *
	 * @param format
	 * @return
	 */
	public static String getPreviouMonthFirstDay(String format) {
		if (format == null || format.equals("") || format.equals(" ")) {
			return getFormat("yyyy-MM-dd hh:mm:ss").format(getPreviousMonthFirstDay());
		}
		return getFormat(format).format(getPreviousMonthFirstDay());
	}

	/**
	 * 按指定格式返回上个月最后一天字符串
	 *
	 * @param format
	 * @return
	 */
	public static String getPreviouMonthLastDay(String format) {
		if (format == null || format.equals("") || format.equals(" ")) {
			return getFormat("yyyy-MM-dd hh:mm:ss").format(getPreviousMonthLastDay());
		}
		return getFormat(format).format(getPreviousMonthLastDay());
	}

	/**
	 * 获取上个月当天00:00:00
	 *
	 * @return date
	 */
	public static Date getTodayBeforeThisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month == 0) {
			year = year - 1;
			month = 11;
		} else {
			month = month - 1;
		}
		calendar.set(year, month, day, 00, 00, 00);
		return calendar.getTime();
	}

	/**
	 * 获取当天23:59：59
	 *
	 * @return Date
	 */
	public static Date getTodayLastSecond() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}

	/**
	 * 返回上个月当天的字符串
	 * 
	 * <pre>
	 * getTodayBeforeThisMonth("yyyy.MM.dd HH:mm:ss") = "2010.11.2 00:00:00";
	 * getTodayBeforeThisMonth("yyyy.MM.dd") = "2010.11.2";
	 * getTodayBeforeThisMonth("HH:mm") = "00:00";
	 * </pre>
	 * 
	 * @param format
	 * @return
	 */
	public static String getTodayBeforeThisMonth(String format) {
		return getFormat(format).format(getTodayBeforeThisMonth());
	}

	/**
	 * 返回获取当天23:59：59的字符串
	 * 
	 * <pre>
	 * getTodayLastSecond("yyyy.MM.dd HH:mm:ss") = "2010.11.2 23:59:59";
	 * getTodayLastSecond("yyyy.MM.dd") = "2010.11.2";
	 * getTodayLastSecond("HH:mm") = "23:59";
	 * </pre>
	 * 
	 * @param format
	 * @return
	 */
	public static String getTodayLastSecond(String format) {
		return getFormat(format).format(getTodayLastSecond());
	}

	/**
	 * 调整日期到凌晨0时0分0秒0毫秒
	 * 
	 * @param date
	 *            日期
	 * @return Date 调整后的时间
	 */
	public static final Date adjustToWee(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.HOUR_OF_DAY, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.SECOND, 0);
		calender.set(Calendar.MILLISECOND, 0);
		return calender.getTime();
	}

	/**
	 * 调整日期的时分秒
	 * 
	 * @param date
	 *            日期
	 * @return Date 调整后的时间
	 */
	public static final Date adjust(Date date, int h, int m, int s) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.HOUR_OF_DAY, h);
		calender.set(Calendar.MINUTE, m);
		calender.set(Calendar.SECOND, s);
		return calender.getTime();
	}

	/**
	 * 时间one与two的时间是否大于monthCount two - one >= monthCount ? true : false
	 * 
	 * @param one
	 * @param two
	 * @param monthCount
	 * @return
	 */
	public static final boolean diffMoreThan(Date one, Date two, int monthCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(two);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if ((month - monthCount) < 0) {
			year = year - 1;
			month = 11 + (month - monthCount) + 1;
		} else {
			month = month - monthCount;
		}
		calendar.set(year, month, day, hour, minute, second);
		Date before = calendar.getTime();
		if (getDateBetween(one, before) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 在当前日期加月份数,再加天数
	 * 
	 * @param date
	 * @param months
	 * @param days
	 * @return
	 */
	public static Date addMonthsAndDays(Date date, int months, int days) {
		Calendar sysDate = new GregorianCalendar();
		sysDate.setTime(date);
		sysDate.add(Calendar.MONTH, months);
		sysDate.add(Calendar.DAY_OF_MONTH, days);
		return sysDate.getTime();
	}

	public static boolean isSameDay(Date date1, Date date2) {
		DateFormat fmt = getFormat(dateFormat);
		return fmt.format(date1).equals(fmt.format(date2));
	}

	public static void main(String[] agrs) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = f.parse("2015-05-17 00:00:00");
		long time = date.getTime();
		System.out.println(time);
		System.out.println(isOneDayZeroPoint(date));

		// System.out.println(""+DateUtils.datetimeFormat(d1.getTime()));
	}

	/** 指定日期多加一天 **/
	public static Date addDateOneDay(Date date) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date); // 设置当前日期
		c.add(Calendar.DATE, 1); // 日期加1天
		// c.add(Calendar.DATE, -1); //日期减1天
		date = c.getTime();
		return date;
	}

	public static boolean isDateTime(String dateTime) {
		try {
			Date dt = toDateTime(dateTime);
			if (dt == null)
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取某段时间内的所有日期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> findDurationDates(String startDate, String endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date dBegin = sdf.parse(startDate.replaceAll("/", "-"));
		Date dEnd = sdf.parse(endDate.replaceAll("/", "-"));
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	/**
	 * 两个时间段差多少秒
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @return
	 */
	public static Long dateTimeDuration(Date dateStart, Date dateEnd) {
		long timeDuration = dateEnd.getTime() - dateStart.getTime();
		// return timeDuration/1000*60; //分钟
		return timeDuration / 1000;// 秒
	}

	/**
	 * 两个时间段超时时间判断
	 * 
	 * @param dateStart
	 * @param dateEnd
	 * @param timeoutMins
	 * @return
	 */
	public static boolean isTimeout(Date dateStart, Date dateEnd, Long timeoutSeconds) {
		Long dateDuration = dateTimeDuration(dateStart, dateEnd);
		boolean isTimeout = dateDuration > timeoutSeconds;
		if (isTimeout)
			return true;
		return false;
	}

	/**
	 * 
	 * @param timeStart
	 *            23:30:00
	 * @param timeEnd
	 *            23:59:59
	 * @return
	 * @throws ParseException
	 */
	public static boolean checkTimeBetween(String timeStart, String timeEnd) throws ParseException {
		String startDateStr = DateUtils.dateFormat(new Date()) + " " + timeStart;
		String endDateStr = DateUtils.dateFormat(new Date()) + " " + timeEnd;
		Date startDate = DateUtils.toDateTime(startDateStr);
		Date endDate = DateUtils.toDateTime(endDateStr);
		return DateUtils.checkBetween(startDate, endDate);
	}

	public static String dateDayFormatNow() {
		return getFormat(dateDayFormatShort).format(new Date());
	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static Date getCurrMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		calendar.setTime(d);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static Date getCurrMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);// 下一个月
		calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return calendar.getTime();
	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getCurrMonthFirstDaySlashFormat() {
		Calendar calendar = Calendar.getInstance();
		Date d = new Date();
		calendar.setTime(d);
		calendar.set(Calendar.DATE, 1);
		return dateSlashFormat(calendar.getTime());
	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static String getCurrMonthLastDaySlashFormat() {
		Calendar calendar = Calendar.getInstance();
		// calendar.add(Calendar.MONTH, 1);// 下一个月
		calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		return dateSlashFormat(calendar.getTime());
	}

	/**
	 * 根据formate获取Long型时间字符串
	 * 
	 * @param date
	 * @return
	 */
	public static final String format(Long dateTmie, String formate) {
		Date date = new Date(dateTmie);
		return getFormat(formate).format(date);
	}

	public static Timestamp getAfterDays(Date date, int days) {
		long time = date.getTime();
		time = time + ONE_DAY_MILLISECONDS * days;
		Timestamp timestamp = new Timestamp(time);
		return timestamp;
	}

	public static String toEnglishDateStr(Date date) {
		return new SimpleDateFormat("d-MMM-yy", Locale.ENGLISH).format(date).toUpperCase();
	}

	/**
	 * 把毫秒转换为时间
	 * 
	 * @return
	 */
	public static Date millisToDate(Long Millis) {
		Calendar cad = Calendar.getInstance();
		cad.setTimeInMillis(Millis);
		return cad.getTime();
	}

	public static String millisToDateString(Long Millis) {
		Date date = millisToDate(Millis);
		return getFormat(datetimeFormat).format(date);

	}

	public static long stringToLong(String dateString) throws ParseException {
		Date date = toDateTime(dateString);
		return date.getTime();
	}

	public static Date formatDateStandard(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(datetimeFormat);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
