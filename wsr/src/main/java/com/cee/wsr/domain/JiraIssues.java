package com.cee.wsr.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JiraIssues {
	
	private Map<String, JiraIssue> storiesMap;
	private Map<String, JiraIssue> bugsMap;
	private Map<String, JiraIssue> tasksMap;
	
	public JiraIssues() {
		this.storiesMap = new HashMap<String, JiraIssue>();
		this.bugsMap = new HashMap<String, JiraIssue>();
		this.tasksMap = new HashMap<String, JiraIssue>();
	}

	public void addIssue(JiraIssue jiraIssue) {
		String issueType = jiraIssue.getType();
		String issueKey = jiraIssue.getKey();
		if (IssueType.STORY.equals(issueType)) {
			storiesMap.put(issueKey, jiraIssue);
		} else if (IssueType.TASK.equals(issueType)) {
			tasksMap.put(issueKey, jiraIssue);
		} else if (IssueType.BUG.equals(issueType)) {
			bugsMap.put(issueKey, jiraIssue);
		} else {
			throw new RuntimeException("[" + issueType + "] is an unsupported issue type.");
		}
	}
	
	public Collection<JiraIssue> getStories() {
		return storiesMap.values();
	}

	public Collection<JiraIssue> getBugs() {
		return bugsMap.values();
	}

	public Collection<JiraIssue> getTasks() {
		return tasksMap.values();
	}
}
