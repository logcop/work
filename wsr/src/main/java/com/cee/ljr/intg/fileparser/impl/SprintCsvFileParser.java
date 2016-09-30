package com.cee.ljr.intg.fileparser.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.domain.common.util.SprintUtil;
import com.cee.ljr.intg.fileparser.CsvFileParser;
import com.cee.ljr.intg.fileparser.SprintFileParser;
import com.cee.ljr.utils.DateUtil;


@Component
@PropertySource("classpath:/properties/data-access.properties")
public class SprintCsvFileParser implements SprintFileParser {

	private static final int NAME_INDEX = 0;
	private static final int START_DATE_INDEX = 1;
	private static final int END_DATE_INDEX = 2;
	
	private static final boolean SKIP_HEADER = true;
	
	@Value("${sprints.url}")
	String filePath;
	
	@Autowired
	CsvFileParser<CSVRecord> csvFileParser;
	
	@Override
	public List<Sprint> parseAll() {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);
		for (CSVRecord record : records) {
			Sprint sprint = mapToSprint(record);
			sprintList.add(sprint);
		}

		return sprintList;
	}
	


	@Override
	public List<Sprint> parseByNumber(int sprintNumber) {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);
		for (CSVRecord record : records) {
			String name = record.get(NAME_INDEX);
			int number = SprintUtil.getNumberFromName(name);
			
			if (number == sprintNumber) {
				Sprint sprint = mapToSprint(record);
				sprintList.add(sprint);
			}
		}

		return sprintList;
	}
	
	@Override
	public List<Sprint> parseByNames(List<String> sprintNames) {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);
		for (CSVRecord record : records) {
			String name = record.get(NAME_INDEX);
			for (String sprintName : sprintNames) {
				if (sprintName.equals(name)) {
					Sprint sprint = mapToSprint(record);
					sprintList.add(sprint);
				}
			}
		}

		return sprintList;
	}


	@Override
	public Sprint parseByName(String sprintName) {
		Sprint sprint = null;
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);
		for (CSVRecord record : records) {
			String name = record.get(NAME_INDEX);			
			if (sprintName.equals(name)) {
				sprint = mapToSprint(record);
				break;
			}
		}
		
		return sprint;
	}
	
	
	
	private Sprint mapToSprint(CSVRecord record) {
		String name = record.get(NAME_INDEX);
		String startDateStr = record.get(START_DATE_INDEX);
		String endDateStr = record.get(END_DATE_INDEX);
		
		return new Sprint(name, DateUtil.getSprintDate(startDateStr), DateUtil.getSprintDate(endDateStr));
	}
	
	
}
