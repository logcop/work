package com.cee.ljr.intg.dao;

import java.util.List;

import com.cee.ljr.domain.jira.JiraIssue;


public interface JiraIssueDao {
	
	List<JiraIssue> getAllIssues();
}
