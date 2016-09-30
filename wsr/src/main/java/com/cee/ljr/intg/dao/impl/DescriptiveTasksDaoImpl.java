package com.cee.ljr.intg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cee.ljr.domain.common.DescriptiveTask;
import com.cee.ljr.intg.dao.DescriptiveTaskDao;

@Component
public class DescriptiveTasksDaoImpl implements DescriptiveTaskDao {

	@Autowired
	DescriptiveTaskCsvDao dtCsvDao;
	
	@Autowired
	EpicCsvDao epicCsvDao;
	
	public DescriptiveTasksDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DescriptiveTask> getAllSprintTasksForDeveloper(String developerName, List<String> sprintNames) {
		List<DescriptiveTask> tasksToJoin = dtCsvDao.getTasksByDeveloperAndSprints(developerName, sprintNames);
		return null;
	}
	
	private List<String> getEpicKeys(List<Desci>)

}
