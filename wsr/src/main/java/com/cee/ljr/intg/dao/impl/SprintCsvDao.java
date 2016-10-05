package com.cee.ljr.intg.dao.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.file.csv.CSVRecord;
import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.dao.SprintDao;
import com.cee.ljr.intg.fileparser.SprintFileParser;
import com.cee.ljr.intg.mapping.SprintMapper;

@Component
public class SprintCsvDao implements SprintDao {

	
	@Autowired
	private SprintFileParser<CSVRecord> sprintFileParser;
	
	@Override
	public Set<Sprint> getAll() {
		Iterable<CSVRecord> records = sprintFileParser.parseAll();
		
		Set<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}

	
	@Override
	public Set<Sprint> getByNumber(int sprintNumber) {
		Iterable<CSVRecord> records = sprintFileParser.parseByNumber(sprintNumber);
		
		Set<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}
	
	
	@Override
	public Set<Sprint> getAllByNames(Collection<String> sprintNames) {
		Iterable<CSVRecord> records = sprintFileParser.parseByNames(sprintNames);
		
		Set<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}
	
	
	public Set<Sprint> getAllBetweenDates(Date beginDate, Date endDate) {
		Iterable<CSVRecord> records = sprintFileParser.parseBetweenDates(beginDate, endDate);
		
		Set<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}
	
	
	@Override
	public Sprint getByName(String sprintName) {
		CSVRecord record = sprintFileParser.parseByName(sprintName);
		
		Sprint sprint = SprintMapper.mapRecord(record);
		
		return sprint;
	}
	
	
	@Override
	public Set<Sprint> getByKeys(Collection<String> sprintKeys) {
		Iterable<CSVRecord> records = sprintFileParser.parseByKeys(sprintKeys);
		
		Set<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}

}
