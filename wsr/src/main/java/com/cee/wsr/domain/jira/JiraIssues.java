package com.cee.wsr.domain.jira;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JiraIssues {
	Logger log = LoggerFactory.getLogger(JiraIssues.class);
	
	private Map<String, String> taskIdToKeyMap;
	private Map<String, String> taskKeyToStoryKeyMap;
	private Map<String, JiraIssue> epicsMap;
	private Map<String, JiraIssue> storiesMap;
	private Map<String, JiraIssue> bugsMap;
	private Map<String, JiraIssue> tasksMap;
	private Map<String, JiraIssue> subTasksMap;
	
	protected JiraIssues() {
		this.taskIdToKeyMap = new HashMap<String, String>();
		this.taskKeyToStoryKeyMap = new HashMap<String, String>();
		this.epicsMap = new HashMap<String, JiraIssue>();
		this.storiesMap = new HashMap<String, JiraIssue>();
		this.bugsMap = new HashMap<String, JiraIssue>();
		this.tasksMap = new HashMap<String, JiraIssue>();
		this.subTasksMap = new HashMap<String, JiraIssue>();
	}


	protected void addIssue(JiraIssue jiraIssue) {
		String issueType = jiraIssue.getValue(JiraAttribute.ISSUE_TYPE);
		String issueKey = jiraIssue.getValue(JiraAttribute.ISSUE_KEY);
		String issueId = jiraIssue.getValue(JiraAttribute.ISSUE_ID);
		//log.debug("Add Issue Type = [{}]", issueType);
		if (IssueType.EPIC.equals(issueType)) {
			//log.debug("Adding Epic jiraIssue:/n {}", jiraIssue);
			epicsMap.put(issueKey, jiraIssue);
		}
		else if (IssueType.STORY.equals(issueType)) {
			//log.debug("Adding Story jiraIssue:/n {}", jiraIssue);
			List<String> linkedTasks = jiraIssue.getValues(JiraAttribute.OUTWARD_ISSUE_LINK_PROBLEM_INCIDENT);
			// map this association so we can look up a story related to a task.
			for (String taskKey : linkedTasks) {
				if(!taskKeyToStoryKeyMap.containsKey(taskKey)) {
					taskKeyToStoryKeyMap.put(taskKey, issueKey);
				}
			}
			storiesMap.put(issueKey, jiraIssue);
		} 
		else if (IssueType.TASK.equals(issueType)) {
			//log.debug("Adding Task jiraIssue:/n {}", jiraIssue);
			tasksMap.put(issueKey, jiraIssue);
			taskIdToKeyMap.put(issueId, issueKey);
		} 
		else if (IssueType.SUB_TASK.equals(issueType)) {
			//log.debug("\nAdding key: {} \nand Sub-Task: {}/n", issueKey, jiraIssue.getSummary());
			subTasksMap.put(issueKey, jiraIssue);
		}
		else if (IssueType.BUG.equals(issueType)) {
			//log.debug("\nAdding key: {} \nand Bug: {}\n", issueKey, jiraIssue.getSummary());
			bugsMap.put(issueKey, jiraIssue);
		} 
		else {
			throw new RuntimeException("unsupported issue type for JiraIssue: " + jiraIssue);
		}
	}
	
	public JiraIssue getStoryByTaskKey(String taskKey) {
		String storyKey = taskKeyToStoryKeyMap.get(taskKey);
		return storiesMap.get(storyKey);
	}
	
	public Collection<JiraIssue> getEpics() {
		return epicsMap.values();
	}
	
	public JiraIssue getEpic(String epicKey) {
		return epicsMap.get(epicKey);
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
	
	public Collection<JiraIssue> getSubTasks() {
		return subTasksMap.values();
	}
	
	public String getTaskIssueKey(String issueId) {
		return taskIdToKeyMap.get(issueId);
	}
	
	public String getStoryKey(String taskKey) {
		return taskKeyToStoryKeyMap.get(taskKey);
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String printStats() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nJiraIssues Stats\n")
		  .append("================\n")
		  .append("Number of Epics: ").append(getEpics().size()).append("\n")
		  .append("Number of Stories: ").append(getStories().size()).append("\n")
		  .append("Number of Tasks: ").append(getTasks().size()).append("\n")
		  .append("Number of Bugs: ").append(getBugs().size()).append("\n");
		  
		return sb.toString();
	
	}
}
