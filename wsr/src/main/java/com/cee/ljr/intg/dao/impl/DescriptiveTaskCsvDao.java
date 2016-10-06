package com.cee.ljr.intg.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.domain.common.Developer;
import com.cee.ljr.intg.fileparser.impl.DescriptiveTaskCsvFileParser;

@Component
public class DescriptiveTaskCsvDao {
	
	@Autowired
	DescriptiveTaskCsvFileParser fileParser;
	
	
	public List<DescriptiveTask> getAllByDeveloperBetweenDates(String csvPaths, Developer developer, Date beginDate, Date endDate) {		
		return fileParser.parseTasksByDeveloperBetweenDates(csvPaths, developer.getNameInJira(), beginDate, endDate);
	}
	
}
