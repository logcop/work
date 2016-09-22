package com.cee.ljr.intg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.intg.jira.dao.impl.JiraIssueCsvDao;
import com.cee.ljr.intg.jira.domain.JiraIssue;

@Component
public class DescriptiveTaskCsvDao {
	
	@Autowired
	JiraIssueCsvDao jiraIssueDao;
	
	public List<DescriptiveTask> getAllByDeveloperAndSprints(String developerName, List<String> sprintNames) {
		List<JiraIssue> tasks = jiraIssueDao.getTasksByDeveloperAndSprints(developerName, sprintNames);
		List<JiraIssue> epics = jiraIssueDao.getEpics(epicKeys);
	}
}
