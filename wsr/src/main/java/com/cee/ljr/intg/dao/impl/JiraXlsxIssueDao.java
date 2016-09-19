package com.cee.ljr.intg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.jira.JiraIssue;
import com.cee.ljr.intg.dao.JiraIssueDao;
import com.cee.ljr.properties.WeeklyStatusReportProperties;
import com.cee.ljr.spreadsheet.parser.JiraIssuesXlsxParser;

@Component
public class JiraXlsxIssueDao implements JiraIssueDao{
	//private static final Logger LOG = LoggerFactory.getLogger(JiraXlsxIssueDao.class);

	@Autowired
	JiraIssuesXlsxParser jiraIssuesXlsxParser;
	
	@Autowired
	WeeklyStatusReportProperties weeklyStatusReportProperties;
	
	@Override
	public List<JiraIssue> getAllIssues() {
		// TODO: have to figure out how to inject a different path if user overrides path.
		String[] xlsxPath = weeklyStatusReportProperties.getXlsPath();
		return jiraIssuesXlsxParser.parseXlsx(xlsxPath);
	}

}
