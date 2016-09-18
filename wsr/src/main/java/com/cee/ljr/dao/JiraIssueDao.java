package com.cee.ljr.dao;

import java.util.List;

import com.cee.ljr.domain.jira.JiraIssue;


public interface JiraIssueDao {
	
	List<JiraIssue> getAllIssues();
}
