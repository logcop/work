package com.cee.wsr.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class Project {
	private static final Logger log = LoggerFactory.getLogger(Project.class);
	private String name;
	private String key;
	private Map<String, Epic> keyToEpicMap = new HashMap<String, Epic>();
	
	@Deprecated
	private Map<String, List<JiraIssue>> epicToTaskListMap = new HashMap<String, List<JiraIssue>>(); 
		
	public Project(String key, String name) {
		this.key = key;
		this.name = name;
	}
	
	public float getTimeSpentInHours() {
		float timeSpent = 0;
		for (Epic epic : keyToEpicMap.values()) {
			timeSpent += epic.getTimeSpentInHours();
		}
		return timeSpent;
	}
	
	public float getTotalLoggedHours() {
		float loggedHours = 0;
		
		for (Epic epic : keyToEpicMap.values()) {
			loggedHours += epic.getTotalLoggedHours();
		}
		
		return loggedHours;
	}
	
	public float getLoggedHoursBetween(Date startDate, Date endDate) {
		float loggedHours = 0;
		for (Epic epic : keyToEpicMap.values()) {
			loggedHours = epic.getLoggedHoursBetween(startDate, endDate);
		}
		return loggedHours;
	}
	
	public Epic getEpic(String epicKey) {
		return keyToEpicMap.get(epicKey);
	}
	
	public void addEpic(Epic epic) {
		if (epic == null) {
			throw new IllegalArgumentException("Task cannot be null.");
		}
		String epicKey = epic.getKey();
		if (epicKey == null) {
			throw new RuntimeException("Epic must contain a key.");
		}
		if (keyToEpicMap.containsKey(epicKey)) {
			throw new RuntimeException("Epic '" + epicKey + "' already exists in Project " + getName());
		}
		if (CollectionUtils.isEmpty(keyToEpicMap)) {
			keyToEpicMap = new HashMap<String, Epic>();
		}
		keyToEpicMap.put(epicKey, epic);
		
	}
	
	/*public boolean addEpic(JiraIssue jiraIssue) {
		if (jiraIssue == null) {
			log.error("Cannot add null epic.");
			return false;
		}
		if (!IssueType.EPIC.equals(jiraIssue.getValue(JiraAttribute.ISSUE_TYPE))) {
			log.error("Given JiraIssue is not an epic type.");
			return false; // not an epic...
		}
		
		String epicKey = jiraIssue.getValue(JiraAttribute.ISSUE_KEY);
		String epicName = jiraIssue.getValue(JiraAttribute.CUSTOM_FIELD_EPIC_NAME);
		Epic epic = keyToEpicMap.get(epicKey);
		if (epic == null) {
			epic = addEpic(epicKey, epicName);
		}
		
		keyToEpicMap.put(epicKey, epic);
		
		return true;
		
	}*/
	
	
	public static String getProjectAbbr(String projectName) {
		String abbr = "";
		if (StringUtils.isNotBlank(projectName)) {
			int abbrPosition = 0;
			String[] nameSegmentedBySpace = StringUtils.split(projectName, ' ');
			if (StringUtils.isAllUpperCase(nameSegmentedBySpace[abbrPosition])) {
				// should be the correct acronym if it's all upper case.
				abbr = nameSegmentedBySpace[abbrPosition];
			}
		}
		return abbr;
	}
	
	public Collection<Epic> getEpics() {
		return keyToEpicMap.values();
	}

	@Deprecated
	public Set<String> getEpicsSet() {
		return epicToTaskListMap.keySet();
	}

	@Deprecated
	public List<JiraIssue> getTasksByEpic(String epic) {
		return epicToTaskListMap.get(epic);
	}

	/*public void addToEpic(JiraIssue jiraIssue) {
		Epic epic;
		String epicKey = jiraIssue.getEpic();
		if (keyToEpicMap.containsKey(epicKey)) {
			epic = keyToEpicMap.get(epicKey);
		} else {
			epic = new Epic(epicKey);
			keyToEpicMap.put(epicKey, epic);
		}
		epic.addJiraIssue(jiraIssue);
	}*/
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
