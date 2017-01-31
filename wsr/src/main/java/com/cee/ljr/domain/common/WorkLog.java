package com.cee.ljr.domain.common;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


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
	
	public double getTimeInHours() {
		return (timeInSeconds / 3600.00);
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
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		sb.append("hours", this.getTimeInHours());
		sb.append("date", this.date);
		return sb.toString();
	}
}
