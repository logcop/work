package com.cee.ljr.intg.fileparser.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.fileparser.DeveloperFileParser;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class DeveloperCsvFileParser extends BaseExcelCsvFileParser implements DeveloperFileParser {
	
	@Value("${developers.url}")
	String filePath;
	
	@Override
	public List<Developer> parseAll() {
		List<Developer> developerList = new ArrayList<Developer>();
		
		Iterable<CSVRecord> records = parse(filePath);		
		for (CSVRecord record : records) {
		    String jiraName = record.get(0);
		    String firstName = record.get(1);
		    String lastName = record.get(2);
		    developerList.add(new Developer(jiraName, firstName, lastName));
		}

		return developerList;
	}
}
