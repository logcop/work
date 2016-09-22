package com.cee.ljr.intg.jira.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.cee.ljr.intg.fileparser.impl.JiraIssuesXlsxParser;
import com.cee.ljr.intg.jira.dao.JiraIssueDao;
import com.cee.ljr.intg.jira.domain.JiraIssue;

//@Component
@PropertySource("classpath:/properties/data-access.properties")
public class JiraXlsxIssueDao implements JiraIssueDao{
	//private static final Logger LOG = LoggerFactory.getLogger(JiraXlsxIssueDao.class);

	@Autowired
	JiraIssuesXlsxParser jiraIssuesXlsxParser;
	
	@Value("${jira.xlsx.urls}")
	String xlsxPaths;
	
	@Override
	public List<JiraIssue> getAllIssues() {		
		return jiraIssuesXlsxParser.parseXlsx(xlsxPaths);
	}

	@Override
	public List<JiraIssue> getTasksByDeveloperAndSprints(String developerName, List<String> sprintNames) {
		// TODO Auto-generated method stub
		return null;
	}	

}
