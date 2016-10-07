package com.cee.ljr.intg.dao;

import java.util.Date;
import java.util.List;

import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.domain.common.Developer;

public interface DescriptiveTaskDao {

	//List<DescriptiveTask> getAllSprintTasksForDeveloper(String developerName, List<String> sprintNames);
	
	public List<DescriptiveTask> getAllByDeveloperBetweenDates(String csvPaths, Developer developer, Date beginDate, Date endDate);
}
