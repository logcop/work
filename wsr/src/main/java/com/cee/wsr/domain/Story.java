package com.cee.wsr.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Story extends BaseIssue {
	
	private String name;

	public static final String MISC_STORY_NAME = "MISC";
	
	public Map<String, Task> keyToTaskMap;
	
	public Story(String name) {
		this.name = name;
		keyToTaskMap = new HashMap<String, Task>();
	}
	
	public float getTimeSpentInHours() {
		float timeSpent = 0;
		for (Task task : keyToTaskMap.values()) {
			timeSpent += task.getTimeSpentInHours();
		}
		return timeSpent;
	}
	
	public float getTotalLoggedHours() {
		float loggedHours = 0;
		for (Task task : keyToTaskMap.values()) {
			loggedHours += task.getTotalLoggedHours();
		}
		return loggedHours;
	}
	
	public float getLoggedHoursBetween(Date startDate, Date endDate) {
		float loggedHours = 0;
		for (Task task : keyToTaskMap.values()) {
			loggedHours = task.getLoggedHoursBetween(startDate, endDate);
		}
		return loggedHours;
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
		keyToTaskMap.put(taskKey, task);
		
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
