package com.cee.ljr.intg.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.ljr.intg.fileparser.impl.CsvReaderFileParser;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class EpicCsvDao {
	
	@Autowired
	CsvReaderFileParser fileParser;	
	
	@Value("${jira.csv.urls}")
	String csvPaths;
}
