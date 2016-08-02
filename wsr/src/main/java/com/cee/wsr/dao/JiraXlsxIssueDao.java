package com.cee.wsr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.JiraIssues;
import com.cee.wsr.properties.StatusReportProperties;
import com.cee.wsr.spreadsheet.JiraIssuesXlsxParser;

@Component
public class JiraXlsxIssueDao implements JiraIssueDao{
	//private static final Logger LOG = LoggerFactory.getLogger(JiraXlsxIssueDao.class);
	
	@Autowired
	JiraIssuesXlsxParser jiraIssuesXlsxParser;
	@Autowired
	StatusReportProperties statusReportProperties;
	
	public JiraIssues getAllIssues() {
		// TODO: have to figure out how to inject a different path if user overrides path.
		String xlsxPath = statusReportProperties.getXlsPath();
		
		return jiraIssuesXlsxParser.parseXlsx(xlsxPath);		
	}

}
