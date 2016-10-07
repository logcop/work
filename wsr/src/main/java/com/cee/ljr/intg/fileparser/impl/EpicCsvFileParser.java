package com.cee.ljr.intg.fileparser.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.intg.jira.domain.IssueType;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.mapping.EpicMapper;

@Component
public class EpicCsvFileParser extends BaseCsvFileParser<Epic>{

	private static final boolean WITH_HEADER = true;
	
	@Autowired
	EpicMapper mapper;
	
	public EpicCsvFileParser() {
		super(WITH_HEADER);
	}
	
	
	
	public List<Epic> parseByKeys(String csvPaths, List<String> issueKeys) {
		
		Criteria criteria = new Criteria(
				Expression.and (
					Condition.eq(JiraAttribute.ISSUE_TYPE, IssueType.EPIC),
					Condition.containsOne(JiraAttribute.ISSUE_KEY, issueKeys)
				)
		);
		
		return parseForAll(csvPaths, criteria);
	}	
	
	
	public Epic parseByKey(String csvPaths, String issueKey) {
		
		Criteria criteria = new Criteria(
				Expression.and (
					Condition.eq(JiraAttribute.ISSUE_TYPE, IssueType.EPIC),
					Condition.eq(JiraAttribute.ISSUE_KEY, issueKey)
				)
		);
		
		return parseForSingle(csvPaths, criteria);
	}

	
	@Override
	protected List<Epic> parseForAll(String filePaths, Criteria criteria) {
		
		Iterable<CSVRecord> records = parseForAllRecords(filePaths, criteria);
		
		List<Epic> epics = mapper.map(records);
		
		return epics;
	}

	@Override
	protected List<Epic> parseForAll(String filePaths) {
		
		Iterable<CSVRecord> records = parseForAllRecords(filePaths);
		
		List<Epic> epics = mapper.map(records);
		
		return epics;
	}

	@Override
	protected Epic parseForSingle(String filePaths, Criteria criteria) {
		
		CSVRecord record = parseForSingleRecord(filePaths, criteria);
		
		Epic epic = null;	
		
		if (record != null) {
			epic = mapper.map(record);
		}
		
		return epic;
	}
	
	

}
