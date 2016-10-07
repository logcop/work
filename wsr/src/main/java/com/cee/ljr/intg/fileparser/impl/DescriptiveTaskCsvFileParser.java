package com.cee.ljr.intg.fileparser.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.intg.jira.domain.IssueType;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.mapping.DescriptiveTaskMapper;
import com.cee.ljr.utils.DateUtil;

@Component
public class DescriptiveTaskCsvFileParser extends BaseCsvFileParser<DescriptiveTask> {

	private static final boolean WITH_HEADER = true;
	
	@Autowired
	DescriptiveTaskMapper mapper;
	
	public DescriptiveTaskCsvFileParser() {
		super(WITH_HEADER);
	}
	
	
	public List<DescriptiveTask> parseTasksByDeveloperBetweenDates(String csvPaths, String developerName, Date beginDate, Date endDate) {
		DateFormat dateFormater = 
				new SimpleDateFormat(DateUtil.JIRA_WORKLOG_DATE_FORMAT);
		
		Criteria criteria = new Criteria(
			Expression.and (
				Condition.containsOne(
						JiraAttribute.ISSUE_TYPE, 
						new ArrayList<String>(Arrays.asList(IssueType.TASK, IssueType.SUB_TASK, IssueType.BUG))),
				Expression.and(
						Condition.eq(JiraAttribute.CUSTOM_FIELD_ASSIGNED_DEVELOPER, developerName),				
						Condition.between(JiraAttribute.LOG_WORK, beginDate, endDate, dateFormater)
				)
			)
		);		
		
		List<DescriptiveTask> tasks = parseForAll(csvPaths, criteria);
				
		return tasks;		
	}
	
	
	@Override
	protected List<DescriptiveTask> parseForAll(String filePaths, Criteria criteria) {			
		Collection<CSVRecord> records = parseForAllRecords(filePaths, criteria);
		
		List<DescriptiveTask> tasks = mapper.map(filePaths, records);
		
		return tasks;
	}
	

	@Override
	protected List<DescriptiveTask> parseForAll(String filePaths) {	
			
		Collection<CSVRecord> records = parseForAllRecords(filePaths);
			
		List<DescriptiveTask> tasks = mapper.map(filePaths, records);
		
		return tasks;
	}
	

	@Override
	protected DescriptiveTask parseForSingle(String filePaths, Criteria criteria) {
		
		CSVRecord record = parseForSingleRecord(filePaths, criteria);
		
		DescriptiveTask task = mapper.map(filePaths, record);
		
		return task;
	}
	
	
	
}
