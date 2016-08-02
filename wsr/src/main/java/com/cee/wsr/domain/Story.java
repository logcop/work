package com.cee.wsr.domain;

import java.util.HashMap;
import java.util.Map;

public class Story {
	private String name;

	
	public Map<String, Task> tasksMap;
	
	public Story(String name) {
		this.name = name;
		this.tasksMap = new HashMap<String, Task>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
