package com.cee.ljr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cee.ljr.domain.jira.JiraIssue;
import com.cee.ljr.properties.WeeklyStatusReportProperties;
import com.cee.ljr.spreadsheet.parser.JiraIssuesCsvParser;

public class JiraIssueCsvDao implements JiraIssueDao{

	@Autowired
	JiraIssuesCsvParser jiraIssuesCsvParser;
	
	@Autowired
	WeeklyStatusReportProperties weeklyStatusReportProperties;
	
	@Override
	public List<JiraIssue> getAllIssues() {
		String[] csvPaths = weeklyStatusReportProperties.getCsvPath();
		return jiraIssuesCsvParser.parseCsv(csvPaths);
		
	}
	
	
}
