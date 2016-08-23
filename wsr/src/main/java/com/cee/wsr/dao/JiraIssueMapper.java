package com.cee.wsr.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.cee.wsr.domain.Epic;
import com.cee.wsr.domain.IssueType;
import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.domain.Sprint;
import com.cee.wsr.domain.Story;
import com.cee.wsr.domain.Task;
import com.cee.wsr.domain.WorkLog;
import com.cee.wsr.utils.DateUtil;

public class JiraIssueMapper {

	public static final String WORK_LOG_DATE_FORMAT = "dd/MMM/yy hh:mm aa";
	
	public static Epic createEpic(JiraIssue epicJiraIssue) {		
		if (!IssueType.EPIC.equals(epicJiraIssue.getType())) {
			throw new IllegalArgumentException("epicJiraIssue must be an epic type.");
		}
		Epic epic = new Epic(epicJiraIssue.getEpicName());
		epic.setKey(epicJiraIssue.getKey());
		epic.setStatus(epicJiraIssue.getStatus());
		
		return epic;
	}
	
	public static Story createStory(JiraIssue storyJiraIssue) {
		Story story = null;
		if (!IssueType.STORY.equals(storyJiraIssue.getType())) {
			return story;
		}
		story = new Story(storyJiraIssue.getSummary());
		story.setKey(storyJiraIssue.getKey());
		//story.set
		return story;
	}
	
	public static Task createTask(JiraIssue taskJiraIssue) {
		Task task = null;
		if (!IssueType.TASK.equals(taskJiraIssue.getType())) {
			return task;
		}
		task = new Task(taskJiraIssue.getSummary());
		task.setKey(taskJiraIssue.getKey());
		task.setStoryPoints(taskJiraIssue.getStoryPoints());
		task.setStatus(taskJiraIssue.getStatus());
		String timeSpentString = taskJiraIssue.getTimeSpent();
		if (NumberUtils.isDigits(timeSpentString)) {
			task.setTimeSpentInSeconds(Integer.valueOf(timeSpentString));
		}
		List<Sprint> sprints = createSprints(taskJiraIssue.getSprints());
		task.setSprints(sprints);
		task.setDevelopers(taskJiraIssue.getDevelopers());
		for(String workLogString : taskJiraIssue.getWorkLog()) {
			WorkLog workLog = createWorkLog(workLogString);
			task.addWorkLog(workLog);
		}
		
		return task;
	}
	
	public static WorkLog createWorkLog(String workLogString) {
		String comment = "";
		Date date = null; 
		String owner = "";
		int time = 0;
		
		String[] attributes = workLogString.split(";"); 
		if (attributes.length > 0) {
			comment = attributes[0];
		}
		if (attributes.length > 1) {
			date = DateUtil.toDate(WORK_LOG_DATE_FORMAT, attributes[1]);
		}
		if (attributes.length > 2) {
			owner = attributes[2];
		}
		if (attributes.length > 3) {
			String timeString = attributes[3];
			if(NumberUtils.isDigits(timeString)) {
				time = Integer.valueOf(timeString);
			}
		}		
		return new WorkLog(comment, date, owner, time);
		
	}
	
	public static List<Sprint> createSprints(List<String> sprintNames) {
		List<Sprint> sprints = new ArrayList<Sprint>();
		for (String sprintName : sprintNames) {
			Sprint sprint = new Sprint(sprintName);
			sprints.add(sprint);
		}
		return sprints;
	}
}
