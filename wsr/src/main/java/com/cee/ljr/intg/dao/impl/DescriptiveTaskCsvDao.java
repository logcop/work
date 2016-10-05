package com.cee.ljr.intg.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.domain.common.Developer;
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
		
		return mapper.map(taskRecords);	
	}
	
}
