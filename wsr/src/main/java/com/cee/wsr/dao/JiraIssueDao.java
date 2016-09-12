package com.cee.wsr.dao;

import java.util.List;

import com.cee.wsr.domain.jira.JiraIssue;


public interface JiraIssueDao {
	
	List<JiraIssue> getAllIssues();
}
