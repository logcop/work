package com.cee.wsr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.jira.JiraIssue;
import com.cee.wsr.properties.StatusReportProperties;
import com.cee.wsr.spreadsheet.parser.JiraIssuesXlsxParser;

@Component
public class JiraXlsxIssueDao implements JiraIssueDao{
	//private static final Logger LOG = LoggerFactory.getLogger(JiraXlsxIssueDao.class);

	@Autowired
	JiraIssuesXlsxParser jiraIssuesXlsxParser;
	
	@Autowired
	StatusReportProperties statusReportProperties;
	
	@Override
	public List<JiraIssue> getAllIssues() {
		// TODO: have to figure out how to inject a different path if user overrides path.
		String[] xlsxPath = statusReportProperties.getXlsPath();
		return jiraIssuesXlsxParser.parseXlsx(xlsxPath);
	}

}
