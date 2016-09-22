package com.cee.ljr.domain.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends BaseIssue {
	private static final Logger log = LoggerFactory.getLogger(Story.class);
	private String type;
	private String parentId;
	private String summary;	
	private String status;
	private String storyPoints;
	private int timeSpentInSeconds = 0;
	private int hoursWorkedBetween = 0;
	private float totalHoursWorked = 0;
	
	private List<String> developers = new ArrayList<String>();
	private Map<Date, WorkLog> dateToWorkLogMap = new HashMap<Date, WorkLog>();

	private Map<String, Task> keyToSubtaskMap = new HashMap<String, Task>();

	public Task(String summary) {
		this.summary = summary;
	}
	
	@Override
	public float getTotalHoursWorked() {
		if (totalHoursWorked <=0 ) {
			//log.debug("calculating totalHoursWorked.....");
			for (WorkLog workLog : dateToWorkLogMap.values()) {
				float workLogHours = workLog.getHours();
				//log.debug("\tAdding workLogHours: {} to total.", workLogHours);
				totalHoursWorked += workLogHours;
			}
			
			for (Task task : keyToSubtaskMap.values()) {
				float totalSubtaskHoursWorked = task.getTotalHoursWorked();
				totalHoursWorked += totalSubtaskHoursWorked;
				//log.debug("\tAdding totalSubtaskHoursWorked: {} to total.", totalSubtaskHoursWorked );
			}			
		}
		//log.debug("returning totalHoursWorked: {}.", totalHoursWorked );
		return totalHoursWorked;
	}
	
	
	@Override
	public float getHoursWorkedBetween(Date startDate, Date endDate) {
		//log.debug("getting hours worked between {} and {}", startDate, endDate);
		if (hoursWorkedBetween <=0 ) {
			//log.debug("hoursWorkedBetween <=0");
			for (Date date : dateToWorkLogMap.keySet()) {
				//log.debug("Date key = " + date);
				if (date != null && date.after(startDate) && date.before(endDate)) {
					//log.debug("{} is between {} and {}", date, startDate, endDate);
					float workLogHours = dateToWorkLogMap.get(date).getHours();
					//log.debug("Adding workLogHours: " + workLogHours);
					hoursWorkedBetween += workLogHours;
				}
				else {
					//log.debug("{} is NOT between {} and {}", date, startDate, endDate);
				}
			}
			
			for (Task task : keyToSubtaskMap.values()) {
				hoursWorkedBetween += task.getHoursWorkedBetween(startDate, endDate);
			}
		}
		return hoursWorkedBetween;
	}
	
	public void addSubTask(Task subTask) {
		keyToSubtaskMap.put(subTask.getKey(), subTask);
	}
	
	public Collection<Task> getSubTasks() {
		return keyToSubtaskMap.values();
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
		
		////log.debug("adding worklog: " + workLog);
		////log.debug("worklog hours: " + workLog.getHours());
		dateToWorkLogMap.put(workLog.getDate(), workLog);
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
		return getTimeSpentInSeconds() / 3600f;
	}
	
	/**
	 * @return the timeSpentInSeconds
	 */
	public int getTimeSpentInSeconds() {
		return timeSpentInSeconds / 3600;
	}
	
	public void setTimeSpentInSeconds(int timeSpentInSeconds) {
		this.timeSpentInSeconds = timeSpentInSeconds;
	}
	
	public int getLoggedTimeInHours () {
		int timeSpentInSeconds = 0;
		
		for (WorkLog workLog : dateToWorkLogMap.values()) {
			timeSpentInSeconds += workLog.getTimeInSeconds();
		}
		return timeSpentInSeconds;
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

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toStringLight() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
		sb.appendSuper(super.toStringLight());
		sb.append("workLogs", this.dateToWorkLogMap.values());
		return sb.toString();
	}
	
	
}
