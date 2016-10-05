package com.cee.ljr.intg.fileparser.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.file.csv.criteria.expression.Expression;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.fileparser.SprintFileParser;


@Component
public class SprintCsvFileParser extends BaseCsvFileParser implements SprintFileParser<CSVRecord> {
	
	private static final boolean SKIP_HEADER = true;
	
	@Value("${sprints.url}")
	String filePath;
	
	@Override
	public Iterable<CSVRecord> parseAll() {
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);

		return records;
	}	


	@Override
	public Iterable<CSVRecord> parseByNumber(int sprintNumber) {
		Criteria criteria = new Criteria(
				Condition.like(
						SprintHeader.SPRINT_NAME, 
						Integer.toString(sprintNumber))
		);		
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, criteria);
		
		return records;
	}
	
	
	@Override
	public Iterable<CSVRecord> parseByNames(List<String> sprintNames) {		
		Criteria criteria = new Criteria(
				Condition.containsOne(
						SprintHeader.SPRINT_NAME, 
						sprintNames)
		);		
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, criteria);
		
		return records;
	}


	@Override
	public CSVRecord parseByName(String sprintName) {
		
		Criteria criteria = new Criteria(
				Condition.eq(
						SprintHeader.SPRINT_NAME, 
						sprintName)
		);
		
		CSVRecord record = csvFileParser.parseForSingleRecord(filePath, criteria);
		
		return record;
	}


	@Override
	public Iterable<CSVRecord> parseDateBetweenSprintStartAndEnd(Date date) {
		DateFormat dateFormater = 
				new SimpleDateFormat(Sprint.DATE_FORMAT);
		
		Criteria criteria = new Criteria(
			Expression.and(
					Condition.gt(
							SprintHeader.START_DATE, 
							date, 
							dateFormater), 
					Condition.lt(
							SprintHeader.START_DATE, 
							date, 
							dateFormater))
		);		
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, criteria);
		
		return records;
	}
	
	
}
