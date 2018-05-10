package com.hust.party.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

public class UtilDate {

	/** "yyyy-MM-dd HH:mm:ss" */
	public static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";
	/** 格式:yyyy-MM-dd */
	public static final String patternDate = "yyyy-MM-dd";
	public static final String patternTime = "HH:mm:ss";

	private static final DateFormat defaultPatternDateFormat = new SimpleDateFormat(defaultPattern);
	private static final DateFormat patternDateDateFormat = new SimpleDateFormat(patternDate);
	private static final DateFormat patternTimeDateFormat = new SimpleDateFormat(patternTime);

	private static final String[] patternList = { defaultPattern, patternDate, patternTime };

	public static Date parse(String str) throws ParseException {

		return DateUtils.parseDate(str, patternList);
	}

	public static Date parse(long l) throws ParseException {
		return new Date(l);
	}

	public static String toDateString(Date date) {
		return defaultPatternDateFormat.format(date);
	}

	public static String toTimeString(Date date) {
		return patternTimeDateFormat.format(date);
	}

	public static String toCalendarString(Date date) {
		return patternDateDateFormat.format(date);
	}

	public static LocalDate nextWhichDay(LocalDate currDay, DayOfWeek dayOfWeek) {
		DayOfWeek curr = currDay.getDayOfWeek();
		int diff = curr.compareTo(dayOfWeek);
		if (diff < 0) {
			return currDay.plusDays(0 - diff);
		}
		return currDay.plusDays(7 - diff);
	}

	// 01. java.util.Date --> java.time.LocalDateTime
	public static LocalDateTime UDateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	// 02. java.util.Date --> java.time.LocalDate
	public static LocalDate UDateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}

	// 03. java.util.Date --> java.time.LocalTime
	public static LocalTime UDateToLocalTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalTime();
	}

	// 04. java.time.LocalDateTime --> java.util.Date
	public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	// 05. java.time.LocalDate --> java.util.Date
	public static Date LocalDateToUdate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		return Date.from(instant);
	}

	// 06. java.time.LocalTime --> java.util.Date
	public static Date LocalTimeToUdate(LocalTime localTime) {
		return LocalDateTimeToUdate(LocalDate.now(),localTime);
	}

	public static Date LocalDateTimeToUdate(LocalDate localDate,LocalTime localTime) {
		LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

}
