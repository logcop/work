/**
 * 
 */
package com.cee.wsr.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cee.wsr.dao.JiraIssueDao;
import com.cee.wsr.domain.jira.JiraIssue;
import com.cee.wsr.domain.report.StatusReport;
import com.cee.wsr.domain.report.StatusReportFactory;

/**
 * @author chuck
 *
 */
@Service
public class StatusReportService {
	//private static final Logger log = LoggerFactory.getLogger(StatusReportService.class);
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Autowired
	StatusReportFactory statusReportFactory;
			
	public StatusReport getWeeklyStatusReport(Date weekStartDate, Date weekendingDate) {
		
		List<JiraIssue> jiraIssueList = jiraIssueDao.getAllIssues();		
		
		return statusReportFactory.getWeeklyStatusReport(jiraIssueList, weekStartDate, weekendingDate);
	}
	
	
	
}
