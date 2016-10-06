package com.cee.ljr.intg.dao.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.Sprint;
import com.cee.ljr.intg.dao.SprintDao;
import com.cee.ljr.intg.fileparser.SprintFileParser;

@Component
public class SprintCsvDao implements SprintDao {

	
	@Autowired
	private SprintFileParser sprintFileParser;
	
	@Override
	public List<Sprint> getAll() {
		return sprintFileParser.parseAll();
	}

	
	@Override
	public List<Sprint> getByNumber(int sprintNumber) {
		return sprintFileParser.parseByNumber(sprintNumber);
	}
	
	
	@Override
	public List<Sprint> getAllByNames(Collection<String> sprintNames) {
		return sprintFileParser.parseByNames(sprintNames);
	}
	
	
	public List<Sprint> getAllBetweenDates(Date beginDate, Date endDate) {
		return sprintFileParser.parseBetweenDates(beginDate, endDate);
	}
	
	
	@Override
	public Sprint getByName(String sprintName) {
		return sprintFileParser.parseByName(sprintName);
	}
	
	
	@Override
	public List<Sprint> getByKeys(Collection<String> sprintKeys) {
		return sprintFileParser.parseByKeys(sprintKeys);
	}

}
