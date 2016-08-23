package com.cee.wsr.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task extends BaseIssue {

	private String type;
	private String summary;	
	private String status;
	private String storyPoints;
	private int timeSpentInSeconds;
	
	private List<String> developers = new ArrayList<String>();
	private Map<Date, WorkLog> dateToWorkLogMap = new HashMap<Date, WorkLog>();


	public Task(String summary) {
		this.summary = summary;
	}
	
	/**
	 * @return the workLog
	 */
	public Collection<WorkLog> getWorkLog() {
		return dateToWorkLogMap.values();
	}
	
	public void addWorkLog(WorkLog workLog) {
		if(workLog == null) {
			return;
		}
		if (dateToWorkLogMap.containsValue(workLog)) {
			return;
		}
		
		dateToWorkLogMap.put(workLog.getDate(), workLog);
	}
	
	public float getTotalLoggedHours() {
		float loggedHours = 0;
		for (WorkLog workLog : dateToWorkLogMap.values()) {
			loggedHours += workLog.getHours();
		}
		return loggedHours;
	}
	
	public float getLoggedHoursBetween(Date startDate, Date endDate) {
		float loggedHours = 0;
		for (WorkLog workLog : dateToWorkLogMap.values()) {
			Date logDate = workLog.getDate();
			if (logDate.after(startDate) && logDate.before(endDate)) {
				loggedHours += workLog.getHours();
			}
		}
		return loggedHours;
	}
	
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
	@Override
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	@Override
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public float getTimeSpentInHours() {
		float timeSpentInHours = 0;
		if (timeSpentInSeconds > 0) {
			timeSpentInHours = timeSpentInSeconds / 3600f;
		}
		return timeSpentInHours;
	}
	
	/**
	 * @return the timeSpentInSeconds
	 */
	public int getTimeSpentInSeconds() {
		return timeSpentInSeconds;
	}

	/**
	 * @param timeSpentInSeconds the timeSpentInSeconds to set
	 */
	public void setTimeSpentInSeconds(int timeSpentInSeconds) {
		this.timeSpentInSeconds = timeSpentInSeconds;
	}

	/**
	 * @return the developers
	 */
	public List<String> getDevelopers() {
		return developers;
	}

	/**
	 * @param developers the developers to set
	 */
	public void setDevelopers(List<String> developers) {
		this.developers = developers;
	}

	/**
	 * @return the status
	 */
	@Override
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the storyPoints
	 */
	public String getStoryPoints() {
		return storyPoints;
	}

	/**
	 * @param storyPoints the storyPoints to set
	 */
	public void setStoryPoints(String storyPoints) {
		this.storyPoints = storyPoints;
	}
	
	
	
}
