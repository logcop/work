package com.cee.ljr.intg.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.intg.jira.dao.JiraIssueDao;
import com.cee.ljr.intg.jira.domain.JiraIssue;
import com.cee.ljr.test.BaseTest;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class JiraXlsxIssueDaoTest extends BaseTest {
	//Logger log = LoggerFactory.getLogger(JiraXlsxIssueDaoTest.class);
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Test
	public void testGetAllIssues() {
		List<JiraIssue> issues = jiraIssueDao.getAllIssues(xlsxPaths);
		assertNotNull(issues);
		assertFalse(issues.isEmpty());
		//log.debug("Retreived JiraIssues.. {}", issues.size());
	}
}
