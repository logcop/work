package com.cee.wsr.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.dao.JiraIssueDao;
import com.cee.ljr.domain.jira.JiraIssue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class JiraXlsxIssueDaoTest {
	Logger log = LoggerFactory.getLogger(JiraXlsxIssueDaoTest.class);
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Test
	public void testGetAllIssues() {
		List<JiraIssue> issues = jiraIssueDao.getAllIssues();
		Assert.assertNotNull(issues);
		log.debug("Retreived JiraIssues.. {}", issues.size());
	}
}
