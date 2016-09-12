package com.cee.wsr.domain.jira;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class JiraIssuesFactory {
	
	public JiraIssues getJiraIssues(List<JiraIssue> jiraIssueList) {
		JiraIssues jiraIssues = new JiraIssues();
		
		for (JiraIssue jiraIssue : jiraIssueList) {
			jiraIssues.addIssue(jiraIssue);
		}
		
		return jiraIssues;
	}
}
