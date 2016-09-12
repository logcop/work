package com.cee.wsr.domain.report;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.common.Epic;
import com.cee.wsr.domain.common.Project;
import com.cee.wsr.domain.common.Sprint;
import com.cee.wsr.domain.common.Story;
import com.cee.wsr.domain.common.Task;
import com.cee.wsr.domain.jira.IssueType;
import com.cee.wsr.domain.jira.JiraIssue;
import com.cee.wsr.domain.jira.JiraIssues;
import com.cee.wsr.domain.jira.JiraIssuesFactory;
import com.cee.wsr.mapping.JiraIssueMapper;
import com.cee.wsr.properties.StatusReportProperties;

@Component
public class StatusReportFactory {
	
	@Autowired 
	StatusReportProperties srProps;
	
	@Autowired
	JiraIssuesFactory jiraIssuesFactory;
	
	public StatusReport getWeeklyStatusReport(List<JiraIssue> jiraIssueList, Date weekStartDate, Date weekEndingDate) {
		JiraIssues jiraIssues = jiraIssuesFactory.getJiraIssues(jiraIssueList);
		
		StatusReport statusReport = new StatusReport(
				srProps.getReportTitle(), 
				srProps.getReportClassification(), 
				createSprint(), 
				createAuthor(),
				weekStartDate,
				weekEndingDate);	
		
		// iterate over tasks, bugs, and sub-tasks
		addTasksToStatusReport(jiraIssues, statusReport);	
		addBugsToStatusReport(jiraIssues, statusReport);
		addSubTasksToStatusReport(jiraIssues, statusReport);
		
		return statusReport;
	}
	
	private void addBugsToStatusReport(JiraIssues jiraIssues, StatusReport statusReport) {
		for (JiraIssue taskJiraIssue : jiraIssues.getBugs()) {
			String projectKey = taskJiraIssue.getProjectKey();
			Project project = statusReport.getProject(projectKey);
			
			if (project == null) {
				project = createNewProject(taskJiraIssue, jiraIssues);
				statusReport.addProject(project);
			} 
			else {
				addTaskToProject(taskJiraIssue, project, jiraIssues);			
			}
		}
	}
	
	private void addSubTasksToStatusReport(JiraIssues jiraIssues, StatusReport statusReport) {
		for (JiraIssue taskJiraIssue : jiraIssues.getSubTasks()) {
			String projectKey = taskJiraIssue.getProjectKey();
			Project project = statusReport.getProject(projectKey);
			
			/*if (project == null) {
				project = createNewProject(taskJiraIssue, jiraIssues);
				statusReport.addProject(project);
			} 
			else {*/
				addTaskToProject(taskJiraIssue, project, jiraIssues);			
			//}
		}
	}
	
	private void addTasksToStatusReport(JiraIssues jiraIssues, StatusReport statusReport) {
		for (JiraIssue taskJiraIssue : jiraIssues.getTasks()) {
			String projectKey = taskJiraIssue.getProjectKey();
			Project project = statusReport.getProject(projectKey);
			
			if (project == null) {
				project = createNewProject(taskJiraIssue, jiraIssues);
				statusReport.addProject(project);
			} 
			else {
				addTaskToProject(taskJiraIssue, project, jiraIssues);			
			}
		}
	}
	
	private void addTaskToProject(JiraIssue taskJiraIssue, Project project, JiraIssues jiraIssues) {
		String epicKey = taskJiraIssue.getEpicKey();
		//log.debug("addTaskToProject epicKey = '{}'", epicKey);
		Epic epic = project.getEpic(epicKey);
		if (epic == null) {
			epic = createNewEpic(taskJiraIssue, jiraIssues);
			project.addEpic(epic);
		} 
		else {					
			addTaskToEpic(taskJiraIssue, epic, jiraIssues);
		}
	}
	
	private void addTaskToEpic(JiraIssue taskJiraIssue, Epic epic, JiraIssues jiraIssues) {
		String storyKey = jiraIssues.getStoryKey(taskJiraIssue.getKey());
		if (storyKey == null) {
			storyKey = "";
		}
		//log.debug("addTaskToEpic storyKey = '{}'", storyKey);
		Story story = epic.getStory(storyKey);
		if (story == null) {
			story = createNewStory(taskJiraIssue, jiraIssues);
			epic.addStory(story);
		}
		else {
			Task task = createNewTask(taskJiraIssue);
			story.addTask(task);
		}
	}
	
	private Project createNewProject(JiraIssue taskJiraIssue, JiraIssues jiraIssues) {
		String issueType = taskJiraIssue.getType();
		if(!IssueType.TASK.equals(issueType)) {
			throw new IllegalArgumentException("JiraIssue is of type '" + issueType + ", must be a Task.");
		}
		String projectName = taskJiraIssue.getProjectName();
		String projectKey = taskJiraIssue.getProjectKey();
		Project project = new Project(projectKey, projectName);		
		
		Epic epic = createNewEpic(taskJiraIssue, jiraIssues);
		project.addEpic(epic);
		
		return project;
	}
	
	private Epic createNewEpic(JiraIssue taskJiraIssue, JiraIssues jiraIssues) {
		Epic epic = null;
		
		String epicKey = taskJiraIssue.getEpicKey();
		//log.debug("createNewEpic epicKey = '{}'", epicKey);
		JiraIssue epicJiraIssue = jiraIssues.getEpic(epicKey);
		
		if (epicJiraIssue != null) {
			epic = JiraIssueMapper.createEpic(epicJiraIssue);
		} 
		else {
			epic = new Epic("Miscellaneous");
			epic.setKey("");
		}
		
		Story story = createNewStory(taskJiraIssue, jiraIssues);
		epic.addStory(story);
		
		return epic;
		
	}
	
	private Story createNewStory(JiraIssue taskJiraIssue, JiraIssues jiraIssues) {
		Story story = null;
		
		String taskKey = taskJiraIssue.getKey();
		JiraIssue storyJiraIssue = jiraIssues.getStoryByTaskKey(taskKey);
		
		if (storyJiraIssue != null) {
			story = JiraIssueMapper.createStory(storyJiraIssue);
		} else {
			story = new Story("Misc. Tasks");
			story.setKey("");
		}
		
		Task task = createNewTask(taskJiraIssue);
		//if (IssueType.BUG.equals(task.getType()))
		story.addTask(task);
		
		return story;
	}
	
	private Task createNewTask(JiraIssue taskJiraIssue) {
		return JiraIssueMapper.createTask(taskJiraIssue);		
	}
	
	private Author createAuthor() {
		return new Author(
				srProps.getAuthorName(), 
				srProps.getAuthorType(), 
				srProps.getAuthorTitle());
	}
	
	private Sprint createSprint() {
		return new Sprint(
				srProps.getSprintNumber(), 
				srProps.getSprintStartDate(), 
				srProps.getSprintEndDate());
	}
}
