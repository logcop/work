package com.cee.wsr.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Project {
	private String name;
	private Map<String, Epic> epicMap = new HashMap<String, Epic>();
	
	@Deprecated
	private Map<String, List<JiraIssue>> epicToTaskListMap = new HashMap<String, List<JiraIssue>>(); 
	
	public Project(String name) {
		this.name = name;
	}
	
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
		return epicMap.values();
	}

	@Deprecated
	public Set<String> getEpicsSet() {
		return epicToTaskListMap.keySet();
	}

	@Deprecated
	public List<JiraIssue> getTasksByEpic(String epic) {
		return epicToTaskListMap.get(epic);
	}

	public void addJiraIssue(JiraIssue jiraIssue) {
		Epic epic;
		String epicKey = jiraIssue.getEpic();
		if (epicMap.containsKey(epicKey)) {
			epic = epicMap.get(epicKey);
		} else {
			epic = new Epic(epicKey);
			epicMap.put(epicKey, epic);
		}
		epic.addJiraIssue(jiraIssue);
	}
	
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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
