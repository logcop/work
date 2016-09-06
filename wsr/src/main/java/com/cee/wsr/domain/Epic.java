package com.cee.wsr.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class Epic extends BaseIssue {
	private static final Logger log = LoggerFactory.getLogger(Epic.class);
	private String name;
	
	private Map<String, Story> keyToStoryMap = new HashMap<String, Story>(); 
	
	public Epic(String name) {
		this.name = name;
	}
	
	public float getTimeSpentInHours() {
		float timeSpent = 0;
		for (Story story : keyToStoryMap.values()) {
			timeSpent += story.getTimeSpentInHours();
		}
		return timeSpent;
	}
	
	public float getTotalLoggedHours() {
		float loggedHours = 0;
		for (Story story : keyToStoryMap.values()) {
			loggedHours += story.getTotalLoggedHours();
		}
		return loggedHours;
	}
	
	public float getLoggedHoursBetween(Date startDate, Date endDate) {
		float loggedHours = 0;
		for (Story story : keyToStoryMap.values()) {
			loggedHours = story.getLoggedHoursBetween(startDate, endDate);
		}
		return loggedHours;
	}
	
	public void addStory(Story story) {
		if (story == null) {
			throw new IllegalArgumentException("Story cannot be null.");
		}
		String storyKey = story.getKey();
		log.debug("addStory storyKey= '{}'", storyKey);
		if (storyKey == null) {
			throw new RuntimeException("Story must contain a key.");
		}
		if (keyToStoryMap.containsKey(storyKey)) {
			throw new RuntimeException("Story " + storyKey + " already exists in Epic " + getKey());
		}
		if (CollectionUtils.isEmpty(keyToStoryMap)) {
			keyToStoryMap = new HashMap<String, Story>();
		}
		keyToStoryMap.put(storyKey, story);
		
	}
	
	public Story getStory(String storyKey) {
		return keyToStoryMap.get(storyKey);
	}
	
	public Collection<Story> getStories() {
		return keyToStoryMap.values();
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
	
	/*public void addJiraIssue(JiraIssue jiraIssue) {
		String issueType = jiraIssue.getType();
		if (IssueType.STORY.equals(issueType)) {
			keyToStoryMap.put(jiraIssue.getKey(), createStory(jiraIssue));
		} 
		else if (IssueType.TASK.equals(issueType)) {
			Set<String> linkedKeys = jiraIssue.getLinkedIssueKeys();
			String storyKey;
			if (linkedKeys != null && linkedKeys.iterator().hasNext()) {
				storyKey = linkedKeys.iterator().next();				
			} else {
				storyKey = Story.MISC_STORY_NAME;
			}
			Story story = keyToStoryMap.get(storyKey);				
			if (story == null) {
				story = new Story(jiraIssue.getSummary());
				keyToStoryMap.put(jiraIssue.getKey(), story);
			}
			story.addTask(jiraIssue.getKey(), createTask(jiraIssue));
		} 
		else if (IssueType.BUG.equals(issueType)) {
			//createBug(jiraIssue)
		}
	}*/
	
	/*private Task createTask(JiraIssue jiraIssue) {
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
	}*/
	
	/*private Story createStory(JiraIssue jiraIssue) {
		if(jiraIssue == null) {
			throw new IllegalArgumentException("jiraIssue must not be null.");
		} else if(!IssueType.STORY.equals(jiraIssue)) {
			throw new IllegalArgumentException("jiraIssue must be of type Story.");
		}
		
		return new Story(jiraIssue.getSummary());
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
