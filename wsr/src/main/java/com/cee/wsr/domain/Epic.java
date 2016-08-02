package com.cee.wsr.domain;

import java.util.Map;

public class Epic {

	private String name;
	
	private Map<String, Story> storiesMap; 
	
	public Epic(String name) {
		this.name = name;
	}
	
	public void addJiraIssue(JiraIssue jiraIssue) {
		String issueType = jiraIssue.getType();
		if (IssueType.STORY.equals(issueType)) {
			storiesMap.put(jiraIssue.getKey(), createStory(jiraIssue));
		} else if (IssueType.TASK.equals(issueType)) {
			createTask(JiraIssue jiraIssue) {
				
			}
		}
	}
	
	private Task createTask(JiraIssue jiraIssue) {
		if(jiraIssue == null) {
			throw new IllegalArgumentException("jiraIssue must not be null.");
		} else if(!IssueType.TASK.equals(jiraIssue)) {
			throw new IllegalArgumentException("jiraIssue must be of type Task.");
		}
		Task task = new Task();
		task.
		
	}
	
	private Story createStory(JiraIssue jiraIssue) {
		if(jiraIssue == null) {
			throw new IllegalArgumentException("jiraIssue must not be null.");
		} else if(!IssueType.STORY.equals(jiraIssue)) {
			throw new IllegalArgumentException("jiraIssue must be of type Story.");
		}
		
		return new Story(jiraIssue.getSummary());		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
