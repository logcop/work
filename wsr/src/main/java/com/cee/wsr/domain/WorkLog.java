package com.cee.wsr.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;


public class WorkLog {
	private String comment;
	private Date date;
	private String owner;
	private int timeInSeconds;
	
	public WorkLog() {
	}
	
	public WorkLog(String comment, Date date, String owner, int timeInSeconds) {
		this.comment = comment;
		this.date = date;
		this.owner = owner;
		this.timeInSeconds = timeInSeconds;
	}
	
	public float getHours() {
		return (Float.valueOf(timeInSeconds) / 3600);
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the timeInSeconds
	 */
	public int getTimeInSeconds() {
		return timeInSeconds;
	}
	/**
	 * @param timeInSeconds the timeInSeconds to set
	 */
	public void setTimeInSeconds(int timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
