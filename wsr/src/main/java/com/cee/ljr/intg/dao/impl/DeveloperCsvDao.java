package com.cee.ljr.intg.dao.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.dao.DeveloperDao;
import com.cee.ljr.intg.fileparser.DeveloperFileParser;
import com.cee.ljr.intg.mapping.DeveloperMapper;

@Component
public class DeveloperCsvDao implements DeveloperDao {
	
	@Autowired
	private DeveloperFileParser<CSVRecord> fileParser;
	
	@Autowired
	private DeveloperMapper mapper;
	
	@Override
	public Set<Developer> getAll() {
		Iterable<CSVRecord> records = fileParser.parseAll();
		
		return mapper.map(records);
	}
	
	@Override
	public Developer getByNameInJira(String nameInJira) {
		CSVRecord record = fileParser.parseForName(nameInJira);
		
		return mapper.map(record);
	}
	
	@Override
	public Set<Developer> getByKeys(Collection<String> keys) {
		Iterable<CSVRecord> records = fileParser.parseByKeys(keys);
		
		return mapper.map(records);
	}
}
