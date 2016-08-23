package com.cee.wsr.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.wsr.config.ApplicationConfig;
import com.cee.wsr.domain.JiraIssues;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class JiraXlsxIssueDaoTest {
	Logger log = LoggerFactory.getLogger(JiraXlsxIssueDaoTest.class);
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Test
	public void testGetAllIssues() {
		JiraIssues issues = jiraIssueDao.getAllIssues();
		Assert.assertNotNull(issues);
		log.debug("Retreived JiraIssues.. {}", issues.printStats());
	}
}
