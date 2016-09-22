package com.cee.ljr.intg.jira.dao;

import java.util.List;

import com.cee.ljr.intg.jira.domain.JiraIssue;


public interface JiraIssueDao {
	
	List<JiraIssue> getAllIssues();
	
	List<JiraIssue> getTasksByDeveloperAndSprints(String developerName, List<String> sprintNames);
}
