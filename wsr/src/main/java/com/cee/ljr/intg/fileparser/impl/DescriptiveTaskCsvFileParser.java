package com.cee.ljr.intg.fileparser.impl;

import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.intg.jira.domain.IssueType;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.mapping.DescriptiveTaskMapper;
import com.cee.ljr.utils.DateUtil;

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
		Reader reader = getFileReader(filePaths);
		
		Iterable<CSVRecord> records = parseForAllRecords(reader, criteria);
		
		List<DescriptiveTask> tasks = mapper.map(filePaths, records);
		
		closeReader(reader);
		
		return tasks;
	}
	

	@Override
	protected List<DescriptiveTask> parseForAll(String filePaths) {		
		Reader reader = getFileReader(filePaths);
		
		Iterable<CSVRecord> records = parseForAllRecords(reader);
		
		List<DescriptiveTask> tasks = mapper.map(filePaths, records);
		
		closeReader(reader);
		
		return tasks;
	}
	

	@Override
	protected DescriptiveTask parseForSingle(String filePaths, Criteria criteria) {
		Reader reader = getFileReader(filePaths);
		
		CSVRecord record = parseForSingleRecord(reader, criteria);
		
		DescriptiveTask task = null;	
		
		if (record != null) {
			task = mapper.map(filePaths, record);
		}
		
		closeReader(reader);
		
		return task;
	}
	
	
	
}
