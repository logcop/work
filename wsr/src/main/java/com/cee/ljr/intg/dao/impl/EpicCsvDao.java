package com.cee.ljr.intg.dao.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.file.csv.criteria.Criteria;
import com.cee.file.csv.criteria.condition.Condition;
import com.cee.ljr.domain.common.Epic;
import com.cee.ljr.intg.dao.EpicDao;
import com.cee.ljr.intg.fileparser.impl.CsvReaderFileParser;
import com.cee.ljr.intg.jira.domain.JiraAttribute;
import com.cee.ljr.intg.mapping.EpicMapper;

@Component
@PropertySource("classpath:/properties/data-access.properties")
public class EpicCsvDao implements EpicDao {
	
	@Autowired
	CsvReaderFileParser fileParser;	
	
	@Value("${jira.csv.urls}")
	String csvPaths;
	
	@Override
	public Set<Epic> getByKeys(Collection<String> keys) {
		Criteria criteria = new Criteria(
			Condition.containsOne(JiraAttribute.ISSUE_KEY, keys)	
		);
		
		Iterable<CSVRecord> records = fileParser.parse(csvPaths, criteria);
		
		return EpicMapper.mapRecords(records);
	}
	
	@Override
	public Epic getByKey(String key) {
		Criteria criteria = new Criteria(
			Condition.eq(JiraAttribute.ISSUE_KEY, key)	
		);
		
		CSVRecord record = fileParser.parseForSingleRecord(csvPaths, criteria);
		
		return EpicMapper.mapRecord(record);
	}
}
