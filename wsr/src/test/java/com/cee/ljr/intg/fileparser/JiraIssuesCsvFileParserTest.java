package com.cee.ljr.intg.fileparser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cee.ljr.config.ApplicationConfig;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.domain.common.util.SprintUtil;
import com.cee.ljr.intg.dao.impl.DeveloperCsvDao;
import com.cee.ljr.intg.dao.impl.SprintCsvDao;
import com.cee.ljr.intg.fileparser.impl.JiraIssuesCsvFileParser;
import com.cee.ljr.intg.jira.domain.JiraIssue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@PropertySource("classpath:/properties/data-access.properties")
public class JiraIssuesCsvFileParserTest {

	@Autowired
	JiraIssuesCsvFileParser jiraIssuesCsvFileParser;
	
	@Autowired
	DeveloperCsvDao developerCsvDao;
	
	@Autowired
	SprintCsvDao sprintCsvDao;
	
	@Value("${jira.csv.urls}")
	String csvPaths;
	
	@Test
	public void testParseAll() {
		List<JiraIssue> jiraIssues = jiraIssuesCsvFileParser.parseAll(csvPaths);
		
		Assert.assertNotNull(jiraIssues);
		Assert.assertFalse(jiraIssues.isEmpty());
		for (JiraIssue jiraIssue : jiraIssues) {
			System.out.println(jiraIssue);
		}
	}
	
	@Test
	public void testparseAllByDeveloperAndSprints() {
		//Developer developer = developerCsvDao.getByNameInJira("Chuck");
		List<Sprint> sprints = sprintCsvDao.getByNumber(10);
		List<String> sprintNames = SprintUtil.getSprintNames(sprints);
		
		List<JiraIssue> jiraIssues = 
				jiraIssuesCsvFileParser.parseTasksByDeveloperAndSprints(csvPaths, "Chuck", sprintNames);
		
		Assert.assertNotNull(jiraIssues);
		Assert.assertFalse(jiraIssues.isEmpty());
		
		for (JiraIssue jiraIssue : jiraIssues) {
			System.out.println("Key: " + jiraIssue.getKey() + " Devs: " + jiraIssue.getDevelopers() + " Sprints: " + jiraIssue.getSprints());
		}
		System.out.println("Found " + jiraIssues.size() + " jira issues.");
	}
}
