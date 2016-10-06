package com.cee.ljr.intg.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.dao.DeveloperDao;
import com.cee.ljr.intg.dao.EpicDao;
import com.cee.ljr.intg.dao.SprintDao;
import com.cee.ljr.intg.jira.domain.JiraAttribute;

@Component
public class DescriptiveTaskMapper {

	@Autowired
	SprintDao sprintDao;
	
	@Autowired
	EpicDao epicDao;
	
	@Autowired
	DeveloperDao developerDao;
	
	public List<DescriptiveTask> map(String filePaths, Iterable<CSVRecord> taskRecords) {		
		List<DescriptiveTask> tasks = new ArrayList<DescriptiveTask>();
		for (CSVRecord record : taskRecords) {			
			tasks.add(map(filePaths, record));
		}
		
		return tasks;
	}
	
		
	public DescriptiveTask map(String filePaths, CSVRecord taskRecord) {
		DescriptiveTask task = new DescriptiveTask();
		
		task.setCreated(taskRecord.getSingleValueFor(JiraAttribute.CREATED));
		task.setDescription(taskRecord.getSingleValueFor(JiraAttribute.DESCRIPTION));
		task.setId(taskRecord.getSingleValueFor(JiraAttribute.ISSUE_ID));
		task.setIssueType(taskRecord.getSingleValueFor(JiraAttribute.ISSUE_TYPE));
		task.setKey(taskRecord.getSingleValueFor(JiraAttribute.ISSUE_KEY));
		task.setParentId(taskRecord.getSingleValueFor(JiraAttribute.PARENT_ID));
		task.setPriority(taskRecord.getSingleValueFor(JiraAttribute.PRIORITY));
		task.setStatus(taskRecord.getSingleValueFor(JiraAttribute.STATUS));
		task.setStoryPoints(taskRecord.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_STORY_POINTS));
		task.setSummary(taskRecord.getSingleValueFor(JiraAttribute.SUMMARY));
		task.setTimeSpentInSeconds(Integer.valueOf(taskRecord.getSingleValueFor(JiraAttribute.TIME_SPENT)));
		task.setUpdated(taskRecord.getSingleValueFor(JiraAttribute.UPDATED));

		task.setDevelopers(getDevelopers(taskRecord));
		task.setEpic(getEpic(filePaths, taskRecord));
		task.setSprints(getSprints(taskRecord));
		
		return task;
	}
	
	private List<Sprint> getSprints(CSVRecord taskRecord) {
		Collection<String> sprintKeys = taskRecord.getAllValuesFor(JiraAttribute.CUSTOM_FIELD_EPIC_LINK);
		
		return sprintDao.getByKeys(sprintKeys);
	}
	
	private Epic getEpic(String filePaths, CSVRecord taskRecord) {
		String epicKey = taskRecord.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_EPIC_LINK);
		
		return epicDao.getByKey(filePaths, epicKey);
	}
	
	private List<Developer> getDevelopers(CSVRecord taskRecord) {
		List<String> developerKeys = taskRecord.getAllValuesFor(JiraAttribute.CUSTOM_FIELD_ASSIGNED_DEVELOPER);
		
		return developerDao.getByKeys(developerKeys);
	}
}
