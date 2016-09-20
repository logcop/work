package com.cee.ljr.domain.report;

import java.util.List;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.domain.common.Task;

public class DeveloperSprintReport {
	
	private Sprint sprint;
	private Developer developer;
	private List<Task> tasks;
	
	
	/**
	 * @return the sprint
	 */
	public Sprint getSprint() {
		return sprint;
	}
	/**
	 * @param sprint the sprint to set
	 */
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	/**
	 * @return the developer
	 */
	public Developer getDeveloper() {
		return developer;
	}
	/**
	 * @param developer the developer to set
	 */
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}
	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	

}
