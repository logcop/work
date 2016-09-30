package com.cee.ljr.intg.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.file.csv.test.util.DateUtil;
import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.fileparser.impl.CsvReaderFileParser;
import com.cee.ljr.intg.jira.domain.IssueType;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.mapping.DescriptiveTaskMapper;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class DescriptiveTaskCsvDao {
	
	@Autowired
	CsvReaderFileParser fileParser;
	
	@Autowired
	DescriptiveTaskMapper mapper;
	
	@Value("${jira.csv.urls}")
	String csvPaths;
	
	public List<DescriptiveTask> getTasksByDeveloperAndSprints(Developer developer, Date beginDate, Date endDate) {		
		DateFormat dateFormater = new SimpleDateFormat(DateUtil.JIRA_WORKLOG_DATE_FORMAT);
		
		Criteria criteria = new Criteria(
			Expression.and (
				Condition.containsOne(
						JiraAttribute.ISSUE_TYPE, 
						new ArrayList<String>(Arrays.asList(IssueType.TASK, IssueType.SUB_TASK, IssueType.BUG))),
				Expression.and(
						Condition.eq(JiraAttribute.CUSTOM_FIELD_ASSIGNED_DEVELOPER, developer.getNameInJira()),				
						Condition.between(JiraAttribute.LOG_WORK, beginDate, endDate, dateFormater)
				)
			)
		);
				
		Iterable<CSVRecord> taskRecords = fileParser.parse(csvPaths, criteria);
		
		public
		
		return mapper.map(taskRecords);
	}
	
	
	private Map<String, CSVRecord> getAssociatedEpics(Iterable<CSVRecord> taskRecords) {
		Map<String, CSVRecord> epicMap = new HashMap<String, CSVRecord>();
		
		Set<String> epicKeys = new TreeSet<String>();		
		for (CSVRecord taskRecord : taskRecords) {
			String epicKey = taskRecord.getSingleValueFor(JiraAttribute.CUSTOM_FIELD_EPIC_LINK);
			epicKeys.add(epicKey);
		}
		
		Criteria criteria = new Criteria(
			Expression.and (
				Condition.eq(JiraAttribute.ISSUE_TYPE, IssueType.EPIC),
				Condition.containsOne(JiraAttribute.ISSUE_KEY, epicKeys)
			)
		);
		
		Iterable<CSVRecord> epics = fileParser.parse(csvPaths, criteria);
		for (CSVRecord epic : epics) {
			epicMap.put(epic.getSingleValueFor(JiraAttribute.ISSUE_KEY), epic);
		}
		
		return epicMap;
	}
	
}
