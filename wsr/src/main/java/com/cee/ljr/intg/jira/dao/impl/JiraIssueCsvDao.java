package com.cee.ljr.intg.jira.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.intg.jira.dao.JiraIssueDao;
import com.cee.ljr.intg.jira.domain.JiraIssue;
import com.cee.ljr.intg.jira.fileparser.JiraIssuesCsvFileParser;

@Component
@Deprecated
public class JiraIssueCsvDao implements JiraIssueDao{

	@Autowired
	JiraIssuesCsvFileParser jiraIssuesCsvFileParser;
	
	@Override
	public List<JiraIssue> getAllIssues(String csvPaths) {
		return jiraIssuesCsvFileParser.parseAll(csvPaths);		
	}
	
	//@Override
	/*public List<JiraIssue> getTasksByDeveloperAndSprints(String developerName, List<String> sprintNames) {
		return jiraIssuesCsvFileParser.parseTasksByDeveloperAndSprints(csvPaths, developerName, sprintNames);
	}*/
	
	/*public List<JiraIssue> getEpics(List<String> epicKeys) {
		return jiraIssuesCsvFileParser.parseEpics(csvPaths, epicKeys);
	}*/
	
}
