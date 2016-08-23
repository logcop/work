package com.cee.wsr.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author chuck
 *
 */
public class Sprint {
	
	private String name;
	private Integer number;
	private Date startDate;
	private Date endDate;

	public Sprint(String name) {
		this.name = name;
	}
	
	public Sprint(int number, Date startDate, Date endDate) {
		super();
		this.number = number;
		this.startDate = startDate;
		this.endDate = endDate;
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
		if (number == null) {
			String sprintNumber = name.substring(name.lastIndexOf("Sprint ") + "Sprint ".length());
			System.out.println("getSprintNumber = " + sprintNumber);
			number = new Integer(sprintNumber);
		}
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
