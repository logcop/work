package com.cee.ljr.intg.dao.impl;

import java.util.List;

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
	public List<Sprint> getAll() {
		Iterable<CSVRecord> records = sprintFileParser.parseAll();
		
		List<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}

	
	@Override
	public List<Sprint> getByNumber(int sprintNumber) {
		Iterable<CSVRecord> records = sprintFileParser.parseByNumber(sprintNumber);
		
		List<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}
	
	
	@Override
	public List<Sprint> getAllByNames(List<String> sprintNames) {
		Iterable<CSVRecord> records = sprintFileParser.parseByNames(sprintNames);
		
		List<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}
	
	
	/*public List<Sprint> getAllBetweenDates(Date beginDate, Date endDate) {
		Iterable<CSVRecord> records = sprintFileParser.parseBetweenDates(beginDate, endDate);
		
		List<Sprint> sprints = SprintMapper.mapRecords(records);
		
		return sprints;
	}*/

	
	@Override
	public Sprint getByName(String sprintName) {
		CSVRecord record = sprintFileParser.parseByName(sprintName);
		
		Sprint sprint = SprintMapper.mapRecord(record);
		
		return sprint;
	}

}
