package com.cee.ljr.domain.common;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProgramSprint {
	private int number;
	private Date startDate;
	private Date endDate;	
	
	public ProgramSprint(int number, Date startDate, Date endDate) {
		super();
		this.number = number;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
