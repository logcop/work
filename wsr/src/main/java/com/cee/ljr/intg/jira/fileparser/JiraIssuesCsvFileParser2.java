package com.cee.ljr.intg.jira.fileparser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.ljr.intg.fileparser.impl.CsvReaderFileParser;
import com.cee.ljr.intg.jira.domain.IssueType;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.utils.DateUtil;

@Component
public class JiraIssuesCsvFileParser2 {
	private static final Logger log = LoggerFactory.getLogger(JiraIssuesCsvFileParser2.class);
	
	@Autowired
	CsvReaderFileParser csvFileParser;	
	
	//private String[]
	
	public Iterable<CSVRecord> parseTasksByDeveloperBetweenDates(String csvPaths, String developerName, Date beginDate, Date endDate) {
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
		
		Iterable<CSVRecord> records = csvFileParser.parse(csvPaths, criteria);
				
		return records;		
	}
	
	
	public Iterable<CSVRecord> parseEpicsByIssueKeys(String csvPaths, Set<String> issueKeys) {
		
		Criteria criteria = new Criteria(
				Expression.and (
					Condition.eq(JiraAttribute.ISSUE_TYPE, IssueType.EPIC),
					Condition.containsOne(JiraAttribute.ISSUE_KEY, issueKeys)
				)
		);
		
		Iterable<CSVRecord> records = csvFileParser.parse(csvPaths, criteria);
		
		return records;		
	}

}
