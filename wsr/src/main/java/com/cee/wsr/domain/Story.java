package com.cee.wsr.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Story {
	
	private String name;

	public static final String MISC_STORY_NAME = "MISC";
	
	public Map<String, Task> tasksMap;
	
	public Story(String name) {
		this.name = name;
		this.tasksMap = new HashMap<String, Task>();
	}
	
	public void addTask(String key, Task task) {
		tasksMap.put(key, task);
	}
	
	public Collection<Task> getTasks() {
		return tasksMap.values();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
