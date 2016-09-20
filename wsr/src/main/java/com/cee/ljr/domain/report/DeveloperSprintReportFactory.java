package com.cee.ljr.domain.report;

import org.springframework.beans.factory.annotation.Autowired;

import com.cee.ljr.domain.jira.JiraIssuesFactory;

public class DeveloperSprintReportFactory {
	
	@Autowired
	JiraIssuesFactory jiraIssuesFactory;
	
	/*public DeveloperSprintReport getDevelopersSprintReport(List<JiraIssue> jiraIssueList, int sprintNumber) {
		DeveloperSprintReport developerSprintReport = new DeveloperSprintReport();
		
		return developerSprintReport;
	}*/

}
