package com.cee.wsr.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private static final String DATE_STRING_FORMAT = "dd MMM yyyy";
	private static final String TIME_DATE_STRING_FORMAT = "HH:mm:ss:SS  dd MMM yyyy";
	private static final DateFormat DATE_FORMATER = new SimpleDateFormat(DATE_STRING_FORMAT);
	private static final DateFormat TIME_DATE_FORMATER = new SimpleDateFormat(TIME_DATE_STRING_FORMAT);

	private DateUtil() {
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

