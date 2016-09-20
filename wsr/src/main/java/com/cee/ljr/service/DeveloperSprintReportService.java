package com.cee.ljr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cee.ljr.domain.report.DeveloperSprintReport;
import com.cee.ljr.intg.dao.JiraIssueDao;

@Service
public class DeveloperSprintReportService {
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	//@Autowired
	//DeveloperSprintReportFactory developerSprintReportFactory;
	
	public DeveloperSprintReport getReport(String developerName, int sprintNumber) {
		return null;
	}

}
