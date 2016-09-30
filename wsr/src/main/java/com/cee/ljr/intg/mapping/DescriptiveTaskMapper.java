package com.cee.ljr.intg.mapping;

import java.util.ArrayList;
import java.util.List;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.intg.jira.domain.JiraAttribute;

public class DescriptiveTaskMapper {

	
	public List<DescriptiveTask> map(Iterable<CSVRecord> records) {
		List<DescriptiveTask> tasks = new ArrayList<DescriptiveTask>();
		for (CSVRecord record : records) {
			tasks.add(map(record));
		}
		
		return tasks;
	}
	
	
	public DescriptiveTask map(CSVRecord record) {
		DescriptiveTask task = new DescriptiveTask();
		
		task.setCreated(record.getSingleValueFor(JiraAttribute.CREATED));
		task.setDescription(record.getSingleValueFor(JiraAttribute.DESCRIPTION));
		//task.setDevelopers(developers); TODO: inject the commented out values...
		//task.setEpicName(epicName);
		task.setId(record.getSingleValueFor(JiraAttribute.ISSUE_ID));
		task.setIssueType(record.getSingleValueFor(JiraAttribute.ISSUE_TYPE));
		task.setKey(record.getSingleValueFor(JiraAttribute.ISSUE_KEY));
		task.setParentId(record.getSingleValueFor(JiraAttribute.PARENT_ID));
		task.setPriority(record.getSingleValueFor(JiraAttribute.PRIORITY));
		//task.setSprints(sprints);
		task.setStatus(record.getSingleValueFor(JiraAttribute.STATUS));
		task.setStoryPoints(record.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_STORY_POINTS));
		task.setSummary(record.getSingleValueFor(JiraAttribute.SUMMARY));
		task.setTimeSpentInSeconds(Integer.valueOf(record.getSingleValueFor(JiraAttribute.TIME_SPENT)));
		task.setUpdated(record.getSingleValueFor(JiraAttribute.UPDATED));
		
		return task;
	}
}
