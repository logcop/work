package com.cee.wsr.mapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cee.wsr.domain.common.Epic;
import com.cee.wsr.domain.common.Sprint;
import com.cee.wsr.domain.common.Story;
import com.cee.wsr.domain.common.Task;
import com.cee.wsr.domain.common.WorkLog;
import com.cee.wsr.domain.jira.IssueType;
import com.cee.wsr.domain.jira.JiraIssue;
import com.cee.wsr.utils.DateUtil;

public class JiraIssueMapper {
	private static final Logger log = LoggerFactory.getLogger(JiraIssueMapper.class);
	
	public static final String WORK_LOG_DATE_FORMAT = DateUtil.JIRA_WORKLOG_DATE_FORMAT;
	private static final int NUM_OF_WORK_LOG_ATTRBS = 4;
	
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
		/*if (!IssueType.TASK.equals(taskJiraIssue.getType())) {
			return task;
		}*/
		task = new Task(taskJiraIssue.getSummary());
		task.setKey(taskJiraIssue.getKey());
		task.setId(taskJiraIssue.getId());
		task.setParentId(taskJiraIssue.getParentId());
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
		//log.debug("returning task: {}", task.getSummary());
		return task;
	}	
	
	public static WorkLog createWorkLog(String workLogString) {
		WorkLog workLog = new WorkLog();
		String [] splitBySemiColonValues = workLogString.split(";");
		// log.debug("split worklog str = " + ToStringBuilder.reflectionToString(delimitedBySemiColonString));
		// start the processing if worklog has required number of potential attributes.
		// log.debug("delimitedBySemiColonString.length = " + delimitedBySemiColonString.length);
		// log.debug("NUM_OF_WORK_LOG_ATTRBS = " + NUM_OF_WORK_LOG_ATTRBS);
		if (splitBySemiColonValues.length >= NUM_OF_WORK_LOG_ATTRBS) {			
			boolean foundDate = false;
			boolean foundOwner = false;
			boolean foundTime = false;
			// workLog attributes are positional in the following order: 
			// comment, date, owner, time.
			String comment = splitBySemiColonValues[0];
			for (int i=1; i < splitBySemiColonValues.length; i++) {
				String nextString = splitBySemiColonValues[i];
				if (!foundDate) {
					// comment may have one or more ';' so have to append to comment until date is found.
					// log.debug("checking if nextString is date: " + nextString);
					if (DateUtil.isDateByFormat(WORK_LOG_DATE_FORMAT, nextString)) {
						// log.debug("nextString is date! " + nextString);
						Date date = DateUtil.toDate(WORK_LOG_DATE_FORMAT, nextString);
						// log.debug("setting comment = " + comment);
						// log.debug("setting date = " + date);
						workLog.setComment(comment);
						workLog.setDate(date);
						foundDate = true;
					}
					else {
						comment += ";" + nextString;
					}
				}
				else if (!foundOwner) {
					if (!NumberUtils.isDigits(nextString)) {
						//assuming here that the owner has some alpha characters
						// log.debug("setting owner = " + nextString);
						workLog.setOwner(nextString);
						foundOwner = true;
					}
				}
				else if (!foundTime) {
					if (NumberUtils.isDigits(nextString)) {
						// log.debug("setting timeInSeconds = " + nextString);
						workLog.setTimeInSeconds(new Integer(nextString));
						foundTime = true;
					}
				}
			}
		}
		return workLog;
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
