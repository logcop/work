package com.cee.wsr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cee.wsr.domain.jira.JiraIssue;
import com.cee.wsr.properties.StatusReportProperties;
import com.cee.wsr.spreadsheet.parser.JiraIssuesCsvParser;

public class JiraIssueCsvDao implements JiraIssueDao{

	@Autowired
	JiraIssuesCsvParser jiraIssuesCsvParser;
	
	@Autowired
	StatusReportProperties statusReportProperties;
	
	@Override
	public List<JiraIssue> getAllIssues() {
		String[] csvPaths = statusReportProperties.getCsvPath();
		return jiraIssuesCsvParser.parseCsv(csvPaths);
		
	}
	
	
}
