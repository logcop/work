package com.cee.wsr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.wsr.domain.JiraIssues;
import com.cee.wsr.properties.StatusReportProperties;
import com.cee.wsr.spreadsheet.parser.JiraIssuesXlsxParser;

@Component
public class JiraXlsxIssueDao implements JiraIssueDao{
	//private static final Logger LOG = LoggerFactory.getLogger(JiraXlsxIssueDao.class);
	
	//@Autowired
	//JiraIssuesCsvParser jiraIssuesCsvParser;
	@Autowired
	JiraIssuesXlsxParser jiraIssuesXlsxParser;
	
	@Autowired
	StatusReportProperties statusReportProperties;
	
	@Override
	public JiraIssues getAllIssues() {
		// TODO: have to figure out how to inject a different path if user overrides path.
		String[] xlsxPath = statusReportProperties.getXlsPath();
		//String[] csvPath = statusReportProperties.getCsvPath();
		return jiraIssuesXlsxParser.parseXlsx(xlsxPath);	
		// the lazy way, need to create another dao for csv files.
		//return jiraIssuesCsvParser.parseCsv(csvPath);
	}

}
