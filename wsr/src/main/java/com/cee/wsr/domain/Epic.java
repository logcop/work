package com.cee.wsr.domain;

import java.util.Map;
import java.util.Set;

public class Epic {

	private String name;
	
	private Map<String, Story> storiesMap; 
	
	public Epic(String name) {
		this.name = name;
	}
	
	/*public void addJiraIssue(JiraIssue jiraIssue) {
		Story story = new Story();
		String epicKey = jiraIssue.getEpic();
		if (epicMap.containsKey(epicKey)) {
			epic = epicMap.get(epicKey);
		} else {
			epic = new Epic(epicKey);
			epicMap.put(epicKey, epic);
		}
		epic.addJiraIssue(jiraIssue);
	}*/
	
	public void addJiraIssue(JiraIssue jiraIssue) {
		String issueType = jiraIssue.getType();
		if (IssueType.STORY.equals(issueType)) {
			storiesMap.put(jiraIssue.getKey(), createStory(jiraIssue));
		} 
		else if (IssueType.TASK.equals(issueType)) {
			Set<String> linkedKeys = jiraIssue.getLinkedIssueKeys();
			String storyKey;
			if (linkedKeys != null && linkedKeys.iterator().hasNext()) {
				storyKey = linkedKeys.iterator().next();				
			} else {
				storyKey = Story.MISC_STORY_NAME;
			}
			Story story = storiesMap.get(storyKey);				
			if (story == null) {
				story = new Story(jiraIssue.getSummary());
				storiesMap.put(jiraIssue.getKey(), story);
			}
			story.addTask(jiraIssue.getKey(), createTask(jiraIssue));
		} 
		else if (IssueType.BUG.equals(issueType)) {
			//createBug(jiraIssue)
		}
	}
	
	private Task createTask(JiraIssue jiraIssue) {
		if(jiraIssue == null) {
			throw new IllegalArgumentException("jiraIssue must not be null.");
		} else if(!IssueType.TASK.equals(jiraIssue)) {
			throw new IllegalArgumentException("jiraIssue must be of type Task.");
		}
		Task task = new Task();
		task.setType(jiraIssue.getType());
		task.setSummary(jiraIssue.getSummary());
		task.setDevelopers(jiraIssue.getDevelopers());
		task.setHoursSpent(jiraIssue.getTimeSpent());
		task.setStatus(jiraIssue.getStatus());
		return task;
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
