package com.cee.ljr.intg.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.jira.fileparser.JiraIssuesCsvFileParser2;
import com.cee.ljr.intg.mapping.DescriptiveTaskMapper;

@Component
public class DescriptiveTaskCsvDao {
	
	@Autowired
	JiraIssuesCsvFileParser2 fileParser;
	
	@Autowired
	DescriptiveTaskMapper mapper;
	
	
	public List<DescriptiveTask> getAllByDeveloperBetweenDates(String csvPaths, Developer developer, Date beginDate, Date endDate) {		
		Iterable<CSVRecord> taskRecords = fileParser.parseTasksByDeveloperBetweenDates(csvPaths, developer.getNameInJira(), beginDate, endDate);
		Iterable<CSVRecord> epicRecords = getEpicsForTasks(csvPaths, taskRecords);
		List<Sprint> sprints = sprintCsvDao.getSprintsBetweenDates(beginDate, endDate);
	
	}
	
	// do i return a map of records
	
	
	private Iterable<CSVRecord> getEpicsForTasks(String csvPaths, Iterable<CSVRecord> taskRecords) {
		Set<String> epicKeys = new HashSet<String>();
		
		for (CSVRecord taskRecord : taskRecords) {
			String epicKey = 
					taskRecord.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_EPIC_LINK);
			epicKeys.add(epicKey);
		}

		Iterable<CSVRecord> epics = fileParser.parseEpicsByIssueKeys(csvPaths, epicKeys);
				
		return epics;
	}
	
	
	
}
