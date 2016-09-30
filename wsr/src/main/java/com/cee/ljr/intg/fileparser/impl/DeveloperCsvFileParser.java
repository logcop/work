package com.cee.ljr.intg.fileparser.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.fileparser.CsvFileParser;
import com.cee.ljr.intg.fileparser.DeveloperFileParser;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class DeveloperCsvFileParser implements DeveloperFileParser {
	
	private static final int NAME_IN_JIRA_INDEX = 0;
	private static final int FIRST_NAME_INDEX = 1;
	private static final int LAST_NAME_INDEX = 2;
	
	private static final boolean SKIP_HEADER = true;
	
	@Value("${developers.url}")
	String filePath;
	
	@Autowired
	CsvFileParser<CSVRecord> csvFileParser;
	
	@Override
	public List<Developer> parseAll() {
		List<Developer> developerList = new ArrayList<Developer>();
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);		
		for (CSVRecord record : records) {
		    Developer developer = mapToDeveloper(record);
		    developerList.add(developer);
		}

		return developerList;
	}
	
	@Override
	public Developer parseForName(String nameInJira) {
		if (nameInJira == null) {
			throw new IllegalArgumentException("nameInJira must not be null.");
		}
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);	
		
		for (CSVRecord record : records) {
			if (nameInJira.equals(record.get(NAME_IN_JIRA_INDEX))) {
				return mapToDeveloper(record);
			}
		}
		
		return null;
	}
	
	
	private Developer mapToDeveloper(CSVRecord record) {
		String jiraName = record.get(NAME_IN_JIRA_INDEX);
	    String firstName = record.get(FIRST_NAME_INDEX);
	    String lastName = record.get(LAST_NAME_INDEX);
	    
	    return new Developer(jiraName, firstName, lastName);
	}
}
