package com.cee.ljr.intg.jira.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cee.ljr.intg.jira.dao.JiraIssueDao;
import com.cee.ljr.intg.jira.domain.JiraIssue;
import com.cee.ljr.intg.jira.fileparser.JiraIssuesXlsxParser;

//@Component
public class JiraXlsxIssueDao implements JiraIssueDao{
	//private static final Logger LOG = LoggerFactory.getLogger(JiraXlsxIssueDao.class);

	@Autowired
	JiraIssuesXlsxParser jiraIssuesXlsxParser;
	
	@Override
	public List<JiraIssue> getAllIssues(String xlsxPaths) {		
		return jiraIssuesXlsxParser.parseXlsx(xlsxPaths);
	}

	/*@Override
	public List<JiraIssue> getTasksByDeveloperAndSprints(String developerName, List<String> sprintNames) {
		// TODO Auto-generated method stub
		return null;
	}	*/

}
