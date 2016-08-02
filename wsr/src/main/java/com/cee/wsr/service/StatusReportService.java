/**
 * 
 */
package com.cee.wsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cee.wsr.dao.JiraIssueDao;
import com.cee.wsr.domain.Author;
import com.cee.wsr.domain.JiraIssue;
import com.cee.wsr.domain.JiraIssues;
import com.cee.wsr.domain.Sprint;
import com.cee.wsr.domain.StatusReport;
import com.cee.wsr.properties.StatusReportProperties;

/**
 * @author chuck
 *
 */
@Service
public class StatusReportService {

	@Autowired
	JiraIssueDao jiraIssueDao;
	@Autowired 
	StatusReportProperties srProps;
	
	public StatusReport getStatusReport() {
		StatusReport statusReport = new StatusReport(
				srProps.getReportTitle(), 
				srProps.getReportClassification(), 
				createSprint(), 
				createAuthor(),
				srProps.getReportWeekEndingDate());	
		
		/*List<JiraIssue> jiraIssues = jiraIssueDao.getAllIssues();
		for (JiraIssue jiraIssue : jiraIssues) {
			statusReport.addJiraIssue(jiraIssue);
		}*/
		return statusReport;
	}
	
	public StatusReport getV2StatusReport() {
		StatusReport statusReport = new StatusReport(
				srProps.getReportTitle(), 
				srProps.getReportClassification(), 
				createSprint(), 
				createAuthor(),
				srProps.getReportWeekEndingDate());	
		
		JiraIssues jiraIssues = jiraIssueDao.getAllIssues();
		
		for (JiraIssue jiraIssue : jiraIssues.getStories()) {
			statusReport.addJiraIssue(jiraIssue);
		}
		return statusReport;
	}
	
	private Author createAuthor() {
		return new Author(
				srProps.getAuthorName(), 
				srProps.getAuthorType(), 
				srProps.getAuthorTitle());
	}
	
	private Sprint createSprint() {
		return new Sprint(
				srProps.getSprintNumber(), 
				srProps.getSprintStartDate(), 
				srProps.getSprintEndDate());
	}
	
}
