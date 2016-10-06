package com.cee.ljr.intg.fileparser.impl;

import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.fileparser.SprintFileParser;
import com.cee.ljr.intg.mapping.SprintMapper;


@Component
public class SprintCsvFileParser extends BaseCsvFileParser<Sprint> implements SprintFileParser {
	
	private static final boolean WITH_HEADER = true;
	
	@Value("${sprints.url}")
	String filePath;
	
	@Autowired
	SprintMapper mapper;
	
	
	public SprintCsvFileParser() {
		super(WITH_HEADER);
	}
	
	
	@Override
	public List<Sprint> parseAll() {
		return parseForAll(filePath);
	}	


	@Override
	public List<Sprint> parseByNumber(int sprintNumber) {
		Criteria criteria = new Criteria(
				Condition.like(
						SprintHeader.SPRINT_NAME, 
						Integer.toString(sprintNumber))
		);
		
		return parseForAll(filePath, criteria);
	}
	
	
	@Override
	public List<Sprint> parseByNames(Collection<String> sprintNames) {		
		Criteria criteria = new Criteria(
				Condition.containsOne(
						SprintHeader.SPRINT_NAME, 
						sprintNames)
		);
		
		return parseForAll(filePath, criteria);
	}


	@Override
	public Sprint parseByName(String sprintName) {
		
		Criteria criteria = new Criteria(
				Condition.eq(
						SprintHeader.SPRINT_NAME, 
						sprintName)
		);
		
		return parseForSingle(filePath, criteria);
	}


	@Override
	public List<Sprint> parseBetweenDates(Date beginDate, Date endDate) {
		DateFormat dateFormater = new SimpleDateFormat(Sprint.DATE_FORMAT);
		
		Criteria criteria = new Criteria(
			// begin date OR end date is between sprint start and end dates	
			Expression.or( 
					// beginDate is between sprint start and end dates
					Expression.and( 
							Condition.gt(SprintHeader.START_DATE, beginDate, dateFormater), 
							Condition.lt(SprintHeader.END_DATE, beginDate, dateFormater)),
					// end date is between sprint start and end dates
					Expression.and( 
							Condition.gt(SprintHeader.START_DATE, endDate, dateFormater), 
							Condition.lt(SprintHeader.END_DATE, endDate, dateFormater)))
		);
		
		return parseForAll(filePath, criteria);
	}
	
	
	@Override
	public List<Sprint> parseByKeys(Collection<String> sprintKeys) {
		Criteria criteria = new Criteria(
				Condition.containsOne(SprintHeader.SPRINT_NAME, sprintKeys)
		);
		
		return parseForAll(filePath, criteria);
	}


	@Override
	protected List<Sprint> parseForAll(String filePaths, Criteria criteria) {		
		Reader reader = getFileReader(filePaths);
		
		Iterable<CSVRecord> records = parseForAllRecords(reader, criteria);
		
		List<Sprint> sprints = mapper.map(records);
		
		closeReader(reader);
		
		return sprints;
	}


	@Override
	protected List<Sprint> parseForAll(String filePaths) {		
		Reader reader = getFileReader(filePaths);
		
		Iterable<CSVRecord> records = parseForAllRecords(reader);
		
		List<Sprint> sprints = mapper.map(records);
		
		closeReader(reader);
		
		return sprints;
	}
	
	
	@Override
	protected Sprint parseForSingle(String filePaths, Criteria criteria) {		
		Reader reader = getFileReader(filePaths);
		
		CSVRecord record = parseForSingleRecord(reader, criteria);
		
		Sprint sprint = null;	
		
		if (record != null) {
			sprint = mapper.map(record);
		}
		
		closeReader(reader);
		
		return sprint;
	}
	
	
	
}
