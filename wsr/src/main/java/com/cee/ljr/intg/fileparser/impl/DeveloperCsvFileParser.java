package com.cee.ljr.intg.fileparser.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.ljr.intg.fileparser.CsvFileParser;
import com.cee.ljr.intg.fileparser.DeveloperFileParser;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class DeveloperCsvFileParser implements DeveloperFileParser<CSVRecord> {
	
	private static final boolean SKIP_HEADER = true;
	
	@Value("${developers.url}")
	String filePath;
	
	@Autowired
	CsvFileParser<CSVRecord> csvFileParser;
	
	
	@Override
	public Iterable<CSVRecord> parseAll() {
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, SKIP_HEADER);		
		
		return records;
	}
	
	
	@Override
	public CSVRecord parseForName(String nameInJira) {
		if (nameInJira == null) {
			throw new IllegalArgumentException("nameInJira must not be null.");
		}
		
		Criteria criteria = new Criteria(
				Condition.eq(DeveloperHeader.KEY, nameInJira)
		);
		
		CSVRecord record = csvFileParser.parseForSingleRecord(filePath, criteria);	
		
		return record;
	}

	
	@Override
	public Iterable<CSVRecord> parseByKeys(Collection<String> keys) {
		Criteria criteria = new Criteria(
				Condition.containsOne(DeveloperHeader.KEY, keys)
		);
		
		Iterable<CSVRecord> records = csvFileParser.parse(filePath, criteria);
		
		return records;
	}
}
