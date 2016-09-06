/**
 * 
 */
package com.cee.wsr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cee.wsr.dao.JiraIssueDao;
import com.cee.wsr.domain.Author;
import com.cee.wsr.domain.Epic;
import com.cee.wsr.domain.IssueType;
import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.domain.JiraIssues;
import com.cee.wsr.domain.Project;
import com.cee.wsr.domain.Sprint;
import com.cee.wsr.domain.StatusReport;
import com.cee.wsr.domain.Story;
import com.cee.wsr.domain.Task;
import com.cee.wsr.mapping.JiraIssueMapper;
import com.cee.wsr.properties.StatusReportProperties;

/**
 * @author chuck
 *
 */
@Service
public class StatusReportService {
	private static final Logger log = LoggerFactory.getLogger(StatusReportService.class);
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	@Autowired 
	StatusReportProperties srProps;

	private JiraIssues jiraIssues;
		
	public StatusReport getStatusReport() {
		StatusReport statusReport = new StatusReport(
				srProps.getReportTitle(), 
				srProps.getReportClassification(), 
				createSprint(), 
				createAuthor(),
				srProps.getReportWeekEndingDate());	
		
		jiraIssues = jiraIssueDao.getAllIssues();
		
		// iterate over tasks, bugs, and sub-tasks 
		// TODO: filter on everything a week prior or less by work log)
		for (JiraIssue taskJiraIssue : jiraIssues.getTasks()) {
			String projectKey = taskJiraIssue.getProjectKey();
			Project project = statusReport.getProject(projectKey);
			
			if (project == null) {
				project = createNewProject(taskJiraIssue);
				statusReport.addProject(project);
			} 
			else {
				addTaskToProject(project, taskJiraIssue);			
			}
		}
		
		return statusReport;
	}
	
	private void addTaskToProject(Project project, JiraIssue taskJiraIssue) {
		String epicKey = taskJiraIssue.getEpicKey();
		//log.debug("addTaskToProject epicKey = '{}'", epicKey);
		Epic epic = project.getEpic(epicKey);
		if (epic == null) {
			epic = createNewEpic(taskJiraIssue);
			project.addEpic(epic);
		} 
		else {					
			addTaskToEpic(epic, taskJiraIssue);
		}
	}
	
	private void addTaskToEpic(Epic epic, JiraIssue taskJiraIssue) {
		String storyKey = jiraIssues.getStoryKey(taskJiraIssue.getKey());
		if (storyKey == null) {
			storyKey = "";
		}
		//log.debug("addTaskToEpic storyKey = '{}'", storyKey);
		Story story = epic.getStory(storyKey);
		if (story == null) {
			story = createNewStory(taskJiraIssue);
			epic.addStory(story);
		}
		else {
			Task task = createNewTask(taskJiraIssue);
			story.addTask(task);
		}
	}
	
	private Project createNewProject(JiraIssue taskJiraIssue) {
		String issueType = taskJiraIssue.getType();
		if(!IssueType.TASK.equals(issueType)) {
			throw new IllegalArgumentException("JiraIssue is of type '" + issueType + ", must be a Task.");
		}
		String projectName = taskJiraIssue.getProjectName();
		String projectKey = taskJiraIssue.getProjectKey();
		Project project = new Project(projectKey, projectName);		
		
		Epic epic = createNewEpic(taskJiraIssue);
		project.addEpic(epic);
		
		return project;
	}
	
	private Epic createNewEpic(JiraIssue taskJiraIssue) {
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
		
		Story story = createNewStory(taskJiraIssue);
		epic.addStory(story);
		
		return epic;
		
	}
	
	private Story createNewStory(JiraIssue taskJiraIssue) {
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
