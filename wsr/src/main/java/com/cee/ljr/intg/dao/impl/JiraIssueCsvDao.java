package com.cee.ljr.intg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.jira.JiraIssue;
import com.cee.ljr.intg.dao.JiraIssueDao;
import com.cee.ljr.intg.fileparser.impl.JiraIssuesCsvFileParser;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class JiraIssueCsvDao implements JiraIssueDao{

	@Autowired
	JiraIssuesCsvFileParser jiraIssuesCsvFileParser;
	
	@Value("${jira.csv.urls}")
	String csvPaths;
	
	@Override
	public List<JiraIssue> getAllIssues() {
		return jiraIssuesCsvFileParser.parseAll(csvPaths);		
	}
	
	@Override
	public List<JiraIssue> getAllByDeveloperAndSprints(String developerName, List<String> sprintNames) {
		return jiraIssuesCsvFileParser.parseAllByDeveloperAndSprints(csvPaths, developerName, sprintNames);
	}
	
	
}
