package com.cee.wsr.domain;

import java.util.Set;

public class Task {

	private String type;
	private String summary;
	private float hoursSpent;
	
	private Set<String> developers;
	
	private String status;

	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the hoursSpent
	 */
	public float getHoursSpent() {
		return hoursSpent;
	}

	/**
	 * @param hoursSpent the hoursSpent to set
	 */
	public void setHoursSpent(float hoursSpent) {
		this.hoursSpent = hoursSpent;
	}

	/**
	 * @return the developers
	 */
	public Set<String> getDevelopers() {
		return developers;
	}

	/**
	 * @param developers the developers to set
	 */
	public void setDevelopers(Set<String> developers) {
		this.developers = developers;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
