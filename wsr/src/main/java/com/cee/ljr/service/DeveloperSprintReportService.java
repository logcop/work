package com.cee.ljr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.domain.common.Task;
import com.cee.ljr.domain.common.util.SprintUtil;
import com.cee.ljr.domain.jira.JiraIssue;
import com.cee.ljr.domain.report.DeveloperSprintReport;
import com.cee.ljr.intg.dao.DeveloperDao;
import com.cee.ljr.intg.dao.JiraIssueDao;
import com.cee.ljr.intg.dao.SprintDao;
import com.cee.ljr.mapping.JiraIssueMapper;

@Service
public class DeveloperSprintReportService {
	
	@Autowired
	JiraIssueDao jiraIssueDao;
	
	@Autowired
	JiraIssueMapper jiraIssueMapper;
	
	@Autowired
	DeveloperDao developerDao;
	
	@Autowired
	SprintDao sprintDao;
	
	//@Autowired
	//DeveloperSprintReportFactory developerSprintReportFactory;
	
	public List<DeveloperSprintReport> getReports(int sprintNumber) {
		List<DeveloperSprintReport> developerSprintReports = new ArrayList<DeveloperSprintReport>();		
		
		List<Developer> developers =  developerDao.getAll();
		
		for (Developer developer : developers) {
			String developerName = developer.getNameInJira();
			DeveloperSprintReport developerSprintReport = getReport(developerName, sprintNumber);
			developerSprintReports.add(developerSprintReport);
		}
		
		return developerSprintReports;
	}
	
	public DeveloperSprintReport getReport(String developerName, int sprintNumber) {
		List<Task> tasks = new ArrayList<Task>();
		Developer developer = developerDao.getByNameInJira(developerName);
		List<Sprint> sprints = sprintDao.getByNumber(sprintNumber);
		List<String> sprintNames = SprintUtil.getSprintNames(sprints);
		
		List<JiraIssue> jiraIssues = jiraIssueDao.getAllByDeveloperAndSprints(developerName, sprintNames);
		
		for (JiraIssue jiraIssue : jiraIssues) {
			Task task = jiraIssueMapper.createTask(jiraIssue);
			tasks.add(task);
		}
		
		Sprint aSprint = sprints.get(0);
		Date sprintStartDate = aSprint.getStartDate();
		Date sprintEndDate = aSprint.getEndDate();
		
		return new DeveloperSprintReport(sprintNumber, sprintStartDate, sprintEndDate, developer, tasks);
	}

}
