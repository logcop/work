package com.cee.ljr.domain.common;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cee.ljr.domain.common.util.SprintUtil;
import com.cee.ljr.utils.DateUtil;

/**
 * @author chuck
 *
 */
public class Sprint {
	private static final Logger log = LoggerFactory.getLogger(Sprint.class);
	
	private String name;
	private Integer number;
	private Date startDate;
	private Date endDate;

	public static final String DATE_FORMAT = "MM/dd/yyyy HH:mm";
	
	
	public Sprint(String name, Date startDate, Date endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.number = SprintUtil.getNumberFromName(name);
	}
	

	
	public static Date getSprintDate(String dateString) {
		if (!DateUtil.isDateByFormat(DATE_FORMAT, dateString)) {
			throw new IllegalArgumentException("String \"" + dateString + 
					"\" must be in the following format - '" + DATE_FORMAT + "'");
		}
		
		Date date = DateUtil.toDate(DATE_FORMAT, dateString);
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
