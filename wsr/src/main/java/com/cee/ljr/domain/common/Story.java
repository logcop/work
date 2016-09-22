package com.cee.ljr.domain.common;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cee.ljr.intg.jira.domain.IssueType;

public class Story extends BaseIssue {
	//private static final Logger //log = LoggerFactory.getLogger(Story.class);
	private String name;
	private float hoursWorkedBetween;
	private float totalHoursWorked;
	public static final String MISC_STORY_NAME = "MISC";
	
	public Map<String, Task> keyToTaskMap;
	
	public Story(String name) {
		this.name = name;
		keyToTaskMap = new HashMap<String, Task>();
		hoursWorkedBetween = 0;
	}
	
	@Override
	public float getTotalHoursWorked() {
		if (totalHoursWorked <=0 ) {
			////log.debug("totalHoursWorked <=0");
			for (Task task : keyToTaskMap.values()) {
				totalHoursWorked += task.getTotalHoursWorked();
			}
		}
		return totalHoursWorked;
	}
	
	@Override
	public float getHoursWorkedBetween(Date startDate, Date endDate) {
		//log.debug("getting hours worked between {} and {}", startDate, endDate);
		if (hoursWorkedBetween <=0 ) {
			//log.debug("\thoursWorkedBetween <=0");
			for (Task task : keyToTaskMap.values()) {
				float taskHours = task.getHoursWorkedBetween(startDate, endDate);
				//log.debug("adding task hours: " + taskHours);
				hoursWorkedBetween += taskHours;
			}
		}
		//log.debug("returning hoursWorkedBetween: {}", hoursWorkedBetween);
		return hoursWorkedBetween;
	}
	
	public float getTimeSpentInHours() {
		float timeSpent = 0;
		for (Task task : keyToTaskMap.values()) {
			timeSpent += task.getTimeSpentInHours();
		}
		return timeSpent;
	}
	
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null.");
		}
		String taskKey = task.getKey();
		if (taskKey == null) {
			throw new RuntimeException("Task must contain a key.");
		}
		if (keyToTaskMap.containsKey(taskKey)) {
			throw new RuntimeException("Task " + taskKey + " already exists in Story " + getKey());
		}
		if (IssueType.TASK.equals(task.getType()) || IssueType.BUG.equals(task.getType())) {
			//log.debug("{} adding {} {}.", getKey(), task.getType(), task.getKey());
			keyToTaskMap.put(taskKey, task);
		}
		if (IssueType.SUB_TASK.equals(task.getType())) {
			Task parent = null;
			for (Task task1 : getTasks()) {
				if (task1.getId().equals(task.getParentId())) {
					parent = task1;
					break;
				}
			}
			if (parent != null) {
				//log.debug("{} adding {} {}.", parent.getKey(), task.getType(), task.getKey());
				parent.addSubTask(task);
			}
		}
		
	}
	
	public Collection<Task> getTasks() {
		return keyToTaskMap.values();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
