package com.cee.ljr.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 	TODO: look into making this comment to look more like a table... <p>
 * 
 *  following from <a href="https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">oracle docs</link>:
 *  <p>  		
 *  Letter	Date or Time Component								Presentation		Examples  <br>
	G		Era designator										Text				AD  <br>
	y		Year												Year				1996; 96  <br>
	Y		Week year											Year				2009; 09   <br>
	M		Month in year										Month				July; Jul; 07  <br>
	w		Week in year										Number				27  <br>
	W		Week in month										Number				2  <br>
	D		Day in year											Number				189  <br>
	d		Day in month										Number				10  <br>
	F		Day of week in month								Number				2  <br>
	E		Day name in week									Text				Tuesday; Tue  <br>
	u		Day number of week (1 = Monday, ..., 7 = Sunday)	Number				1  <br>
	a		Am/pm marker										Text				PM  <br>
	H		Hour in day (0-23)									Number				0  <br>
	k		Hour in day (1-24)									Number				24  <br>
	K		Hour in am/pm (0-11)								Number				0  <br>
	h		Hour in am/pm (1-12)								Number				12  <br>
	m		Minute in hour										Number				30  <br>
	s		Second in minute									Number				55  <br>
	S		Millisecond											Number				978  <br>
	z		Time zone											General time zone	Pacific Standard Time; PST; GMT-08:00  <br>
	Z		Time zone											RFC 822 time zone	-0800  <br>
	X		Time zone											ISO 8601 time zone	-08; -0800; -08:00  <p>
 */

public class DateUtil {
	
	public static final String DATE_STRING_FORMAT = "dd MMM yyyy";	
	public static final String WEEK_ENDING_DATE_FORMAT = "MM/dd/yyyy";
	public static final String JIRA_SPRINT_DATE_FORMAT = "MM/dd/yyyy HH:mm";
	public static final String JIRA_WORKLOG_DATE_FORMAT = "dd/MMM/yy hh:mm aa";
	public static final String TIME_DATE_STRING_FORMAT = "HH:mm:ss:SS  dd MMM yyyy";
	public static final DateFormat DATE_FORMATER = new SimpleDateFormat(DATE_STRING_FORMAT);
	public static final DateFormat TIME_DATE_FORMATER = new SimpleDateFormat(TIME_DATE_STRING_FORMAT);

	private DateUtil() {
	}
	
	public static Date getSprintDate(String dateString) {
		if (!isDateByFormat(JIRA_SPRINT_DATE_FORMAT, dateString)) {
			throw new IllegalArgumentException("String \"" + dateString + 
					"\" must be in the following format - '" + JIRA_SPRINT_DATE_FORMAT + "'");
		}
		Date date = toDate(JIRA_SPRINT_DATE_FORMAT, dateString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 05);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.MINUTE, 30);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);
		
		return cal.getTime();
	}
	
	/**
	 * Creates a week ending date from the given string. The week ending date must be in 
	 * conform to the <code>DateUtil.WEEK_ENDING_DATE_FORMAT</code>. 
	 * @param dateString The end date string
	 * @return a week ending Date.
	 */
	public static Date getWeekEndingDate(String dateString) {
		if (!isDateByFormat(WEEK_ENDING_DATE_FORMAT, dateString)) {
			throw new IllegalArgumentException("String \"" + dateString + 
					"\" must be in the following format - '" + WEEK_ENDING_DATE_FORMAT + "'");
		}
		
		Date date = toDate(WEEK_ENDING_DATE_FORMAT, dateString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.AM_PM, Calendar.PM);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		
		return cal.getTime();
	}
	
	public static Date getWeekStartDate(String weekEndingDateString) {
		if (!isDateByFormat(WEEK_ENDING_DATE_FORMAT, weekEndingDateString)) {
			throw new IllegalArgumentException("String \"" + weekEndingDateString + 
					"\" must be in the following format - '" + WEEK_ENDING_DATE_FORMAT + "'");
		}
		
		Date weekEndingDate = toDate(WEEK_ENDING_DATE_FORMAT, weekEndingDateString);
		Date weekStartDate = DateUtils.addDays(weekEndingDate, -6);
		Calendar cal = Calendar.getInstance();
		cal.setTime(weekStartDate);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 001);
		
		return cal.getTime();
	}
	
	public static Date toDate(String format, String dateString) {
		Date date = null;
		if (isDateByFormat(format, dateString)) {
			try {
				DateFormat dateFormater = new SimpleDateFormat(format);
				
				date = dateFormater.parse(dateString);
			} catch (ParseException pe) {
				throw new IllegalArgumentException("String \"" + dateString + "\" must be in the following format - '"
						+ format + "'", pe);
			}
		}
		return date;
	}
	
	public static boolean isDateByFormat(String format, String dateString) {
		Date date = null;
		try {
			DateFormat dateFormater = new SimpleDateFormat(format);
			
			date = dateFormater.parse(dateString);
		} catch (Exception ex) {
			return false;
		}
		return (date != null);
	}
	
	public static Date toDate(String string) {
		Date date = null;
		try {
			date = DATE_FORMATER.parse(string);
		} catch (ParseException pe) {
			throw new IllegalArgumentException("String must be in the following format - '"
					+ DATE_STRING_FORMAT + "'", pe);
		}
		return date;
	}
	
	public static Date toTimeDate(String string) {
		Date date = null;
		try {
			date = TIME_DATE_FORMATER.parse(string);
		} catch (ParseException pe) {
			throw new IllegalArgumentException("String must be in the following format - '"
					+ TIME_DATE_STRING_FORMAT + "'", pe);
		}
		return date;
	}

	public static String toStringDate(Date date) {
		return DATE_FORMATER.format(date);
	}

	public static String toStringTimeDate(Date date) {
		return TIME_DATE_FORMATER.format(date);
	}
}

